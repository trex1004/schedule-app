package com.example.scheduleapp.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, length = 30)
    private String title;
    @Column(nullable = false, length = 200)
    private String contents;

    @Column(nullable = false, length = 10)
    private String userName;

    @Column(nullable = false, length = 10)
    private String password;
    public Schedule(String title, String contents, String userName, String password) {
        this.title = title;
        this.contents = contents;
        this.userName = userName;
        this.password = password;
    }
    public void update(String title, String contents, String userName, String password) {
        this.title = title;
        this.contents = contents;
        this.userName = userName;
        this.password = password;
    }
}
