package com.ooad.project.entity;

import com.ooad.project.entity.interfaces.ITask;
import com.ooad.project.entity.interfaces.ITaskReport;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @author 刘佳兴
 * @date 2021/1/9 20:17
 * mail 1260968291@qq.com
 */

@Getter
@Setter
public class ExpertSamplingTask implements ITask {

    public ExpertSamplingTask(String description, List<Product> products, List<Market> markets) {
        this.description = description;
        this.products = products;
        this.markets = markets;
    }

    private List<Product> products;
    private List<Market> markets;
    private Date deadline;
    private Date finishDate;
    private String finishInfo;
    private String description;
    private boolean isFinished;
    private ITaskReport taskReport;

    public ITaskReport getTaskReport() {
        return taskReport;
    }

    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    @Override
    public void setTaskReport(ITaskReport iTaskReport) {
        this.taskReport = iTaskReport;
    }

    public ExpertSamplingTask() {
    }

    @Override
    public void setFinishInfo(String info) {
        this.finishInfo = info;
    }

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

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public String getFinishInfo() {
        return finishInfo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
