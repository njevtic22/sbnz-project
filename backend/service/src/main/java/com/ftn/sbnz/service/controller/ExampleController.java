package com.ftn.sbnz.service.controller;

import com.ftn.sbnz.model.model.NivoNasilja;
import com.ftn.sbnz.model.model.Report;
import com.ftn.sbnz.model.model.Student;
import com.ftn.sbnz.service.service.HistoryItemService;
import com.ftn.sbnz.service.service.KnowledgeService;
import com.ftn.sbnz.service.service.RoleService;
import com.ftn.sbnz.service.service.StudentService;
import com.ftn.sbnz.service.util.LongGenerator;
import org.kie.api.runtime.KieSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("api/examples")
public class ExampleController {
    private final KnowledgeService knowledgeService;
    private final StudentService studentService;
    private final RoleService roleService;
    private final HistoryItemService historyItemService;
    private final LongGenerator reportId;

    public ExampleController(KnowledgeService knowledgeService, StudentService studentService, RoleService roleService, HistoryItemService historyItemService, LongGenerator reportId) {
        this.knowledgeService = knowledgeService;
        this.studentService = studentService;
        this.roleService = roleService;
        this.historyItemService = historyItemService;
        this.reportId = reportId;
    }

    @PostMapping("simple-example")
    public ResponseEntity<Void> setExample() {
        System.out.println("-------------------------------------------------- Start of controller --------------------------------------------------");
        KieSession kSession = knowledgeService.getkSession();

        Student student1 = studentService.getById(7L);
        Student student2 = studentService.getById(8L);

        Report report1 = new Report(reportId.next(), student1, NivoNasilja.PRVI);
        Report report2 = new Report(reportId.next(), student2, NivoNasilja.DRUGI);

        System.out.println("Student1 - " + student1.toString());
        System.out.println("Student1 - History size: " + student1.getHistory().size());
        System.out.println("Student1 - Nivo sklonosti ka nasilju: " + student1.getNivoSklonosti());

        System.out.println("Student2 - " + student2.toString());
        System.out.println("Student2 - History size: " + student2.getHistory().size());
        System.out.println("Student2 - Nivo sklonosti ka nasilju: " + student2.getNivoSklonosti());


        System.out.println("\n------------------------------\nRules firing");
        kSession.insert(student1);
        kSession.insert(report1);
        kSession.insert(student2);
        kSession.insert(report2);
        kSession.fireAllRules();
        System.out.println("------------------------------\nThe end of rules\n");

        historyItemService.saveAll(student1.getHistory());
        historyItemService.saveAll(student2.getHistory());

        student1 = studentService.save(student1);
        student2 = studentService.save(student2);

        System.out.println("Student1 - " + student1.toString());
        System.out.println("Student1 - History size: " + student1.getHistory().size());
        System.out.println(Arrays.toString(student1.getHistory().toArray()));
        System.out.println("Student1 - Nivo sklonosti ka nasilju: " + student1.getNivoSklonosti());

        System.out.println("Student2 - " + student2.toString());
        System.out.println("Student2 - History size: " + student2.getHistory().size());
        System.out.println(Arrays.toString(student2.getHistory().toArray()));
        System.out.println("Student2 - Nivo sklonosti ka nasilju: " + student2.getNivoSklonosti());


        System.out.println("--------------------------------------------------- End of controller ---------------------------------------------------");
        return ResponseEntity.noContent().build();
    }


    @PostMapping("execute-example")
    public ResponseEntity<Void> executeExample() {
        KieSession kSession = knowledgeService.getkSession();
        kSession.fireAllRules();
        return ResponseEntity.noContent().build();
    }

    @PostMapping("complex-example")
    public ResponseEntity<Void> executeComplexExample() {
        System.out.println("-------------------------------------------------- Start of controller --------------------------------------------------");
        KieSession kSession = knowledgeService.getkSession();

        Student student1 = studentService.getById(7L);

        Report report1 = new Report(reportId.next(), student1, NivoNasilja.PRVI);

        System.out.println("Student1 - " + student1.toString());
        System.out.println("Student1 - History size: " + student1.getHistory().size());
        System.out.println("Student1 - Nivo sklonosti ka nasilju: " + student1.getNivoSklonosti());



        System.out.println("\n------------------------------\nRules firing");
        kSession.insert(student1);
        kSession.insert(report1);
        kSession.fireAllRules();
        System.out.println("------------------------------\nThe end of rules\n");

        historyItemService.saveAll(student1.getHistory());

        student1 = studentService.save(student1);

        System.out.println("Student1 - " + student1.toString());
        System.out.println("Student1 - History size: " + student1.getHistory().size());
        System.out.println(Arrays.toString(student1.getHistory().toArray()));
        System.out.println("Student1 - Nivo sklonosti ka nasilju: " + student1.getNivoSklonosti());

        System.out.println("--------------------------------------------------- End of controller ---------------------------------------------------");
        return ResponseEntity.noContent().build();
    }
}
