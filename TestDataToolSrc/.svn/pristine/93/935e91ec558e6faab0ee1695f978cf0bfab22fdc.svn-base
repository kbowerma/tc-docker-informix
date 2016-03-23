/*
 * Copyright (C) 2011-2012 TopCoder Inc., All Rights Reserved.
 */
package com.cronos.onlinereview.test.data.generator.project;

import com.cronos.onlinereview.test.data.ProjectPhaseTemplate;
import com.cronos.onlinereview.test.data.TopCoderUser;
import com.cronos.onlinereview.test.data.User;
import com.cronos.onlinereview.test.data.corporateoltp.TcDirectProject;
import com.cronos.onlinereview.test.data.generator.IdGenerator;
import com.cronos.onlinereview.test.data.tcscatalog.Catalog;
import com.cronos.onlinereview.test.data.tcscatalog.CostLevel;
import com.cronos.onlinereview.test.data.tcscatalog.PhaseType;
import com.cronos.onlinereview.test.data.tcscatalog.ProjectCategory;
import com.cronos.onlinereview.test.data.tcscatalog.ProjectInfoType;
import com.cronos.onlinereview.test.data.tcscatalog.ProjectStatus;
import com.cronos.onlinereview.test.data.timeoltp.BillingProject;

import java.io.Serializable;

/**
 * <p>A class holding the parameters to be used for generating test data for a single project.</p>
 *
 * <p>
 * Version 1.1 (Release Assembly - TopCoder System Test Data Generator Update 1 Assembly 1.0) Change notes:
 *   <ol>
 *     <li>Added {@link #copilot} property.</li>
 *     <li>Added {@link #phasesTemplate} property.</li>
 *     <li>Added {@link #submissionDeclarationIdGenerator} property.</li>
 *     <li>Added {@link #studioSpecificationIdGenerator} property.</li>
 *     <li>Added {@link #milestoneReviewer} property.</li>
 *     <li>Added {@link #prizeIdGenerator} property.</li>
 *     <li>Added {@link #startDateOffset} property.</li>
 *   </ol>
 * </p>
 *
 * <p>
 * Version 1.2 (Release Assembly - TopCoder System Test Data Generator Update 2 Assembly 1.0) Change notes:
 *   <ol>
 *     <li>Changed the type of {@link #submitters} property from {@link User} to {@link TopCoderUser}. 
 *     (1.1.1.2)</li>
 *     <li>Added {@link #submissionsFailureRate} property. (1.1.1.4)</li>
 *     <li>Added {@link #paymentIdGenerator} property. (1.1.3)</li>
 *     <li>Added {@link #projectId} property.</li>
 *   </ol>
 * </p>
 * 
 * @author isv
 * @version 1.2
 */
public class ProjectGeneratorConfig implements Serializable {

    /**
     * <p>A <code>PhaseType</code> providing the type of the phase which has to be set to open status of the project 
     * which has to be generated.</p>
     */
    private PhaseType currentPhaseType;

    /**
     * <p>A <code>BillingProject</code> providing the details for billing project which generated project has to be
     * associated with.</p>
     */
    private BillingProject billingProject;

    /**
     * <p>A <code>TcDirectProject</code> providing the details for TC Direct project which generated project has to be
     * associated with.</p>
     */
    private TcDirectProject tcDirectProject;

    /**
     * <p>A <code>Catalog</code> providing the catalog set for this project.</p>
     */
    private Catalog catalog;

    /**
     * <p>A <code>ProjectCategory</code> providing the category of this project.</p>
     */
    private ProjectCategory projectCategory;

    /**
     * <p>A <code>ProjectStatus</code> providing the status of this project.</p>
     */
    private ProjectStatus projectStatus;

    /**
     * <p>A <code>ProjectInfoType[]</code> providing the list of project infos of boolean type which are to be set to
     * true.</p>
     */
    private ProjectInfoType[] flagsToBeSet;

    /**
     * <p>A <code>ProjectInfoType[]</code> providing the list of project infos of boolean type which are to be set to
     * false.</p>
     */
    private ProjectInfoType[] flagsToBeUnSet;

    /**
     * <p>A <code>User[]</code> providing the list of users to be set as managers for the generated project.</p>
     */
    private User[] managers;

    /**
     * <p>A <code>User[]</code> providing the list of users to be set as specification submitters for the generated
     * project.</p>
     */
    private User[] specificationSubmitters;

