/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
@Table(name = "orders")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Order1.findAll", query = "SELECT o FROM Order1 o"),
    @NamedQuery(name = "Order1.findById", query = "SELECT o FROM Order1 o WHERE o.order1PK.id = :id"),
    @NamedQuery(name = "Order1.findByActiveAccount", query = "SELECT o FROM Order1 o WHERE o.order1PK.accountActivated = :accountActivated AND o.order1PK.accountid = :accountid")})
public class Order1 implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected Order1PK order1PK;
    @JoinColumns({
        @JoinColumn(name = "Account_id", referencedColumnName = "id", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Account account;

    public Order1() {
    }

    public Order1(Order1PK order1PK) {
        this.order1PK = order1PK;
    }

    public Order1(int id, int accountid) {
        this.order1PK = new Order1PK(id, accountid);
    }

    public Order1PK getOrder1PK() {
        return order1PK;
    }

    public void setOrder1PK(Order1PK order1PK) {
        this.order1PK = order1PK;
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
        hash += (order1PK != null ? order1PK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Order1)) {
            return false;
        }
        Order1 other = (Order1) object;
        if ((this.order1PK == null && other.order1PK != null) || (this.order1PK != null && !this.order1PK.equals(other.order1PK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.team33.entities.Order1[ order1PK=" + order1PK + " ]";
    }
    
}
