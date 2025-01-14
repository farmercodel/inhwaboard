package com.study.board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data // Getter/Setter, toString, equals 등 필요 메소드 자동 생성 -> 데이터 모델 생성의 편리함을 줌.
public class Board {
    @Id // primary key를 의미함
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 전략 지정 - 데이터베이스에서 기본 키 값을 어떻게 생성할지를 정의
    // GenerationType.IDENTITY는 mariadb에서, GenerationType.SEQUENCE는 Oracle에서 사용하며 GenerationType.AUTO는 자동 지정
    private Integer id;
    private String title;
    private String content;
    private String filename;
    private String filepath;
}
