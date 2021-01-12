package com.ooad.project.entity.interfaces;

import com.ooad.project.entity.Product;
import com.ooad.project.entity.ProductCheckResult;

import java.util.List;

/**
 * @author 刘佳兴
 * @date 2021/1/9 22:18
 * mail 1260968291@qq.com
 */
public interface ITaskReport {
    /**
     * 通过product查抄product的抽检结果
     * @param product 产品分类
     * @return 抽检结果列表
     */
    List<ProductCheckResult> findByProduct(Product product);

}
