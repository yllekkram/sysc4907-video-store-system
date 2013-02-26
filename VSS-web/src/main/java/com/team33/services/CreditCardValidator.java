package com.team33.services;

import com.team33.services.exception.*;

/**
 * Stub credit card validation class simulates Validation of credit information
 * for order processing
 *
 * @author Samual
 */
public class CreditCardValidator {

    /**
     * max line of credit for visa
     */
    public static final int VISA_LOC = 500;
    /**
     * max transaction charge for visa per transaction
     */
    public static final int VISA_TMAX = 250;
    /**
     * Assumes all visas have these authentication digits
     */
    public static final int VISA_ID = 459;
    /**
     * max line of credit for amex
     */
    public static final int AMEX_LOC = 400;
    /**
     * max transaction charge for amex per transaction
     */
    public static final int AMEX_TMAX = 200;
    /**
     * Assumes all amex has these authentication digits
     */
    public static final int AMEX_ID = 236;
    /**
     * max line of credit for paypal
     */
    public static final int PAYPAL_LOC = 600;
    /**
     * max transaction charge for paypal per transaction
     */
    public static final int PAYPAL_TMAX = 300;
    /**
     * Assumes all paypal accounts have these authentication digits
     */
    public static final int PAYPAL_ID = 198;

    /**
     * Checks if the validationNum given for a credit card is an expected value
     *
     * @param validationNum
     * @return boolean
     * @throws PaymentException
     */
    public boolean isCardValid(int validationNum) throws PaymentException {
        if (validationNum == VISA_ID || validationNum == AMEX_ID || validationNum == PAYPAL_ID) {
            return true;
        }
        throw new PaymentException("Transaction cannot be processed: Card is not valid!");
    }

    /**
     * Checks if the charge is valid
     *
     * @param cost
     * @return boolean
     * @throws InsufficientFundsException
     */
    public boolean isChargeValid(int cost) throws InsufficientFundsException {
        if (cost <= VISA_TMAX || cost <= AMEX_TMAX || cost == PAYPAL_TMAX) {
            return true;
        }
        throw new InsufficientFundsException("Transaction cannot be processed:Transaction charge too high!");
    }

    /**
     * Checks if the card can be charged
     *
     * @param totalCharges
     * @param cost
     * @return boolean
     * @throws InsufficientFundsException
     */
    public boolean isUnderLOC(int totalCharges, int cost) throws InsufficientFundsException {
        if (cost + totalCharges <= VISA_LOC || cost + totalCharges <= AMEX_LOC || cost + totalCharges == PAYPAL_LOC) {
            return true;
        }
        throw new InsufficientFundsException("Transaction cannot be processed:Over line of credit!");
    }

    /**
     * Simulates a charge to a card returns a charge success msg
     *
     * @return string
     */
    public String charge() {
        return "Transaction has been processed!:Chargers were apllied to your account";
    }
}