    /**
     * <p>A <code>User</code> providing the user to be set as specification reviewer for the generated project.</p>
     */
    private User specificationReviewer;

    /**
     * <p>A <code>User</code> providing the user to be set as primary reviewer for the generated project.</p>
     */
    private User primaryReviewer;

    /**
     * <p>A <code>User[]</code> providing the list of users to be set as reviewers for the generated project.</p>
     */
    private User[] reviewers;

    /**
     * <p>A <code>TopCoderUser[]</code> providing the list of users t obe set as submitters for the generated project.</p>
     */
    private TopCoderUser[] submitters;

    /**
     * <p>A <code>User</code> providing the user to be set as approver for the generated project.</p>
     */
    private User approver;

    /**
     * <p>A <code>User</code> providing the user to be set as copilot for the generated project.</p>
     * 
     * @since 1.1
     */
    private User copilot;

    /**
     * <p>A <code>Long</code> providing the forum ID for the generated project.</p>
     */
    private Long forumId;

    /**
     * <p>A <code>CostLevel</code> providing the cost level for project.</p>
     */
    private CostLevel costLevel;

    /**
     * <p>A <code>ProjectPhaseTemplate</code> providing the project phases template.</p>
     * 
     * @since 1.1
     */
    private ProjectPhaseTemplate phasesTemplate;

    /**
     * <p>A <code>IdGenerator</code> providing the generator for project specification IDs.</p>
     */
    private IdGenerator projectSpecIdGenerator;

    /**
     * <p>A <code>IdGenerator</code> providing the generator for component IDs.</p>
     */
    private IdGenerator componentIdGenerator;

    /**
     * <p>A <code>IdGenerator</code> providing the generator for component version IDs.</p>
     */
    private IdGenerator componentVersionIdGenerator;

    /**
     * <p>A <code>IdGenerator</code> providing the generator for project numbers.</p>
     */
    private IdGenerator projectCountGenerator;
    
    /**
     * <p>A <code>IdGenerator</code> providing the generator for project phase IDs.</p>
     */
    private IdGenerator phaseIdGenerator;
    
    /**
     * <p>A <code>IdGenerator</code> providing the generator for project resource IDs.</p>
     */
    private IdGenerator resourceIdGenerator;
    
    /**
     * <p>A <code>IdGenerator</code> providing the generator for component technology IDs.</p>
     */
    private IdGenerator compTechnologyIdGenerator;
    
    /**
     * <p>A <code>IdGenerator</code> providing the generator for component inquiry IDs.</p>
     */
    private IdGenerator compInquiryIdGenerator;

    /**
     * <p>A <code>IdGenerator</code> providing the generator for contest sale IDs.</p>
     */
    private IdGenerator contestSaleIdGenerator;

    /**
     * <p>A <code>IdGenerator</code> providing the generator for upload IDs.</p>
     */
    private IdGenerator uploadIdGenerator;

    /**
     * <p>A <code>IdGenerator</code> providing the generator for submission IDs.</p>
     */
    private IdGenerator submissionIdGenerator;

    /**
     * <p>A <code>IdGenerator</code> providing the generator for review IDs.</p>
     */
    private IdGenerator reviewIdGenerator;

    /**
     * <p>A <code>IdGenerator</code> providing the generator for review item IDs.</p>
     */
    private IdGenerator reviewItemIdGenerator;

    /**
     * <p>A <code>IdGenerator</code> providing the generator for review comment IDs.</p>
     */
    private IdGenerator reviewCommentIdGenerator;

    /**
     * <p>A <code>IdGenerator</code> providing the generator for review item comment IDs.</p>
     */
    private IdGenerator reviewItemCommentIdGenerator;

    /**
     * <p>A <code>IdGenerator</code> providing the generator for screening task IDs.</p>
     */
    private IdGenerator screeningTaskIdGenerator;

    /**
     * <p>A <code>IdGenerator</code> providing the generator for prize IDs.</p>
     * 
     * @since 1.1
     */
    private IdGenerator prizeIdGenerator;

    /**
     * <p>A <code>IdGenerator</code> providing the generator for Studio specification IDs.</p>
     * 
     * @since 1.1
     */
    private IdGenerator studioSpecificationIdGenerator;

    /**
     * <p>A <code>IdGenerator</code> providing the generator for IDs for submission declarations.</p>
     * 
     * @since 1.1
     */
    private IdGenerator submissionDeclarationIdGenerator;

