package com.ooad.project.repo;

import com.ooad.project.entity.Market;
import com.ooad.project.entity.ScoreRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: Admin
 * Date: 2021/1/5
 * Time: 15:15
 * Author: LuoRong
 * Student Number:17302010081
 * Note:
 */
@Repository
public interface ScoreRecordRepository extends CrudRepository<ScoreRecord, Integer> {
    List<ScoreRecord> findAll();
//    List<ScoreRecord> findByMarket(Market market); // 这里会报错，不允许的呀 why？
}
