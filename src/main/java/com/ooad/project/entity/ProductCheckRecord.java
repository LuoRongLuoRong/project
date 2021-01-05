package com.ooad.project.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * User: Admin
 * Date: 2021/1/5
 * Time: 16:25
 * Author: LuoRong
 * Student Number:17302010081
 * Note:
 */

@Getter
@Setter
@Entity
public class ProductCheckRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
    private int id;

    @Transient
    private Product product;
    @Transient
    private Date finishDate; // 抽检日期
    @Column
    private int unqualifiedNumber; // 不合格数目
    @Column
    private boolean isFinished; // 抽检结束
}
