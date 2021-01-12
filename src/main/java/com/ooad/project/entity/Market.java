package com.ooad.project.entity;
import com.ooad.project.entity.interfaces.ISupervise;
import com.ooad.project.entity.interfaces.ITask;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author 刘佳兴
 * @date 2021/1/9 21:24
 * mail 1260968291@qq.com
 */

@Data
@Entity
@AllArgsConstructor
public class Market implements ISupervise, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    private String name;

    @Transient
    private List<MarketSelfCheckTask> tasks;

    public Market(String name) {
        this.name = name;
    }

    public Market() {}

    public void addMarketSelfCheckTask(MarketSelfCheckTask marketSelfCheckTask) {
        tasks.add(marketSelfCheckTask);
    }

    public List<MarketSelfCheckTask> getUnfinishedTasks(){
        return (List<MarketSelfCheckTask>) tasks.stream().filter(MarketSelfCheckTask::isFinished);
    }

    @Override
    public boolean checkMyTask(ITask iTask) {
        return iTask instanceof MarketSelfCheckTask && tasks.indexOf(iTask) >= 0;
    }
}