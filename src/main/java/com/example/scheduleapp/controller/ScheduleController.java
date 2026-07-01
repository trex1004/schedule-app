package com.example.scheduleapp.controller;

import com.example.scheduleapp.dto.CreateSchedule;
import com.example.scheduleapp.dto.ResponseSchedule;
import com.example.scheduleapp.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ResponseSchedule> createSchedule(
            @RequestBody
            CreateSchedule createSchedule) {
        return ResponseEntity.
                status(HttpStatus.CREATED).
                body(scheduleService.create(createSchedule));
    }

    @GetMapping
    public ResponseEntity<List<ResponseSchedule>> findAllSchedule() {
        List<ResponseSchedule> scheduleList = scheduleService.findAllSchedule();
        return ResponseEntity.ok(scheduleList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseSchedule> findOneSchedule(@PathVariable Long id) {
        return ResponseEntity.ok(scheduleService.findOneSchedule(id));
    }


}
