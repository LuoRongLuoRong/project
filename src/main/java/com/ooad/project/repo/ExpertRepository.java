package com.ooad.project.repo;

import com.ooad.project.entity.Expert;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 刘佳兴
 * @date 2021/1/9 21:16
 * mail 1260968291@qq.com
 */

@Repository
public interface ExpertRepository extends CrudRepository<Expert,Integer> {
    /**
     * 工厂模式，根据专家的名称找到专家对象
     * @param name 名称
     * @return 专家
     */
    Expert findByName(String name);
}
