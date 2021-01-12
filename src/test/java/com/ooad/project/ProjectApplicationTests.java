package com.ooad.project;

import com.ooad.project.entity.*;
import com.ooad.project.service.CrudService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
class ProjectApplicationTests {

    @Autowired
    private CrudService crud;

    @Test
    void contextLoads() {
        System.out.println("OOAD project Test Starts!");
    }

    /* 监管局给一组农贸市场发起监管任务，农贸市场查看待完成任务，并完成抽检任务。 */
    @Test
    void case1() {
        System.out.println(">>> case1...");

        /* 一、监管局给一组农贸市场发起监管任务 */
        // 选取农贸市场
        List<Market> markets = Arrays.asList(crud.getMarket("Green Land Market"), crud.getMarket("Red Land Market"),
                crud.getMarket("Blue Sky Market"));

        // 选取农产品类别
        List<Product> products = Arrays.asList(crud.getProduct("Meat"),
                crud.getProduct("Aquatic"),crud.getProduct("Vegetable"));

        // 设定截至时间
        Date deadline = getDate("2020-01-10");

        // 创建监管任务
        markets.forEach(market -> {
            market.addMarketSelfCheckTask(crud.getMarketSelfCheckTask("Green Land Market 自检任务", products, deadline));
        });

        // 农贸市场查看待完成任务
        for(Market m: markets){
            List<MarketSelfCheckTask> unfinishedTasks = m.getUnfinishedTasks();
            Assertions.assertEquals(1, unfinishedTasks.size());
        }
    }

    private Date getDate(String dateStr) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}
