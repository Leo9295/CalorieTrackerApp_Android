package com.fit5046.RESTfulWSEntities;

import java.util.Date;

public class Usercredential {
    private long credentialid;
    private String username;
    private String passwordhash;
    private Date dateofsignup;
    private Appuser userid;

    public long getCredentialid() {
        return credentialid;
    }

    public void setCredentialid(long credentialid) {
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

    public void setDateofsignup(Date dateofsignin) {
        this.dateofsignup = dateofsignin;
    }

    public Appuser getUserid() {
        return userid;
    }

    public void setUserid(Appuser userid) {
        this.userid = userid;
    }
}
