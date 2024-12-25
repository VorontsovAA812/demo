package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "theme")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Theme {
    @Id
    @Column(name = "theme_name", length = 100, nullable = false)
    private String themeName;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "creator_username")
    private Usr creator;
}
