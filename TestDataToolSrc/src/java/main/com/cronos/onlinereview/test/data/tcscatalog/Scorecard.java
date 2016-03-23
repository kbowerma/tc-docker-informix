/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package com.cronos.onlinereview.test.data.tcscatalog;

/**
 * <p>An enumeration over the project statuses. Corresponds to <code>tcs_catalog.scorecard</code> database table.</p>
 *
 * <p>
 * Version 1.1 (Release Assembly - TopCoder System Test Data Generator Update 1 Assembly 1.0) Change notes:
 *   <ol>
 *     <li>Added {@link #DEFAULT_MILESTONE_SCREENING_SCORECARD} item.</li>
 *     <li>Added {@link #DEFAULT_MILESTONE_REVIEW_SCORECARD} item.</li>
 *     <li>Added {@link #DEFAULT_STUDIO_REVIEW_SCORECARD} item.</li>
 *   </ol>
 * </p>
 * 
 * @author isv
 * @version 1.1
 */
public enum Scorecard {
    
    DEFAULT_DESIGN_SCREENING_SCORECARD(30000410, "Default Design Screening Scorecard", ScorecardType.SCREENING, 
                                       75, 100, 30001000),
    
    DEFAULT_DESIGN_REVIEW_SCORECARD(30000411, "Default Design Review Scorecard", ScorecardType.REVIEW, 75, 100, 
                                    30001001),
    
    DEFAULT_DEVELOPMENT_SCREENING_SCORECARD(30000412, "Default Development Screening Scorecard", 
                                            ScorecardType.SCREENING, 75, 100, 30001002),
    
    DEFAULT_DEVELOPMENT_REVIEW_SCORECARD(30000413, "Default Development Review Scorecard", ScorecardType.REVIEW, 
                                         75, 100, 30001003),
    
    DEFAULT_APPLICATION_SCREENING_SCORECARD(30000414, "Default Application Screening Scorecard", 
                                            ScorecardType.SCREENING, 75, 100, 30001004),
    
    DEFAULT_APPLICATION_REVIEW_SCORECARD(30000415, "Default Application Review Scorecard", ScorecardType.REVIEW, 
                                         75, 100, 30001005),
    
    DEFAULT_APPROVAL_SCORECARD(30000720, "Default Approval Scorecard", ScorecardType.APPROVAL, 0, 100, 30001006),
    
    DEFAULT_POST_MORTEM_SCORECARD(30000721, "Default Post Mortem Review Scorecard", ScorecardType.POST_MORTEM, 0, 100, 
                                  30001007),
    
    DEFAULT_SPEC_REVIEW_SCORECARD(30000722, "Default Spec Review Scorecard", ScorecardType.SPECIFICATION_REVIEW, 
                                  0, 100, 30001008),
    
    DEFAULT_COPILOT_SELECTION_SCREENING_SCORECARD(30000870, "Default Copilot Selection Screening Scorecard", 
                                                  ScorecardType.SCREENING, 75, 100, 30003110),
    
    DEFAULT_COPILOT_SELECTION_REVIEW_SCORECARD(30000871, "Default Copilot Selection Review Scorecard", 
                                               ScorecardType.REVIEW, 80, 100, 30003111),

    DEFAULT_STUDIO_REVIEW_SCORECARD(30000418, "Default Studio Review Scorecard", 
                                               ScorecardType.REVIEW, 10, 100, 30003114),
    
    DEFAULT_MILESTONE_SCREENING_SCORECARD(30000416, "Default Milestone Screening Scorecard",
                                          ScorecardType.MILESTONE_SCREENING, 100, 100, 30003112),
    
        
    DEFAULT_MILESTONE_REVIEW_SCORECARD(30000417, "Default Milestone Review Scorecard",
                                       ScorecardType.MILESTONE_REVIEW, 10, 100, 30003113);
    

    /**
     * <p>A <code>long</code> providing the ID for the scorecard.</p>
     */
    private long scorecardId;

    /**
     * <p>A <code>String</code> providing the name of the scorecard.</p>
     */
    private String name;

    /**
     * <p>A <code>ScorecardType</code> providing the type of this scorecard.</p>
     */
    private ScorecardType type;

    /**
     * <p>A <code>double</code> providing the minimum score for scorecard.</p>
     */
    private double minScore;

    /**
     * <p>A <code>double</code> providing the maximum score for scorecard.</p>
     */
    private double maxScore;

    /**
     * <p>A <code>long</code> providing the ID of a scorecard question.</p>
     */
    private long scorecardQuestionId;

    /**
     * <p>Constructs new <code>Scorecard</code> instance.</p>
     * 
     * @param scorecardId a <code>long</code> providing the ID for the scorecard.
     * @param name a <code>String</code> providing the name of the scorecard.
     * @param type a <code>ScorecardType</code> providing the type of this scorecard.
     * @param minScore a <code>double</code> providing the minimum score for scorecard.
     * @param maxScore a <code>double</code> providing the maximum score for scorecard.
     * @param scorecardQuestionId a <code>long</code> providing the scorecard question ID.
     */
    private Scorecard(long scorecardId, String name, ScorecardType type, double minScore, double maxScore, 
                      long scorecardQuestionId) {
        this.scorecardId = scorecardId;
        this.name = name;
        this.type = type;
        this.minScore = minScore;
        this.maxScore = maxScore;
        this.scorecardQuestionId = scorecardQuestionId;
    }

    /**
     * <p>Gets the maximum score for scorecard.</p>
     *
     * @return a <code>double</code> providing the maximum score for scorecard.
     */
    public double getMaxScore() {
        return this.maxScore;
    }

    /**
     * <p>Gets the minimum score for scorecard.</p>
     *
     * @return a <code>double</code> providing the minimum score for scorecard.
     */
    public double getMinScore() {
        return this.minScore;
    }

    /**
     * <p>Gets the type of this scorecard.</p>
     *
     * @return a <code>ScorecardType</code> providing the type of this scorecard.
     */
    public ScorecardType getType() {
        return this.type;
    }

    /**
     * <p>Gets the ID for the scorecard.</p>
     *
     * @return a <code>long</code> providing the ID for the scorecard.
     */
    public long getScorecardId() {
        return this.scorecardId;
    }

    /**
     * <p>Gets the name of the scorecard.</p>
     *
     * @return a <code>String</code> providing the name of the scorecard.
     */
    public String getName() {
        return this.name;
    }

    /**
     * <p>Gets the ID of a scorecard question.</p>
     *
     * @return a <code>long</code> providing the ID of a scorecard question.
     */
    public long getScorecardQuestionId() {
        return this.scorecardQuestionId;
    }
}
