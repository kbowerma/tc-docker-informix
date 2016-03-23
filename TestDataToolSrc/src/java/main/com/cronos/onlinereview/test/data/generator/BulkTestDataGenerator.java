/*
 * Copyright (C) 2011  -2014 TopCoder Inc., All Rights Reserved.
 */
package com.cronos.onlinereview.test.data.generator;

import com.cronos.onlinereview.test.data.ProjectPhaseTemplate;
import com.cronos.onlinereview.test.data.TopCoderUser;
import com.cronos.onlinereview.test.data.User;
import com.cronos.onlinereview.test.data.commonoltp.SecurityRole;
import com.cronos.onlinereview.test.data.commonoltp.TermsOfUse;
import com.cronos.onlinereview.test.data.corporateoltp.PermissionType;
import com.cronos.onlinereview.test.data.corporateoltp.TcDirectProject;
import com.cronos.onlinereview.test.data.corporateoltp.TcDirectProjectStatus;
import com.cronos.onlinereview.test.data.corporateoltp.UserPermissionGrant;
import com.cronos.onlinereview.test.data.generator.project.ProjectDataGenerator;
import com.cronos.onlinereview.test.data.generator.project.ProjectGeneratorConfig;
import com.cronos.onlinereview.test.data.tcscatalog.Catalog;
import com.cronos.onlinereview.test.data.tcscatalog.CopilotProfile;
import com.cronos.onlinereview.test.data.tcscatalog.CopilotProfileStatus;
import com.cronos.onlinereview.test.data.tcscatalog.CopilotProject;
import com.cronos.onlinereview.test.data.tcscatalog.CostLevel;
import com.cronos.onlinereview.test.data.tcscatalog.Group;
import com.cronos.onlinereview.test.data.tcscatalog.GroupMember;
import com.cronos.onlinereview.test.data.tcscatalog.GroupPermissionType;
import com.cronos.onlinereview.test.data.tcscatalog.PhaseType;
import com.cronos.onlinereview.test.data.tcscatalog.Project;
import com.cronos.onlinereview.test.data.tcscatalog.ProjectCategory;
import com.cronos.onlinereview.test.data.tcscatalog.ProjectInfoType;
import com.cronos.onlinereview.test.data.tcscatalog.ProjectStatus;
import com.cronos.onlinereview.test.data.tcscatalog.ProjectType;
import com.cronos.onlinereview.test.data.timeoltp.AccountStatus;
import com.cronos.onlinereview.test.data.timeoltp.BillingProject;
import com.cronos.onlinereview.test.data.timeoltp.BillingProjectManager;
import com.cronos.onlinereview.test.data.timeoltp.BillingProjectStatus;
import com.cronos.onlinereview.test.data.timeoltp.BillingProjectWorker;
import com.cronos.onlinereview.test.data.timeoltp.Client;
import com.cronos.onlinereview.test.data.timeoltp.ClientStatus;
import com.cronos.onlinereview.test.data.timeoltp.Company;
import com.cronos.onlinereview.test.data.timeoltp.PaymentTerms;
import com.cronos.onlinereview.test.data.timeoltp.UserAccount;
import com.topcoder.util.commandline.CommandLineUtility;
import com.topcoder.util.commandline.IntegerValidator;
import com.topcoder.util.commandline.Switch;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * <p>A class generating the sample data to represent multiple projects and client accounts in <code>Online Review
 * </code>, <code>TopCoder Direct</code> applications covering various possible variations of projects and clients.</p>
 *
 * <p>
 * Version 1.1 Change notes:
 *   <ol>
 *     <li>Updated {@link #generateContests(int, int, int, int, int)} method to set copilot for generated contest.
 *     </li>
 *     <li>Updated {@link #generateContests(int, int, int, int, int)} method to set project categories for 
 *     generated contests randomly instead of sequentially.</li>
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
 *     <li>Updated {@link #generateBillingProjects(int, int)} method to set billing account expiration dates to be far 
 *     in the future. (1.1.4#1)</li>
 *     <li>Updated {@link #generateTcDirectProjects(int, int, int, int, int, int, int)} method to configure the 
 *     parameters for project fulfillment. (1.1.1.1)</li>
 *     <li>Added {@link #contestRegistrants} property. (1.1.1.2)</li>
 *     <li>Updated {@link #generateContests(int, int, int, int, int)} method to generate data for contest 
 *     registrants.(1.1.1.2)</li>
 *     <li>Added {@link #USER_HANDLE_SAMPLES}, {@link #USER_FIRST_NAMES}, {@link #USER_LAST_NAMES} constants. (1.1.1.2)
 *     </li>
 *     <li>Updated {@link #generateContests(int, int, int, int, int)} method to configure the submission generation
 *     parameters.(1.1.1.3, 1.1.1.4)</li>
 *     <li>Added {@link #copilotProfiles} property. (1.1.2.3)</li>
 *     <li>Updated {@link #generateTcDirectProjects(int, int, int, int, int, int, int)} method to set copilots for 
 *     project. (1.1.2.5)</li>
 *   </ol>
 * </p>
 *
 * <p>
 * Version 1.4 (Test Data Preparation for topcoder Permission related performance improvement) change notes:
 * <ol>
 *     <li>Add group related test data</li>
 *     <li>Add user_role_xref test data</li>
 * </ol>
 * </p>
 * 
 * @author isv, GreatKevin
 * @version 1.4
 */
public class BulkTestDataGenerator {

    private static int MAX_NUMBER_CLIENT_PER_BATCH = 100;

    /**
     * <p>A <code>String</code> array providing the bases for handles for generated coder accounts.</p>
     */
    public static final String[] USER_HANDLE_SAMPLES = {"dok", "heffan", "hohosky", "flexme", "pvmagacho", 
                                                        "TonyJ", "jmpld", "jwlms", "mess", "marcg", "bblais", 
                                                        "ThinMan", "srowen", "Pops", "WishingBone"};

    /**
     * <p>A <code>String</code> array providing the sample first names for generated coder accounts.</p>
     */
    public static final String[] USER_FIRST_NAMES = {"John", "Jessica", "Jessie", "Marc", "Tony",
                                                    "Bill", "Rowan", "Charlize", "Dave"};

    /**
     * <p>A <code>String</code> array providing the sample last names for generated coder accounts.</p>
     */
    public static final String[] USER_LAST_NAMES = {"Smith", "Doe", "Theron", "Atkinson", "Blais", "Grzeskowiak", 
                                                    "Bollinger", "Roberts", "Wu", "Messinger"};

    /**
     * <p>A <code>List</code> listing the generated company accounts.</p>
     */
    private List<Company> companies = new ArrayList<Company>();

    /**
     * <p>A <code>List</code> listing the generated client accounts.</p>
     */
    private List<Client> clients = new ArrayList<Client>();

    /**
     * <p>A <code>List</code> listing the generated payment terms.</p>
     */
    private List<PaymentTerms> paymentTerms = new ArrayList<PaymentTerms>();

    /**
     * <p>A <code>List</code> listing the generated billing projects.</p>
     */
    private List<BillingProject> billingProjects = new ArrayList<BillingProject>();
    
    /**
     * <p>A <code>List</code> listing the generated TC Direct projects.</p>
     */
    private List<TcDirectProject> tcDirectProjects = new ArrayList<TcDirectProject>();
    
    /**
     * <p>A <code>List</code> listing the generated user permission grants.</p>
     */
    private List<UserPermissionGrant> userPermissionGrants = new ArrayList<UserPermissionGrant>();
    
    /**
     * <p>A <code>List</code> listing the generated user accounts.</p>
     */
    private List<UserAccount> billingUserAccounts = new ArrayList<UserAccount>();
    
    /**
     * <p>A <code>List</code> listing the generated billing project managers.</p>
     */
    private List<BillingProjectManager> billingProjectManagers = new ArrayList<BillingProjectManager>();

    /**
     * <p>A <code>List</code> listing the generated copilot profiles.</p>
     */
    private List<CopilotProfile> copilotProfiles = new ArrayList<CopilotProfile>();
    
    /**
     * <p>A <code>List</code> listing the generated billing project workers.</p>
     */
    private List<BillingProjectWorker> billingProjectWorkers = new ArrayList<BillingProjectWorker>();

    /**
     * <p>A <code>List</code> listing the generated contests.</p>
     */
    private List<Project> contests = new ArrayList<Project>();

    /**
     * <p>A <code>List<TopCoderUser></code> providing the data for generated contest registrants.</p>
     * 
     * @since 1.3
     */
    private List<TopCoderUser> contestRegistrants = new ArrayList<TopCoderUser>();

    /**
     * <p>A <code>List<Group></code> providing the data for generated groups.</p>
     *
     * @since 1.4
     */
    private List<Group> groups = new ArrayList<Group>();
    /**
     * <p>A <code>List<TopCoderUser></code> providing the data for users of direct project.</p>
     *
     * @since 1.4
     */
    private List<TopCoderUser> directProjectUsers = new ArrayList<TopCoderUser>();

    /**
     * <p>A <code>Random</code> to be used for generating random numbers.</p>
     */
    private static Random random = new Random(System.currentTimeMillis());

    /**
     * <p>A <code>long</code> providing the starting ID for all ID generation sequences.</p>
     */
    private final long idGeneratorStart;

    /**
     * <p>A <code>String</code> providing the path to a file with available IDs for Studio submissions.</p>
     */
    private final String studioSubmissionIdsFileName;

    /**
     * All the fields of the BulkTestDataGenerator.
     *
     * @since 1.4
     */
    private static final Field[] FIELDS = BulkTestDataGenerator.class.getDeclaredFields();

    /**
     * Clear all the generated data via reflection.
     *
     * @throws Exception if any error.
     *
     * @since 1.4
     */
    public void clearData() throws Exception {
        for (Field field : FIELDS) {
            if (!Modifier.isStatic(field.getModifiers()) && List.class.isAssignableFrom(field.getType())) {
                // non static list - clear the data
                List ol = (List) field.get(this);
                ol.clear();
            }
        }
    }

    /**
     * <p>Constructs new <code>BulkTestDataGenerator</code> instance. This implementation does nothing.</p>
     * 
     * @param idGeneratorStart a <code>long</code> providing the starting ID for all ID generation sequences.
     * @param studioSubmissionIdsFileName a <code>String</code> providing the path to a file with available IDs for 
     *                                    Studio submissions.
     * @throws IllegalArgumentException if specified <code>idGeneratorStart</code> is negative or specified 
     *         <code>studioSubmissionIdsFileName</code> is <code>null</code>  or empty.  
     */
    public BulkTestDataGenerator(long idGeneratorStart, String studioSubmissionIdsFileName) {
        if (idGeneratorStart < 0) {
            throw new IllegalArgumentException("idGeneratorStart must be positive");
        }
        if ((studioSubmissionIdsFileName == null) || (studioSubmissionIdsFileName.trim().length() == 0)) {
            throw new IllegalArgumentException("The parameter [studioSubmissionIdsFileName] is not valid. [" 
                                               + studioSubmissionIdsFileName + "]");
        }
        this.idGeneratorStart = idGeneratorStart;
        this.studioSubmissionIdsFileName = studioSubmissionIdsFileName;
    }

    /**
     * <p>A command line interface.</p>
     * 
     * @param args a <code>String</code> array providing the command line arguments.
     * @throws Exception if an unexpected error occurs.
     */
    public static void main(String[] args) throws Exception {
        // Parse command line arguments
        CommandLineUtility clu = new CommandLineUtility();
        clu.addSwitch(new Switch("detailedContestData", true, 1, 1, new IntegerValidator(),
                "DetailedContestData - whether the detailed contest data is needed, 0 is false, other values are true"));
        clu.addSwitch(new Switch("clientsCount", true, 1, 1, new IntegerValidator(),
                                 "Number of client accounts to be generated"));
        clu.addSwitch(new Switch("clientsPerBatch", false, 1, 1, new IntegerValidator(),
                "Number of client accounts to be generated on 1 batch."));
        clu.addSwitch(new Switch("minBillingCount", true, 1, 1, new IntegerValidator(),
                                 "Minimum number of billing account per client to be generated"));
        clu.addSwitch(new Switch("maxBillingCount", true, 1, 1, new IntegerValidator(),
                                 "Maximum number of billing account per client to be generated"));
        clu.addSwitch(new Switch("minDirectCount", true, 1, 1, new IntegerValidator(),
                                 "Minimum number of TC Direct projects per billing account to be generated"));
        clu.addSwitch(new Switch("maxDirectCount", true, 1, 1, new IntegerValidator(),
                                 "Maximum number of TC Direct projects per billing account to be generated"));
        clu.addSwitch(new Switch("minActiveContestsCount", true, 1, 1, new IntegerValidator(),
                                 "Minimum number of active contests per TC Direct project to be generated"));
        clu.addSwitch(new Switch("maxActiveContestsCount", true, 1, 1, new IntegerValidator(),
                                 "Maximum number of active contests per TC Direct project to be generated"));
        clu.addSwitch(new Switch("groupPerClient", true, 1, 1, new IntegerValidator(),
                "The number of groups per client to be generated"));
        clu.addSwitch(new Switch("outputFile", true, 1, 1, null,
                                 "A path to a file to write generated SQL statements to"));
        clu.addSwitch(new Switch("idGeneratorStart", true, 1, 1, new IntegerValidator(),
                                 "An initial starting ID for all ID generation sequences."));
        clu.addSwitch(new Switch("studioSubmissionIdsFile", true, 1, 1, null, 
                                 "A path to file with available IDs for Studio submissions"));
        clu.addSwitch(new Switch("submissionDeclarationsFile", true, 1, 1, null,
                                 "A path to a text file to write the data for submission declarations"));
        clu.addSwitch(new Switch("minContestRegistrantsCount", true, 1, 1, new IntegerValidator(),
                                 "Minimum number of registrants per contest to be generated"));
        clu.addSwitch(new Switch("maxContestRegistrantsCount", true, 1, 1, new IntegerValidator(),
                                 "Maximum number of registrants per contest to be generated"));
        clu.addSwitch(new Switch("averageContestRegistrantsCount", true, 1, 1, new IntegerValidator(),
                                 "Average number of registrants per contest to be generated"));
        clu.addSwitch(new Switch("averageContestSubmissionsCount", true, 1, 1, new IntegerValidator(),
                                 "Average number of submissions per contest to be generated"));
        clu.addSwitch(new Switch("minProjectFulfillment", true, 1, 1, new IntegerValidator(1, 100),
                                 "Minimum project fulfillment"));
        clu.addSwitch(new Switch("maxProjectFulfillment", true, 1, 1, new IntegerValidator(1, 100),
                                 "Maximum project fulfillment"));
        clu.addSwitch(new Switch("averageProjectFulfillment", true, 1, 1, new IntegerValidator(1, 100),
                                 "Average project fulfillment"));
        clu.addSwitch(new Switch("averageContestSubmissionsFailureRate", true, 1, 1, new IntegerValidator(1, 100),
                                 "Average contest submissions failure rate"));
        clu.addSwitch(new Switch("termsOutputFile", true, 1, 1, null,
                                 "A path to a file to write note on users terms of use acceptance"));
        clu.parse(args);

        boolean needDetailedContestData = Integer.parseInt(clu.getSwitch("detailedContestData").getValue()) != 0;
        int clientsCount = Integer.parseInt(clu.getSwitch("clientsCount").getValue());

        // set clientsPerBatch to default value first
        int clientsPerBatch = MAX_NUMBER_CLIENT_PER_BATCH;

        // try getting value from switch if there is
        if (clu.getSwitch("clientsPerBatch") != null && clu.getSwitch("clientsPerBatch").getValue() != null) {
            clientsPerBatch = Integer.parseInt(clu.getSwitch("clientsPerBatch").getValue());
        }

        int minBillingCount = Integer.parseInt(clu.getSwitch("minBillingCount").getValue());
        int maxBillingCount = Integer.parseInt(clu.getSwitch("maxBillingCount").getValue());
        int minDirectCount = Integer.parseInt(clu.getSwitch("minDirectCount").getValue());
        int maxDirectCount = Integer.parseInt(clu.getSwitch("maxDirectCount").getValue());
        int minActiveContestsCount = Integer.parseInt(clu.getSwitch("minActiveContestsCount").getValue());
        int maxActiveContestsCount = Integer.parseInt(clu.getSwitch("maxActiveContestsCount").getValue());
        int idGeneratorStart = Integer.parseInt(clu.getSwitch("idGeneratorStart").getValue());
        int groupPerClient = Integer.parseInt(clu.getSwitch("groupPerClient").getValue());
        String studioIdsFile = clu.getSwitch("studioSubmissionIdsFile").getValue();
        String submissionDeclarationsFile = clu.getSwitch("submissionDeclarationsFile").getValue();
        String outputFileName = clu.getSwitch("outputFile").getValue();
        String termsOutputFileName = clu.getSwitch("termsOutputFile").getValue();
        int minContestRegistrantsCount = Integer.parseInt(clu.getSwitch("minContestRegistrantsCount").getValue());
        int maxContestRegistrantsCount = Integer.parseInt(clu.getSwitch("maxContestRegistrantsCount").getValue());
        int averageContestRegistrantsCount = Integer.parseInt(
            clu.getSwitch("averageContestRegistrantsCount").getValue());
        int averageContestSubmissionsCount = Integer.parseInt(
            clu.getSwitch("averageContestSubmissionsCount").getValue());
        int minProjectFulfillment = Integer.parseInt(clu.getSwitch("minProjectFulfillment").getValue());
        int maxProjectFulfillment = Integer.parseInt(clu.getSwitch("maxProjectFulfillment").getValue());
        int averageProjectFulfillment = Integer.parseInt(
            clu.getSwitch("averageProjectFulfillment").getValue());
        int averageContestSubmissionsFailureRate = Integer.parseInt(
            clu.getSwitch("averageContestSubmissionsFailureRate").getValue());

        // Validate input parameters
        if (minContestRegistrantsCount <= 0) {
            throw new IllegalArgumentException("minContestRegistrantsCount is not positive");
        }
        if (maxContestRegistrantsCount <= 0) {
            throw new IllegalArgumentException("maxContestRegistrantsCount is not positive");
        }
        if (maxContestRegistrantsCount < minContestRegistrantsCount) {
            throw new IllegalArgumentException("maxContestRegistrantsCount is less than minContestRegistrantsCount");
        }
        if (! (averageContestRegistrantsCount >= minContestRegistrantsCount 
               && averageContestRegistrantsCount <= maxContestRegistrantsCount) ) {
            throw new IllegalArgumentException("averageContestRegistrantsCount is not between " +
                                               "minContestRegistrantsCount and maxContestRegistrantsCount");
        }
        if (averageContestSubmissionsCount <= 0) {
            throw new IllegalArgumentException("averageContestSubmissionsCount is not positive");
        }
        if (averageContestSubmissionsCount > maxContestRegistrantsCount) {
            throw new IllegalArgumentException("averageContestSubmissionsCount is greater than " +
                                               "maxContestRegistrantsCount");
        }
        if (averageContestSubmissionsCount > averageContestRegistrantsCount) {
            throw new IllegalArgumentException("averageContestSubmissionsCount is greater than " +
                                               "averageContestRegistrantsCount");
        }
        if (maxProjectFulfillment < minProjectFulfillment) {
            throw new IllegalArgumentException("maxProjectFulfillment is less than minProjectFulfillment");
        }
        if (!(averageProjectFulfillment >= minProjectFulfillment
              && averageProjectFulfillment <= maxProjectFulfillment)) {
            throw new IllegalArgumentException("averageProjectFulfillment is not between " +
                                               "minProjectFulfillment and maxProjectFulfillment");
        }


        int clientNumberCount = clientsCount;
        long idGeneratorStartCount = idGeneratorStart;

        // Generate SQL statements for test data and write them to destination file
        PrintWriter out = new PrintWriter(outputFileName);
        PrintWriter submissionDeclarationsOut = new PrintWriter(submissionDeclarationsFile);
        PrintWriter termsOut = new PrintWriter(termsOutputFileName);

        try {

            for(;;) {

                int clientNumberToGenerate = clientNumberCount > clientsPerBatch ? clientsPerBatch : clientNumberCount;

                BulkTestDataGenerator generator = new BulkTestDataGenerator(idGeneratorStartCount, studioIdsFile);
                generator.generateBulkTestData(clientNumberToGenerate, minBillingCount, maxBillingCount, minDirectCount, maxDirectCount,
                        minActiveContestsCount, maxActiveContestsCount, minContestRegistrantsCount,
                        maxContestRegistrantsCount, averageContestRegistrantsCount,
                        averageContestSubmissionsCount,
                        minProjectFulfillment, maxProjectFulfillment, averageProjectFulfillment,
                        averageContestSubmissionsFailureRate, groupPerClient);

                out.println("DATABASE time_oltp;");

                TestDataSQLConverter sqlGenerator = new TestDataSQLConverter();

                List<Company> companies = generator.getCompanies();
                for (Company company : companies) {
                    sqlGenerator.generateSQLStatements(company, out);
                }

                List<PaymentTerms> paymentTerms = generator.getPaymentTerms();
                for (PaymentTerms paymentTerm : paymentTerms) {
                    sqlGenerator.generateSQLStatements(paymentTerm, out);
                }

                List<Client> clients = generator.getClients();
                for (Client client : clients) {
                    sqlGenerator.generateSQLStatements(client, out);
                }

                List<BillingProject> billingProjects = generator.getBillingProjects();
                for (BillingProject billingProject : billingProjects) {
                    sqlGenerator.generateSQLStatements(billingProject, out);
                }

                List<UserAccount> billingUserAccounts = generator.getBillingUserAccounts();
                for (UserAccount userAccount : billingUserAccounts) {
                    sqlGenerator.generateSQLStatements(userAccount, out);
                }

                List<BillingProjectManager> billingProjectManagers = generator.getBillingProjectManagers();
                for (BillingProjectManager billingProjectManager : billingProjectManagers) {
                    sqlGenerator.generateSQLStatements(billingProjectManager, out);
                }

                List<BillingProjectWorker> billingProjectWorkers = generator.getBillingProjectWorkers();
                for (BillingProjectWorker billingProjectWorker : billingProjectWorkers) {
                    sqlGenerator.generateSQLStatements(billingProjectWorker, out);
                }

                out.println("DATABASE corporate_oltp;");

                List<TcDirectProject> tcDirectProjects = generator.getTcDirectProjects();
                for (TcDirectProject tcDirectProject : tcDirectProjects) {
                    sqlGenerator.generateSQLStatements(tcDirectProject, out);
                }

                List<UserPermissionGrant> userPermissionGrants = generator.getUserPermissionGrants();
                for (UserPermissionGrant userPermissionGrant : userPermissionGrants) {
                    sqlGenerator.generateSQLStatements(userPermissionGrant, out);
                }

                out.println("DATABASE tcs_catalog;");

                List<TopCoderUser> directProjectRoleUsers = generator.getDirectProjectUsers();
                if (directProjectRoleUsers != null && directProjectRoleUsers.size() > 0) {
                    for (TopCoderUser tu : directProjectRoleUsers) {
                        sqlGenerator.generateSQLStatements(tu, out);
                    }
                }

                List<Group> groups = generator.getGroups();
                if (groups != null && groups.size() > 0) {
                    for (Group gp : groups) {
                        sqlGenerator.generateSQLStatements(gp, out);
                    }
                }

                out.println("INSERT INTO user_terms_of_use_xref " +
                        "    SELECT u.user_id, t.terms_of_use_id, CURRENT, CURRENT " +
                        "    FROM user u JOIN terms_of_use t ON 1=1 " +
                        "    LEFT JOIN (SELECT user_id, terms_of_use_id FROM user_terms_of_use_xref) x " +
                        "         ON x.user_id = u.user_id AND x.terms_of_use_id = t.terms_of_use_id" +
                        "    WHERE x.user_id IS NULL;");

                StringBuilder b = new StringBuilder();
                ProjectCategory[] projectCategories = ProjectCategory.values();
                for (ProjectCategory projectCategory : projectCategories) {
                    if (projectCategory.getRated()) {
                        if (b.length() > 0) {
                            b.append(", ");
                        }
                        b.append(projectCategory.getProjectCategoryId());
                    }
                }

                out.println("INSERT INTO user_rating " +
                        "  SELECT u.user_id, " +
                        "         ABS(MOD(TRUNC((((u.user_id * 1103515245) + 12345) - 4294967296 * TRUNC(((t.project_category_id * 1103515245) + 12345) / 4294967296)) / 65536), 3000)), " +
                        "         t.project_category_id + 111, 0, 0, 0, CURRENT, CURRENT, " +
                        "  NULL::DECIMAL(12,0) " +
                        "  FROM user u JOIN project_category_lu t ON 1=1 " +
                        "  LEFT JOIN (SELECT user_id, phase_id FROM user_rating) x " +
                        "       ON x.user_id = u.user_id AND x.phase_id = (t.project_category_id + 111)" +
                        "  WHERE x.user_id IS NULL AND t.project_category_id IN (" + b + ");");

                // Generate review board members per project category
                List<User> reviewerCandidates = new ArrayList<User>();
                reviewerCandidates.addAll(Arrays.asList(User.REVIEWER_CANDIDATES));
                reviewerCandidates.addAll(Arrays.asList(User.PRIMARY_REVIEWER_CANDIDATES));
                User[] reviewerCandidateUsers = reviewerCandidates.toArray(new User[0]);
                for (ProjectCategory projectCategory : projectCategories) {
                    if (projectCategory.getReviewRespIds() != null) {
                        int reviewBoardMembersCount = getRandomInt(1, reviewerCandidateUsers.length);
                        User[] reviewBoardMembers = getRandomUsers(reviewerCandidateUsers, reviewBoardMembersCount);
                        b.setLength(0);
                        for (User reviewBoardMember : reviewBoardMembers) {
                            if (b.length() > 0) {
                                b.append(", ");
                            }
                            b.append(reviewBoardMember.getUserId());
                        }
                        out.println("INSERT INTO rboard_user " +
                                "SELECT u.user_id, " + projectCategory.getProjectCategoryId() + ", c.catalog_id, 100, 1 " +
                                "FROM user u, catalog c " +
                                "WHERE u.user_id IN (" + b + ") " +
                                "AND NOT EXISTS (SELECT 1 FROM rboard_user r " +
                                "WHERE r.user_id = u.user_id " +
                                "AND r.catalog_id = c.catalog_id " +
                                "AND r.project_type_id = "
                                + projectCategory.getProjectCategoryId() + ");");
                    }
                }

                if(needDetailedContestData) {
                    List<TopCoderUser> contestRegistrants = generator.getContestRegistrants();
                    for (TopCoderUser registrant : contestRegistrants) {
                        sqlGenerator.generateSQLStatements(registrant, out);
                    }
                }


                List<CopilotProfile> copilotProfiles = generator.getCopilotProfiles();
                for (CopilotProfile copilotProfile : copilotProfiles) {
                    sqlGenerator.generateSQLStatements(copilotProfile, out);
                }

                List<Project> contests = generator.getContests();
                for (Project contest : contests) {
                    // needDetailedContestData is passed in
                    sqlGenerator.generateSQLStatements(contest, out, submissionDeclarationsOut, needDetailedContestData);
                }

                // Note on user's acceptance of terms of use
                termsOut.println("Following users accepted specified terms of use:");
                User[] users = User.values();
                for (User user : users) {
                    termsOut.println("User: " + user.getUserId() + " - " + user.getName());
                    TermsOfUse[] termsOfUses = TermsOfUse.values();
                    for (TermsOfUse termsOfUse : termsOfUses) {
                        termsOut.println("        " + termsOfUse.getTermsOfUseId() + " - " + termsOfUse.getName());
                    }
                    termsOut.println();
                }


                // iteration condition checking
                clientNumberCount = clientNumberCount - clientNumberToGenerate;

                if(clientNumberCount <= 0) break;
                
                idGeneratorStartCount += clientNumberToGenerate * maxDirectCount * contests.size() * 20;

                generator.clearData();
                generator = null;

                out.flush();
                submissionDeclarationsOut.flush();
                termsOut.flush();

            }

        } finally {
            out.close();
            submissionDeclarationsOut.close();
            termsOut.close();
        }
    }
    
    /**
     * <p>Gets the list of generated company accounts.</p>
     * 
     * @return a <code>List</code> listing the generated company accounts.
     */
    public List<Company> getCompanies() {
        return this.companies;
    }

    /**
     * <p>Gets the list of generated client accounts.</p>
     * 
     * @return a <code>List</code> listing the generated client accounts.
     */
    public List<Client> getClients() {
        return this.clients;
    }

    /**
     * <p>Get the list of generated payment terms.</p>
     * 
     * @return a <code>List</code> listing the generated payment terms.
     */
    public List<PaymentTerms> getPaymentTerms() {
        return this.paymentTerms;
    }

    /**
     * <p>Gets the list of generated billing projects.</p>
     * 
     * @return a <code>List</code> listing the generated billing projects.
     */
    public List<BillingProject> getBillingProjects() {
        return this.billingProjects;
    }

    /**
     * <p>Gets the list of generated TC Direct projects.</p>
     * 
     * @return a <code>List</code> listing the generated TC Direct projects.
     */
    public List<TcDirectProject> getTcDirectProjects() {
        return this.tcDirectProjects;
    }

    /**
     * <p>Gets the list of generated user permission grants.</p>
     * 
     * @return a <code>List</code> listing the generated user permission grants.
     */
    public List<UserPermissionGrant> getUserPermissionGrants() {
        return this.userPermissionGrants;
    }

    /**
     * <p>Gets the list of generated billing project managers.</p>
     * 
     * @return a <code>List</code> listing the generated billing project managers.
     */
    public List<BillingProjectManager> getBillingProjectManagers() {
        return this.billingProjectManagers;
    }

    /**
     * <p>Gets the list of generated copilot profiles.</p>
     * 
     * @return a <code>List</code> listing the generated copilot profiles.
     * @since 1.3
     */
    public List<CopilotProfile> getCopilotProfiles() {
        return this.copilotProfiles;
    }

    /**
     * <p>Gets the list of generated billing user accounts.</p>
     * 
     * @return a <code>List</code> listing the generated user accounts.
     */
    public List<UserAccount> getBillingUserAccounts() {
        return billingUserAccounts;
    }

    /**
     * <p>Gets the list of generated billing project workers.</p>
     * 
     * @return a <code>List</code> listing the generated billing project workers.
     */
    public List<BillingProjectWorker> getBillingProjectWorkers() {
        return this.billingProjectWorkers;
    }

    /**
     * <p>Gets the list of generated contests.</p>
     * 
     * @return a <code>List</code> listing the generated contests.
     */
    public List<Project> getContests() {
        return contests;
    }

    /**
     * <p>Gets the data for generated contest registrants.</p>
     *
     * @return a <code>List<TopCoderUser></code> providing the data for generated contest registrants.
     * @since 1.3
     */
    public List<TopCoderUser> getContestRegistrants() {
        return this.contestRegistrants;
    }

    /**
     * <p>Gets the groups</p>
     *
     * @return the groups.
     * @since 1.4
     */
    public List<Group> getGroups() {
        return groups;
    }

    /**
     * <p>Gets the direct project users</p>
     *
     * @return the direct project users.
     * @since 1.4
     */
    public List<TopCoderUser> getDirectProjectUsers() {
        return directProjectUsers;
    }

    /**
     * <p>Generates the sample data for numerous projects and client accounts of various types and states.</p>
     *
     *
     *
     * @param numberOfClients an <code>int</code> providing the number of clients to be generated.
     * @param minBillingProjectsCountPerClient an <code>int</code> providing the minimum number of billing projects per
     *        clients to be generated.
     * @param maxBillingProjectsCountPerClient an <code>int</code> providing the maximum number of billing projects per
     *        clients to be generated.
     * @param minTcDirectProjectsCountPerBillingAccount an <code>int</code> providing the minimum number of TC Direct
     *        projects per billing account to be generated.
     * @param maxTcDirectProjectsCountPerBillingAccount an <code>int</code> providing the maximum number of TC Direct
     *        projects per billing account to be generated.
     * @param minActiveContestCountPerTcDirectProject an <code>int</code> providing the minimum number of active 
     *                                                contests per TC Direct project to be generated.
     * @param maxActiveContestCountPerTcDirectProject an <code>int</code> providing the maximum number of active 
     *                                                contests per TC Direct project to be generated.
     * @param averageContestRegistrantsCount an <code>int</code> specifying the average number of registrants per 
     *        contests to be generated.
     * @param averageContestSubmissionsCount an <code>int</code> specifying the average number of submissions per 
     *        contests to be generated.
     * @param minProjectFulfillment an <code>int</code> providing the minimum project fulfillment.
     * @param maxProjectFulfillment an <code>int</code> providing the maximum project fulfillment.
     * @param averageProjectFulfillment an <code>int</code> providing the average project fulfillment.
     * @param averageContestSubmissionsFailureRate an <code>int</code> providing the average submission failure rate.
     * @param groupPerClient the number of groups per client
     * @throws IOException if an unexpected error occurs.
     */
    public void generateBulkTestData(int numberOfClients, int minBillingProjectsCountPerClient,
                                     int maxBillingProjectsCountPerClient,
                                     int minTcDirectProjectsCountPerBillingAccount,
                                     int maxTcDirectProjectsCountPerBillingAccount,
                                     int minActiveContestCountPerTcDirectProject,
                                     int maxActiveContestCountPerTcDirectProject,
                                     int minContestRegistrantsCount,
                                     int maxContestRegistrantsCount,
                                     int averageContestRegistrantsCount, int averageContestSubmissionsCount,
                                     int minProjectFulfillment, int maxProjectFulfillment,
                                     int averageProjectFulfillment, int averageContestSubmissionsFailureRate, int groupPerClient)
        throws IOException {

        generatePaymentTerms(numberOfClients);
        generateCompanies(numberOfClients);
        generateClients(numberOfClients);
        generateBillingProjects(minBillingProjectsCountPerClient, maxBillingProjectsCountPerClient);
        generateBillingUserAccounts();
        generateBillingProjectManagers();
        generateBillingProjectWorkers();
        generateTcDirectProjects(minTcDirectProjectsCountPerBillingAccount, maxTcDirectProjectsCountPerBillingAccount,
                                 minProjectFulfillment, maxProjectFulfillment, averageProjectFulfillment, 
                                 minActiveContestCountPerTcDirectProject, maxActiveContestCountPerTcDirectProject);
        generateUserPermissionGrants();
        generateCopilotPool();
        generateRegistrants(maxContestRegistrantsCount);
        generateContests(minContestRegistrantsCount, maxContestRegistrantsCount, averageContestRegistrantsCount,
                averageContestSubmissionsCount, averageContestSubmissionsFailureRate);
        generateCustomerGroups(groupPerClient);
        generateDirectProjectUserWithRoles();
    }

    /**
     * <p>Generates the sample data for various payment terms of various types and states.</p>
     *
     * @param numberOfClients an <code>int</code> providing the number of clients to be generated.
     */
    private void generatePaymentTerms(int numberOfClients) {
        int numberOfPaymentTerms = numberOfClients / 2;
        if (numberOfPaymentTerms == 0) {
            numberOfPaymentTerms++;
        }

        long paymentTermsIdsGenerator = this.idGeneratorStart;
        for (int i = 1; i <= numberOfPaymentTerms; i++) {
            PaymentTerms paymentTerm = new PaymentTerms();
            paymentTerm.setPaymentTermsId(++paymentTermsIdsGenerator);
            paymentTerm.setDescription("Payment Terms " + i);
            paymentTerm.setActive(true);
            paymentTerm.setTerm(i);
            this.paymentTerms.add(paymentTerm);
        }
    }

    /**
     * <p>Generates the sample data for various company accounts of various types and states.</p>
     *
     * @param numberOfClients an <code>int</code> providing the number of clients to be generated.
     */
    private void generateCompanies(int numberOfClients) {
        int numberOfCompanies = numberOfClients / 3;
        if (numberOfCompanies == 0) {
            numberOfCompanies++;
        }

        long companyIdsGenerator = this.idGeneratorStart;
        for (int i = 1; i <= numberOfCompanies; i++) {
            Company company = new Company();
            company.setCompanyId(++companyIdsGenerator);
            company.setName("Company " + i);
            company.setDeleted(false);
            company.setPassCode("PASSCODE " + i + "(" + company.getCompanyId() + ")");
            this.companies.add(company);
        }
    }

    /**
     * <p>Generates the sample data for various client accounts of various types and states.</p>
     *
     * @param numberOfClients an <code>int</code> providing the number of clients to be generated.
     */
    private void generateClients(int numberOfClients) {
        ClientStatus[] allClientStatuses = ClientStatus.values();
        long clientIdsGenerator = this.idGeneratorStart;
        for (int i = 1; i <= numberOfClients; i++) {
            Client client = new Client();
            client.setClientId(++clientIdsGenerator);
//            client.setName("Client " + client.getClientId());
            client.setName("Client " + client.getClientId());
            client.setCompany(this.companies.get(i % this.companies.size()));
            client.setPaymentTerms(this.paymentTerms.get(i % this.paymentTerms.size()));
            client.setDeleted(false);
            client.setStatus(allClientStatuses[i % allClientStatuses.length]);
            client.setCodeName("CodeName " + i);
            client.setSalesTax(getRandomInt(10, 40));
            client.setStartDate(new Date(System.currentTimeMillis() - getRandomInt(1, 10) * 24 * 3600 * 1000L));
            client.setEndDate(new Date(System.currentTimeMillis() + getRandomInt(1, 10) * 24 * 3600 * 1000L));
            
            this.clients.add(client);
        }
    }

    /**
     * <p>Generates the sample data for various billing projects of various types and states.</p>
     *  
     * @param minBillingProjectsCountPerClient an <code>int</code> providing the minimum number of billing projects per
     *        clients to be generated.
     * @param maxBillingProjectsCountPerClient an <code>int</code> providing the maximum number of billing projects per
     *        clients to be generated.
     */
    private void generateBillingProjects(int minBillingProjectsCountPerClient, int maxBillingProjectsCountPerClient) {
        long billingProjectIdsGenerator = this.idGeneratorStart;
        BillingProjectStatus[] allBillingProjectStatuses = BillingProjectStatus.values();

        for (Client client : this.clients) {
            int numberOfBillingProjects 
                = getRandomInt(minBillingProjectsCountPerClient, maxBillingProjectsCountPerClient);
            for (int i = 1; i <= numberOfBillingProjects; i++) {
                BillingProject billingProject = new BillingProject();
                billingProject.setActive(true);
                billingProject.setDeleted(false);
                billingProject.setBillingProjectId(++billingProjectIdsGenerator);
                billingProject.setClientId(client.getClientId());
                billingProject.setCompanyId(client.getCompany().getCompanyId());
                billingProject.setBudget(getRandomInt(10000, 100000));
                billingProject.setDescription(client.getName() + " Billing Account " + i);
                billingProject.setName(client.getName() + " Billing Account " + i);
                billingProject.setPaymentTermsId(client.getPaymentTerms().getPaymentTermsId());
                billingProject.setParentProjectId(null);
                billingProject.setStatus(allBillingProjectStatuses[i % allBillingProjectStatuses.length]);
                billingProject.setStartDate(
                    new Date(System.currentTimeMillis() - getRandomInt(1, 10) * 24 * 3600 * 1000L));
                billingProject.setEndDate(
                    new Date(System.currentTimeMillis() + getRandomInt(1000, 2000) * 24 * 3600 * 1000L));
                billingProject.setPoBoxNumber("PO BOX " + billingProject.getBillingProjectId());
                billingProject.setManualPrizeSetting(false);
                billingProject.setSalesTax(getRandomInt(10, 40));
                
                this.billingProjects.add(billingProject);
            }
        }
    }

    /**
     * <p>Generates the sample data for various billing user accounts.</p>
     */
    private void generateBillingUserAccounts() {
        final User[] users = new User[] {User.HEFFAN, User.USER, User.YOSHI};
        long userAccountIdsGenerator = this.idGeneratorStart;
        for (Company company : getCompanies()) {
            for (User user : users) {
                UserAccount billingUserAccount = new UserAccount();
                billingUserAccount.setUserAccountId(++userAccountIdsGenerator);
                billingUserAccount.setStatus(AccountStatus.ACTIVE);
                billingUserAccount.setUsername(user.getName());
                billingUserAccount.setPassword("password");
                billingUserAccount.setCompanyId(company.getCompanyId());

                this.billingUserAccounts.add(billingUserAccount);
            }    
        }
    }

    /**
     * <p>Generates the sample data for various billing project managers.</p>
     */
    private void generateBillingProjectManagers() {
        for (BillingProject billingProject : this.billingProjects) {
            BillingProjectManager billingProjectManager = new BillingProjectManager();
            billingProjectManager.setUserAccount(getUserAccount(User.HEFFAN.getName()));
            billingProjectManager.setActive(true);
            billingProjectManager.setProjectId(billingProject.getBillingProjectId());
            billingProjectManager.setPayRate(getRandomInt(10, 100));
            billingProjectManager.setCost(getRandomInt(10, 999));
            
            this.billingProjectManagers.add(billingProjectManager);
        }
    }

    /**
     * <p>Generate customer groups and related data.</p>
     *
     * @param groupPerClient the number of groups per client.
     * @since 1.4
     */
    private void generateCustomerGroups(int groupPerClient) {
        long groupIdGenerator = this.idGeneratorStart;

        for (Client client : this.clients) {
            for (int i = 1; i <= groupPerClient; ++i) {
                String groupName = client.getName() + " Group #" + (i);
                // active one
                Group activeGroup = new Group();
                activeGroup.setId(groupIdGenerator++);
                activeGroup.setName(groupName);
                activeGroup.setDefaultPermission(GroupPermissionType.getRandomPermissionType());
                activeGroup.setClient(client);
                activeGroup.setArchived(false);
                activeGroup.setArchivedOn(null);
                activeGroup.setEffectiveGroup(null);
                activeGroup.setAutoGrant(getRandomBoolean());
                randomFillGroupWithBillingAccountsAndDirectProjects(activeGroup, client);
                fillGroupMembers(activeGroup);


                // archived one
                Group archivedGroup = new Group();
                archivedGroup.setId(groupIdGenerator++);
                archivedGroup.setName(groupName);
                archivedGroup.setDefaultPermission(GroupPermissionType.getRandomPermissionType());
                archivedGroup.setClient(client);
                archivedGroup.setArchived(true);
                archivedGroup.setArchivedOn(
                        new Date(System.currentTimeMillis() - (getRandomInt(10, 100) * 24L * 3600L * 1000L)));
                archivedGroup.setEffectiveGroup(activeGroup);
                randomFillGroupWithBillingAccountsAndDirectProjects(archivedGroup, client);
                fillGroupMembers(archivedGroup);

                this.groups.add(activeGroup);
                this.groups.add(archivedGroup);
            }
        }
    }

    /**
     * <p>Generate the billing accounts to associated with the group</p>
     *
     * @param group the group
     * @param client the client of the billing accounts
     * @since 1.4
     */
    private void randomFillGroupWithBillingAccountsAndDirectProjects(Group group, Client client) {
        int numberOfBillingProjectsToAdd = getRandomInt(0, 4);
        int numberOfDirectProjectsToAdd = getRandomInt(0, 40);
        group.setBillingAccounts(new ArrayList<BillingProject>());
        group.setDirectProjects(new ArrayList<TcDirectProject>());

        for (BillingProject billingProject : this.billingProjects) {
            if (billingProject.getClientId() == client.getClientId()) {
                // client billing project
                if(getRandomBoolean() && numberOfBillingProjectsToAdd > 0) {
                    group.getBillingAccounts().add(billingProject);
                    numberOfBillingProjectsToAdd--;
                }
            }
        }

        for (TcDirectProject directProject : this.tcDirectProjects) {
            if (directProject.getBillingAccount() != null &&
                    directProject.getBillingAccount().getClientId() == client.getClientId()) {
                if (getRandomBoolean() && numberOfDirectProjectsToAdd > 0) {
                    group.getDirectProjects().add(directProject);
                    numberOfDirectProjectsToAdd--;
                }
            }
        }
    }

    /**
     * <p>Generate the group members for the group</p>
     *
     * @param group the group to generate group members.
     * @since 1.4
     */
    private void fillGroupMembers(Group group) {
        User[] allUsers = User.ALL_USERS;
        group.setGroupMembers(new ArrayList<GroupMember>());
        for (User u : allUsers) {

            if (getRandomBoolean()) {
                GroupMember member = new GroupMember();
                member.setUserId(u.getUserId());
                member.setHandle(u.getName());
                member.setActive(true);
                member.setGroup(group);
                member.setActivatedOn(new Date(System.currentTimeMillis() - getRandomInt(10, 10000) * 3600L * 1000L));
                member.setUseGroupDefault(true);

                group.getGroupMembers().add(member);
            }
        }
    }

    /**
     * <p>Generates the sample data for various copilot profiles.</p>
     * 
     * @since 1.3
     */
    private void generateCopilotPool() {
        long copilotProfileIdsGenerator = this.idGeneratorStart;
        long copilotProjectIdsGenerator = this.idGeneratorStart;
        User[] copilotCandidates = User.COPILOT_CANDIDATES;
        for (User copilot : copilotCandidates) {
            CopilotProfile copilotProfile = new CopilotProfile();
            copilotProfile.setActivationTime(new Date());
            copilotProfile.setCopilotProfileId(++copilotProfileIdsGenerator);
            copilotProfile.setReliability(copilot.getUserId() % 100);
            copilotProfile.setShowEarnings(true);
            copilotProfile.setStatus(CopilotProfileStatus.ACTIVE);
            copilotProfile.setSuspensionCount(0);
            copilotProfile.setUserId(copilot.getUserId());
            
            List<CopilotProject> copilotProjects = new ArrayList<CopilotProject>();
            for (TcDirectProject tcDirectProject : this.tcDirectProjects) {
                User[] copilots = tcDirectProject.getCopilots();
                if (copilots != null) {
                    for (User tcDirectProjectCopilot : copilots) {
                        if (tcDirectProjectCopilot == copilot) {
                            CopilotProject copilotProject = new CopilotProject();
                            copilotProject.setCopilotProfileId(copilotProfile.getCopilotProfileId());
                            copilotProject.setCopilotProjectId(++copilotProjectIdsGenerator);
                            copilotProject.setTcDirectProjectId(tcDirectProject.getTcDirectProjectId());
                            copilotProjects.add(copilotProject);
                        }
                    }
                }
            }
            copilotProfile.setProjects(copilotProjects);
            
            this.copilotProfiles.add(copilotProfile);
        }
    }

    /**
     * <p>Generates the sample data for various user accounts who will be registered to generated contests as 
     * submitters.</p>
     *
     * @since 1.3
     */
    private void generateRegistrants(int maxContestRegistrantsCount) {
        IdGenerator userIdGenerator = new SimpleIdGenerator(this.idGeneratorStart);
        int contestRegistrantsCount = maxContestRegistrantsCount * 6;
        for (int i = 0; i < contestRegistrantsCount; i++) {
            long registrantId = userIdGenerator.getNextId();
            String registrantHandle =
                USER_HANDLE_SAMPLES[getRandomInt(0, USER_HANDLE_SAMPLES.length - 1)];
            registrantHandle += registrantId;
            String registrantFirstName =
                USER_FIRST_NAMES[getRandomInt(0, USER_FIRST_NAMES.length - 1)];
            String registrantLastName =
                USER_LAST_NAMES[getRandomInt(0, USER_LAST_NAMES.length - 1)];

            TopCoderUser registrant = new TopCoderUser();
            registrant.setFirstName(registrantFirstName);
            registrant.setLastName(registrantLastName);
            registrant.setHandle(registrantHandle);
            registrant.setStatus('A');
            registrant.setUserId(registrantId);
            this.contestRegistrants.add(registrant);
        }
    }


    /**
     * <p>Generator one user for each direct project, this user will have 2 random security roles. This data
     * will be used inserting test data
     * - insert one user
     * - insert the two records in user_xref_role
     * </p>
     *
     * @since 1.4
     *
     */
    private void generateDirectProjectUserWithRoles() {
        IdGenerator userIdGenerator = new SimpleIdGenerator(this.idGeneratorStart);
        int directProjectUserCounts = this.tcDirectProjects.size();
        for (int i = 0; i < directProjectUserCounts; i++) {
            long userId = userIdGenerator.getNextId();
            String handle =
                    USER_HANDLE_SAMPLES[getRandomInt(0, USER_HANDLE_SAMPLES.length - 1)];
            handle += userId;
            String firstName =
                    USER_FIRST_NAMES[getRandomInt(0, USER_FIRST_NAMES.length - 1)];
            String lastName =
                    USER_LAST_NAMES[getRandomInt(0, USER_LAST_NAMES.length - 1)];

            TopCoderUser user = new TopCoderUser();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setHandle(handle);
            user.setStatus('A');
            user.setUserId(userId);

            user.setRoles(new HashSet<SecurityRole>());

            user.getRoles().add(SecurityRole.getRandomSecurityRole());
            user.getRoles().add(SecurityRole.getRandomSecurityRole());

            this.directProjectUsers.add(user);
        }
    }

    /**
     * <p>Generates the sample data for various billing project workers.</p>
     */
    private void generateBillingProjectWorkers() {
        final User[] users = new User[] {User.USER, User.YOSHI};
        for (BillingProject billingProject : this.billingProjects) {
            for (User user : users) {
                BillingProjectWorker billingProjectWorker = new BillingProjectWorker();
                billingProjectWorker.setUserAccount(getUserAccount(user.getName()));
                billingProjectWorker.setActive(true);
                billingProjectWorker.setProjectId(billingProject.getBillingProjectId());
                billingProjectWorker.setPayRate(getRandomInt(10, 100));
                billingProjectWorker.setCost(getRandomInt(10, 999));
                billingProjectWorker.setStartDate(billingProject.getStartDate());
                billingProjectWorker.setEndDate(billingProject.getEndDate());

                this.billingProjectWorkers.add(billingProjectWorker);
            }    
        }
    }

    /**
     * <p>Gets the generated user account matching the specified username.</p>
     * 
     * @param username a <code>String</code> providing the username to find matching user account for.
     * @return an <code>UserAccount</code> matching the specified username or <code>null</code> if there is no one. 
     */
    private UserAccount getUserAccount(String username) {
        for (UserAccount userAccount : this.billingUserAccounts) {
            if (userAccount.getUsername().equals(username)) {
                return userAccount;
            }
        }
        return null;
    }


    /**
     * <p>Generates the sample data for various billing projects of various types and states.</p>
     *  
     * @param minTcDirectProjectsCountPerBillingAccount an <code>int</code> providing the minimum number of TC Direct
     *        projects per billing account to be generated.
     * @param maxTcDirectProjectsCountPerBillingAccount an <code>int</code> providing the maximum number of TC Direct
     *        projects per billing account to be generated.
     * @param minProjectFulfillment
     * @param maxProjectFulfillment
     * @param averageProjectFulfillment
     * @param minActiveContestCountPerTcDirectProject an <code>int</code> providing the 
     * @param maxActiveContestCountPerTcDirectProject
     */
    private void generateTcDirectProjects(int minTcDirectProjectsCountPerBillingAccount, 
                                          int maxTcDirectProjectsCountPerBillingAccount, 
                                          int minProjectFulfillment,
                                          int maxProjectFulfillment,
                                          int averageProjectFulfillment, 
                                          int minActiveContestCountPerTcDirectProject,
                                          int maxActiveContestCountPerTcDirectProject) {
        final User[] projectCreators = new User[] {User.HEFFAN, User.USER, User.YOSHI};

        long projectIdsGenerator = this.idGeneratorStart;

        TcDirectProjectStatus[] allProjectStatuses = TcDirectProjectStatus.values();

        for (BillingProject billingAccount : this.billingProjects) {
            int numberOfTcDirectProjects 
                = getRandomInt(minTcDirectProjectsCountPerBillingAccount, maxTcDirectProjectsCountPerBillingAccount);
            for (int i = 1; i <= numberOfTcDirectProjects; i++) {
                int projectFulfillment = getRandomInt(minProjectFulfillment, maxProjectFulfillment);
                int activeContestsCount = 0;
                int completedContestsCount;
                int projectTotalContestsCount;
                
                if (projectFulfillment != 100) {
                    activeContestsCount =
                        getRandomInt(minActiveContestCountPerTcDirectProject, maxActiveContestCountPerTcDirectProject);
                    completedContestsCount = ((activeContestsCount * projectFulfillment) / (100 - projectFulfillment)) + 1;
                    projectTotalContestsCount = activeContestsCount + completedContestsCount;
                } else {
                    completedContestsCount = getRandomInt(2, 5);
                    projectTotalContestsCount = completedContestsCount;
                }

                int copilotsCount = 2;
                User[] copilots = getRandomUsers(User.COPILOT_CANDIDATES, copilotsCount);
                TcDirectProject tcDirectProject = new TcDirectProject();
                tcDirectProject.setTcDirectProjectId(++projectIdsGenerator);
                tcDirectProject.setDescription(billingAccount.getName() + " Project " + i);
                tcDirectProject.setName(billingAccount.getName() + " Project " + i);
                tcDirectProject.setStatus(allProjectStatuses[i % allProjectStatuses.length]);
                tcDirectProject.setOwner(projectCreators[i % projectCreators.length]);
                tcDirectProject.setBillingAccount(billingAccount);
                tcDirectProject.setActiveContestsCount(activeContestsCount);
                tcDirectProject.setCompletedContestsCount(completedContestsCount);
                tcDirectProject.setTotalContestsCount(projectTotalContestsCount);
                tcDirectProject.setCopilots(copilots);

                this.tcDirectProjects.add(tcDirectProject);
            }
        }
    }

    /**
     * <p>Generates the sample data for various permission grants for users for accessing the generated TC Direct
     * projects.</p>
     */
    private void generateUserPermissionGrants() {
        final User[] users = new User[] {User.HEFFAN, User.USER, User.YOSHI, User.ANNEJ9NY,
                User.KSMITH, User.HUNG, User.PLINEHAN, User.SANDKING, User.SUPER, User.WYZMO, User.CARTAJS};
        long grantIdsGenerator = this.idGeneratorStart;
        for (TcDirectProject tcDirectProject : this.tcDirectProjects) {
            for (User user : users) {
                UserPermissionGrant userPermissionGrant = new UserPermissionGrant();
                userPermissionGrant.setUserPermissionGrantId(++grantIdsGenerator);
                if (user.getUserId() == tcDirectProject.getOwner().getUserId()) {
                    userPermissionGrant.setPermissionType(PermissionType.PROJECT_FULL);
                } else {
                    userPermissionGrant.setPermissionType(PermissionType.getRandomProjectPermission());
                }
                userPermissionGrant.setUserId(user.getUserId());
                userPermissionGrant.setResourceId(tcDirectProject.getTcDirectProjectId());
                this.userPermissionGrants.add(userPermissionGrant);
            }
        }
    }
    
    /**
     * <p>Generates the sample data for various billing projects of various types and states.</p>
     *  
     *
     * @param minContestRegistrantsCount an <code>int</code> providing the minimum number of registrants per contest
     *        to be generated.
     * @param maxContestRegistrantsCount an <code>int</code> providing the maximum number of registrants per contest
     *        to be generated.
     * @param averageContestRegistrantsCount an <code>int</code> providing the average number of registrants per contest
     *        to be generated.
     * @param averageContestSubmissionsCount an <code>int</code> specifying the average number of submissions per 
     *        contests to be generated.
     * @param averageContestSubmissionsFailureRate an <code>int</code> providing the average submisison failure rate.
     * @throws IOException if an unexpected error occurs.
     */
    private void generateContests(int minContestRegistrantsCount, int maxContestRegistrantsCount, 
                                  int averageContestRegistrantsCount, int averageContestSubmissionsCount, 
                                  int averageContestSubmissionsFailureRate)
        throws IOException {
        Map<ProjectStatus, Map<PhaseType, Map<ProjectCategory, Long>>> stats 
            = new TreeMap<ProjectStatus, Map<PhaseType, Map<ProjectCategory, Long>>>();
        Map<ProjectCategory, Long> stats2 = new TreeMap<ProjectCategory, Long>();
        Map<PhaseType, Long> stats3 = new TreeMap<PhaseType, Long>();
        Map<Long, Map<ProjectCategory, Long>> stats4 
            = new TreeMap<Long, Map<ProjectCategory, Long>>();
        Map<ProjectCategory, Long> stats5 = new TreeMap<ProjectCategory, Long>();
        Map<ProjectCategory, Long> stats6 = new TreeMap<ProjectCategory, Long>();
        Map<Long, Map<PhaseType, Long>> stats7
            = new TreeMap<Long, Map<PhaseType, Long>>();

        Catalog[] catalogs = Catalog.values();
        CostLevel[] costLevels = CostLevel.values();

        // Initialize ID generators
        IdGenerator projectIdGenerator = new SimpleIdGenerator(idGeneratorStart);
        IdGenerator prizeIdGenerator = new SimpleIdGenerator(idGeneratorStart);
        IdGenerator projectSpecIdGenerator = new SimpleIdGenerator(idGeneratorStart);
        IdGenerator componentIdGenerator = new SimpleIdGenerator(idGeneratorStart);
        IdGenerator componentVersionIdGenerator = new SimpleIdGenerator(idGeneratorStart);
        IdGenerator phaseIdGenerator = new SimpleIdGenerator(idGeneratorStart);
        IdGenerator resourceIdGenerator = new SimpleIdGenerator(idGeneratorStart);
        IdGenerator compTechnologyIdGenerator = new SimpleIdGenerator(idGeneratorStart);
        IdGenerator compInquiryIdGenerator = new SimpleIdGenerator(idGeneratorStart);
        IdGenerator contestSaleIdGenerator = new SimpleIdGenerator(idGeneratorStart);
        IdGenerator uploadIdGenerator = new SimpleIdGenerator(idGeneratorStart);
        IdGenerator submissionIdGenerator = new SimpleIdGenerator(idGeneratorStart);
        IdGenerator studioSubmissionIdGenerator = new StudioSubmissionIdGenerator(studioSubmissionIdsFileName);
//        IdGenerator studioSubmissionIdGenerator = submissionIdGenerator; // TODO : Try this to test the issue with 0 fulfillments
        IdGenerator reviewIdGenerator = new SimpleIdGenerator(idGeneratorStart);
        IdGenerator reviewItemIdGenerator = new SimpleIdGenerator(idGeneratorStart);
        IdGenerator reviewCommentIdGenerator = new SimpleIdGenerator(idGeneratorStart);
        IdGenerator reviewItemCommentIdGenerator = new SimpleIdGenerator(idGeneratorStart);
        IdGenerator screeningTaskIdGenerator = new SimpleIdGenerator(idGeneratorStart);
        IdGenerator studioSpecificationIdGenerator = new SimpleIdGenerator(idGeneratorStart);
        IdGenerator submissionDeclarationIdGenerator = new SimpleIdGenerator(idGeneratorStart);
        IdGenerator projectCountGenerator = new SimpleIdGenerator(0);
        IdGenerator paymentIdGenerator = new SimpleIdGenerator(idGeneratorStart);
        
        int contestsWithRegistrantsCount = 0;
        int contestsWithSubmissionsCount = 0;
        int totalContestSubmissionsCount = 0;
        int totalContestRegistrantsCount = 0;
        int totalCompletedContestsCount = 0;
        
        for (TcDirectProject tcDirectProject : this.tcDirectProjects) {
            // Select billing account, catalog, category and status for project to be generated
            BillingProject billingAccount = tcDirectProject.getBillingAccount();
            stats4.put(tcDirectProject.getTcDirectProjectId(), new TreeMap<ProjectCategory, Long>());
            stats7.put(tcDirectProject.getTcDirectProjectId(), new TreeMap<PhaseType, Long>());

            int numberOfContests = tcDirectProject.getTotalContestsCount();
            int completedContestsCount = tcDirectProject.getCompletedContestsCount();
            int activeContestsCount = tcDirectProject.getActiveContestsCount();
            int generatedContestsCount = 0;

            while ((generatedContestsCount < numberOfContests)) {
                ProjectStatus projectStatus;
                if (completedContestsCount > 0) {
                    projectStatus = ProjectStatus.COMPLETED;
                    completedContestsCount--;
                    totalCompletedContestsCount++;
                } else if (activeContestsCount > 0) {
                    projectStatus = ProjectStatus.ACTIVE;
                    activeContestsCount--;
                } else {
                    do {
                        projectStatus = ProjectStatus.getRandomValue();
                    } while ((projectStatus == ProjectStatus.COMPLETED) || (projectStatus == ProjectStatus.ACTIVE));
                }

                // Switch through all phase types and create a project with phase of selected type to be of Open status
                final PhaseType phaseType;
                if (projectStatus == ProjectStatus.ACTIVE) {
                    phaseType = PhaseType.getRandomValue();
                } else {
                    phaseType = null;
                }

                ProjectCategory projectCategory = ProjectCategory.getRandomValue();

                boolean isStudio = projectCategory.getProjectType() == ProjectType.STUDIO;

                ProjectPhaseTemplate[] phasesTemplates;
                if (projectCategory.getMilestonePhasesTemplate() != null) {
                    phasesTemplates = new ProjectPhaseTemplate[]{projectCategory.getMilestonePhasesTemplate(),
                                                                 projectCategory.getPhasesTemplate()};
                } else {
                    phasesTemplates = new ProjectPhaseTemplate[]{projectCategory.getPhasesTemplate()};
                }

                ProjectPhaseTemplate phasesTemplate = phasesTemplates[generatedContestsCount % phasesTemplates.length];
                if (phasesTemplate.contains(phaseType) || phaseType == null) {
                    Catalog catalog;
                    if (!isStudio) {
                        catalog = catalogs[getRandomInt(0, catalogs.length - 1)];
                    } else {
                        catalog = Catalog.NOT_SET;
                    }
                    // Cost level
                    CostLevel costLevel = null;
                    if (!isStudio) {
                        costLevel = costLevels[getRandomInt(0, costLevels.length - 1)];
                    }

                    if (areCompatible(phaseType, projectStatus)) {
                        if (!stats.containsKey(projectStatus)) {
                            stats.put(projectStatus, new TreeMap<PhaseType, Map<ProjectCategory, Long>>());
                        }
                        if (phaseType != null) {
                            if (!stats.get(projectStatus).containsKey(phaseType)) {
                                stats.get(projectStatus).put(phaseType, new TreeMap<ProjectCategory, Long>());
                            }
                            if (!stats.get(projectStatus).get(phaseType).containsKey(projectCategory)) {
                                stats.get(projectStatus).get(phaseType).put(projectCategory, 0L);
                            }
                        }
                        if (projectStatus == ProjectStatus.ACTIVE) {
                            if (!stats2.containsKey(projectCategory)) {
                                stats2.put(projectCategory, 0L);
                            }
                            if (!stats5.containsKey(projectCategory)) {
                                stats5.put(projectCategory, 0L);
                            }
                            if (!stats6.containsKey(projectCategory)) {
                                stats6.put(projectCategory, 0L);
                            }
                            if (phaseType != null) {
                                if (!stats3.containsKey(phaseType)) {
                                    stats3.put(phaseType, 0L);
                                }
                            }
                            if (!stats4.get(tcDirectProject.getTcDirectProjectId()).containsKey(projectCategory)) {
                                stats4.get(tcDirectProject.getTcDirectProjectId()).put(projectCategory, 0L);
                            }
                            if (phaseType != null) {
                                if (!stats7.get(tcDirectProject.getTcDirectProjectId()).containsKey(phaseType)) {
                                    stats7.get(tcDirectProject.getTcDirectProjectId()).put(phaseType, 0L);
                                }
                            }
                        }

                        // Set configuration for project generation
                        long projectId = projectIdGenerator.getNextId();
                        ProjectGeneratorConfig projectGeneratorConfig = new ProjectGeneratorConfig();
                        projectGeneratorConfig.setCompInquiryIdGenerator(compInquiryIdGenerator);
                        projectGeneratorConfig.setPhasesTemplate(phasesTemplate);
                        projectGeneratorConfig.setComponentIdGenerator(componentIdGenerator);
                        projectGeneratorConfig.setComponentVersionIdGenerator(componentVersionIdGenerator);
                        projectGeneratorConfig.setCompTechnologyIdGenerator(compTechnologyIdGenerator);
                        projectGeneratorConfig.setPrizeIdGenerator(prizeIdGenerator);
                        projectGeneratorConfig.setProjectId(projectId);
                        projectGeneratorConfig.setProjectSpecIdGenerator(projectSpecIdGenerator);
                        projectGeneratorConfig.setProjectCountGenerator(projectCountGenerator);
                        projectGeneratorConfig.setPhaseIdGenerator(phaseIdGenerator);
                        projectGeneratorConfig.setResourceIdGenerator(resourceIdGenerator);
                        projectGeneratorConfig.setContestSaleIdGenerator(contestSaleIdGenerator);
                        projectGeneratorConfig.setSubmissionsFailureRate(averageContestSubmissionsFailureRate);
                        if (!isStudio) {
                            projectGeneratorConfig.setSubmissionIdGenerator(submissionIdGenerator);
                        } else {
                            projectGeneratorConfig.setSubmissionIdGenerator(studioSubmissionIdGenerator);
                        }
                        projectGeneratorConfig.setPaymentIdGenerator(paymentIdGenerator);
                        projectGeneratorConfig.setUploadIdGenerator(uploadIdGenerator);
                        projectGeneratorConfig.setReviewIdGenerator(reviewIdGenerator);
                        projectGeneratorConfig.setReviewItemIdGenerator(reviewItemIdGenerator);
                        projectGeneratorConfig.setReviewCommentIdGenerator(reviewCommentIdGenerator);
                        projectGeneratorConfig
                            .setReviewItemCommentIdGenerator(reviewItemCommentIdGenerator);
                        projectGeneratorConfig.setScreeningTaskIdGenerator(screeningTaskIdGenerator);
                        projectGeneratorConfig
                            .setSubmissionDeclarationIdGenerator(submissionDeclarationIdGenerator);
                        projectGeneratorConfig
                            .setStudioSpecificationIdGenerator(studioSpecificationIdGenerator);

                        projectGeneratorConfig.setCurrentPhaseType(phaseType);
                        projectGeneratorConfig.setBillingProject(billingAccount);
                        projectGeneratorConfig.setTcDirectProject(tcDirectProject);
                        projectGeneratorConfig.setCatalog(catalog);
                        projectGeneratorConfig.setProjectCategory(projectCategory);
                        projectGeneratorConfig.setProjectStatus(projectStatus);
                        projectGeneratorConfig.setCostLevel(costLevel);

                        boolean viewableSubmissions = false;
                        if (isStudio) {
                            viewableSubmissions = (getRandomInt(0, 1) == 1);
                        }

                        List<ProjectInfoType> flagsToBeSet = new ArrayList<ProjectInfoType>();
                        if (!isStudio) {
                            flagsToBeSet.add(ProjectInfoType.RATED);
                            flagsToBeSet.add(ProjectInfoType.AUTOPILOT_OPTION);
                        }
                        flagsToBeSet.add(ProjectInfoType.PUBLIC);
                        flagsToBeSet.add(ProjectInfoType.TIMELINE_NOTIFICATION);
                        flagsToBeSet.add(ProjectInfoType.STATUS_NOTIFICATION);
                        flagsToBeSet.add(ProjectInfoType.DIGITAL_RUN_FLAG);
                        flagsToBeSet.add(ProjectInfoType.RELIABILITY_BONUS_ELIGIBLE);
                        if (!isStudio) {
                            flagsToBeSet.add(ProjectInfoType.APPROVAL_REQUIRED);
                            flagsToBeSet.add(ProjectInfoType.PHASE_DEPENDENCIES_EDITABLE);
                            flagsToBeSet.add(ProjectInfoType.TRACK_LATE_DELIVERABLES);
                        }
                        flagsToBeSet.add(ProjectInfoType.SEND_WINNER_EMAILS);
                        flagsToBeSet.add(ProjectInfoType.MEMBER_PAYMENTS_ELIGIBLE);
                        if (isStudio) {
                            if (viewableSubmissions) {
                                flagsToBeSet.add(ProjectInfoType.VIEWABLE_SUBMISSIONS_FLAG);
                            }
                            flagsToBeSet.add(ProjectInfoType.ALLOW_STOCK_ART);
                        }
                        projectGeneratorConfig.setFlagsToBeSet(flagsToBeSet.toArray(
                            new ProjectInfoType[flagsToBeSet.size()]));

                        List<ProjectInfoType> flagsToBeUnset = new ArrayList<ProjectInfoType>();
                        flagsToBeUnset.add(ProjectInfoType.ELIGIBILITY);
                        flagsToBeUnset.add(ProjectInfoType.POST_MORTEM_REQUIRED);
                        projectGeneratorConfig.setFlagsToBeUnSet(flagsToBeUnset.toArray(
                            new ProjectInfoType[flagsToBeUnset.size()]));
                        if (isStudio) {
                            flagsToBeUnset.add(ProjectInfoType.RATED);
                            flagsToBeUnset.add(ProjectInfoType.AUTOPILOT_OPTION);
                            flagsToBeUnset.add(ProjectInfoType.APPROVAL_REQUIRED);
                            if (!viewableSubmissions) {
                                flagsToBeUnset.add(ProjectInfoType.VIEWABLE_SUBMISSIONS_FLAG);
                            }
                        }

                        if (projectCategory.getProjectType() == ProjectType.APPLICATION) {
                            projectGeneratorConfig.setManagers(new User[]{User.HEFFAN, User.APPLICATIONS});
                        } else if (projectCategory.getProjectType() == ProjectType.COMPONENT) {
                            projectGeneratorConfig.setManagers(new User[]{User.HEFFAN, User.COMPONENTS});
                        } else if (isStudio) {
                            projectGeneratorConfig.setManagers(new User[]{User.HEFFAN, User.LCSUPPORT});
                        } else {
                            projectGeneratorConfig.setManagers(new User[]{User.HEFFAN});
                        }
                        if (phasesTemplate.contains(PhaseType.SPECIFICATION_SUBMISSION)) {
                            projectGeneratorConfig
                                .setSpecificationSubmitters(new User[]{tcDirectProject.getOwner()});
                        }
                        if (phasesTemplate.contains(PhaseType.SPECIFICATION_REVIEW)) {
                            projectGeneratorConfig.setSpecificationReviewer(
                                getRandomUser(User.SPECIFICATION_REVIEWER_CANDIDATES));
                        }
                        projectGeneratorConfig
                            .setPrimaryReviewer(getRandomUser(User.PRIMARY_REVIEWER_CANDIDATES));
                        projectGeneratorConfig
                            .setMilestoneReviewer(getRandomUser(User.PRIMARY_REVIEWER_CANDIDATES));
                        projectGeneratorConfig.setReviewers(getRandomUsers(User.REVIEWER_CANDIDATES, 2));
                        
                        // Determine if open project phase requires submissions
                        boolean requiresSubmission = false;
                        PhaseType currentPhaseType = projectGeneratorConfig.getCurrentPhaseType();
                        if (currentPhaseType != null) {
                            if ((currentPhaseType == PhaseType.SUBMISSION || currentPhaseType.getOrdinal() >
                                                                             PhaseType.SUBMISSION.getOrdinal())
                                && (projectStatus != ProjectStatus.CANCELLED_ZERO_SUBMISSIONS)) {
                                requiresSubmission = true;
                            } else {
                                if ((currentPhaseType == PhaseType.MILESTONE_SUBMISSION
                                     || currentPhaseType.getOrdinal() >
                                        PhaseType.MILESTONE_SUBMISSION.getOrdinal())
                                    && (projectStatus != ProjectStatus.CANCELLED_ZERO_SUBMISSIONS)) {
                                    requiresSubmission = true;
                                }
                            }
                        } else {
                            if (projectStatus == ProjectStatus.CANCELLED_FAILED_REVIEW 
                                || projectStatus == ProjectStatus.CANCELLED_FAILED_SCREENING 
                                || projectStatus == ProjectStatus.CANCELLED_WINNER_UNRESPONSIVE 
                                || projectStatus == ProjectStatus.COMPLETED) {
                                requiresSubmission = true;
                            }
                        }

                        // Registrants for contest
                        if (projectStatus != ProjectStatus.CANCELLED_ZERO_REGISTRATIONS
                            && phaseType != PhaseType.SPECIFICATION_SUBMISSION
                            && phaseType != PhaseType.SPECIFICATION_REVIEW) {

                            int contestRegistrantsCount 
                                = calculateNumberOfRecordsToGenerate(minContestRegistrantsCount,
                                                                     maxContestRegistrantsCount,
                                                                     averageContestRegistrantsCount,
                                                                     totalContestRegistrantsCount,
                                                                     contestsWithRegistrantsCount + 1, "Registrants");
                            totalContestRegistrantsCount += contestRegistrantsCount;

                            int contestSubmissionsCount = 0;
                            if (requiresSubmission) {
                                contestSubmissionsCount 
                                    = calculateNumberOfRecordsToGenerate(1,
                                                                         contestRegistrantsCount, 
                                                                         averageContestSubmissionsCount,
                                                                         totalContestSubmissionsCount,
                                                                         contestsWithSubmissionsCount + 1, 
                                                                         "Submissions");
                            }
                            totalContestSubmissionsCount += contestSubmissionsCount;

                            if (requiresSubmission && (contestSubmissionsCount > 0)) {
                                contestsWithSubmissionsCount++;
                            }

                            TopCoderUser[] submitters;
                            if (projectCategory != ProjectCategory.COPILOT_POSTING) {
                                submitters = getRandomRegistrants(contestRegistrantsCount);
                                for (TopCoderUser registrant : submitters) {
                                    if (contestSubmissionsCount > 0) {
                                        registrant.setRequiresSubmission(projectId);
                                    }
                                    registrant.setRequiresMilestoneSubmission(true);
                                    contestSubmissionsCount--;
                                }
                            } else {
                                submitters = new TopCoderUser[this.copilotProfiles.size()];
                                int i = 0;
                                for (CopilotProfile copilot : this.copilotProfiles) {
                                    for (User copilotUser : User.COPILOT_CANDIDATES) {
                                        if (copilotUser.getUserId() == copilot.getUserId()) {
                                            TopCoderUser registrant = new TopCoderUser();
                                            registrant.setFirstName("CopilotFirstName");
                                            registrant.setLastName("CopilotLastName");
                                            registrant.setHandle(copilotUser.getName());
                                            registrant.setStatus('A');
                                            registrant.setUserId(copilot.getUserId());
                                            submitters[i++] = registrant;
                                            if (contestSubmissionsCount > 0) {
                                                registrant.setRequiresSubmission(projectId);
                                            }
                                            registrant.setRequiresMilestoneSubmission(true);
                                            contestSubmissionsCount--;
                                        }
                                    }
                                }
                            }
                            
                            projectGeneratorConfig.setSubmitters(submitters);
                            contestsWithRegistrantsCount++;
                        }


                        if (phasesTemplate.contains(PhaseType.APPROVAL)) {
                            projectGeneratorConfig.setApprover(tcDirectProject.getOwner());
                        }
                        projectGeneratorConfig.setCopilot(getRandomUser(tcDirectProject.getCopilots()));
                        
                        // Generate and collect generated project
                        if (!requiresSubmission || requiresSubmission && submissionIdGenerator.isAvailable()) {
                            ProjectDataGenerator projectDataGenerator =
                                new ProjectDataGenerator(projectGeneratorConfig);
                            this.contests.add(projectDataGenerator.generateProject());
                            generatedContestsCount++;
                            Long count;
                            if (phaseType != null) {
                                count = stats.get(projectStatus).get(phaseType).get(projectCategory);
                                stats.get(projectStatus).get(phaseType).put(projectCategory, count + 1);
                            }

                            if (projectStatus == ProjectStatus.ACTIVE) {
                                count = stats2.get(projectCategory);
                                stats2.put(projectCategory, count + 1);
                                if (phaseType != null) {
                                    count = stats3.get(phaseType);
                                    stats3.put(phaseType, count + 1);
                                }
                                count = stats4.get(tcDirectProject.getTcDirectProjectId()).get(
                                    projectCategory);
                                stats4.get(tcDirectProject.getTcDirectProjectId()).put(projectCategory,
                                                                                       count + 1);
                                if (phaseType != null) {
                                    count = stats7.get(tcDirectProject.getTcDirectProjectId()).get(phaseType);
                                    stats7.get(tcDirectProject.getTcDirectProjectId()).put(phaseType, count + 1);
                                }
                                if (phasesTemplate.contains(PhaseType.MILESTONE_SUBMISSION)) {
                                    count = stats5.get(projectCategory);
                                    stats5.put(projectCategory, count + 1);
                                } else {
                                    count = stats6.get(projectCategory);
                                    stats6.put(projectCategory, count + 1);
                                }
                            }
                        } else {
                            System.out.println("WARNING! Skipping contest generation for : " + projectCategory 
                                               + ", " + projectStatus + ", " + requiresSubmission + ", " +
                                               submissionIdGenerator.isAvailable());
                        }
                    }
                }
            }
        }

        System.out.println("OVERALL STATS ON GENERATED PROJECTS:");
        System.out.println(" *** *** BY PROJECT STATUS, PHASE TYPE, PROJECT CATEGORY *** ***");
        for (Map.Entry<ProjectStatus, Map<PhaseType, Map<ProjectCategory, Long>>> e : stats.entrySet()) {
            System.out.println(e.getKey());
            for (Map.Entry<PhaseType, Map<ProjectCategory, Long>> e1 : e.getValue().entrySet()) {
                System.out.println("    " + e1.getKey());
                for (Map.Entry<ProjectCategory, Long> e2 : e1.getValue().entrySet()) {
                    System.out.println("        " +  e2.getKey() + " = " + e2.getValue());
                }
            }
        }
        System.out.println(" *** *** ACTIVE PROJECTS BY PROJECT CATEGORY *** ***");
        for (Map.Entry<ProjectCategory, Long> e : stats2.entrySet()) {
            System.out.println(e.getKey() + " = " + e.getValue());
        }
        System.out.println(" *** *** ACTIVE PROJECTS BY OPEN PHASE *** ***");
        for (Map.Entry<PhaseType, Long> e : stats3.entrySet()) {
            System.out.println(e.getKey() + " = " + e.getValue());
        }
        System.out.println(" *** *** TC DIRECT PROJECTS BY CATEGORIES OF ACTIVE PROJECTS *** ***");
        for (Map.Entry<Long, Map<ProjectCategory, Long>> e : stats4.entrySet()) {
            System.out.println("Tc Direct Project ID: " + e.getKey());
            for (Map.Entry<ProjectCategory, Long> e1 : e.getValue().entrySet()) {
                System.out.println("    " + e1.getKey() + " = " + e1.getValue());
            }
        }
        System.out.println(" *** *** TC DIRECT PROJECTS BY OPEN PHASES OF ACTIVE PROJECTS *** ***");
        for (Map.Entry<Long, Map<PhaseType, Long>> e : stats7.entrySet()) {
            System.out.println("Tc Direct Project ID: " + e.getKey());
            for (Map.Entry<PhaseType, Long> e1 : e.getValue().entrySet()) {
                System.out.println("    " + e1.getKey() + " = " + e1.getValue());
            }
        }
        System.out.println(" *** *** ACTIVE PROJECTS BY PROJECT CATEGORY WITH MILTI-ROUND FORMAT *** ***");
        for (Map.Entry<ProjectCategory, Long> e : stats5.entrySet()) {
            System.out.println(e.getKey() + " = " + e.getValue());
        }
        System.out.println(" *** *** ACTIVE PROJECTS BY PROJECT CATEGORY WITH SINGLE-ROUND FORMAT *** ***");
        for (Map.Entry<ProjectCategory, Long> e : stats6.entrySet()) {
            System.out.println(e.getKey() + " = " + e.getValue());
        }
        System.out.println(" *** *** CONTEST REGISTRANTS *** ***");
        System.out.println("Contests with registrants count = " + contestsWithRegistrantsCount);
        System.out.println("Registrants count = " + totalContestRegistrantsCount);
        if (contestsWithRegistrantsCount != 0) {
            System.out.println("Average registrants count per contest = " 
                               + Math.round(totalContestRegistrantsCount * 1D/ contestsWithRegistrantsCount));
        }
        System.out.println(" *** *** CONTEST SUBMISSIONS *** ***");
        System.out.println("Contests with submissions count = " + contestsWithSubmissionsCount);
        System.out.println("Submissions count = " + totalContestSubmissionsCount);
        if (contestsWithSubmissionsCount != 0) {
            System.out.println("Average submissions count per contest = "
                               + Math.round(totalContestSubmissionsCount * 1D / contestsWithSubmissionsCount));
        }
        System.out.println(" *** *** PROJECT FULFILLMENTS *** ***");
        System.out.println("Completed contests count = " + totalCompletedContestsCount);
        System.out.println("Total contests count = " + this.contests.size());
        if (this.contests.size() != 0) {
            System.out.println("Average fulfillment = "
                               + Math.round(totalCompletedContestsCount * 100D / this.contests.size()));
        }
    }

    /**
     * <p>Checks if specified phase is compatible with specified project status.</p>
     * 
     * @param currentPhaseType a <code>PhaseType</code> referencing the phase type. 
     * @param projectStatus a <code>ProjectStatus</code> referencing the project status. 
     * @return <code>true</code> if specified phase is compatible with project status; <code>false</code> otherwise.
     */
    private boolean areCompatible(PhaseType currentPhaseType, ProjectStatus projectStatus) {
        if (currentPhaseType == null) {
            return true;
        }
        if (projectStatus == ProjectStatus.ACTIVE) {
            return currentPhaseType != PhaseType.POST_MORTEM;
        } else if (projectStatus == ProjectStatus.INACTIVE) {
            return currentPhaseType != PhaseType.POST_MORTEM;
        } else if (projectStatus == ProjectStatus.DELETED) {
            return currentPhaseType != PhaseType.POST_MORTEM;
        } else if (projectStatus == ProjectStatus.CANCELLED_CLIENT_REQUEST) {
            return currentPhaseType != PhaseType.POST_MORTEM;
        } else if (projectStatus == ProjectStatus.CANCELLED_REQUIREMENTS_INFEASIBLE) {
            return currentPhaseType != PhaseType.POST_MORTEM;
        } else if (projectStatus == ProjectStatus.CANCELLED_WINNER_UNRESPONSIVE) {
            return currentPhaseType != PhaseType.POST_MORTEM;
        } else if (projectStatus == ProjectStatus.CANCELLED_ZERO_REGISTRATIONS) {
            return true;
        } else if (projectStatus == ProjectStatus.CANCELLED_ZERO_SUBMISSIONS) {
            return true;
        } else if (projectStatus == ProjectStatus.CANCELLED_FAILED_SCREENING) {
            return true;
        } else if (projectStatus == ProjectStatus.CANCELLED_FAILED_REVIEW) {
            return true;
        } else if (projectStatus == ProjectStatus.COMPLETED) {
            return currentPhaseType != PhaseType.POST_MORTEM;
        } else if (currentPhaseType == PhaseType.POST_MORTEM) {
            return false;
        }
        return true;
    }

    /**
     * <p>Generates a random value in specified range (inclusive).</p>
     * 
     * @param min an <code>int</code> providing the minimum range value. 
     * @param max an <code>int</code> providing the maximum range value.
     * @return an <code>int</code> providing the generated value.
     */
    private static int getRandomInt(int min, int max) {
        int result;
        result = random.nextInt((max - min) + 1) + min;
        return result;
    }

    /**
     * <p>Generate a random boolean value</p>
     *
     * @return the random boolean value.
     * @since 1.4
     */
    private static boolean getRandomBoolean() {
        return getRandomInt(0, 1) == 0;
    }

    /**
     * <p>Gets the random user account from the specified user accounts.</p>
     * 
     * @param users a <code>User</code> array to select user account from. 
     * @return an <code>User</code> selected randomly from specified list.
     */
    private static User getRandomUser(User[] users) {
        return users[getRandomInt(0, users.length - 1)];
    }

    /**
     * <p>Gets the specified number of random user accounts from the specified user accounts.</p>
     * 
     * @param users a <code>User</code> array to select user account from.
     * @param userCount an <code>int</code> providing the number of users to select.
     * @return an <code>User</code> selected randomly from specified list.
     */
    private static User[] getRandomUsers(User[] users, int userCount) {
        List<User> result = new ArrayList<User>();
        List<User> remainingUsers = new ArrayList<User>(Arrays.asList(users));
        for (int i = 0; i < userCount && !remainingUsers.isEmpty(); i++) {
            User user = getRandomUser(remainingUsers.toArray(new User[remainingUsers.size()]));
            result.add(user);
            remainingUsers.remove(user);
        }
        
        return result.toArray(new User[result.size()]);
    }

    /**
     * <p>Calculates the number of records to generate based on specified parameters.</p>
     * 
     * @param minValue an <code>int</code> providing the minimum value.
     * @param maxValue an <code>int</code> providing the maximum value.
     * @param averageValue an <code>int</code> providing the average value.
     * @param currentTotalValue an <code>int</code> providing the current total value.
     * @param contestsCount an <code>int</code> providing the contests count.
     * @param type a <code>String</code> providing the type of entity (for logging)
     * @return an <code>int</code> providing the calculated value.
     */
    private int calculateNumberOfRecordsToGenerate(int minValue, int maxValue, int averageValue, 
                                                   int currentTotalValue, int contestsCount, String type) {
        // TODO : Improve the algorithm
        int expectedTotalValue = contestsCount * averageValue;
        int diff = expectedTotalValue - currentTotalValue;

        StringBuilder b = new StringBuilder();
        b.append("calculateNumberOfRecordsToGenerate for ").append(type).append(" : ");
        b.append("[").append(minValue).append(", ").append(maxValue).append("], average = ").append(averageValue);
        b.append(", expectedTotalRecordsCount = ").append(expectedTotalValue).append(", DIFF = ").append(diff).append(" ,");
        
        int count;
        if (diff > 0) {
            if (diff <= minValue) {
                count = minValue;
            } else if (diff >= maxValue) {
                count = getRandomInt(minValue, maxValue);
            } else {
                count = getRandomInt(minValue, diff);
            }
        } else {
            count = minValue;
        }
        
        b.append(count);

//        System.out.println(b.toString());
        
        return count;
    }

    /**
     * <p>Gets the random user account from the specified user accounts.</p>
     *
     * @param users a <code>TopCoderUser</code> array to select user account from.
     * @return an <code>TopCoderUser</code> selected randomly from specified list.
     */
    private static TopCoderUser getRandomTopCoderUser(TopCoderUser[] users) {
        return users[getRandomInt(0, users.length - 1)];
    }

    /**
     * <p>Gets the specified number of random user accounts from the specified user accounts.</p>
     *
     * @param userCount an <code>int</code> providing the number of users to select.
     * @return an <code>User</code> selected randomly from specified list.
     */
    private TopCoderUser[] getRandomRegistrants(int userCount) {
        List<TopCoderUser> result = new ArrayList<TopCoderUser>();
        List<TopCoderUser> remainingUsers = new ArrayList<TopCoderUser>(this.contestRegistrants);
        for (int i = 0; i < userCount && !remainingUsers.isEmpty(); i++) {
            TopCoderUser user = getRandomTopCoderUser(remainingUsers.toArray(new TopCoderUser[remainingUsers.size()]));
            result.add(user);
            remainingUsers.remove(user);
        }

        return result.toArray(new TopCoderUser[result.size()]);
    }
}
