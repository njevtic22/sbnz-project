package com.ftn.sbnz.service.mapper;

import com.ftn.sbnz.model.model.Teacher;
import com.ftn.sbnz.service.dto.teacher.AddTeacherDto;
import com.ftn.sbnz.service.dto.teacher.TeacherViewDto;
import com.ftn.sbnz.service.dto.teacher.UpdateTeacherDto;
import org.springframework.stereotype.Component;

@Component
public class TeacherMapper {
    public Teacher toModel(AddTeacherDto addTeacherDto) {
        return new Teacher(
                addTeacherDto.getName(),
                addTeacherDto.getSurname(),
                addTeacherDto.getBirthDate(),
                addTeacherDto.getJmbg(),
                addTeacherDto.getEmail(),
                addTeacherDto.getUsername(),
                addTeacherDto.getPassword(),
                false,
                null
        );
    }

    public Teacher toModel(UpdateTeacherDto updateTeacherDto) {
        return new Teacher(
                updateTeacherDto.getName(),
                updateTeacherDto.getSurname(),
                updateTeacherDto.getBirthDate(),
                updateTeacherDto.getJmbg(),
                updateTeacherDto.getEmail(),
                updateTeacherDto.getUsername(),
                "",
                false,
                null
        );
    }

    public TeacherViewDto toViewDto(Teacher teacher) {
        return new TeacherViewDto(
                teacher.getId(),
                teacher.getName(),
                teacher.getSurname(),
                teacher.getBirthDate(),
                teacher.getJmbg(),
                teacher.getEmail(),
                teacher.getUsername(),
                teacher.getRole().getName()
        );
    }
}
