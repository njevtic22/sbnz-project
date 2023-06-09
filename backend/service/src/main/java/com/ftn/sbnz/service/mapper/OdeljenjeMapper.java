package com.ftn.sbnz.service.mapper;

import com.ftn.sbnz.model.model.Odeljenje;
import com.ftn.sbnz.model.model.Student;
import com.ftn.sbnz.model.model.Teacher;
import com.ftn.sbnz.service.dto.odeljenje.AddOdeljenjeDto;
import com.ftn.sbnz.service.dto.odeljenje.OdeljenjeViewDto;
import com.ftn.sbnz.service.dto.odeljenje.UpdateOdeljenjeDto;
import com.ftn.sbnz.service.dto.teacher.TeacherViewDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OdeljenjeMapper {
    public Odeljenje toModel(AddOdeljenjeDto addOdeljenjeDto) {
        return new Odeljenje(
                addOdeljenjeDto.getNaziv(),
                new Teacher(
                        addOdeljenjeDto.getStaresinaId(),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        false,
                        null
                ),
                new ArrayList<>(),
                false
        );
    }

    public Odeljenje toModel(UpdateOdeljenjeDto updateOdeljenjeDto) {
        return new Odeljenje(
                updateOdeljenjeDto.getNaziv(),
                new Teacher(
                        updateOdeljenjeDto.getStaresinaId(),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        false,
                        null
                ),
                new ArrayList<>(),
                false
        );
    }

    public OdeljenjeViewDto toViewDto(Odeljenje odeljenje, TeacherViewDto teacherViewDto) {
        return new OdeljenjeViewDto(
                odeljenje.getId(),
                odeljenje.getNaziv(),
                countNotArchivedStudents(odeljenje.getUcenici()),
                teacherViewDto
        );
    }

    private long countNotArchivedStudents(List<Student> students) {
        long notArchived = 0;
        for (Student student : students) {
            if (!student.isArchived()) {
                notArchived++;
            }
        }
        return notArchived;
    }
}
