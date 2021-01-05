package com.ooad.project;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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

    }

    /* 监管局给一组专家发起监管任务，专家查看待完成任务，并完成抽检任务。 */
    @Test
    void case2() {
        System.out.println(">>> case2...");
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
