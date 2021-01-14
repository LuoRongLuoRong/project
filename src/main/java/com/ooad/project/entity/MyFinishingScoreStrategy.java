package com.ooad.project.entity;

import java.util.Date;

/**
 * @author 刘佳兴
 * @date 2021/1/13 9:15
 * mail 1260968291@qq.com
 */
public class MyFinishingScoreStrategy implements FinishingScoreStrategy {
    private MyFinishingScoreStrategy(){}
    private static MyFinishingScoreStrategy myFinishingScoreStrategy;
    final int DEAD_DAY = 10;
    final int HOURS_ONE_DAY = 24;
    final int MINUTES_ONE_HOUR = 60;
    final int THOUSAND = 1000;

    /**
     * 单例模式产生计分策略
     * @return 策略
     */
    public static MyFinishingScoreStrategy instance(){
        if(myFinishingScoreStrategy == null){
            myFinishingScoreStrategy = new MyFinishingScoreStrategy();
        }
        return myFinishingScoreStrategy;
    }

    @Override
    public int getScore(ITask iTask, Date currentDate) {
        if(iTask.isFinished()){
            if(iTask.getFinishDate().compareTo(iTask.getDeadline()) <= 0){
                return 10;
            } else if(iTask.getFinishDate().compareTo(new Date(iTask.getDeadline().getTime() + DEAD_DAY * HOURS_ONE_DAY
                    * MINUTES_ONE_HOUR * MINUTES_ONE_HOUR * THOUSAND)) > 0){
                return -20;
            } else {
                return -10;
            }
        } else {
            if(iTask.getDeadline().compareTo(new Date(currentDate.getTime() - DEAD_DAY * HOURS_ONE_DAY
                    * MINUTES_ONE_HOUR * MINUTES_ONE_HOUR * THOUSAND)) < 0){
                return -20;
            } else if(iTask.getDeadline().compareTo(currentDate) < 0){
                return -10;
            } else {
                return 0;
            }
        }

    }
}
