package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false, length = 10)
    private String memberName;

    @Column(nullable = false, length = 10)
    private String idInsert;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime dtInsert;

    @Column(nullable = false, length = 10)
    private String idUpdate;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime dtUpdate;
}
