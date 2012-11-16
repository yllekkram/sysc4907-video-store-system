/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author LaFamiglia
 */
@Entity
@Table(name = "account")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a"),
    @NamedQuery(name = "Account.findById", query = "SELECT a FROM Account a WHERE a.accountPK.id = :id"),
    @NamedQuery(name = "Account.findByName", query = "SELECT a FROM Account a WHERE a.name = :name"),
    @NamedQuery(name = "Account.findByPassword", query = "SELECT a FROM Account a WHERE a.password = :password"),
    @NamedQuery(name = "Account.findBySalt", query = "SELECT a FROM Account a WHERE a.salt = :salt"),
    @NamedQuery(name = "Account.findByStatusid", query = "SELECT a FROM Account a WHERE a.accountPK.statusid = :statusid")})
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AccountPK accountPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "name")
    private String name;
    @Size(max = 120)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Column(name = "salt")
    private int salt;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private Collection<Order1> order1Collection;
    @JoinColumn(name = "Status_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Status status;

    public Account() {
    }

    public Account(AccountPK accountPK) {
        this.accountPK = accountPK;
    }

    public Account(AccountPK accountPK, String name, int salt) {
        this.accountPK = accountPK;
        this.name = name;
        this.salt = salt;
    }

    public Account(int id, int statusid) {
        this.accountPK = new AccountPK(id, statusid);
    }

    public AccountPK getAccountPK() {
        return accountPK;
    }

    public void setAccountPK(AccountPK accountPK) {
        this.accountPK = accountPK;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSalt() {
        return salt;
    }

    public void setSalt(int salt) {
        this.salt = salt;
    }

    @XmlTransient
    public Collection<Order1> getOrder1Collection() {
        return order1Collection;
    }

    public void setOrder1Collection(Collection<Order1> order1Collection) {
        this.order1Collection = order1Collection;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accountPK != null ? accountPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.accountPK == null && other.accountPK != null) || (this.accountPK != null && !this.accountPK.equals(other.accountPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.team33.entities.Account[ accountPK=" + accountPK + " ]";
    }
    
}
