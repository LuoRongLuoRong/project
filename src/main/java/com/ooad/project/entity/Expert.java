package com.ooad.project.entity;

import com.ooad.project.entity.interfaces.ISupervise;
import com.ooad.project.entity.interfaces.ITask;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author 刘佳兴
 * @date 2021/1/9 21:24
 * mail 1260968291@qq.com
 */

@Getter
@Setter
@Entity
public class Expert implements ISupervise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    public Expert() {}

    public Expert(String name) {
        this.name = name;
    }

    @Transient
    private List<ExpertSamplingTask> tasks;

    @Override
    public boolean checkMyTask(ITask iTask) {
        return iTask instanceof ExpertSamplingTask && tasks.indexOf(iTask) >= 0;
    }
}
