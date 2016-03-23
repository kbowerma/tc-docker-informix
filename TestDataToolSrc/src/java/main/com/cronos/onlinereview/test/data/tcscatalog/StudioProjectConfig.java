/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package com.cronos.onlinereview.test.data.tcscatalog;

/**
 * <p>A DTO for configuration for single Studio project to be generated. Corresponds to 
 * <code>tcs_catalog.project_studio_specification</code> database table.</p>
 * 
 * @author TCSDEVELOPER
 * @version 1.0 (Release Assembly - TopCoder System Test Data Generator Update 1)
 */
public class StudioProjectConfig {

    /**
     * <p>A <code>long</code> providing the ID for this specification.</p>
     */
    private long studioSpecificationId;

    /**
     * <p>A <code>String</code> providing the goals of Studio contest.</p>
     */
    private String goals;

    /**
     * <p>A <code>String</code> providing the target audience for Studio contest.</p>
     */
    private String targetAudience;

    /**
     * <p>A <code>String</code> providing the branding guidelines for Studio contest.</p>
     */
    private String brandingGuidelines;

    /**
     * <p>A <code>String</code> providing the disliked design websites for Studio contest.</p>
     */
    private String dislikedDesignWebsites;

    /**
     * <p>A <code>String</code> providing the other instructions for Studio contest.</p>
     */
    private String otherInstructions;

    /**
     * <p>A <code>String</code> providing the winning criteria for Studio contest.</p>
     */
    private String winningCriteria;

    /**
     * <p>A <code>boolean</code> providing the flag indicating whether submitters are locked between rounds or not.</p>
     */
    private boolean submittersLockedBetweenRounds;

    /**
     * <p>A <code>String</code> providing the round 1 introduction for Studio contest.</p>
     */
    private String round1Introduction;

    /**
     * <p>A <code>String</code> providing the round 2 introduction for Studio contest.</p>
     */
    private String round2Introduction;

    /**
     * <p>A <code>String</code> providing the colors for Studio contest.</p>
     */
    private String colors;

    /**
     * <p>A <code>String</code> providing the fonts for Studio contests.</p>
     */
    private String fonts;

    /**
     * <p>A <code>String</code> providing the layout and size for Studio contests.</p>
     */
    private String layoutAndSize;

    /**
     * <p>A <code>String</code> providing the contest introduction for Studio contest.</p>
     */
    private String contestIntroduction;

    /**
     * <p>A <code>String</code> providing the description for Studio contest.</p>
     */
    private String contestDescription;

    /**
     * <p>A <code>String</code> providing the content requirements for Studio contest.</p>
     */
    private String contentRequirements;

    /**
     * <p>A <code>String</code> providing the general feedback for Studio contest.</p>
     */
    private String generalFeedback;

    /**
     * <p>A <code>boolean</code> providing the flag indicating whether to allow stock art for Studio contest or
     * not.</p>
     */
    private boolean allowStockArt;

    /**
     * <p>A <code>boolean</code> providing the flag indicating whether the submissions are viewable for Studio contest
     * or not.</p>
     */
    private boolean viewableSubmissions;

    /**
     * <p>A <code>boolean</code> providing the flag indicating whether the submitters are viewable for Studio contest or
     * not.</p>
     */
    private boolean viewableSubmitters;

    /**
     * <p>A <code>int</code> providing the maximum allowed number of submissions for Studio contest.</p>
     */
    private int maximumSubmissions;

    /**
     * <p>Constructs new <code>StudioProjectConfig</code> instance. This implementation does nothing.</p>
     */
    public StudioProjectConfig() {
    }

    /**
     * <p>Gets the ID for this specification.</p>
     *
     * @return a <code>long</code> providing the ID for this specification.
     */
    public long getStudioSpecificationId() {
        return this.studioSpecificationId;
    }

    /**
     * <p>Sets the ID for this specification.</p>
     *
     * @param studioSpecificationId a <code>long</code> providing the ID for this specification.
     */
    public void setStudioSpecificationId(long studioSpecificationId) {
        this.studioSpecificationId = studioSpecificationId;
    }

    /**
     * <p>Gets the general feedback for Studio contest.</p>
     *
     * @return a <code>String</code> providing the general feedback for Studio contest.
     */
    public String getGeneralFeedback() {
        return this.generalFeedback;
    }

    /**
     * <p>Sets the general feedback for Studio contest.</p>
     *
     * @param generalFeedback a <code>String</code> providing the general feedback for Studio contest.
     */
    public void setGeneralFeedback(String generalFeedback) {
        this.generalFeedback = generalFeedback;
    }

    /**
     * <p>Gets the content requirements for Studio contest.</p>
     *
     * @return a <code>String</code> providing the content requirements for Studio contest.
     */
    public String getContentRequirements() {
        return this.contentRequirements;
    }

    /**
     * <p>Sets the content requirements for Studio contest.</p>
     *
     * @param contentRequirements a <code>String</code> providing the content requirements for Studio contest.
     */
    public void setContentRequirements(String contentRequirements) {
        this.contentRequirements = contentRequirements;
    }

