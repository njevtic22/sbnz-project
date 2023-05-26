package com.ftn.sbnz.service.service;

import com.ftn.sbnz.service.util.Pair;

public interface LoginService {
    Pair<String, String> login(String username, String password);
}
