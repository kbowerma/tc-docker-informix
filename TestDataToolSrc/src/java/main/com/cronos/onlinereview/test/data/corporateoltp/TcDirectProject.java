/*
 * Copyright (C) 2011-2012 TopCoder Inc., All Rights Reserved.
 */
package com.cronos.onlinereview.test.data.corporateoltp;

import com.cronos.onlinereview.test.data.User;
import com.cronos.onlinereview.test.data.timeoltp.BillingProject;

import java.io.Serializable;

/**
 * <p>A DTO for a single TC Direct project. Corresponds to <code>corporate_oltp.tc_direct_project</code> database table.
 * </p>
 *
 * <p>
 * Version 1.1 ( Assembly 1.0) Change notes:
 *   <ol>
 *     <li>Added {@link #completedContestsCount} property. (1.1.1.1)</li>
 *     <li>Added {@link #totalContestsCount} property. (1.1.1.1)</li>
 *     <li>Added {@link #copilots} property. (1.1.2.5)</li>
 *     <li>Added {@link #activeContestsCount} property.</li>
 *   </ol>
 * </p>
 * 
 * @author isv
 * @version 1.1
 */
public class TcDirectProject implements Serializable {

    /**
     * <p>A <code>long</code> providing the ID of this project.</p>
     */
    private long tcDirectProjectId;

    /**
     * <p>A <code>String</code> providing the name of this project.</p>
     */
    private String name;

    /**
     * <p>A <code>String</code> providing the description of this project.</p>
     */
    private String description;

    /**
     * <p>A <code>User</code> providing the user account for creator of this project.</p>
     */
    private User owner;

    /**
     * <p>A <code>TcDirectProjectStatus</code> providing the status of this project.</p>
     */
    private TcDirectProjectStatus status;

    /**
     * <p>A <code>BillingProject</code> providing the billing account which this project is associated with.</p>
     */
    private BillingProject billingAccount;

    /**
     * <p>A <code>int</code> providing the total number of contests to be generated for this project.</p>
     * 
     * @since 1.1
     */
    private int totalContestsCount;

    /**
     * <p>A <code>int</code> providing the number of completed contests to be generated for this project.</p>
     * 
     * @since 1.1
     */
    private int completedContestsCount;

    /**
     * <p>A <code>User[]</code> providing the copilots for this project.</p>
     * 
     * @since 1.1
     */
    private User[] copilots;

    /**
     * <p>A <code>int</code> providing the number of active contests to be generated for this project.</p>
     * 
     * @since 1.1
     */
    private int activeContestsCount;

    /**
     * <p>Constructs new <code>TcDirectProject</code> instance. This implementation does nothing.</p>
     */
    public TcDirectProject() {
    }

    /**
     * <p>Gets the status of this project.</p>
     *
     * @return a <code>TcDirectProjectStatus</code> providing the status of this project.
     */
    public TcDirectProjectStatus getStatus() {
        return this.status;
    }

    /**
     * <p>Sets the status of this project.</p>
     *
     * @param status a <code>TcDirectProjectStatus</code> providing the status of this project.
     */
    public void setStatus(TcDirectProjectStatus status) {
        this.status = status;
    }

    /**
     * <p>Gets the description of this project.</p>
     *
     * @return a <code>String</code> providing the description of this project.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * <p>Sets the description of this project.</p>
     *
     * @param description a <code>String</code> providing the description of this project.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * <p>Gets the name of this project.</p>
     *
     * @return a <code>String</code> providing the name of this project.
     */
    public String getName() {
        return this.name;
    }

    /**
     * <p>Sets the name of this project.</p>
     *
     * @param name a <code>String</code> providing the name of this project.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * <p>Gets the ID of this project.</p>
     *
     * @return a <code>long</code> providing the ID of this project.
     */
    public long getTcDirectProjectId() {
        return this.tcDirectProjectId;
    }

    /**
     * <p>Sets the ID of this project.</p>
     *
     * @param tcDirectProjectId a <code>long</code> providing the ID of this project.
     */
    public void setTcDirectProjectId(long tcDirectProjectId) {
        this.tcDirectProjectId = tcDirectProjectId;
    }

    /**
     * <p>Gets the billing account which this project is associated with.</p>
     *
     * @return a <code>BillingProject</code> providing the billing account which this project is associated with.
     */
    public BillingProject getBillingAccount() {
        return this.billingAccount;
    }

    /**
     * <p>Sets the billing account which this project is associated with.</p>
     *
     * @param billingAccount a <code>BillingProject</code> providing the billing account which this project is
     *                       associated with.
     */
    public void setBillingAccount(BillingProject billingAccount) {
        this.billingAccount = billingAccount;
    }

    /**
     * <p>Gets the user account for creator of this project.</p>
     *
     * @return a <code>User</code> providing the user account for creator of this project.
     */
    public User getOwner() {
        return this.owner;
    }

    /**
     * <p>Sets the user account for creator of this project.</p>
     *
     * @param owner a <code>User</code> providing the user account for creator of this project.
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }

    /**
     * <p>Gets the number of completed contests to be generated for this project.</p>
     *
     * @return a <code>int</code> providing the number of completed contests to be generated for this project.
     * @since 1.1
     */
    public int getCompletedContestsCount() {
        return this.completedContestsCount;
    }

    /**
     * <p>Sets the number of completed contests to be generated for this project.</p>
     *
     * @param completedContestsCount a <code>int</code> providing the number of completed contests to be generated for
     *                               this project.
     * @since 1.1
     */
    public void setCompletedContestsCount(int completedContestsCount) {
        this.completedContestsCount = completedContestsCount;
    }

    /**
     * <p>Gets the total number of contests to be generated for this project.</p>
     *
     * @return a <code>int</code> providing the total number of contests to be generated for this project.
     * @since 1.1
     */
    public int getTotalContestsCount() {
        return this.totalContestsCount;
    }

    /**
     * <p>Sets the total number of contests to be generated for this project.</p>
     *
     * @param totalContestsCount a <code>int</code> providing the total number of contests to be generated for this
     *                           project.
     * @since 1.1
     */
    public void setTotalContestsCount(int totalContestsCount) {
        this.totalContestsCount = totalContestsCount;
    }

    /**
     * <p>Gets the copilots for this project.</p>
     *
     * @return a <code>User[]</code> providing the copilots for this project.
     * @since 1.1
     */
    public User[] getCopilots() {
        return this.copilots;
    }

    /**
     * <p>Sets the copilots for this project.</p>
     *
     * @param copilots a <code>User[]</code> providing the copilots for this project.
     * @since 1.1
     */
    public void setCopilots(User[] copilots) {
        this.copilots = copilots;
    }

    /**
     * <p>Gets the number of active contests to be generated for this project.</p>
     *
     * @return a <code>int</code> providing the number of active contests to be generated for this project.
     * @since 1.1
     */
    public int getActiveContestsCount() {
        return this.activeContestsCount;
    }

    /**
     * <p>Sets the number of active contests to be generated for this project.</p>
     *
     * @param activeContestsCount a <code>int</code> providing the number of active contests to be generated for this
     *                            project.
     * @since 1.1
     */
    public void setActiveContestsCount(int activeContestsCount) {
        this.activeContestsCount = activeContestsCount;
    }
}
