/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package com.cronos.onlinereview.test.data.tcscatalog;

import java.util.Random;

/**
 * <p>
 * This enum represents the types of group permissions.
 * </p>
 *
 * @author TCSASSEMBLER
 */
public enum GroupPermissionType {
    /**
     * <p>
     * Represents the group permission to report.
     * </p>
     */
    REPORT,
    /**
     * <p>
     * Represents the group permission to read.
     * </p>
     */
    READ,

    /**
     * <p>
     * Represents the group permission to write.
     * </p>
     */
    WRITE,

    /**
     * <p>
     * Represents the group permission to read and write.
     * </p>
     */
    FULL;

    /**
     * All the group permission types.
     */
    private static final GroupPermissionType[] ALL_PERMISSION_TYPES = GroupPermissionType.values();

    /**
     * The random.
     */
    private static final Random RANDOM = new Random();

    /**
     * Generates the random GroupPermissionType
     *
     * @return the GroupPermissionType.
     */
    public static GroupPermissionType getRandomPermissionType() {
        return ALL_PERMISSION_TYPES[RANDOM.nextInt(ALL_PERMISSION_TYPES.length)];
    }
}
