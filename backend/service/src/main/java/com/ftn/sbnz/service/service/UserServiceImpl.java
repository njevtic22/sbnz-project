package com.ftn.sbnz.service.service;

import com.ftn.sbnz.model.model.User;
import com.ftn.sbnz.service.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Map<String, List<String>> getTakenEmailsAndUsernames() {
        List<User> allUsers = repository.findAll();
        ArrayList<String> emails = new ArrayList<>(allUsers.size());
        ArrayList<String> usernames = new ArrayList<>(allUsers.size());

        for (User user : allUsers) {
            emails.add(user.getEmail());
            usernames.add(user.getUsername());
        }

        HashMap<String, List<String>> taken = new HashMap<>(2);
        taken.put("emails", emails);
        taken.put("usernames", usernames);

        return taken;
    }

    @Override
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }
}
