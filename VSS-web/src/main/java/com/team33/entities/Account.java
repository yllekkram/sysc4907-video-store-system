package com.team33.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * This class represents account entities persisted in the database
 *
 * @author Samual
 */
@Entity
@Table(name = "account")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a"),
    @NamedQuery(name = "Account.findById", query = "SELECT a FROM Account a WHERE a.id = :id"),
    @NamedQuery(name = "Account.findByName", query = "SELECT a FROM Account a WHERE a.name = :name"),
    @NamedQuery(name = "Account.findByPassword", query = "SELECT a FROM Account a WHERE a.password = :password"),
    @NamedQuery(name = "Account.findByActivated", query = "SELECT a FROM Account a WHERE a.activated = :activated")})
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "activated")
    private Boolean activated;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private Collection<Orders> ordersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private Collection<LoginToken> logintokenCollection;

    /**
     * Constructs an account entity
     */
    public Account() {
    }

    /**
     * Constructs an account entity
     *
     * @param id
     */
    public Account(Integer id) {
        this.id = id;
    }

    /**
     * Constructs an account entity
     *
     * @param id
     * @param name
     */
    public Account(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Retrieves account id
     *
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the account id to provided number
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retrieves the username of an account
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of an account
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves account password
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password to the provided password
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Retrieves the account activation status
     *
     * @return
     */
    public Boolean getActivated() {
        return activated;
    }

    /**
     * Sets the account activation status
     *
     * @param activated
     */
    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    /**
     * Retrieves the orders attached to an account
     *
     * @return
     */
    @XmlTransient
    public Collection<Orders> getOrdersCollection() {
        return ordersCollection;
    }

    /**
     * Sets the orders attached to the account
     *
     * @param ordersCollection
     */
    public void setOrdersCollection(Collection<Orders> ordersCollection) {
        this.ordersCollection = ordersCollection;
    }

    /**
     * Retrieves the login token collection
     *
     * @return
     */
    @XmlTransient
    public Collection<LoginToken> getLogintokenCollection() {
        return logintokenCollection;
    }

    /**
     * Sets the login token collection
     *
     * @param logintokenCollection
     */
    public void setLogintokenCollection(Collection<LoginToken> logintokenCollection) {
        this.logintokenCollection = logintokenCollection;
    }

    /**
     * hashes the id
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Returns true if the accounts are equal
     *
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * Returns the account entity as a string
     *
     * @return
     */
    @Override
    public String toString() {
        return "javaapplication5.Account[ id=" + id + " ]";
    }
}
