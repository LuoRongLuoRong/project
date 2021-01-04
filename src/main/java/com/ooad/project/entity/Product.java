package com.ooad.project.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * User: Admin
 * Date: 2020/12/26
 * Time: 13:21
 * Author: LuoRong
 * Student Number:17302010081
 * Note:
 */

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
    private String id;

    @Column
    private String name;

}
