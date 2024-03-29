package com.ftn.sbnz.service.config;

import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KjarConfiguration {
    @Bean
    public KieContainer kieContainer() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks
                .newKieContainer(ks.newReleaseId("com.ftn.sbnz", "kjar", "0.0.1-SNAPSHOT"));
        KieScanner kScanner = ks.newKieScanner(kContainer);
        kScanner.start(1000);
        return kContainer;
    }
}

/*
package com.ftn.sbnz.service;
//import org.kie.api.definition.type.FactType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
//import org.kie.api.runtime.rule.FactHandle;

import com.ftn.sbnz.model.model.Message;
import com.ftn.sbnz.model.util.KnowledgeSessionHelper;


public class Test{
    public static void main(){
        try{
            // instanciranje
            KieContainer kc = KnowledgeSessionHelper.createRuleBase();
            KieSession kSession = KnowledgeSessionHelper.getStatefulKnowledgeSession(kc, "k-session");


           // KieSession kSession1 = kContainer.newKieSession("example-session");
            // insertovanje fact-a
            Message message = new Message();
            message.setMessage("Hello World");
            message.setStatus(Message.HELLO);
            kSession.insert(message);
            kSession.fireAllRules();

            // kSession.getAgenda().getAgendaGroup("promotions").setFocus();
            // kSession.fireAllRules();

            // FactHandle messageHandle = kSession.insert(message);
            // message.setMessage("new update");
            // kSession.update(messageHandle, message);
            // kSession.delete(messageHandle);
            // kSession.fireAllRules();
            // kSession.setGlobal("newGlobal", 2);

            // FactType type = kSession.getKieBase().getFactType(
            //     "chapter04.declaredTypes", "SpecialOrder");
            // Object instance = type.newInstance();
            // type.set(instance, "relevance", 2L);
           // Object attr = type.get(instance, "relevance");

        }catch(Throwable t){
            t.printStackTrace();
        }
    }
}
 */
