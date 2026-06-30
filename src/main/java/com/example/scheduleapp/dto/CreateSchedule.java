package com.example.scheduleapp.dto;

import lombok.Getter;

@Getter
public class CreateSchedule {
    private String title;
    private String contents;
    private String userName;
    private String password;
}
