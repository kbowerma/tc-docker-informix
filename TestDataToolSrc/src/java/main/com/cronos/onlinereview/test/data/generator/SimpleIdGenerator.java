/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package com.cronos.onlinereview.test.data.generator;

/**
 * <p>A generator for the IDs to be used when generating the test data for various projects and accounts. The generator
 * is initialized with an initial value for ID generation which is simply increased on each call to 
 * {@link #getNextId()} method.</p>
 * 
 * @author TCSDEVELOPER
 * @version 1.0 (Release Assembly - TopCoder System Test Data Generator Update 1)
 */
public class SimpleIdGenerator implements IdGenerator {

    /**
     * <p>A <code>long</code> providing the current ID.</p>
     */
    private long currentId;

    /**
     * <p>Constructs new <code>SimpleIdGenerator</code> instance with specified ID to start generation from.</p>
     *
     * @param currentId a <code>long</code> providing the current ID.
     */
    public SimpleIdGenerator(long currentId) {
        this.currentId = currentId;
    }

    /**
     * <p>Gets the next ID to be used in data generation.</p>
     *
     * @return a <code>long</code> providing the next ID.
     */
    public long getNextId() {
        return ++this.currentId;
    }

    /**
     * <p>Checks if there is next ID available.</p>
     *
     * @return <code>true</code> always.
     */
    public boolean isAvailable() {
        return true;
    }
}
