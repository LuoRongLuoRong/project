package com.ooad.project.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author 刘佳兴
 * @date 2021/1/9 21:24
 * mail 1260968291@qq.com
 */

@Getter
@Setter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    public Product(String name) {
        this.name = name;
    }

    public Product() {}

    @Override
    public boolean equals(Object o){
        return o instanceof Product && ((Product) o).name.equals(this.name);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
