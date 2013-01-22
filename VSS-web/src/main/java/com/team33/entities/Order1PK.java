/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author LaFamiglia
 */
@Embeddable
public class Order1PK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Account_id")
    private int accountid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Account_activated")
    private boolean accountActivated;

    public Order1PK() {
    }

    public Order1PK(int id, int accountid, boolean accountActivated) {
        this.id = id;
        this.accountid = accountid;
        this.accountActivated = accountActivated;
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

    public boolean getAccountActivated() {
        return accountActivated;
    }

    public void setAccountActivated(boolean accountActivated) {
        this.accountActivated = accountActivated;
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
        Order1PK other = (Order1PK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.accountid != other.accountid) {
            return false;
        }
        if (this.accountActivated != other.accountActivated) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.team33.entities.Order1PK[ id=" + id + ", accountid=" + accountid + ", accountActivated=" + accountActivated + " ]";
    }
    
}
