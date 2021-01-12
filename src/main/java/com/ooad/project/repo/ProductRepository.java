package com.ooad.project.repo;

import com.ooad.project.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 刘佳兴
 * @date 2021/1/9 21:21
 * mail 1260968291@qq.com
 */

@Repository
public interface ProductRepository extends CrudRepository<Product,Integer> {
    /**
     * 工厂模式，根据产品分类名称查找产品分类对象
     * @param name 产品分类名称
     * @return 产品分类对象
     */
    Product findByName(String name);
}
