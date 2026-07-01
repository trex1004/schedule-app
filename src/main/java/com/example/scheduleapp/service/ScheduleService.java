package com.example.scheduleapp.service;

import com.example.scheduleapp.dto.CreateSchedule;
import com.example.scheduleapp.dto.DeleteSchedule;
import com.example.scheduleapp.dto.ResponseSchedule;
import com.example.scheduleapp.dto.UpdateSchedule;
import com.example.scheduleapp.entity.Schedule;
import com.example.scheduleapp.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

        return new ResponseSchedule(
                createdSchedule.getId(),
                createdSchedule.getTitle(),
                createdSchedule.getContents(),
                createdSchedule.getUserName(),
                createdSchedule.getCreatedAt(),
                createdSchedule.getModifiedAt()
        );

    }

    @Transactional(readOnly = true)
    public List<ResponseSchedule> findAllSchedule() {
        List<Schedule> scheduleList = scheduleRepository.findAll();
        return scheduleList.stream().map(schedule -> new ResponseSchedule(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getUserName(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        )).toList();
    }

    @Transactional(readOnly = true)
    public ResponseSchedule findOneSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("없는 게시물"));
        return new ResponseSchedule(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getUserName(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    @Transactional
    public ResponseSchedule updateSchedule(Long id, UpdateSchedule request) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("없는 게시물"));
        if (!schedule.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("비밀번호 불일치");
        }
        schedule.update(request.getTitle(), request.getUserName());

        return new ResponseSchedule(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getUserName(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );

    }

    @Transactional
    public void delete(Long id, DeleteSchedule request) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("없는 게시물"));
        if (!schedule.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("비밀번호 불일치");
        }

        scheduleRepository.delete(schedule);


    }
}
