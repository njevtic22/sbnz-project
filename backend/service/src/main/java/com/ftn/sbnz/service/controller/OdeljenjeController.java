package com.ftn.sbnz.service.controller;

import com.ftn.sbnz.model.model.Odeljenje;
import com.ftn.sbnz.service.core.PaginatedResponse;
import com.ftn.sbnz.service.dto.odeljenje.AddOdeljenjeDto;
import com.ftn.sbnz.service.dto.odeljenje.OdeljenjeViewDto;
import com.ftn.sbnz.service.dto.odeljenje.UpdateOdeljenjeDto;
import com.ftn.sbnz.service.dto.teacher.TeacherViewDto;
import com.ftn.sbnz.service.mapper.OdeljenjeMapper;
import com.ftn.sbnz.service.mapper.TeacherMapper;
import com.ftn.sbnz.service.service.OdeljenjeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/odeljenja")
public class OdeljenjeController {
    private final OdeljenjeService service;
    private final OdeljenjeMapper odeljenjeMapper;
    private final TeacherMapper teacherMapper;

    public OdeljenjeController(OdeljenjeService service, OdeljenjeMapper odeljenjeMapper, TeacherMapper teacherMapper) {
        this.service = service;
        this.odeljenjeMapper = odeljenjeMapper;
        this.teacherMapper = teacherMapper;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> addOdeljenje(@Valid @RequestBody AddOdeljenjeDto newOdeljenjeDto, UriComponentsBuilder uriBuilder) {
        Odeljenje newOdeljenje = odeljenjeMapper.toModel(newOdeljenjeDto);
        Odeljenje added = service.add(newOdeljenje);

        URI uri = uriBuilder
                .path("/api/odeljenja/{id}")
                .buildAndExpand(added.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PaginatedResponse<OdeljenjeViewDto>> getOdeljenja(Pageable pageable) {
        Page<Odeljenje> allOdeljenja = service.getAll(pageable);

        ArrayList<OdeljenjeViewDto> odeljenjaDto = new ArrayList<>(10);
        for (Odeljenje odeljenje : allOdeljenja) {
            odeljenjaDto.add(
                    odeljenjeMapper.toViewDto(
                            odeljenje,
                            teacherMapper.toViewDto(odeljenje.getStaresina())
                    )
            );
        }

        PaginatedResponse<OdeljenjeViewDto> responseDto = new PaginatedResponse<>(
                odeljenjaDto,
                allOdeljenja.getTotalElements(),
                allOdeljenja.getTotalPages()
        );
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<OdeljenjeViewDto> getOdeljenje(@PathVariable Long id) {
        Odeljenje found = service.getById(id);
        TeacherViewDto teacherDto = teacherMapper.toViewDto(found.getStaresina());
        OdeljenjeViewDto foundDto = odeljenjeMapper.toViewDto(found, teacherDto);
        return ResponseEntity.ok(foundDto);
    }

    @GetMapping("/for-teacher")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<OdeljenjeViewDto> getOdeljenjeForTeacher() {
        Odeljenje found = service.getForTeacher();
        if (found == null) {
            return ResponseEntity.ok(null);
        }

        TeacherViewDto teacherDto = teacherMapper.toViewDto(found.getStaresina());
        OdeljenjeViewDto foundDto = odeljenjeMapper.toViewDto(found, teacherDto);
        return ResponseEntity.ok(foundDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OdeljenjeViewDto> updateOdeljenje(@PathVariable Long id, @Valid @RequestBody UpdateOdeljenjeDto changesDto) {
        Odeljenje changes = odeljenjeMapper.toModel(changesDto);
        Odeljenje updated = service.update(id, changes);

        TeacherViewDto teacherDto = teacherMapper.toViewDto(updated.getStaresina());
        OdeljenjeViewDto updatedDto = odeljenjeMapper.toViewDto(updated, teacherDto);

        return ResponseEntity.ok(updatedDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteOdeljenje(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
