/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Samual
 */
@Entity
@Table(name = "loginToken")
@XmlRootElement
public class LoginToken implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LoginTokenPK loginTokenPK;
    @JoinColumns({
        @JoinColumn(name = "Account_id", referencedColumnName = "id", insertable = false, updatable = false)})
    private Account account;

    public LoginToken() {
    }

    public LoginToken(LoginTokenPK loginTokenPK) {
        this.loginTokenPK = loginTokenPK;
    }

    public LoginToken(int id, int accountid) {
        this.loginTokenPK = new LoginTokenPK(id, accountid);
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    public LoginTokenPK getLoginTokenPK(){
        return this.loginTokenPK;
    }
    public void setLoginTokenPK(LoginTokenPK loginTokenPK){
        this.loginTokenPK = loginTokenPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (loginTokenPK != null ? loginTokenPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LoginToken)) {
            return false;
        }
        LoginToken other = (LoginToken) object;
        if ((this.loginTokenPK == null && other.loginTokenPK != null) || (this.loginTokenPK != null && !this.loginTokenPK.equals(other.loginTokenPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.team33.entities.Order1[ order1PK=" + loginTokenPK + " ]";
    }
}
