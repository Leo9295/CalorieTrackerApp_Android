/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fit5046.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Leo
 */
@Entity
@Table(name = "APPUSER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Appuser.findAll", query = "SELECT a FROM Appuser a")
    , @NamedQuery(name = "Appuser.findByUserid", query = "SELECT a FROM Appuser a WHERE a.userid = :userid")
    , @NamedQuery(name = "Appuser.findByFirstname", query = "SELECT a FROM Appuser a WHERE a.firstname = :firstname")
    , @NamedQuery(name = "Appuser.findBySurname", query = "SELECT a FROM Appuser a WHERE a.surname = :surname")
    , @NamedQuery(name = "Appuser.findByEmail", query = "SELECT a FROM Appuser a WHERE a.email = :email")
    , @NamedQuery(name = "Appuser.findByDateofbirth", query = "SELECT a FROM Appuser a WHERE a.dateofbirth = :dateofbirth")
    , @NamedQuery(name = "Appuser.findByHeight", query = "SELECT a FROM Appuser a WHERE a.height = :height")
    , @NamedQuery(name = "Appuser.findByWeight", query = "SELECT a FROM Appuser a WHERE a.weight = :weight")
    , @NamedQuery(name = "Appuser.findByGender", query = "SELECT a FROM Appuser a WHERE a.gender = :gender")
    , @NamedQuery(name = "Appuser.findByAddress", query = "SELECT a FROM Appuser a WHERE a.address = :address")
    , @NamedQuery(name = "Appuser.findByPostcode", query = "SELECT a FROM Appuser a WHERE a.postcode = :postcode")
    , @NamedQuery(name = "Appuser.findByActivitylevel", query = "SELECT a FROM Appuser a WHERE a.activitylevel = :activitylevel")
    , @NamedQuery(name = "Appuser.findByStepsnumber", query = "SELECT a FROM Appuser a WHERE a.stepsnumber = :stepsnumber")})
public class Appuser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "USERID")
    private Long userid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "SURNAME")
    private String surname;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "EMAIL")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATEOFBIRTH")
    @Temporal(TemporalType.DATE)
    private Date dateofbirth;
    @Column(name = "HEIGHT")
    private Integer height;
    @Column(name = "WEIGHT")
    private Integer weight;
    @Size(max = 1)
    @Column(name = "GENDER")
    private String gender;
    @Size(max = 50)
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "POSTCODE")
    private Short postcode;
    @Column(name = "ACTIVITYLEVEL")
    private Short activitylevel;
    @Column(name = "STEPSNUMBER")
    private Integer stepsnumber;
    @OneToMany(mappedBy = "userid")
    private Collection<Report> reportCollection;
    @OneToMany(mappedBy = "userid")
    private Collection<Usercredential> usercredentialCollection;
    @OneToMany(mappedBy = "userid")
    private Collection<Consumption> consumptionCollection;

    public Appuser() {
    }

    public Appuser(Long userid) {
        this.userid = userid;
    }

    public Appuser(Long userid, String firstname, String surname, String email, Date dateofbirth) {
        this.userid = userid;
        this.firstname = firstname;
        this.surname = surname;
        this.email = email;
        this.dateofbirth = dateofbirth;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(Date dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Short getPostcode() {
        return postcode;
    }

    public void setPostcode(Short postcode) {
        this.postcode = postcode;
    }

    public Short getActivitylevel() {
        return activitylevel;
    }

    public void setActivitylevel(Short activitylevel) {
        this.activitylevel = activitylevel;
    }

    public Integer getStepsnumber() {
        return stepsnumber;
    }

    public void setStepsnumber(Integer stepsnumber) {
        this.stepsnumber = stepsnumber;
    }

    @XmlTransient
    public Collection<Report> getReportCollection() {
        return reportCollection;
    }

    public void setReportCollection(Collection<Report> reportCollection) {
        this.reportCollection = reportCollection;
    }

    @XmlTransient
    public Collection<Usercredential> getUsercredentialCollection() {
        return usercredentialCollection;
    }

    public void setUsercredentialCollection(Collection<Usercredential> usercredentialCollection) {
        this.usercredentialCollection = usercredentialCollection;
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
        hash += (userid != null ? userid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Appuser)) {
            return false;
        }
        Appuser other = (Appuser) object;
        if ((this.userid == null && other.userid != null) || (this.userid != null && !this.userid.equals(other.userid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fit5046.entity.Appuser[ userid=" + userid + " ]";
    }
    
}
