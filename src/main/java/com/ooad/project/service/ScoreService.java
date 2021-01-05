package com.ooad.project.service;

import com.ooad.project.entity.Market;
import com.ooad.project.repo.MarketRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * User: Admin
 * Date: 2021/1/4
 * Time: 20:47
 * Author: LuoRong
 * Student Number:17302010081
 * Note:
 *
 + getScoreByMarketID(id: int)
 + getScoreByMarketName(name: String)
 + getRecords(market: Market)  // 得分和扣分的情况
 *
 */

@Getter
public class ScoreService {

    private Market market;

    @Autowired
    private MarketRepository marketRepository;

    public ScoreService(Market market) {
        this.market = market;
    }

    public int getScoreByMarketID(int marketId) {
        return marketRepository.getScoreById(marketId);
    }

    public int getScoreByMarketName(String marketName) {
        return marketRepository.getScoreByName(marketName);
    }
}
