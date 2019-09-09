package com.fit5046.RESTfulWSEntities;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

public class Appuser implements Serializable {

    private static final long serialVersionUID = 1L;

    private long userid;
    private String firstname;
    private String surname;
    private String email;
    private Date dateofbirth;
    private Integer height;
    private Integer weight;
    private String gender;
    private String address;
    private Integer postcode;
    private Integer activitylevel;
    private Integer stepsnumber;

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
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

    public Integer getPostcode() {
        return postcode;
    }

    public void setPostcode(Integer postcode) {
        this.postcode = postcode;
    }

    public Integer getActivitylevel() {
        return activitylevel;
    }

    public void setActivitylevel(Integer activitylevel) {
        this.activitylevel = activitylevel;
    }

    public Integer getStepsnumber() {
        return stepsnumber;
    }

    public void setStepsnumber(Integer stepsnumber) {
        this.stepsnumber = stepsnumber;
    }
}
