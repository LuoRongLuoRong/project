package com.ooad.project.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Admin
 * Date: 2021/1/4
 * Time: 18:39
 * Author: LuoRong
 * Student Number:17302010081
 * Note:
 * - id
 * - task: Task
 * - marketTask:MarketTask
 * - unqualifiedProducts: Map<Product, Integer>
 * - isFinished: boolean
 * - startDate: Date
 * - deadlineDate: Date
 * - finishDate: Date
 */

@Getter
@Setter
@Entity // 表明这是一个实体
@Table(name = "task_reports")
public class MarketTaskReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
    private int id;

    @Transient
    private Map<Product, ProductCheckRecord> unqualifiedProductMap;

    @Column
    private boolean isFinished;
    @Column
    private Date startDate;
    @Column
    private Date deadlineDate;
    @Column
    private Date finishDate;


    public MarketTaskReport(List<Product> products) {
        initMap(products);
    }

    /**
     * 初始化每项产品的记录，将 isFinished 设置为 false
     * @param products
     */
    private void initMap(List<Product> products) {
        unqualifiedProductMap = new HashMap<>();
        for (Product product: products) {
            ProductCheckRecord record = new ProductCheckRecord();
            record.setProduct(product);
            unqualifiedProductMap.put(product, record);
        }
    }

    public void addProductCheckRecord(ProductCheckRecord productCheckRecord) {
        unqualifiedProductMap.put(productCheckRecord.getProduct(), productCheckRecord);
    }
}