    /**
     * <p>Gets the description for Studio contest.</p>
     *
     * @return a <code>String</code> providing the description for Studio contest.
     */
    public String getContestDescription() {
        return this.contestDescription;
    }

    /**
     * <p>Sets the description for Studio contest.</p>
     *
     * @param contestDescription a <code>String</code> providing the description for Studio contest.
     */
    public void setContestDescription(String contestDescription) {
        this.contestDescription = contestDescription;
    }

    /**
     * <p>Gets the contest introduction for Studio contest.</p>
     *
     * @return a <code>String</code> providing the contest introduction for Studio contest.
     */
    public String getContestIntroduction() {
        return this.contestIntroduction;
    }

    /**
     * <p>Sets the contest introduction for Studio contest.</p>
     *
     * @param contestIntroduction a <code>String</code> providing the contest introduction for Studio contest.
     */
    public void setContestIntroduction(String contestIntroduction) {
        this.contestIntroduction = contestIntroduction;
    }

    /**
     * <p>Gets the layout and size for Studio contests.</p>
     *
     * @return a <code>String</code> providing the layout and size for Studio contests.
     */
    public String getLayoutAndSize() {
        return this.layoutAndSize;
    }

    /**
     * <p>Sets the layout and size for Studio contests.</p>
     *
     * @param layoutAndSize a <code>String</code> providing the layout and size for Studio contests.
     */
    public void setLayoutAndSize(String layoutAndSize) {
        this.layoutAndSize = layoutAndSize;
    }

    /**
     * <p>Gets the fonts for Studio contests.</p>
     *
     * @return a <code>String</code> providing the fonts for Studio contests.
     */
    public String getFonts() {
        return this.fonts;
    }

    /**
     * <p>Sets the fonts for Studio contests.</p>
     *
     * @param fonts a <code>String</code> providing the fonts for Studio contests.
     */
    public void setFonts(String fonts) {
        this.fonts = fonts;
    }

    /**
     * <p>Gets the colors for Studio contest.</p>
     *
     * @return a <code>String</code> providing the colors for Studio contest.
     */
    public String getColors() {
        return this.colors;
    }

    /**
     * <p>Sets the colors for Studio contest.</p>
     *
     * @param colors a <code>String</code> providing the colors for Studio contest.
     */
    public void setColors(String colors) {
        this.colors = colors;
    }

    /**
     * <p>Gets the round 2 introduction for Studio contest.</p>
     *
     * @return a <code>String</code> providing the round 2 introduction for Studio contest.
     */
    public String getRound2Introduction() {
        return this.round2Introduction;
    }

    /**
     * <p>Sets the round 2 introduction for Studio contest.</p>
     *
     * @param round2Introduction a <code>String</code> providing the round 2 introduction for Studio contest.
     */
    public void setRound2Introduction(String round2Introduction) {
        this.round2Introduction = round2Introduction;
    }

    /**
     * <p>Gets the round 1 introduction for Studio contest.</p>
     *
     * @return a <code>String</code> providing the round 1 introduction for Studio contest.
     */
    public String getRound1Introduction() {
        return this.round1Introduction;
    }

    /**
     * <p>Sets the round 1 introduction for Studio contest.</p>
     *
     * @param round1Introduction a <code>String</code> providing the round 1 introduction for Studio contest.
     */
    public void setRound1Introduction(String round1Introduction) {
        this.round1Introduction = round1Introduction;
    }

    /**
     * <p>Gets the flag indicating whether submitters are locked between rounds or not.</p>
     *
     * @return a <code>boolean</code> providing the flag indicating whether submitters are locked between rounds or
     *         not.
     */
    public boolean getSubmittersLockedBetweenRounds() {
        return this.submittersLockedBetweenRounds;
    }

    /**
     * <p>Sets the flag indicating whether submitters are locked between rounds or not.</p>
     *
     * @param submittersLockedBetweenRounds a <code>boolean</code> providing the flag indicating whether submitters are
     *                                      locked between rounds or not.
     */
    public void setSubmittersLockedBetweenRounds(boolean submittersLockedBetweenRounds) {
        this.submittersLockedBetweenRounds = submittersLockedBetweenRounds;
    }

    /**
     * <p>Gets the winning criteria for Studio contest.</p>
     *
     * @return a <code>String</code> providing the winning criteria for Studio contest.
     */
    public String getWinningCriteria() {
        return this.winningCriteria;
    }

    /**
     * <p>Sets the winning criteria for Studio contest.</p>
     *
     * @param winningCriteria a <code>String</code> providing the winning criteria for Studio contest.
     */
    public void setWinningCriteria(String winningCriteria) {
        this.winningCriteria = winningCriteria;
    }

    /**
     * <p>Gets the other instructions for Studio contest.</p>
     *
     * @return a <code>String</code> providing the other instructions for Studio contest.
     */
    public String getOtherInstructions() {
        return this.otherInstructions;
    }

