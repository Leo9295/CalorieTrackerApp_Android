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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Leo
 */
@Entity
@Table(name = "USERCREDENTIAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usercredential.findAll", query = "SELECT u FROM Usercredential u")
    , @NamedQuery(name = "Usercredential.findByCredentialid", query = "SELECT u FROM Usercredential u WHERE u.credentialid = :credentialid")
    , @NamedQuery(name = "Usercredential.findByUsername", query = "SELECT u FROM Usercredential u WHERE u.username = :username")
    , @NamedQuery(name = "Usercredential.findByPasswordhash", query = "SELECT u FROM Usercredential u WHERE u.passwordhash = :passwordhash")
    , @NamedQuery(name = "Usercredential.findByDateofsignup", query = "SELECT u FROM Usercredential u WHERE u.dateofsignup = :dateofsignup")})
public class Usercredential implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREDENTIALID")
    private Long credentialid;
    @Size(max = 20)
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "PASSWORDHASH")
    private String passwordhash;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATEOFSIGNUP")
    @Temporal(TemporalType.DATE)
    private Date dateofsignup;
    @JoinColumn(name = "USERID", referencedColumnName = "USERID")
    @ManyToOne
    private Appuser userid;

    public Usercredential() {
    }

    public Usercredential(Long credentialid) {
        this.credentialid = credentialid;
    }

    public Usercredential(Long credentialid, String passwordhash, Date dateofsignup) {
        this.credentialid = credentialid;
        this.passwordhash = passwordhash;
        this.dateofsignup = dateofsignup;
    }

    public Long getCredentialid() {
        return credentialid;
    }

    public void setCredentialid(Long credentialid) {
        this.credentialid = credentialid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordhash() {
        return passwordhash;
    }

    public void setPasswordhash(String passwordhash) {
        this.passwordhash = passwordhash;
    }

    public Date getDateofsignup() {
        return dateofsignup;
    }

    public void setDateofsignup(Date dateofsignup) {
        this.dateofsignup = dateofsignup;
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
        hash += (credentialid != null ? credentialid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usercredential)) {
            return false;
        }
        Usercredential other = (Usercredential) object;
        if ((this.credentialid == null && other.credentialid != null) || (this.credentialid != null && !this.credentialid.equals(other.credentialid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fit5046.entity.Usercredential[ credentialid=" + credentialid + " ]";
    }
    
}
