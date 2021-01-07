package com.ooad.project.service;

import com.ooad.project.entity.Product;
import com.ooad.project.entity.ProductCheckRecord;
import com.ooad.project.repo.ProductCheckRecordRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * User: Admin
 * Date: 2021/1/7
 * Time: 10:10
 * Author: LuoRong
 * Student Number:17302010081
 * Note:
 */
@Service
public class ProductReportService {

    @Autowired
    ProductCheckRecordRepository productCheckRecordRepository;

    /**
     * 从两次抽检日期中查看某个农贸产品类别在某个时间范围内的总的不合格数
     * 这方法和 task 好像没关系
     * @param product
     * @param firstFinishDate
     * @param secondFinishDate
     * @return
     */
    public int getUnqualifiedProductNumberByInterval(Product product, Date firstFinishDate, Date secondFinishDate) {
        int number = 0;
        /*
        // 这方法也不知道能不能运行。好像不太行
        List<ProductCheckRecord> records = productCheckRecordRepository.findDistinctByProductAndFinishDateAfterAndFinishDateBefore(product, firstFinishDate, secondFinishDate);
        for (ProductCheckRecord record: records) {
            number += record.getUnqualifiedNumber();
        }*/

        List<ProductCheckRecord> records = productCheckRecordRepository.findAll();
        for (ProductCheckRecord record: records) {
            // 尚未结束抽检
            if (!record.isFinished()) {
                continue;
            }

            // 产品不符合
            Product recordProduct = record.getProduct();
            if (product.getId() != recordProduct.getId()) {
                continue;
            }

            // 抽检时间是否满足
            Date finishDate = record.getFinishDate();
            if (finishDate.after(firstFinishDate) && finishDate.before(secondFinishDate)) {
                number += record.getUnqualifiedNumber();
            }
        }

        return number;
    }
}
