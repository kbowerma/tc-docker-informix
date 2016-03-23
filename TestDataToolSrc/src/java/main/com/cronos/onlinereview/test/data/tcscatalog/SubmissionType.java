/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package com.cronos.onlinereview.test.data.tcscatalog;

/**
 * <p>An enumeration over existing submission types. Corresponds to <code>tcs_catalog.submission_type_lu</code> 
 * database table.</p>
 *
 * <p>
 * Version 1.1 (Release Assembly - TopCoder System Test Data Generator Update 1 Assembly 1.0) Change notes:
 *   <ol>
 *     <li>Added {@link #MILESTONE_SUBMISSION} item.</li>
 *   </ol>
 * </p>
 * 
 * @author isv
 * @version 1.1
 */
public enum SubmissionType {
    
    CONTEST_SUBMISSION(1, "Contest Submission"),
    
    SPECIFICATION_SUBMISSION(2, "Specification Submission"),
    
    MILESTONE_SUBMISSION(3, "Milestone Submission");

    /**
     * <p>A <code>long</code> providing the ID of this submission type.</p>
     */
    private long submissionTypeId;

    /**
     * <p>A <code>String</code> providing the name of this submission type.</p>
     */
    private String name;

    /**
     * <p>Constructs new <code>SubmissionType</code> instance with specified ID and name.</p>
     *
     * @param submissionTypeId a <code>long</code> providing the ID of this submission type.
     * @param name a <code>String</code> providing the name of this submission type.
     */
    private SubmissionType(long submissionTypeId, String name) {
        this.submissionTypeId = submissionTypeId;
        this.name = name;
    }

    /**
     * <p>Gets the name of this submission type.</p>
     *
     * @return a <code>String</code> providing the name of this submission type.
     */
    public String getName() {
        return this.name;
    }

    /**
     * <p>Gets the ID of this submission type.</p>
     *
     * @return a <code>long</code> providing the ID of this submission type.
     */
    public long getSubmissionTypeId() {
        return this.submissionTypeId;
    }
}
