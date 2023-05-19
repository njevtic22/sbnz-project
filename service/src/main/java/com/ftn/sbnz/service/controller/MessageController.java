package com.ftn.sbnz.service.controller;

import com.ftn.sbnz.model.model.Message;
import com.ftn.sbnz.model.model.ParentMessage;
import com.ftn.sbnz.model.util.KnowledgeSessionHelper;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/messages")
public class MessageController {
    @PostMapping
    public ResponseEntity<Void> addMessage() {
        KieContainer kc = KnowledgeSessionHelper.createRuleBase();
        KieSession kSession = KnowledgeSessionHelper.getStatefulKnowledgeSession(kc, "k-session");


        // KieSession kSession1 = kContainer.newKieSession("example-session");
        // insertovanje fact-a
        ParentMessage message = new Message();
        message.setMessage("Hello World");
        message.setStatus(Message.HELLO);
        kSession.insert(message);
        kSession.fireAllRules();

        return ResponseEntity.noContent().build();
    }
}
