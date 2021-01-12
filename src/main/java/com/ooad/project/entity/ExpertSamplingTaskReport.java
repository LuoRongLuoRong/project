package com.ooad.project.entity;

import com.ooad.project.entity.interfaces.ITaskReport;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 刘佳兴
 * @date 2021/1/9 23:10
 * mail 1260968291@qq.com
 */
public class ExpertSamplingTaskReport implements ITaskReport {
    public List<MarketCheckTaskReport> getMarketCheckTaskReports() {
        return marketCheckTaskReports;
    }

    public void setMarketCheckTaskReports(List<MarketCheckTaskReport> marketCheckTaskReports) {
        this.marketCheckTaskReports = marketCheckTaskReports;
    }

    private List<MarketCheckTaskReport> marketCheckTaskReports;

    @Override
    public List<ProductCheckResult> findByProduct(Product product) {
        List<ProductCheckResult> results = new ArrayList<>();
        marketCheckTaskReports.forEach(m->{
            results.addAll(m.findByProduct(product));
        });
        return results;
    }
}
