package com.ooad.project.service;

import com.ooad.project.entity.Market;
import com.ooad.project.entity.MarketTask;
import com.ooad.project.entity.MarketTaskReport;
import com.ooad.project.entity.Task;
import lombok.Getter;

import java.util.List;

/**
 * User: Admin
 * Date: 2021/1/6
 * Time: 10:39
 * Author: LuoRong
 * Student Number:17302010081
 * Note:
 */
@Getter
public class MarketTaskQueryService {
    private Task task;
    private Market market;

    public MarketTaskQueryService(Market market, Task task) {
        this.market = market;
        this.task = task;
    }

    /* 输入 ① Market 和 ② 具体的 Task，可以查询具体的 MarketTask 的任务完成情况 */
    public MarketTask getMarketTask() {
        List<Market> markets = this.getTask().getMarkets();
        List<MarketTask> marketTasks = this.getTask().getMarketTasks();
        int size = markets.size();
        for (int i = 0; i < size; ++i) {
            if (markets.get(i).getId() == this.getMarket().getId()) {
                return marketTasks.get(i);
            }
        }
        return null;
    }
}
