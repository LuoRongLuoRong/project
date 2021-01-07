package com.ooad.project.repo;

import com.ooad.project.entity.Expert;
import com.ooad.project.entity.Market;
import com.ooad.project.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: Admin
 * Date: 2021/1/4
 * Time: 16:04
 * Author: LuoRong
 * Student Number:17302010081
 * Note:
 */
@Repository
public interface MarketRepository extends CrudRepository<Market, Integer> {
//    int getScoreById(int id);
//    int getScoreByName(String name);
//    Market save(Market market);

//    Market findById(int id);
//    Market findByName(String name);
//    List<Market> findAll();
}
