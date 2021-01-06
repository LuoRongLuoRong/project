package com.ooad.project.service;

import com.ooad.project.entity.Market;
import com.ooad.project.entity.MarketTask;
import com.ooad.project.entity.ScoreRecord;
import com.ooad.project.entity.Task;
import lombok.Getter;

import java.util.Date;
import java.util.List;

/**
 * User: Admin
 * Date: 2021/1/4
 * Time: 16:50
 * Author: LuoRong
 * Student Number:17302010081
 * Note:
 * 对于农贸市场和专家完成任务的及时性，设定评估指标：按时完成得 10 分，未按时完成扣 10 分，超过 20 天未完成扣 20 分。
 * 监管局能够查看当前农贸市场的得分情况以及得分和扣分的历史记录和原因。
 * 假设系统会有一个定时器，每天调用系统的一个服务（IndicatorService.update）来执行这个评估。
 *
 * 这个类使用前需要注入 task, 对于每个 task
 */

@Getter
public class IndicatorService {

    private Task task;

    public IndicatorService(Task task) {
        this.task = task;
    }

    /* 每天自动执行一遍 */
    public void updateScore(Date currDate) {
        List<MarketTask> marketTasks = this.getTask().getMarketTasks();
        for (MarketTask marketTask: marketTasks) {
            Market market = marketTask.getMarket();

            Date finishDate = marketTask.getFinishDate();
            Date deadlineDate = marketTask.getDeadlineDate();

            boolean isFinished = marketTask.isFinished();
            boolean isDue = deadlineDate.after(currDate);

            // 1. 未完成未到期：0
            if (!isFinished && !isDue) {
                break;
            }

            ScoreRecord record = new ScoreRecord();
            record.setDate(currDate);
            record.setMarket(market);

            // 2. 已完成未到期：+10
            if (isFinished && !isDue) {
                // TODO: 可以抽取成一个方法
                record.setDescription("TODO");
                record.setScore(10);
                market.addScoreRecord(record);
                break;
            }

            boolean isDueOver1Day = (currDate.getTime() - deadlineDate.getTime()) / 1000 / 3600 / 24 == 1;
            // 3. 未完成已过期 20 天之内：-10
            if (!isFinished && isDueOver1Day) {
                record.setDescription("TODO");
                record.setScore(-10);
                market.addScoreRecord(record);
                break;
            }

            boolean isDueOver20Days = (currDate.getTime() - finishDate.getTime()) / 1000 / 3600 / 24 == 20;
            // 4. 未完成已过期 20 天之后：-20
            if (!isFinished && isDueOver20Days) {
                record.setDescription("TODO");
                record.setScore(-10);
                market.addScoreRecord(record);
                break;
            }

        }
    }
}