    /**
     * <p>A <code>User</code> providing the details for user responsible for doing milestone screening and reviews.</p>
     * 
     * @since 1.1
     */
    private User milestoneReviewer;

    /**
     * <p>A <code>long</code> providing the offset for project to start (in minutes).</p>
     *
     * @since 1.1
     */
    private long startDateOffset;

    /**
     * <p>A <code>int</code> providing the rate for submissions failed to pass review.</p>
     * 
     * @since 1.2
     */
    private int submissionsFailureRate;

    /**
     * <p>A <code>IdGenerator</code> providing the generator for payment IDs.</p>
     * 
     * @since 1.2
     */
    private IdGenerator paymentIdGenerator;

    /**
     * <p>A <code>long</code> providing the ID of a project to be generated.</p>
     *
     * @since 1.2
     */
    private long projectId;

    /**
     * <p>Constructs new <code>ProjectGeneratorConfig</code> instance.</p>
     */
    public ProjectGeneratorConfig() {
    }

    /**
     * <p>Gets the details for TC Direct project which generated project has to be associated with.</p>
     *
     * @return a <code>TcDirectProject</code> providing the details for TC Direct project which generated project has to
     *         be associated with.
     */
    public TcDirectProject getTcDirectProject() {
        return this.tcDirectProject;
    }

    /**
     * <p>Sets the details for TC Direct project which generated project has to be associated with.</p>
     *
     * @param tcDirectProject a <code>TcDirectProject</code> providing the details for TC Direct project which generated
     *                        project has to be associated with.
     */
    public void setTcDirectProject(TcDirectProject tcDirectProject) {
        this.tcDirectProject = tcDirectProject;
    }

    /**
     * <p>Gets the list of users to be set as managers for the generated project.</p>
     *
     * @return a <code>User[]</code> providing the list of users to be set as managers for the generated project.
     */
    public User[] getManagers() {
        return this.managers;
    }

    /**
     * <p>Sets the list of users to be set as managers for the generated project.</p>
     *
     * @param managers a <code>User[]</code> providing the list of users to be set as managers for the generated
     *                 project.
     */
    public void setManagers(User[] managers) {
        this.managers = managers;
    }

    /**
     * <p>Gets the catalog set for this project.</p>
     *
     * @return a <code>Catalog</code> providing the catalog set for this project.
     */
    public Catalog getCatalog() {
        return this.catalog;
    }

    /**
     * <p>Sets the catalog set for this project.</p>
     *
     * @param catalog a <code>Catalog</code> providing the catalog set for this project.
     */
    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    /**
     * <p>Gets the category of this project.</p>
     *
     * @return a <code>ProjectCategory</code> providing the category of this project.
     */
    public ProjectCategory getProjectCategory() {
        return this.projectCategory;
    }

    /**
     * <p>Sets the category of this project.</p>
     *
     * @param projectCategory a <code>ProjectCategory</code> providing the category of this project.
     */
    public void setProjectCategory(ProjectCategory projectCategory) {
        this.projectCategory = projectCategory;
    }

    /**
     * <p>Gets the status of this project.</p>
     *
     * @return a <code>ProjectStatus</code> providing the status of this project.
     */
    public ProjectStatus getProjectStatus() {
        return this.projectStatus;
    }

