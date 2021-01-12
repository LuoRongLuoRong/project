package com.ooad.project.entity.interfaces;

import java.util.Date;

/**
 * @author 刘佳兴
 * @date 2021/1/9 19:48
 * mail 1260968291@qq.com
 */
public interface ISupervise {

    /**
     * 作为任务执行人为任务设置反馈信息和时间，完成任务
     * @param iTask 任务
     * @param info 任务完成信息
     * @param date 任务完成时间
     * @param iTaskReport 任务报告
     */

    default void finishTask(ITask iTask, String info, Date date, ITaskReport iTaskReport){
        if(checkMyTask(iTask)){
            iTask.setFinishDate(date);
            iTask.setFinishInfo(info);
            iTask.setFinished(true);
            iTask.setTaskReport(iTaskReport);
        }
    }

    /**
     * 判断是否是自己的任务
     * @param iTask 任务
     * @return 是否是自己的任务
     */
    boolean checkMyTask(ITask iTask);
}
