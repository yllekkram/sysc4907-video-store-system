/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author LaFamiglia
 */
@Entity
@Table(name = "invoice")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Invoice.findAll", query = "SELECT i FROM Invoice i"),
    @NamedQuery(name = "Invoice.findById", query = "SELECT i FROM Invoice i WHERE i.invoicePK.id = :id"),
    @NamedQuery(name = "Invoice.findByOrdersid", query = "SELECT i FROM Invoice i WHERE i.invoicePK.ordersid = :ordersid"),
    @NamedQuery(name = "Invoice.findByOrdersAccountid", query = "SELECT i FROM Invoice i WHERE i.invoicePK.ordersAccountid = :ordersAccountid"),
    @NamedQuery(name = "Invoice.findByDate", query = "SELECT i FROM Invoice i WHERE i.date = :date"),
    @NamedQuery(name = "Invoice.findByOrderCharge", query = "SELECT i FROM Invoice i WHERE i.invoicePK.orderCharge = :orderCharge")})
public class Invoice implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected InvoicePK invoicePK;
    @Basic(optional = false)
    @Column(name = "Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @JoinColumns({
        @JoinColumn(name = "Orders_id", referencedColumnName = "id", insertable = false, updatable = false),
        @JoinColumn(name = "Orders_Account_id", referencedColumnName = "Account_id", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Orders orders;

    public Invoice() {
    }

    public Invoice(InvoicePK invoicePK) {
        this.invoicePK = invoicePK;
    }

    public Invoice(InvoicePK invoicePK, Date date) {
        this.invoicePK = invoicePK;
        this.date = date;
    }

    public Invoice(int id, int ordersid, int ordersAccountid, int orderCharge) {
        this.invoicePK = new InvoicePK(id, ordersid, ordersAccountid, orderCharge);
    }

    public InvoicePK getInvoicePK() {
        return invoicePK;
    }

    public void setInvoicePK(InvoicePK invoicePK) {
        this.invoicePK = invoicePK;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (invoicePK != null ? invoicePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Invoice)) {
            return false;
        }
        Invoice other = (Invoice) object;
        if ((this.invoicePK == null && other.invoicePK != null) || (this.invoicePK != null && !this.invoicePK.equals(other.invoicePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication5.Invoice[ invoicePK=" + invoicePK + " ]";
    }
    
}
