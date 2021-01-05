package com.ooad.project.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * User: Admin
 * Date: 2021/1/4
 * Time: 20:26
 * Author: LuoRong
 * Student Number:17302010081
 * Note:
 * 记录每一条得分 / 扣分的原因
 */

@Getter
@Setter
@Entity
@Table(name = "records")
public class ScoreRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
    private int id;

    @Column
    private Date date;

    @Column
    private String description;

    @Column
    private int score;

    @Transient
    private Market market;
}
