package com.tekenlight.lms.module.leavepolicy.controller;

import com.tekenlight.lms.module.leavepolicy.entity.LeavePolicy;
import com.tekenlight.lms.module.leavepolicy.service.LeavePolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/leave-policies")
@RequiredArgsConstructor
public class LeavePolicyController {

    private final LeavePolicyService leavePolicyService;
    @PostMapping
    public ResponseEntity<LeavePolicy> create(@RequestBody LeavePolicy leavePolicy) {
        return ResponseEntity.ok(leavePolicyService.create(leavePolicy));
    }
    @GetMapping
    public ResponseEntity<List<LeavePolicy>> getAll() {
        return ResponseEntity.ok(leavePolicyService.getAll());
    }
    @GetMapping("/active")
    public ResponseEntity<List<LeavePolicy>> getActive() {
        return ResponseEntity.ok(leavePolicyService.getActive());
    }
    @GetMapping("/{id}")
    public ResponseEntity<LeavePolicy> getById(@PathVariable Long id) {
        return ResponseEntity.ok(leavePolicyService.getById(id));
    }
    @GetMapping("/code/{policyCode}")
    public ResponseEntity<LeavePolicy> getByCode(@PathVariable String policyCode) {
        return ResponseEntity.ok(leavePolicyService.getByCode(policyCode));
    }
    @PutMapping("/{id}")
    public ResponseEntity<LeavePolicy> update(@PathVariable Long id, @RequestBody LeavePolicy leavePolicy) {
        return ResponseEntity.ok(leavePolicyService.update(id, leavePolicy));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        leavePolicyService.delete(id);
        return ResponseEntity.noContent().build();
    }
}