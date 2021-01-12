package com.ooad.project.entity;

import com.ooad.project.entity.interfaces.ITaskReport;

import java.util.*;

/**
 * @author 刘佳兴
 * @date 2021/1/9 22:02
 * mail 1260968291@qq.com
 */

public class MarketCheckTaskReport implements ITaskReport {
    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Map<Product, ProductCheckResult> getProductReports() {
        return productReports;
    }

    public void setProductReports(Map<Product, ProductCheckResult> productReports) {
        this.productReports = productReports;
    }

    private Map<Product, ProductCheckResult> productReports = new HashMap<>();

    @Override
    public List<ProductCheckResult> findByProduct(Product product) {
        return Collections.singletonList(productReports.get(product));
    }

}

