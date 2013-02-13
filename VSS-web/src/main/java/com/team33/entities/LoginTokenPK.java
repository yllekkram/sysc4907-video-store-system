/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author LaFamiglia
 */
@Embeddable
public class LoginTokenPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "Account_id")
    private int accountid;
    @Basic(optional = false)
    @Column(name = "id")
    private int id;

    public LoginTokenPK() {
    }

    public LoginTokenPK(int accountid, int id) {
        this.accountid = accountid;
        this.id = id;
    }

    public int getAccountid() {
        return accountid;
    }

    public void setAccountid(int accountid) {
        this.accountid = accountid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) accountid;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LoginTokenPK)) {
            return false;
        }
        LoginTokenPK other = (LoginTokenPK) object;
        if (this.accountid != other.accountid) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication5.LogintokenPK[ accountid=" + accountid + ", id=" + id + " ]";
    }
    
}
