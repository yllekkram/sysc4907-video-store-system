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
 * Represents the orders in the database
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
    private static Integer nextID = 0;
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
     * Constructs the orders
     */
    public Orders() {
    }

    /**
     * Constructs the orders
     *
     * @param ordersPK
     */
    public Orders(OrdersPK ordersPK) {
        this.ordersPK = ordersPK;
    }

    public Orders(int accountID) {
        this.pendingCharge = 0;
        this.ordersPK = new OrdersPK(Orders.nextID++, accountID);
    }
    
    /**
     * Constructs the orders
     *
     * @param id
     * @param accountid
     */
    public Orders(int id, int accountid) {
        this.ordersPK = new OrdersPK(id, accountid);
    }

    /**
     * Retrieves the orders PK
     *
     * @return
     */
    public OrdersPK getOrdersPK() {
        return ordersPK;
    }

    /**
     * Sets the orders PK
     *
     * @param ordersPK
     */
    public void setOrdersPK(OrdersPK ordersPK) {
        this.ordersPK = ordersPK;
    }

    /**
     * Retrieves the pending charge for an order
     *
     * @return
     */
    public Integer getPendingCharge() {
        return pendingCharge;
    }

    /**
     * Sets the pending charge
     *
     * @param pendingCharge
     */
    public void setPendingCharge(Integer pendingCharge) {
        this.pendingCharge = pendingCharge;
    }

    /**
     * Retrieves the collection of invoices
     *
     * @return
     */
    @XmlTransient
    public Collection<Invoice> getInvoiceCollection() {
        return invoiceCollection;
    }

    /**
     * Sets the invoice collection
     *
     * @param invoiceCollection
     */
    public void setInvoiceCollection(Collection<Invoice> invoiceCollection) {
        this.invoiceCollection = invoiceCollection;
    }

    /**
     * Gets the account
     *
     * @return
     */
    public Account getAccount() {
        return account;
    }

    /**
     * Sets the account
     *
     * @param account
     */
    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     * Gets the purchase collection
     *
     * @return
     */
    @XmlTransient
    public Collection<Purchase> getPurchaseCollection() {
        return purchaseCollection;
    }

    /**
     * Sets the purchase collection
     *
     * @param purchaseCollection
     */
    public void setPurchaseCollection(Collection<Purchase> purchaseCollection) {
        this.purchaseCollection = purchaseCollection;
    }

    /**
     * Gets the rental collection
     *
     * @return
     */
    @XmlTransient
    public Collection<Rental> getRentalCollection() {
        return rentalCollection;
    }

    /**
     * Sets the rental collection
     *
     * @param rentalCollection
     */
    public void setRentalCollection(Collection<Rental> rentalCollection) {
        this.rentalCollection = rentalCollection;
    }

    /**
     * hashes the orders
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
     * Returns true if the objects are equal
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
     * Represents the orders as a string
     *
     * @return
     */
    @Override
    public String toString() {
        return "javaapplication5.Orders[ ordersPK=" + ordersPK + " ]";
    }
}
