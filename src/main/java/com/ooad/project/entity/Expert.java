package com.ooad.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 刘佳兴
 * @date 2021/1/9 21:24
 * mail 1260968291@qq.com
 */

@Data
@Entity
@AllArgsConstructor
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

    public List<ExpertSamplingTask> getTasks() {
        return tasks;
    }

    public void setTasks(List<ExpertSamplingTask> tasks) {
        this.tasks = tasks;
    }

    @Transient
    private List<ExpertSamplingTask> tasks = new ArrayList<>();

    @Override
    public boolean checkMyTask(ITask iTask) {
        return iTask instanceof ExpertSamplingTask && tasks.indexOf(iTask) >= 0;
    }

    @Override
    public List<ITask> getMyTasks() {
        return new ArrayList<>(tasks);
    }

    public void addExpertSamplingTask(ExpertSamplingTask expertSamplingTask) {
        tasks.add(expertSamplingTask);
    }

    public List<ExpertSamplingTask> getUnfinishedTasks(){
        List<ExpertSamplingTask> expertSamplingTasks = new ArrayList<>();
        tasks.stream().filter(expertSamplingTask -> (!expertSamplingTask.isFinished())).forEach(expertSamplingTasks::add);
        return expertSamplingTasks;
    }
}
