package com.ooad.project.entity.interfaces;

import java.util.Date;

/**
 * @author 刘佳兴
 * @date 2021/1/13 9:06
 * mail 1260968291@qq.com
 */
public interface FinishingScoreStrategy {
    /**
     * 根据任务情况计算分数
     * @param iTask 任务
     * @param currentDate 当前时间
     * @return 分数
     */
    int getScore(ITask iTask, Date currentDate);
}