    /**
     * <p>Sets the other instructions for Studio contest.</p>
     *
     * @param otherInstructions a <code>String</code> providing the other instructions for Studio contest.
     */
    public void setOtherInstructions(String otherInstructions) {
        this.otherInstructions = otherInstructions;
    }

    /**
     * <p>Gets the disliked design websites for Studio contest.</p>
     *
     * @return a <code>String</code> providing the disliked design websites for Studio contest.
     */
    public String getDislikedDesignWebsites() {
        return this.dislikedDesignWebsites;
    }

    /**
     * <p>Sets the disliked design websites for Studio contest.</p>
     *
     * @param dislikedDesignWebsites a <code>String</code> providing the disliked design websites for Studio contest.
     */
    public void setDislikedDesignWebsites(String dislikedDesignWebsites) {
        this.dislikedDesignWebsites = dislikedDesignWebsites;
    }

    /**
     * <p>Gets the branding guidelines for Studio contest.</p>
     *
     * @return a <code>String</code> providing the branding guidelines for Studio contest.
     */
    public String getBrandingGuidelines() {
        return this.brandingGuidelines;
    }

    /**
     * <p>Sets the branding guidelines for Studio contest.</p>
     *
     * @param brandingGuidelines a <code>String</code> providing the branding guidelines for Studio contest.
     */
    public void setBrandingGuidelines(String brandingGuidelines) {
        this.brandingGuidelines = brandingGuidelines;
    }

    /**
     * <p>Gets the target audience for Studio contest.</p>
     *
     * @return a <code>String</code> providing the target audience for Studio contest.
     */
    public String getTargetAudience() {
        return this.targetAudience;
    }

    /**
     * <p>Sets the target audience for Studio contest.</p>
     *
     * @param targetAudience a <code>String</code> providing the target audience for Studio contest.
     */
    public void setTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
    }

    /**
     * <p>Gets the goals of Studio contest.</p>
     *
     * @return a <code>String</code> providing the goals of Studio contest.
     */
    public String getGoals() {
        return this.goals;
    }

    /**
     * <p>Sets the goals of Studio contest.</p>
     *
     * @param goals a <code>String</code> providing the goals of Studio contest.
     */
    public void setGoals(String goals) {
        this.goals = goals;
    }

    /**
     * <p>Gets the flag indicating whether to allow stock art for Studio contest or not.</p>
     *
     * @return a <code>boolean</code> providing the flag indicating whether to allow stock art for Studio contest or
     *         not.
     */
    public boolean getAllowStockArt() {
        return this.allowStockArt;
    }

    /**
     * <p>Sets the flag indicating whether to allow stock art for Studio contest or not.</p>
     *
     * @param allowStockArt a <code>boolean</code> providing the flag indicating whether to allow stock art for Studio
     *                      contest or not.
     */
    public void setAllowStockArt(boolean allowStockArt) {
        this.allowStockArt = allowStockArt;
    }

    /**
     * <p>Gets the flag indicating whether the submitters are viewable for Studio contest or not.</p>
     *
     * @return a <code>boolean</code> providing the flag indicating whether the submitters are viewable for Studio
     *         contest or not.
     */
    public boolean getViewableSubmitters() {
        return this.viewableSubmitters;
    }

    /**
     * <p>Sets the flag indicating whether the submitters are viewable for Studio contest or not.</p>
     *
     * @param viewableSubmitters a <code>boolean</code> providing the flag indicating whether the submitters are
     *                           viewable for Studio contest or not.
     */
    public void setViewableSubmitters(boolean viewableSubmitters) {
        this.viewableSubmitters = viewableSubmitters;
    }

    /**
     * <p>Gets the flag indicating whether the submissions are viewable for Studio contest or not.</p>
     *
     * @return a <code>boolean</code> providing the flag indicating whether the submissions are viewable for Studio
     *         contest or not.
     */
    public boolean getViewableSubmissions() {
        return this.viewableSubmissions;
    }

    /**
     * <p>Sets the flag indicating whether the submissions are viewable for Studio contest or not.</p>
     *
     * @param viewableSubmissions a <code>boolean</code> providing the flag indicating whether the submissions are
     *                            viewable for Studio contest or not.
     */
    public void setViewableSubmissions(boolean viewableSubmissions) {
        this.viewableSubmissions = viewableSubmissions;
    }

    /**
     * <p>Gets the maximum allowed number of submissions for Studio contest.</p>
     *
     * @return a <code>int</code> providing the maximum allowed number of submissions for Studio contest.
     */
    public int getMaximumSubmissions() {
        return this.maximumSubmissions;
    }

    /**
     * <p>Sets the maximum allowed number of submissions for Studio contest.</p>
     *
     * @param maximumSubmissions a <code>int</code> providing the maximum allowed number of submissions for Studio
     *                           contest.
     */
    public void setMaximumSubmissions(int maximumSubmissions) {
        this.maximumSubmissions = maximumSubmissions;
    }
}
