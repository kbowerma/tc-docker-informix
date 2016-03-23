/*
 * Copyright (C) 2012 TopCoder Inc., All Rights Reserved.
 */
package com.cronos.onlinereview.test.data.informixoltp;

/**
 * <p>An enumeration over the payment types. Corresponds to <code>informixoltp.payment_type_lu</code> database
 * table.</p>
 * 
 * @author isv
 * @version 1.0
 */
public enum PaymentType {
    
    COMPONENT_PAYMENT(6, "Component Payment"),
    
    ARCHITECTURE_PAYMENT(29, "Architecture Payment"),
    
    ASSEMBLY_PAYMENT(10, "Assembly Payment"),

    SPECIFICATION_PAYMENT(42, "Specification Payment"),

    CONCEPTUALIZATION_PAYMENT(43, "Conceptualization Payment"),

    TEST_SUITES_PAYMENT(44, "Test Suites Payment"),

    UI_PROTOTYPE_PAYMENT(49, "UI Prototype Competition Payment"),

    RIA_BUILD_PAYMENT(50, "RIA Build Competition Payment"),

    RIA_COMPONENT_PAYMENT(51, "RIA Component Competition Payment"),

    TEST_SCENARIOS_PAYMENT(55, "Test Scenarios Payment"),

    CONTENT_CREATION_PAYMENT(61, "Content Creation Payment"),

    CONTEST_PAYMENT(65, "Contest Payment"),

    COPILOT_POSTING_PAYMENT(60, "Copilot Posting Payment"),

    STUDIO_CONTEST_PAYMENT(13, "TopCoder Studio Contest Payment"),
    
    REVIEW_BOARD_PAYMENT(7, "Review Board Payment"),
    
    ARCHITECTURE_REVIEW_PAYMENT(26, "Architecture Review Payment"),
    
    ASSEMBLY_COMPETITION_REVIEW(28, "Assembly Competition Review"),
    
    SPECIFICATION_REVIEW_PAYMENT(27, "Specification Review Payment"),
    
    STUDIO_SPECIFICATION_REVIEW_PAYMENT(48, "Studio Specification Review Payment"),
    
    COPILOT_PAYMENT(45, "Copilot Payment"),
    
    STUDIO_COPILOT_PAYMENT(57, "Studio Copilot Payment");

    /**
     * <p>An <code>int</code> providing the ID for this payment type.</p>
     */
    private int paymentTypeId;

    /**
     * <p>A <code>String</code> providing the name for this payment type.</p>
     */
    private String name;

    /**
     * <p>Constructs new <code>PaymentType</code> instance.</p>
     * 
     * @param paymentTypeId an <code>int</code> providing the ID for this payment type.
     * @param name a <code>String</code> providing the name for this payment type.
     */
    private PaymentType(int paymentTypeId, String name) {
        this.paymentTypeId = paymentTypeId;
        this.name = name;
    }

    /**
     * <p>Gets the name for this payment type.</p>
     *
     * @return a <code>String</code> providing the name for this payment type.
     */
    public String getName() {
        return this.name;
    }

    /**
     * <p>Gets the ID for this payment type.</p>
     *
     * @return a <code>int</code> providing the ID for this payment type.
     */
    public int getPaymentTypeId() {
        return this.paymentTypeId;
    }

}
