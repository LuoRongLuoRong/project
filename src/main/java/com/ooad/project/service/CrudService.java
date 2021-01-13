package com.ooad.project.service;

import com.ooad.project.entity.*;
import com.ooad.project.repo.ExpertRepository;
import com.ooad.project.repo.MarketRepository;
import com.ooad.project.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author 刘佳兴
 * @date 2021/1/9 21:24
 * mail 1260968291@qq.com
 */

@Service
@RestController
public class CrudService {

    @Autowired
    private ExpertRepository expertRepository;

    @Autowired
    private MarketRepository marketRepository;

    @Autowired
    private ProductRepository productRepository;

    public Expert getExpert(String name){
        return expertRepository.findByName(name);
    }

    public Market getMarket(String name){
        return marketRepository.findByName(name);
    }

    public Product getProduct(String name){
        return productRepository.findByName(name);
    }

    public MarketSelfCheckTask getMarketSelfCheckTask(String description, List<Product> products, Date deadline){
        return new MarketSelfCheckTask(description, products, deadline);
    }

    public ExpertSamplingTask getExpertSamplingTask(String description, List<Product> products, List<Market> markets, Date deadline){
        return new ExpertSamplingTask(description, products, markets, deadline);
    }

}
