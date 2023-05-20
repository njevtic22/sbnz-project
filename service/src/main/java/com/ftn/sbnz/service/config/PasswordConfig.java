package com.ftn.sbnz.service.config;

import com.ftn.sbnz.service.core.validation.passwordRules.BlacklistRule;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.EnglishSequenceData;
import org.passay.IllegalSequenceRule;
import org.passay.LengthRule;
import org.passay.PasswordValidator;
import org.passay.PropertiesMessageResolver;
import org.passay.WhitespaceRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class PasswordConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PasswordValidator passwordValidator() throws IOException {
        final String passayMessages = "passay-messages.properties";
        final String passwordBlacklist = "password-blacklist.txt";
        final String separator = System.getProperty("file.separator");

        // For some reason default cwd is ...\sbnz-project\kjar
        // But it needs to be in ...\sbnz-project\service
        final String cwd = "." + separator + ".." + separator + "service";      // .\..\service
        final String resourcesPath = cwd + separator + "src" + separator + "main" + separator + "resources";
        final String passayMessagesPath = resourcesPath + separator + passayMessages;
        final String passwordBlacklistPath = resourcesPath + separator + passwordBlacklist;

        Properties messageProperties = new Properties();
        messageProperties.load(new BufferedReader(new FileReader(new File(passayMessagesPath))));
        PropertiesMessageResolver resolver = new PropertiesMessageResolver(messageProperties);

        return new PasswordValidator(
                resolver,

                // Positive matching rules
                new LengthRule(8, 50),
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                new CharacterRule(EnglishCharacterData.LowerCase, 1),
                new CharacterRule(EnglishCharacterData.Digit, 1),

                // ! " # $ % & ' ( ) * + , - . / : ; < = > ? @ [ \ ] ^ _ ` { | } ~ ¡ ¢ £ ¤ ¥ ¦ § ¨ © ª « ¬ ­ ® ¯ ° ± ² ³ ´ µ ¶ · ¸ ¹ º » ¼ ½ ¾ ¿ × ÷ – — ― ‗ ‘ ’ ‚ ‛ “ ” „ † ‡ • … ‰ ′ ″ ‹ › ‼ ‾ ⁄ ⁊ ₠ ₡ ₢ ₣ ₤ ₥ ₦ ₧ ₨ ₩ ₪ ₫ € ₭ ₮ ₯ ₰ ₱ ₲ ₳ ₴ ₵ ₶ ₷ ₸ ₹ ₺ ₻ ₼ ₽ ₾
                new CharacterRule(EnglishCharacterData.Special, 1),

                // Negative matching rules
                new IllegalSequenceRule(EnglishSequenceData.Alphabetical, 5, false),
                new IllegalSequenceRule(EnglishSequenceData.Numerical, 5, false),
                new IllegalSequenceRule(EnglishSequenceData.USQwerty, 5, false),
                new WhitespaceRule(),
                /*
                 * Blacklist source
                 * https://github.com/danielmiessler/SecLists/blob/master/Passwords/Common-Credentials/10-million-password-list-top-1000000.txt
                 * */
                new BlacklistRule(passwordBlacklistPath)
        );
    }

//    private String getServiceDirectory() {
//        final String separator = System.getProperty("file.separator");
//        System.out.println(System.getProperty("user.dir"));
//        String cwd = System.getProperty("user.dir");
//        List<String> cwdSplit = new ArrayList<>(Arrays.asList(cwd.split(separator)));
//        while (!cwdSplit.get(cwdSplit.size() - 1).equals("sbnz-project")) {
//            cwdSplit.remove(cwdSplit.size() - 1);
//        }
//        cwdSplit.add("service");
//        String correctCwd = String.join(separator, cwdSplit);
//        System.out.println(correctCwd);
//        return correctCwd;
//    }
}
