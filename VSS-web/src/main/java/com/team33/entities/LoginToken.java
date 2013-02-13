/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Samual
 */
@Entity
@Table(name = "logintoken")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LoginToken.findAll", query = "SELECT l FROM LoginToken l"),
    @NamedQuery(name = "LoginToken.findByAccountid", query = "SELECT l FROM LoginToken l WHERE l.logintokenPK.accountid = :accountid"),
    @NamedQuery(name = "LoginToken.findById", query = "SELECT l FROM LoginToken l WHERE l.logintokenPK.id = :id")})
public class LoginToken implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LoginTokenPK logintokenPK;
    @JoinColumn(name = "Account_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Account account;

    public LoginToken() {
    }

    public LoginToken(LoginTokenPK logintokenPK) {
        this.logintokenPK = logintokenPK;
    }

    public LoginToken(int accountid, int id) {
        this.logintokenPK = new LoginTokenPK(accountid, id);
    }

    public LoginTokenPK getLogintokenPK() {
        return logintokenPK;
    }

    public void setLogintokenPK(LoginTokenPK logintokenPK) {
        this.logintokenPK = logintokenPK;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (logintokenPK != null ? logintokenPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LoginToken)) {
            return false;
        }
        LoginToken other = (LoginToken) object;
        if ((this.logintokenPK == null && other.logintokenPK != null) || (this.logintokenPK != null && !this.logintokenPK.equals(other.logintokenPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication5.Logintoken[ logintokenPK=" + logintokenPK + " ]";
    }
    
}
