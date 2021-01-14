package com.ooad.project.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author 刘佳兴
 * @date 2021/1/9 20:17
 * mail 1260968291@qq.com
 */

@Getter
@Setter
public class ExpertSamplingTask implements ITask {

    public ExpertSamplingTask(String description, List<Product> products, List<Market> markets, Date deadline) {
        this.description = description;
        this.products = products;
        this.markets = markets;
        this.deadline = deadline;
        this.taskReport = new TaskReport();
    }

    private List<Product> products;
    private List<Market> markets;
    private Date deadline;
    private Date finishDate;
    private String description;
    private boolean isFinished;
    private TaskReport taskReport;

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    private List<Product> steam2List(Stream<Product> productStream){
        List<Product> products = new ArrayList<>();
        productStream.forEach(products::add);
        return products;
    }

    @Override
    public void addSuperviseResult(Market market, List<ProductCheckResult> productCheckResultList, Date superviseDate) {
        // 已经finished后不允许添加入报告中
        if(isFinished){
            return;
        }
        taskReport.addSuperviseResult(market,productCheckResultList);
        boolean isFinished = true;
        for(Market m: markets){
            List<ProductCheckResult> allProductCheckResults = taskReport.findByMarket(m);
            if(allProductCheckResults == null){
                isFinished = false;
                break;
            }
            // 获得该市场检查过的所有农产品类型
            List<Product> checkedProducts = steam2List(allProductCheckResults.stream().map(ProductCheckResult::getProduct));
            // 查找是否所有的Product都check了
            for(Product p: products){
                if(checkedProducts.indexOf(p) < 0){
                    isFinished = false;
                    break;
                }
            }
        }
        // 如果所有的product都check了，则设置任务的时间和状态
        if(isFinished){
            setFinishDate(superviseDate);
            setFinished(true);
        }
    }

    @Override
    public List<ProductCheckResult> getProductCheckResultsByProduct(Product product) {
        return taskReport.findByProduct(product);
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public ExpertSamplingTask() {}

    @Override
    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Market> getMarkets() {
        return markets;
    }

    public void setMarkets(List<Market> markets) {
        this.markets = markets;
    }

    @Override
    public Date getDeadline() {
        return deadline;
    }

    @Override
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    @Override
    public Date getFinishDate() {
        return finishDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
