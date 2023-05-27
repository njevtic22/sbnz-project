package com.ftn.sbnz.service.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

@Service
public class KnowledgeService {
    private final KieContainer kieContainer;
    private final KieSession kSession;

    public KnowledgeService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
        this.kSession = kieContainer.newKieSession("k-session");
    }

    public KieContainer getKieContainer() {
        return kieContainer;
    }

    public KieSession getkSession() {

        return kSession;
    }
}
