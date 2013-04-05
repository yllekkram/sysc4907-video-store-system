package com.team33.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;

/**
 * Represents the primary key for orders
 *
 * @author Samual
 */
@Embeddable
public class OrdersPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id")
    @GeneratedValue
    private int id;
    @Basic(optional = false)
    @Column(name = "Account_id")
    private int accountid;

    /**
     * Constructs the primary key for orders
     */
    public OrdersPK() {
    }

    /**
     * Constructs the primary key for orders
     *
     * @param id
     * @param accountid
     */
    public OrdersPK(int id, int accountid) {
        this.id = id;
        this.accountid = accountid;
    }

    /**
     * Retrieves the id for the primary key
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
     * Retreieves the account id
     *
     * @return
     */
    public int getAccountid() {
        return accountid;
    }

    /**
     * Sets account id
     *
     * @param accountid
     */
    public void setAccountid(int accountid) {
        this.accountid = accountid;
    }

    /**
     * hashes the order PK
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) accountid;
        return hash;
    }

    /**
     * Returns true if objects are equal
     *
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdersPK)) {
            return false;
        }
        OrdersPK other = (OrdersPK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.accountid != other.accountid) {
            return false;
        }
        return true;
    }

    /**
     * Represents the orders PK as a string
     *
     * @return
     */
    @Override
    public String toString() {
        return "javaapplication5.OrdersPK[ id=" + id + ", accountid=" + accountid + " ]";
    }
}
