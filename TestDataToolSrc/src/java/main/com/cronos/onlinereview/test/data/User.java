/*
 * Copyright (C) 2011-2014 TopCoder Inc., All Rights Reserved.
 */
package com.cronos.onlinereview.test.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * <p>An enumeration over the pre-defined user accounts.</p>
 *
 * <p>
 * Version 1.1 (Release Assembly - TopCoder System Test Data Generator Update 1 Assembly 1.0) Change notes:
 *   <ol>
 *     <li>Added {@link #COPILOT_CANDIDATES} array.</li>
 *   </ol>
 * </p>
 *
 * <p>
 * Version 1.2 (Release Assembly - TopCoder System Test Data Generator Update 2 Assembly 1.0) Change notes:
 *   <ol>
 *     <li>Deleted <code>SUBMITTER_CANDIDATES</code> array. (1.1.1.2)</li>
 *   </ol>
 * </p>
 *
 * <p>
 * Verson 1.3 (Test Data Preparation for topcoder Permission related performance improvement)
 * <ol>
 *     <li>Added {@link #getRandomUser()}</li>
 * </ol>
 * </p>
 * 
 * @author isv, GreatKevin
 * @version 1.3
 */
public enum User {

    DOK_TESTER(20, "dok_tester", 0),

    DOK_TESTER1(21, "dok_tester1", 0),

    HUNG(124764, "Hung", 0),

    TWIGHT(124766, "twight", 0),

    PARTHA(124772, "Partha", 0),

    SANDKING(124776, "sandking", 0),

    LIGHTSPEED(124834, "lightspeed", 0),

    REASSEMBLER(124835, "reassembler", 0),

    ANNEJ9NY(124836, "annej9ny", 0),

    PLINEHAN(124852, "plinehan", 0),

    CHELSEASIMON(124853, "chelseasimon", 0),

    WYZMO(124856, "wyzmo", 0),

    CARTAJS(124857, "cartajs", 0),

    KSMITH(124861, "ksmith", 0),

    YOSHI(124916, "Yoshi", 0),

    HEFFAN(132456, "heffan", 50),

    SUPER(132457, "super", 0),

    USER(132458, "user", 0),

    APPLICATIONS(22770213, "Applications", 100000),
    
    COMPONENTS(22719217, "Components", 100000),
    
    LCSUPPORT(22873364, "LCSUPPORT", 100000);
    
    public static final User[] SPECIFICATION_REVIEWER_CANDIDATES = new User[] {SUPER, YOSHI};
    
    public static final User[] REVIEWER_CANDIDATES = new User[] {CHELSEASIMON, WYZMO, KSMITH};
    
    public static final User[] PRIMARY_REVIEWER_CANDIDATES = new User[] {REASSEMBLER, ANNEJ9NY, PLINEHAN};
    
    public static final User[] COPILOT_CANDIDATES = new User[] {HEFFAN, USER, SUPER, REASSEMBLER, TWIGHT, WYZMO, 
                                                                LIGHTSPEED};

    public static final User[] ALL_USERS = User.values();

    public static final Random RANDOM = new Random();

    /**
     * <p>A <code>long</code> providing the ID of this user.</p>
     */
    private long userId;

    /**
     * <p>A <code>String</code> providing the name of this user.</p>
     */
    private String name;

    /**
     * <p>A <code>String</code> providing the email address for this user.</p>
     */
    private String email;

    /**
     * <p>A <code>int</code> providing the number of projects which are allowed to be assigned to user.</p>
     */
    private int projectLimit;

    /**
     * <p>Constructs new <code>User</code> instance with specified ID and name.</p>
     *
     * @param userId a <code>long</code> providing the ID of this user.
     * @param name a <code>String</code> providing the name of this user.
     * @param projectLimit a <code>int</code> providing the number of projects which are allowed to be assigned to user.
     */
    private User(long userId, String name, int projectLimit) {
        this.userId = userId;
        this.name = name;
        this.projectLimit = projectLimit;
    }

    /**
     * <p>Gets the name of this user.</p>
     *
     * @return a <code>String</code> providing the name of this user.
     */
    public String getName() {
        return this.name;
    }

    /**
     * <p>Gets the ID of this user.</p>
     *
     * @return a <code>long</code> providing the ID of this user.
     */
    public long getUserId() {
        return this.userId;
    }

    /**
     * <p>Gets the number of projects which are allowed to be assigned to user.</p>
     *
     * @return a <code>int</code> providing the number of projects which are allowed to be assigned to user.
     */
    public int getProjectLimit() {
        return this.projectLimit;
    }

    /**
     * <p>Gets the email address for this user.</p>
     *
     * @return a <code>String</code> providing the email address for this user.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * <p>Gets the project limits for users.</p>
     *
     * @return a <code>Map</code> mapping the user IDs to project limits.
     */
    public Map<Long, Integer> getProjectLimits() {
        Map<Long, Integer> result = new HashMap<Long, Integer>();
        User[] users = values();
        for (User user : users) {
            if (user.getProjectLimit() > 0) {
                result.put(user.getUserId(), user.getProjectLimit());
            }
        }
        return result;
    }

    /**
     * Gets a random user.
     *
     * @return the random user.
     * @since 1.3
     */
    public static User getRandomUser() {
        return ALL_USERS[RANDOM.nextInt(ALL_USERS.length)];
    }
}
