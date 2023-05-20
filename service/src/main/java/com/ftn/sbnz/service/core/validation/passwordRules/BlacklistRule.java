package com.ftn.sbnz.service.core.validation.passwordRules;

import org.passay.PasswordData;
import org.passay.Rule;
import org.passay.RuleResult;
import org.passay.RuleResultMetadata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class BlacklistRule implements Rule {
    public static String ERROR_CODE = "FORBIDDEN_PASSWORD";
    private final String blacklistPath;

    public BlacklistRule(String blacklistPath) {
        this.blacklistPath = blacklistPath;
    }

    @Override
    public RuleResult validate(PasswordData passwordData) {
        RuleResult result = new RuleResult();
        String password = passwordData.getPassword();

        try (BufferedReader in = new BufferedReader(new FileReader(new File(blacklistPath)))) {
            String forbiddenPassword = "";
            while ((forbiddenPassword = in.readLine()) != null) {
                if (forbiddenPassword.equals(password)) {
                    result.addError(ERROR_CODE, createRuleResultDetailParameters(forbiddenPassword));
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        result.setMetadata(createRuleResultMetadata(passwordData));
        return result;
    }

    private Map<String, Object> createRuleResultDetailParameters(String forbiddenPassword) {
        Map<String, Object> parameters = new LinkedHashMap<>();          // LinkedHashMap, because order is important in message (passay-messages.properties)
        parameters.put("forbiddenPassword", forbiddenPassword);
        return parameters;
    }

    private RuleResultMetadata createRuleResultMetadata(PasswordData passwordData) {
        return new RuleResultMetadata(RuleResultMetadata.CountCategory.Illegal, passwordData.getPassword().length());
    }
}
