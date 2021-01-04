package com.ooad.project.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
    private int id;

    @Column
    private String name;

    @Transient
    private List<MarketTask> marketTasks;
    @Transient
    private List<Product> products;
    @Transient
    private List<Indicator> indicators;
    @Transient
    private Expert expert;

    /* 农贸市场自检任务 */
    public Task(int id, String name, List<MarketTask> marketTasks, List<Product> products) {
        this.id = id;
        this.name = name;
        this.marketTasks = marketTasks;
        this.products = products;
        // TODO: indicators
    }

    /* 专家任务 */
    public Task(int id, String name, List<MarketTask> marketTasks, List<Product> products, Expert expert) {
        this(id, name, marketTasks, products);
        this.expert = expert;
    }
}
