/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Samual
 */
@Entity
public class LoginTokenPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid-hex")
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Account_id")
    private int accountid;

    public LoginTokenPK() {
    }

    public LoginTokenPK(int id, int accountid) {
        this.id = id;
        this.accountid = accountid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountid() {
        return accountid;
    }

    public void setAccountid(int accountid) {
        this.accountid = accountid;
    }

  

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) accountid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Order1PK)) {
            return false;
        }
        LoginTokenPK other = (LoginTokenPK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.accountid != other.accountid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.team33.entities.Order1PK[ id=" + id + ", accountid=" + accountid + " ]";
    }
}
