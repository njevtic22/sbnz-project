package com.ftn.sbnz.service.mapper;

import com.ftn.sbnz.model.model.Student;
import com.ftn.sbnz.service.dto.student.AddStudentDto;
import com.ftn.sbnz.service.dto.student.StudentViewDto;
import com.ftn.sbnz.service.dto.student.UpdateStudentDto;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    public Student toModel(AddStudentDto addStudentDto) {
        return new Student(
                addStudentDto.getName(),
                addStudentDto.getSurname(),
                addStudentDto.getBirthDate(),
                addStudentDto.getJmbg(),
                addStudentDto.getEmail(),
                addStudentDto.getUsername(),
                addStudentDto.getPassword(),
                false,
                null,
                null,
                null
        );
    }

    public Student toModel(UpdateStudentDto updateStudentDto) {
        return new Student(
                updateStudentDto.getName(),
                updateStudentDto.getSurname(),
                updateStudentDto.getBirthDate(),
                updateStudentDto.getJmbg(),
                updateStudentDto.getEmail(),
                updateStudentDto.getUsername(),
                "",
                false,
                null,
                null,
                null
        );
    }

    public StudentViewDto toViewDto(Student student) {
        return new StudentViewDto(
                student.getId(),
                student.getName(),
                student.getSurname(),
                student.getBirthDate(),
                student.getJmbg(),
                student.getEmail(),
                student.getUsername(),
                student.getRole().getName(),
                student.getNivoSklonosti().toString()
        );
    }
}
