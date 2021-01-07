package com.ooad.project.repo;

import com.ooad.project.entity.Product;
import com.ooad.project.entity.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: Admin
 * Date: 2021/1/4
 * Time: 16:48
 * Author: LuoRong
 * Student Number:17302010081
 * Note:
 */
@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {

    List<Product> getProductsById(int id);

    List<Task> findAll();
    Task findById(int id);
    Task findByName(String name);
}
