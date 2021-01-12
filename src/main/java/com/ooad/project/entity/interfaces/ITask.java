package com.ooad.project.entity.interfaces;

import java.util.Date;

/**
 * @author 刘佳兴
 * @date 2021/1/9 20:51
 * mail 1260968291@qq.com
 */
public interface ITask {

    /**
     * 设置任务的完成信息
     * @param info 完成信息
     */
    void setFinishInfo(String info);

    /**
     * 设置任务的完成时间
     * @param finishDate 完成时间
     */
    void setFinishDate(Date finishDate);

    /**
     * 设置是否已已经完成
     * @param finished 是否已经完成
     */
    void setFinished(boolean finished);

    /**
     * 设置任务报告
     * @param iTaskReport 任务报告
     */
    void setTaskReport(ITaskReport iTaskReport);
}
