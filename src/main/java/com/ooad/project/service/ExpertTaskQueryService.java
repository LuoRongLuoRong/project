package com.ooad.project.service;

import com.ooad.project.entity.Expert;
import com.ooad.project.entity.MarketTask;
import com.ooad.project.entity.Task;

import java.util.List;

/**
 * User: Admin
 * Date: 2021/1/6
 * Time: 12:04
 * Author: LuoRong
 * Student Number:17302010081
 * Note:
 * 这个类说实话，感觉没啥用
 */
public class ExpertTaskQueryService {
    private Task task;
    private Expert expert;

    public ExpertTaskQueryService(Task task, Expert expert) {
        this.task = task;
        this.expert = expert;
    }

    /* 输入 ① Market 和 ② 具体的 Task，可以查询具体的 MarketTask 的任务完成情况 */
    public List<MarketTask> getMarketTask() {
        return task.getMarketTasks();
    }
}
