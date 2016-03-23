/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package com.cronos.onlinereview.test.data.tcscatalog;

import java.io.Serializable;

/**
 * <p>A DTO for single submission declaration to be generated. Corresponds to 
 * <code>tcs_catalog.submission_declaration</code> database table.</p>
 * 
 * @author TCSDEVELOPER
 * @version 1.0 (Release Assembly - TopCoder System Test Data Generator Update 1)
 */
public class SubmissionDeclaration implements Serializable {

    /**
     * <p>A <code>long</code> providing the ID of this submission declaration.</p>
     */
    private long submissionDeclarationId;

    /**
     * <p>A <code>String</code> providing the comment for submission declaration.</p>
     */
    private String comment;

    /**
     * <p>A <code>boolean</code> providing the flag indicating whether submission has external content or not.</p>
     */
    private boolean hasExternalContent;

    /**
     * <p>Constructs new <code>SubmissionDeclaration</code> instance. This implementation does nothing.</p>
     */
    public SubmissionDeclaration() {
    }

    /**
     * <p>Gets the flag indicating whether submission has external content or not.</p>
     *
     * @return a <code>boolean</code> providing the flag indicating whether submission has external content or not.
     */
    public boolean getHasExternalContent() {
        return this.hasExternalContent;
    }

    /**
     * <p>Sets the flag indicating whether submission has external content or not.</p>
     *
     * @param hasExternalContent a <code>boolean</code> providing the flag indicating whether submission has external
     *                           content or not.
     */
    public void setHasExternalContent(boolean hasExternalContent) {
        this.hasExternalContent = hasExternalContent;
    }

    /**
     * <p>Gets the ID of this submission declaration.</p>
     *
     * @return a <code>long</code> providing the ID of this submission declaration.
     */
    public long getSubmissionDeclarationId() {
        return this.submissionDeclarationId;
    }

    /**
     * <p>Sets the ID of this submission declaration.</p>
     *
     * @param submissionDeclarationId a <code>long</code> providing the ID of this submission declaration.
     */
    public void setSubmissionDeclarationId(long submissionDeclarationId) {
        this.submissionDeclarationId = submissionDeclarationId;
    }

    /**
     * <p>Gets the comment for submission declaration.</p>
     *
     * @return a <code>String</code> providing the comment for submission declaration.
     */
    public String getComment() {
        return this.comment;
    }

    /**
     * <p>Sets the comment for submission declaration.</p>
     *
     * @param comment a <code>String</code> providing the comment for submission declaration.
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
}
