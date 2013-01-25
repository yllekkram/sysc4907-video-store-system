/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Caleb
 */
@Entity
public class ScreenRating implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @NotNull
    @Basic(optional = false)
    @Size(min = 1, max = 45)
    @Column(name = "id")
    private String screenRatingShort;
    
    @NotNull
    @Basic(optional = false)
    @Size(min = 1, max = 45)
    @Column(name = "id")
    private String screenRatingLong;
    
    @NotNull
    @Basic(optional = false)
    @Column(name = "id")
    private String description;

    public String getScreenRatingShort() {
        return screenRatingShort;
    }

    public String getScreenRatingLong() {
        return screenRatingLong;
    }

    public String getDescription() {
        return description;
    }
    
    public Integer getId() {
        return id;
    }

    public void setScreenRatingShort(String screenRatingShort) {
    }

    public void setScreenRatingLong(String screenRatingLong) {
    }

    public void setDescription(String description) {
    }

    public void setId(Integer id) {
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScreenRating)) {
            return false;
        }
        ScreenRating other = (ScreenRating) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.team33.entities.ScreenRating[ id=" + id + " ]";
    }
    
}