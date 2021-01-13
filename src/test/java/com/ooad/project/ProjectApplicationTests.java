package com.ooad.project;

import com.ooad.project.entity.*;
import com.ooad.project.entity.interfaces.ITask;
import com.ooad.project.service.CrudService;
import com.ooad.project.service.IndicatorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
class ProjectApplicationTests {

    @Autowired
    private CrudService crud;

    @Autowired
    private IndicatorService indicatorService;

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
        Market greenLandMarket = crud.getMarket("Green Land Market");
        List<Market> markets = Arrays.asList(greenLandMarket, crud.getMarket("Red Land Market"),
                crud.getMarket("Blue Sky Market"));

        // 选取农产品类别
        List<Product> products = Arrays.asList(crud.getProduct("Meat"),
                crud.getProduct("Aquatic"),crud.getProduct("Vegetable"));

        // 设定截至时间
        Date deadline = getDate("2020-01-10");


        // 创建监管任务
        System.out.println("下面为三个市场添加自检任务，自检的农产品类型有Meat，Aquatic，Vegetable。");
        markets.forEach(market -> {
            market.addMarketSelfCheckTask(crud.getMarketSelfCheckTask(market.getName() + "  task1", products, deadline));
        });

        // 农贸市场查看待完成任务
        System.out.println("添加任务后，所有市场直接检查自己的未完成任务情况，数量都为1。");
        for(Market m: markets){
            List<MarketSelfCheckTask> unfinishedTasks = m.getUnfinishedTasks();
            Assertions.assertEquals(1, unfinishedTasks.size());
        }

        // 绿地市场完成部分农产品的抽检（Meat和Aquatic），但总体上仍未完成
        System.out.println("绿地市场抽查自己的农产品，但只抽查了Meat，Aquatic，所以仍然未完成此自检任务。绿地市场未完成任务数量为1。");
        Date superviseDate = getDate("2020-01-09");
        List<ProductCheckResult> productCheckResultList = new ArrayList<>(Arrays.asList(new ProductCheckResult(crud.getProduct("Meat"), 10),
                new ProductCheckResult(crud.getProduct("Aquatic"),20)));
        greenLandMarket.supervise(greenLandMarket.getTasks().get(0), greenLandMarket, productCheckResultList,superviseDate);
        Assertions.assertEquals(1, greenLandMarket.getUnfinishedTasks().size());

        // 绿地市场完成最后的一个农产品的抽检，则它的抽检任务完成，未完成的抽检任务数为0
        System.out.println("绿地市场抽查自己的农产品Vegetable。至此，绿地市场抽查了本次自检任务的所有农产品，故绿地市场完成了此次抽检任务。绿地市场未完成任务数减1，变为0。");
        superviseDate = getDate("2020-01-10");
        productCheckResultList = new ArrayList<>(Collections.singletonList(new ProductCheckResult(crud.getProduct("Vegetable"), 10)));
        greenLandMarket.supervise(greenLandMarket.getTasks().get(0), greenLandMarket, productCheckResultList, superviseDate);
        Assertions.assertEquals(0, greenLandMarket.getUnfinishedTasks().size());

