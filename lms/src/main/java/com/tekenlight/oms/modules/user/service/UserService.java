package com.tekenlight.oms.modules.user.service;

import com.tekenlight.oms.modules.user.entities.User;
import com.tekenlight.oms.modules.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User create(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("User with this email already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(User.UserStatus.ACTIVE);
        return userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User update(String id, User updated) {
        User existing = getById(id);
        existing.setFirstName(updated.getFirstName());
        existing.setLastName(updated.getLastName());
        existing.setDepartment(updated.getDepartment());
        existing.setDesignation(updated.getDesignation());
        existing.setReportingManager(updated.getReportingManager());
        existing.setJoiningDate(updated.getJoiningDate());
        return userRepository.save(existing);
    }

    public User updateStatus(String id, User.UserStatus status) {
        User user = getById(id);
        user.setStatus(status);
        return userRepository.save(user);
    }

    public User updateRole(String id, User.Role role) {
        User user = getById(id);
        user.setRole(role);
        return userRepository.save(user);
    }

    public List<User> getReportees(String managerId) {
        return userRepository.findByReportingManagerId(managerId);
    }
}