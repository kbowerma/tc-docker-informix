/*
 * Copyright (C) 2011-2012 TopCoder Inc., All Rights Reserved.
 */
package com.cronos.onlinereview.test.data.tcscatalog;

import com.cronos.onlinereview.test.data.ProjectPhaseTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * <p>An enumeration over the project categories. Corresponds to <code>tcs_catalog.project_category_lu</code> database
 * table.</p>
 *
 * <p>
 * Version 1.1 (Release Assembly - TopCoder System Test Data Generator Update 1 Assembly 1.0) Change notes:
 *   <ol>
 *     <li>Added items for <code>Studio</code> project categories.</li>
 *     <li>Updated all items to provide additional parameters like phase templates for single/multi-round formats.</li>
 *   </ol>
 * </p>
 *
 * <p>
 * Version 1.2 (Release Assembly - TopCoder System Test Data Generator Update 2 Assembly 1.0) Change notes:
 *   <ol>
 *     <li>Updated phase template for {@link #COPILOT_POSTING} project category. (1.1.4#2)</li>
 *     <li>Added {@link #REPORTING} item. (1.1.2.1)</li>
 *   </ol>
 * </p>
 *
 * @author isv
 * @version 1.2
 */
public enum ProjectCategory {

    DESIGN(1, "Design", ProjectType.COMPONENT, true, new long[]{4, 5, 6}, 39, true, ProjectPhaseTemplate.DESIGN, null),

    DEVELOPMENT(2, "Development", ProjectType.COMPONENT, true, new long[]{1, 2, 3}, 38, true,
                ProjectPhaseTemplate.DEVELOPMENT, null),

    SPECIFICATION(6, "Specification", ProjectType.APPLICATION, false, new long[]{16, 17, 18}, 43, true,
                  ProjectPhaseTemplate.APPLICATION, ProjectPhaseTemplate.APPLICATION_WITH_MILESTONE),

    ARCHITECTURE(7, "Architecture", ProjectType.APPLICATION, true, new long[]{10, 11, 12}, 41, true,
                 ProjectPhaseTemplate.APPLICATION, ProjectPhaseTemplate.APPLICATION_WITH_MILESTONE),

    TEST_SUITES(13, "Test Suites", ProjectType.APPLICATION, true, new long[]{19, 20, 21}, 44, true,
                ProjectPhaseTemplate.APPLICATION, ProjectPhaseTemplate.APPLICATION_WITH_MILESTONE),

    ASSEMBLY(14, "Assembly Competition", ProjectType.APPLICATION, true, new long[]{7, 8, 9}, 40, true,
             ProjectPhaseTemplate.APPLICATION, ProjectPhaseTemplate.APPLICATION_WITH_MILESTONE),

    UI_PROTOTYPES(19, "UI Prototypes", ProjectType.APPLICATION, true, new long[]{22, 23, 24}, 45, true,
                  ProjectPhaseTemplate.APPLICATION, ProjectPhaseTemplate.APPLICATION_WITH_MILESTONE),

    CONCEPTUALIZATION(23, "Conceptualization", ProjectType.APPLICATION, false, new long[]{13, 14, 15}, 42, true,
                      ProjectPhaseTemplate.APPLICATION, ProjectPhaseTemplate.APPLICATION_WITH_MILESTONE),

    RIA_BUILD(24, "RIA Build", ProjectType.APPLICATION, true, new long[]{25, 26, 27}, 46, true,
              ProjectPhaseTemplate.APPLICATION, ProjectPhaseTemplate.APPLICATION_WITH_MILESTONE),

    RIA_COMPONENT(25, "RIA Component", ProjectType.APPLICATION, true, new long[]{28, 29, 30}, 47, false,
                  ProjectPhaseTemplate.APPLICATION, ProjectPhaseTemplate.APPLICATION_WITH_MILESTONE),

    TEST_SCENARIOS(26, "Test Scenarios", ProjectType.APPLICATION, true, new long[]{31, 32, 33}, 48, true,
                   ProjectPhaseTemplate.APPLICATION, ProjectPhaseTemplate.APPLICATION_WITH_MILESTONE),

    COPILOT_POSTING(29, "Copilot Posting", ProjectType.APPLICATION, false, new long[]{60, 61, 62}, 0, false,
                    ProjectPhaseTemplate.COPILOT_POSTING, null),

    CONTENT_CREATION(35, "Content Creation", ProjectType.APPLICATION, false, new long[]{63, 64, 65}, 50, true,
                     ProjectPhaseTemplate.APPLICATION, null),

    BANNERS_ICONS(16, "Banners/Icons", ProjectType.STUDIO, false, new long[]{66}, 50, false,
                  ProjectPhaseTemplate.STUDIO, ProjectPhaseTemplate.STUDIO_WITH_MILESTONE),

    WEB_DESIGN(17, "Web Design", ProjectType.STUDIO, false, new long[]{67}, 50, false,
               ProjectPhaseTemplate.STUDIO, ProjectPhaseTemplate.STUDIO_WITH_MILESTONE),

    WIREFRAMES(18, "Wireframes", ProjectType.STUDIO, false, new long[]{68}, 50, false, ProjectPhaseTemplate.STUDIO, 
               ProjectPhaseTemplate.STUDIO_WITH_MILESTONE),

    LOGO_DESIGN(20, "Logo Design", ProjectType.STUDIO, false, new long[]{69}, 50, false,
                ProjectPhaseTemplate.STUDIO, ProjectPhaseTemplate.STUDIO_WITH_MILESTONE),

    PRINT_PRESENTATION(21, "Print/Presentation", ProjectType.STUDIO, false, new long[]{70}, 50, false,
                       ProjectPhaseTemplate.STUDIO, ProjectPhaseTemplate.STUDIO_WITH_MILESTONE),

    IDEA_GENERATION(22, "Idea Generation", ProjectType.STUDIO, false, new long[]{71}, 50, false,
                    ProjectPhaseTemplate.STUDIO, ProjectPhaseTemplate.STUDIO_WITH_MILESTONE),

    WIDGET_MOBILE_SCREEN_DESIGN(30, "Widget or Mobile Screen Design", ProjectType.STUDIO, false, new long[]{72}, 50, 
                                false, ProjectPhaseTemplate.STUDIO, ProjectPhaseTemplate.STUDIO_WITH_MILESTONE),

    FRONT_END_FLASH(31, "Front-End Flash", ProjectType.STUDIO, false, new long[]{73}, 50, false,
                    ProjectPhaseTemplate.STUDIO, ProjectPhaseTemplate.STUDIO_WITH_MILESTONE),

    APPLICATION_FRONT_END_DESIGN(32, "Application Front-End Design", ProjectType.STUDIO, false, new long[]{74}, 50, 
                                 false, ProjectPhaseTemplate.STUDIO, ProjectPhaseTemplate.STUDIO_WITH_MILESTONE),
    
    REPORTING(36, "Reporting", ProjectType.APPLICATION, false, new long[]{76, 77, 78}, 79, true,
              ProjectPhaseTemplate.APPLICATION, ProjectPhaseTemplate.APPLICATION_WITH_MILESTONE);

    private static List<ProjectCategory> CANDIDATES_FOR_RANDOM_SELECTION = new ArrayList<ProjectCategory>();

    /**
     * <p>A <code>long</code> providing the ID of this project category.</p>
     */
    private long projectCategoryId;

    /**
     * <p>A <code>String</code> providing the name of this project category.</p>
     */
    private String name;

    /**
     * <p>A <code>ProjectType</code> providing the type of the project this category belongs to.</p>
     */
    private ProjectType projectType;

    /**
     * <p>A <code>boolean</code> providing the flag indicating whether the projects of this category are required the
     * list of technologies to be set or not.</p>
     */
    private boolean technologyRequired;

    /**
     * <p>A <code>long</code> providing the list of IDs for records in review_resp table mapped to this category.</p>
     */
    private long[] reviewRespIds;

    /**
     * <p>A <code>long</code> providing the ID for record in review_resp table mapped to this category for spec
     * review.</p>
     */
    private long specReviewRespId;

    /**
     * <p>A <code>boolean</code> providing the flag indicating whether the user ratings for this category are supported
     * or not.</p>
     */
    private boolean rated;

    /**
     * <p>A <code>ProjectPhaseTemplate</code> providing the project phases template.</p>
     */
    private ProjectPhaseTemplate phasesTemplate;

    /**
     * <p>A <code>ProjectPhaseTemplate</code> providing the project phases templates for multi-round projects.</p>
     */
    private ProjectPhaseTemplate milestonePhasesTemplate;

    /**
     * <p>Constructs new <code>ProjectCategory</code> instance with specified ID and name.</p>
     *
     * @param projectCategoryId  a <code>long</code> providing the ID of this project category.
     * @param name               a <code>String</code> providing the name of this project category.
     * @param projectType        a <code>ProjectType</code> specifying the type of this project category.
     * @param technologyRequired <code>true</code> if projects of this category are required the list of technologies
     *                           to
     * @param reviewRespIds      a <code>long</code> array providing the list of IDs for records in review_resp table
     *                           mapped
     * @param specReviewRespId   a <code>long</code> providing the ID for record in review_resp table mapped to this
     * @param rated              <code>true</code> if user ratings for this project category are support;
     *                           <code>false</code> otherwise.
     * @param phasesTemplate     a <code>ProjectPhaseTemplate</code> providing the project phases template.
     * @param milestonePhasesTemplate a <code>ProjectPhaseTemplate</code> providing the project phases template.
     */
    private ProjectCategory(long projectCategoryId, String name, ProjectType projectType, boolean technologyRequired,
                            long[] reviewRespIds, long specReviewRespId, boolean rated,
                            ProjectPhaseTemplate phasesTemplate, ProjectPhaseTemplate milestonePhasesTemplate) {
        this.projectCategoryId = projectCategoryId;
        this.name = name;
        this.projectType = projectType;
        this.technologyRequired = technologyRequired;
        this.reviewRespIds = reviewRespIds;
        this.specReviewRespId = specReviewRespId;
        this.rated = rated;
        this.phasesTemplate = phasesTemplate;
        this.milestonePhasesTemplate = milestonePhasesTemplate;
    }

    /**
     * <p>Gets the name of this project category.</p>
     *
     * @return a <code>String</code> providing the name of this project category.
     */
    public String getName() {
        return this.name;
    }

    /**
     * <p>Gets the ID of this project category.</p>
     *
     * @return a <code>long</code> providing the ID of this project category.
     */
    public long getProjectCategoryId() {
        return this.projectCategoryId;
    }

    /**
     * <p>Gets the type of the project this category belongs to.</p>
     *
     * @return a <code>ProjectType</code> providing the type of the project this category belongs to.
     */
    public ProjectType getProjectType() {
        return this.projectType;
    }

    /**
     * <p>Gets the flag indicating whether the projects of this category are required the list of technologies to be set
     * or not.</p>
     *
     * @return a <code>boolean</code> providing the flag indicating whether the projects of this category are required
     *         the list of technologies to be ste or not.
     */
    public boolean getTechnologyRequired() {
        return this.technologyRequired;
    }

    /**
     * <p>Gets the list of IDs for records in review_resp table mapped to this category.</p>
     *
     * @return a <code>long</code> providing the list of IDs for records in review_resp table mapped to this category.
     */
    public long[] getReviewRespIds() {
        return this.reviewRespIds;
    }

    /**
     * <p>Gets the ID for record in review_resp table mapped to this category for spec review.</p>
     *
     * @return a <code>long</code> providing the ID for record in review_resp table mapped to this category for spec
     *         review.
     */
    public long getSpecReviewRespId() {
        return this.specReviewRespId;
    }

    /**
     * <p>Gets the flag indicating whether the user ratings for this category are supported or not.</p>
     *
     * @return a <code>boolean</code> providing the flag indicating whether the user ratings for this category are
     *         supported or not.
     */
    public boolean getRated() {
        return this.rated;
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
     * <p>Gets the project phases templates for multi-round projects.</p>
     *
     * @return a <code>ProjectPhaseTemplate</code> providing the project phases templates for multi-round projects.
     * @since 1.1
     */
    public ProjectPhaseTemplate getMilestonePhasesTemplate() {
        return this.milestonePhasesTemplate;
    }

    /**
     * <p>Gets the randmly selected value.</p>
     *
     * @return a <code>ProjectCategory</code> randomly selected.
     * @since 1.1
     */
    public static ProjectCategory getRandomValue() {
        if (CANDIDATES_FOR_RANDOM_SELECTION.isEmpty()) {
            CANDIDATES_FOR_RANDOM_SELECTION.addAll(Arrays.asList(ProjectCategory.values()));
        }
        int n = getRandomInt(0, CANDIDATES_FOR_RANDOM_SELECTION.size() - 1);
        return CANDIDATES_FOR_RANDOM_SELECTION.remove(n);
    }

    /**
     * <p>Generates a random value in specified range (inclusive).</p>
     *
     * @param min an <code>int</code> providing the minimum range value.
     * @param max an <code>int</code> providing the maximum range value.
     * @return an <code>int</code> providing the generated value.
     * @since 1.1
     */
    private static int getRandomInt(int min, int max) {
        Random random = new Random();
        int result;
        do {
            result = random.nextInt(max + 1);
        } while ((result < min) || (result > max));
        return result;
    }
}
