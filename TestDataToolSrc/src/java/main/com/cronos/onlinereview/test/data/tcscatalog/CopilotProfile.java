/*
 * Copyright (C) 2012 TopCoder Inc., All Rights Reserved.
 */
package com.cronos.onlinereview.test.data.tcscatalog;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>A DTO for single copilot profile to be generated. Corresponds to <code>tcs_catalog.copilot_profile</code> database 
 * table.</p>
 * 
 * @author isv
 * @version 1.0
 */
public class CopilotProfile implements Serializable {

    /**
     * <p>A <code>CopilotProfileStatus</code> providing the status of this profile.</p>
     */
    private CopilotProfileStatus status;

    /**
     * <p>A <code>long</code> providing the ID of a user associated with this profile.</p>
     */
    private long userId;

    /**
     * <p>A <code>long</code> providing the ID of this profile.</p>
     */
    private long copilotProfileId;

    /**
     * <p>A <code>int</code> providing the suspension count for profile.</p>
     */
    private int suspensionCount;

    /**
     * <p>A <code>double</code> providing the reliability for this profile.</p>
     */
    private double reliability;

    /**
     * <p>A <code>Date</code> providing the date of activation of this profile.</p>
     */
    private Date activationTime;

    /**
     * <p>A <code>boolean</code> providing the flag on showing earning for this profile.</p>
     */
    private boolean showEarnings;

    /**
     * <p>A <code>List<CopilotProject></code> providing the porjects for this copilot.</p>
     */
    private List<CopilotProject> projects;

    /**
     * <p>Constructs new <code>CopilotProfile</code> instance. This implementation does nothing.</p>
     */
    public CopilotProfile() {
    }

    /**
     * <p>Gets the flag on showing earning for this profile.</p>
     *
     * @return a <code>boolean</code> providing the flag on showing earning for this profile.
     */
    public boolean getShowEarnings() {
        return this.showEarnings;
    }

    /**
     * <p>Sets the flag on showing earning for this profile.</p>
     *
     * @param showEarnings a <code>boolean</code> providing the flag on showing earning for this profile.
     */
    public void setShowEarnings(boolean showEarnings) {
        this.showEarnings = showEarnings;
    }

    /**
     * <p>Gets the date of activation of this profile.</p>
     *
     * @return a <code>Date</code> providing the date of activation of this profile.
     */
    public Date getActivationTime() {
        return this.activationTime;
    }

    /**
     * <p>Sets the date of activation of this profile.</p>
     *
     * @param activationTime a <code>Date</code> providing the date of activation of this profile.
     */
    public void setActivationTime(Date activationTime) {
        this.activationTime = activationTime;
    }

    /**
     * <p>Gets the reliability for this profile.</p>
     *
     * @return a <code>double</code> providing the reliability for this profile.
     */
    public double getReliability() {
        return this.reliability;
    }

    /**
     * <p>Sets the reliability for this profile.</p>
     *
     * @param reliability a <code>double</code> providing the reliability for this profile.
     */
    public void setReliability(double reliability) {
        this.reliability = reliability;
    }

    /**
     * <p>Gets the suspension count for profile.</p>
     *
     * @return a <code>int</code> providing the suspension count for profile.
     */
    public int getSuspensionCount() {
        return this.suspensionCount;
    }

    /**
     * <p>Sets the suspension count for profile.</p>
     *
     * @param suspensionCount a <code>int</code> providing the suspension count for profile.
     */
    public void setSuspensionCount(int suspensionCount) {
        this.suspensionCount = suspensionCount;
    }

    /**
     * <p>Gets the ID of this profile.</p>
     *
     * @return a <code>long</code> providing the ID of this profile.
     */
    public long getCopilotProfileId() {
        return this.copilotProfileId;
    }

    /**
     * <p>Sets the ID of this profile.</p>
     *
     * @param copilotProfileId a <code>long</code> providing the ID of this profile.
     */
    public void setCopilotProfileId(long copilotProfileId) {
        this.copilotProfileId = copilotProfileId;
    }

    /**
     * <p>Gets the ID of a user associated with this profile.</p>
     *
     * @return a <code>long</code> providing the ID of a user associated with this profile.
     */
    public long getUserId() {
        return this.userId;
    }

    /**
     * <p>Sets the ID of a user associated with this profile.</p>
     *
     * @param userId a <code>long</code> providing the ID of a user associated with this profile.
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * <p>Gets the status of this profile.</p>
     *
     * @return a <code>CopilotProfileStatus</code> providing the status of this profile.
     */
    public CopilotProfileStatus getStatus() {
        return this.status;
    }

    /**
     * <p>Sets the status of this profile.</p>
     *
     * @param status a <code>CopilotProfileStatus</code> providing the status of this profile.
     */
    public void setStatus(CopilotProfileStatus status) {
        this.status = status;
    }

    /**
     * <p>Gets the porjects for this copilot.</p>
     *
     * @return a <code>List<CopilotProject></code> providing the porjects for this copilot.
     */
    public List<CopilotProject> getProjects() {
        return this.projects;
    }

    /**
     * <p>Sets the porjects for this copilot.</p>
     *
     * @param projects a <code>List<CopilotProject></code> providing the porjects for this copilot.
     */
    public void setProjects(List<CopilotProject> projects) {
        this.projects = projects;
    }
}
