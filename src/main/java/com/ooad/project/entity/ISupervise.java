package com.ooad.project.entity;

import java.util.Date;
import java.util.List;

/**
 * @author 刘佳兴
 * @date 2021/1/9 19:48
 * mail 1260968291@qq.com
 */
public interface ISupervise {

    /**
     * 作为任务执行人抽查某个市场的部分农产品类型
     * @param iTask 任务
     * @param market 市场
     * @param productCheckResultList 农产品类型
     * @param superviseDate 抽检时间
     */
    default void supervise(ITask iTask, Market market, List<ProductCheckResult> productCheckResultList, Date superviseDate){
        if(checkMyTask(iTask)){
            // 为此次抽查的所有农产品设定抽查时间
            productCheckResultList.forEach(productCheckResult -> {
                productCheckResult.setCheckDate(superviseDate);
            });
            iTask.addSuperviseResult(market, productCheckResultList,superviseDate);
        }
    }

    /**
     * 判断是否是自己的任务
     * @param iTask 任务
     * @return 是否是自己的任务
     */
    boolean checkMyTask(ITask iTask);

    /**
     * 获得自己的所有task
     * @return tasks
     */
    List<ITask> getMyTasks();
}
