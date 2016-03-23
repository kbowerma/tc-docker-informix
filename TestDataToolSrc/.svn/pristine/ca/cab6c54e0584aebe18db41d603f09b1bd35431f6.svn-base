/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package com.cronos.onlinereview.test.data.generator;

/**
 * <p>A generator for the IDs to be used when generating the test data for various projects and accounts.</p>
 *
 * <p>
 * Version 1.1 (Release Assembly - TopCoder System Test Data Generator Update 1 Assembly 1.0) Change notes:
 *   <ol>
 *     <li>Turned this class into interface with implementation logic moved into {@link SimpleIdGenerator} class.</li>
 *   </ol>
 * </p>
 * 
 * @author isv
 * @version 1.1
 */
public interface IdGenerator {

    /**
     * <p>Gets the next ID to be used in data generation.</p>
     * 
     * @return a <code>long</code> providing the next ID.
     */
    public long getNextId();

    /**
     * <p>Checks if there is next ID available.</p>
     * 
     * @return <code>true</code> if there is next ID available; <code>false</code> otherwise.
     * @since 1.1
     */
    public boolean isAvailable();
}
