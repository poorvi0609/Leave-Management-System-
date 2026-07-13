package com.tekenlight.oms.modules.user.controllers;

import com.tekenlight.oms.modules.user.dto.UserRequestDto;
import com.tekenlight.oms.modules.user.dto.UserResponseDto;
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
    public ResponseEntity<UserResponseDto> create(@RequestBody UserRequestDto dto) {
        return ResponseEntity.ok(userService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable String id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> update(@PathVariable String id, @RequestBody UserRequestDto dto) {
        return ResponseEntity.ok(userService.update(id, dto));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<UserResponseDto> updateStatus(@PathVariable String id, @RequestParam User.UserStatus status) {
        return ResponseEntity.ok(userService.updateStatus(id, status));
    }

    @PatchMapping("/{id}/role")
    public ResponseEntity<UserResponseDto> updateRole(@PathVariable String id, @RequestParam User.Role role) {
        return ResponseEntity.ok(userService.updateRole(id, role));
    }

    @GetMapping("/{id}/reportees")
    public ResponseEntity<List<UserResponseDto>> getReportees(@PathVariable String id) {
        return ResponseEntity.ok(userService.getReportees(id));
    }
}