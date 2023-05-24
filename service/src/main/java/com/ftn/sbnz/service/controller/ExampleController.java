package com.ftn.sbnz.service.controller;

import com.ftn.sbnz.model.model.NivoNasilja;
import com.ftn.sbnz.model.model.NivoSklonostiKaNasilju;
import com.ftn.sbnz.model.model.Odeljenje;
import com.ftn.sbnz.model.model.Report;
import com.ftn.sbnz.model.model.Role;
import com.ftn.sbnz.model.model.Student;
import com.ftn.sbnz.model.model.Teacher;
import com.ftn.sbnz.service.service.KnowledgeService;
import org.kie.api.runtime.KieSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

@RestController
@RequestMapping("api/examples")
public class ExampleController {
    private final KnowledgeService knowledgeService;

    public ExampleController(KnowledgeService knowledgeService) {
        this.knowledgeService = knowledgeService;
    }

    @PostMapping("set-example")
    public ResponseEntity<Void> setExample() {
        KieSession kSession = knowledgeService.getkSession();

        Role teacherRole = new Role("ROLE_TEACHER");
        Role studentRole = new Role("ROLE_STUDENT");

        Teacher teacher1 = new Teacher(1L, "Dušan", "Bojanić", LocalDate.of(1980, 5, 5), "0505980000000", "teacheremail1@gmail.com", "teacherusername1", "password", false, teacherRole);

        LocalDate studentBirthDate = LocalDate.of(2007, 4, 4);
        final int STUDENTS_NUM = 1;
        ArrayList<Student> students = new ArrayList<>(STUDENTS_NUM);

        long studentId = 0;

        studentId++;
        Student student1 = new Student(
                studentId,
                "ime" + studentId,
                "prezime" + studentId,
                studentBirthDate,
                "0404007000000",
                "studentmail" + studentId+ "@gmail.com",
                "studentusername" + studentId + "@gmail.com",
                "password",
                false,
                studentRole,
                NivoSklonostiKaNasilju.NEMA,
                new ArrayList<>()
        );
        students.add(student1);

        studentId++;
        Student student2 = new Student(
                studentId,
                "ime" + studentId,
                "prezime" + studentId,
                studentBirthDate,
                "0404007000000",
                "studentmail" + studentId+ "@gmail.com",
                "studentusername" + studentId + "@gmail.com",
                "password",
                false,
                studentRole,
                NivoSklonostiKaNasilju.NEMA,
                new ArrayList<>()
        );
        students.add(student2);


        Odeljenje odeljenjeVII3 = new Odeljenje(1L, "VII-3", teacher1, students);


        Report report1 = new Report(1L, student1, NivoNasilja.PRVI);
        Report report2 = new Report(2L, student2, NivoNasilja.DRUGI);

        System.out.println("Student1");
        System.out.println("Student1 - History size: " + student1.getHistory().size());
        System.out.println("Student1 - Nivo sklonosti ka nasilju: " + student1.getNivoSklonosti());

        System.out.println("Student2");
        System.out.println("Student2 - History size: " + student2.getHistory().size());
        System.out.println("Student2 - Nivo sklonosti ka nasilju: " + student2.getNivoSklonosti());


        System.out.println("\n------------------------------\nRules firing");
        kSession.insert(student1);
        kSession.insert(report1);
        kSession.insert(student2);
        kSession.insert(report2);
        kSession.fireAllRules();
        System.out.println("------------------------------\nThe end of rules\n");

        System.out.println("Student1");
        System.out.println("Student1 - History size: " + student1.getHistory().size());
        System.out.println(Arrays.toString(student1.getHistory().toArray()));
        System.out.println("Student1 - Nivo sklonosti ka nasilju: " + student1.getNivoSklonosti());

        System.out.println("Student2");
        System.out.println("Student2 - History size: " + student2.getHistory().size());
        System.out.println(Arrays.toString(student2.getHistory().toArray()));
        System.out.println("Student2 - Nivo sklonosti ka nasilju: " + student2.getNivoSklonosti());

        return ResponseEntity.noContent().build();
    }


    @PostMapping("execute-example")
    public ResponseEntity<Void> executeExample() {
        KieSession kSession = knowledgeService.getkSession();
        kSession.fireAllRules();
        return ResponseEntity.noContent().build();
    }
}
