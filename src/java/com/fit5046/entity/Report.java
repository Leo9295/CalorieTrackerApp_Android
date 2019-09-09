/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fit5046.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Leo
 */
@Entity
@Table(name = "REPORT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Report.findAll", query = "SELECT r FROM Report r")
    , @NamedQuery(name = "Report.findByReportid", query = "SELECT r FROM Report r WHERE r.reportid = :reportid")
    , @NamedQuery(name = "Report.findByReportdate", query = "SELECT r FROM Report r WHERE r.reportdate = :reportdate")
    , @NamedQuery(name = "Report.findByCaloriesconsumed", query = "SELECT r FROM Report r WHERE r.caloriesconsumed = :caloriesconsumed")
    , @NamedQuery(name = "Report.findByCaloriesburned", query = "SELECT r FROM Report r WHERE r.caloriesburned = :caloriesburned")
    , @NamedQuery(name = "Report.findByTotalstepsnum", query = "SELECT r FROM Report r WHERE r.totalstepsnum = :totalstepsnum")
    , @NamedQuery(name = "Report.findByClaoriegoal", query = "SELECT r FROM Report r WHERE r.claoriegoal = :claoriegoal")})
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "REPORTID")
    private Long reportid;
    @Column(name = "REPORTDATE")
    @Temporal(TemporalType.DATE)
    private Date reportdate;
    @Column(name = "CALORIESCONSUMED")
    private Integer caloriesconsumed;
    @Column(name = "CALORIESBURNED")
    private Integer caloriesburned;
    @Column(name = "TOTALSTEPSNUM")
    private Integer totalstepsnum;
    @Column(name = "CLAORIEGOAL")
    private Integer claoriegoal;
    @JoinColumn(name = "USERID", referencedColumnName = "USERID")
    @ManyToOne
    private Appuser userid;

    public Report() {
    }

    public Report(Long reportid) {
        this.reportid = reportid;
    }

    public Long getReportid() {
        return reportid;
    }

    public void setReportid(Long reportid) {
        this.reportid = reportid;
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

    public Appuser getUserid() {
        return userid;
    }

    public void setUserid(Appuser userid) {
        this.userid = userid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reportid != null ? reportid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Report)) {
            return false;
        }
        Report other = (Report) object;
        if ((this.reportid == null && other.reportid != null) || (this.reportid != null && !this.reportid.equals(other.reportid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fit5046.entity.Report[ reportid=" + reportid + " ]";
    }
    
}
