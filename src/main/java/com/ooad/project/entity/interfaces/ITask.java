package com.ooad.project.entity.interfaces;

import com.ooad.project.entity.Market;
import com.ooad.project.entity.Product;
import com.ooad.project.entity.ProductCheckResult;

import java.util.Date;
import java.util.List;

/**
 * @author 刘佳兴
 * @date 2021/1/9 20:51
 * mail 1260968291@qq.com
 */
public interface ITask {

    /**
     * 设置任务的完成时间
     * @param finishDate 完成时间
     */
    void setFinishDate(Date finishDate);

    /**
     * 获得结束时间
     * @return 结束时间
     */
    Date getFinishDate();


    /**
     * 获得任务的截至时间
     * @return 任务的截止时间
     */
    Date getDeadline();

    /**
     * 设置任务的截至时间
     * @param deadline 截至时间
     */
    void setDeadline(Date deadline);

    /**
     * 是否已经结束
     * @return 是否已经结束
     */
    boolean isFinished();

    /**
     * 添加一项抽查结果
     * @param market 市场
     * @param productCheckResultList 农产品类型抽查结果
     * @param superviseDate 抽检日期
     */
    void addSuperviseResult(Market market,  List<ProductCheckResult> productCheckResultList, Date superviseDate);

    /**
     * 获得当前task某个农产品类型的所有检查结果
     * @param product 农产品类型
     * @return 检查结果列表
     */
    List<ProductCheckResult> getProductCheckResultsByProduct(Product product);
}
