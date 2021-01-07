package com.ooad.project.service;

import com.ooad.project.entity.*;
import com.ooad.project.repo.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * User: Admin
 * Date: 2021/1/4
 * Time: 20:54
 * Author: LuoRong
 * Student Number:17302010081
 * Note:
 *
 + getUnfinishedMarketTasks()
 + getUnfinishedProductsByMarketTask(marketTask: MarketTask)
 + getUnqualifiedProductsByInterval(product, fisrtFinishDate, secondFinishDate)
 *
 * 调用该方法时需要先使用 Task 绑定一下
 */

//@Service // task 输入的时候会报错，看着很不爽啊

public class TaskReportService {

    @Getter
    private Task task;

//    private MarketTaskReportRepository marketTaskReportRepository;
//    private ProductRepository productRepository;
//    private ProductCheckRecordRepository productCheckRecordRepository;
//    private TaskRepository taskRepository;
//    private MarketTaskRepository marketTaskRepository;
//    private ScoreRecordRepository recordRepository;

    // 这个报错可以看看 https://www.cnblogs.com/Howinfun/p/11731826.html
    public TaskReportService(Task task) {
        this.task = task;
    }

    /**
     * 查看未完成的任务
     * @return
     */
    public List<MarketTask> getUnfinishedMarketTasks() {
        List<MarketTask> marketTasks = this.task.getMarketTasks();
        List<MarketTask> unfinishedMarketTasks = new ArrayList<>();
        for (MarketTask marketTask: marketTasks) {
            if (!marketTask.isFinished()) {
                unfinishedMarketTasks.add(marketTask);
            }
        }
        return unfinishedMarketTasks;
    }

    /**
     * 查询此次任务中某市场尚未被抽检的产品
     * @param marketTask
     * @return
     */
    public List<Product> getUnfinishedProductsByMarketTask(MarketTask marketTask) {
        List<Product> retProducts = new ArrayList<>();
        MarketTaskReport marketTaskReport = marketTask.getMarketTaskReport();
        Map<Product, ProductCheckRecord> unqualifiedProductMap = marketTaskReport.getUnqualifiedProductMap();

        // 遍历出某 MarketTask 中尚未抽检的产品
        Iterator iterator = unqualifiedProductMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            Product product = (Product)entry.getKey();
            ProductCheckRecord productCheckRecord = (ProductCheckRecord)entry.getValue();
            if (!productCheckRecord.isFinished()) {
                retProducts.add(product);
            }
        }

        return retProducts;
    }


}
