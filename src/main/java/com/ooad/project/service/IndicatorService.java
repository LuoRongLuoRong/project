package com.ooad.project.service;

import com.ooad.project.entity.Product;
import com.ooad.project.entity.ProductCheckResult;
import com.ooad.project.entity.FinishingScoreStrategy;
import com.ooad.project.entity.ISupervise;
import com.ooad.project.entity.ITask;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author 刘佳兴
 * @date 2021/1/13 9:26
 * mail 1260968291@qq.com
 */
@Service
@RestController
public class IndicatorService {
    public int update(FinishingScoreStrategy myFinishingScoreStrategy, ISupervise supervise, Date currentTime){
        int totalScore = 0;
        for(ITask task: supervise.getMyTasks()){
            totalScore += myFinishingScoreStrategy.getScore(task,currentTime);
        }
        return totalScore;
    }

    public int getTotalUnqualifiedCount(List<ITask> iTasks, Date startDate, Date endDate, Product product) {
        int totalCount = 0;
        for(ITask iTask: iTasks){
            List<ProductCheckResult> productCheckResults = iTask.getProductCheckResultsByProduct(product);
            if(productCheckResults != null){
                for(ProductCheckResult productCheckResult: productCheckResults){
                    if(productCheckResult.getCheckDate().compareTo(startDate) >= 0 && productCheckResult.getCheckDate().compareTo(endDate) <= 0){
                        totalCount += productCheckResult.getUnqualifiedNumber();
                    }
                }
            }
        }
        return totalCount;
    }
}
