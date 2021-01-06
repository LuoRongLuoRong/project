package com.ooad.project.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
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
@Table(name = "market_tasks")
public class MarketTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
    private int id;

    @Transient
    private Market market;
    @Transient
    private List<Product> products;
    @Transient
    private MarketTaskReport marketTaskReport;

    @Column
    private boolean isFinished;
    @Column
    private Date startDate;
    @Column
    private Date deadlineDate;
    @Column
    private Date finishDate;

    public MarketTask(Market market, List<Product> products) {
        this.market = market;
        this.products = products;
        initMarketTaskReport();
    }

    private void initMarketTaskReport() {
        this.marketTaskReport = new MarketTaskReport(products);
    }
}
