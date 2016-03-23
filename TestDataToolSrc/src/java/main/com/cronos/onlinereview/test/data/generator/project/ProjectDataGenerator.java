/*
 * Copyright (C) 2011-2012 TopCoder Inc., All Rights Reserved.
 */
package com.cronos.onlinereview.test.data.generator.project;

import com.cronos.onlinereview.test.data.ProjectPhaseTemplate;
import com.cronos.onlinereview.test.data.TopCoderUser;
import com.cronos.onlinereview.test.data.User;
import com.cronos.onlinereview.test.data.corporateoltp.TcDirectProject;
import com.cronos.onlinereview.test.data.generator.IdGenerator;
import com.cronos.onlinereview.test.data.informixoltp.Payment;
import com.cronos.onlinereview.test.data.informixoltp.PaymentStatus;
import com.cronos.onlinereview.test.data.informixoltp.PaymentType;
import com.cronos.onlinereview.test.data.tcscatalog.Catalog;
import com.cronos.onlinereview.test.data.tcscatalog.CommentType;
import com.cronos.onlinereview.test.data.tcscatalog.Component;
import com.cronos.onlinereview.test.data.tcscatalog.ComponentVersion;
import com.cronos.onlinereview.test.data.tcscatalog.ContestSale;
import com.cronos.onlinereview.test.data.tcscatalog.CostLevel;
import com.cronos.onlinereview.test.data.tcscatalog.FileType;
import com.cronos.onlinereview.test.data.tcscatalog.Phase;
import com.cronos.onlinereview.test.data.tcscatalog.PhaseCriteriaType;
import com.cronos.onlinereview.test.data.tcscatalog.PhaseStatus;
import com.cronos.onlinereview.test.data.tcscatalog.PhaseType;
import com.cronos.onlinereview.test.data.tcscatalog.Prize;
import com.cronos.onlinereview.test.data.tcscatalog.PrizeType;
import com.cronos.onlinereview.test.data.tcscatalog.Project;
import com.cronos.onlinereview.test.data.tcscatalog.ProjectCategory;
import com.cronos.onlinereview.test.data.tcscatalog.ProjectInfoType;
import com.cronos.onlinereview.test.data.tcscatalog.ProjectSpec;
import com.cronos.onlinereview.test.data.tcscatalog.ProjectStatus;
import com.cronos.onlinereview.test.data.tcscatalog.ProjectType;
import com.cronos.onlinereview.test.data.tcscatalog.Resource;
import com.cronos.onlinereview.test.data.tcscatalog.ResourceRole;
import com.cronos.onlinereview.test.data.tcscatalog.Review;
import com.cronos.onlinereview.test.data.tcscatalog.ReviewComment;
import com.cronos.onlinereview.test.data.tcscatalog.ReviewItem;
import com.cronos.onlinereview.test.data.tcscatalog.ReviewItemComment;
import com.cronos.onlinereview.test.data.tcscatalog.SaleStatus;
import com.cronos.onlinereview.test.data.tcscatalog.SaleType;
import com.cronos.onlinereview.test.data.tcscatalog.Scorecard;
import com.cronos.onlinereview.test.data.tcscatalog.ScreeningStatus;
import com.cronos.onlinereview.test.data.tcscatalog.ScreeningTask;
import com.cronos.onlinereview.test.data.tcscatalog.StudioProjectConfig;
import com.cronos.onlinereview.test.data.tcscatalog.Submission;
import com.cronos.onlinereview.test.data.tcscatalog.SubmissionDeclaration;
import com.cronos.onlinereview.test.data.tcscatalog.SubmissionStatus;
import com.cronos.onlinereview.test.data.tcscatalog.SubmissionType;
import com.cronos.onlinereview.test.data.tcscatalog.TechnologyType;
import com.cronos.onlinereview.test.data.tcscatalog.Upload;
import com.cronos.onlinereview.test.data.tcscatalog.UploadStatus;
import com.cronos.onlinereview.test.data.tcscatalog.UploadType;
import com.cronos.onlinereview.test.data.timeoltp.BillingProject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * <p>Generator for single project data.</p>
 * 
 * <p>
 * Version 1.1 Change notes:
 *   <ol>
 *     <li>Updated {@link #generateProject()} method to set completion timestamps for completed, deleted and cancelled
 *     contests.</li>
 *   </ol>
 * </p>
 *
 * <p>
 * Version 1.2 (Release Assembly - TopCoder System Test Data Generator Update 1 Assembly 1.0) Change notes:
 *   <ol>
 *     <li>Added the logic for generating SQL statements for Studio contests and milestone phases.</li>
 *   </ol>
 * </p>
 *
 * <p>
 * Version 1.3 (Release Assembly - TopCoder System Test Data Generator Update 2 Assembly 1.0) Change notes:
 *   <ol>
 *     <li>Updated {@link #generateProject()} and 
 *     {@link #generateReviews(ProjectStatus, Phase, List, Resource, ProjectCategory, long)} methods to set phase IDs 
 *     for generated uploads. (1.1.4#3)</li>
 *     <li>Added {@link #createResource(TopCoderUser, ResourceRole, long, Phase, List)} method. (1.1.1.2)</li>
 *     <li>Updated {@link #generateProject()} method to generate submissions for registrants only if respective flag is
 *     set.</li>
 *   </ol>
 * </p>
 * 
 * @author isv
 * @version 1.3
 */
public class ProjectDataGenerator {

    /**
     * <p>A <code>Random</code> to be used for generating random numbers.</p>
     */
    private final Random random = new Random();

    /**
     * <p>A <code>ProjectGeneratorConfig</code> providing the parameters to be used for generating the test data for 
     * project.</p>
     */
    private final ProjectGeneratorConfig config;

    /**
     * <p>Constructs new <code>ProjectDataGenerator</code> instance with specified configuration for single project to
     * be generated.</p>
     * 
     * @param config a <code>ProjectGeneratorConfig</code> providing the parameters to be used for generating the test
     *        data for project.
     */
    public ProjectDataGenerator(ProjectGeneratorConfig config) {
        this.config = config;
    }

    /**
     * <p>Generates a single project based on configuration data passed at construction.</p>
     * 
     * @return a <code>Project</code> providing the data for generated project. 
     */
    public Project generateProject() {
        long projectId = this.config.getProjectId();
        TcDirectProject tcDirectProject = this.config.getTcDirectProject();
        ProjectCategory projectCategory = this.config.getProjectCategory();
        ProjectStatus projectStatus = this.config.getProjectStatus();
        Catalog catalog = this.config.getCatalog();
        ProjectPhaseTemplate phasesTemplate = this.config.getPhasesTemplate();
        
        // Basic project data
        Project project = new Project();
        project.setProjectId(projectId);
        if (tcDirectProject != null) {
            project.setTcDirectProjectId(tcDirectProject.getTcDirectProjectId());
        }
        project.setCatalog(catalog);
        project.setProjectCategory(projectCategory);
        project.setProjectStatus(projectStatus);
        if (tcDirectProject != null) {
            project.setProjectName(tcDirectProject.getName() + " " + projectCategory.getName() + " Contest "
                                   + this.config.getProjectCountGenerator().getNextId());
        } else {
            project.setProjectName(projectCategory.getName() + " Contest " 
                                   + this.config.getProjectCountGenerator().getNextId());
        }
        project.setProjectVersion("1.0");
        project.setSvnModule("");
        project.setNotes("");
        
        // Studio-specific properties
        boolean isStudio = projectCategory.getProjectType() == ProjectType.STUDIO;
        if (isStudio) {
            // File Types
            project.setFileTypes(getRandomItems(FileType.values(), getRandomInt(1, 5)));

            // Studio specification
            StudioProjectConfig studioSpec = new StudioProjectConfig();
            studioSpec.setStudioSpecificationId(this.config.getStudioSpecificationIdGenerator().getNextId());
            studioSpec.setGoals("");
            studioSpec.setTargetAudience("");
            studioSpec.setBrandingGuidelines("");
            studioSpec.setDislikedDesignWebsites("");
            studioSpec.setOtherInstructions("");
            studioSpec.setWinningCriteria("");
            studioSpec.setSubmittersLockedBetweenRounds(false);
            if (phasesTemplate.contains(PhaseType.MILESTONE_SUBMISSION)) {
                studioSpec.setRound1Introduction("<p>Round 1 introduction for " + project.getProjectName() + "</p>");
                studioSpec.setRound2Introduction("<p>Round 2 introduction for " + project.getProjectName() + "</p>");
            } else {
                studioSpec.setRound1Introduction("");
                studioSpec.setRound2Introduction("");
            }
            studioSpec.setColors("");
            studioSpec.setFonts("");
            studioSpec.setLayoutAndSize("");
            studioSpec.setContestIntroduction("<p>Contest Introduction for " + project.getProjectName() + "</p>");
            studioSpec.setContestDescription("<p>Contest Description for " + project.getProjectName() + "</p>");
            studioSpec.setViewableSubmissions(true);
            studioSpec.setAllowStockArt(true);
            studioSpec.setMaximumSubmissions(5);
            project.setStudioSpecification(studioSpec);
        } else {
            // Project specification
            ProjectSpec projectSpec = new ProjectSpec();
            projectSpec.setProjectSpecId(this.config.getProjectSpecIdGenerator().getNextId());
            projectSpec.setProjectId(projectId);
            projectSpec.setVersion(1);
            projectSpec.setDetailedRequirements("Detailed requirements for " + project.getProjectName());
            projectSpec.setSubmissionDeliverables("");
            projectSpec.setEnvironmentSetupInstructions("");
            projectSpec.setFinalSubmissionGuidelines("Final submission guidelines for " + project.getProjectName());
            projectSpec.setPrivateDescription("");
            project.setProjectSpec(projectSpec);
        }
        
        // Project info flag properties 
        ProjectInfoType[] flagsToBeSet = this.config.getFlagsToBeSet();
        for (int i = 0; i < flagsToBeSet.length; i++) {
            ProjectInfoType projectInfoType = flagsToBeSet[i];
            if (ProjectInfoType.CONFIDENTIALITY_TYPE == projectInfoType) {
                project.setConfidentialityType(projectInfoType.getTrueValue());
                project.setCcaRequired(true);
            } else if (ProjectInfoType.RATED == projectInfoType) {
                project.setRated(true);
            } else if (ProjectInfoType.PUBLIC == projectInfoType) {
                project.setVisible(true);
            } else if (ProjectInfoType.AUTOPILOT_OPTION == projectInfoType) {
                project.setAutoPilotOption(true);
            } else if (ProjectInfoType.TIMELINE_NOTIFICATION == projectInfoType) {
                project.setTimelineNotificationOption(true);
            } else if (ProjectInfoType.STATUS_NOTIFICATION == projectInfoType) {
                project.setStatusNotificationOption(true);
            } else if (ProjectInfoType.DIGITAL_RUN_FLAG == projectInfoType) {
                project.setDigitalRunEnabled(true);
            } else if (ProjectInfoType.PHASE_DEPENDENCIES_EDITABLE == projectInfoType) {
                project.setPhaseDependenciesEditable(true);
            } else if (ProjectInfoType.RELIABILITY_BONUS_ELIGIBLE == projectInfoType) {
                project.setReliabilityBonusEligible(true);
            } else if (ProjectInfoType.APPROVAL_REQUIRED == projectInfoType) {
                project.setApprovalRequired(true);
            } else if (ProjectInfoType.POST_MORTEM_REQUIRED == projectInfoType) {
                project.setPostMortemRequired(true);
            } else if (ProjectInfoType.SEND_WINNER_EMAILS == projectInfoType) {
                project.setSendWinnerEmails(true);
            } else if (ProjectInfoType.MEMBER_PAYMENTS_ELIGIBLE == projectInfoType) {
                project.setMemberPaymentsEligible(true);
            } else if (ProjectInfoType.TRACK_LATE_DELIVERABLES == projectInfoType) {
                project.setTrackLateDeliverables(true);
            } else if (ProjectInfoType.ALLOW_STOCK_ART == projectInfoType) {
                project.getStudioSpecification().setAllowStockArt(true);
            } else if (ProjectInfoType.VIEWABLE_SUBMISSIONS_FLAG == projectInfoType) {
                project.getStudioSpecification().setViewableSubmissions(true);
            } else if (ProjectInfoType.VIEWABLE_SUBMITTERS == projectInfoType) {
                project.getStudioSpecification().setViewableSubmitters(true);
            }
        }
        ProjectInfoType[] flagsToBeUnSet = this.config.getFlagsToBeUnSet();
        for (int i = 0; i < flagsToBeUnSet.length; i++) {
            ProjectInfoType projectInfoType = flagsToBeUnSet[i];
            if (ProjectInfoType.CONFIDENTIALITY_TYPE == projectInfoType) {
                project.setConfidentialityType(projectInfoType.getFalseValue());
                project.setCcaRequired(false);
            } else if (ProjectInfoType.RATED == projectInfoType) {
                project.setRated(false);
            } else if (ProjectInfoType.PUBLIC == projectInfoType) {
                project.setVisible(false);
            } else if (ProjectInfoType.AUTOPILOT_OPTION == projectInfoType) {
                project.setAutoPilotOption(false);
            } else if (ProjectInfoType.TIMELINE_NOTIFICATION == projectInfoType) {
                project.setTimelineNotificationOption(false);
            } else if (ProjectInfoType.STATUS_NOTIFICATION == projectInfoType) {
                project.setStatusNotificationOption(false);
            } else if (ProjectInfoType.DIGITAL_RUN_FLAG == projectInfoType) {
                project.setDigitalRunEnabled(false);
            } else if (ProjectInfoType.PHASE_DEPENDENCIES_EDITABLE == projectInfoType) {
                project.setPhaseDependenciesEditable(false);
            } else if (ProjectInfoType.RELIABILITY_BONUS_ELIGIBLE == projectInfoType) {
                project.setReliabilityBonusEligible(false);
            } else if (ProjectInfoType.APPROVAL_REQUIRED == projectInfoType) {
                project.setApprovalRequired(false);
            } else if (ProjectInfoType.POST_MORTEM_REQUIRED == projectInfoType) {
                project.setPostMortemRequired(false);
            } else if (ProjectInfoType.SEND_WINNER_EMAILS == projectInfoType) {
                project.setSendWinnerEmails(false);
            } else if (ProjectInfoType.MEMBER_PAYMENTS_ELIGIBLE == projectInfoType) {
                project.setMemberPaymentsEligible(false);
            } else if (ProjectInfoType.TRACK_LATE_DELIVERABLES == projectInfoType) {
                project.setTrackLateDeliverables(false);
            } else if (ProjectInfoType.ALLOW_STOCK_ART == projectInfoType) {
                project.getStudioSpecification().setAllowStockArt(false);
            } else if (ProjectInfoType.VIEWABLE_SUBMISSIONS_FLAG == projectInfoType) {
                project.getStudioSpecification().setViewableSubmissions(false);
            } else if (ProjectInfoType.VIEWABLE_SUBMITTERS == projectInfoType) {
                project.getStudioSpecification().setViewableSubmitters(false);
            }
        }

        // Billing account
        BillingProject billingProject = this.config.getBillingProject();
        if (billingProject != null) {
            project.setBillingProjectId(billingProject.getBillingProjectId());
        }

        // Cost and prizes
        Prize milestoneTypePrize = null;
        List<Prize> contestTypePrizes = new ArrayList<Prize>();
            
        boolean costSet = false;
        CostLevel costLevel = this.config.getCostLevel();
        if (costLevel != null) {
            project.setCostLevel(costLevel.getCode());
            project.setAdminFee(costLevel.getAdminFee());
            project.setReviewCost(costLevel.getReviewCost());
            project.setSpecReviewCost(costLevel.getSpecReviewFee());
            project.setFirstPlaceCost(costLevel.getFirstPlaceCost());
            project.setSecondPlaceCost(costLevel.getSecondPlaceCost());
            if (project.isReliabilityBonusEligible()) {
                project.setReliabilityBonusCost(costLevel.getReliabilityBonus());
            }
            if (project.isDigitalRunEnabled()) {
                project.setDigitalRunPoints(costLevel.getDigitalRunPoints());
            }
            project.setMilestoneBonusCost(0D);
            project.setPrice(costLevel.getFirstPlaceCost());
            costSet = true;

            contestTypePrizes.add(createPrize(project.getFirstPlaceCost(), 1, 1, PrizeType.CONTEST_PRIZE));
            contestTypePrizes.add(createPrize(project.getSecondPlaceCost(), 1, 2, PrizeType.CONTEST_PRIZE));
        } else {
            if (isStudio) {
                int numberOfPrizes = getRandomInt(2, 5);
                for (int i = 1; i <= numberOfPrizes; i++) {
                    Prize prize = createPrize(2000 - i * 300, 1, i, PrizeType.CONTEST_PRIZE);
                    contestTypePrizes.add(prize);
                    if (i == 1) {
                        project.setPrice(prize.getAmount());
                    }
                }

                project.setAdminFee(375D);
                project.setReviewCost(200D);
                project.setSpecReviewCost(150D);
                project.setFirstPlaceCost(contestTypePrizes.get(0).getAmount());
                project.setSecondPlaceCost(contestTypePrizes.get(1).getAmount());
                if (project.isDigitalRunEnabled()) {
                    project.setDigitalRunPoints(project.getFirstPlaceCost() * 0.30);
                }
                project.setPrice(project.getFirstPlaceCost());
                costSet = true;
            }
        }

        List<Prize> contestPrizes = new ArrayList<Prize>();
        project.setPrizes(contestPrizes);
        contestPrizes.addAll(contestTypePrizes);
        if (phasesTemplate.contains(PhaseType.MILESTONE_SUBMISSION)) {
            milestoneTypePrize = createPrize(getRandomInt(50, 100), getRandomInt(1, 5), 1, PrizeType.MILESTONE_PRIZE);
            contestPrizes.add(milestoneTypePrize);
        }
        
        // Contest sale
        if (costSet) {
            if ((projectStatus != ProjectStatus.DELETED) && (projectStatus != ProjectStatus.INACTIVE)) {
                double salePrice = project.getAdminFee() + project.getFirstPlaceCost() + project.getSecondPlaceCost()
                                   + project.getReviewCost() + project.getSpecReviewCost()
                                   + (project.getMilestoneBonusCost() == null ? 0 : project.getMilestoneBonusCost());
                if (project.isReliabilityBonusEligible() && project.getReliabilityBonusCost() != null) {
                    salePrice += project.getReliabilityBonusCost();
                }
                if (project.isDigitalRunEnabled()) {
                    salePrice += project.getDigitalRunPoints();
                }
                
                ContestSale contestSale = new ContestSale();
                contestSale.setPrice(salePrice);
                contestSale.setStatus(SaleStatus.PAID);
                contestSale.setType(SaleType.TC_PURCHASE_ORDER);
                contestSale.setContestId(projectId);
                contestSale.setContestSaleId(this.config.getContestSaleIdGenerator().getNextId());
                contestSale.setPaypalOrderId(billingProject.getPoBoxNumber());
                contestSale.setSaleReferenceId(billingProject.getPoBoxNumber());
                
                project.setContestSale(contestSale);
            }
        }

        // Forum ID
        Long forumId = this.config.getForumId();
        if (forumId != null) {
            project.setForumId(forumId);
        }
        
        // Component version
        List<ComponentVersion> componentVersions = new ArrayList<ComponentVersion>();
        ComponentVersion version = new ComponentVersion();
        version.setComments("Comments for version #1");
        version.setPhaseId(projectCategory.getProjectCategoryId() + 111);
        version.setPrice(project.getPrice());
        version.setSuspended(false);
        version.setVersionId(this.config.getComponentVersionIdGenerator().getNextId());
        version.setVersionNumber(1);
        version.setVersionText("1.0");
        componentVersions.add(version);
        
        // Technologies
        TechnologyType[] allTechnologyTypes = TechnologyType.values();
        if (projectCategory.getTechnologyRequired()) {
            int technologiesCount = getRandomInt(1, 5);
            List<TechnologyType> technologyTypes = getRandomItems(allTechnologyTypes, technologiesCount); 
            version.setTechnologies(technologyTypes.toArray(new TechnologyType[technologyTypes.size()]));
        }

        // Component
        Component component = new Component();
        component.setCatalogId(this.config.getCatalog().getCatalogId());
        component.setComponentId(this.config.getComponentIdGenerator().getNextId());
        component.setComponentName(project.getProjectName());
        component.setCurrentVersionId(version.getVersionNumber());
        component.setDescription("Description for " + component.getComponentName());
        component.setFunctionalDesc("Functional description for " + component.getComponentName());
        component.setRootCategoryId(catalog.getRootCategoryId());
        component.setShortDesc("Short description for " + component.getComponentName());
        if (projectStatus == ProjectStatus.DELETED) {
            component.setStatusId(0);
        } else {
            component.setStatusId(1);
        }
        
        component.setVersions(componentVersions);
        component.setVisible(project.isVisible());
        project.setComponent(component);
        
        // Project phases
        PhaseType currentOpenPhaseType = this.config.getCurrentPhaseType();
        IdGenerator phaseIdGenerator = this.config.getPhaseIdGenerator();
        List<Phase> phases = new ArrayList<Phase>();

        Phase specSubmissionPhase = null;
        if (phasesTemplate.contains(PhaseType.SPECIFICATION_SUBMISSION)) {
            if (projectCategory.getSpecReviewRespId() > 0) {
                specSubmissionPhase = createPhase(project, PhaseType.SPECIFICATION_SUBMISSION, currentOpenPhaseType,
                                                  phases, phaseIdGenerator.getNextId(), 
                                                  phasesTemplate.getItem(PhaseType.SPECIFICATION_SUBMISSION).getDuration());
            }
        }

        Phase specReviewPhase = null;
        if (phasesTemplate.contains(PhaseType.SPECIFICATION_REVIEW)) {
            if ((projectCategory.getSpecReviewRespId() > 0)) {
                specReviewPhase = createPhase(project, PhaseType.SPECIFICATION_REVIEW, currentOpenPhaseType, phases,
                                              phaseIdGenerator.getNextId(), 
                                              phasesTemplate.getItem(PhaseType.SPECIFICATION_REVIEW).getDuration());
            }
        }

        Phase registrationPhase = null;
        if (phasesTemplate.contains(PhaseType.REGISTRATION)) {
            registrationPhase = createPhase(project, PhaseType.REGISTRATION, currentOpenPhaseType, phases,
                                            phaseIdGenerator.getNextId(),
                                            phasesTemplate.getItem(PhaseType.REGISTRATION).getDuration());
            registrationPhase.setFixedStartTimeOffset(registrationPhase.getScheduledStartTimeOffset());
        }

        Phase milestoneSubmissionPhase = null;
        if (phasesTemplate.contains(PhaseType.MILESTONE_SUBMISSION)) {
            milestoneSubmissionPhase = createPhase(project, PhaseType.MILESTONE_SUBMISSION, currentOpenPhaseType, 
                                                   phases, phaseIdGenerator.getNextId(),
                                                   phasesTemplate.getItem(PhaseType.MILESTONE_SUBMISSION).getDuration());
        }

        Phase milestoneScreeningPhase = null;
        if (phasesTemplate.contains(PhaseType.MILESTONE_SCREENING)) {
            milestoneScreeningPhase = createPhase(project, PhaseType.MILESTONE_SCREENING, currentOpenPhaseType, phases,
                                                  phaseIdGenerator.getNextId(),
                                                  phasesTemplate.getItem(PhaseType.MILESTONE_SCREENING).getDuration());
        }

        Phase milestoneReviewPhase = null;
        if (phasesTemplate.contains(PhaseType.MILESTONE_REVIEW)) {
            milestoneReviewPhase = createPhase(project, PhaseType.MILESTONE_REVIEW, currentOpenPhaseType, phases,
                                               phaseIdGenerator.getNextId(),
                                               phasesTemplate.getItem(PhaseType.MILESTONE_REVIEW).getDuration());
        }

        Phase submissionPhase = null;
        if (phasesTemplate.contains(PhaseType.SUBMISSION)) {
            submissionPhase = createPhase(project, PhaseType.SUBMISSION, currentOpenPhaseType, phases,
                                          phaseIdGenerator.getNextId(),
                                          phasesTemplate.getItem(PhaseType.SUBMISSION).getDuration());
        }

        Phase screeningPhase = null;
        if (phasesTemplate.contains(PhaseType.SCREENING)) {
            screeningPhase = createPhase(project, PhaseType.SCREENING, currentOpenPhaseType, phases,
                                         phaseIdGenerator.getNextId(), 
                                         phasesTemplate.getItem(PhaseType.SCREENING).getDuration());
        }

        Phase reviewPhase = null;
        if (phasesTemplate.contains(PhaseType.REVIEW)) {
            reviewPhase = createPhase(project, PhaseType.REVIEW, currentOpenPhaseType, phases,
                                      phaseIdGenerator.getNextId(), 
                                      phasesTemplate.getItem(PhaseType.REVIEW).getDuration());
        }

        if (phasesTemplate.contains(PhaseType.APPEALS)) {
            createPhase(project, PhaseType.APPEALS, currentOpenPhaseType, phases, phaseIdGenerator.getNextId(),
                        phasesTemplate.getItem(PhaseType.APPEALS).getDuration());
        }

        Phase appealsResponsePhase = null;
        if (phasesTemplate.contains(PhaseType.APPEALS_RESPONSE)) {
            appealsResponsePhase = createPhase(project, PhaseType.APPEALS_RESPONSE, currentOpenPhaseType,
                                               phases, phaseIdGenerator.getNextId(), 
                                               phasesTemplate.getItem(PhaseType.APPEALS_RESPONSE).getDuration());
        }

        Phase aggregationPhase = null;
        if (phasesTemplate.contains(PhaseType.AGGREGATION)) {
            aggregationPhase = createPhase(project, PhaseType.AGGREGATION, currentOpenPhaseType, phases,
                                           phaseIdGenerator.getNextId(), 
                                           phasesTemplate.getItem(PhaseType.AGGREGATION).getDuration());
        }

        Phase aggregationReviewPhase = null;
        if (phasesTemplate.contains(PhaseType.AGGREGATION_REVIEW)) {
            aggregationReviewPhase = createPhase(project, PhaseType.AGGREGATION_REVIEW, currentOpenPhaseType,
                                                 phases, phaseIdGenerator.getNextId(), 
                                                 phasesTemplate.getItem(PhaseType.AGGREGATION_REVIEW).getDuration());
        }

        Phase finalFixPhase = null;
        if (phasesTemplate.contains(PhaseType.FINAL_FIX)) {
            finalFixPhase = createPhase(project, PhaseType.FINAL_FIX, currentOpenPhaseType, phases,
                                        phaseIdGenerator.getNextId(), 
                                        phasesTemplate.getItem(PhaseType.FINAL_FIX).getDuration());
        }

        Phase finalReviewPhase = null;
        if (phasesTemplate.contains(PhaseType.FINAL_REVIEW)) {
            finalReviewPhase = createPhase(project, PhaseType.FINAL_REVIEW, currentOpenPhaseType, phases,
                                           phaseIdGenerator.getNextId(), 
                                           phasesTemplate.getItem(PhaseType.FINAL_REVIEW).getDuration());
        }

        Phase approvalPhase = null;
        if (phasesTemplate.contains(PhaseType.APPROVAL)) {
            approvalPhase = createPhase(project, PhaseType.APPROVAL, currentOpenPhaseType, phases,
                                        phaseIdGenerator.getNextId(), 
                                        phasesTemplate.getItem(PhaseType.APPROVAL).getDuration());
        }

        project.setPhases(phases.toArray(new Phase[phases.size()]));

        // Project resources
        List<Resource> resources = new ArrayList<Resource>();
        project.setResources(resources);
        IdGenerator resourceIdGenerator = this.config.getResourceIdGenerator();

        // Managers
        User[] managerUsers = this.config.getManagers();
        if (managerUsers != null) {
            for (User manager : managerUsers) {
                createResource(manager, ResourceRole.MANAGER, resourceIdGenerator.getNextId(), null, resources);
            }
        }
        
        // Specification Submitters
        List<Resource> specificationSubmitters = new ArrayList<Resource>();
        if ((specSubmissionPhase != null) && !(specSubmissionPhase.isScheduled())) {
            User[] specificationSubmitterUsers = this.config.getSpecificationSubmitters();
            if (specificationSubmitterUsers != null) {
                for (int i = 0; i < specificationSubmitterUsers.length; i++) {
                    User specificationSubmitterUser = specificationSubmitterUsers[i];
                    Resource specSubmitter = createResource(specificationSubmitterUser, 
                                                            ResourceRole.SPECIFICATION_SUBMITTER, 
                                                            resourceIdGenerator.getNextId(), null, resources);
                    specificationSubmitters.add(specSubmitter);
                    if (specSubmissionPhase.isOpen() || specSubmissionPhase.isClosed()) {
                        Upload upload = new Upload();
                        upload.setUploadId(this.config.getUploadIdGenerator().getNextId());
                        upload.setProjectId(projectId);
                        upload.setResourceId(specSubmitter.getResourceId());
                        upload.setStatus(UploadStatus.ACTIVE);
                        upload.setType(UploadType.SUBMISSION);
                        upload.setParameter(projectId + "_" + specSubmitter.getResourceId() + "_" + upload.getUploadId()
                                            + ".zip");
                        upload.setCreationTimestamp(specSubmitter.getRegistrationDate());
                        upload.setProjectPhaseId(specSubmissionPhase.getPhaseId());
                        
                        Submission specSubmission = new Submission();
                        if (isStudio) {
                            specSubmission.setSubmissionId(this.config.getStudioSpecificationIdGenerator().getNextId());
                        } else {
                            specSubmission.setSubmissionId(this.config.getSubmissionIdGenerator().getNextId());
                        }
                        specSubmission.setStatus(SubmissionStatus.ACTIVE);
                        specSubmission.setType(SubmissionType.SPECIFICATION_SUBMISSION);
                        specSubmission.setUpload(upload);

                        specSubmitter.setSubmissions(new Submission[] {specSubmission});
                    }
                }
            }

            // Specification Reviewer
            User specificationReviewerUser = this.config.getSpecificationReviewer();
            if (specificationReviewerUser != null) {
                Resource specReviewer = createResource(specificationReviewerUser,
                                                       ResourceRole.SPECIFICATION_REVIEWER,
                                                       resourceIdGenerator.getNextId(), specReviewPhase, resources);
//                String[] sql = new String[1];
//                sql[0] = "INSERT INTO rboard_application (user_id, project_id, phase_id, review_resp_id, primary_ind, "
//                         + "create_date, modify_date) VALUES (" + specificationReviewerUser.getUserId() + ", "
//                         + projectId + ", " + (1000 + 111 + projectCategory.getProjectCategoryId()) + ", "
//                         + projectCategory.getSpecReviewRespId() + ", 0, CURRENT, CURRENT);";
//                specReviewer.setSql(sql);
                
                if (specReviewPhase.isOpen() || specReviewPhase.isClosed()) {
                    List<Review> specificationReviews = new ArrayList<Review>();
                    for (Resource specificationSubmitter : specificationSubmitters) {
                        Submission[] specificationSubmissions = specificationSubmitter.getSubmissions();
                        if (specificationSubmissions != null) {
                            for (Submission specificationSubmission : specificationSubmissions) {
                                int score = getRandomInt(3, 4);
                                Review specReview = new Review();
                                specReview.setReviewId(this.config.getReviewIdGenerator().getNextId());
                                specReview.setResourceId(specReviewer.getResourceId());
                                specReview.setSubmissionId(specificationSubmission.getSubmissionId());
                                specReview.setScorecardId(specReviewPhase.getReviewScorecard().getScorecardId());
                                specReview.setCommitted(specReviewPhase.isClosed());
                                if (specReviewPhase.isClosed()) {
                                    specReview.setInitialScore(score / 4D * 100);
                                    specReview.setScore(specReview.getInitialScore());
                                }
                                specificationReviews.add(specReview);

                                ReviewComment reviewComment = new ReviewComment();
                                reviewComment.setReviewCommentId(this.config.getReviewCommentIdGenerator().getNextId());
                                reviewComment.setReviewId(specReview.getReviewId());
                                reviewComment.setResourceId(specReviewer.getResourceId());
                                reviewComment.setContent("");
                                reviewComment.setExtraInfo("Approved");
                                reviewComment.setType(CommentType.SPECIFICATION_REVIEW_COMMENT);
                                reviewComment.setSortOrder(0);
                                specReview.setComments(new ReviewComment[] {reviewComment});

                                ReviewItem reviewItem = new ReviewItem();
                                reviewItem.setReviewItemId(this.config.getReviewItemIdGenerator().getNextId());
                                reviewItem.setReviewId(specReview.getReviewId());
                                reviewItem.setAnswer(score + "/4");
                                reviewItem.setScorecardQuestionId(
                                    specReviewPhase.getReviewScorecard().getScorecardQuestionId());
                                reviewItem.setSortOrder(0);
                                specReview.setItems(new ReviewItem[] {reviewItem});
                                
                                ReviewItemComment reviewItemComment = new ReviewItemComment();
                                reviewItemComment.setReviewItemCommentId(
                                    this.config.getReviewItemCommentIdGenerator().getNextId());
                                reviewItemComment.setResourceId(specReviewer.getResourceId());
                                reviewItemComment.setReviewItemId(reviewItem.getReviewItemId());
                                reviewItemComment.setType(CommentType.COMMENT);
                                reviewItemComment.setContent("Ok");
                                reviewItemComment.setSortOrder(0);
                                reviewItem.setComments(new ReviewItemComment[] {reviewItemComment});
                            }
                        }
                    }
                    specReviewer.setReviews(specificationReviews.toArray(new Review[specificationReviews.size()]));
                }
            }
        }

        // Submitters
        List<Resource> contestSubmitters = new ArrayList<Resource>();
        TopCoderUser[] submitterUsers = this.config.getSubmitters();
        if ((submitterUsers != null) && !(registrationPhase.isScheduled()) 
            && (projectStatus != ProjectStatus.CANCELLED_ZERO_REGISTRATIONS)) {
            for (int i = 0; i < submitterUsers.length; i++) {
                TopCoderUser submitterUser = submitterUsers[i];
                if (!submitterUser.getRequiresSubmission(projectId) 
                    && !submitterUser.getRequiresMilestoneSubmission()) {
                    continue;
                }
                Resource submitter = createResource(submitterUser, ResourceRole.SUBMITTER, 
                                                   resourceIdGenerator.getNextId(), null, resources);
                boolean isReliabilityAvailable = getRandomInt(0, 1) == 1;
                if (isReliabilityAvailable) {
                    submitter.setReliability(getRandomInt(0, 100));
                }
                if (projectCategory.getRated()) {
                    if (submitter.getReliability() != null) {
                        submitter.setRating(getRandomInt(100, 3000));
                    }
                }
                submitter.setAppealsCompletedEarly(false);
                
                List<String> sql = new ArrayList<String>();
                sql.add("INSERT INTO component_inquiry (component_inquiry_id, component_id, user_id, comment, " +
                        "agreed_to_terms, rating, phase, tc_user_id, version, create_time, project_id) " +
                        "VALUES (" + this.config.getCompInquiryIdGenerator().getNextId() + ", " + version.getVersionId() 
                        + ", " + submitterUser.getUserId() + ", null, 1, " 
                        + (submitter.getRating() == null ? 0 : submitter.getRating()) + ", " 
                        + (projectCategory.getProjectCategoryId() + 111) + ", " + submitterUser.getUserId() + ", 0, "
                        + "CURRENT, " + projectId + ");");
                sql.add("INSERT INTO project_result (user_id, project_id, old_rating, new_rating, raw_score, " +
                        "final_score, payment, placed, rating_ind, valid_submission_ind, create_date, modify_date, " +
                        "passed_review_ind, point_adjustment, rating_order) " +
                        "VALUES (" + submitterUser.getUserId() + ", " + projectId + ", " + submitter.getRating() 
                        + ", null, null, null, null, null, 0, 0, CURRENT, CURRENT, null, null, null);");
                sql.add("UPDATE user_rating SET rating = " + (submitter.getRating() == null ? 0 : submitter.getRating()) 
                        + " WHERE user_id = " + submitterUser.getUserId() + " AND phase_id = " 
                        + (projectCategory.getProjectCategoryId() + 111) + ";");
                submitter.setSql(sql.toArray(new String[sql.size()]));
                contestSubmitters.add(submitter);
                
                // Submitter submissions
                if ((submissionPhase.isClosed()) 
                    && (projectStatus != ProjectStatus.CANCELLED_ZERO_SUBMISSIONS) 
                    && this.config.getSubmissionIdGenerator().isAvailable() 
                    && submitterUser.getRequiresSubmission(projectId)) {
                    Upload upload = new Upload();
                    upload.setUploadId(this.config.getUploadIdGenerator().getNextId());
                    upload.setProjectId(projectId);
                    upload.setResourceId(submitter.getResourceId());
                    upload.setStatus(UploadStatus.ACTIVE);
                    upload.setType(UploadType.SUBMISSION);
                    upload.setParameter(projectId + "_" + submitter.getResourceId() + "_" + upload.getUploadId()
                                        + ".zip");
                    upload.setCreationTimestamp(submitter.getRegistrationDate());
                    upload.setProjectPhaseId(submissionPhase.getPhaseId());

                    ScreeningTask screeningTask = new ScreeningTask();
                    screeningTask.setScreeningTaskId(this.config.getScreeningTaskIdGenerator().getNextId());
                    screeningTask.setStatus(ScreeningStatus.PENDING);
                    screeningTask.setUploadId(upload.getUploadId());

                    Submission contestSubmission = new Submission();
                    contestSubmission.setSubmissionId(this.config.getSubmissionIdGenerator().getNextId());
                    contestSubmission.setStatus(SubmissionStatus.ACTIVE);
                    contestSubmission.setType(SubmissionType.CONTEST_SUBMISSION);
                    contestSubmission.setUpload(upload);
                    contestSubmission.setScreeningTask(screeningTask);

                    if (isStudio) {
                        SubmissionDeclaration declaration = new SubmissionDeclaration();
                        declaration.setComment("Submission declaration for submission " 
                                               + contestSubmission.getSubmissionId());
                        declaration.setHasExternalContent(false);
                        declaration.setSubmissionDeclarationId(
                            this.config.getSubmissionDeclarationIdGenerator().getNextId());
                        contestSubmission.setDeclaration(declaration);
                    }

                    submitter.setSubmissions(new Submission[]{contestSubmission});
                }
                
                if ((milestoneSubmissionPhase != null) 
                    && (milestoneSubmissionPhase.isClosed())
                    && (projectStatus != ProjectStatus.CANCELLED_ZERO_SUBMISSIONS)
                    && this.config.getSubmissionIdGenerator().isAvailable() 
                    && submitterUser.getRequiresMilestoneSubmission()) {
                    Upload upload = new Upload();
                    upload.setUploadId(this.config.getUploadIdGenerator().getNextId());
                    upload.setProjectId(projectId);
                    upload.setResourceId(submitter.getResourceId());
                    upload.setStatus(UploadStatus.ACTIVE);
                    upload.setType(UploadType.SUBMISSION);
                    upload.setParameter(projectId + "_" + submitter.getResourceId() + "_" + upload.getUploadId()
                                        + ".zip");
                    upload.setCreationTimestamp(submitter.getRegistrationDate());
                    upload.setProjectPhaseId(milestoneSubmissionPhase.getPhaseId());

                    Submission milestoneSubmission = new Submission();
                    milestoneSubmission.setSubmissionId(this.config.getSubmissionIdGenerator().getNextId());
                    milestoneSubmission.setStatus(SubmissionStatus.ACTIVE);
                    milestoneSubmission.setType(SubmissionType.MILESTONE_SUBMISSION);
                    milestoneSubmission.setUpload(upload);

                    if (isStudio) {
                        SubmissionDeclaration declaration = new SubmissionDeclaration();
                        declaration.setComment("Submission declaration for submission "
                                               + milestoneSubmission.getSubmissionId());
                        declaration.setHasExternalContent(false);
                        declaration.setSubmissionDeclarationId(this.config.getSubmissionDeclarationIdGenerator().getNextId());
                        milestoneSubmission.setDeclaration(declaration);
                    }

                    submitter.setMilestoneSubmissions(new Submission[]{milestoneSubmission});
                }
            }
        }
        
        List<Resource> allReviewers = new ArrayList<Resource>();
        
        User milestoneReviewerUser = this.config.getMilestoneReviewer();

        // Milestone Screener
        if (milestoneReviewerUser != null && milestoneScreeningPhase != null) {
            if (!registrationPhase.isScheduled() || milestoneScreeningPhase.isOpen() 
                || milestoneScreeningPhase.isClosed()) {
                Resource milestoneScreener = createResource(milestoneReviewerUser, ResourceRole.MILESTONE_SCREENER,
                                                            resourceIdGenerator.getNextId(), milestoneScreeningPhase, 
                                                            resources);
                if (milestoneScreeningPhase.isOpen() || milestoneScreeningPhase.isClosed()) {
                    List<Review> milestoneScreeningReviews = new ArrayList<Review>();
                    for (Resource contestSubmitter : contestSubmitters) {
                        Submission[] milestoneSubmissions = contestSubmitter.getMilestoneSubmissions();
                        if (milestoneSubmissions != null) {
                            for (Submission milestoneSubmission : milestoneSubmissions) {
                                int score = getRandomInt(3, 4);

                                Review screeningReview = new Review();
                                screeningReview.setReviewId(this.config.getReviewIdGenerator().getNextId());
                                screeningReview.setResourceId(milestoneScreener.getResourceId());
                                screeningReview.setSubmissionId(milestoneSubmission.getSubmissionId());
                                screeningReview.setScorecardId(
                                    milestoneScreeningPhase.getReviewScorecard().getScorecardId());
                                screeningReview.setCommitted(milestoneScreeningPhase.isClosed());
                                if (milestoneScreeningPhase.isClosed()) {
                                    screeningReview.setInitialScore(score / 4D * 100);
                                    screeningReview.setScore(screeningReview.getInitialScore());
                                    milestoneSubmission.setScreeningScore(screeningReview.getInitialScore());
                                    if (screeningReview.getScore()
                                        < milestoneScreeningPhase.getReviewScorecard().getMinScore()) {
                                        milestoneSubmission.setStatus(SubmissionStatus.FAILED_SCREENING);
                                    }
                                }
                                milestoneScreeningReviews.add(screeningReview);

                                ReviewItem reviewItem = new ReviewItem();
                                reviewItem.setReviewItemId(this.config.getReviewItemIdGenerator().getNextId());
                                reviewItem.setReviewId(screeningReview.getReviewId());
                                reviewItem.setAnswer(score + "/4");
                                reviewItem.setScorecardQuestionId(
                                    milestoneScreeningPhase.getReviewScorecard().getScorecardQuestionId());
                                reviewItem.setSortOrder(0);
                                screeningReview.setItems(new ReviewItem[]{reviewItem});

                                ReviewItemComment reviewItemComment = new ReviewItemComment();
                                reviewItemComment.setReviewItemCommentId(
                                    this.config.getReviewItemCommentIdGenerator().getNextId());
                                reviewItemComment.setResourceId(milestoneScreener.getResourceId());
                                reviewItemComment.setReviewItemId(reviewItem.getReviewItemId());
                                reviewItemComment.setContent("Ok");
                                reviewItemComment.setType(CommentType.COMMENT);
                                reviewItemComment.setSortOrder(0);
                                reviewItem.setComments(new ReviewItemComment[]{reviewItemComment});
                            }
                        }
                    }
                    milestoneScreener.setReviews(milestoneScreeningReviews.toArray(
                        new Review[milestoneScreeningReviews.size()]));
                }
            }
        }

        // Milestone Reviewer
        Resource milestoneReviewer = null;
        if (milestoneReviewerUser != null && milestoneReviewPhase != null) {
            if (!registrationPhase.isScheduled() || milestoneReviewPhase.isOpen()
                || milestoneReviewPhase.isClosed()) {
                milestoneReviewer = createResource(milestoneReviewerUser, ResourceRole.MILESTONE_REVIEWER,
                                                   resourceIdGenerator.getNextId(), milestoneReviewPhase,
                                                   resources);
                if (milestoneReviewPhase.isOpen() || milestoneReviewPhase.isClosed()) {
                    generateMilestoneReviews(milestoneReviewPhase, contestSubmitters, milestoneReviewer);
                }
            }
        }    

        // Primary Reviewer - screener, reviewer, aggregator, final reviewer
        long[] reviewRespIds = projectCategory.getReviewRespIds();
        User primaryReviewerUser = this.config.getPrimaryReviewer();
        if ((primaryReviewerUser != null) 
            && (!registrationPhase.isScheduled() || reviewPhase.isOpen() || reviewPhase.isClosed())) {
            
            // Primary Screener
            Resource primaryScreener = createResource(primaryReviewerUser, ResourceRole.PRIMARY_SCREENER, 
                                                      resourceIdGenerator.getNextId(), screeningPhase, resources);
            if (screeningPhase != null && (screeningPhase.isOpen() || screeningPhase.isClosed())) {
                List<Review> screeningReviews = new ArrayList<Review>();
                for (Resource contestSubmitter : contestSubmitters) {
                    Submission[] contestSubmissions = contestSubmitter.getSubmissions();
                    if (contestSubmissions != null) {
                        for (Submission contestSubmission : contestSubmissions) {
                            int score;
                            if (projectStatus == ProjectStatus.CANCELLED_FAILED_SCREENING) {
                                score = 1;
                            } else {
                                score = getRandomInt(3, 4);
                            }
                            
                            Review screeningReview = new Review();
                            screeningReview.setReviewId(this.config.getReviewIdGenerator().getNextId());
                            screeningReview.setResourceId(primaryScreener.getResourceId());
                            screeningReview.setSubmissionId(contestSubmission.getSubmissionId());
                            screeningReview.setScorecardId(screeningPhase.getReviewScorecard().getScorecardId());
                            screeningReview.setCommitted(screeningPhase.isClosed());
                            if (screeningPhase.isClosed()) {
                                screeningReview.setInitialScore(score / 4D * 100);
                                screeningReview.setScore(screeningReview.getInitialScore());
                                contestSubmission.setScreeningScore(screeningReview.getInitialScore());
                                if (screeningReview.getScore() 
                                    < screeningPhase.getReviewScorecard().getMinScore()) {
                                    contestSubmission.setStatus(SubmissionStatus.FAILED_SCREENING);
                                } else {
                                    addResourceSql(contestSubmitter, 
                                                   "UPDATE project_result SET rating_ind = 1, valid_submission_ind = 1 "
                                                   + "WHERE user_id = " + contestSubmitter.getTopCoderUser().getUserId()
                                                   + " AND project_id = " + projectId + ";");
                                }
                            }
                            screeningReviews.add(screeningReview);

                            ReviewItem reviewItem = new ReviewItem();
                            reviewItem.setReviewItemId(this.config.getReviewItemIdGenerator().getNextId());
                            reviewItem.setReviewId(screeningReview.getReviewId());
                            reviewItem.setAnswer(score + "/4");
                            reviewItem.setScorecardQuestionId(
                                screeningPhase.getReviewScorecard().getScorecardQuestionId());
                            reviewItem.setSortOrder(0);
                            screeningReview.setItems(new ReviewItem[]{reviewItem});
                                
                            ReviewItemComment reviewItemComment = new ReviewItemComment();
                            reviewItemComment.setReviewItemCommentId(
                                this.config.getReviewItemCommentIdGenerator().getNextId());
                            reviewItemComment.setResourceId(primaryScreener.getResourceId());
                            reviewItemComment.setReviewItemId(reviewItem.getReviewItemId());
                            if (projectStatus == ProjectStatus.CANCELLED_FAILED_SCREENING) {
                                reviewItemComment.setContent("Misses some deliverables");
                                reviewItemComment.setType(CommentType.REQUIRED);
                            } else {
                                reviewItemComment.setContent("Ok");
                                reviewItemComment.setType(CommentType.COMMENT);
                            }
                            reviewItemComment.setSortOrder(0);
                            reviewItem.setComments(new ReviewItemComment[] {reviewItemComment});
                        }
                    }
                }
                primaryScreener.setReviews(screeningReviews.toArray(new Review[screeningReviews.size()]));
            }

            // Primary Reviewer
            Resource primaryReviewer;
            String[] sql = new String[1];
            if (projectCategory == ProjectCategory.DEVELOPMENT) {
                primaryReviewer = createResource(primaryReviewerUser, ResourceRole.FAILURE_REVIEWER, 
                                                         resourceIdGenerator.getNextId(), reviewPhase, resources);
                sql[0] = "";
            } else {
                primaryReviewer = createResource(primaryReviewerUser, ResourceRole.REVIEWER, 
                                                         resourceIdGenerator.getNextId(), reviewPhase, resources);
                sql[0] = "";
            }
            primaryReviewer.setSql(sql);
            if (reviewPhase.isOpen() || reviewPhase.isClosed()) {
                generateReviews(projectStatus, reviewPhase, contestSubmitters, primaryReviewer, projectCategory,
                                projectId);
            }
            allReviewers.add(primaryReviewer);
        }

        // Reviewers
        User[] reviewerUsers = this.config.getReviewers();
        if ((reviewerUsers != null)
            && (!registrationPhase.isScheduled() || !reviewPhase.isScheduled())) {
            for (int i = 0; i < reviewerUsers.length && (i + 1) < reviewRespIds.length; i++) {
                User reviewerUser = reviewerUsers[i];
                Resource reviewer;
                String[] sql = new String[1];
                if (projectCategory == ProjectCategory.DEVELOPMENT) {
                    reviewer = createResource(reviewerUser, ResourceRole.REVIEWER, resourceIdGenerator.getNextId(),
                                              reviewPhase, resources);
                    sql[0] = "";
                } else {
                    reviewer = createResource(reviewerUser, ResourceRole.REVIEWER, resourceIdGenerator.getNextId(),
                                              reviewPhase, resources);
                    sql[0] = "";
                }
                reviewer.setSql(sql);
                if (reviewPhase.isOpen() || reviewPhase.isClosed()) {
                    generateReviews(projectStatus, reviewPhase, contestSubmitters, reviewer, projectCategory, projectId);
                }
                allReviewers.add(reviewer);
            }
        }
        
        // Update submission states if reviews were finished and completed
        if (reviewPhase.isClosed()) {
            for (Resource contestSubmitter : contestSubmitters) {
                Submission[] contestSubmissions = contestSubmitter.getSubmissions();
                if (contestSubmissions != null) {
                    for (Submission contestSubmission : contestSubmissions) {
                        double submissionScore = 0;
                        int reviewsCount = 0;
                        for (Resource reviewer : allReviewers) {
                            Review[] reviews = reviewer.getReviews();
                            for (Review review : reviews) {
                                if (review.getSubmissionId() == contestSubmission.getSubmissionId()) {
                                    submissionScore += review.getInitialScore();
                                    reviewsCount++;
                                }
                            }
                        }
                        contestSubmission.setInitialScore(submissionScore / reviewsCount);
                        addResourceSql(contestSubmitter,
                                       "UPDATE project_result SET raw_score = " + 80
                                       + " WHERE user_id = " + contestSubmitter.getTopCoderUser().getUserId()
                                       + " AND project_id = " + projectId + ";");
                    }
                }
            }
        }
        
        // Update contest submission states if appeals were responded, determine winners
        Submission winningSubmission = null;
        Resource winningSubmitter = null;
        
        if ((!isStudio && appealsResponsePhase != null && appealsResponsePhase.isClosed()) 
            || isStudio && reviewPhase.isClosed()) {
            List<Submission> allPassingSubmissions = new ArrayList<Submission>();
            for (Resource contestSubmitter : contestSubmitters) {
                Submission[] contestSubmissions = contestSubmitter.getSubmissions();
                if (contestSubmissions != null) {
                    for (Submission contestSubmission : contestSubmissions) {
                        double submissionScore = 0;
                        int reviewsCount = 0;
                        for (Resource reviewer : allReviewers) {
                            Review[] reviews = reviewer.getReviews();
                            for (Review review : reviews) {
                                if (review.getSubmissionId() == contestSubmission.getSubmissionId()) {
                                    submissionScore += review.getScore();
                                    reviewsCount++;
                                }
                            }
                        }
                        contestSubmission.setFinalScore(submissionScore / reviewsCount);
                        boolean failedToPassReview 
                            = contestSubmission.getFinalScore() < reviewPhase.getReviewScorecard().getMinScore();
                        addResourceSql(contestSubmitter,
                                       "UPDATE project_result SET final_score = " + contestSubmission.getFinalScore()    
                                       + ", passed_review_ind = " + (failedToPassReview ? 0 : 1) 
                                       + " WHERE user_id = " + contestSubmitter.getTopCoderUser().getUserId()
                                       + " AND project_id = " + projectId + ";");
                        if (failedToPassReview) {
                            contestSubmission.setStatus(SubmissionStatus.FAILED_REVIEW);
                        } else {
                            allPassingSubmissions.add(contestSubmission);
                        }
                    }
                }
            }
            // Sort submissions to determine placements
            Collections.sort(allPassingSubmissions, new Comparator<Submission>() {
                public int compare(Submission o1, Submission o2) {
                    Double finalScore1 = o1.getFinalScore() == null ? 0 : o1.getFinalScore(); 
                    Double finalScore2 = o2.getFinalScore() == null ? 0 : o2.getFinalScore();
                    return ((!finalScore1.equals(finalScore2))
                            ? finalScore2.compareTo(finalScore1)
                            : o1.getUpload().getCreationTimestamp().compareTo(o2.getUpload().getCreationTimestamp()));
                }
            });
            
            for (int i = 0; i < allPassingSubmissions.size(); i++) {
                int placement = i + 1;
                Submission contestSubmission = allPassingSubmissions.get(i);
                contestSubmission.setPlacement(placement);
                if (true) {
                    if ((placement - 1) < contestTypePrizes.size()) {
                        contestSubmission.setPrizeId(contestTypePrizes.get(placement - 1).getPrizeId());
                    }
                }
                
                long submitterResourceId = contestSubmission.getUpload().getResourceId();
                for (Resource contestSubmitter : contestSubmitters) {
                    if (contestSubmitter.getResourceId() == submitterResourceId) {
                        if (placement == 1) {
                            winningSubmission = contestSubmission;
                            winningSubmitter = contestSubmitter;
                            
                            contestSubmitter.setPayment(project.getFirstPlaceCost());
                            contestSubmitter.setPaymentStatus("No");
                            project.setWinnerUserId(contestSubmitter.getTopCoderUser().getUserId());
                            addResourceSql(contestSubmitter,
                                           "UPDATE project_result SET placed = 1, payment = " 
                                           + contestSubmitter.getPayment() 
                                           + " WHERE user_id = " + contestSubmitter.getTopCoderUser().getUserId()
                                           + " AND project_id = " + projectId + ";");
                        } else if (placement == 2) {
                            contestSubmitter.setPayment(project.getSecondPlaceCost());
                            contestSubmitter.setPaymentStatus("No");
                            project.setRunnerUpUserId(contestSubmitter.getTopCoderUser().getUserId());
                            addResourceSql(contestSubmitter,
                                           "UPDATE project_result SET placed = 2, payment = " 
                                           + contestSubmitter.getPayment() 
                                           + " WHERE user_id = " + contestSubmitter.getTopCoderUser().getUserId()
                                           + " AND project_id = " + projectId + ";");
                        } else {
                            addResourceSql(contestSubmitter,
                                           "UPDATE project_result SET placed = " + placement 
                                           + " WHERE user_id = " + contestSubmitter.getTopCoderUser().getUserId()
                                           + " AND project_id = " + projectId + ";");
                        }
                    }
                }
            }
        }

        // Update milestone submission states if milestone review is over
        if (milestoneReviewPhase != null && milestoneReviewPhase.isClosed()) {
            List<Submission> allPassingSubmissions = new ArrayList<Submission>();
            for (Resource contestSubmitter : contestSubmitters) {
                Submission[] milestoneSubmissions = contestSubmitter.getMilestoneSubmissions();
                if (milestoneSubmissions != null) {
                    for (Submission milestoneSubmission : milestoneSubmissions) {
                        double submissionScore = 0;
                        int reviewsCount = 0;
                        Review[] reviews = milestoneReviewer.getReviews();
                        for (Review review : reviews) {
                            if (review.getSubmissionId() == milestoneSubmission.getSubmissionId()) {
                                submissionScore += review.getScore();
                                reviewsCount++;
                            }
                        }

                        boolean failedToPassReview = false;
                        if (reviewsCount != 0) {
                            milestoneSubmission.setFinalScore(submissionScore / reviewsCount);
                            failedToPassReview = milestoneSubmission.getFinalScore() <
                                                 milestoneReviewPhase.getReviewScorecard().getMinScore();
                        }
                        if (failedToPassReview) {
                            milestoneSubmission.setStatus(SubmissionStatus.FAILED_MILESTONE_REVIEW);
                        } else {
                            allPassingSubmissions.add(milestoneSubmission);
                        }
                    }
                }
            }
            // Sort submissions to determine placements
            Collections.sort(allPassingSubmissions, new Comparator<Submission>() {
                public int compare(Submission o1, Submission o2) {
                    Double finalScore1 = o1.getFinalScore() == null ? 0 : o1.getFinalScore();
                    Double finalScore2 = o2.getFinalScore() == null ? 0 : o2.getFinalScore();
                    return ((!finalScore1.equals(finalScore2))
                            ? finalScore2.compareTo(finalScore1)
                            : o1.getUpload().getCreationTimestamp().compareTo(o2.getUpload().getCreationTimestamp()));
                }
            });

            for (int i = 0; i < allPassingSubmissions.size(); i++) {
                int placement = i + 1;
                Submission milestoneSubmission = allPassingSubmissions.get(i);
                milestoneSubmission.setPlacement(placement);
                if (isStudio) {
                    if (milestoneTypePrize != null) {
                        if (placement <= milestoneTypePrize.getNumberOfSubmissions()) {
                            milestoneSubmission.setPrizeId(milestoneTypePrize.getPrizeId());
                        }
                    } else {
                        System.out.println("WARNING! No milestone prize for project found");
                    }
                }
            }
        }

        // Aggregator
        Resource aggregator = null;
        Review aggregation = null;
        if (aggregationPhase != null) {
            if (primaryReviewerUser != null && !registrationPhase.isScheduled()) {
                aggregator = createResource(primaryReviewerUser, ResourceRole.AGGREGATOR,
                                                     resourceIdGenerator.getNextId(), aggregationPhase, resources);
            }
            aggregation = null;
            if ((aggregator != null) && (winningSubmission != null) 
                && (aggregationPhase.isOpen() || aggregationPhase.isClosed())) {
                aggregation = new Review();
                aggregation.setReviewId(this.config.getReviewIdGenerator().getNextId());
                aggregation.setResourceId(aggregator.getResourceId());
                aggregation.setSubmissionId(winningSubmission.getSubmissionId());
                aggregation.setScorecardId(reviewPhase.getReviewScorecard().getScorecardId());
                aggregation.setCommitted(reviewPhase.isClosed());
                
                // Aggregation Review comments from remaining reviewers and winning submitters
                List<ReviewComment> reviewComments = new ArrayList<ReviewComment>();
                int reviewCommentSortIndex = 0;
                for (Resource reviewer : allReviewers) {
                    if (reviewer.getUser().getUserId() != aggregator.getUser().getUserId()) {
                        ReviewComment reviewComment = new ReviewComment();
                        reviewComment.setReviewCommentId(this.config.getReviewCommentIdGenerator().getNextId());
                        reviewComment.setReviewId(aggregation.getReviewId());
                        reviewComment.setResourceId(reviewer.getResourceId());
                        reviewComment.setContent("");
                        reviewComment.setType(CommentType.AGGREGATION_REVIEW_COMMENT);
                        reviewComment.setSortOrder(reviewCommentSortIndex++);
                        if (aggregationReviewPhase != null && aggregationReviewPhase.isClosed()) {
                            reviewComment.setExtraInfo("Approved");
                        }
                        reviewComments.add(reviewComment);
                    }
                }
                ReviewComment submitterAggregationComment = new ReviewComment();
                submitterAggregationComment.setReviewCommentId(this.config.getReviewCommentIdGenerator().getNextId());
                submitterAggregationComment.setReviewId(aggregation.getReviewId());
                submitterAggregationComment.setResourceId(winningSubmitter.getResourceId());
                submitterAggregationComment.setContent("");
                submitterAggregationComment.setType(CommentType.SUBMITTER_COMMENT);
                submitterAggregationComment.setSortOrder(reviewCommentSortIndex++);
                if (aggregationReviewPhase != null && aggregationReviewPhase.isClosed()) {
                    submitterAggregationComment.setExtraInfo("Approved");
                }
                reviewComments.add(submitterAggregationComment);
                
                aggregation.setComments(reviewComments.toArray(new ReviewComment[reviewComments.size()]));
                
                // Review items from review scorecards for winning submission
                List<ReviewItem> reviewItems = new ArrayList<ReviewItem>();
                for (Resource reviewer : allReviewers) {
                    Review[] reviews = reviewer.getReviews();
                    int reviewItemSortingIndex = 0;
                    for (Review review : reviews) {
                        if (review.getSubmissionId() == winningSubmission.getSubmissionId()) {
                            ReviewItem[] originalReviewItems = review.getItems();
                            for (ReviewItem originalReviewItem : originalReviewItems) {
                                ReviewItem reviewItem = new ReviewItem();
                                reviewItem.setReviewItemId(this.config.getReviewItemIdGenerator().getNextId());
                                reviewItem.setReviewId(aggregation.getReviewId());
                                reviewItem.setAnswer(originalReviewItem.getAnswer());
                                reviewItem.setScorecardQuestionId(originalReviewItem.getScorecardQuestionId());
                                reviewItem.setSortOrder(reviewItemSortingIndex++);
                                reviewItems.add(reviewItem);
    
                                List<ReviewItemComment> reviewItemComments = new ArrayList<ReviewItemComment>();
                                ReviewItemComment[] originalReviewItemComments = originalReviewItem.getComments();
                                for (ReviewItemComment originalReviewItemComment : originalReviewItemComments) {
                                    ReviewItemComment reviewItemComment = new ReviewItemComment();
                                    reviewItemComment.setReviewItemCommentId(
                                        this.config.getReviewItemCommentIdGenerator().getNextId());
                                    reviewItemComment.setResourceId(originalReviewItemComment.getResourceId());
                                    reviewItemComment.setReviewItemId(reviewItem.getReviewItemId());
                                    reviewItemComment.setContent(originalReviewItemComment.getContent());
                                    reviewItemComment.setType(originalReviewItemComment.getType());
                                    reviewItemComment.setExtraInfo("Accept");
                                    reviewItemComment.setSortOrder(0);
                                    reviewItemComments.add(reviewItemComment);
                                }
                                
                                // Aggregation comments from Aggregator
                                ReviewItemComment aggregatorReviewItemComment = new ReviewItemComment();
                                aggregatorReviewItemComment.setReviewItemCommentId(
                                    this.config.getReviewItemCommentIdGenerator().getNextId());
                                aggregatorReviewItemComment.setResourceId(aggregator.getResourceId());
                                aggregatorReviewItemComment.setReviewItemId(reviewItem.getReviewItemId());
                                aggregatorReviewItemComment.setContent("");
                                aggregatorReviewItemComment.setType(CommentType.AGGREATION_COMMENT);
                                aggregatorReviewItemComment.setSortOrder(1);
                                reviewItemComments.add(aggregatorReviewItemComment);
    
                                reviewItem.setComments(
                                    reviewItemComments.toArray(new ReviewItemComment[reviewItemComments.size()]));
                            
                            }
                        }
                    }
                }
                
                aggregation.setItems(reviewItems.toArray(new ReviewItem[reviewItems.size()]));
                aggregator.setReviews(new Review[] {aggregation});
            }
        }

        // Final Fix
        if (finalFixPhase != null) {
            Upload finalFixUpload;
            if (winningSubmitter != null) {
                if ((finalFixPhase.isOpen() || finalFixPhase.isClosed()) 
                    && (projectStatus != ProjectStatus.CANCELLED_WINNER_UNRESPONSIVE)) {
                    finalFixUpload = new Upload();
                    finalFixUpload.setUploadId(this.config.getUploadIdGenerator().getNextId());
                    finalFixUpload.setProjectId(projectId);
                    finalFixUpload.setResourceId(winningSubmitter.getResourceId());
                    finalFixUpload.setType(UploadType.FINAL_FIX);
                    finalFixUpload.setStatus(UploadStatus.ACTIVE);
                    finalFixUpload.setParameter(projectId + "_" + winningSubmitter.getResourceId() + "_"
                                                + finalFixUpload.getUploadId() + "_final_fix.zip");
                    finalFixUpload.setCreationTimestamp(new Date());
                    finalFixUpload.setProjectPhaseId(finalFixPhase.getPhaseId());
                    addResourceUpload(winningSubmitter, finalFixUpload);
                }
            }
        }

        // Final Reviewer
        if (finalReviewPhase != null) {
            Resource finalReviewer = null;
            if (primaryReviewerUser != null && !registrationPhase.isScheduled()) {
                finalReviewer = createResource(primaryReviewerUser, ResourceRole.FINAL_REVIEWER,
                                               resourceIdGenerator.getNextId(), finalReviewPhase, resources);
            }
            if ((finalReviewer != null) && (winningSubmission != null) 
                && (finalFixPhase.isOpen() || finalFixPhase.isClosed())) {
                List<ReviewItemComment> aggregationReviewItemComments = new ArrayList<ReviewItemComment>();
                ReviewItem[] aggregationReviewItems = aggregation.getItems();
                for (ReviewItem aggregationReviewItem : aggregationReviewItems) {
                    ReviewItemComment[] comments = aggregationReviewItem.getComments();
                    for (ReviewItemComment comment : comments) {
                        if (comment.getType() == CommentType.AGGREATION_COMMENT) {
                            aggregationReviewItemComments.add(comment);
                        }
                    }
                }
    
                Review finalReview = new Review();
                finalReview.setReviewId(this.config.getReviewIdGenerator().getNextId());
                finalReview.setResourceId(finalReviewer.getResourceId());
                finalReview.setSubmissionId(winningSubmission.getSubmissionId());
                finalReview.setScorecardId(reviewPhase.getReviewScorecard().getScorecardId());
                finalReview.setCommitted(finalReviewPhase.isClosed());
                
                // Aggregation Review comments from remaining reviewers and winning submitters
                List<ReviewComment> reviewComments = new ArrayList<ReviewComment>();
                int reviewCommentSortIndex = 0;
                for (Resource reviewer : allReviewers) {
                    if (reviewer.getUser().getUserId() != aggregator.getUser().getUserId()) {
                        ReviewComment reviewComment = new ReviewComment();
                        reviewComment.setReviewCommentId(this.config.getReviewCommentIdGenerator().getNextId());
                        reviewComment.setReviewId(finalReview.getReviewId());
                        reviewComment.setResourceId(reviewer.getResourceId());
                        reviewComment.setContent("");
                        reviewComment.setType(CommentType.AGGREGATION_REVIEW_COMMENT);
                        reviewComment.setSortOrder(reviewCommentSortIndex++);
                        reviewComments.add(reviewComment);
                    }
                }
                ReviewComment submitterAggregationComment = new ReviewComment();
                submitterAggregationComment.setReviewCommentId(this.config.getReviewCommentIdGenerator().getNextId());
                submitterAggregationComment.setReviewId(finalReview.getReviewId());
                submitterAggregationComment.setResourceId(winningSubmitter.getResourceId());
                submitterAggregationComment.setContent("");
                submitterAggregationComment.setType(CommentType.SUBMITTER_COMMENT);
                submitterAggregationComment.setSortOrder(reviewCommentSortIndex++);
                reviewComments.add(submitterAggregationComment);
                
                if (finalReviewPhase.isClosed()) {
                    ReviewComment finalReviewerComment = new ReviewComment();
                    finalReviewerComment.setReviewCommentId(this.config.getReviewCommentIdGenerator().getNextId());
                    finalReviewerComment.setReviewId(finalReview.getReviewId());
                    finalReviewerComment.setResourceId(finalReviewer.getResourceId());
                    finalReviewerComment.setContent("");
                    finalReviewerComment.setExtraInfo("Approved");
                    finalReviewerComment.setType(CommentType.FINAL_REVIEW_COMMENT);
                    finalReviewerComment.setSortOrder(reviewCommentSortIndex++);
                    reviewComments.add(finalReviewerComment);
                }
                
                finalReview.setComments(reviewComments.toArray(new ReviewComment[reviewComments.size()]));
                
                // Review items from review scorecards for winning submission
                List<ReviewItem> reviewItems = new ArrayList<ReviewItem>();
                for (Resource reviewer : allReviewers) {
                    Review[] reviews = reviewer.getReviews();
                    int reviewItemSortingIndex = 0;
                    for (Review review : reviews) {
                        if (review.getSubmissionId() == winningSubmission.getSubmissionId()) {
                            ReviewItem[] originalReviewItems = review.getItems();
                            int index = 0;
                            for (ReviewItem originalReviewItem : originalReviewItems) {
                                ReviewItem reviewItem = new ReviewItem();
                                reviewItem.setReviewItemId(this.config.getReviewItemIdGenerator().getNextId());
                                reviewItem.setReviewId(finalReview.getReviewId());
                                reviewItem.setAnswer(originalReviewItem.getAnswer());
                                reviewItem.setScorecardQuestionId(originalReviewItem.getScorecardQuestionId());
                                reviewItem.setSortOrder(reviewItemSortingIndex++);
                                reviewItems.add(reviewItem);
                                
                                int sortIndex = 0;
                                List<ReviewItemComment> reviewItemComments = new ArrayList<ReviewItemComment>();
                                ReviewItemComment[] originalReviewItemComments = originalReviewItem.getComments();
                                for (ReviewItemComment originalReviewItemComment : originalReviewItemComments) {
                                    ReviewItemComment reviewItemComment = new ReviewItemComment();
                                    reviewItemComment.setReviewItemCommentId(
                                        this.config.getReviewItemCommentIdGenerator().getNextId());
                                    reviewItemComment.setResourceId(originalReviewItemComment.getResourceId());
                                    reviewItemComment.setReviewItemId(reviewItem.getReviewItemId());
                                    reviewItemComment.setType(originalReviewItemComment.getType());
                                    reviewItemComment.setContent(originalReviewItemComment.getContent());
                                    reviewItemComment.setSortOrder(sortIndex++);
                                    reviewItemComments.add(reviewItemComment);
                                    if (finalReviewPhase.isClosed()) {
                                        reviewItemComment.setExtraInfo("Fixed");
                                    }
                                }
                                ReviewItemComment aggregationReviewItemComment = aggregationReviewItemComments.get(index++);
                                ReviewItemComment reviewItemComment = new ReviewItemComment();
                                reviewItemComment.setReviewItemCommentId(
                                    this.config.getReviewItemCommentIdGenerator().getNextId());
                                reviewItemComment.setResourceId(aggregationReviewItemComment.getResourceId());
                                reviewItemComment.setReviewItemId(reviewItem.getReviewItemId());
                                reviewItemComment.setType(aggregationReviewItemComment.getType());
                                reviewItemComment.setContent(aggregationReviewItemComment.getContent());
                                reviewItemComment.setSortOrder(sortIndex++);
                                reviewItemComments.add(reviewItemComment);
    
                                if (finalReviewPhase.isClosed()) {
                                    ReviewItemComment finalReviewItemComment = new ReviewItemComment();
                                    finalReviewItemComment.setReviewItemCommentId(
                                        this.config.getReviewItemCommentIdGenerator().getNextId());
                                    finalReviewItemComment.setResourceId(finalReviewer.getResourceId());
                                    finalReviewItemComment.setReviewItemId(reviewItem.getReviewItemId());
                                    finalReviewItemComment.setType(CommentType.FINAL_REVIEW_COMMENT);
                                    finalReviewItemComment.setContent("");
                                    finalReviewItemComment.setSortOrder(sortIndex++);
                                    reviewItemComments.add(finalReviewItemComment);
                                }
    
                                reviewItem.setComments(
                                    reviewItemComments.toArray(new ReviewItemComment[reviewItemComments.size()]));
                            }
                        }
                    }
                }
                finalReview.setItems(reviewItems.toArray(new ReviewItem[reviewItems.size()]));
                finalReviewer.setReviews(new Review[]{finalReview});
            }
        }

        // Copilot
        if (this.config.getCopilot() != null) {
            createResource(this.config.getCopilot(), ResourceRole.COPILOT, resourceIdGenerator.getNextId(), null, 
                           resources);
            project.setCopilotCost(600D);
        } else {
            project.setCopilotCost(0D);
        }

        // Approver
        if (this.config.getApprover() != null) {
            Resource approver = createResource(this.config.getApprover(), ResourceRole.APPROVER,
                                               resourceIdGenerator.getNextId(), null, resources);
            if ((winningSubmission != null) && (approvalPhase.isClosed())) {
                Review approval = new Review();
                approval.setReviewId(this.config.getReviewIdGenerator().getNextId());
                approval.setResourceId(approver.getResourceId());
                approval.setSubmissionId(winningSubmission.getSubmissionId());
                approval.setScorecardId(approvalPhase.getReviewScorecard().getScorecardId());
                approval.setCommitted(approvalPhase.isClosed());
                if (approvalPhase.isClosed()) {
                    approval.setInitialScore(100D);
                    approval.setScore(approval.getInitialScore());
                }
                
                ReviewComment approvalComment = new ReviewComment();
                approvalComment.setReviewCommentId(this.config.getReviewCommentIdGenerator().getNextId());
                approvalComment.setReviewId(approval.getReviewId());
                approvalComment.setResourceId(approver.getResourceId());
                approvalComment.setContent("");
                approvalComment.setExtraInfo("Approved");
                approvalComment.setType(CommentType.APPROVAL_REVIEW_COMMENT);
                approvalComment.setSortOrder(0);
                
                ReviewComment approvalCommentOtherFixes = new ReviewComment();
                approvalCommentOtherFixes.setReviewCommentId(this.config.getReviewCommentIdGenerator().getNextId());
                approvalCommentOtherFixes.setReviewId(approval.getReviewId());
                approvalCommentOtherFixes.setResourceId(approver.getResourceId());
                approvalCommentOtherFixes.setContent("");
                approvalCommentOtherFixes.setExtraInfo("");
                approvalCommentOtherFixes.setType(CommentType.APPROVAL_REVIEW_COMMENT_OTHER_FIXES);
                approvalCommentOtherFixes.setSortOrder(1);
    
                approval.setComments(new ReviewComment[] {approvalComment, approvalCommentOtherFixes}); 
    
                ReviewItem reviewItem = new ReviewItem();
                reviewItem.setReviewItemId(this.config.getReviewItemIdGenerator().getNextId());
                reviewItem.setReviewId(approval.getReviewId());
                reviewItem.setAnswer("4/4");
                reviewItem.setScorecardQuestionId(approvalPhase.getReviewScorecard().getScorecardQuestionId());
                reviewItem.setSortOrder(0);
                approval.setItems(new ReviewItem[]{reviewItem});
                            
                ReviewItemComment reviewItemComment = new ReviewItemComment();
                reviewItemComment.setReviewItemCommentId(
                    this.config.getReviewItemCommentIdGenerator().getNextId());
                reviewItemComment.setResourceId(approver.getResourceId());
                reviewItemComment.setReviewItemId(reviewItem.getReviewItemId());
                reviewItemComment.setContent("Ok");
                reviewItemComment.setType(CommentType.COMMENT);
                reviewItemComment.setSortOrder(0);
                reviewItem.setComments(new ReviewItemComment[] {reviewItemComment});
                
                approver.setReviews(new Review[] {approval});
            }
        }

        // Completion date (for cancelled, deleted, completed statuses only)
        if (projectStatus != ProjectStatus.ACTIVE && projectStatus != ProjectStatus.INACTIVE) {
            Long lastPhaseEndTimeOffset = null;
            if ((projectStatus == ProjectStatus.COMPLETED) 
                || (projectStatus == ProjectStatus.CANCELLED_CLIENT_REQUEST)
                || (projectStatus == ProjectStatus.CANCELLED_FAILED_REVIEW)
                || (projectStatus == ProjectStatus.CANCELLED_FAILED_REVIEW)
                || (projectStatus == ProjectStatus.CANCELLED_FAILED_SCREENING)
                || (projectStatus == ProjectStatus.CANCELLED_REQUIREMENTS_INFEASIBLE)
                || (projectStatus == ProjectStatus.CANCELLED_WINNER_UNRESPONSIVE)
                || (projectStatus == ProjectStatus.CANCELLED_ZERO_REGISTRATIONS)
                || (projectStatus == ProjectStatus.CANCELLED_ZERO_SUBMISSIONS)
                || (projectStatus == ProjectStatus.DELETED)) {
                Phase[] projectPhases = project.getPhases();
                for (int i = 0; i < projectPhases.length; i++) {
                    Phase projectPhase = projectPhases[i];
                    Long actualEndTimeOffset = projectPhase.getActualEndTimeOffset();
                    if (actualEndTimeOffset != null) {
                        if ((lastPhaseEndTimeOffset == null) 
                            || (lastPhaseEndTimeOffset.compareTo(actualEndTimeOffset) < 0)) {
                            lastPhaseEndTimeOffset = actualEndTimeOffset;
                        }
                    }
                }
            }
            if (lastPhaseEndTimeOffset == null) {
                lastPhaseEndTimeOffset = 0L;
            }
            Date now = new Date();
            project.setCompletionDate(new Date(now.getTime() + lastPhaseEndTimeOffset * 60 * 1000));
        }
        
        // Payments (for completed projects)
        if (projectStatus == ProjectStatus.COMPLETED) {
            List<Payment> payments = new ArrayList<Payment>();

            List<Resource> projectResources = project.getResources();
            for (Resource resource : projectResources) {
                boolean addPayment = false;
                long userId = resource.getUserId();
                PaymentType paymentType = null;
                PaymentStatus paymentStatus = PaymentStatus.OWED;
                String paymentDescription = "";
                double paymentAmount = 0;
                
                ResourceRole resourceRole = resource.getRole();
                if ((resourceRole == ResourceRole.SPECIFICATION_REVIEWER) && (project.getSpecReviewCost() != null)) {
                    addPayment = true;
                    if (isStudio) {
                        paymentType = PaymentType.STUDIO_SPECIFICATION_REVIEW_PAYMENT;
                    } else {
                        paymentType = PaymentType.SPECIFICATION_REVIEW_PAYMENT;
                    }
                    paymentAmount = project.getSpecReviewCost();
                    paymentDescription = "Specification Review for " + project.getProjectName();
                } else if ((resourceRole == ResourceRole.REVIEWER) && (project.getReviewCost() != null)) {
                    addPayment = true;
                    if (projectCategory == ProjectCategory.ARCHITECTURE) {
                        paymentType = PaymentType.ARCHITECTURE_REVIEW_PAYMENT;
                    } else if (projectCategory == ProjectCategory.ASSEMBLY) {
                        paymentType = PaymentType.ASSEMBLY_COMPETITION_REVIEW;
                    } else {
                        paymentType = PaymentType.REVIEW_BOARD_PAYMENT;
                    }
                    paymentAmount = project.getReviewCost();
                    paymentDescription = "Review for " + project.getProjectName();
                } else if ((resourceRole == ResourceRole.COPILOT) && (project.getCopilotCost() != null)) {
                    addPayment = true;
                    if (isStudio) {
                        paymentType = PaymentType.STUDIO_COPILOT_PAYMENT;
                    } else {
                        paymentType = PaymentType.COPILOT_PAYMENT;
                    }
                    paymentAmount = project.getCopilotCost();
                    paymentDescription = "Co-piloting for " + project.getProjectName();
                } else if ((resourceRole == ResourceRole.SUBMITTER) && (resource.getPayment() != null)) {
                    addPayment = true;
                    if (projectCategory == ProjectCategory.DESIGN || projectCategory == ProjectCategory.DEVELOPMENT) {
                        paymentType = PaymentType.COMPONENT_PAYMENT;
                    } else if (projectCategory == ProjectCategory.ARCHITECTURE) {
                        paymentType = PaymentType.ARCHITECTURE_PAYMENT;
                    } else if (projectCategory == ProjectCategory.ASSEMBLY) {
                        paymentType = PaymentType.ASSEMBLY_PAYMENT;
                    } else if (projectCategory == ProjectCategory.SPECIFICATION) {
                        paymentType = PaymentType.SPECIFICATION_PAYMENT;
                    } else if (projectCategory == ProjectCategory.CONCEPTUALIZATION) {
                        paymentType = PaymentType.CONCEPTUALIZATION_PAYMENT;
                    } else if (projectCategory == ProjectCategory.TEST_SUITES) {
                        paymentType = PaymentType.TEST_SUITES_PAYMENT;
                    } else if (projectCategory == ProjectCategory.UI_PROTOTYPES) {
                        paymentType = PaymentType.UI_PROTOTYPE_PAYMENT;
                    } else if (projectCategory == ProjectCategory.RIA_BUILD) {
                        paymentType = PaymentType.RIA_BUILD_PAYMENT;
                    } else if (projectCategory == ProjectCategory.RIA_COMPONENT) {
                        paymentType = PaymentType.RIA_COMPONENT_PAYMENT;
                    } else if (projectCategory == ProjectCategory.TEST_SCENARIOS) {
                        paymentType = PaymentType.TEST_SCENARIOS_PAYMENT;
                    } else if (projectCategory == ProjectCategory.COPILOT_POSTING) {
                        paymentType = PaymentType.COPILOT_POSTING_PAYMENT;
                    } else if (projectCategory == ProjectCategory.CONTENT_CREATION) {
                        paymentType = PaymentType.CONTENT_CREATION_PAYMENT;
                    } else if (isStudio) {
                        paymentType = PaymentType.STUDIO_CONTEST_PAYMENT;
                    } else {
                        paymentType = PaymentType.CONTEST_PAYMENT;
                    }
                    paymentAmount = resource.getPayment();
                    paymentDescription = "Contest Payment for " + project.getProjectName();
                }
                
                if (addPayment) {
                    Payment payment = new Payment();
                    payment.setPaymentId(this.config.getPaymentIdGenerator().getNextId());
                    payment.setPaymentDetailId(payment.getPaymentId());
                    payment.setAmount(paymentAmount);
                    payment.setDescription(paymentDescription);
                    payment.setPaymentStatus(paymentStatus);
                    payment.setPaymentType(paymentType);
                    payment.setUserId(userId);

                    payments.add(payment);
                }
            }

            
            project.setPayments(payments);
        }

        return project;
    }

    /**
     * <p>Creates new prize instance with specified parameters.</p>
     *  
     * @param amount a <code>double</code> providing the prize amount. 
     * @param numberOfSubmissions an <code>int</code> providing the number of submissions for prize.
     * @param placement an <code>int</code> providing the placement for prize.
     * @param prizeType a <code>PrizeType</code> specifying the type of prize. 
     * @return a <code>Prize</code> representing a single prize. 
     */
    private Prize createPrize(double amount, int numberOfSubmissions, int placement, PrizeType prizeType) {
        Prize prize = new Prize();
        prize.setAmount(amount);
        prize.setNumberOfSubmissions(numberOfSubmissions);
        prize.setPlacement(placement);
        prize.setPrizeId(this.config.getPrizeIdGenerator().getNextId());
        prize.setType(prizeType);
        return prize;
    }

    /**
     * <p>Generates the reviews for contest submissions from specified project of specified status.</p>
     *
     * @param projectStatus a <code>ProjectStatus</code> providing the status of the project. 
     * @param reviewPhase a <code>Phase</code> providing the review phase. 
     * @param contestSubmitters a <code>List</code> of submitters for contest. 
     * @param reviewerResource a <code>Resource</code> representing the reviewer. 
     * @param projectCategory a <code>ProjectCategory</code> providing the project category. 
     * @param projectId a <code>long</code> providing the project ID.
     */
    private void generateReviews(ProjectStatus projectStatus, Phase reviewPhase, List<Resource> contestSubmitters,
                                 Resource reviewerResource, ProjectCategory projectCategory, long projectId) {
        
        int submissionsFailureRate = this.config.getSubmissionsFailureRate();
        int submissionsCount = 0;
        for (Resource contestSubmitter : contestSubmitters) {
            Submission[] contestSubmissions = contestSubmitter.getSubmissions();
            if (contestSubmissions != null) {
                submissionsCount += contestSubmissions.length;
            }
        }
        int submissionsToFail = 0;
        if (submissionsCount != 0) {
            submissionsToFail = submissionsFailureRate * submissionsCount / 100;
        }

        int submissionsProcessed = 0;
        List<Review> reviews = new ArrayList<Review>();
        for (Resource contestSubmitter : contestSubmitters) {
            Submission[] contestSubmissions = contestSubmitter.getSubmissions();
            if (contestSubmissions != null) {
                for (Submission contestSubmission : contestSubmissions) {
                    submissionsProcessed++;
                    int score;
                    if (projectStatus == ProjectStatus.CANCELLED_FAILED_REVIEW 
                        || submissionsProcessed <= submissionsToFail) {
                        score = 1;
                    } else {
                        score = getRandomInt(3, 4);
                    }
                    
                    Review review = new Review();
                    review.setReviewId(this.config.getReviewIdGenerator().getNextId());
                    review.setResourceId(reviewerResource.getResourceId());
                    review.setSubmissionId(contestSubmission.getSubmissionId());
                    review.setScorecardId(reviewPhase.getReviewScorecard().getScorecardId());
                    review.setCommitted(reviewPhase.isClosed());
                    if (reviewPhase.isClosed()) {
                        review.setInitialScore(score / 4D * 100);
                        review.setScore(review.getInitialScore());
                    }
                    reviews.add(review);

                    ReviewItem reviewItem = new ReviewItem();
                    reviewItem.setReviewItemId(this.config.getReviewItemIdGenerator().getNextId());
                    reviewItem.setReviewId(review.getReviewId());
                    reviewItem.setAnswer(score + "/4");
                    reviewItem.setScorecardQuestionId(
                        reviewPhase.getReviewScorecard().getScorecardQuestionId());
                    reviewItem.setSortOrder(0);
                    review.setItems(new ReviewItem[]{reviewItem});
                        
                    ReviewItemComment reviewItemComment = new ReviewItemComment();
                    reviewItemComment.setReviewItemCommentId(
                        this.config.getReviewItemCommentIdGenerator().getNextId());
                    reviewItemComment.setResourceId(reviewerResource.getResourceId());
                    reviewItemComment.setReviewItemId(reviewItem.getReviewItemId());
                    if (score == 1) {
                        reviewItemComment.setContent("Misses some requirements");
                        reviewItemComment.setType(CommentType.REQUIRED);
                    } else {
                        reviewItemComment.setContent("Ok");
                        reviewItemComment.setType(CommentType.COMMENT);
                    }
                    reviewItemComment.setSortOrder(0);
                    reviewItem.setComments(new ReviewItemComment[] {reviewItemComment});
                }
            }
        }
        reviewerResource.setReviews(reviews.toArray(new Review[reviews.size()]));
        
        // For development reviewers add test case upload in case the reviews are done
        if ((projectCategory == ProjectCategory.DEVELOPMENT) && (reviewPhase.isClosed())) {
            Upload testCaseUpload = new Upload();
            testCaseUpload.setUploadId(this.config.getUploadIdGenerator().getNextId());
            testCaseUpload.setProjectId(projectId);
            testCaseUpload.setResourceId(reviewerResource.getResourceId());
            testCaseUpload.setStatus(UploadStatus.ACTIVE);
            testCaseUpload.setType(UploadType.TEST_CASE);
            testCaseUpload.setParameter(projectId + "_" + reviewerResource.getResourceId() + "_"
                                        + testCaseUpload.getUploadId() + "_testcase.zip");
            testCaseUpload.setCreationTimestamp(new Date());
            testCaseUpload.setProjectPhaseId(reviewPhase.getPhaseId());
            addResourceUpload(reviewerResource, testCaseUpload);
        }
    }

    /**
     * <p>Generates the milestone reviews for contest submissions from specified project of specified status.</p>
     *
     * @param milestoneReviewPhase a <code>Phase</code> providing the review phase.
     * @param contestSubmitters a <code>List</code> of submitters for contest.
     * @param milestoneReviewerResource  a <code>Resource</code> representing the reviewer.
     */
    private void generateMilestoneReviews(Phase milestoneReviewPhase, List<Resource> contestSubmitters, 
                                          Resource milestoneReviewerResource) {
        List<Review> reviews = new ArrayList<Review>();
        for (Resource contestSubmitter : contestSubmitters) {
            Submission[] milestoneSubmissions = contestSubmitter.getMilestoneSubmissions();
            if (milestoneSubmissions != null) {
                for (Submission milestoneSubmission : milestoneSubmissions) {
                    int score = getRandomInt(3, 4);

                    Review review = new Review();
                    review.setReviewId(this.config.getReviewIdGenerator().getNextId());
                    review.setResourceId(milestoneReviewerResource.getResourceId());
                    review.setSubmissionId(milestoneSubmission.getSubmissionId());
                    review.setScorecardId(milestoneReviewPhase.getReviewScorecard().getScorecardId());
                    review.setCommitted(milestoneReviewPhase.isClosed());
                    if (milestoneReviewPhase.isClosed()) {
                        review.setInitialScore(score / 4D * 100);
                        review.setScore(review.getInitialScore());
                    }
                    reviews.add(review);

                    ReviewItem reviewItem = new ReviewItem();
                    reviewItem.setReviewItemId(this.config.getReviewItemIdGenerator().getNextId());
                    reviewItem.setReviewId(review.getReviewId());
                    reviewItem.setAnswer(score + "/4");
                    reviewItem.setScorecardQuestionId(
                        milestoneReviewPhase.getReviewScorecard().getScorecardQuestionId());
                    reviewItem.setSortOrder(0);
                    review.setItems(new ReviewItem[]{reviewItem});

                    ReviewItemComment reviewItemComment = new ReviewItemComment();
                    reviewItemComment.setReviewItemCommentId(
                        this.config.getReviewItemCommentIdGenerator().getNextId());
                    reviewItemComment.setResourceId(milestoneReviewerResource.getResourceId());
                    reviewItemComment.setReviewItemId(reviewItem.getReviewItemId());
                    reviewItemComment.setContent("Ok");
                    reviewItemComment.setType(CommentType.COMMENT);
                    reviewItemComment.setSortOrder(0);
                    reviewItem.setComments(new ReviewItemComment[]{reviewItemComment});
                }
            }
        }
        milestoneReviewerResource.setReviews(reviews.toArray(new Review[reviews.size()]));
    }

    /**
     * <p>Generates a random value in specified range (inclusive).</p>
     * 
     * @param min an <code>int</code> providing the minimum range value. 
     * @param max an <code>int</code> providing the maximum range value.
     * @return an <code>int</code> providing the generated value.
     */
    private int getRandomInt(int min, int max) {
        int result;
        do {
            result = this.random.nextInt(max + 1);
        } while ((result < min) || (result > max));
        return result;
    }

    /**
     * <p>Marks specified phase as scheduled.</p>
     *
     * @param phase a <code>Phase</code> to be set with <code>Scheduled</code> status.  
     * @param phases a <code>Phase</code> list listing the project phases. 
     */
    private void schedule(Phase phase, List<Phase> phases) {
        Phase prevPhase = null;
        for (Phase p : phases) {
            if (p.getPhaseType() == phase.getPhaseType().getMainPhaseType()) {
                prevPhase = p;
            }
        }
        phase.setPhaseStatus(PhaseStatus.SCHEDULED);
        if (prevPhase == null) {
            phase.setScheduledStartTimeOffset(getRandomInt(10, 500) * 1L);
        } else {
            if (phase.getPhaseType().getStartsWhenDependencyStarts()) {
                phase.setScheduledStartTimeOffset(prevPhase.getScheduledStartTimeOffset());
            } else {
                phase.setScheduledStartTimeOffset(prevPhase.getScheduledEndTimeOffset());
            }
        }
        phase.setScheduledEndTimeOffset(phase.getScheduledStartTimeOffset() + phase.getDuration() / 1000 / 60);
        phase.setActualStartTimeOffset(null);
        phase.setActualEndTimeOffset(null);
        if (phase.getPhaseType() == PhaseType.SPECIFICATION_SUBMISSION) {
            phase.setFixedStartTimeOffset(phase.getScheduledStartTimeOffset());
        } else {
            phase.setFixedStartTimeOffset(null);
        }
    }

    /**
     * <p>Marks specified phase as open.</p>
     *
     * @param phase a <code>Phase</code> to be set with <code>Open</code> status.  
     * @param phases a <code>Phase</code> list listing the project phases. 
     */
    private void open(Phase phase, List<Phase> phases) {
        Phase prevPhase = null;
        for (Phase p : phases) {
            if (p.getPhaseType() == phase.getPhaseType().getMainPhaseType()) {
                prevPhase = p;
            }
        }
        phase.setPhaseStatus(PhaseStatus.OPEN);
        if (prevPhase == null) {
            phase.setScheduledStartTimeOffset((phase.getDuration() / 1000 / 60 / 2) * (-1));
        } else {
            if (phase.getPhaseType().getStartsWhenDependencyStarts()) {
                phase.setScheduledStartTimeOffset(prevPhase.getActualStartTimeOffset());
            } else {
                phase.setScheduledStartTimeOffset(prevPhase.getActualEndTimeOffset());
            }
        }
        phase.setScheduledEndTimeOffset(phase.getScheduledStartTimeOffset() + phase.getDuration() / 1000 / 60);
        phase.setActualStartTimeOffset(phase.getScheduledStartTimeOffset());
        phase.setActualEndTimeOffset(null);
        if (phase.getPhaseType() == PhaseType.SPECIFICATION_SUBMISSION) {
            phase.setFixedStartTimeOffset(phase.getScheduledStartTimeOffset());
        } else {
            phase.setFixedStartTimeOffset(null);
        }
    }

    /**
     * <p>Marks specified phase as closed.</p>
     *
     * @param phase a <code>Phase</code> to be set with <code>Closed</code> status.  
     * @param phases a <code>Phase</code> list listing the project phases. 
     */
    private void close(Phase phase, List<Phase> phases) {
        Phase prevPhase = null;
        for (Phase p : phases) {
            if (p.getPhaseType() == phase.getPhaseType().getMainPhaseType()) {
                prevPhase = p;
            }
        }
        phase.setPhaseStatus(PhaseStatus.CLOSED);
        if (prevPhase == null) {
            phase.setScheduledStartTimeOffset(getRandomInt(30, 80) * 24 * 60L * (-1));
        } else {
            if (phase.getPhaseType().getStartsWhenDependencyStarts()) {
                phase.setScheduledStartTimeOffset(prevPhase.getActualStartTimeOffset());
            } else {
                phase.setScheduledStartTimeOffset(prevPhase.getActualEndTimeOffset());
            }
        }
        phase.setScheduledEndTimeOffset(phase.getScheduledStartTimeOffset() + phase.getDuration() / 1000 / 60);
        phase.setActualStartTimeOffset(phase.getScheduledStartTimeOffset());
        phase.setActualEndTimeOffset(phase.getScheduledEndTimeOffset());
        if (phase.getPhaseType() == PhaseType.SPECIFICATION_SUBMISSION) {
            phase.setFixedStartTimeOffset(phase.getScheduledStartTimeOffset());
        } else {
            phase.setFixedStartTimeOffset(null);
        }
    }

    /**
     * <p>Creates new fully-initialized phase of specified type for specified project.</p>
     *
     * @param project a <code>Project</code> providing the details for target project. 
     * @param phaseType a <code>PhaseType</code> referencing the type of phase.
     * @param currentOpenPhaseType a <code>PhaseType</code> referencing the type of phase which is to be opened.
     * @param phases a <code>List</code> collecting the phases.
     * @param phaseId a <code>long</code> providing the ID of a phase.
     * @param duration a <code>long</code> providing the duration of a phase in hours.
     * @return a <code>Phase</code> of specified type for specified project.
     */
    private Phase createPhase(Project project, PhaseType phaseType, PhaseType currentOpenPhaseType,
                              List<Phase> phases, long phaseId, long duration) {

        boolean isStudio = project.getProjectCategory().getProjectType() == ProjectType.STUDIO;
        Phase phase = new Phase();
        phases.add(phase);

        phase.setPhaseType(phaseType);
        phase.setPhaseId(phaseId);
        phase.setDuration(duration * 60 * 60 * 1000L);

        ProjectStatus projectStatus = project.getProjectStatus();
        if (projectStatus == ProjectStatus.ACTIVE) {
            if (currentOpenPhaseType == null) {
                schedule(phase, phases);
            } else if ((currentOpenPhaseType.getPhaseTypeId() == phaseType.getPhaseTypeId()) 
                       || (currentOpenPhaseType == PhaseType.REGISTRATION 
                           && (phaseType == PhaseType.MILESTONE_SUBMISSION || phaseType == PhaseType.SUBMISSION))) {
                open(phase, phases);
            } else if (currentOpenPhaseType.getOrdinal() < phaseType.getOrdinal()) {
                schedule(phase, phases);
            } else {
                close(phase, phases);
            }
        }

        if (phaseType == PhaseType.SPECIFICATION_SUBMISSION) {
            if (projectStatus == ProjectStatus.ACTIVE) {
            } else if (projectStatus == ProjectStatus.INACTIVE) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.DELETED) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_CLIENT_REQUEST) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_REQUIREMENTS_INFEASIBLE) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_WINNER_UNRESPONSIVE) {
                close(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_ZERO_REGISTRATIONS) {
                close(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_ZERO_SUBMISSIONS) {
                close(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_FAILED_SCREENING) {
                close(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_FAILED_REVIEW) {
                close(phase, phases);
            } else if (projectStatus == ProjectStatus.COMPLETED) {
                close(phase, phases);
            }
        } else if (phaseType == PhaseType.SPECIFICATION_REVIEW) {
            phase.addCriteria(PhaseCriteriaType.SCORECARD_ID, 
                              String.valueOf(Scorecard.DEFAULT_SPEC_REVIEW_SCORECARD.getScorecardId()));
            phase.addCriteria(PhaseCriteriaType.REVIEWER_NUMBER, "1");
            phase.setReviewScorecard(Scorecard.DEFAULT_SPEC_REVIEW_SCORECARD);

            if (projectStatus == ProjectStatus.ACTIVE) {
            } else if (projectStatus == ProjectStatus.INACTIVE) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.DELETED) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_CLIENT_REQUEST) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_REQUIREMENTS_INFEASIBLE) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_WINNER_UNRESPONSIVE) {
                close(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_ZERO_REGISTRATIONS) {
                close(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_ZERO_SUBMISSIONS) {
                close(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_FAILED_SCREENING) {
                close(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_FAILED_REVIEW) {
                close(phase, phases);
            } else if (projectStatus == ProjectStatus.COMPLETED) {
                close(phase, phases);
            }
        } else if (phaseType == PhaseType.REGISTRATION) {
            if (!isStudio) {
                phase.addCriteria(PhaseCriteriaType.REGISTRATION_NUMBER, "0");
                phase.addCriteria(PhaseCriteriaType.MANUAL_SCREENING, "No");
            }

            if (projectStatus == ProjectStatus.ACTIVE) {
            } else if (projectStatus == ProjectStatus.INACTIVE) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.DELETED) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_CLIENT_REQUEST) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_REQUIREMENTS_INFEASIBLE) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_WINNER_UNRESPONSIVE) {
                close(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_ZERO_REGISTRATIONS) {
                close(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_ZERO_SUBMISSIONS) {
                close(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_FAILED_SCREENING) {
                close(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_FAILED_REVIEW) {
                close(phase, phases);
            } else if (projectStatus == ProjectStatus.COMPLETED) {
                close(phase, phases);
            }
        } else if (phaseType == PhaseType.MILESTONE_SUBMISSION) {
            if (!isStudio) {
                phase.addCriteria(PhaseCriteriaType.SUBMISSION_NUMBER, "0");
                phase.addCriteria(PhaseCriteriaType.MANUAL_SCREENING, "Yes");
            }

            if (projectStatus == ProjectStatus.ACTIVE) {
            } else if (projectStatus == ProjectStatus.INACTIVE) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.DELETED) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_CLIENT_REQUEST) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_REQUIREMENTS_INFEASIBLE) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_WINNER_UNRESPONSIVE) {
                close(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_ZERO_REGISTRATIONS) {
                close(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_ZERO_SUBMISSIONS) {
                close(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_FAILED_SCREENING) {
                close(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_FAILED_REVIEW) {
                close(phase, phases);
            } else if (projectStatus == ProjectStatus.COMPLETED) {
                close(phase, phases);
            }
        } else if (phaseType == PhaseType.MILESTONE_SCREENING) {
            phase.setReviewScorecard(Scorecard.DEFAULT_MILESTONE_SCREENING_SCORECARD);
            phase.addCriteria(PhaseCriteriaType.SCORECARD_ID,
                              String.valueOf(phase.getReviewScorecard().getScorecardId()));
            if (!isStudio) {
                phase.addCriteria(PhaseCriteriaType.MANUAL_SCREENING, "No");
            }

            if (projectStatus == ProjectStatus.ACTIVE) {
            } else if (projectStatus == ProjectStatus.INACTIVE) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.DELETED) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_CLIENT_REQUEST) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_REQUIREMENTS_INFEASIBLE) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_WINNER_UNRESPONSIVE) {
                close(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_ZERO_REGISTRATIONS) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_ZERO_SUBMISSIONS) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_FAILED_SCREENING) {
                close(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_FAILED_REVIEW) {
                close(phase, phases);
            } else if (projectStatus == ProjectStatus.COMPLETED) {
                close(phase, phases);
            }
        } else if (phaseType == PhaseType.MILESTONE_REVIEW) {
            phase.setReviewScorecard(Scorecard.DEFAULT_MILESTONE_REVIEW_SCORECARD);
            phase.addCriteria(PhaseCriteriaType.SCORECARD_ID,
                              String.valueOf(phase.getReviewScorecard().getScorecardId()));
            if (!isStudio) {
                phase.addCriteria(PhaseCriteriaType.MANUAL_SCREENING, "No");
                phase.addCriteria(PhaseCriteriaType.REVIEWER_NUMBER, "3");
            } else {
                phase.addCriteria(PhaseCriteriaType.REVIEWER_NUMBER, "1");
            }

            if (projectStatus == ProjectStatus.ACTIVE) {
            } else if (projectStatus == ProjectStatus.INACTIVE) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.DELETED) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_CLIENT_REQUEST) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_REQUIREMENTS_INFEASIBLE) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_WINNER_UNRESPONSIVE) {
                close(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_ZERO_REGISTRATIONS) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_ZERO_SUBMISSIONS) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_FAILED_SCREENING) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_FAILED_REVIEW) {
                close(phase, phases);
            } else if (projectStatus == ProjectStatus.COMPLETED) {
                close(phase, phases);
            }
        } else if (phaseType == PhaseType.SUBMISSION) {
            if (!isStudio) {
                phase.addCriteria(PhaseCriteriaType.SUBMISSION_NUMBER, "0");
                phase.addCriteria(PhaseCriteriaType.MANUAL_SCREENING, "Yes");
            }

            if (projectStatus == ProjectStatus.ACTIVE) {
            } else if (projectStatus == ProjectStatus.INACTIVE) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.DELETED) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_CLIENT_REQUEST) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_REQUIREMENTS_INFEASIBLE) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_WINNER_UNRESPONSIVE) {
                close(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_ZERO_REGISTRATIONS) {
                close(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_ZERO_SUBMISSIONS) {
                close(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_FAILED_SCREENING) {
                close(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_FAILED_REVIEW) {
                close(phase, phases);
            } else if (projectStatus == ProjectStatus.COMPLETED) {
                close(phase, phases);
            }
        } else if (phaseType == PhaseType.SCREENING) {
            if (isStudio) {
                phase.setReviewScorecard(Scorecard.DEFAULT_APPLICATION_SCREENING_SCORECARD);
            } else if (project.getProjectCategory() == ProjectCategory.DESIGN) {
                phase.setReviewScorecard(Scorecard.DEFAULT_DESIGN_SCREENING_SCORECARD);
            } else if (project.getProjectCategory() == ProjectCategory.DEVELOPMENT) {
                phase.setReviewScorecard(Scorecard.DEFAULT_DEVELOPMENT_SCREENING_SCORECARD);
            } else if (project.getProjectCategory() == ProjectCategory.SPECIFICATION) {
                phase.setReviewScorecard(Scorecard.DEFAULT_APPLICATION_SCREENING_SCORECARD);
            } else if (project.getProjectCategory() == ProjectCategory.COPILOT_POSTING) {
                phase.setReviewScorecard(Scorecard.DEFAULT_COPILOT_SELECTION_SCREENING_SCORECARD);
            } else {
                phase.setReviewScorecard(Scorecard.DEFAULT_DESIGN_SCREENING_SCORECARD);
            }
            phase.addCriteria(PhaseCriteriaType.SCORECARD_ID,
                              String.valueOf(phase.getReviewScorecard().getScorecardId()));
            if (!isStudio) {
                phase.addCriteria(PhaseCriteriaType.MANUAL_SCREENING, "No");
            }

            if (projectStatus == ProjectStatus.ACTIVE) {
            } else if (projectStatus == ProjectStatus.INACTIVE) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.DELETED) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_CLIENT_REQUEST) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_REQUIREMENTS_INFEASIBLE) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_WINNER_UNRESPONSIVE) {
                close(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_ZERO_REGISTRATIONS) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_ZERO_SUBMISSIONS) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_FAILED_SCREENING) {
                close(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_FAILED_REVIEW) {
                close(phase, phases);
            } else if (projectStatus == ProjectStatus.COMPLETED) {
                close(phase, phases);
            }
        } else if (phaseType == PhaseType.REVIEW) {
            if (isStudio) {
                phase.setReviewScorecard(Scorecard.DEFAULT_STUDIO_REVIEW_SCORECARD);
            } else if (project.getProjectCategory() == ProjectCategory.DESIGN) {
                phase.setReviewScorecard(Scorecard.DEFAULT_DESIGN_REVIEW_SCORECARD);
            } else if (project.getProjectCategory() == ProjectCategory.DEVELOPMENT) {
                phase.setReviewScorecard(Scorecard.DEFAULT_DEVELOPMENT_REVIEW_SCORECARD);
            } else if (project.getProjectCategory() == ProjectCategory.SPECIFICATION) {
                phase.setReviewScorecard(Scorecard.DEFAULT_APPLICATION_REVIEW_SCORECARD);
            } else if (project.getProjectCategory() == ProjectCategory.COPILOT_POSTING) {
                phase.setReviewScorecard(Scorecard.DEFAULT_COPILOT_SELECTION_REVIEW_SCORECARD);
            } else {
                phase.setReviewScorecard(Scorecard.DEFAULT_DESIGN_REVIEW_SCORECARD);
            }
            phase.addCriteria(PhaseCriteriaType.SCORECARD_ID,
                              String.valueOf(phase.getReviewScorecard().getScorecardId()));
            if (!isStudio) {
                phase.addCriteria(PhaseCriteriaType.MANUAL_SCREENING, "No");
                phase.addCriteria(PhaseCriteriaType.REVIEWER_NUMBER, "3");
            } else {
                phase.addCriteria(PhaseCriteriaType.REVIEWER_NUMBER, "1");
            }

            if (projectStatus == ProjectStatus.ACTIVE) {
            } else if (projectStatus == ProjectStatus.INACTIVE) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.DELETED) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_CLIENT_REQUEST) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_REQUIREMENTS_INFEASIBLE) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_WINNER_UNRESPONSIVE) {
                close(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_ZERO_REGISTRATIONS) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_ZERO_SUBMISSIONS) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_FAILED_SCREENING) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_FAILED_REVIEW) {
                close(phase, phases);
            } else if (projectStatus == ProjectStatus.COMPLETED) {
                close(phase, phases);
            }
        } else if (phaseType == PhaseType.APPEALS) {
            phase.addCriteria(PhaseCriteriaType.VIEW_RESPONSE_DURING_APPEALS, "No");
            phase.addCriteria(PhaseCriteriaType.MANUAL_SCREENING, "No");

            if (projectStatus == ProjectStatus.ACTIVE) {
            } else if (projectStatus == ProjectStatus.INACTIVE) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.DELETED) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_CLIENT_REQUEST) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_REQUIREMENTS_INFEASIBLE) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_WINNER_UNRESPONSIVE) {
                close(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_ZERO_REGISTRATIONS) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_ZERO_SUBMISSIONS) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_FAILED_SCREENING) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_FAILED_REVIEW) {
                close(phase, phases);
            } else if (projectStatus == ProjectStatus.COMPLETED) {
                close(phase, phases);
            }
        } else if (phaseType == PhaseType.APPEALS_RESPONSE) {
            phase.addCriteria(PhaseCriteriaType.MANUAL_SCREENING, "No");

            if (projectStatus == ProjectStatus.ACTIVE) {
            } else if (projectStatus == ProjectStatus.INACTIVE) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.DELETED) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_CLIENT_REQUEST) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_REQUIREMENTS_INFEASIBLE) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_WINNER_UNRESPONSIVE) {
                close(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_ZERO_REGISTRATIONS) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_ZERO_SUBMISSIONS) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_FAILED_SCREENING) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_FAILED_REVIEW) {
                close(phase, phases);
            } else if (projectStatus == ProjectStatus.COMPLETED) {
                close(phase, phases);
            }
        } else if (phaseType == PhaseType.AGGREGATION) {
            phase.addCriteria(PhaseCriteriaType.MANUAL_SCREENING, "No");

            if (projectStatus == ProjectStatus.ACTIVE) {
            } else if (projectStatus == ProjectStatus.INACTIVE) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.DELETED) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_CLIENT_REQUEST) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_REQUIREMENTS_INFEASIBLE) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_WINNER_UNRESPONSIVE) {
                close(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_ZERO_REGISTRATIONS) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_ZERO_SUBMISSIONS) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_FAILED_SCREENING) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_FAILED_REVIEW) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.COMPLETED) {
                close(phase, phases);
            }
        } else if (phaseType == PhaseType.AGGREGATION_REVIEW) {
            phase.addCriteria(PhaseCriteriaType.MANUAL_SCREENING, "No");

            if (projectStatus == ProjectStatus.ACTIVE) {
            } else if (projectStatus == ProjectStatus.INACTIVE) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.DELETED) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_CLIENT_REQUEST) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_REQUIREMENTS_INFEASIBLE) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_WINNER_UNRESPONSIVE) {
                close(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_ZERO_REGISTRATIONS) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_ZERO_SUBMISSIONS) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_FAILED_SCREENING) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_FAILED_REVIEW) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.COMPLETED) {
                close(phase, phases);
            }
        } else if (phaseType == PhaseType.FINAL_FIX) {
            phase.addCriteria(PhaseCriteriaType.MANUAL_SCREENING, "No");

            if (projectStatus == ProjectStatus.ACTIVE) {
            } else if (projectStatus == ProjectStatus.INACTIVE) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.DELETED) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_CLIENT_REQUEST) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_REQUIREMENTS_INFEASIBLE) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_WINNER_UNRESPONSIVE) {
                open(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_ZERO_REGISTRATIONS) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_ZERO_SUBMISSIONS) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_FAILED_SCREENING) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_FAILED_REVIEW) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.COMPLETED) {
                close(phase, phases);
            }
        } else if (phaseType == PhaseType.FINAL_REVIEW) {
            phase.addCriteria(PhaseCriteriaType.MANUAL_SCREENING, "No");

            if (projectStatus == ProjectStatus.ACTIVE) {
            } else if (projectStatus == ProjectStatus.INACTIVE) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.DELETED) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_CLIENT_REQUEST) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_REQUIREMENTS_INFEASIBLE) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_WINNER_UNRESPONSIVE) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_ZERO_REGISTRATIONS) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_ZERO_SUBMISSIONS) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_FAILED_SCREENING) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_FAILED_REVIEW) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.COMPLETED) {
                close(phase, phases);
            }
        } else if (phaseType == PhaseType.APPROVAL) {
            phase.setReviewScorecard(Scorecard.DEFAULT_APPROVAL_SCORECARD);
            phase.addCriteria(PhaseCriteriaType.SCORECARD_ID,
                              String.valueOf(Scorecard.DEFAULT_APPROVAL_SCORECARD.getScorecardId()));
            phase.addCriteria(PhaseCriteriaType.REVIEWER_NUMBER, "1");

            if (projectStatus == ProjectStatus.ACTIVE) {
            } else if (projectStatus == ProjectStatus.INACTIVE) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.DELETED) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_CLIENT_REQUEST) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_REQUIREMENTS_INFEASIBLE) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_WINNER_UNRESPONSIVE) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_ZERO_REGISTRATIONS) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_ZERO_SUBMISSIONS) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_FAILED_SCREENING) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_FAILED_REVIEW) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.COMPLETED) {
                close(phase, phases);
            }
        } else if (phaseType == PhaseType.POST_MORTEM) {
            phase.setReviewScorecard(Scorecard.DEFAULT_POST_MORTEM_SCORECARD);
            phase.addCriteria(PhaseCriteriaType.SCORECARD_ID,
                              String.valueOf(Scorecard.DEFAULT_POST_MORTEM_SCORECARD.getScorecardId()));
            phase.addCriteria(PhaseCriteriaType.MANUAL_SCREENING, "No");

            if (projectStatus == ProjectStatus.ACTIVE) {
            } else if (projectStatus == ProjectStatus.INACTIVE) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.DELETED) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_CLIENT_REQUEST) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_REQUIREMENTS_INFEASIBLE) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_WINNER_UNRESPONSIVE) {
                schedule(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_ZERO_REGISTRATIONS) {
                open(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_ZERO_SUBMISSIONS) {
                open(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_FAILED_SCREENING) {
                open(phase, phases);
            } else if (projectStatus == ProjectStatus.CANCELLED_FAILED_REVIEW) {
                open(phase, phases);
            } else if (projectStatus == ProjectStatus.COMPLETED) {
                schedule(phase, phases);
            }
        }

        return phase;
    }

    /**
     * <p>Creates new resource with basic data like external reference ID, handle, payment status and registration date.
     * </p>
     *
     * @param user a <code>User</code> referencing the user account to be associated with resource. 
     * @param role a <code>ResourceRole</code> referencing the role to be assigned to resource. 
     * @param resourceId a <code>long</code> providing the ID for the resource.
     * @param phase a <code>Phase</code> referencing the phase to associate the resource to.  
     * @param resources a <code>List</code> to collect the created resource to. 
     * @return a <code>Resource</code> providing the basic data for resource. 
     */
    private Resource createResource(User user, ResourceRole role, long resourceId, Phase phase,
                                    List<Resource> resources) {
        Resource resource = new Resource();
        resources.add(resource);
        resource.setResourceId(resourceId);
        resource.setRole(role);
        if (user == null) {
            System.out.println("User is null for " + role.getName());
        }
        resource.setUser(user);
        resource.setPhase(phase);
        resource.setPaymentStatus("N/A");
        if (phase != null) {
            resource.setRegistrationDate(new Date(System.currentTimeMillis() 
                                                  + (phase.getScheduledStartTimeOffset() 
                                                     + resources.size()) * 60 * 1000));
        } else {
            resource.setRegistrationDate(new Date(System.currentTimeMillis() + resources.size() * 60 * 1000));
        }
        return resource;
    }

    /**
     * <p>Creates new resource with basic data like external reference ID, handle, payment status and registration date.
     * </p>
     *
     * @param user       a <code>User</code> referencing the user account to be associated with resource.
     * @param role       a <code>ResourceRole</code> referencing the role to be assigned to resource.
     * @param resourceId a <code>long</code> providing the ID for the resource.
     * @param phase      a <code>Phase</code> referencing the phase to associate the resource to.
     * @param resources  a <code>List</code> to collect the created resource to.
     * @return a <code>Resource</code> providing the basic data for resource.
     * @since 1.3
     */
    private Resource createResource(TopCoderUser user, ResourceRole role, long resourceId, Phase phase,
                                    List<Resource> resources) {
        Resource resource = new Resource();
        resources.add(resource);
        resource.setResourceId(resourceId);
        resource.setRole(role);
        if (user == null) {
            System.out.println("User is null for " + role.getName());
        }
        resource.setTopCoderUser(user);
        resource.setPhase(phase);
        resource.setPaymentStatus("N/A");
        if (phase != null) {
            resource.setRegistrationDate(new Date(System.currentTimeMillis()
                                                  + (phase.getScheduledStartTimeOffset()
                                                     + resources.size()) * 60 * 1000));
        } else {
            resource.setRegistrationDate(new Date(System.currentTimeMillis() + resources.size() * 60 * 1000));
        }
        return resource;
    }


    /**
     * <p>Gets the random item from the specified items.</p>
     * 
     * @param items a <code>Object</code> array to select item from. 
     * @return an <code>Object</code> selected randomly from specified list.
     */
    private <T> T getRandomItem(T[] items) {
        return items[getRandomInt(0, items.length - 1)];
    }
    
    /**
     * <p>Gets the specified number of random items from the specified items.</p>
     * 
     * @param items a <code>User</code> array to select items from.
     * @param itemCount an <code>int</code> providing the number of items to select.
     * @return an <code>Object</code> array selected randomly from specified list.
     */
    private <T> List<T> getRandomItems(T[] items, int itemCount) {
        List<T> result = new ArrayList<T>();
        List<T> remainingItems = new ArrayList<T>(Arrays.asList(items));
        for (int i = 0; i < itemCount && !remainingItems.isEmpty(); i++) {
            T item = getRandomItem((T[]) remainingItems.toArray());
            result.add(item);
            remainingItems.remove(item);
        }
        return result;
    }

    /**
     * <p>Adds specified SQL statement to list of sql statements for specified resource.</p>
     * 
     * @param resource a <code>Resource</code> providing the details for the resource. 
     * @param s a <code>String</code> providing the SQL statement to be added.
     */
    private void addResourceSql(Resource resource, String s) {
        String[] sql = resource.getSql();
        if (sql == null) {
            resource.setSql(new String[]{s});
        } else {
            List<String> newSql = new ArrayList<String>(Arrays.asList(sql));
            newSql.add(s);
            resource.setSql(newSql.toArray(new String[newSql.size()]));
        }
    }

    /**
     * <p>Adds specified upload to list of uploads for specified resource.</p>
     * 
     * @param resource a <code>Resource</code> providing the details for the resource. 
     * @param upload a <code>String</code> providing the SQL statement to be added.
     */
    private void addResourceUpload(Resource resource, Upload upload) {
        Upload[] uploads = resource.getUploads();
        if (uploads == null) {
            resource.setUploads(new Upload[]{upload});
        } else {
            List<Upload> newUploads = new ArrayList<Upload>(Arrays.asList(uploads));
            newUploads.add(upload);
            resource.setUploads(newUploads.toArray(new Upload[newUploads.size()]));
        }
    }
}
