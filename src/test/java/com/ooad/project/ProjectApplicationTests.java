package com.ooad.project;

import com.ooad.project.entity.*;
import com.ooad.project.service.MarketTaskQueryService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = new Date();
        Date deadlineDate = new Date();
        try {
            startDate = format.parse("2021-01-01");
            deadlineDate = format.parse("2021-01-15");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // 新建监督任务 String name, List<Market> markets, List<Product> products, Date startDate, Date deadlineDate
        Task task = new Task("20210105-重点市场", markets, products, startDate, deadlineDate);

        /* 二、农贸市场查看待完成任务，并完成抽检任务。 */
        // 1. 绿地农贸市场
        // 1.1 查看完成任务
        MarketTaskQueryService greenLandMarketTaskQueryService = new MarketTaskQueryService(greenLandMarket, task);
        MarketTask greenLandMarketTask = greenLandMarketTaskQueryService.getMarketTask();
        MarketTaskReport greenLandMarketTaskReport = greenLandMarketTask.getMarketTaskReport();
        // 1.2 农产品一号：蔬菜 veg
        recordAndUploadProductCheckReport(vegetable, greenLandMarketTaskReport, 1, new Date());
        // 1.3 农产品二号：水产品 aqu
        recordAndUploadProductCheckReport(aquatic, greenLandMarketTaskReport, 2, new Date());
        // 1.4 农产品三号：mea
        recordAndUploadProductCheckReport(meat, greenLandMarketTaskReport, 5, new Date());
        // 1.5 该 MarketTask 抽检全部完成
        greenLandMarketTask.setFinishDate(new Date());
        greenLandMarketTask.setFinished(true);

        // 2. 红土农贸市场，如上

        // 3. 蓝天农贸市场，如上
    }

    /* 在报告上记录某一项农产品的抽检结果 */
    private void recordAndUploadProductCheckReport(Product vegetable, MarketTaskReport greenLandMarketTaskReport, int unqualifiedNumber, Date finishDate) {
        // 记录抽检结果
        ProductCheckRecord greenLandMarketVegCheckRecord = creatProductCheckRecord(vegetable, unqualifiedNumber, finishDate);
        // 上传抽检结果
        greenLandMarketTaskReport.addProductCheckRecord(greenLandMarketVegCheckRecord);
    }

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
        // 新建一组专家
        Expert lucy = new Expert("Lucy");
        Expert lily = new Expert("Lily");
        Expert dino = new Expert("Dino");



    }

    /* 监管局查看某个农贸产品类别在某个时间范围内的总的不合格数（时间以抽检日期为准） */
    @Test
    void case3() {
        System.out.println(">>> case3...");
    }

    /* 验证专家和农贸市场按时完成和未按时完成的抽检的场景，获取评估总得分和评估得 / 扣分的记录。测试代码中可以模拟定时器调用IndicatorService.update，需要设计控制系统时间的方法。*/
    @Test
    void case4() {
        System.out.println(">>> case4...");
    }

}
