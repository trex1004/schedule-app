package com.example.scheduleapp.controller;

import com.example.scheduleapp.dto.CreateSchedule;
import com.example.scheduleapp.dto.ResponseSchedule;
import com.example.scheduleapp.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
