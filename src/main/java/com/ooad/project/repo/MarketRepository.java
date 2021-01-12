package com.ooad.project.repo;
import com.ooad.project.entity.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 刘佳兴
 * @date 2021/1/9 21:18
 * mail 1260968291@qq.com
 */

@Repository
public interface MarketRepository extends JpaRepository<Market,Integer> {
    /**
     * 工厂模式，根据农贸市场名称查找农贸市场对象
     * @param name 农贸市场名称
     * @return 农贸市场对象
     */
    Market findByName(String name);
}
