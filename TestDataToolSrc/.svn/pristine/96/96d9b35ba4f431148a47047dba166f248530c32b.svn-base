/*
 * Copyright (C) 2012 TopCoder Inc., All Rights Reserved.
 */
package com.cronos.onlinereview.test.data.informixoltp;

/**
 * <p>An enumeration over the payment types. Corresponds to <code>informixoltp.payment_status_lu</code> database
 * table.</p>
 * 
 * @author isv
 * @version 1.0
 */
public enum PaymentStatus {

    PAID(53, "Paid"),
    
    ON_HOLD(55, "On Hold"),
    
    OWED(56, "Owed");
    
    /**
     * <p>An <code>int</code> providing the ID of this payment status.</p>
     */
    private int paymentStatusId;

    /**
     * <p>A <code>String</code> providing the name of this payment status.</p>
     */
    private String name;

    /**
     * <p>Constructs new <code>PaymentStatus</code> instance.</p>
     * 
     * @param paymentStatusId an <code>int</code> providing the ID of this payment status.
     * @param name a <code>String</code> providing the name of this payment status.
     */
    private PaymentStatus(int paymentStatusId, String name) {
        this.paymentStatusId = paymentStatusId;
        this.name = name;
    }

    /**
     * <p>Gets the name of this payment status.</p>
     *
     * @return a <code>String</code> providing the name of this payment status.
     */
    public String getName() {
        return this.name;
    }

    /**
     * <p>Gets the ID of this payment status.</p>
     *
     * @return a <code>int</code> providing the ID of this payment status.
     */
    public int getPaymentStatusId() {
        return this.paymentStatusId;
    }

}
