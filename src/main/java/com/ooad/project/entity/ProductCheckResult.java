package com.ooad.project.entity;

import java.util.Date;

/**
 * @author 刘佳兴
 * @date 2021/1/9 22:33
 * mail 1260968291@qq.com
 */
public class ProductCheckResult {

    public ProductCheckResult() {
    }

    public ProductCheckResult(Product product, int unqualifiedNumber) {
        this.product = product;
        this.unqualifiedNumber = unqualifiedNumber;
    }

    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getUnqualifiedNumber() {
        return unqualifiedNumber;
    }

    public void setUnqualifiedNumber(int unqualifiedNumber) {
        this.unqualifiedNumber = unqualifiedNumber;
    }

    private int unqualifiedNumber;

    public ProductCheckResult(Product product, int unqualifiedNumber, Date checkDate) {
        this.product = product;
        this.unqualifiedNumber = unqualifiedNumber;
        this.checkDate = checkDate;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    private Date checkDate;
}
