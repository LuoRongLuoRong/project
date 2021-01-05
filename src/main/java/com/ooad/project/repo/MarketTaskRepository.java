package com.ooad.project.repo;

import com.ooad.project.entity.MarketTask;
import com.ooad.project.entity.MarketTaskReport;
import org.springframework.data.repository.CrudRepository;

/**
 * User: Admin
 * Date: 2021/1/4
 * Time: 16:49
 * Author: LuoRong
 * Student Number:17302010081
 * Note:
 */
public interface MarketTaskRepository extends CrudRepository<MarketTask, Integer> {
    // TODO: 此处用 getMarketTaskReport 似乎是不对的，为什么呢

//    MarketTaskReport findMarketTaskReport();
}
