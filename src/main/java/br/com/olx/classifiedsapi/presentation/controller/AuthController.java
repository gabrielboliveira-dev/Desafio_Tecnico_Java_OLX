package br.com.olx.classifiedsapi.presentation.controller;

import br.com.olx.classifiedsapi.application.dto.auth.AuthResponseDTO;
import br.com.olx.classifiedsapi.application.dto.auth.LoginDTO;
import br.com.olx.classifiedsapi.application.dto.auth.RegisterDTO;
import br.com.olx.classifiedsapi.application.service.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@Valid @RequestBody RegisterDTO request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginDTO request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}