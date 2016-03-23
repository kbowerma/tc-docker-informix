/*
 * Copyright (C) 2011-2014 TopCoder Inc., All Rights Reserved.
 */
package com.cronos.onlinereview.test.data;

import com.cronos.onlinereview.test.data.commonoltp.SecurityRole;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>A DTO for single user account.</p>
 *
 * <p>
 * Version 1.1 ( Assembly 1.0) Change notes:
 *   <ol>
 *     <li>Added {@link #requiresSubmission} property. (1.1.1.3}</li>
 *     <li>Added {@link #requiresMilestoneSubmission} property. (1.1.1.3}</li>
 *   </ol>
 * </p>
 *
 * <p>
 * Version 1.2 (Test Data Preparation for topcoder Permission related performance improvement)
 * <ol>
 *     <li>Add security roles data</li>
 * </ol>
 * </p>
 * 
 * @author isv, GreatKevin
 * @version 1.2
 */
public class TopCoderUser {

    /**
     * <p>A <code>long</code> providing the user ID.</p>
     */
    private long userId;

    /**
     * <p>A <code>String</code> providing the user handle.</p>
     */
    private String handle;

    /**
     * <p>A <code>String</code> providing the first name for user.</p>
     */
    private String firstName;

    /**
     * <p>A <code>String</code> providing the last name for user.</p>
     */
    private String lastName;

    /**
     * <p>A <code>char</code> providing the status of the user.</p>
     */
    private char status;

    /**
     * The security roles of the user.
     * @since 1.2
     */
    private Set<SecurityRole> roles;

    /**
     * <p>A <code>boolean</code> providing the flag indicating whether a submission is to be generated for this user or
     * not.</p>
     * 
     * @since 1.1
     */
    private Set<Long> requiresSubmission = new HashSet<Long>();

    /**
     * <p>A <code>boolean</code> providing the flag indicating whether a milestone submission is to be generated for
     * this user or not.</p>
     * 
     * @since 1.1
     */
    private boolean requiresMilestoneSubmission;

    /**
     * <p>Gets the status of the user.</p>
     *
     * @return a <code>char</code> providing the status of the user.
     */
    public char getStatus() {
        return this.status;
    }

    /**
     * <p>Sets the status of the user.</p>
     *
     * @param status a <code>char</code> providing the status of the user.
     */
    public void setStatus(char status) {
        this.status = status;
    }

    /**
     * <p>Gets the last name for user.</p>
     *
     * @return a <code>String</code> providing the last name for user.
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * <p>Sets the last name for user.</p>
     *
     * @param lastName a <code>String</code> providing the last name for user.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * <p>Gets the first name for user.</p>
     *
     * @return a <code>String</code> providing the first name for user.
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * <p>Sets the first name for user.</p>
     *
     * @param firstName a <code>String</code> providing the first name for user.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * <p>Gets the user handle.</p>
     *
     * @return a <code>String</code> providing the user handle.
     */
    public String getHandle() {
        return this.handle;
    }

    /**
     * <p>Sets the user handle.</p>
     *
     * @param handle a <code>String</code> providing the user handle.
     */
    public void setHandle(String handle) {
        this.handle = handle;
    }

    /**
     * <p>Gets the user ID.</p>
     *
     * @return a <code>long</code> providing the user ID.
     */
    public long getUserId() {
        return this.userId;
    }

    /**
     * <p>Sets the user ID.</p>
     *
     * @param userId a <code>long</code> providing the user ID.
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * <p>Gets the flag indicating whether a milestone submission is to be generated for this user or not.</p>
     *
     * @return a <code>boolean</code> providing the flag indicating whether a milestone submission is to be generated
     *         for this user or not.
     * @since 1.1
     */
    public boolean getRequiresMilestoneSubmission() {
        return this.requiresMilestoneSubmission;
    }

    /**
     * <p>Sets the flag indicating whether a milestone submission is to be generated for this user or not.</p>
     *
     * @param requiresMilestoneSubmission a <code>boolean</code> providing the flag indicating whether a milestone
     *                                    submission is to be generated for this user or not.
     * @since 1.1
     */
    public void setRequiresMilestoneSubmission(boolean requiresMilestoneSubmission) {
        this.requiresMilestoneSubmission = requiresMilestoneSubmission;
    }

    /**
     * <p>Checks if a submission is to be generated for this user for specified contest.</p>
     *
     * @return a <code>boolean</code> providing the flag indicating whether a submission is to be generated for this
     *         user or not.
     * @since 1.1
     */
    public boolean getRequiresSubmission(long contestId) {
        return this.requiresSubmission.contains(contestId);
    }

    /**
     * <p>Sets the flag indicating that this user is required to have submission generated for specified contest.</p>
     *
     * @param contestId a <code>long</code> providing the ID of a contest which this user is required to have submission
     *                  generated for.
     * @since 1.1
     */
    public void setRequiresSubmission(long contestId) {
        this.requiresSubmission.add(contestId);
    }

    /**
     * Gets the security roles.
     *
     * @return the security roles.
     * @since 1.2
     */
    public Set<SecurityRole> getRoles() {
        return roles;
    }

    /**
     * Sets the security roles.
     *
     * @param roles the security roles.
     * @since 1.2
     */
    public void setRoles(Set<SecurityRole> roles) {
        this.roles = roles;
    }

    /**
     * <p>Constructs new <code>TopCoderUser</code> instance. This implementation does nothing.</p>
     */
    public TopCoderUser() {
    }
}
