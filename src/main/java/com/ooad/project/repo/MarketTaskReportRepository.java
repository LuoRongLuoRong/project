package com.ooad.project.repo;

import com.ooad.project.entity.MarketTask;
import com.ooad.project.entity.MarketTaskReport;
import com.ooad.project.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Map;

/**
 * User: Admin
 * Date: 2021/1/4
 * Time: 18:41
 * Author: LuoRong
 * Student Number:17302010081
 * Note:
 * 某种蔬菜的不合格数目
 */

public interface MarketTaskReportRepository extends CrudRepository<MarketTaskReport, Integer> {
}
