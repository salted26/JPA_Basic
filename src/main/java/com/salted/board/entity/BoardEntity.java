package com.salted.board.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CurrentTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="tb_board")
public class BoardEntity {
    //board_no를 사용할 수 없음 : boardno, bno로 사용

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    @Column(columnDefinition = "TEXT")
    private String title;

    @Column(columnDefinition = "LONGTEXT")
    private String content;

    @Column(columnDefinition = "TEXT")
    private String board_type;

    @Column(columnDefinition = "TEXT")
    private String user_id;

    @ColumnDefault("0")
    @Column(unique = false)
    private int likes;

    @ColumnDefault("1")
    private int counts;

    @CreatedDate
    private LocalDateTime created_dt = LocalDateTime.now();

    @LastModifiedDate
    private LocalDateTime modified_dt = LocalDateTime.now();
}

