/*
 * Copyright (C) 2011 - 2014 TopCoder Inc., All Rights Reserved.
 */
package com.cronos.onlinereview.test.data.corporateoltp;

import java.util.Random;

/**
 * <p>An enumeration over the permission types. Corresponds to <code>corporate_oltp.permission_type</code> database
 * table.</p>
 *
 * <p>
 * Version 1.1 (Test Data Preparation for topcoder Permission related performance improvement)
 * <ol>
 *     <li>Added PROJECT_REPORT</li>
 *     <li>Added {@link #getRandomContestPermission()}</li>
 *     <li>Added {@link #getRandomContestPermission()}}</li>
 * </ol>
 * </p>
 * 
 * @author isv, GreatKevin
 * @version 1.1
 */
public enum PermissionType {
    
    PROJECT_REPORT(0),

    PROJECT_READ(1),
    
    PROJECT_WRITE(2),
    
    PROJECT_FULL(3),
    
    CONTEST_READ(4),
    
    CONTEST_WRITE(5),
    
    CONTEST_FULL(6);


    public static final PermissionType[] PROJECT_PERMISSIONS = new PermissionType[]{
            PROJECT_REPORT,
            PROJECT_READ,
            PROJECT_WRITE,
            PROJECT_FULL
    };

    public static final PermissionType[] CONTEST_PERMISSIONS = new PermissionType[]{
            CONTEST_READ,
            CONTEST_WRITE,
            CONTEST_FULL
    };

    private static final Random RANDOM = new Random();

    /**
     * <p>A <code>long</code> providing the ID of this client status.</p>
     */
    private long id;

    /**
     * <p>Constructs new <code>PermissionType</code> instance.</p>
     * 
     * @param id a <code>long</code> providing the ID of this permission type.
     */
    private PermissionType(long id) {
        this.id = id;
    }

    /**
     * <p>Gets the ID of this permission type.</p>
     *
     * @return a <code>long</code> providing the ID of this permission type.
     */
    public long getId() {
        return this.id;
    }

    /**
     * Gets the random project permission
     *
     * @return the PermissionType of project permissions.
     * @since 1.1
     */
    public static PermissionType getRandomProjectPermission() {
        return PROJECT_PERMISSIONS[RANDOM.nextInt(PROJECT_PERMISSIONS.length)];
    }

    /**
     * Gets the random contest permission.
     *
     * @return the PermissionType of contest permissions.
     * @since 1.1
     */
    public static PermissionType getRandomContestPermission() {
        return CONTEST_PERMISSIONS[RANDOM.nextInt(CONTEST_PERMISSIONS.length)];
    }
}
