package com.ooad.project.repo;

import com.ooad.project.entity.Product;
import com.ooad.project.entity.ProductCheckRecord;
import com.ooad.project.entity.ScoreRecord;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * User: Admin
 * Date: 2021/1/5
 * Time: 16:34
 * Author: LuoRong
 * Student Number:17302010081
 * Note:
 */
public interface ProductCheckRecordRepository extends CrudRepository<ProductCheckRecord, Integer> {
    List<ProductCheckRecord> findAll();

    // TODO: 这方法好像不太行
//    List<ProductCheckRecord> findDistinctByProductAndFinishDateAfterAndFinishDateBefore(Product product, Date firstDate, Date secondDate);
}
