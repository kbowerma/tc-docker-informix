/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package com.cronos.onlinereview.test.data.commonoltp;

import java.util.Random;

/**
 * <p>Enum represents all the security roles</p>
 *
 * @author TCSASSEMBLER
 * @version 1.0
 */
public enum SecurityRole {

    MEMBER_ADMIN_ROUND_ACCESS(15471983, "Member Admin Round Access"),
    MEMBER_ADMIN_BASIC_PERMS(15471941, "Member Admin Basic Perms"),
    PUBLIC_PACTS_INTERNAL(2000150, "Public PACTS internal"),
    FULL_TCES_ACCESS(2000132, "Full TCES Access"),
    TESTING_TOOL_ADMINISTRATIVE_ACCESS(2000131, "Testing Tool Administrative Access"),
    TESTING_TOOL_RESULTS_ACCESS(2000130, "Testing Tool Results Access"),
    CORP_SECURE_PROCESSORS(2000128, "Corp Secure Processors"),
    CORP_PUBLIC_PROCESSORS(2000127, "Corp Public Processors"),
    CORP_PUBLIC_DIRECTORIES(2000126, "Corp Public Directories"),
    CORP_SECURE_DIRECTORIES(2000125, "Corp Secure Directories"),
    GROUP_TESTER_WRITER(2000124, "group_Tester/Writer"),
    GROUP_ANONYMOUS(2000123, "group_Anonymous"),
    GROUP_CANDIDATE(2000122, "group_Candidate"),
    GROUP_CORP_USER(2000121, "group_Corp User"),
    GROUP_ADMIN(2000120, "group_Admin"),
    HS_COACH_REGISTRATION(1800020, "HS Coach Registration"),
    HS_STUDENT_REGISTRATION(1800019, "HS Student Registration"),
    HS_SECURE_PROCESSORS(1800018, "HS Secure Processors"),
    HS_REGISTRATION_PROCESSORS(1800017, "HS Registration Processors"),
    HS_PUBLIC_PROCESSORS(1800016, "HS Public Processors"),
    HS_PUBLIC_DIRECTORIES(1800015, "HS Public Directories"),
    HS_SECURE_DIRECTORIES(1800014, "HS Secure Directories"),
    GROUP_STUDENT(1800008, "group_Student"),
    GROUP_COACH(1800007, "group_Coach"),
    SCORECARD_ADMINISTRATOR(390003, "Scorecard Administrator"),
    ADMIN_REGULAR_ROLE(390001, "Admin Regular Role"),
    ADMIN_SUPER_ROLE(9391, "Admin Super Role"),
    TC_OPERATIONS(9390, "TC Operations"),
    LIQUID_ADMINISTRATOR(9389, "Liquid Administrator"),
    TC_ACCOUNTING(9387, "TC Accounting"),
    COCKPIT_SOFTWARE_CONTEST_USER(9386, "Cockpit Software Contest User"),
    COCKPIT_ADMINISTRATOR(9385, "Cockpit Administrator"),
    COCKPIT_USER(9384, "Cockpit User"),
    PLATFORM_SPECIALIST(2088, "Platform Specialist"),
    TC_STAFF(2087, "TC Staff"),
    PUBLIC_AOLICQ_ACCESS(2085, "Public Aolicq Access"),
    AOLICQ_COMPETITOR(2084, "Aolicq Competitor"),
    AOLICQ_CONTEST_ADMINISTRATOR(2083, "Aolicq Contest Administrator"),
    ADMIN_DATA_DUMPS(2081, "Admin Data Dumps"),
    PIPELINE_TOPCODER_STAFF(2080, "Pipeline TopCoder Staff"),
    PUBLIC_WINFORMULA_ACCESS(2078, "Public Winformula Access"),
    WINFORMULA_COMPETITOR(2077, "Winformula Competitor"),
    WINFORMULA_CONTEST_ADMINISTRATOR(2076, "Winformula Contest Administrator"),
    PUBLIC_TRUVEO_ACCESS(2075, "Public Truveo Access"),
    TRUVEO_COMPETITOR(2074, "Truveo Competitor"),
    TRUVEO_CONTEST_ADMINISTRATOR(2073, "Truveo Contest Administrator"),
    PUBLIC_OPENAIM_ACCESS(2072, "Public OpenAIM Access"),
    OPENAIM_COMPETITOR(2071, "OpenAIM Competitor"),
    OPENAIM_CONTEST_ADMINISTRATOR(2070, "OpenAIM Contest Administrator"),
    EP_PUBLIC(2063, "EP Public"),
    EP_ADMIN(2062, "EP Admin"),
    EP_PROFESSOR(2061, "EP Professor"),
    EP_STUDENT(2060, "EP Student"),
    PUBLIC_THE_ORACLE_ACCESS(2049, "Public The Oracle Access"),
    THE_ORACLE_COMPETITOR(2048, "The Oracle Competitor"),
    THE_ORACLE_ADMINISTRATOR(2046, "The Oracle Administrator"),
    PUBLIC_TC_STUDIO_ACCESS(2042, "Public TC Studio Access"),
    TC_STUDIO_COMPETITOR(2041, "TC Studio Competitor"),
    TC_STUDIO_CONTEST_ADMINISTRATOR(2040, "TC Studio Contest Administrator"),
    SCREENING_PUBLIC_PROCESSORS(2030, "Screening Public Processors"),
    PUBLIC_RSS_FEEDS(2006, "Public RSS Feeds"),
    PACTS_SUPERVISOR(2005, "PACTS Supervisor"),
    ASSIGNMENT_DOCUMENTS_REPORTS(2004, "Assignment Documents Reports"),
    PACTS_INTERNAL(2003, "PACTS Internal"),
    PUBLIC_DATA_DUMPS(2002, "Public Data Dumps"),
    PUBLIC_QUERY_TOOL_ACCESS(2001, "Public Query Tool Access"),
    QUERY_TOOL_ACCESS(2000, "Query Tool Access"),
    MEMBER_CONTACT_ACCESS(1002, "Member Contact Access"),
    LONG_CONTEST_ADMIN(1001, "Long Contest Admin"),
    GENERAL_COMPETITION_USER(1000, "General Competition User"),
    VMMANAGER(101, "VMManager"),
    UNLIMITED_DOWNLOAD(100, "Unlimited download"),
    APPLICATION_SPECIFICATION_UPLOADERS(50, "Application Specification Uploaders"),
    IMPERSONATED_LOGIN(28, "Impersonated Login"),
    LIQUID_DATA_FEEDS(27, "Liquid Data Feeds"),
    GROUP_HS_COMPETITION_USER(26, "group_HS Competition User"),
    GROUP_COMPETITION_USER(25, "group_Competition User"),
    ADMIN_ACCESS(24, "Admin Access"),
    PUBLIC_ADMIN_ACCESS(23, "Public Admin Access"),
    DOT_NET_CATALOG_SUBSCRIPTION(22, ".NET Catalog Subscription"),
    JAVA_CATALOG_SUBSCRIPTION(21, "Java Catalog Subscription"),
    COMPONENT_SUBSCRIPTION(20, "Component Subscription"),
    USER(2, "user"),
    ADMINISTRATOR(1, "Administrator");

    private long id;

    private String name;

    private static final SecurityRole[] ALL_ROLES = SecurityRole.values();

    private static final Random RANDOM = new Random();

    SecurityRole(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static SecurityRole getRandomSecurityRole() {
        return ALL_ROLES[RANDOM.nextInt(ALL_ROLES.length)];
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
