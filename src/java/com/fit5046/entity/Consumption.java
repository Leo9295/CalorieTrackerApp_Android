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
@Table(name = "CONSUMPTION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Consumption.findAll", query = "SELECT c FROM Consumption c")
    , @NamedQuery(name = "Consumption.findByRecordid", query = "SELECT c FROM Consumption c WHERE c.recordid = :recordid")
    , @NamedQuery(name = "Consumption.findByRecorddate", query = "SELECT c FROM Consumption c WHERE c.recorddate = :recorddate")
    , @NamedQuery(name = "Consumption.findByConsumptionamount", query = "SELECT c FROM Consumption c WHERE c.consumptionamount = :consumptionamount")
    /*New Static Query for task3d*/
    , @NamedQuery(name = "Consumption.findByRecorddateAndSurname", query = "SELECT c FROM Consumption c WHERE c.userid.surname = :surname AND c.recorddate = :recorddate")})
public class Consumption implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "RECORDID")
    private Long recordid;
    @Column(name = "RECORDDATE")
    @Temporal(TemporalType.DATE)
    private Date recorddate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CONSUMPTIONAMOUNT")
    private Double consumptionamount;
    @JoinColumn(name = "USERID", referencedColumnName = "USERID")
    @ManyToOne
    private Appuser userid;
    @JoinColumn(name = "FOODID", referencedColumnName = "FOODID")
    @ManyToOne
    private Food foodid;

    public Consumption() {
    }

    public Consumption(Long recordid) {
        this.recordid = recordid;
    }

    public Long getRecordid() {
        return recordid;
    }

    public void setRecordid(Long recordid) {
        this.recordid = recordid;
    }

    public Date getRecorddate() {
        return recorddate;
    }

    public void setRecorddate(Date recorddate) {
        this.recorddate = recorddate;
    }

    public Double getConsumptionamount() {
        return consumptionamount;
    }

    public void setConsumptionamount(Double consumptionamount) {
        this.consumptionamount = consumptionamount;
    }

    public Appuser getUserid() {
        return userid;
    }

    public void setUserid(Appuser userid) {
        this.userid = userid;
    }

    public Food getFoodid() {
        return foodid;
    }

    public void setFoodid(Food foodid) {
        this.foodid = foodid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recordid != null ? recordid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Consumption)) {
            return false;
        }
        Consumption other = (Consumption) object;
        if ((this.recordid == null && other.recordid != null) || (this.recordid != null && !this.recordid.equals(other.recordid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fit5046.entity.Consumption[ recordid=" + recordid + " ]";
    }
    
}
