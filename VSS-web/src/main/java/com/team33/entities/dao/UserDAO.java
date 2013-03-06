/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.entities.dao;

import com.team33.entities.User;
import java.util.List;

/**
 *
 * @author Mark
 */
public interface UserDAO {
    public void addUser(User user);
    public List<User> listUsers();
    public void removeUser(Integer id);
    public void updateUser(User user);
}
