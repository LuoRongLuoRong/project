package com.ooad.project.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: Admin
 * Date: 2021/1/4
 * Time: 16:51
 * Author: LuoRong
 * Student Number:17302010081
 * Note:
 * ReportService 中报错：Could not autowire. No beans of 'Task' type found. less... (Ctrl+F1)
 * Inspection info:Checks autowiring problems in a bean class.
 */

@Getter
@Setter
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
    private int id;
    @Column
    private String name;
    @Column
    private boolean isFinished;
    @Column
    private Date startDate;
    @Column
    private Date deadlineDate;
    @Column
    private Date finishDate;

    @Transient
    private Expert expert;
    @Transient
    private List<Market> markets;
    @Transient
    private List<MarketTask> marketTasks;
    @Transient
    private List<Product> products;


    /* 农贸市场自检任务 */
    public Task(String name, List<Market> markets, List<Product> products, Date startDate, Date deadlineDate) {
        this.name = name;
        this.markets = markets;
        this.products = products;
        this.startDate = startDate;
        this.deadlineDate = deadlineDate;

        this.isFinished = false;

        initMarketTasks();
    }

    /* 专家任务 */
    public Task(String name, List<Market> markets, List<Product> products, Date startDate, Date deadlineDate, Expert expert) {
        this(name, markets, products, startDate, deadlineDate);
        this.expert = expert;
    }

    private void initMarketTasks() {
        this.setMarketTasks(new ArrayList<>());
        for (Market market: this.getMarkets()) {
            MarketTask marketTask = new MarketTask(market, this.getProducts());

            this.getMarketTasks().add(marketTask);
        }
    }
}
