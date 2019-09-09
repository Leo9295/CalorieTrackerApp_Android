package com.fit5046.RESTfulWSEntities;

import java.util.Date;

public class Report {
    private long reportid;
    private Appuser userid;
    private Date reportdate;
    private Integer caloriesconsumed;
    private Integer caloriesburned;
    private Integer totalstepsnum;
    private Integer claoriegoal;

    public Report () { };
    public Report(long reportid, Appuser userid, Date reportdate) {
        this.reportid = reportid;
        this.userid = userid;
        this.reportdate = reportdate;
    }

    public long getReportid() {
        return reportid;
    }

    public void setReportid(long reportid) {
        this.reportid = reportid;
    }

    public Appuser getUserid() {
        return userid;
    }

    public void setUserid(Appuser userid) {
        this.userid = userid;
    }

    public Date getReportdate() {
        return reportdate;
    }

    public void setReportdate(Date reportdate) {
        this.reportdate = reportdate;
    }

    public Integer getCaloriesconsumed() {
        return caloriesconsumed;
    }

    public void setCaloriesconsumed(Integer caloriesconsumed) {
        this.caloriesconsumed = caloriesconsumed;
    }

    public Integer getCaloriesburned() {
        return caloriesburned;
    }

    public void setCaloriesburned(Integer caloriesburned) {
        this.caloriesburned = caloriesburned;
    }

    public Integer getTotalstepsnum() {
        return totalstepsnum;
    }

    public void setTotalstepsnum(Integer totalstepsnum) {
        this.totalstepsnum = totalstepsnum;
    }

    public Integer getClaoriegoal() {
        return claoriegoal;
    }

    public void setClaoriegoal(Integer claoriegoal) {
        this.claoriegoal = claoriegoal;
    }
}
