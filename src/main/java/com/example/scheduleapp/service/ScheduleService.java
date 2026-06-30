package com.example.scheduleapp.service;

import com.example.scheduleapp.dto.CreateSchedule;
import com.example.scheduleapp.dto.ResponseSchedule;
import com.example.scheduleapp.entity.Schedule;
import com.example.scheduleapp.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ResponseSchedule create(CreateSchedule createSchedule) {
        Schedule schedule = new Schedule(
                createSchedule.getTitle(),
                createSchedule.getContents(),
                createSchedule.getUserName(),
                createSchedule.getPassword()
                );
        Schedule createdSchedule = scheduleRepository.save(schedule);

        ResponseSchedule responseSchedule = new ResponseSchedule(
                createdSchedule.getId(),
                createdSchedule.getTitle(),
                createdSchedule.getContents(),
                createdSchedule.getUserName(),
                createdSchedule.getCreatedAt(),
                createdSchedule.getModifiedAt()
        );
        return responseSchedule;

    }

}
