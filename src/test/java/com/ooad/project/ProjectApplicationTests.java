package com.ooad.project;

import com.ooad.project.entity.*;
import com.ooad.project.service.MarketTaskQueryService;
import com.ooad.project.service.ProductReportService;
import com.ooad.project.service.TaskReportService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
class ProjectApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("OOAD project Test Starts!");
    }

    /* 监管局给一组农贸市场发起监管任务，农贸市场查看待完成任务，并完成抽检任务。 */
    @Test
    void case1() {
        System.out.println(">>> case1...");

        /* 一、监管局给一组农贸市场发起监管任务 */
        // 新建农贸市场
        Market greenLandMarket = new Market("Green Land Market"); // TODO: 工厂模式
        Market redLandMarket = new Market("Red Land Market");
        Market blueSkyMarket = new Market("Blue Sky Market");
        List<Market> markets = Arrays.asList(greenLandMarket, redLandMarket, blueSkyMarket);

        // 新建农产品
        Product vegetable = new Product("Vegetable");
        Product aquatic = new Product("Aquatic");
        Product meat = new Product("Meat");
        List<Product> products = Arrays.asList(vegetable, aquatic, meat);

        // 创建时间
        Date startDate = getDate("2021-01-01");
        Date deadlineDate = getDate("2021-01-10");

        // 新建监督任务 String name, List<Market> markets, List<Product> products, Date startDate, Date deadlineDate
        Task task = new Task("20210105-重点市场", markets, products, startDate, deadlineDate);

        /* 二、农贸市场查看待完成任务，并完成抽检任务。 */
        // 1. 绿地农贸市场
        checkMarket(greenLandMarket, products, task, new Date());

        // 2. 红土农贸市场，如上
        checkMarket(redLandMarket, products, task, new Date());

        // 3. 蓝天农贸市场，如上
        checkMarket(blueSkyMarket, products, task, new Date());

        // 所有 MarketTask 抽检任务完成
        task.setFinishDate(new Date());
        task.setFinished(true);
    }

    /* 完成抽检市场的每项产品 */
    private void checkMarket(Market greenLandMarket, List<Product> products, Task task, Date date) {
        MarketTaskQueryService greenLandMarketTaskQueryService = new MarketTaskQueryService(greenLandMarket, task);
        MarketTask greenLandMarketTask = greenLandMarketTaskQueryService.getMarketTask();
        MarketTaskReport greenLandMarketTaskReport = greenLandMarketTask.getMarketTaskReport();

        for (Product product: products) {
            // 农产品抽检结果上传
            recordAndUploadProductCheckReport(product, greenLandMarketTaskReport, (int)(Math.random() * 10), new Date());
        }

        // 该 MarketTask 抽检全部完成
        greenLandMarketTask.setFinishDate(date);
        greenLandMarketTask.setFinished(true);
    }

    /* 在报告上记录某一项农产品的抽检结果 */
    private void recordAndUploadProductCheckReport(Product vegetable, MarketTaskReport greenLandMarketTaskReport, int unqualifiedNumber, Date finishDate) {
        // 记录抽检结果
        ProductCheckRecord greenLandMarketVegCheckRecord = creatProductCheckRecord(vegetable, unqualifiedNumber, finishDate);
        // 上传抽检结果
        greenLandMarketTaskReport.addProductCheckRecord(greenLandMarketVegCheckRecord);
    }

    /* 记录农产品的抽检结果 */
    private ProductCheckRecord creatProductCheckRecord(Product product, int unqualifiedNumber, Date finishDate) {
        ProductCheckRecord productCheckRecord = new ProductCheckRecord();
        productCheckRecord.setProduct(product);
        productCheckRecord.setUnqualifiedNumber(unqualifiedNumber);
        productCheckRecord.setFinishDate(finishDate);
        productCheckRecord.setFinished(true);
        return productCheckRecord;
    }

    /* 监管局给一组专家发起监管任务，专家查看待完成任务，并完成抽检任务。 */
    @Test
    void case2() {
        System.out.println(">>> case2...");

        // 新建一组任务
        /* 一、监管局给一组专家发起监管任务 */
        // 新建一组专家
        Expert lucyExpert = new Expert("Lucy");
        Expert lilyExpert = new Expert("Lily");
        Expert dinoExpert = new Expert("Dino");

        // 新建农贸市场
        Market greenLandMarket = new Market("Green Land Market");
        Market redLandMarket = new Market("Red Land Market");
        Market blueSkyMarket = new Market("Blue Sky Market");
        List<Market> markets = Arrays.asList(greenLandMarket, redLandMarket, blueSkyMarket);

        // 新建农产品
        Product vegetable = new Product("Vegetable");
        Product aquatic = new Product("Aquatic");
        Product meat = new Product("Meat");
        List<Product> products = Arrays.asList(vegetable, aquatic, meat);

        // 创建时间
        Date startDate = getDate("2021-01-02");
        Date deadlineDate = getDate("2021-01-10");

        // 新建专家监督任务
        Task lucyTask = new Task("20210101-重点市场-专家", markets, products, startDate, deadlineDate, lucyExpert);
        Task lilyTask = new Task("20210102-重点市场-专家", markets, products, startDate, deadlineDate, lilyExpert);
        Task dinoTask = new Task("20210103-重点市场-专家", markets, products, startDate, deadlineDate, dinoExpert);

        // 二、专家查看待完成任务，并完成抽检任务。
        // 1. Lucy
        // 查看待完成任务，并完成抽检
        List<Market> lucyMarkets = lucyTask.getMarkets();
        for (Market market: lucyMarkets) {
            checkMarket(market, lucyTask.getProducts(), lucyTask, new Date());
        }

        // 2. Lily: 同 Lucy
        List<Market> lilyMarkets = lilyTask.getMarkets();
        for (Market market: lilyMarkets) {
            checkMarket(market, lilyTask.getProducts(), lilyTask, new Date());
        }

        // 3. Dino：同 Lucy
        List<Market> dinoMarkets = lucyTask.getMarkets();
        for (Market market: dinoMarkets) {
            checkMarket(market, dinoTask.getProducts(), dinoTask, new Date());
        }
    }

    /* 监管局查看某个农贸产品类别在某个时间范围内的总的不合格数（时间以抽检日期为准） */
    @Test
    void case3() {
        System.out.println(">>> case3...");

        /* 一、监管局给一组农贸市场发起监管任务 */
        // 新建农贸市场
        Market greenLandMarket = new Market("Green Land Market"); // TODO: 工厂模式
        Market redLandMarket = new Market("Red Land Market");
        Market blueSkyMarket = new Market("Blue Sky Market");
        List<Market> markets = Arrays.asList(greenLandMarket, redLandMarket, blueSkyMarket);

        // 新建农产品
        Product vegetable = new Product("Vegetable");
        Product aquatic = new Product("Aquatic");
        Product meat = new Product("Meat");
        List<Product> products = Arrays.asList(vegetable, aquatic, meat);

        // 新建一组专家
        Expert lucyExpert = new Expert("Lucy");
        Expert lilyExpert = new Expert("Lily");
        Expert dinoExpert = new Expert("Dino");

        // 创建时间
        Date startDate = getDate("2021-01-01");
        Date deadlineDate = getDate("2021-01-10");

        // 新建监督任务 String name, List<Market> markets, List<Product> products, Date startDate, Date deadlineDate
        Task task = new Task("20210105-重点市场", markets, products, startDate, deadlineDate);

        /* 二、农贸市场查看待完成任务，并完成抽检任务。 */
        // 1. 绿地农贸市场：查看并完成抽检任务
        checkMarket(greenLandMarket, products, task, new Date());

        // 2. 红土农贸市场，如上
        checkMarket(redLandMarket, products, task, new Date());

        // 3. 蓝天农贸市场，如上
        checkMarket(blueSkyMarket, products, task, new Date());

        // 新建专家监督任务
        Task lucyTask = new Task("20210101-重点市场-专家", markets, products, startDate, deadlineDate, lucyExpert);
        Task lilyTask = new Task("20210102-重点市场-专家", markets, products, startDate, deadlineDate, lilyExpert);
        Task dinoTask = new Task("20210103-重点市场-专家", markets, products, startDate, deadlineDate, dinoExpert);

        // 二、专家查看待完成任务，并完成抽检任务。
        // 1. Lucy
        // 查看待完成任务，并完成抽检
        List<Market> lucyMarkets = lucyTask.getMarkets();
        for (Market market: lucyMarkets) {
            checkMarket(market, lucyTask.getProducts(), lucyTask, new Date());
        }

        // 2. Lily: 同 Lucy
        List<Market> lilyMarkets = lilyTask.getMarkets();
        for (Market market: lilyMarkets) {
            checkMarket(market, lilyTask.getProducts(), lilyTask, new Date());
        }

        // 3. Dino：同 Lucy
        List<Market> dinoMarkets = lucyTask.getMarkets();
        for (Market market: dinoMarkets) {
            checkMarket(market, dinoTask.getProducts(), dinoTask, new Date());
        }

        TaskReportService marketsTaskReportService = new TaskReportService(task);
        TaskReportService lucyTaskReportService = new TaskReportService(lucyTask);
        TaskReportService lilyTaskReportService = new TaskReportService(lilyTask);
        TaskReportService dinoTaskReportService = new TaskReportService(dinoTask);

        // 查看某个农贸产品类别在某个时间范围内的总的不合格数
        ProductReportService productReportService = new ProductReportService();
        int vegUnqualifiedNumber = productReportService.getUnqualifiedProductNumberByInterval(vegetable, new Date(), new Date());
        System.out.println(vegetable.getName() + " 不合格数目：" + vegUnqualifiedNumber);

    }

    /* 验证专家和农贸市场按时完成和未按时完成的抽检的场景，获取评估总得分和评估得 / 扣分的记录。测试代码中可以模拟定时器调用IndicatorService.update，需要设计控制系统时间的方法。*/
    @Test
    void case4() {
        System.out.println(">>> case4...");

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
