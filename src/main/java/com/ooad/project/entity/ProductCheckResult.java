package com.ooad.project.entity;

/**
 * @author 刘佳兴
 * @date 2021/1/9 22:33
 * mail 1260968291@qq.com
 */
public class ProductCheckResult {
    public int getUnqualifiedNumber() {
        return unqualifiedNumber;
    }

    public void setUnqualifiedNumber(int unqualifiedNumber) {
        this.unqualifiedNumber = unqualifiedNumber;
    }

    int unqualifiedNumber;
    public ProductCheckResult(int unqualifiedNumber){
        this.unqualifiedNumber = unqualifiedNumber;
    }
}
