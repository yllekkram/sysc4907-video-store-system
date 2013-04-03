/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.form;

import com.team33.entities.Account;

/**
 *
 * @author Mark
 */
public class OrderRequest {

    private Integer loginToken;
    private Integer totalPrice;
    private String paymentMethod;
    private String cardholderName;
    private String creditCardNumber;
    private String creditCardVerification;

    public OrderRequest() {
        loginToken = null;
        totalPrice = 0;
        paymentMethod = "creditCard";
    }
    
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setLoginToken(Integer loginToken) {
        this.loginToken = loginToken;
    }

    public void setCardholderName(String cardholderName) {
        this.cardholderName = cardholderName;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public void setCreditCardVerification(String creditCardVerification) {
        this.creditCardVerification = creditCardVerification;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public Integer getLoginToken() {
        return loginToken;
    }

    public String getCardholderName() {
        return cardholderName;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public String getCreditCardVerification() {
        return creditCardVerification;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }
}
