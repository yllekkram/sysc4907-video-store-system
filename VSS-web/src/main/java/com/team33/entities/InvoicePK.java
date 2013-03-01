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
     *
     */
    public InvoicePK() {
    }

    /**
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
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public int getOrdersid() {
        return ordersid;
    }

    /**
     *
     * @param ordersid
     */
    public void setOrdersid(int ordersid) {
        this.ordersid = ordersid;
    }

    /**
     *
     * @return
     */
    public int getOrdersAccountid() {
        return ordersAccountid;
    }

    /**
     *
     * @param ordersAccountid
     */
    public void setOrdersAccountid(int ordersAccountid) {
        this.ordersAccountid = ordersAccountid;
    }

    /**
     *
     * @return
     */
    public int getOrderCharge() {
        return orderCharge;
    }

    /**
     *
     * @param orderCharge
     */
    public void setOrderCharge(int orderCharge) {
        this.orderCharge = orderCharge;
    }

    /**
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
     *
     * @return
     */
    @Override
    public String toString() {
        return "javaapplication5.InvoicePK[ id=" + id + ", ordersid=" + ordersid + ", ordersAccountid=" + ordersAccountid + ", orderCharge=" + orderCharge + " ]";
    }
    
}
