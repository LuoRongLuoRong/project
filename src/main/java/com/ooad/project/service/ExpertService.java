package com.ooad.project.service;

import com.ooad.project.entity.Expert;
import com.ooad.project.repo.ExpertRepository;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Admin
 * Date: 2021/1/4
 * Time: 16:35
 * Author: LuoRong
 * Student Number:17302010081
 * Note: 用于测试
 * 代码参考 https://geek-docs.com/spring-boot/spring-boot-tutorials/crudrepository.html
 */

@Service
public class ExpertService {

    // IDEA 提示，此处不建议用自动装配，why?
    @Autowired
    private ExpertRepository expertRepository;

//    public ExpertService(ExpertRepository expertRepository) {
//        this.expertRepository = expertRepository;
//    }

    // 方法属于 CrudRepository
    public List<Expert> findAll() {
        var it = expertRepository.findAll();

        var users = new ArrayList<Expert>();
        it.forEach(e -> users.add(e));

        return users;
    }
}
