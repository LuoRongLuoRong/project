package com.ooad.project.repo;

import com.ooad.project.entity.Product;
import com.ooad.project.entity.Task;
import org.springframework.data.repository.CrudRepository;

/**
 * User: Admin
 * Date: 2021/1/4
 * Time: 16:48
 * Author: LuoRong
 * Student Number:17302010081
 * Note:
 */
public interface TaskRepository extends CrudRepository<Task, Integer> {
}
