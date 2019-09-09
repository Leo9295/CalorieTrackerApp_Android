/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fit5046.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Leo
 */
@Entity
@Table(name = "FOOD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Food.findAll", query = "SELECT f FROM Food f")
    , @NamedQuery(name = "Food.findByFoodid", query = "SELECT f FROM Food f WHERE f.foodid = :foodid")
    , @NamedQuery(name = "Food.findByFoodname", query = "SELECT f FROM Food f WHERE f.foodname = :foodname")
    , @NamedQuery(name = "Food.findByCategory", query = "SELECT f FROM Food f WHERE f.category = :category")
    , @NamedQuery(name = "Food.findByCalorieamount", query = "SELECT f FROM Food f WHERE f.calorieamount = :calorieamount")
    , @NamedQuery(name = "Food.findByServingunit", query = "SELECT f FROM Food f WHERE f.servingunit = :servingunit")
    , @NamedQuery(name = "Food.findByServingamount", query = "SELECT f FROM Food f WHERE f.servingamount = :servingamount")
    , @NamedQuery(name = "Food.findByFat", query = "SELECT f FROM Food f WHERE f.fat = :fat")})
public class Food implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "FOODID")
    private Long foodid;
    @Size(max = 30)
    @Column(name = "FOODNAME")
    private String foodname;
    @Size(max = 20)
    @Column(name = "CATEGORY")
    private String category;
    @Column(name = "CALORIEAMOUNT")
    private Integer calorieamount;
    @Size(max = 20)
    @Column(name = "SERVINGUNIT")
    private String servingunit;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SERVINGAMOUNT")
    private Double servingamount;
    @Column(name = "FAT")
    private Integer fat;
    @OneToMany(mappedBy = "foodid")
    private Collection<Consumption> consumptionCollection;

    public Food() {
    }

    public Food(Long foodid) {
        this.foodid = foodid;
    }

    public Long getFoodid() {
        return foodid;
    }

    public void setFoodid(Long foodid) {
        this.foodid = foodid;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getCalorieamount() {
        return calorieamount;
    }

    public void setCalorieamount(Integer calorieamount) {
        this.calorieamount = calorieamount;
    }

    public String getServingunit() {
        return servingunit;
    }

    public void setServingunit(String servingunit) {
        this.servingunit = servingunit;
    }

    public Double getServingamount() {
        return servingamount;
    }

    public void setServingamount(Double servingamount) {
        this.servingamount = servingamount;
    }

    public Integer getFat() {
        return fat;
    }

    public void setFat(Integer fat) {
        this.fat = fat;
    }

    @XmlTransient
    public Collection<Consumption> getConsumptionCollection() {
        return consumptionCollection;
    }

    public void setConsumptionCollection(Collection<Consumption> consumptionCollection) {
        this.consumptionCollection = consumptionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (foodid != null ? foodid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Food)) {
            return false;
        }
        Food other = (Food) object;
        if ((this.foodid == null && other.foodid != null) || (this.foodid != null && !this.foodid.equals(other.foodid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fit5046.entity.Food[ foodid=" + foodid + " ]";
    }
    
}
