/*
 * Copyright (C) 2011-2012 TopCoder Inc., All Rights Reserved.
 */
package com.cronos.onlinereview.test.data.tcscatalog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * <p>An enumeration over the project statuses. Corresponds to <code>tcs_catalog.project_status_lu</code> database
 * table.</p>
 *
 * <p>
 * Version 1.1 (Release Assembly - TopCoder System Test Data Generator Update 1 Assembly 1.0) Change notes:
 *   <ol>
 *     <li>Added {@link #CANDIDATES_FOR_RANDOM_SELECTION} property.</li>
 *     <li>Added {@link #getRandomValue()} method.</li>
 *   </ol>
 * </p>
 * 
 * @author isv
 * @version 1.1
 */
public enum ProjectStatus {
    
    ACTIVE(1, "Active"),

    INACTIVE(2, "Inactive"),

    DELETED(3, "Deleted"),

    CANCELLED_FAILED_REVIEW(4, "Cancelled - Failed Review"),

    CANCELLED_FAILED_SCREENING(5, "Cancelled - Failed Screening"),

    CANCELLED_ZERO_SUBMISSIONS(6, "Cancelled - Zero Submissions"),

    COMPLETED(7, "Completed"),

    CANCELLED_WINNER_UNRESPONSIVE(8, "Cancelled - Winner Unresponsive"),

    CANCELLED_CLIENT_REQUEST(9, "Cancelled - Client Request"),

    CANCELLED_REQUIREMENTS_INFEASIBLE(10, "Cancelled - Requirements Infeasible"),

    CANCELLED_ZERO_REGISTRATIONS(11, "Cancelled - Zero Registrations");

    private static List<ProjectStatus> CANDIDATES_FOR_RANDOM_SELECTION = new ArrayList<ProjectStatus>();

    /**
     * <p>A <code>long</code> providing the ID of this project status.</p>
     */
    private long projectStatusId;

    /**
     * <p>A <code>String</code> providing the name of this project status.</p>
     */
    private String name;

    /**
     * <p>Constructs new <code>ProjectStatus</code> instance with specified ID and name.</p>
     *
     * @param projectStatusId a <code>long</code> providing the ID of this project status.
     * @param name a <code>String</code> providing the name of this project status.
     */
    private ProjectStatus(long projectStatusId, String name) {
        this.projectStatusId = projectStatusId;
        this.name = name;
    }

    /**
     * <p>Gets the name of this project status.</p>
     *
     * @return a <code>String</code> providing the name of this project status.
     */
    public String getName() {
        return this.name;
    }

    /**
     * <p>Gets the ID of this project status.</p>
     *
     * @return a <code>long</code> providing the ID of this project status.
     */
    public long getProjectStatusId() {
        return this.projectStatusId;
    }

    /**
     * <p>Gets the randomly selected value.</p>
     *
     * @return a <code>ProjectCategory</code> randomly selected.
     * @since 1.1
     */
    public static ProjectStatus getRandomValue() {
        if (CANDIDATES_FOR_RANDOM_SELECTION.isEmpty()) {
            CANDIDATES_FOR_RANDOM_SELECTION.addAll(Arrays.asList(ProjectStatus.values()));
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
