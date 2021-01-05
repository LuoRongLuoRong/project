package com.ooad.project.repo;

import com.ooad.project.entity.Expert;
import com.ooad.project.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * User: Admin
 * Date: 2021/1/4
 * Time: 16:05
 * Author: LuoRong
 * Student Number:17302010081
 * Note:
 */
public interface ProductRepository extends CrudRepository<Product, Integer> {
    List<Product> findAll();
//    Product findById(int id);
//    Product findByName(String name);
}
