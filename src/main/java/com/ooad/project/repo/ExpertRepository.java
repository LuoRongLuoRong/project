package com.ooad.project.repo;

import com.ooad.project.entity.Expert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * User: Admin
 * Date: 2020/12/26
 * Time: 17:08
 * Author: LuoRong
 * Student Number:17302010081
 * Note:
 */
@Repository
public interface ExpertRepository extends CrudRepository<Expert, Integer> {

}
