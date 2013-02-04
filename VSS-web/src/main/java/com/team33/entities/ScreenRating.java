/*
 *
 */
package com.team33.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Caleb,Samual
 */
@Entity
@Table(name = "ScreenRating")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScreenRating.findAll", query = "SELECT r FROM ScreenRating r"),
    @NamedQuery(name = "ScreenRating.findByRating", query = "SELECT r FROM ScreenRating r where r.ratingType = :ratingType")
})
public class ScreenRating implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @NotNull
    @Basic(optional = false)
    @Size(min = 1, max = 45)
    @Column(name = "ratingType")
    private String ratingType;

    public int getId() {
        return id;
    }

    public String getRatingType() {
        return ratingType;
    }

    public void setRatingType(String ratingType) {
        this.ratingType = ratingType;
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
        if (!(object instanceof ScreenRating)) {
            return false;
        }
        ScreenRating other = (ScreenRating) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.team33.entities.ScreenRating[ id=" + id + "ratingType=" + ratingType + " ]";
    }
}
