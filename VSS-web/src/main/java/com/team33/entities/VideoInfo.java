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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Caleb,Samual
 */
@Entity
@Table(name = "Video_Info")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VideoInfo.findAll", query = "SELECT v FROM VideoInfo v"),
    @NamedQuery(name = "VideoInfo.findByGenre", query = "SELECT v FROM VideoInfo v where v.genre = :genre"),
    @NamedQuery(name = "VideoInfo.findByTitle", query = "SELECT v FROM VideoInfo v where v.title = :title"),
    @NamedQuery(name = "VideoInfo.findByScreenRating", query = "SELECT v FROM VideoInfo v where v.Screen_Rating = :ScreenRating")
})
public class VideoInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "id")
    @Basic(optional = false)
    private int id;
    @NotNull
    @Column(name = "purchasePrice")
    @Basic(optional = false)
    private int purchasePrice;
    @NotNull
    @Column(name = "rentalPrice")
    @Basic(optional = false)
    private int rentalPrice;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "runningTime")
    private int runningTime;
    @ManyToOne
    @JoinColumn(name = "id")
    private int ScreenRating_id;
    @ManyToOne
    @JoinColumn(name = "id")
    private int Genre_id;

    public VideoInfo() {
    }

    public VideoInfo(int id) {
        this.id = id;
    }

    public VideoInfo(int id, String title) {
        this(id);
        this.title = title;
    }

    public VideoInfo(int id, String title, String description) {
        this(id, title);
        this.description = description;
    }

    public VideoInfo(int id, String title, String description, int runningTime) {
        this(id, title, description);
        this.runningTime = runningTime;
    }

    public VideoInfo(int id, String title, String description, int runningTime, int rentalPrice, int purchasePrice) {
        this(id, title, description, runningTime);
        this.purchasePrice = purchasePrice;
        this.rentalPrice = rentalPrice;
    }

    public int getScreenRating_id() {
        return this.ScreenRating_id;
    }

    public int getGenre_id() {
        return this.Genre_id;
    }

    public String getDescription() {
        return description;
    }

    public int getPurchasePrice() {
        return this.purchasePrice;
    }

    public int getRentalPrice() {
        return this.rentalPrice;
    }

    public String getTitle() {
        return title;
    }

    public int getRunningTime() {
        return runningTime;
    }

    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setScreenRating_id(int ScreenRating_id) {
        this.ScreenRating_id = ScreenRating_id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }

    public void setPurchasePrice(int price) {
        this.purchasePrice = price;
    }

    public void setRentalPrice(int price) {
        this.rentalPrice = price;
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
        if (!(object instanceof VideoInfo)) {
            return false;
        }
        VideoInfo other = (VideoInfo) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.team33.entities.VideoInfo[ id=" + id + "title:" + title + "]";
    }
}