        // 红地市场完成抽检任务1。绿地市场未完成任务数减1，变为0
        System.out.println("红地市场完成抽检任务1。红地市场未完成任务数减1，变为0。");
        productCheckResultList = new ArrayList<>(
                Arrays.asList(
                        new ProductCheckResult(crud.getProduct("Meat"), 10),
                        new ProductCheckResult(crud.getProduct("Aquatic"), 15),
                        new ProductCheckResult(crud.getProduct("Vegetable"), 7)
                )
        );
        markets.get(1).supervise(markets.get(1).getTasks().get(0), markets.get(1), productCheckResultList, superviseDate);
        Assertions.assertEquals(0, markets.get(1).getUnfinishedTasks().size());
    }

    /* 监管局给一组专家发起监管任务，专家查看待完成任务，并完成抽检任务。 */
    @Test
    void case2() {
        System.out.println(">>> case2...");
        /* 一、监管局给一组专家发起监管任务 */

        // 选取专家
        Expert alikoExpert = crud.getExpert("Aliko");
        Expert billExpert = crud.getExpert("Bill");

        // 选取农贸市场
        Market greenLandMarket = crud.getMarket("Green Land Market");
        Market redLandMarket = crud.getMarket("Red Land Market");

        // 选取农产品类别
        List<Product> products = Arrays.asList(crud.getProduct("Meat"), crud.getProduct("Aquatic"));

        // 设定截至时间
        Date deadline = getDate("2020-01-10");

        // 创建监管任务
        System.out.println("下面为两个专家，分别添加抽检任务。Aliko专家是一个抽检任务两个市场；Bill专家是两个抽检任务，分别对应一个市场。抽检的农产品类型有Meat，Aquatic。");
        alikoExpert.addExpertSamplingTask(crud.getExpertSamplingTask("Aliko expert task1", products, Arrays.asList(greenLandMarket,redLandMarket),deadline));
        billExpert.addExpertSamplingTask(crud.getExpertSamplingTask("Bill expert task1", products, Collections.singletonList(greenLandMarket),deadline));
        billExpert.addExpertSamplingTask(crud.getExpertSamplingTask("Bill expert task2", products, Collections.singletonList(redLandMarket),deadline));

        // 专家查看待完成任务
        System.out.println("添加任务后，两个专家直接检查自己的未完成任务情况。Aliko 未完成任务数量为1，Bill未完成任务数量为2");
        Assertions.assertEquals(1, alikoExpert.getUnfinishedTasks().size());
        Assertions.assertEquals(2, billExpert.getUnfinishedTasks().size());

        // Bill专家抽检任务1的部分农产品，但该抽检任务仍未完成
        System.out.println("Bill专家抽检任务1的部分农产品Meat，于是该抽检任务仍未完成。Bill未完成任务数量为2。");
        Date superviseDate = getDate("2020-01-09");
        billExpert.supervise(billExpert.getTasks().get(0),greenLandMarket,
                new ArrayList<>(Collections.singletonList(new ProductCheckResult(crud.getProduct("Meat"), 10))), superviseDate);
        Assertions.assertEquals(2, billExpert.getUnfinishedTasks().size());

        // Bill专家完成抽检任务1的剩下农产品Aquatic，则该抽检任务完成，未完成的抽检任务数减1，变为1
        System.out.println("Bill专家完成抽检任务1的剩下农产品Aquatic，则该抽检任务完成，未完成的抽检任务数减1，变为1。");
        superviseDate = getDate("2020-01-10");
        billExpert.supervise(billExpert.getTasks().get(0),greenLandMarket,
                new ArrayList<>(Collections.singletonList(new ProductCheckResult(crud.getProduct("Aquatic"), 10))), superviseDate);
        Assertions.assertEquals(1, billExpert.getUnfinishedTasks().size());

        // Bill专家完成抽检任务2，于是未完成的抽检任务数减1，变为0
        System.out.println("Bill专家完成抽检任务2，于是未完成的抽检任务数减1，变为0。");
        superviseDate = getDate("2020-01-11");
        billExpert.supervise(billExpert.getTasks().get(1),
                redLandMarket,
                new ArrayList<>(
                        Arrays.asList(
                                new ProductCheckResult(crud.getProduct("Meat"), 10),
                                new ProductCheckResult(crud.getProduct("Aquatic"), 10)
                        )
                ),
                superviseDate);
        Assertions.assertEquals(0, billExpert.getUnfinishedTasks().size());
    }

    /* 监管局查看某个农贸产品类别在某个时间范围内的总的不合格数（时间以抽检日期为准）。 */
    @Test
    void case3() {
        System.out.println(">>> case3...");
        // 设置任务收集器
        List<ITask> allTasks = new ArrayList<>();

        // 选取专家
        Expert alikoExpert = crud.getExpert("Aliko");

        // 选取农贸市场
        Market greenLandMarket = crud.getMarket("Green Land Market");
        Market redLandMarket = crud.getMarket("Red Land Market");

        // 选取农产品类别
        List<Product> products = Arrays.asList(crud.getProduct("Meat"), crud.getProduct("Aquatic"));

        // 设定截至时间
        Date deadline = getDate("2020-01-10");

        // 创建两个自检任务
        System.out.println("创建两个自检任务。绿地市场和红地市场分别自检自己的农产品，农产品类别是Meat和Aquatic");
        MarketSelfCheckTask m1 = crud.getMarketSelfCheckTask("Green Land Market task1", products, deadline);
        MarketSelfCheckTask m2 = crud.getMarketSelfCheckTask("Red Land Market task1", products, deadline);
        greenLandMarket.addMarketSelfCheckTask(m1);
        redLandMarket.addMarketSelfCheckTask(m2);
        allTasks.add(m1);
        allTasks.add(m2);

        // 创建一个抽检任务
        System.out.println("创建一个抽检任务。Aliko专家抽检绿地市场的农产品，农产品类别是Meat和Aquatic");
        ExpertSamplingTask e1 = crud.getExpertSamplingTask("Aliko task1",products, Collections.singletonList(greenLandMarket),deadline);
        alikoExpert.addExpertSamplingTask(e1);
        allTasks.add(e1);

        // 红地市场2020-01-09，完成自检任务，Meat不合格数10，Aquatic不合格数15
        System.out.println("红地市场2020-01-09，完成自检任务，Meat不合格数10，Aquatic不合格数15");
        List<ProductCheckResult> productCheckResultList = new ArrayList<>(
                Arrays.asList(
                        new ProductCheckResult(crud.getProduct("Meat"), 10),
                        new ProductCheckResult(crud.getProduct("Aquatic"), 15)
                )
        );
        redLandMarket.supervise(redLandMarket.getTasks().get(0), redLandMarket, productCheckResultList, getDate("2020-01-09"));

        // 绿地市场2020-01-11，完成自检任务，Meat不合格数10，Aquatic不合格数15
        System.out.println("绿地市场2020-01-11，完成自检任务，Meat不合格数8，Aquatic不合格数7");
        productCheckResultList = new ArrayList<>(
                Arrays.asList(
                        new ProductCheckResult(crud.getProduct("Meat"), 8),
                        new ProductCheckResult(crud.getProduct("Aquatic"), 7)
                )
        );
        greenLandMarket.supervise(greenLandMarket.getTasks().get(0), redLandMarket, productCheckResultList, getDate("2020-01-11"));

        // Aliko专家2020-01-12，完成抽检任务，Meat不合格数6，Aquatic不合格数9
        System.out.println("Aliko专家2020-01-12，完成抽检任务。2020-01-11完成Meat抽检，Meat不合格数6，2020-01-12完成Aquatic抽检，Aquatic不合格数9。");
        productCheckResultList = new ArrayList<>(
                Collections.singletonList(
                        new ProductCheckResult(crud.getProduct("Meat"), 6)
                )
        );
        alikoExpert.supervise(alikoExpert.getTasks().get(0),greenLandMarket, productCheckResultList, getDate("2020-01-11"));
        productCheckResultList = new ArrayList<>(
                Collections.singletonList(
                        new ProductCheckResult(crud.getProduct("Aquatic"), 9)
                )
        );
        alikoExpert.supervise(alikoExpert.getTasks().get(0),greenLandMarket, productCheckResultList, getDate("2020-01-12"));

        // 验证某个阶段内的不合格数
        System.out.println("2020-01-09到2020-01-11时间段内（包括两端），Meat农产品不合格数应该为 10 + 8 + 6 = 24。");
        Assertions.assertEquals(24, indicatorService.getTotalUnqualifiedCount(allTasks, getDate("2020-01-09"), getDate("2020-01-11"), crud.getProduct("Meat")));
        System.out.println("2020-01-10到2020-01-12时间段内（包括两端），Aquatic农产品不合格数应该为 7 + 9 = 16。");
        Assertions.assertEquals(16, indicatorService.getTotalUnqualifiedCount(allTasks, getDate("2020-01-10"), getDate("2020-01-12"), crud.getProduct("Aquatic")));
    }

    /* 验证专家和农贸市场按时完成和未按时完成的抽检的场景，获取评估总得分和评估得/扣分的记录。 */
    @Test
    void case4() {
        System.out.println(">>> case4...");
        // 策略模式设置扣分策略
        MyFinishingScoreStrategy myFinishingScoreStrategy = MyFinishingScoreStrategy.instance();

        // 选取专家
        Expert alikoExpert = crud.getExpert("Aliko");

        // 选取农贸市场
        Market greenLandMarket = crud.getMarket("Green Land Market");
        Market redLandMarket = crud.getMarket("Red Land Market");
        Market blueSkyMarket = crud.getMarket("Blue Sky Market");

        // 选取农产品类别
        List<Product> products = Collections.singletonList(crud.getProduct("Meat"));

        // 为Aliko专家创建三个抽检任务，分别对应一个农贸市场，抽检农产品类型都是Meat
        System.out.println("为Aliko专家创建三个抽检任务，分别对应一个农贸市场，抽检农产品类型都是Meat。截止时间分别为2020-01-09，2020-01-20，2020-02-15");
        alikoExpert.addExpertSamplingTask(crud.getExpertSamplingTask("Aliko task1", products, Collections.singletonList(greenLandMarket),getDate("2020-01-09")));
        alikoExpert.addExpertSamplingTask(crud.getExpertSamplingTask("Aliko task2", products, Collections.singletonList(redLandMarket),getDate("2020-01-20")));
        alikoExpert.addExpertSamplingTask(crud.getExpertSamplingTask("Aliko task3", products, Collections.singletonList(blueSkyMarket),getDate("2020-02-15")));

        // 为绿地市场创建两个自检任务
        System.out.println("为绿地市场创建两个自检任务，抽检农产品类型都是Meat。截止时间分别为2020-01-07，2020-02-01");
        greenLandMarket.addMarketSelfCheckTask(crud.getMarketSelfCheckTask("Green Land Market task1",products, getDate("2020-01-07")));
        greenLandMarket.addMarketSelfCheckTask(crud.getMarketSelfCheckTask("Green Land Market task2",products, getDate("2020-02-01")));

        // 目前都没有完成，更新计时器时间为2020-01-30，则Aliko专家三个抽检任务对应分数为-20，-10，0，共计-30分
        System.out.println("目前都没有完成，更新计时器时间为2020-01-30，则Aliko专家三个抽检任务对应分数为-20，-10，0，共计-30分。");
        Assertions.assertEquals(-30, indicatorService.update(myFinishingScoreStrategy, alikoExpert, getDate("2020-01-30")));
        System.out.println("目前都没有完成，更新计时器时间为2020-01-28，则绿地市场两个自检任务对应分数为-10，-10，共计-20分。");
        Assertions.assertEquals(-20, indicatorService.update(myFinishingScoreStrategy, greenLandMarket, getDate("2020-01-28")));

        // Aliko专家于2020-01-08完成自己的第1个抽检任务，于2020-01-27完成自己的第2个抽检任务
        System.out.println("Aliko专家于2020-01-08完成自己的第1个抽检任务，于2020-01-27完成自己的第2个抽检任务。");
        alikoExpert.supervise(alikoExpert.getTasks().get(0),
                greenLandMarket,
                Collections.singletonList(new ProductCheckResult(crud.getProduct("Meat"), 10)),
                getDate("2020-01-08"));
        alikoExpert.supervise(alikoExpert.getTasks().get(1),
                redLandMarket,
                Collections.singletonList(new ProductCheckResult(crud.getProduct("Meat"), 15)),
                getDate("2020-01-27"));
        System.out.println("更新计时器时间为2020-01-28，则Aliko专家三个抽检任务对应分数为10，-10，0，共计0分。");
        Assertions.assertEquals(0, indicatorService.update(myFinishingScoreStrategy, alikoExpert, getDate("2020-01-28")));

        // 绿地市场于2020-01-07完成自己的第1个自检任务，于2020-02-21完成自己的第2个自检任务
        System.out.println("绿地市场于2020-01-07完成自己的第1个自检任务，于2020-02-21完成自己的第2个自检任务。");
        greenLandMarket.supervise(greenLandMarket.getTasks().get(0),
                greenLandMarket,
                Collections.singletonList(new ProductCheckResult(crud.getProduct("Meat"), 20)),
                getDate("2020-01-07"));
        greenLandMarket.supervise(greenLandMarket.getTasks().get(1),
                greenLandMarket,
                Collections.singletonList(new ProductCheckResult(crud.getProduct("Meat"), 9)),
                getDate("2020-02-21"));
        System.out.println("更新计时器时间为2020-01-30，则绿地市场两个自检任务对应分数为10，-20，共计-10分。");
        Assertions.assertEquals(-10, indicatorService.update(myFinishingScoreStrategy, greenLandMarket, getDate("2020-01-30")));

    }
    private Date getDate(String str) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}
