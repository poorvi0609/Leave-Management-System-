package com.tekenlight.oms.modules.auth.controllers;

import com.tekenlight.oms.modules.auth.entities.RefreshToken;
import com.tekenlight.oms.modules.auth.service.AuthService;
import com.tekenlight.oms.modules.user.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");
        User user = authService.login(email, password);
        RefreshToken refreshToken = authService.createRefreshToken(user);
        return ResponseEntity.ok(Map.of(
                "user", user,
                "refreshToken", refreshToken.getToken()
        ));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody Map<String, String> request) {
        String token = request.get("refreshToken");
        RefreshToken refreshToken = authService.validateRefreshToken(token);
        return ResponseEntity.ok(Map.of(
                "user", refreshToken.getUser()
        ));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody Map<String, String> request) {
        String token = request.get("refreshToken");
        authService.logout(token);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(@RequestParam String email) {
        User user = authService.getMe(email);
        return ResponseEntity.ok(user);
    }
}