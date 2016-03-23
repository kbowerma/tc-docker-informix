/*
 * Copyright (C) 2012 TopCoder Inc., All Rights Reserved.
 */
package com.cronos.onlinereview.test.data.tcscatalog;

/**
 * <p>An enumeration over the copilot profile statuses. Corresponds to <code>tcs_catalog.copilot_profile_status</code>
 * database table.</p>
 * 
 * @author isv
 * @version 1.0
 */
public enum CopilotProfileStatus {
    
    ACTIVE(1, "Active"),
    
    TEMPORARY_SUSPENDED(3, "Temporary Suspended"),

    PERMANENTLY_SUSPENDED(3, "Permanently Suspended");

    /**
     * <p>An <code>int</code> providing the ID of this copilot profile status.</p>
     */
    private int copilotProfileStatusId;

    /**
     * <p>A <code>String</code> providing the name of this copilot profile status.</p>
     */
    private String name;

    /**
     * <p>Constructs new <code>CopilotProfileStatus</code> instance.</p>
     * 
     * @param copilotProfileStatusId an <code>int</code> providing the ID of this copilot profile status.
     * @param name a <code>String</code> providing the name of this copilot profile status.
     */
    private CopilotProfileStatus(int copilotProfileStatusId, String name) {
        this.copilotProfileStatusId = copilotProfileStatusId;
        this.name = name;
    }

    /**
     * <p>Gets the name of this copilot profile status.</p>
     *
     * @return a <code>String</code> providing the name of this copilot profile status.
     */
    public String getName() {
        return this.name;
    }

    /**
     * <p>Gets the ID of this copilot profile status.</p>
     *
     * @return a <code>int</code> providing the ID of this copilot profile status.
     */
    public int getCopilotProfileStatusId() {
        return this.copilotProfileStatusId;
    }
}
