package com.ftn.sbnz.service.service;

import com.ftn.sbnz.model.model.Odeljenje;
import com.ftn.sbnz.model.model.School;
import com.ftn.sbnz.model.model.Teacher;
import com.ftn.sbnz.service.core.error.exceptions.EntityNotFoundException;
import com.ftn.sbnz.service.core.error.exceptions.MultipleDeletedRowsException;
import com.ftn.sbnz.service.core.error.exceptions.StaresinaTakenException;
import com.ftn.sbnz.service.core.error.exceptions.UniquePropertyException;
import com.ftn.sbnz.service.repository.OdeljenjeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class OdeljenjeServiceImpl implements OdeljenjeService {
    private final OdeljenjeRepository repository;
    private final TeacherService teacherService;
    private final SchoolService schoolService;

    public OdeljenjeServiceImpl(OdeljenjeRepository repository, TeacherService teacherService, SchoolService schoolService) {
        this.repository = repository;
        this.teacherService = teacherService;
        this.schoolService = schoolService;
    }

    @Override
    public boolean existsByNaziv(String naziv) {
        return repository.existsByNazivAndArchivedFalse(naziv);
    }

    @Override
    public Odeljenje save(Odeljenje toSave) {
        return repository.save(toSave);
    }

    @Override
    public Odeljenje add(Odeljenje newOdeljenje) {
        validateNaziv(newOdeljenje.getNaziv());

        Teacher staresina = teacherService.getById(newOdeljenje.getStaresina().getId());
        validateStaresina(staresina);

        Odeljenje toAdd = new Odeljenje(
                newOdeljenje.getNaziv(),
                staresina,
                new ArrayList<>(),
                false
        );

        Odeljenje added = repository.save(toAdd);

        School school = schoolService.getById();
        school.addOdeljenje(added);
        schoolService.save(school);

        return added;
    }

    @Override
    public Page<Odeljenje> getAll(Pageable pageable) {
        return repository.findAllByArchivedFalse(pageable);
    }

    @Override
    public Odeljenje getById(Long id) {
        Objects.requireNonNull(id, "Id must not be null.");
        return repository.findByIdAndArchivedFalse(id)
                .orElseThrow(() -> new EntityNotFoundException("Odeljenje", id));
    }

    @Override
    public Odeljenje update(Long id, Odeljenje changes) {
        // Without this check, NullPointerException (if changes is null) would be thrown after orm mapping (getById)
        // which will render orm mapping useless
        Objects.requireNonNull(changes, "Changes must not be null.");

        Odeljenje exist = getById(id);
        if (!exist.getNaziv().equals(changes.getNaziv())) {
            validateNaziv(changes.getNaziv());
        }

        Teacher newStaresina = teacherService.getById(changes.getStaresina().getId());
        if (!exist.getStaresina().equals(newStaresina)) {
            validateStaresina(newStaresina);
        }

        Odeljenje updated = new Odeljenje(
                exist.getId(),
                changes.getNaziv(),
                newStaresina,
                exist.getUcenici(),
                exist.isArchived()
        );
        return repository.save(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Objects.requireNonNull(id, "Id must not be null");

        if (!repository.existsByIdAndArchivedFalse(id)) {
            throw new EntityNotFoundException("Odeljenje", id);
        }

        int rowsAffected = repository.archiveById(id);
        if (rowsAffected != 1) {
            throw new MultipleDeletedRowsException("Odeljenja");
        }
    }

    private void validateNaziv(String naziv) {
        if (existsByNaziv(naziv)) {
            throw new UniquePropertyException("Class name '" + naziv + "' is already taken.");
        }
    }

    private void validateStaresina(Teacher teacher) {
        List<Odeljenje> odeljenja = getAll(PageRequest.ofSize(Integer.MAX_VALUE)).getContent();
        for (Odeljenje odeljenje : odeljenja) {
            if (odeljenje.getStaresina().equals(teacher)) {
                throw new StaresinaTakenException(teacher.getId());
            }
        }
    }
}
