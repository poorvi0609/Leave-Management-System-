package com.tekenlight.oms.modules.user.controllers;

import com.tekenlight.oms.modules.user.entities.User;
import com.tekenlight.oms.modules.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        return ResponseEntity.ok(userService.create(user));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable String id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable String id, @RequestBody User user) {
        return ResponseEntity.ok(userService.update(id, user));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<User> updateStatus(@PathVariable String id, @RequestParam User.UserStatus status) {
        return ResponseEntity.ok(userService.updateStatus(id, status));
    }

    @PatchMapping("/{id}/role")
    public ResponseEntity<User> updateRole(@PathVariable String id, @RequestParam User.Role role) {
        return ResponseEntity.ok(userService.updateRole(id, role));
    }

    @GetMapping("/{id}/reportees")
    public ResponseEntity<List<User>> getReportees(@PathVariable String id) {
        return ResponseEntity.ok(userService.getReportees(id));
    }
}