package com.ftn.sbnz.service.service;

import com.ftn.sbnz.model.model.HistoryItem;
import com.ftn.sbnz.model.model.Report;
import com.ftn.sbnz.model.model.Student;
import com.ftn.sbnz.service.repository.HistoryItemRepository;
import org.kie.api.runtime.KieSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class HistoryItemServiceImpl implements HistoryItemService {
    private final HistoryItemRepository repository;
    private final StudentService studentService;
    private final KnowledgeService knowledgeService;

    public HistoryItemServiceImpl(HistoryItemRepository repository, StudentService studentService, KnowledgeService knowledgeService) {
        this.repository = repository;
        this.studentService = studentService;
        this.knowledgeService = knowledgeService;
    }

    @Override
    public HistoryItem reportStudent(Long studentId, Report reportData) {
        System.out.println("-------------------------------------------------- Start of reporting --------------------------------------------------");
        Student reported = studentService.getById(studentId);
        Report report = new Report(reportData.getId(), reported, reportData.getNivoNasilja(), reportData.getTipNasilja(), reportData.getOblikNasilja(), reportData.getOpis());

        System.out.println("Reported - " + reported.getName() + " " + reported.getSurname());
        System.out.println("Reported - History size: " + reported.getHistory().size());
        System.out.println("Reported - Nivo sklonosti ka nasilju: " + reported.getNivoSklonosti());

        KieSession kSession = knowledgeService.getkSession();
        System.out.println("\n------------------------------\nRules firing");
        kSession.insert(reported);
        kSession.insert(report);
        kSession.fireAllRules();
        System.out.println("------------------------------\nThe end of rules\n");

        saveAll(reported.getHistory());
        reported = studentService.save(reported);

        System.out.println("Reported - " + reported.getName() + " " + reported.getSurname());
        System.out.println("Reported - History size: " + reported.getHistory().size());
        System.out.println(Arrays.toString(reported.getHistory().toArray()));
        System.out.println("Reported - Nivo sklonosti ka nasilju: " + reported.getNivoSklonosti());

        System.out.println("--------------------------------------------------- End of reporting ---------------------------------------------------");
        return reported.getHistory().get(reported.getHistory().size() - 1);
    }

    @Override
    public List<HistoryItem> saveAll(Iterable<HistoryItem> history) {
        return repository.saveAll(history);
    }

    @Override
    public Page<HistoryItem> getAll(Long studentId, Pageable pageable) {
        return repository.findAllByStudentId(studentId, pageable);
    }
}
