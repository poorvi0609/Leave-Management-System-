package com.tekenlight.lms.module.leavetype.controller;

import com.tekenlight.lms.module.leavetype.entity.LeaveType;
import com.tekenlight.lms.module.leavetype.service.LeaveTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/leave-types")
@RequiredArgsConstructor
public class LeaveTypeController {
    private final LeaveTypeService leaveTypeService;
    @PostMapping
    public ResponseEntity<LeaveType> create(@RequestBody LeaveType leaveType) {
        return ResponseEntity.ok(leaveTypeService.create(leaveType));
    }
    @GetMapping
    public ResponseEntity<List<LeaveType>> getAll() {
        return ResponseEntity.ok(leaveTypeService.getAll());
    }
    @GetMapping("/active")
    public ResponseEntity<List<LeaveType>> getActive() {
        return ResponseEntity.ok(leaveTypeService.getActive());
    }
    @GetMapping("/{id}")
    public ResponseEntity<LeaveType> getById(@PathVariable Long id) {
        return ResponseEntity.ok(leaveTypeService.getById(id));
    }
    @GetMapping("/code/{leaveCode}")
    public ResponseEntity<LeaveType> getByCode(@PathVariable String leaveCode) {
        return ResponseEntity.ok(leaveTypeService.getByCode(leaveCode));
    }
    @PutMapping("/{id}")
    public ResponseEntity<LeaveType> update(@PathVariable Long id, @RequestBody LeaveType leaveType) {
        return ResponseEntity.ok(leaveTypeService.update(id, leaveType));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        leaveTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}