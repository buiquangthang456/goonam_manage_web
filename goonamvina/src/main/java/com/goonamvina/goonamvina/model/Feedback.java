package com.goonamvina.goonamvina.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;
    private String email;
    private String message;

    @Enumerated(EnumType.STRING)
    private FeedbackStatus status = FeedbackStatus.SPAM;

    private LocalDateTime createdAt = LocalDateTime.now();

    // Getters and Setters
}
