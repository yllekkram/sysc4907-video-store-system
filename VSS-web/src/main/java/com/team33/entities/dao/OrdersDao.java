package com.team33.entities.dao;

import java.util.List;

import com.team33.entities.Orders;
import com.team33.entities.Purchase;
import com.team33.entities.Rental;
import com.team33.entities.LoginToken;
import org.springframework.dao.DataAccessException;

public interface OrdersDao {

    /*
     * Return all orders associated with an account
     */
    public List<Orders> getOrders(LoginToken loginToken) throws DataAccessException;

    public Orders getOrder(Integer orderId) throws DataAccessException;

    public LoginToken getLoginToken(int uuid) throws DataAccessException;

    public void saveOrder(Orders order) throws DataAccessException;

    public void saveRental(Orders order, Rental rental) throws DataAccessException;

    public void savePurchase(Orders order, Purchase purchase) throws DataAccessException;

    /*
     * An order is attached to a particular account at creation so remove order
     * only needs the orderId to remove an order from an account
     */
    public void removeOrder(Integer orderId);

    public void removeRental(Orders order, Rental rental) throws DataAccessException;

    public void removePurchase(Orders order, Purchase purchase) throws DataAccessException;
}