    /**
     * <p>Sets the status of this project.</p>
     *
     * @param projectStatus a <code>ProjectStatus</code> providing the status of this project.
     */
    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }

    /**
     * <p>Gets the list of project infos of boolean type which are to be set to true.</p>
     *
     * @return a <code>ProjectInfoType[]</code> providing the list of project infos of boolean type which are to be set
     *         to true.
     */
    public ProjectInfoType[] getFlagsToBeSet() {
        return this.flagsToBeSet;
    }

    /**
     * <p>Sets the list of project infos of boolean type which are to be set to true.</p>
     *
     * @param flagsToBeSet a <code>ProjectInfoType[]</code> providing the list of project infos of boolean type which
     *                     are to be set to true.
     */
    public void setFlagsToBeSet(ProjectInfoType[] flagsToBeSet) {
        this.flagsToBeSet = flagsToBeSet;
    }

    /**
     * <p>Gets the list of project infos of boolean type which are to be set to false.</p>
     *
     * @return a <code>ProjectInfoType[]</code> providing the list of project infos of boolean type which are to be set
     *         to false.
     */
    public ProjectInfoType[] getFlagsToBeUnSet() {
        return this.flagsToBeUnSet;
    }

    /**
     * <p>Sets the list of project infos of boolean type which are to be set to false.</p>
     *
     * @param flagsToBeUnSet a <code>ProjectInfoType[]</code> providing the list of project infos of boolean type which
     *                     are to be set to false.
     */
    public void setFlagsToBeUnSet(ProjectInfoType[] flagsToBeUnSet) {
        this.flagsToBeUnSet = flagsToBeUnSet;
    }

    /**
     * <p>Gets the details for billing project which generated project has to be associated with.</p>
     *
     * @return a <code>BillingProject</code> providing the details for billing project which generated project has to be
     *         associated with.
     */
    public BillingProject getBillingProject() {
        return this.billingProject;
    }

    /**
     * <p>Sets the details for billing project which generated project has to be associated with.</p>
     *
     * @param billingProject a <code>BillingProject</code> providing the details for billing project which generated
     *                       project has to be associated with.
     */
    public void setBillingProject(BillingProject billingProject) {
        this.billingProject = billingProject;
    }

    /**
     * <p>Gets the forum ID for the generated project.</p>
     *
     * @return a <code>Long</code> providing the forum ID for the generated project.
     */
    public Long getForumId() {
        return this.forumId;
    }

    /**
     * <p>Sets the forum ID for the generated project.</p>
     *
     * @param forumId a <code>Long</code> providing the forum ID for the generated project.
     */
    public void setForumId(Long forumId) {
        this.forumId = forumId;
    }

    /**
     * <p>Gets the generator for project specification IDs.</p>
     *
     * @return an <code>IdGenerator</code> providing the generator for project specification IDs.
     */
    public IdGenerator getProjectSpecIdGenerator() {
        return this.projectSpecIdGenerator;
    }

    /**
     * <p>Gets the generator for component IDs.</p>
     * 
     * @return an <code>IdGenerator</code> providing the generator for component IDs.
     */
    public IdGenerator getComponentIdGenerator() {
        return this.componentIdGenerator;
    }

    /**
     * <p>Gets the generator for component version IDs.</p>
     * 
     * @return an <code>IdGenerator</code> providing the generator for component version IDs.
     */
    public IdGenerator getComponentVersionIdGenerator() {
        return componentVersionIdGenerator;
    }

    /**
     * <p>Gets the generator for project numbers.</p>
     * 
     * @return an <code>IdGenerator</code> providing the generator for project numbers.
     */
    public IdGenerator getProjectCountGenerator() {
        return this.projectCountGenerator;
    }

    /**
     * <p>Gets the generator for project phase IDs.</p>
     * 
     * @return an <code>IdGenerator</code> providing the generator for project phase IDs.
     */
    public IdGenerator getPhaseIdGenerator() {
        return this.phaseIdGenerator;
    }

    /**
     * <p>Gets the generator for project resource IDs.</p>
     * 
     * @return an <code>IdGenerator</code> providing the generator for project resource IDs.
     */
    public IdGenerator getResourceIdGenerator() {
        return this.resourceIdGenerator;
    }

    /**
     * <p>Gets the generator for component technology IDs.</p>
     * 
     * @return an <code>IdGenerator</code> providing the generator for component technology IDs.
     */
    public IdGenerator getCompTechnologyIdGenerator() {
        return this.compTechnologyIdGenerator;
    }

    /**
     * <p>Gets the generator for component inquiry IDs.</p>
     * 
     * @return a <code>IdGenerator</code> providing the generator for component inquiry IDs.
     */
    public IdGenerator getCompInquiryIdGenerator() {
        return this.compInquiryIdGenerator;
    }

    /**
     * <p>Gets the type of the phase which has to be set to open status of the project which has to be generated.</p>
     *
     * @return a <code>PhaseType</code> providing the type of the phase which has to be set to open status of the 
     *         project which has to be generated.
     */
    public PhaseType getCurrentPhaseType() {
        return this.currentPhaseType;
    }

    /**
     * <p>Sets the type of the phase which has to be set to open status of the project which has to be generated.</p>
     *
     * @param currentPhaseType a <code>PhaseType</code> providing the type of the phase which has to be set to open 
     *        status of the project which has to be generated.
     */
    public void setCurrentPhaseType(PhaseType currentPhaseType) {
        this.currentPhaseType = currentPhaseType;
    }

    /**
     * <p>Gets the cost level for project.</p>
     *
     * @return a <code>CostLevel</code> providing the cost level for project.
     */
    public CostLevel getCostLevel() {
        return this.costLevel;
    }

    /**
     * <p>Sets the cost level for project.</p>
     *
     * @param costLevel a <code>CostLevel</code> providing the cost level for project.
     */
    public void setCostLevel(CostLevel costLevel) {
        this.costLevel = costLevel;
    }

    /**
     * <p>Gets the list of users to be set as specification submitters for the generated project.</p>
     *
     * @return a <code>User[]</code> providing the list of users to be set as specification submitters for the generated
     *         project.
     */
    public User[] getSpecificationSubmitters() {
        return this.specificationSubmitters;
    }

    /**
     * <p>Sets the list of users to be set as specification submitters for the generated project.</p>
     *
     * @param specificationSubmitters a <code>User[]</code> providing the list of users to be set as specification
     *                                submitters for the generated project.
     */
    public void setSpecificationSubmitters(User[] specificationSubmitters) {
        this.specificationSubmitters = specificationSubmitters;
    }

    /**
     * <p>Gets the user to be set as specification reviewer for the generated project.</p>
     *
     * @return a <code>User</code> providing the user to be set as specification reviewer for the generated project.
     */
    public User getSpecificationReviewer() {
        return this.specificationReviewer;
    }

    /**
     * <p>Sets the user to be set as specification reviewer for the generated project.</p>
     *
     * @param specificationReviewer a <code>User</code> providing the user to be set as specification reviewer for the
     *                              generated project.
     */
    public void setSpecificationReviewer(User specificationReviewer) {
        this.specificationReviewer = specificationReviewer;
    }

    /**
     * <p>Gets the user to be set as primary reviewer for the generated project.</p>
     *
     * @return a <code>User</code> providing the user to be set as primary reviewer for the generated project.
     */
    public User getPrimaryReviewer() {
        return this.primaryReviewer;
    }

    /**
     * <p>Sets the user to be set as primary reviewer for the generated project.</p>
     *
     * @param primaryReviewer a <code>User</code> providing the user to be set as primary reviewer for the generated
     *                        project.
     */
    public void setPrimaryReviewer(User primaryReviewer) {
        this.primaryReviewer = primaryReviewer;
    }

    /**
     * <p>Gets the list of users to be set as reviewers for the generated project.</p>
     *
     * @return a <code>User[]</code> providing the list of users to be set as reviewers for the generated project.
     */
    public User[] getReviewers() {
        return this.reviewers;
    }

    /**
     * <p>Sets the list of users to be set as reviewers for the generated project.</p>
     *
     * @param reviewers a <code>User[]</code> providing the list of users to be set as reviewers for the generated
     *                  project.
     */
    public void setReviewers(User[] reviewers) {
        this.reviewers = reviewers;
    }

    /**
     * <p>Gets the user to be set as approver for the generated project.</p>
     *
     * @return a <code>User</code> providing the user to be set as approver for the generated project.
     */
    public User getApprover() {
        return this.approver;
    }

    /**
     * <p>Sets the user to be set as approver for the generated project.</p>
     *
     * @param approver a <code>User</code> providing the user to be set as approver for the generated project.
     */
    public void setApprover(User approver) {
        this.approver = approver;
    }

    /**
     * <p>Gets the list of users t obe set as submitters for the generated project.</p>
     *
     * @return a <code>TopCoderUser[]</code> providing the list of users t obe set as submitters for the generated 
     *         project.
     */
    public TopCoderUser[] getSubmitters() {
        return this.submitters;
    }

    /**
     * <p>Sets the list of users t obe set as submitters for the generated project.</p>
     *
     * @param submitters a <code>TopCoderUser[]</code> providing the list of users t obe set as submitters for the 
     *                   generated project.
     */
    public void setSubmitters(TopCoderUser[] submitters) {
        this.submitters = submitters;
    }

    /**
     * <p>Sets the generator for project spec IDs.</p>
     *
     * @param projectSpecIdGenerator a <code>IdGenerator</code> providing the generator for project spec IDs.
     */
    public void setProjectSpecIdGenerator(IdGenerator projectSpecIdGenerator) {
        this.projectSpecIdGenerator = projectSpecIdGenerator;
    }

    /**
     * <p>Sets the generator for component IDs.</p>
     *
     * @param componentIdGenerator a <code>IdGenerator</code> providing the generator for component IDs.
     */
    public void setComponentIdGenerator(IdGenerator componentIdGenerator) {
        this.componentIdGenerator = componentIdGenerator;
    }

    /**
     * <p>Sets the generator for component version IDs.</p>
     *
     * @param componentVersionIdGenerator a <code>IdGenerator</code> providing the generator for component version IDs.
     */
    public void setComponentVersionIdGenerator(IdGenerator componentVersionIdGenerator) {
        this.componentVersionIdGenerator = componentVersionIdGenerator;
    }

    /**
     * <p>Sets the generator for project numbers.</p>
     *
     * @param projectCountGenerator a <code>IdGenerator</code> providing the generator for project numbers.
     */
    public void setProjectCountGenerator(IdGenerator projectCountGenerator) {
        this.projectCountGenerator = projectCountGenerator;
    }

    /**
     * <p>Sets the generator for project phase IDs.</p>
     *
     * @param phaseIdGenerator a <code>IdGenerator</code> providing the generator for project phase IDs.
     */
    public void setPhaseIdGenerator(IdGenerator phaseIdGenerator) {
        this.phaseIdGenerator = phaseIdGenerator;
    }

    /**
     * <p>Sets the generator for project resource IDs.</p>
     *
     * @param resourceIdGenerator a <code>IdGenerator</code> providing the generator for project resource IDs.
     */
    public void setResourceIdGenerator(IdGenerator resourceIdGenerator) {
        this.resourceIdGenerator = resourceIdGenerator;
    }

    /**
     * <p>Sets the generator for component technology IDs.</p>
     *
     * @param compTechnologyIdGenerator a <code>IdGenerator</code> providing the generator for component technology IDs.
     */
    public void setCompTechnologyIdGenerator(IdGenerator compTechnologyIdGenerator) {
        this.compTechnologyIdGenerator = compTechnologyIdGenerator;
    }

    /**
     * <p>Sets the generator for component inquiry IDs.</p>
     *
     * @param compInquiryIdGenerator a <code>IdGenerator</code> providing the generator for component inquiry IDs.
     */
    public void setCompInquiryIdGenerator(IdGenerator compInquiryIdGenerator) {
        this.compInquiryIdGenerator = compInquiryIdGenerator;
    }

    /**
     * <p>Gets the generator for contest sale IDs.</p>
     *
     * @return a <code>IdGenerator</code> providing the generator for contest sale IDs.
     */
    public IdGenerator getContestSaleIdGenerator() {
        return this.contestSaleIdGenerator;
    }

    /**
     * <p>Sets the generator for contest sale IDs.</p>
     *
     * @param contestSaleIdGenerator a <code>IdGenerator</code> providing the generator for contest sale IDs.
     */
    public void setContestSaleIdGenerator(IdGenerator contestSaleIdGenerator) {
        this.contestSaleIdGenerator = contestSaleIdGenerator;
    }

    /**
     * <p>Gets the generator for submission IDs.</p>
     *
     * @return a <code>IdGenerator</code> providing the generator for submission IDs.
     */
    public IdGenerator getSubmissionIdGenerator() {
        return this.submissionIdGenerator;
    }

    /**
     * <p>Sets the generator for submission IDs.</p>
     *
     * @param submissionIdGenerator a <code>IdGenerator</code> providing the generator for submission IDs.
     */
    public void setSubmissionIdGenerator(IdGenerator submissionIdGenerator) {
        this.submissionIdGenerator = submissionIdGenerator;
    }

    /**
     * <p>Gets the generator for upload IDs.</p>
     *
     * @return a <code>IdGenerator</code> providing the generator for upload IDs.
     */
    public IdGenerator getUploadIdGenerator() {
        return this.uploadIdGenerator;
    }

    /**
     * <p>Sets the generator for upload IDs.</p>
     *
     * @param uploadIdGenerator a <code>IdGenerator</code> providing the generator for upload IDs.
     */
    public void setUploadIdGenerator(IdGenerator uploadIdGenerator) {
        this.uploadIdGenerator = uploadIdGenerator;
    }

    /**
     * <p>Gets the generator for review item comment IDs.</p>
     *
     * @return a <code>IdGenerator</code> providing the generator for review item comment IDs.
     */
    public IdGenerator getReviewItemCommentIdGenerator() {
        return this.reviewItemCommentIdGenerator;
    }

    /**
     * <p>Sets the generator for review item comment IDs.</p>
     *
     * @param reviewItemCommentIdGenerator a <code>IdGenerator</code> providing the generator for review item comment
     *                                     IDs.
     */
    public void setReviewItemCommentIdGenerator(IdGenerator reviewItemCommentIdGenerator) {
        this.reviewItemCommentIdGenerator = reviewItemCommentIdGenerator;
    }

    /**
     * <p>Gets the generator for review comment IDs.</p>
     *
     * @return a <code>IdGenerator</code> providing the generator for review comment IDs.
     */
    public IdGenerator getReviewCommentIdGenerator() {
        return this.reviewCommentIdGenerator;
    }

    /**
     * <p>Sets the generator for review comment IDs.</p>
     *
     * @param reviewCommentIdGenerator a <code>IdGenerator</code> providing the generator for review comment IDs.
     */
    public void setReviewCommentIdGenerator(IdGenerator reviewCommentIdGenerator) {
        this.reviewCommentIdGenerator = reviewCommentIdGenerator;
    }

    /**
     * <p>Gets the generator for review item IDs.</p>
     *
     * @return a <code>IdGenerator</code> providing the generator for review item IDs.
     */
    public IdGenerator getReviewItemIdGenerator() {
        return this.reviewItemIdGenerator;
    }

    /**
     * <p>Sets the generator for review item IDs.</p>
     *
     * @param reviewItemIdGenerator a <code>IdGenerator</code> providing the generator for review item IDs.
     */
    public void setReviewItemIdGenerator(IdGenerator reviewItemIdGenerator) {
        this.reviewItemIdGenerator = reviewItemIdGenerator;
    }

    /**
     * <p>Gets the generator for review IDs.</p>
     *
     * @return a <code>IdGenerator</code> providing the generator for review IDs.
     */
    public IdGenerator getReviewIdGenerator() {
        return this.reviewIdGenerator;
    }

    /**
     * <p>Sets the generator for review IDs.</p>
     *
     * @param reviewIdGenerator a <code>IdGenerator</code> providing the generator for review IDs.
     */
    public void setReviewIdGenerator(IdGenerator reviewIdGenerator) {
        this.reviewIdGenerator = reviewIdGenerator;
    }

    /**
     * <p>Gets the generator for screening task IDs.</p>
     *
     * @return a <code>IdGenerator</code> providing the generator for screening task IDs.
     */
    public IdGenerator getScreeningTaskIdGenerator() {
        return this.screeningTaskIdGenerator;
    }

    /**
     * <p>Sets the generator for screening task IDs.</p>
     *
     * @param screeningTaskIdGenerator a <code>IdGenerator</code> providing the generator for screening task IDs.
     */
    public void setScreeningTaskIdGenerator(IdGenerator screeningTaskIdGenerator) {
        this.screeningTaskIdGenerator = screeningTaskIdGenerator;
    }

    /**
     * <p>Gets the user to be set as copilot for the generated project.</p>
     *
     * @return a <code>User</code> providing the user to be set as copilot for the generated project.
     * @since 1.1
     */
    public User getCopilot() {
        return this.copilot;
    }

    /**
     * <p>Sets the user to be set as copilot for the generated project.</p>
     *
     * @param copilot a <code>User</code> providing the user to be set as copilot for the generated project.
     * @since 1.1
     */
    public void setCopilot(User copilot) {
        this.copilot = copilot;
    }

    /**
     * <p>Gets the project phases template.</p>
     *
     * @return a <code>ProjectPhaseTemplate</code> providing the project phases template.
     * @since 1.1
     */
    public ProjectPhaseTemplate getPhasesTemplate() {
        return this.phasesTemplate;
    }

    /**
     * <p>Sets the project phases template.</p>
     *
     * @param phasesTemplate a <code>ProjectPhaseTemplate</code> providing the project phases template.
     * @since 1.1
     */
    public void setPhasesTemplate(ProjectPhaseTemplate phasesTemplate) {
        this.phasesTemplate = phasesTemplate;
    }

    /**
     * <p>Gets the generator for prize IDs.</p>
     *
     * @return a <code>IdGenerator</code> providing the generator for prize IDs.
     * @since 1.1
     */
    public IdGenerator getPrizeIdGenerator() {
        return this.prizeIdGenerator;
    }

    /**
     * <p>Sets the generator for prize IDs.</p>
     *
     * @param prizeIdGenerator a <code>IdGenerator</code> providing the generator for prize IDs.
     * @since 1.1
     */
    public void setPrizeIdGenerator(IdGenerator prizeIdGenerator) {
        this.prizeIdGenerator = prizeIdGenerator;
    }

    /**
     * <p>Gets the generator for Studio specification IDs.</p>
     *
     * @return a <code>IdGenerator</code> providing the generator for Studio specification IDs.
     * @since 1.1
     */
    public IdGenerator getStudioSpecificationIdGenerator() {
        return this.studioSpecificationIdGenerator;
    }

    /**
     * <p>Sets the generator for Studio specification IDs.</p>
     *
     * @param studioSpecificationIdGenerator a <code>IdGenerator</code> providing the generator for Studio specification
     *                                       IDs.
     * @since 1.1
     */
    public void setStudioSpecificationIdGenerator(IdGenerator studioSpecificationIdGenerator) {
        this.studioSpecificationIdGenerator = studioSpecificationIdGenerator;
    }

    /**
     * <p>Gets the details for user responsible for doing milestone screening and reviews.</p>
     *
     * @return a <code>User</code> providing the details for user responsible for doing milestone screening and
     *         reviews.
     * @since 1.1
     */
    public User getMilestoneReviewer() {
        return this.milestoneReviewer;
    }

    /**
     * <p>Sets the details for user responsible for doing milestone screening and reviews.</p>
     *
     * @param milestoneReviewer a <code>User</code> providing the details for user responsible for doing milestone
     *                          screening and reviews.
     * @since 1.1
     */
    public void setMilestoneReviewer(User milestoneReviewer) {
        this.milestoneReviewer = milestoneReviewer;
    }

    /**
     * <p>Gets the generator for IDs for submission declarations.</p>
     *
     * @return a <code>IdGenerator</code> providing the generator for IDs for submission declarations.
     * @since 1.1
     */
    public IdGenerator getSubmissionDeclarationIdGenerator() {
        return this.submissionDeclarationIdGenerator;
    }

    /**
     * <p>Sets the generator for IDs for submission declarations.</p>
     *
     * @param submissionDeclarationIdGenerator
     *         a <code>IdGenerator</code> providing the generator for IDs for submission declarations.
     * @since 1.1
     */
    public void setSubmissionDeclarationIdGenerator(IdGenerator submissionDeclarationIdGenerator) {
        this.submissionDeclarationIdGenerator = submissionDeclarationIdGenerator;
    }

    /**
     * <p>Gets the offset for project to start (in minutes).</p>
     *
     * @return a <code>long</code> providing the offset for project to start (in minutes).
     * @since 1.1
     */
    public long getStartDateOffset() {
        return this.startDateOffset;
    }

    /**
     * <p>Sets the offset for project to start (in minutes).</p>
     *
     * @param startDateOffset a <code>long</code> providing the offset for project to start (in minutes).
     * @since 1.1
     */
    public void setStartDateOffset(long startDateOffset) {
        this.startDateOffset = startDateOffset;
    }

    /**
     * <p>Gets the rate for submissions failed to pass review.</p>
     *
     * @return a <code>int</code> providing the rate for submissions failed to pass review.
     * @since 1.2
     */
    public int getSubmissionsFailureRate() {
        return this.submissionsFailureRate;
    }

    /**
     * <p>Sets the rate for submissions failed to pass review.</p>
     *
     * @param submissionsFailureRate a <code>int</code> providing the rate for submissions failed to pass review.
     * @since 1.2
     */
    public void setSubmissionsFailureRate(int submissionsFailureRate) {
        this.submissionsFailureRate = submissionsFailureRate;
    }

    /**
     * <p>Gets the generator for payment IDs.</p>
     *
     * @return a <code>IdGenerator</code> providing the generator for payment IDs.
     * @since 1.2
     */
    public IdGenerator getPaymentIdGenerator() {
        return this.paymentIdGenerator;
    }

    /**
     * <p>Sets the generator for payment IDs.</p>
     *
     * @param paymentIdGenerator a <code>IdGenerator</code> providing the generator for payment IDs.
     * @since 1.2
     */
    public void setPaymentIdGenerator(IdGenerator paymentIdGenerator) {
        this.paymentIdGenerator = paymentIdGenerator;
    }

    /**
     * <p>Gets the ID of a project to be generated.</p>
     *
     * @return a <code>long</code> providing the ID of a project to be generated.
     * @since 1.2
     */
    public long getProjectId() {
        return this.projectId;
    }

    /**
     * <p>Sets the ID of a project to be generated.</p>
     *
     * @param projectId a <code>long</code> providing the ID of a project to be generated.
     * @since 1.2
     */
    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }
}
