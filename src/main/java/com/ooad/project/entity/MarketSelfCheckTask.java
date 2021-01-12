package com.ooad.project.entity;
import com.ooad.project.entity.interfaces.ITask;
import com.ooad.project.entity.interfaces.ITaskReport;

import java.util.Date;
import java.util.List;

/**
 * @author 刘佳兴
 * @date 2021/1/9 21:24
 * mail 1260968291@qq.com
 */

public class MarketSelfCheckTask implements ITask {

    private List<Product> products;
    private Date deadline;
    private Date finishDate;
    private String finishInfo;
    private String description;

    public ITaskReport getTaskReport() {
        return taskReport;
    }

    private ITaskReport taskReport;

    public boolean isFinished() {
        return isFinished;
    }

    private boolean isFinished;

    public MarketSelfCheckTask(String description, List<Product> products, Date deadline) {
        this.products = products;
        this.deadline = deadline;
        this.description = description;
    }

    public MarketSelfCheckTask() {}

    @Override
    public void setFinishInfo(String info) {
        this.finishInfo = info;
    }

    @Override
    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    @Override
    public void setFinished(boolean finished) {
        this.isFinished = finished;
    }

    @Override
    public void setTaskReport(ITaskReport iTaskReport) {
        this.taskReport = iTaskReport;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
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
