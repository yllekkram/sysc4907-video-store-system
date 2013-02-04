/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Samual
 */
@Entity
@Table(name = "Invoice")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Invoice.findAll", query = "SELECT i FROM Invoice i")
})
public class Invoice implements Serializable {

    @EmbeddedId
    protected InvoicePK invoicePK;

    public Invoice() {
    }

    public Invoice(int accountId, int orderId, int orderCharge) {
        this.invoicePK = new InvoicePK(id, accountId, orderId, orderCharge);
    }

    public Invoice(int id, int accountId, int orderId, int orderCharge) {
        this.invoicePK = new InvoicePK(id, accountId, orderId, orderCharge);
    }
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    private Date date;

    @JoinColumns({
        @JoinColumn(name = "Orders_id", referencedColumnName = "id", insertable = false, updatable = false),
        @JoinColumn(name = "Orders_Account_id", referencedColumnName = "Account_id", insertable = false, updatable = false)
    })
    public int getId() {
        return id;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
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
        return "com.team33.entities.Invoice[ invoicePK=" + invoicePK + " ]";
    }
}
