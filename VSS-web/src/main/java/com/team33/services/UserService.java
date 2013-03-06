/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.services;

import com.team33.entities.User;
import java.util.List;

/**
 *
 * @author Mark
 */
public interface UserService {
    public void addUser(User user);
    public User getUser(Integer id);
    public List<User> listUsers();
    public void removeUser(Integer id);
    public void updateUser(User user);
}
