package com.ooad.project.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * User: Admin
 * Date: 2021/1/4
 * Time: 16:51
 * Author: LuoRong
 * Student Number:17302010081
 * Note:
 */

@Getter
@Setter
@Entity
public class MarketTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
    private int id;
}
