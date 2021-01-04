package com.ooad.project.repo;

import com.ooad.project.entity.Expert;
import org.springframework.data.repository.CrudRepository;

/**
 * User: Admin
 * Date: 2021/1/4
 * Time: 16:04
 * Author: LuoRong
 * Student Number:17302010081
 * Note:
 */
public interface MarketRepository extends CrudRepository<Expert, Integer> {
}
