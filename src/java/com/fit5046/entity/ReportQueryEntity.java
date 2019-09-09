/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fit5046.entity;

import java.util.Date;

/**
 *
 * @author Leo
 */
public class ReportQueryEntity {
    
    private Appuser user;
    private Date queryDate;
    private Date periodStartDate;
    private Date periodEndDate;
    private double totalConsumedCalories;
    private double totalBurnedCalories;
    private double singleDayConsumedCalories;
    private double singleDayBurnedCalories;
    private double singleDayRemainingCalorie;
    private int totalStepNum;
    
    public Appuser getUser(){
        return user;
    }
    
    public void setUser(Appuser user){
        this.user = user;
    }

    public Date getQueryDate() {
        return queryDate;
    }

    public void setQueryDate(Date queryDate) {
        this.queryDate = queryDate;
    }

    public Date getPeriodStartDate() {
        return periodStartDate;
    }

    public void setPeriodStartDate(Date periodStartDate) {
        this.periodStartDate = periodStartDate;
    }

    public Date getPeriodEndDate() {
        return periodEndDate;
    }

    public void setPeriodEndDate(Date periodEndDate) {
        this.periodEndDate = periodEndDate;
    }

    public double getTotalConsumedCalories() {
        return totalConsumedCalories;
    }

    public void setTotalConsumedCalories(double totalConsumedCalories) {
        this.totalConsumedCalories = totalConsumedCalories;
    }

    public double getTotalBurnedCalories() {
        return totalBurnedCalories;
    }

    public void setTotalBurnedCalories(double totalBurnedCalories) {
        this.totalBurnedCalories = totalBurnedCalories;
    }

    public double getSingleDayRemainingCalorie() {
        return singleDayRemainingCalorie;
    }

    public void setSingleDayRemainingCalorie(double singleDayRemainingCalorie) {
        this.singleDayRemainingCalorie = singleDayRemainingCalorie;
    }

    public int getTotalStepNum() {
        return totalStepNum;
    }

    public void setTotalStepNum(int totalStepNum) {
        this.totalStepNum = totalStepNum;
    }

    public double getSingleDayConsumedCalories() {
        return singleDayConsumedCalories;
    }

    public void setSingleDayConsumedCalories(double singleDayConsumedCalories) {
        this.singleDayConsumedCalories = singleDayConsumedCalories;
    }

    public double getSingleDayBurnedCalories() {
        return singleDayBurnedCalories;
    }

    public void setSingleDayBurnedCalories(double singleDayBurnedCalories) {
        this.singleDayBurnedCalories = singleDayBurnedCalories;
    }
    
    
    
    
}
