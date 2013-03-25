package com.team33.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Represents a video genre in the database
 *
 * @author Samual
 */
@Entity
@Table(name = "genre")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Genre.findAll", query = "SELECT g FROM Genre g"),
    @NamedQuery(name = "Genre.findById", query = "SELECT g FROM Genre g WHERE g.id = :id"),
    @NamedQuery(name = "Genre.findByCategory", query = "SELECT g FROM Genre g WHERE g.category = :category")})
public class Genre implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "category")
    private String category;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "genreid")
    private Collection<VideoInfo> videoInfoCollection;

    /**
     * Constructs the genre entity
     */
    public Genre() {
    }

    /**
     * Constructs the genre entity
     *
     * @param id
     */
    public Genre(Integer id) {
        this.id = id;
    }

    /**
     * Constructs the genre entity
     *
     * @param id
     * @param category
     */
    public Genre(Integer id, String category) {
        this.id = id;
        this.category = category;
    }

    /**
     * Retrieves the genre id
     *
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets genre id
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Return genre category i.e. "Comedy"
     *
     * @return
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the genre category
     *
     * @param category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Gets the collection of videoinfo attached to specific genre
     *
     * @return
     */
    @XmlTransient
    public Collection<VideoInfo> getVideoInfoCollection() {
        return videoInfoCollection;
    }

    /**
     * Sets the videoinfo collection for a genre
     *
     * @param videoInfoCollection
     */
    public void setVideoInfoCollection(Collection<VideoInfo> videoInfoCollection) {
        this.videoInfoCollection = videoInfoCollection;
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
     * Returns true if object are equal
     *
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Genre)) {
            return false;
        }
        Genre other = (Genre) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * Returns the string representation of the genre
     *
     * @return
     */
    @Override
    public String toString() {
        return "javaapplication5.Genre[ id=" + id + " ]";
    }
}
