package com.sparta.calendar.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    private Integer age;

    @Column(nullable = false)
    private String password;
    // --- 일정 CRUD 기능 관련 필드 추가 --- //


    public User() {
    }

    public User( String name, Integer age, String password) {

        this.name = name;
        this.age = age;
        this.password = password;
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }

    public void updatePassword(String password) {
        this.password = password;
    }
}