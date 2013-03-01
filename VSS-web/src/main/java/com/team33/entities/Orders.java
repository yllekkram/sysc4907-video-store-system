/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Samual
 */
@Entity
@Table(name = "orders")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orders.findAll", query = "SELECT o FROM Orders o"),
    @NamedQuery(name = "Orders.findById", query = "SELECT o FROM Orders o WHERE o.ordersPK.id = :id"),
    @NamedQuery(name = "Orders.findByAccountid", query = "SELECT o FROM Orders o WHERE o.ordersPK.accountid = :accountid"),
    @NamedQuery(name = "Orders.findByPendingCharge", query = "SELECT o FROM Orders o WHERE o.pendingCharge = :pendingCharge")})
public class Orders implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @EmbeddedId
    protected OrdersPK ordersPK;
    @Column(name = "pendingCharge")
    private Integer pendingCharge;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orders")
    private Collection<Invoice> invoiceCollection;
    @JoinColumn(name = "Account_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Account account;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orders")
    private Collection<Purchase> purchaseCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orders")
    private Collection<Rental> rentalCollection;

    /**
     *
     */
    public Orders() {
    }

    /**
     *
     * @param ordersPK
     */
    public Orders(OrdersPK ordersPK) {
        this.ordersPK = ordersPK;
    }

    /**
     *
     * @param id
     * @param accountid
     */
    public Orders(int id, int accountid) {
        this.ordersPK = new OrdersPK(id, accountid);
    }

    /**
     *
     * @return
     */
    public OrdersPK getOrdersPK() {
        return ordersPK;
    }

    /**
     *
     * @param ordersPK
     */
    public void setOrdersPK(OrdersPK ordersPK) {
        this.ordersPK = ordersPK;
    }

    /**
     *
     * @return
     */
    public Integer getPendingCharge() {
        return pendingCharge;
    }

    /**
     *
     * @param pendingCharge
     */
    public void setPendingCharge(Integer pendingCharge) {
        this.pendingCharge = pendingCharge;
    }

    /**
     *
     * @return
     */
    @XmlTransient
    public Collection<Invoice> getInvoiceCollection() {
        return invoiceCollection;
    }

    /**
     *
     * @param invoiceCollection
     */
    public void setInvoiceCollection(Collection<Invoice> invoiceCollection) {
        this.invoiceCollection = invoiceCollection;
    }

    /**
     *
     * @return
     */
    public Account getAccount() {
        return account;
    }

    /**
     *
     * @param account
     */
    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     *
     * @return
     */
    @XmlTransient
    public Collection<Purchase> getPurchaseCollection() {
        return purchaseCollection;
    }

    /**
     *
     * @param purchaseCollection
     */
    public void setPurchaseCollection(Collection<Purchase> purchaseCollection) {
        this.purchaseCollection = purchaseCollection;
    }

    /**
     *
     * @return
     */
    @XmlTransient
    public Collection<Rental> getRentalCollection() {
        return rentalCollection;
    }

    /**
     *
     * @param rentalCollection
     */
    public void setRentalCollection(Collection<Rental> rentalCollection) {
        this.rentalCollection = rentalCollection;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ordersPK != null ? ordersPK.hashCode() : 0);
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
        if (!(object instanceof Orders)) {
            return false;
        }
        Orders other = (Orders) object;
        if ((this.ordersPK == null && other.ordersPK != null) || (this.ordersPK != null && !this.ordersPK.equals(other.ordersPK))) {
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
        return "javaapplication5.Orders[ ordersPK=" + ordersPK + " ]";
    }
    
}
