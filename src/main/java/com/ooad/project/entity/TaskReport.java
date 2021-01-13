package com.ooad.project.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author 刘佳兴
 * @date 2021/1/13 9:44
 * mail 1260968291@qq.com
 */
public class TaskReport {

    Map<Market,List<ProductCheckResult>> checkResults = new HashMap<>();

    public List<ProductCheckResult> findByProduct(Product product) {
        List<ProductCheckResult> pcr = new ArrayList<>();
        checkResults.values().forEach(productCheckResults -> {
            productCheckResults.forEach(productCheckResult -> {
                if(productCheckResult.getProduct().equals(product)){
                    pcr.add(productCheckResult);
                }
            });
        });
        return pcr;
    }

    private List<Product> steam2List(Stream<Product> productStream){
        List<Product> products = new ArrayList<>();
        productStream.forEach(products::add);
        return products;
    }

    List<ProductCheckResult> addSuperviseResult(Market market,  List<ProductCheckResult> productCheckResultList){
        List<ProductCheckResult> productCheckResults = checkResults.get(market);
        if(productCheckResults == null){
            checkResults.put(market, productCheckResultList);
        } else {
            List<Product> checkedProducts = steam2List(productCheckResults.stream().map(ProductCheckResult::getProduct));
            for(ProductCheckResult p: productCheckResultList){
                if(checkedProducts.indexOf(p.getProduct()) < 0){
                    productCheckResults.add(p);
                }
            }
        }
        return checkResults.get(market);
    }

    public List<ProductCheckResult> findByMarket(Market market) {
        return checkResults.get(market);
    }
}
