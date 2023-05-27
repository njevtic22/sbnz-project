package com.ftn.sbnz.service.controller;

import com.ftn.sbnz.service.dto.auth.LoginDto;
import com.ftn.sbnz.service.dto.auth.TokenDto;
import com.ftn.sbnz.service.service.LoginService;
import com.ftn.sbnz.service.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/auth")
public class AuthenticationController {
    private final LoginService loginService;

    public AuthenticationController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("login")
    public ResponseEntity<TokenDto> login(@Valid @RequestBody LoginDto loginDto) {
        Pair<String, String> loginData = loginService.login(loginDto.getUsername(), loginDto.getPassword());
        TokenDto tokenDto = new TokenDto(loginData.getFirst(), loginData.getSecond());
        return ResponseEntity.ok(tokenDto);
    }
}
