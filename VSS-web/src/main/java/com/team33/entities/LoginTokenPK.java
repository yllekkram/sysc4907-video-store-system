package com.team33.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Represents the primary key for login token
 *
 * @author Samual
 */
@Embeddable
public class LoginTokenPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "Account_id")
    private int accountid;
    @Basic(optional = false)
    @Column(name = "id")
    private int id;

    /**
     * Constructs the primary key for login token
     */
    public LoginTokenPK() {
    }

    /**
     * Constructs the primary key for login token
     *
     * @param accountid
     * @param id
     */
    public LoginTokenPK(int accountid, int id) {
        this.accountid = accountid;
        this.id = id;
    }

    /**
     * Gets the account id
     *
     * @return
     */
    public int getAccountid() {
        return accountid;
    }

    /**
     * Sets the id
     *
     * @param accountid
     */
    public void setAccountid(int accountid) {
        this.accountid = accountid;
    }

    /**
     * Gets the id for the primary key
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * hashes the login token PK
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) accountid;
        hash += (int) id;
        return hash;
    }

    /**
     * Returns true if the objects are equal
     *
     * @param object
     * @return
     */
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

    /**
     * Represents the login token PK as a string
     *
     * @return
     */
    @Override
    public String toString() {
        return "javaapplication5.LogintokenPK[ accountid=" + accountid + ", id=" + id + " ]";
    }
}
