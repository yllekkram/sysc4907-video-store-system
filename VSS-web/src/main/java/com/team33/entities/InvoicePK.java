package com.team33.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Represents the primary key for invoice
 *
 * @author Samual
 */
@Embeddable
public class InvoicePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @Column(name = "Orders_id")
    private int ordersid;
    @Basic(optional = false)
    @Column(name = "Orders_Account_id")
    private int ordersAccountid;
    @Basic(optional = false)
    @Column(name = "orderCharge")
    private int orderCharge;

    /**
     * Constructs the primary key for invoice
     */
    public InvoicePK() {
    }

    /**
     * Constructs the primary key for invoice
     *
     * @param id
     * @param ordersid
     * @param ordersAccountid
     * @param orderCharge
     */
    public InvoicePK(int id, int ordersid, int ordersAccountid, int orderCharge) {
        this.id = id;
        this.ordersid = ordersid;
        this.ordersAccountid = ordersAccountid;
        this.orderCharge = orderCharge;
    }

    /**
     * Retrieves the id of the primary key
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
     * Retrieves the order id for invoice
     *
     * @return
     */
    public int getOrdersid() {
        return ordersid;
    }

    /**
     * Sets the order id
     *
     * @param ordersid
     */
    public void setOrdersid(int ordersid) {
        this.ordersid = ordersid;
    }

    /**
     * Retrieves orders account id
     *
     * @return
     */
    public int getOrdersAccountid() {
        return ordersAccountid;
    }

    /**
     * Sets the orders account id
     *
     * @param ordersAccountid
     */
    public void setOrdersAccountid(int ordersAccountid) {
        this.ordersAccountid = ordersAccountid;
    }

    /**
     * Retrieves the charge of the order
     *
     * @return
     */
    public int getOrderCharge() {
        return orderCharge;
    }

    /**
     * Sets the charge
     *
     * @param orderCharge
     */
    public void setOrderCharge(int orderCharge) {
        this.orderCharge = orderCharge;
    }

    /**
     * hashes the primary key
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) ordersid;
        hash += (int) ordersAccountid;
        hash += (int) orderCharge;
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
        if (!(object instanceof InvoicePK)) {
            return false;
        }
        InvoicePK other = (InvoicePK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.ordersid != other.ordersid) {
            return false;
        }
        if (this.ordersAccountid != other.ordersAccountid) {
            return false;
        }
        if (this.orderCharge != other.orderCharge) {
            return false;
        }
        return true;
    }

    /**
     * Represents the invoice PK as a string
     *
     * @return
     */
    @Override
    public String toString() {
        return "javaapplication5.InvoicePK[ id=" + id + ", ordersid=" + ordersid + ", ordersAccountid=" + ordersAccountid + ", orderCharge=" + orderCharge + " ]";
    }
}
