package com.ftn.sbnz.service.controller;

import com.ftn.sbnz.model.model.NivoNasilja;
import com.ftn.sbnz.model.model.OblikNasilja;
import com.ftn.sbnz.model.model.Report;
import com.ftn.sbnz.model.model.Student;
import com.ftn.sbnz.model.model.TipNasilja;
import com.ftn.sbnz.service.service.HistoryItemService;
import com.ftn.sbnz.service.service.KnowledgeService;
import com.ftn.sbnz.service.service.RoleService;
import com.ftn.sbnz.service.service.StudentService;
import com.ftn.sbnz.service.util.LongGenerator;
import org.kie.api.runtime.KieSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PostMapping("second-level-example/{id}")
    public ResponseEntity<Void> setExample(@PathVariable Long id) {
        System.out.println("-------------------------------------------------- Start of controller --------------------------------------------------");
        KieSession kSession = knowledgeService.getkSession();

        Student student1 = studentService.getById(id);

        Report report1 = new Report(reportId.next(), student1, NivoNasilja.DRUGI, TipNasilja.FIZICKO, OblikNasilja.SAMARANJE, "Neki dugacak opis");

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


    @PostMapping("execute-example")
    public ResponseEntity<Void> executeExample() {
        KieSession kSession = knowledgeService.getkSession();
        kSession.fireAllRules();
        return ResponseEntity.noContent().build();
    }

    @PostMapping("first-level-example/{id}")
    public ResponseEntity<Void> executeComplexExample(@PathVariable Long id) {
        System.out.println("-------------------------------------------------- Start of controller --------------------------------------------------");
        KieSession kSession = knowledgeService.getkSession();

        Student student1 = studentService.getById(id);

        Report report1 = new Report(reportId.next(), student1, NivoNasilja.PRVI, TipNasilja.FIZICKO, OblikNasilja.UDARANJE_CVRGA, "Neki dugacki opis");

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
