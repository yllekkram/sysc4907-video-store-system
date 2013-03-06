/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.mindrot.jBcrypt.BCrypt;

/**
 *
 * @author Mark
 */

@Entity
@Table(name="USERS")
public class User {
    @Id
    @Column(name="ID")
    @GeneratedValue
    private Integer id;
    
    @Column(name="NAME")
    private String name;
    
    @Column(name="EMAIL")
    private String email;
    
    @Column(name="PASSWORDHASH")
    private String passwordHash;
    
    @Column(name="ACTIVATED")
    private Boolean activated;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    @Transient
    public void setPassword(String password) {
        this.passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Transient
    public String getPassword() {
        return null;
    }
    
    public String getPasswordHash() {
        return passwordHash;
    }

    public Boolean getActivated() {
        return activated;
    }
}
