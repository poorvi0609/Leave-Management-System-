package com.tekenlight.lms.module.holiday.controller;

import com.tekenlight.lms.module.holiday.entity.Holiday;
import com.tekenlight.lms.module.holiday.enums.HolidayType;
import com.tekenlight.lms.module.holiday.service.HolidayService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/holidays")
@RequiredArgsConstructor
public class HolidayController {

    private final HolidayService holidayService;

    @PostMapping
    public ResponseEntity<Holiday> create(@RequestBody Holiday holiday) {
        return ResponseEntity.ok(holidayService.create(holiday));
    }
    @PostMapping("/bulk")
    public ResponseEntity<List<Holiday>> bulkCreate(@RequestBody List<Holiday> holidays) {
        return ResponseEntity.ok(holidayService.bulkCreate(holidays));
    }
    @GetMapping
    public ResponseEntity<List<Holiday>> getAll() {
        return ResponseEntity.ok(holidayService.getAll());
    }
    @GetMapping("/active")
    public ResponseEntity<List<Holiday>> getActive() {
        return ResponseEntity.ok(holidayService.getActive());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Holiday> getById(@PathVariable Long id) {
        return ResponseEntity.ok(holidayService.getById(id));
    }
    @GetMapping("/code/{holCode}")
    public ResponseEntity<Holiday> getByCode(@PathVariable String holCode) {
        return ResponseEntity.ok(holidayService.getByCode(holCode));
    }
    @GetMapping("/type/{type}")
    public ResponseEntity<List<Holiday>> getByType(@PathVariable HolidayType type) {
        return ResponseEntity.ok(holidayService.getByType(type));
    }
    @GetMapping("/range")
    public ResponseEntity<List<Holiday>> getByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return ResponseEntity.ok(holidayService.getByDateRange(start, end));
    }
    @GetMapping("/range/location")
    public ResponseEntity<List<Holiday>> getByDateRangeAndLocation(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end,
            @RequestParam String location) {
        return ResponseEntity.ok(holidayService.getByDateRangeAndLocation(start, end, location));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Holiday> update(@PathVariable Long id, @RequestBody Holiday holiday) {
        return ResponseEntity.ok(holidayService.update(id, holiday));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        holidayService.delete(id);
        return ResponseEntity.noContent().build();
    }
}