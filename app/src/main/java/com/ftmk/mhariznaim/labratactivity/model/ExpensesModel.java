package com.ftmk.mhariznaim.labratactivity.model;

/**
 * Created by mhariznaim on 21/9/2019.
 */

public class ExpensesModel {

    String strExpName;
    Double dblExpPrice;
    String strExpDate;

    public ExpensesModel(String strExpName, Double dblExpPrice, String strExpDate) {
        this.strExpName = strExpName;
        this.dblExpPrice = dblExpPrice;
        this.strExpDate = strExpDate;
    }

    public ExpensesModel() {
    }

    public String getStrExpName() {
        return strExpName;
    }

    public void setStrExpName(String strExpName) {
        this.strExpName = strExpName;
    }

    public Double getDblExpPrice() {
        return dblExpPrice;
    }

    public void setDblExpPrice(Double dblExpPrice) {
        this.dblExpPrice = dblExpPrice;
    }

    public String getStrExpDate() {
        return strExpDate;
    }

    public void setStrExpDate(String strExpDate) {
        this.strExpDate = strExpDate;
    }
}
