/*
 * Copyright (C) 2011 - 2014 TopCoder Inc., All Rights Reserved.
 */
package com.cronos.onlinereview.test.data.generator;

import com.cronos.onlinereview.test.data.TopCoderUser;
import com.cronos.onlinereview.test.data.User;
import com.cronos.onlinereview.test.data.commonoltp.SecurityRole;
import com.cronos.onlinereview.test.data.corporateoltp.TcDirectProject;
import com.cronos.onlinereview.test.data.corporateoltp.UserPermissionGrant;
import com.cronos.onlinereview.test.data.informixoltp.Payment;
import com.cronos.onlinereview.test.data.tcscatalog.Catalog;
import com.cronos.onlinereview.test.data.tcscatalog.Component;
import com.cronos.onlinereview.test.data.tcscatalog.ComponentVersion;
import com.cronos.onlinereview.test.data.tcscatalog.ContestSale;
import com.cronos.onlinereview.test.data.tcscatalog.CopilotProfile;
import com.cronos.onlinereview.test.data.tcscatalog.CopilotProject;
import com.cronos.onlinereview.test.data.tcscatalog.FileType;
import com.cronos.onlinereview.test.data.tcscatalog.Group;
import com.cronos.onlinereview.test.data.tcscatalog.GroupMember;
import com.cronos.onlinereview.test.data.tcscatalog.Phase;
import com.cronos.onlinereview.test.data.tcscatalog.PhaseCriteriaType;
import com.cronos.onlinereview.test.data.tcscatalog.PhaseType;
import com.cronos.onlinereview.test.data.tcscatalog.Prize;
import com.cronos.onlinereview.test.data.tcscatalog.Project;
import com.cronos.onlinereview.test.data.tcscatalog.ProjectInfoType;
import com.cronos.onlinereview.test.data.tcscatalog.ProjectSpec;
import com.cronos.onlinereview.test.data.tcscatalog.ProjectType;
import com.cronos.onlinereview.test.data.tcscatalog.Resource;
import com.cronos.onlinereview.test.data.tcscatalog.ResourceInfoType;
import com.cronos.onlinereview.test.data.tcscatalog.Review;
import com.cronos.onlinereview.test.data.tcscatalog.ReviewComment;
import com.cronos.onlinereview.test.data.tcscatalog.ReviewItem;
import com.cronos.onlinereview.test.data.tcscatalog.ReviewItemComment;
import com.cronos.onlinereview.test.data.tcscatalog.ScreeningTask;
import com.cronos.onlinereview.test.data.tcscatalog.StudioProjectConfig;
import com.cronos.onlinereview.test.data.tcscatalog.Submission;
import com.cronos.onlinereview.test.data.tcscatalog.SubmissionDeclaration;
import com.cronos.onlinereview.test.data.tcscatalog.TechnologyType;
import com.cronos.onlinereview.test.data.tcscatalog.Upload;
import com.cronos.onlinereview.test.data.timeoltp.BillingProject;
import com.cronos.onlinereview.test.data.timeoltp.BillingProjectManager;
import com.cronos.onlinereview.test.data.timeoltp.BillingProjectWorker;
import com.cronos.onlinereview.test.data.timeoltp.Client;
import com.cronos.onlinereview.test.data.timeoltp.Company;
import com.cronos.onlinereview.test.data.timeoltp.PaymentTerms;
import com.cronos.onlinereview.test.data.timeoltp.UserAccount;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>A class generating the SQL statements for inserting the test data into database tables to represent a single
 * client in <code>Time Tracker</code> application.</p>
 *
 * <p>
 * Version 1.1 Change notes:
 *   <ol>
 *     <li>Updated {@link #insertProjectInfos(Project, PrintWriter)} method to record project completion timestamp if
 *     set.</li>
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
 *     <li>Updated {@link #insertUploads(Project, PrintWriter)} method to set value for
 *     <code>upload.project_phase_id</code> column. (1.1.4#3)</li>
 *     <li>Added {@link #generateSQLStatements(TopCoderUser, PrintWriter)} method. (1.1.1.2)</li>
 *     <li>Added {@link #insertProjectPayments(Project, PrintWriter)} method. (1.1.3)</li>
 *   </ol>
 * </p>
 *
 * <p>
 * Version 1.4 (Test Data Preparation for topcoder Permission related performance improvement)
 * <ol>
 *     <li>Added SQL generation for the group related data</li>
 *     <li>Added SQL generator for user role realted data</li>
 * </ol>
 * </p>
 *
 * @author isv, TCSASSEMBLER
 * @version 1.4
 */
public class TestDataSQLConverter {

    /**
     * <p>A <code>String</code> providing the default ID for the user to be used as project creator.</p>
     */
    public static final String DEFAULT_PROJECT_AUTHOR = "132456";

    /**
     * The id generator for corporate_oltp::direct_project_account_id.
     *
     * @since 1.4
     */
    private static long DIRECT_PROJECT_ACCOUNT_ID = 1000;

    /**
     * The id generator for the common_oltp:user_role_xref
     *
     * @since 1.4
     */
    private static long USER_ROLE_XREF_ID = 1000;

    /**
     * The id generator for the tcs_catalog:group_member
     *
     * @since 1.4
     */
    private static long GROUP_MEMBER_ID = 1000;

    /**
     * The id generator for the tcs_catalog:group_associated_direct_projects
     *
     * @since 1.4
     */
    private static long GROUP_DIRECT_PROJECT_ID = 1000;

    /**
     * The id generator for the tcs_catalog:customer_administrator
     *
     * @since 1.4
     */
    private static long CUSTOMER_ADMINISTRATOR_ID = 1000;

    /**
     * <p>Constructs new <code>TestDataSQLConverter</code> instance. This implementation does nothing.</p>
     */
    public TestDataSQLConverter() {
    }

    /**
     * <p>Generates the SQL statements for inserting the test data into database tables to represent specified copilot
     * profile and outputs them to specified destination.</p>
     *
     * @param copilotProfile a <code>CopilotProfile</code> providing the specification of the copilot profile to
     *                       generate SQL statements for.
     * @param out          a <code>PrintWriter</code> to write the generated SQL statements to.
     * @since 1.3
     */
    public void generateSQLStatements(CopilotProfile copilotProfile, PrintWriter out) {
        final String SQL_TEMPLATE
            = "INSERT INTO copilot_profile (copilot_profile_id, user_id, copilot_profile_status_id, suspension_count, "
              + "reliability, activation_date, show_copilot_earnings, create_user, create_date, update_user, " +
              "update_date) "
              + "VALUES ({0}, {1}, {2}, {3}, {4}, {5}, ''{6}'', {7}, CURRENT, {8}, CURRENT);";
        String sql = MessageFormat.format(SQL_TEMPLATE,
                                          toString(copilotProfile.getCopilotProfileId()),
                                          toString(copilotProfile.getUserId()),
                                          toString(copilotProfile.getStatus().getCopilotProfileStatusId()),
                                          toString(copilotProfile.getSuspensionCount()),
                                          toString(copilotProfile.getReliability()),
                                          toString(copilotProfile.getActivationTime()),
                                          toString(copilotProfile.getShowEarnings() ? 't' : 'f'),
                                          DEFAULT_PROJECT_AUTHOR,
                                          DEFAULT_PROJECT_AUTHOR);
        out.println(sql);

        List<CopilotProject> copilotProjects = copilotProfile.getProjects();
        if (copilotProjects != null) {
            final String SQL_TEMPLATE2
                = "INSERT INTO copilot_project (copilot_project_id, copilot_profile_id, tc_direct_project_id, " +
                  "copilot_type_id, copilot_project_status_id, private_project, create_user, create_date, modify_user, " +
                  "modify_date) "
                  + "VALUES ({0}, {1}, {2}, 1, 1, ''0'', {3}, CURRENT, {4}, CURRENT);";
            for (CopilotProject copilotProject : copilotProjects) {
                sql = MessageFormat.format(SQL_TEMPLATE2,
                                           toString(copilotProject.getCopilotProjectId()),
                                           toString(copilotProject.getCopilotProfileId()),
                                           toString(copilotProject.getTcDirectProjectId()),
                                           DEFAULT_PROJECT_AUTHOR,
                                           DEFAULT_PROJECT_AUTHOR);
                out.println(sql);
            }
        }
    }

    /**
     * <p>Generates the SQL statements for inserting the test data into database tables to represent specified payment
     * terms and outputs them to specified destination.</p>
     *
     * @param paymentTerms a <code>PaymentTerms</code> providing the specification of the payment terms to generate SQL
     *        statements for.
     * @param out a <code>PrintWriter</code> to write the generated SQL statements to.
     */
    public void generateSQLStatements(PaymentTerms paymentTerms, PrintWriter out) {
        final String SQL_TEMPLATE
            = "INSERT INTO payment_terms (payment_terms_id, description, creation_date, creation_user, "
              + "modification_date, modification_user, active, term) "
              + "VALUES ({0}, ''{1}'', CURRENT, {2}, CURRENT, {3}, {4}, {5});";
        String sql = MessageFormat.format(SQL_TEMPLATE,
                                          String.valueOf(paymentTerms.getPaymentTermsId()),
                                          paymentTerms.getDescription(),
                                          DEFAULT_PROJECT_AUTHOR,
                                          DEFAULT_PROJECT_AUTHOR,
                                          paymentTerms.getActive() ? "1" : "0",
                                          paymentTerms.getTerm());
        out.println(sql);
    }

    /**
     * <p>Generates the SQL statements for inserting the test data into database tables to represent specified client
     * and outputs them to specified destination.</p>
     *
     * @param company a <code>Client</code> providing the specification of the company to generate SQL statements for.
     * @param out a <code>PrintWriter</code> to write the generated SQL statements to.
     */
    public void generateSQLStatements(Company company, PrintWriter out) {
        final String SQL_TEMPLATE
            = "INSERT INTO company (company_id, name, passcode, is_deleted, creation_date, creation_user, "
              + "modification_date, modification_user) "
              + "VALUES ({0}, ''{1}'', ''{2}'', {3}, CURRENT, {4}, CURRENT, {5});";
        String sql = MessageFormat.format(SQL_TEMPLATE,
                                          String.valueOf(company.getCompanyId()),
                                          company.getName(),
                                          company.getPassCode(),
                                          company.getDeleted() ? "1" : "0",
                                          DEFAULT_PROJECT_AUTHOR,
                                          DEFAULT_PROJECT_AUTHOR);
        out.println(sql);
    }

    /**
     * <p>Generates the SQL statements for inserting the test data into database tables to represent specified client
     * and outputs them to specified destination.</p>
     *
     * @param client a <code>Client</code> providing the specification of the client to generate SQL statements for.
     * @param out a <code>PrintWriter</code> to write the generated SQL statements to.
     * @throws IOException if an I/O error occurs.
     */
    public void generateSQLStatements(Client client, PrintWriter out) throws IOException {
        final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (client != null) {
            final String SQL_TEMPLATE
                    = "INSERT INTO client (client_id, name, company_id, payment_term_id, status, salestax, start_date, "
                      + "end_date, code_name, is_deleted, client_status_id, creation_date, creation_user, "
                      + "modification_date, modification_user) " +
                      "VALUES ({0}, ''{1}'', {2}, {3}, {4}, {5}, ''{6}'', ''{7}'', ''{8}'', {9}, {10}, "
                      + "CURRENT, {11}, CURRENT, {12});";
            String sql = MessageFormat.format(SQL_TEMPLATE,
                                              String.valueOf(client.getClientId()),
                                              client.getName(),
                                              String.valueOf(client.getCompany().getCompanyId()),
                                              String.valueOf(client.getPaymentTerms().getPaymentTermsId()),
                                              1,
                                              String.valueOf(client.getSalesTax()),
                                              dateFormat.format(client.getStartDate()),
                                              dateFormat.format(client.getEndDate()),
                                              client.getCodeName(),
                                              client.getDeleted() ? "1" : "0",
                                              String.valueOf(client.getStatus().getId()),
                                              DEFAULT_PROJECT_AUTHOR,
                                              DEFAULT_PROJECT_AUTHOR);
            out.println(sql);

            final String CUSTOMER_ADMINISTRATOR_SQL = "INSERT INTO tcs_catalog:customer_administrator VALUES({0}, {1}, {2});";
            String sql1 = MessageFormat.format(CUSTOMER_ADMINISTRATOR_SQL,
                    String.valueOf(CUSTOMER_ADMINISTRATOR_ID++),
                    String.valueOf(User.getRandomUser().getUserId()),
                    String.valueOf(client.getClientId())
            );
            out.println(sql1);
        }
    }

    /**
     * <p>Generates the SQL statements for inserting the test data into database tables to represent specified billing
     * project and outputs them to specified destination.</p>
     *
     * @param billingProject a <code>BillingProject</code> providing the specification of the billing project to
     *        generate SQL statements for.
     * @param out a <code>PrintWriter</code> to write the generated SQL statements to.
     */
    public void generateSQLStatements(BillingProject billingProject, PrintWriter out) {
        final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final String SQL_TEMPLATE
            = "INSERT INTO project (project_id, company_id, name, active, sales_tax, po_box_number, payment_terms_id, "
              + "description, start_date, end_date, project_status_id, client_id, parent_project_id, is_deleted, "
              + "is_manual_prize_setting, budget, creation_date, creation_user, modification_date, modification_user) "
              + "VALUES ({0}, {1}, ''{2}'', {3}, {4}, ''{5}'', {6}, ''{7}'', ''{8}'', ''{9}'', {10}, {11}, {12}, {13}, "
              + "{14}, {15}, CURRENT, {16}, CURRENT, {17});";
        String sql = MessageFormat.format(SQL_TEMPLATE,
                                          String.valueOf(billingProject.getBillingProjectId()),
                                          String.valueOf(billingProject.getCompanyId()),
                                          billingProject.getName(),
                                          billingProject.getActive() ? "1" : "0",
                                          String.valueOf(billingProject.getSalesTax()),
                                          billingProject.getPoBoxNumber(),
                                          String.valueOf(billingProject.getPaymentTermsId()),
                                          billingProject.getDescription(),
                                          dateFormat.format(billingProject.getStartDate()),
                                          dateFormat.format(billingProject.getEndDate()),
                                          String.valueOf(billingProject.getStatus().getId()),
                                          String.valueOf(billingProject.getClientId()),
                                          String.valueOf(billingProject.getParentProjectId()),
                                          billingProject.getDeleted() ? "1" : "0",
                                          billingProject.getManualPrizeSetting() ? "1" : "0",
                                          String.valueOf(billingProject.getBudget()),
                                          DEFAULT_PROJECT_AUTHOR,
                                          DEFAULT_PROJECT_AUTHOR);
        out.println(sql);
        final String SQL_TEMPLATE2
            = "INSERT INTO client_project (client_id, project_id, " +
              "creation_date, creation_user, modification_date, modification_user) "
              + "VALUES ({0}, {1}, CURRENT, {2}, CURRENT, {3});";
        sql = MessageFormat.format(SQL_TEMPLATE2,
                                   String.valueOf(billingProject.getClientId()),
                                   String.valueOf(billingProject.getBillingProjectId()),
                                   DEFAULT_PROJECT_AUTHOR,
                                   DEFAULT_PROJECT_AUTHOR);
        out.println(sql);
    }

    /**
     * <p>Generates the SQL statements for inserting the test data into database tables to represent specified TC Direct
     * project and outputs them to specified destination.</p>
     *
     * @param tcDirectProject a <code>TcDirectProject</code> providing the specification of the TC Direct project to
     *        generate SQL statements for.
     * @param out a <code>PrintWriter</code> to write the generated SQL statements to.
     */
    public void generateSQLStatements(TcDirectProject tcDirectProject, PrintWriter out) {
        final String SQL_TEMPLATE
            = "INSERT INTO tc_direct_project (project_id, name, description, project_status_id, user_id, create_date, "
              + "modify_date) "
              + "VALUES ({0}, ''{1}'', ''{2}'', {3}, {4}, CURRENT, CURRENT);";
        String sql = MessageFormat.format(SQL_TEMPLATE,
                                          String.valueOf(tcDirectProject.getTcDirectProjectId()),
                                          tcDirectProject.getName(),
                                          tcDirectProject.getDescription(),
                                          String.valueOf(tcDirectProject.getStatus().getId()),
                                          String.valueOf(tcDirectProject.getOwner().getUserId()));

        final String PROJECT_BILLING_SQL =
                "INSERT INTO direct_project_account(direct_project_account_id, project_id, billing_account_id) " +
                "VALUES({0}, {1}, {2});";

        String billingSql = MessageFormat.format(PROJECT_BILLING_SQL, String.valueOf(DIRECT_PROJECT_ACCOUNT_ID++),
                String.valueOf(tcDirectProject.getTcDirectProjectId()),
                String.valueOf(tcDirectProject.getBillingAccount().getBillingProjectId()));

        out.println(sql);
        out.println(billingSql);
    }

    /**
     * <p>Generates the SQL statements for inserting the test data into database tables to represent specified
     * permission grant and outputs them to specified destination.</p>
     *
     * @param userPermissionGrant a <code>UserPermissionGrant</code> providing the specification of the permission grant
     *        to generate SQL statements for.
     * @param out a <code>PrintWriter</code> to write the generated SQL statements to.
     */
    public void generateSQLStatements(UserPermissionGrant userPermissionGrant, PrintWriter out) {
        final String SQL_TEMPLATE
            = "INSERT INTO user_permission_grant (user_permission_grant_id, user_id, resource_id, permission_type_id, "
              + "is_studio) VALUES ({0}, {1}, {2}, {3}, 0);";
        String sql = MessageFormat.format(SQL_TEMPLATE,
                                          String.valueOf(userPermissionGrant.getUserPermissionGrantId()),
                                          String.valueOf(userPermissionGrant.getUserId()),
                                          String.valueOf(userPermissionGrant.getResourceId()),
                                          String.valueOf(userPermissionGrant.getPermissionType().getId()));
        out.println(sql);
    }

    /**
     * <p>Generates the SQL statements for inserting the test data into database tables to represent specified billing
     * user account and outputs them to specified destination.</p>
     *
     * @param userAccount a <code>UserAccount</code> providing the specification of the billing user account to generate
     *        SQL statements for.
     * @param out a <code>PrintWriter</code> to write the generated SQL statements to.
     */
    public void generateSQLStatements(UserAccount userAccount, PrintWriter out) {
        final String SQL_TEMPLATE
            = "INSERT INTO user_account (user_account_id, company_id, account_status_id, user_name, password, "
              + "creation_date, creation_user, modification_date, modification_user) "
              + "VALUES ({0}, {1}, {2}, ''{3}'', ''{4}'', CURRENT, {5}, CURRENT, {6});";
        String sql = MessageFormat.format(SQL_TEMPLATE,
                                          String.valueOf(userAccount.getUserAccountId()),
                                          String.valueOf(userAccount.getCompanyId()),
                                          String.valueOf(userAccount.getStatus().getId()),
                                          userAccount.getUsername(),
                                          userAccount.getPassword(),
                                          DEFAULT_PROJECT_AUTHOR,
                                          DEFAULT_PROJECT_AUTHOR);
        out.println(sql);
    }

    /**
     * <p>Generates the SQL statements for inserting the test data into database tables to represent specified billing
     * project manager and outputs them to specified destination.</p>
     *
     * @param billingProjectManager a <code>BillingProjectManager</code> providing the specification of the billing
     *        project manager to generate SQL statements for.
     * @param out a <code>PrintWriter</code> to write the generated SQL statements to.
     */
    public void generateSQLStatements(BillingProjectManager billingProjectManager, PrintWriter out) {
        final String SQL_TEMPLATE
            = "INSERT INTO project_manager (project_id, user_account_id, pay_rate, cost, active, creation_date, "
              + "creation_user, modification_date, modification_user) "
              + "VALUES ({0}, {1}, {2}, {3}, {4}, CURRENT, {5}, CURRENT, {6});";
        String sql = MessageFormat.format(SQL_TEMPLATE,
                                          String.valueOf(billingProjectManager.getProjectId()),
                                          String.valueOf(billingProjectManager.getUserAccount().getUserAccountId()),
                                          String.valueOf(billingProjectManager.getPayRate()),
                                          String.valueOf(billingProjectManager.getCost()),
                                          billingProjectManager.getActive() ? "1" : "0",
                                          DEFAULT_PROJECT_AUTHOR,
                                          DEFAULT_PROJECT_AUTHOR);
        out.println(sql);
    }

    /**
     * <p>Generates the SQL statements for inserting the test data into database tables to represent specified billing
     * project worker and outputs them to specified destination.</p>
     *
     * @param billingProjectWorker a <code>BillingProjectManager</code> providing the specification of the billing
     *        project worker to generate SQL statements for.
     * @param out a <code>PrintWriter</code> to write the generated SQL statements to.
     */
    public void generateSQLStatements(BillingProjectWorker billingProjectWorker, PrintWriter out) {
        final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final String SQL_TEMPLATE
            = "INSERT INTO project_worker (project_id, user_account_id, pay_rate, cost, active, start_date, end_date,"
              + "creation_date, creation_user, modification_date, modification_user) "
              + "VALUES ({0}, {1}, {2}, {3}, {4}, ''{5}'', ''{6}'', CURRENT, {7}, CURRENT, {8});";
        String sql = MessageFormat.format(SQL_TEMPLATE,
                                          String.valueOf(billingProjectWorker.getProjectId()),
                                          String.valueOf(billingProjectWorker.getUserAccount().getUserAccountId()),
                                          String.valueOf(billingProjectWorker.getPayRate()),
                                          String.valueOf(billingProjectWorker.getCost()),
                                          billingProjectWorker.getActive() ? "1" : "0",
                                          dateFormat.format(billingProjectWorker.getStartDate()),
                                          dateFormat.format(billingProjectWorker.getEndDate()),
                                          DEFAULT_PROJECT_AUTHOR,
                                          DEFAULT_PROJECT_AUTHOR);
        out.println(sql);
    }

    /**
     * <p>Generates the SQL statements for inserting the test data into database tables to represent specified contest
     * and outputs them to specified destination.</p>
     *
     * @param contest a <code>Project</code> providing the specification of the contest to generate SQL statements for.
     * @param out a <code>PrintWriter</code> to write the generated SQL statements to.
     * @param submissionDeclarationsOut a <code>PrintWriter</code> to write the generated SQL statements to.
     * @param isDetailed whether to generate the detailed contest data like prize, specification, phases, resources etc.
     */
    public void generateSQLStatements(Project contest, PrintWriter out, PrintWriter submissionDeclarationsOut, boolean isDetailed)
        throws IOException {
        insertCompCatalog(contest, out);
        insertCompCategories(contest, out);
        insertCompVersions(contest, out);
        insertCompVersionDates(contest, out);
        insertCompTechnology(contest, out);
        insertStudioSpecification(contest, out);
        insertProject(contest, out);
        insertProjectInfos(contest, out);

        if(isDetailed) {
            insertPrizes(contest, out);
            insertProjectSpec(contest, out);
            insertProjectPhases(contest, out);
            insertProjectPhaseDependencies(contest, out);
            insertProjectPhaseCriteria(contest, out);
            insertProjectResources(contest, out);
            insertProjectResourceInfos(contest, out);
            insertUploads(contest, out);
            insertSubmissions(contest, out, submissionDeclarationsOut);
            insertReviews(contest, out);
            insertTimelineNotifications(contest, out);
            insertContestSales(contest, out);
            insertFileTypes(contest, out);
            insertProjectPayments(contest, out);
        }
        out.flush();
    }


    /**
     * <p>Generates the SQL statements for inserting the test data into database tables to represent specified user
     * account and outputs them to specified destination.</p>
     *
     * @param userAccount a <code>UserAccount</code> providing the specification of the billing user account to generate
     *                    SQL statements for.
     * @param out         a <code>PrintWriter</code> to write the generated SQL statements to.
     * @since 1.3
     */
    public void generateSQLStatements(TopCoderUser userAccount, PrintWriter out) {
        final String SQL_TEMPLATE
            = "INSERT INTO common_oltp:security_user (login_id, user_id, password, create_user_id) " +
              "VALUES ({0}, ''{1}'', ''4EjPjy6o+/C+dqNPnxIy9A=='', 0);";
        String sql = MessageFormat.format(SQL_TEMPLATE,
                                          String.valueOf(userAccount.getUserId()),
                                          userAccount.getHandle());
        out.println(sql);

        final String SQL_TEMPLATE2
            = "INSERT INTO common_oltp:user_security_key (user_id, security_key, security_key_type_id) " +
              "VALUES ({0}, ''some key data for {1}'', 1);";
        String sql2 = MessageFormat.format(SQL_TEMPLATE2,
                                           String.valueOf(userAccount.getUserId()),
                                           userAccount.getHandle());
        out.println(sql2);

        final String SQL_TEMPLATE3
            = "INSERT INTO common_oltp:user (user_id, first_name, last_name, create_date, handle, " +
              "status, timezone_id) " +
              "VALUES ({0}, ''{1}'', ''{2}'', CURRENT, ''{3}'', ''A'', 143);";
        String sql3 = MessageFormat.format(SQL_TEMPLATE3,
                                           String.valueOf(userAccount.getUserId()),
                                           String.valueOf(userAccount.getFirstName()),
                                           String.valueOf(userAccount.getLastName()),
                                           String.valueOf(userAccount.getHandle()));
        out.println(sql3);

        final String SQL_TEMPLATE4
            = "INSERT INTO common_oltp:email (user_id, email_id, email_type_id, address, create_date, modify_date, " +
              "primary_ind, status_id) " +
              "VALUES ({0}, {1}, 1, ''test@test.com'', CURRENT, CURRENT, 1, 1);";
        String sql4 = MessageFormat.format(SQL_TEMPLATE4,
                                           String.valueOf(userAccount.getUserId()),
                                           String.valueOf(userAccount.getUserId()));
        out.println(sql4);


        final String SQL_TEMPLATE5
            = "INSERT INTO informixoltp:coder (coder_id, member_since, quote, modify_date, language_id, coder_type_id, " +
              "date_of_birth, comp_country_code, contact_date) " +
              "VALUES ({0}, ''2001-05-09 01:04:42.000'', ''User quote'', ''2008-08-14 14:05:37.000'', 1, 2, NULL, ''840'', NULL);";
        String sql5 = MessageFormat.format(SQL_TEMPLATE5, String.valueOf(userAccount.getUserId()));
        out.println(sql5);


        if (userAccount.getRoles() != null && userAccount.getRoles().size() > 0) {
            final String ROLE_TEMPLATE = "INSERT INTO common_oltp:user_role_xref(user_role_id, login_id, role_id, create_user_id, security_status_id) " +
                    " VALUES({0}, {1}, {2}, {3}, 1);";

            for (SecurityRole role : userAccount.getRoles()) {
                String roleSQL = MessageFormat.format(ROLE_TEMPLATE,
                        String.valueOf(USER_ROLE_XREF_ID++),
                        String.valueOf(userAccount.getUserId()),
                        String.valueOf(role.getId()), "132456"
                );

                out.println(roleSQL);
            }
        }
    }


    /**
     * Geernate the SQL statements for the group related data.
     *
     * @param group the group
     * @param out the print writer
     * @since 1.4
     */
    public void generateSQLStatements(Group group, PrintWriter out) {
        // generate customer_group
        final String groupInsertSQL = "INSERT INTO customer_group(group_id, name, default_permission, client_id, archived, archived_on, effective_group_id, auto_grant) " +
                " VALUES({0}, ''{1}'', ''{2}'', {3}, {4}, {5}, {6}, {7});";

        String sql1 = MessageFormat.format(groupInsertSQL,
                String.valueOf(group.getId()),
                group.getName(),
                String.valueOf(group.getDefaultPermission()),
                String.valueOf(group.getClient().getClientId()),
                String.valueOf(group.getArchived() ? 1 : 0),
                toString(group.getArchivedOn()),
                group.getEffectiveGroup() == null ? "null" : String.valueOf(group.getEffectiveGroup().getId()),
                String.valueOf(group.getAutoGrant() ? 1 : 0)
        );

        out.println(sql1);

        // generate group_member
        final String groupMemberInsertSQL =
                "INSERT INTO group_member(group_member_id, user_id, group_id, specific_permission, active, activated_on, use_group_default) " +
                        " VALUES({0}, {1}, {2}, {3}, {4}, {5}, 1);";
        List<GroupMember> groupMembers = group.getGroupMembers();
        if (groupMembers != null && groupMembers.size() > 0) {
            for (GroupMember gm : groupMembers) {
                String sql2 = MessageFormat.format(groupMemberInsertSQL,
                        String.valueOf(GROUP_MEMBER_ID++),
                        String.valueOf(gm.getUserId()),
                        String.valueOf(gm.getGroup().getId()),
                        null,
                        String.valueOf(gm.getActive() ? 1 : 0),
                        toString(gm.getActivatedOn())
                );
                out.println(sql2);
            }
        }

        // generate group_associated_billing_accounts
        final String groupBillingInsertSQL =
                "INSERT INTO group_associated_billing_accounts(group_id, billing_account_id) VALUES({0}, {1});";
        List<BillingProject> billingAccounts = group.getBillingAccounts();
        if (billingAccounts != null && billingAccounts.size() > 0) {
            for (BillingProject bp : billingAccounts) {
                String sql3 = MessageFormat.format(groupBillingInsertSQL,
                        String.valueOf(group.getId()),
                        String.valueOf(bp.getBillingProjectId())
                );
                out.println(sql3);
            }
        }


        // generate group_associated_direct_projects
        final String groupDirectProjectInsertSQL =
                "INSERT INTO group_associated_direct_projects(group_direct_project_id, group_id, tc_direct_project_id) VALUES({0}, {1}, {2});";
        List<TcDirectProject> directProjects = group.getDirectProjects();
        if (directProjects != null && directProjects.size() > 0) {
            for (TcDirectProject tp : directProjects) {
                String sql4 = MessageFormat.format(groupDirectProjectInsertSQL,
                        String.valueOf(GROUP_DIRECT_PROJECT_ID++),
                        String.valueOf(group.getId()),
                        String.valueOf(tp.getTcDirectProjectId())
                );
                out.println(sql4);
            }
        }

    }

    /**
     * <p>Generates the SQL statements for inserting the test data into database <code>comp_catalog</code> table to
     * represent specified project and outputs them to specified destination.</p>
     *
     * @param project a <code>Project</code> providing the specification of the project to generate SQL statements for.
     * @param out a <code>PrintWriter</code> to write the generated SQL statements to.
     */
    private void insertCompCatalog(Project project, PrintWriter out) {
        Component component = project.getComponent();
        if (component != null) {
            final String SQL_TEMPLATE
                = "INSERT INTO comp_catalog (component_id, current_version, short_desc, component_name, description, " +
                  "function_desc, create_time, status_id, root_category_id, modify_date, public_ind) " +
                  " VALUES ({0}, {1}, ''{2}'', ''{3}'', ''{4}'', ''{5}'', CURRENT, {6}, {7}, CURRENT, {8});";
            String sql = MessageFormat.format(SQL_TEMPLATE,
                                              String.valueOf(component.getComponentId()),
                                              String.valueOf(component.getCurrentVersionId()),
                                              nvl(component.getShortDesc(), "NA"),
                                              component.getComponentName(),
                                              nvl(component.getDescription(), "NA"),
                                              nvl(component.getFunctionalDesc(), "NA"),
                                              String.valueOf(component.getStatusId()),
                                              String.valueOf(component.getRootCategoryId()),
                                              component.isVisible() ? "1" : "0");
            out.println(sql);
        }
    }

    /**
     * <p>Generates the SQL statements for inserting the test data into database <code>comp_categories</code> table to
     * represent specified project and outputs them to specified destination.</p>
     *
     * @param contest a <code>Project</code> providing the specification of the project to generate SQL statements for.
     * @param out a <code>PrintWriter</code> to write the generated SQL statements to.
     */
    private void insertCompCategories(Project contest, PrintWriter out) {
        Component component = contest.getComponent();
        if ((component != null) && (component.getRootCategoryId() > 0)) {
            final String SQL_TEMPLATE
                = "INSERT INTO comp_categories (comp_categories_id, component_id, category_id) "
                  + " VALUES ({0}, {1}, {2});";
            String sql = MessageFormat.format(SQL_TEMPLATE,
                                              String.valueOf(component.getComponentId()),
                                              String.valueOf(component.getComponentId()),
                                              String.valueOf(component.getRootCategoryId()));
            out.println(sql);
        }
    }

    /**
     * <p>Generates the SQL statements for inserting the test data into database <code>comp_versions</code> table to
     * represent specified project and outputs them to specified destination.</p>
     *
     * @param project a <code>Project</code> providing the specification of the project to generate SQL statements for.
     * @param out a <code>PrintWriter</code> to write the generated SQL statements to.
     */
    private void insertCompVersions(Project project, PrintWriter out) {
        Component component = project.getComponent();
        if (component != null) {
            List<ComponentVersion> versions = component.getVersions();
            if (versions != null) {
                final String SQL_TEMPLATE
                    = "INSERT INTO comp_versions (comp_vers_id, component_id, version, version_text, create_time, " +
                      "phase_id, phase_time, price, comments, modify_date, suspended_ind) " +
                      "VALUES ({0}, {1}, {2}, ''{3}'', CURRENT, {4}, CURRENT, {5}, ''{6}'', CURRENT, {7});";
                for (ComponentVersion version : versions) {
                    String sql = MessageFormat.format(SQL_TEMPLATE,
                                                      String.valueOf(version.getVersionId()),
                                                      String.valueOf(component.getComponentId()),
                                                      String.valueOf(version.getVersionNumber()),
                                                      version.getVersionText(),
                                                      String.valueOf(version.getPhaseId()),
                                                      String.valueOf(version.getPrice()),
                                                      version.getComments(),
                                                      version.isSuspended() ? "1" : "0");
                    out.println(sql);
                }
            }
        }
    }

    /**
     * <p>Generates the SQL statements for inserting the test data into database <code>comp_version_dates</code> table
     * to represent specified project and outputs them to specified destination.</p>
     *
     * @param project a <code>Project</code> providing the specification of the project to generate SQL statements for.
     * @param out a <code>PrintWriter</code> to write the generated SQL statements to.
     */
    private void insertCompVersionDates(Project project, PrintWriter out) {
        Component component = project.getComponent();
        if (component != null) {
            List<ComponentVersion> versions = component.getVersions();
            if (versions != null) {
                final String SQL_TEMPLATE
                    = "INSERT INTO comp_version_dates (comp_version_dates_id, comp_vers_id, phase_id, posting_date, " +
                      "initial_submission_date, winner_announced_date, final_submission_date, estimated_dev_date, " +
                      "price, total_submissions, status_id, create_time, level_id, screening_complete_date, " +
                      "review_complete_date, aggregation_complete_date, phase_complete_date, production_date, " +
                      "aggregation_complete_date_comment, phase_complete_date_comment, review_complete_date_comment, " +
                      "winner_announced_date_comment, initial_submission_date_comment, screening_complete_date_comment, " +
                      "final_submission_date_comment, production_date_comment, modify_date) " +
                      "VALUES ({0}, {1}, 112, ''1976-06-05'', ''2000-02-01'', ''2000-02-01'', " +
                      "''2000-02-01'', ''2000-02-01'', 0.00, 0, 301, CURRENT, 100, ''2000-02-01'', " +
                      "''2000-02-01'', ''2000-02-01'', ''2000-02-01'', null, null, null, null, " +
                      "null, null, null, null, null, CURRENT);";
                for (ComponentVersion version : versions) {
                    String sql = MessageFormat.format(SQL_TEMPLATE,
                                                      String.valueOf(version.getVersionId()),
                                                      String.valueOf(version.getVersionId()));
                    out.println(sql);
                }
            }
        }
    }

    /**
     * <p>Generates the SQL statements for inserting the test data into database <code>comp_technology</code> table
     * to represent specified project and outputs them to specified destination.</p>
     *
     * @param project a <code>Project</code> providing the specification of the project to generate SQL statements for.
     * @param out a <code>PrintWriter</code> to write the generated SQL statements to.
     */
    private void insertCompTechnology(Project project, PrintWriter out) {
        Component component = project.getComponent();
        if (component != null) {
            List<ComponentVersion> versions = component.getVersions();
            if (versions != null) {
                final String SQL_TEMPLATE
                    = "INSERT INTO comp_technology (comp_tech_id, comp_vers_id, technology_type_id) " +
                      "VALUES ({0}, {1}, {2});";
                for (ComponentVersion version : versions) {
                    TechnologyType[] technologies = version.getTechnologies();
                    if (technologies != null) {
                        int index = 0;
                        for (TechnologyType technology : technologies) {
                            String sql = MessageFormat.format(SQL_TEMPLATE,
                                                              String.valueOf(version.getVersionId() * 10 + index++),
                                                              String.valueOf(version.getVersionId()),
                                                              String.valueOf(technology.getTechnologyTypeId()));
                            out.println(sql);
                        }
                    }
                }
            }
        }
    }

    /**
     * <p>Generates the SQL statements for inserting the test data into database <code>project</code> table to
     * represent specified project and outputs them to specified destination.</p>
     *
     * @param project a <code>Project</code> providing the specification of the project to generate SQL statements for.
     * @param out a <code>PrintWriter</code> to write the generated SQL statements to.
     */
    private void insertProject(Project project, PrintWriter out) {
        String sql;
        StudioProjectConfig studioSpecification = project.getStudioSpecification();
        if (project.getTcDirectProjectId() > 0) {
            final String SQL_TEMPLATE
                    = "INSERT INTO project (project_id, project_status_id, project_category_id, create_user, " +
                      "create_date, modify_user, modify_date, tc_direct_project_id, project_studio_spec_id) " +
                      "VALUES ({0}, {1}, {2}, {3}, CURRENT, {4}, CURRENT, {5}, {6});";
            sql = MessageFormat.format(SQL_TEMPLATE,
                                       String.valueOf(project.getProjectId()),
                                       String.valueOf(project.getProjectStatus().getProjectStatusId()),
                                       String.valueOf(project.getProjectCategory().getProjectCategoryId()),
                                       DEFAULT_PROJECT_AUTHOR, DEFAULT_PROJECT_AUTHOR,
                                       String.valueOf(project.getTcDirectProjectId()),
                                       studioSpecification == null
                                       ? "NULL" : String.valueOf(studioSpecification.getStudioSpecificationId()));
        } else {
            final String SQL_TEMPLATE
                    = "INSERT INTO project (project_id, project_status_id, project_category_id, create_user, " +
                      "create_date, modify_user, modify_date, project_studio_spec_id) " +
                      "VALUES ({0}, {1}, {2}, {3}, CURRENT, {4}, CURRENT, {5});";
            sql = MessageFormat.format(SQL_TEMPLATE,
                                       String.valueOf(project.getProjectId()),
                                       String.valueOf(project.getProjectStatus().getProjectStatusId()),
                                       String.valueOf(project.getProjectCategory().getProjectCategoryId()),
                                       DEFAULT_PROJECT_AUTHOR, DEFAULT_PROJECT_AUTHOR,
                                       studioSpecification == null ?
                                       "NULL" : String.valueOf(studioSpecification.getStudioSpecificationId()));
        }
        out.println(sql);
    }

    /**
     * <p>Generates the SQL statements for inserting the test data into database <code>project_info</code> table to
     * represent specified project and outputs them to specified destination.</p>
     *
     * @param project a <code>Project</code> providing the specification of the project to generate SQL statements for.
     * @param out a <code>PrintWriter</code> to write the generated SQL statements to.
     */
    private void insertProjectInfos(Project project, PrintWriter out) {
        DateFormat dateFormat = new SimpleDateFormat("MM.dd.yyyy HH:mm z");
        NumberFormat numberFormat = new DecimalFormat("###0");
        Component component = project.getComponent();
        if (component != null) {
            insertSingleProjectInfo(project.getProjectId(), ProjectInfoType.EXTERNAL_REFERENCE_ID,
                                    String.valueOf(component.getComponentId()), out);
            insertSingleProjectInfo(project.getProjectId(), ProjectInfoType.COMPONENT_ID,
                                    String.valueOf(component.getComponentId()), out);
            ComponentVersion currentVersion = component.getCurrentVersion();
            if (currentVersion != null) {
                insertSingleProjectInfo(project.getProjectId(), ProjectInfoType.VERSION_ID,
                                        String.valueOf(currentVersion.getVersionId()), out);
            }
        }
        insertSingleProjectInfo(project.getProjectId(), ProjectInfoType.DEVELOPER_FORUM_ID,
                                String.valueOf(project.getForumId()), out);
        Catalog catalog = project.getCatalog();
        if (catalog != null) {
            insertSingleProjectInfo(project.getProjectId(), ProjectInfoType.ROOT_CATALOG_ID,
                                    String.valueOf(catalog.getRootCategoryId()), out);
        }
        insertSingleProjectInfo(project.getProjectId(), ProjectInfoType.PROJECT_NAME, project.getProjectName(), out);
        insertSingleProjectInfo(project.getProjectId(), ProjectInfoType.PROJECT_VERSION, project.getProjectVersion(),
                                out);
        insertSingleProjectInfo(project.getProjectId(), ProjectInfoType.SVN_MODULE, project.getSvnModule(), out);
        insertSingleProjectInfo(project.getProjectId(), ProjectInfoType.TIMELINE_NOTIFICATION,
                                getTextualValue(project.isTimelineNotificationOption(),
                                                ProjectInfoType.TIMELINE_NOTIFICATION),
                                out);
        insertSingleProjectInfo(project.getProjectId(), ProjectInfoType.AUTOPILOT_OPTION,
                                getTextualValue(project.isAutoPilotOption(), ProjectInfoType.AUTOPILOT_OPTION), out);
        insertSingleProjectInfo(project.getProjectId(), ProjectInfoType.STATUS_NOTIFICATION,
                                project.isStatusNotificationOption() ? "On" : "Off", out);
        insertSingleProjectInfo(project.getProjectId(), ProjectInfoType.PUBLIC, project.isVisible() ? "Yes" : "No",
                                out);
        insertSingleProjectInfo(project.getProjectId(), ProjectInfoType.RATED, project.isRated() ? "Yes" : "No", out);
        insertSingleProjectInfo(project.getProjectId(),
                                ProjectInfoType.ELIGIBILITY, project.getCcaRequired() ? "TopCoder Private" : "Open",
                                out);
        Double price = project.getPrice();
        if (price != null) {
            insertSingleProjectInfo(project.getProjectId(), ProjectInfoType.PAYMENTS, String.valueOf(price), out);
        }
        insertSingleProjectInfo(project.getProjectId(), ProjectInfoType.NOTES, project.getNotes(), out);

        insertSingleProjectInfo(project.getProjectId(), ProjectInfoType.DIGITAL_RUN_FLAG,
                                project.isDigitalRunEnabled() ? "On" : "Off", out);
        if (project.isDigitalRunEnabled()) {
            insertSingleProjectInfo(project.getProjectId(), ProjectInfoType.DR_POINTS,
                                    String.valueOf(project.getDigitalRunPoints()), out);
        }
        insertSingleProjectInfo(project.getProjectId(), ProjectInfoType.ADMIN_FEE,
                                String.valueOf(project.getAdminFee()), out);
        insertSingleProjectInfo(project.getProjectId(), ProjectInfoType.REVIEW_COST,
                                String.valueOf(project.getReviewCost()), out);
        insertSingleProjectInfo(project.getProjectId(), ProjectInfoType.SPEC_REVIEW_COST,
                                String.valueOf(project.getSpecReviewCost()), out);
        insertSingleProjectInfo(project.getProjectId(), ProjectInfoType.FIRST_PLACE_COST,
                                String.valueOf(project.getFirstPlaceCost()), out);
        insertSingleProjectInfo(project.getProjectId(), ProjectInfoType.SECOND_PLACE_COST,
                                String.valueOf(project.getSecondPlaceCost()), out);
        insertSingleProjectInfo(project.getProjectId(), ProjectInfoType.RELIABILITY_BONUS_ELIGIBLE,
                                project.isReliabilityBonusEligible() ? "true" : "false", out);
        if (project.getPhaseDependenciesEditable() != null) {
            insertSingleProjectInfo(project.getProjectId(), ProjectInfoType.PHASE_DEPENDENCIES_EDITABLE,
                                    project.getPhaseDependenciesEditable() ? "true" : "false", out);
        }
        if (project.isReliabilityBonusEligible() && project.getReliabilityBonusCost() != null) {
            insertSingleProjectInfo(project.getProjectId(), ProjectInfoType.RELIABILITY_BONUS_COST,
                                    String.valueOf(project.getReliabilityBonusCost()), out);
        }
        if (project.getMilestoneBonusCost() != null) {
            insertSingleProjectInfo(project.getProjectId(), ProjectInfoType.MILESTONE_BONUS_COST,
                                    String.valueOf(project.getMilestoneBonusCost()), out);
        }
        insertSingleProjectInfo(project.getProjectId(), ProjectInfoType.COST_LEVEL, project.getCostLevel(), out);
        insertSingleProjectInfo(project.getProjectId(), ProjectInfoType.CONFIDENTIALITY_TYPE,
                                project.getConfidentialityType(), out);
        if (project.getBillingProjectId() != null) {
            insertSingleProjectInfo(project.getProjectId(), ProjectInfoType.BILLING_PROJECT,
                                    String.valueOf(project.getBillingProjectId()), out);
        }
        insertSingleProjectInfo(project.getProjectId(), ProjectInfoType.APPROVAL_REQUIRED,
                                project.isApprovalRequired() ? "true" : "false", out);
        insertSingleProjectInfo(project.getProjectId(), ProjectInfoType.SEND_WINNER_EMAILS,
                                project.getSendWinnerEmails() ? "true" : "false", out);
        insertSingleProjectInfo(project.getProjectId(), ProjectInfoType.POST_MORTEM_REQUIRED,
                                project.isPostMortemRequired() ? "true" : "false", out);
        insertSingleProjectInfo(project.getProjectId(), ProjectInfoType.MEMBER_PAYMENTS_ELIGIBLE,
                                project.getMemberPaymentsEligible() ? "true" : "false", out);
        if (project.getTrackLateDeliverables() != null) {
            insertSingleProjectInfo(project.getProjectId(), ProjectInfoType.TRACK_LATE_DELIVERABLES,
                                    project.getTrackLateDeliverables() ? "true" : "false", out);
        }
        if (project.getWinnerUserId() != null) {
            insertSingleProjectInfo(project.getProjectId(), ProjectInfoType.WINNER_EXTERNAL_REFERENCE_ID,
                                    String.valueOf(project.getWinnerUserId()), out);
        }
        if (project.getRunnerUpUserId() != null) {
            insertSingleProjectInfo(project.getProjectId(), ProjectInfoType.RUNNER_UP_EXTERNAL_REFERENCE_ID,
                                    String.valueOf(project.getRunnerUpUserId()), out);
        }
        if (project.getCompletionDate() != null) {
            insertSingleProjectInfo(project.getProjectId(), ProjectInfoType.COMPLETION_TIMESTAMP,
                                    dateFormat.format(project.getCompletionDate()), out);
        }
        if (project.getCopilotCost() != null) {
            insertSingleProjectInfo(project.getProjectId(), ProjectInfoType.COPILOT_COST,
                                    numberFormat.format(project.getCopilotCost()), out);
        }
        if (project.getProjectCategory().getProjectType() == ProjectType.STUDIO) {
            insertSingleProjectInfo(project.getProjectId(), ProjectInfoType.ALLOW_STOCK_ART,
                                    project.getStudioSpecification().getAllowStockArt() ? "true" : "false", out);
            insertSingleProjectInfo(project.getProjectId(), ProjectInfoType.VIEWABLE_SUBMISSIONS_FLAG,
                                    project.getStudioSpecification().getViewableSubmissions() ? "true" : "false", out);
            insertSingleProjectInfo(project.getProjectId(), ProjectInfoType.MAXIMUM_SUBMISSIONS,
                                    String.valueOf(project.getStudioSpecification().getMaximumSubmissions()), out);
        }
    }

    /**
     * <p>Generates the SQL statements for inserting the test data into database <code>project_info</code> table to
     * represent specified project and outputs them to specified destination.</p>
     *
     * @param projectId a <code>Long</code> providing the ID of the project to generate SQL statements for.
     * @param type a <code>ProjectInfoType</code> specifying the type of project info.
     * @param value a <code>String</code> providing the value for project info.
     * @param out a <code>PrintWriter</code> to write the generated SQL statements to.
     */
    private void insertSingleProjectInfo(long projectId, ProjectInfoType type, String value, PrintWriter out) {
        if (value != null) {
            final String SQL_TEMPLATE
                = "INSERT INTO project_info (project_id, project_info_type_id, value, create_user, create_date, " +
                  "modify_user, modify_date) " +
                  "VALUES ({0}, {1}, ''{2}'', {3}, CURRENT, {4}, CURRENT);";
            String sql = MessageFormat.format(SQL_TEMPLATE,
                                              String.valueOf(projectId),
                                              String.valueOf(type.getProjectInfoTypeId()),
                                              value,
                                              DEFAULT_PROJECT_AUTHOR, DEFAULT_PROJECT_AUTHOR);
            out.println(sql);
        }
    }

    /**
     * <p>Generates the SQL statements for inserting the test data into database <code>project_spec</code> table to
     * represent specified project and outputs them to specified destination.</p>
     *
     * @param contest a <code>Project</code> providing the specification of the project to generate SQL statements for.
     * @param out a <code>PrintWriter</code> to write the generated SQL statements to.
     */
    private void insertProjectSpec(Project contest, PrintWriter out) {
        ProjectSpec projectSpec = contest.getProjectSpec();
        if (projectSpec != null) {
            final String SQL_TEMPLATE
                    = "INSERT INTO project_spec (project_spec_id, project_id, version, detailed_requirements, "
                      + "submission_deliverables, environment_setup_instruction, final_submission_guidelines, "
                      + "private_description, create_user, create_date, modify_user, modify_date) " +
                      "VALUES ({0}, {1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}, CURRENT, {9}, CURRENT);";
            String sql = MessageFormat.format(SQL_TEMPLATE,
                                       String.valueOf(projectSpec.getProjectSpecId()),
                                       String.valueOf(projectSpec.getProjectId()),
                                       String.valueOf(projectSpec.getVersion()),
                                       sqlString(projectSpec.getDetailedRequirements()),
                                       sqlString(projectSpec.getSubmissionDeliverables()),
                                       sqlString(projectSpec.getEnvironmentSetupInstructions()),
                                       sqlString(projectSpec.getFinalSubmissionGuidelines()),
                                       sqlString(projectSpec.getPrivateDescription()),
                                       DEFAULT_PROJECT_AUTHOR,
                                       DEFAULT_PROJECT_AUTHOR);
            out.println(sql);
        }
    }

    /**
     * <p>Generates the SQL statements for inserting the test data into database <code>project_phase</code> table to
     * represent specified project and outputs them to specified destination.</p>
     *
     * @param project a <code>Project</code> providing the specification of the project to generate SQL statements for.
     * @param out a <code>PrintWriter</code> to write the generated SQL statements to.
     */
    private void insertProjectPhases(Project project, PrintWriter out) {
        Phase[] phases = project.getPhases();
        if (phases != null) {
            final String SQL_TEMPLATE
                = "INSERT INTO project_phase (project_phase_id, project_id, phase_type_id, phase_status_id, " +
                  "fixed_start_time, scheduled_start_time, scheduled_end_time, actual_start_time, actual_end_time, " +
                  "duration, create_user, create_date, modify_user, modify_date) " +
                  "VALUES ({0}, {1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}, {9}, {10}, CURRENT, {11}, CURRENT);";
            for (Phase phase : phases) {
                try {
                    String sql = MessageFormat.format(SQL_TEMPLATE,
                                                      String.valueOf(phase.getPhaseId()),
                                                      String.valueOf(project.getProjectId()),
                                                      String.valueOf(phase.getPhaseType().getPhaseTypeId()),
                                                      String.valueOf(phase.getPhaseStatus().getPhaseStatusId()),
                                                      toOffsetTime(phase.getFixedStartTimeOffset()),
                                                      toOffsetTime(phase.getScheduledStartTimeOffset()),
                                                      toOffsetTime(phase.getScheduledEndTimeOffset()),
                                                      toOffsetTime(phase.getActualStartTimeOffset()),
                                                      toOffsetTime(phase.getActualEndTimeOffset()),
                                                      String.valueOf(phase.getDuration()),
                                                      DEFAULT_PROJECT_AUTHOR, DEFAULT_PROJECT_AUTHOR);
                    out.println(sql);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * <p>Generates the SQL statements for inserting the test data into database <code>phase_dependency</code> table to
     * represent specified project and outputs them to specified destination.</p>
     *
     * @param project a <code>Project</code> providing the specification of the project to generate SQL statements for.
     * @param out a <code>PrintWriter</code> to write the generated SQL statements to.
     */
    private void insertProjectPhaseDependencies(Project project, PrintWriter out) {
        Phase[] phases = project.getPhases();
        if (phases != null) {
            final String SQL_TEMPLATE
                = "INSERT INTO phase_dependency (dependency_phase_id, dependent_phase_id, dependency_start, " +
                  "dependent_start, lag_time, create_user, create_date, modify_user, modify_date) " +
                  "VALUES ({0}, {1}, {2}, {3}, {4}, {5}, CURRENT, {6}, CURRENT);";
            for (Phase phase : phases) {
                Phase mainPhase = null;
                PhaseType mainPhaseType = phase.getPhaseType().getMainPhaseType();
                if (mainPhaseType != null) {
                    for (Phase phase2 : phases) {
                        if (phase2.getPhaseType() == mainPhaseType) {
                            mainPhase = phase2;
                            break;
                        }
                    }
                }
                if (mainPhase != null) {
                    boolean isSubmission = (phase.getPhaseType() == PhaseType.SUBMISSION);
                    boolean isMilestoneSubmission = (phase.getPhaseType() == PhaseType.MILESTONE_SUBMISSION);
                    String sql = MessageFormat.format(SQL_TEMPLATE,
                                                      String.valueOf(mainPhase.getPhaseId()),
                                                      String.valueOf(phase.getPhaseId()),
                                                      isSubmission || isMilestoneSubmission ? "1" : "0", "1", "0",
                                                      DEFAULT_PROJECT_AUTHOR, DEFAULT_PROJECT_AUTHOR);
                    out.println(sql);
                }
            }
        }
    }

    /**
     * <p>Generates the SQL statements for inserting the test data into database <code>phase_criteria</code> table to
     * represent specified project and outputs them to specified destination.</p>
     *
     * @param project a <code>Project</code> providing the specification of the project to generate SQL statements for.
     * @param out a <code>PrintWriter</code> to write the generated SQL statements to.
     */
    private void insertProjectPhaseCriteria(Project project, PrintWriter out) {
        Phase[] phases = project.getPhases();
        if (phases != null) {
            final String SQL_TEMPLATE
                = "INSERT INTO phase_criteria (project_phase_id, phase_criteria_type_id, parameter, create_user, " +
                  "create_date, modify_user, modify_date) VALUES ({0}, {1}, ''{2}'', {3}, CURRENT, {4}, CURRENT);";
            for (Phase phase : phases) {
                Map criteria = phase.getCriteria();
                if (criteria != null) {
                    Set<PhaseCriteriaType> criteriaTypes = criteria.keySet();
                    for (PhaseCriteriaType criteriaType : criteriaTypes) {
                        String sql = MessageFormat.format(SQL_TEMPLATE,
                                                          String.valueOf(phase.getPhaseId()),
                                                          String.valueOf(criteriaType.getPhaseCriteriaTypeId()),
                                                          criteria.get(criteriaType),
                                                          DEFAULT_PROJECT_AUTHOR, DEFAULT_PROJECT_AUTHOR);
                        out.println(sql);
                    }
                }
            }
        }
    }

    /**
     * <p>Generates the SQL statements for inserting the test data into database <code>resource</code> table to
     * represent specified project and outputs them to specified destination.</p>
     *
     * @param project a <code>Project</code> providing the specification of the project to generate SQL statements for.
     * @param out a <code>PrintWriter</code> to write the generated SQL statements to.
     */
    private void insertProjectResources(Project project, PrintWriter out) {
        List<Resource> resources = project.getResources();
        if (resources != null) {
            final String SQL_TEMPLATE
                = "INSERT INTO resource (resource_id, resource_role_id, project_id, project_phase_id, create_user, " +
                  "create_date, modify_user, modify_date) " +
                  "VALUES ({0}, {1}, {2}, {3}, {4}, CURRENT, {5}, CURRENT);";
            for (Resource resource : resources) {
                Phase phase = resource.getPhase();
                String sql = MessageFormat.format(SQL_TEMPLATE,
                                                  String.valueOf(resource.getResourceId()),
                                                  String.valueOf(resource.getRole().getResourceRoleId()),
                                                  String.valueOf(project.getProjectId()),
                                                  phase != null ? String.valueOf(phase.getPhaseId()) : "NULL",
                                                  DEFAULT_PROJECT_AUTHOR, DEFAULT_PROJECT_AUTHOR);
                out.println(sql);
                String[] sqls = resource.getSql();
                if (sqls != null) {
                    for (String s : sqls) {
                        out.println(s);
                    }
                }
            }
        }
    }

    /**
     * <p>Generates the SQL statements for inserting the test data into database <code>resource_info</code> table to
     * represent specified project and outputs them to specified destination.</p>
     *
     * @param project a <code>Project</code> providing the specification of the project to generate SQL statements for.
     * @param out a <code>PrintWriter</code> to write the generated SQL statements to.
     */
    private void insertProjectResourceInfos(Project project, PrintWriter out) {
        DateFormat regDateFormat = new SimpleDateFormat("MM.dd.yyyy hh:mm aa");
        List<Resource> resources = project.getResources();
        if (resources != null) {
            for (Resource resource : resources) {
                if (resource.getUser() != null) {
                    User user = resource.getUser();
                    insertSingleResourceInfo(resource.getResourceId(), ResourceInfoType.EXTERNAL_REFERENCE_ID,
                                            String.valueOf(user.getUserId()), out);
                    insertSingleResourceInfo(resource.getResourceId(), ResourceInfoType.HANDLE, user.getName(), out);
                    insertSingleResourceInfo(resource.getResourceId(), ResourceInfoType.EMAIL, user.getEmail(), out);
                } else {
                    TopCoderUser user = resource.getTopCoderUser();
                    if (user != null) {
                        insertSingleResourceInfo(resource.getResourceId(), ResourceInfoType.EXTERNAL_REFERENCE_ID,
                                                String.valueOf(user.getUserId()), out);
                        insertSingleResourceInfo(resource.getResourceId(), ResourceInfoType.HANDLE, user.getHandle(), out);
                        insertSingleResourceInfo(resource.getResourceId(), ResourceInfoType.EMAIL, "test@test.com", out);
                    }
                }
                insertSingleResourceInfo(resource.getResourceId(), ResourceInfoType.RATING,
                                         toString(resource.getRating()), out);
                insertSingleResourceInfo(resource.getResourceId(), ResourceInfoType.RELIABILITY,
                                         toString(resource.getReliability()), out);
                insertSingleResourceInfo(resource.getResourceId(), ResourceInfoType.PAYMENT,
                                         toString(resource.getPayment()), out);
                insertSingleResourceInfo(resource.getResourceId(), ResourceInfoType.PAYMENT_STATUS,
                                         toString(resource.getPaymentStatus()), out);
                insertSingleResourceInfo(resource.getResourceId(), ResourceInfoType.SCREENING_SCORE,
                                         toString(resource.getScreeningScore()), out);
                insertSingleResourceInfo(resource.getResourceId(), ResourceInfoType.INITIAL_SCORE,
                                         toString(resource.getInitialScore()), out);
                insertSingleResourceInfo(resource.getResourceId(), ResourceInfoType.FINAL_SCORE,
                                         toString(resource.getFinalScore()), out);
                insertSingleResourceInfo(resource.getResourceId(), ResourceInfoType.PLACEMENT,
                                         toString(resource.getPlacement()), out);
                if (resource.getRegistrationDate() != null) {
                    insertSingleResourceInfo(resource.getResourceId(), ResourceInfoType.REGISTRATION_DATE,
                                             regDateFormat.format(resource.getRegistrationDate()), out);
                }
                Boolean appealsCompletedEarly = resource.isAppealsCompletedEarly();
                if (appealsCompletedEarly != null) {
                    insertSingleResourceInfo(resource.getResourceId(), ResourceInfoType.APPEALS_COMPLETED_EARLY,
                                             appealsCompletedEarly ? "Yes" : "No", out);
                }
            }
        }
    }

    /**
     * <p>Generates the SQL statements for inserting the test data into database <code>resource_info</code> table to
     * represent specified project and outputs them to specified destination.</p>
     *
     * @param resourceId a <code>Long</code> providing the ID of the resource to generate SQL statements for.
     * @param type a <code>ResourceInfoType</code> specifying the type of resource info.
     * @param value a <code>String</code> providing the value for project info.
     * @param out a <code>PrintWriter</code> to write the generated SQL statements to.
     */
    private void insertSingleResourceInfo(long resourceId, ResourceInfoType type, String value, PrintWriter out) {
        if (value != null) {
            final String SQL_TEMPLATE
                = "INSERT INTO resource_info (resource_id, resource_info_type_id, value, create_user, create_date, " +
                  "modify_user, modify_date) " +
                  "VALUES ({0}, {1}, ''{2}'', {3}, CURRENT, {4}, CURRENT);";
            String sql = MessageFormat.format(SQL_TEMPLATE,
                                              String.valueOf(resourceId),
                                              String.valueOf(type.getResourceInfoTypeId()),
                                              value,
                                              DEFAULT_PROJECT_AUTHOR, DEFAULT_PROJECT_AUTHOR);
            out.println(sql);
        }
    }

    /**
     * <p>Generates the SQL statements for inserting the test data into database <code>notification</code> table to
     * represent specified project and outputs them to specified destination.</p>
     *
     * @param contest a <code>Project</code> providing the specification of the project to generate SQL statements for.
     * @param out a <code>PrintWriter</code> to write the generated SQL statements to.
     */
    private void insertTimelineNotifications(Project contest, PrintWriter out) {
        if (contest.isTimelineNotificationOption()) {
            List<Resource> resources = contest.getResources();
            if (resources != null) {
                final String SQL_TEMPLATE
                    = "INSERT INTO notification (project_id, external_ref_id, notification_type_id, create_user, " +
                      "create_date, modify_user, modify_date) " +
                      "VALUES ({0}, {1}, {2}, {3}, CURRENT, {4}, CURRENT);";
                Set<Long> userIds = new HashSet<Long>();
                for (Resource resource : resources) {
                    long userId = resource.getUserId();
                    userIds.add(userId);
                }
                for (Long userId : userIds) {
                    String sql = MessageFormat.format(SQL_TEMPLATE,
                                                      String.valueOf(contest.getProjectId()),
                                                      String.valueOf(userId),
                                                      "1", DEFAULT_PROJECT_AUTHOR, DEFAULT_PROJECT_AUTHOR);
                    out.println(sql);
                }
            }
        }
    }

    /**
     * <p>Generates the SQL statements for inserting the test data into database <code>contest_sale</code> table to
     * represent specified project and outputs them to specified destination.</p>
     *
     * @param contest a <code>Project</code> providing the specification of the project to generate SQL statements for.
     * @param out a <code>PrintWriter</code> to write the generated SQL statements to.
     */
    private void insertContestSales(Project contest, PrintWriter out) {
        ContestSale contestSale = contest.getContestSale();
        if (contestSale != null) {
            final String SQL_TEMPLATE
                = "INSERT INTO contest_sale (contest_sale_id, contest_id, sale_status_id, price, " +
                  "paypal_order_id, create_date, sale_reference_id, sale_type_id) " +
                  "VALUES ({0}, {1}, {2}, {3}, {4}, CURRENT, {5}, {6});";
            String sql = MessageFormat.format(SQL_TEMPLATE,
                                              String.valueOf(contestSale.getContestSaleId()),
                                              String.valueOf(contestSale.getContestId()),
                                              String.valueOf(contestSale.getStatus().getSaleStatusId()),
                                              String.valueOf(contestSale.getPrice()),
                                              sqlString(contestSale.getPaypalOrderId()),
                                              sqlString(contestSale.getSaleReferenceId()),
                                              String.valueOf(contestSale.getType().getSaleTypeId()));
            out.println(sql);
        }
    }

    /**
     * <p>Generates the SQL statements for inserting the test data into database <code>upload</code> table to
     * represent specified project and outputs them to specified destination.</p>
     *
     * @param contest a <code>Project</code> providing the specification of the project to generate SQL statements for.
     * @param out a <code>PrintWriter</code> to write the generated SQL statements to.
     */
    private void insertUploads(Project contest, PrintWriter out) {
        List<Resource> resources = contest.getResources();
        if (resources != null) {
            final String SQL_TEMPLATE
                = "INSERT INTO upload (upload_id, project_id, resource_id, upload_type_id, upload_status_id, " +
                  "parameter, create_user, create_date, modify_user, modify_date, project_phase_id) " +
                  "VALUES ({0}, {1}, {2}, {3}, {4}, {5}, {6}, {7}, {6}, CURRENT, {8});";
            for (Resource resource : resources) {
                Upload[] uploads = resource.getUploads();
                if (uploads != null) {
                    for (Upload upload : uploads) {
                        String sql = MessageFormat.format(SQL_TEMPLATE,
                                                          String.valueOf(upload.getUploadId()),
                                                          String.valueOf(contest.getProjectId()),
                                                          String.valueOf(resource.getResourceId()),
                                                          String.valueOf(upload.getType().getUploadTypeId()),
                                                          String.valueOf(upload.getStatus().getUploadStatusId()),
                                                          sqlString(upload.getParameter()),
                                                          String.valueOf(resource.getUserId()),
                                                          toString(upload.getCreationTimestamp()),
                                                          toString(upload.getProjectPhaseId()));
                        out.println(sql);
                    }
                }

                Submission[] submissions = resource.getSubmissions();
                if (submissions != null) {
                    for (Submission submission : submissions) {
                        Upload upload = submission.getUpload();
                        String sql = MessageFormat.format(SQL_TEMPLATE,
                                                          String.valueOf(upload.getUploadId()),
                                                          String.valueOf(contest.getProjectId()),
                                                          String.valueOf(resource.getResourceId()),
                                                          String.valueOf(upload.getType().getUploadTypeId()),
                                                          String.valueOf(upload.getStatus().getUploadStatusId()),
                                                          sqlString(upload.getParameter()),
                                                          String.valueOf(resource.getUserId()),
                                                          toString(upload.getCreationTimestamp()),
                                                          toString(upload.getProjectPhaseId()));
                        out.println(sql);
                    }
                }

                Submission[] mileStoneSubmissions = resource.getMilestoneSubmissions();
                if (mileStoneSubmissions != null) {
                    for (Submission milestoneSubmission : mileStoneSubmissions) {
                        Upload upload = milestoneSubmission.getUpload();
                        String sql = MessageFormat.format(SQL_TEMPLATE,
                                                          String.valueOf(upload.getUploadId()),
                                                          String.valueOf(contest.getProjectId()),
                                                          String.valueOf(resource.getResourceId()),
                                                          String.valueOf(upload.getType().getUploadTypeId()),
                                                          String.valueOf(upload.getStatus().getUploadStatusId()),
                                                          sqlString(upload.getParameter()),
                                                          String.valueOf(resource.getUserId()),
                                                          toString(upload.getCreationTimestamp()),
                                                          toString(upload.getProjectPhaseId()));
                        out.println(sql);
                    }
                }
            }
        }
    }

    /**
     * <p>Generates the SQL statements for inserting the test data into database <code>submission</code> table to
     * represent specified project and outputs them to specified destination.</p>
     *
     * @param contest a <code>Project</code> providing the specification of the project to generate SQL statements for.
     * @param out a <code>PrintWriter</code> to write the generated SQL statements to.
     * @param submissionDeclarationsOut a <code>PrintWriter</code> to write the generated SQL statements to.
     */
    private void insertSubmissions(Project contest, PrintWriter out, PrintWriter submissionDeclarationsOut)
        throws IOException {
        List<Resource> resources = contest.getResources();
        if (resources != null) {
            for (Resource resource : resources) {
                insertSubmissionsData(out, submissionDeclarationsOut, resource, resource.getSubmissions());
                insertSubmissionsData(out, submissionDeclarationsOut, resource, resource.getMilestoneSubmissions());
            }
        }
    }

    /**
     * <p>Generates the SQL statements for inserting the test data into database <code>review</code>,
     * <code>review_item</code>, <code>review_comment</code>, <code>review_item_comment</code> tables to represent
     * specified project and outputs them to specified destination.</p>
     *
     * @param contest a <code>Project</code> providing the specification of the project to generate SQL statements for.
     * @param out a <code>PrintWriter</code> to write the generated SQL statements to.
     */
    private void insertReviews(Project contest, PrintWriter out) {
        List<Resource> resources = contest.getResources();
        if (resources != null) {
            final String SQL_TEMPLATE
                = "INSERT INTO review (review_id, resource_id, submission_id, project_phase_id, scorecard_id, committed, " +
                  "initial_score, score, create_user, create_date, modify_user, modify_date) " +
                  "VALUES ({0}, {1}, {2}, 200, {3}, {4}, {5}, {6}, {7}, CURRENT, {7}, CURRENT);";
            final String SQL_TEMPLATE2
                = "INSERT INTO review_comment (review_comment_id, resource_id, review_id, comment_type_id, content, " +
                  "extra_info, sort, create_user, create_date, modify_user, modify_date) " +
                  "VALUES ({0}, {1}, {2}, {3}, {4}, {5}, {6}, {7}, CURRENT, {7}, CURRENT);";
            final String SQL_TEMPLATE3
                = "INSERT INTO review_item (review_item_id, review_id, scorecard_question_id, upload_id, answer, " +
                  "sort, create_user, create_date, modify_user, modify_date) " +
                  "VALUES ({0}, {1}, {2}, {3}, {4}, {5}, {6}, CURRENT, {6}, CURRENT);";
            final String SQL_TEMPLATE4
                = "INSERT INTO review_item_comment (review_item_comment_id, resource_id, review_item_id, " +
                  "comment_type_id, content, extra_info, sort, create_user, create_date, modify_user, modify_date) " +
                  "VALUES ({0}, {1}, {2}, {3}, {4}, {5}, {6}, {7}, CURRENT, {7}, CURRENT);";

            for (Resource resource : resources) {
                Review[] reviews = resource.getReviews();
                if (reviews != null) {
                    for (Review review : reviews) {
                        String sql = MessageFormat.format(SQL_TEMPLATE,
                                                          String.valueOf(review.getReviewId()),
                                                          String.valueOf(review.getResourceId()),
                                                          String.valueOf(review.getSubmissionId()),
                                                          String.valueOf(review.getScorecardId()),
                                                          review.getCommitted() ? "1" : "0",
                                                          toString(review.getInitialScore()),
                                                          toString(review.getScore()),
                                                          String.valueOf(resource.getUserId()));
                        out.println(sql);

                        ReviewComment[] reviewComments = review.getComments();
                        if (reviewComments != null) {
                            for (ReviewComment reviewComment : reviewComments) {
                                sql = MessageFormat.format(SQL_TEMPLATE2,
                                                           String.valueOf(reviewComment.getReviewCommentId()),
                                                           String.valueOf(reviewComment.getResourceId()),
                                                           String.valueOf(reviewComment.getReviewId()),
                                                           String.valueOf(reviewComment.getType().getCommentTypeId()),
                                                           sqlString(reviewComment.getContent()),
                                                           sqlString(reviewComment.getExtraInfo()),
                                                           String.valueOf(reviewComment.getSortOrder()),
                                                           String.valueOf(resource.getUserId()));
                                out.println(sql);
                            }
                        }

                        ReviewItem[] reviewItems = review.getItems();
                        if (reviewItems != null) {
                            for (ReviewItem reviewItem : reviewItems) {
                                sql = MessageFormat.format(SQL_TEMPLATE3,
                                                           String.valueOf(reviewItem.getReviewItemId()),
                                                           String.valueOf(reviewItem.getReviewId()),
                                                           String.valueOf(reviewItem.getScorecardQuestionId()),
                                                           toString(reviewItem.getUploadId()),
                                                           sqlString(reviewItem.getAnswer()),
                                                           String.valueOf(reviewItem.getSortOrder()),
                                                           String.valueOf(resource.getUserId()));
                                out.println(sql);

                                ReviewItemComment[] reviewItemComments = reviewItem.getComments();
                                if (reviewItemComments != null) {
                                    for (ReviewItemComment reviewItemComment : reviewItemComments) {
                                        sql = MessageFormat.format(SQL_TEMPLATE4,
                                                                   String.valueOf(
                                                                           reviewItemComment.getReviewItemCommentId()),
                                                                   String.valueOf(reviewItemComment.getResourceId()),
                                                                   String.valueOf(reviewItemComment.getReviewItemId()),
                                                                   String.valueOf(
                                                                           reviewItemComment.getType().getCommentTypeId()),
                                                                   sqlString(reviewItemComment.getContent()),
                                                                   sqlString(reviewItemComment.getExtraInfo()),
                                                                   String.valueOf(reviewItemComment.getSortOrder()),
                                                                   String.valueOf(resource.getUserId()));
                                        out.println(sql);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * <p>Generates the SQL statements for inserting the test data into database
     * <code>project_studio_specification</code> table to represent specified project and outputs them to specified
     * destination.</p>
     *
     * @param contest a <code>Project</code> providing the specification of the project to generate SQL statements for.
     * @param out a <code>PrintWriter</code> to write the generated SQL statements to.
     */
    private void insertStudioSpecification(Project contest, PrintWriter out) {
        StudioProjectConfig studioSpecification = contest.getStudioSpecification();
        if (studioSpecification != null) {
                final String SQL_TEMPLATE
                    = "INSERT INTO project_studio_specification " +
                      "(project_studio_spec_id, goals, target_audience, branding_guidelines, " +
                      "disliked_design_websites, other_instructions, winning_criteria, " +
                      "submitters_locked_between_rounds, round_one_introduction, " +
                      "round_two_introduction, colors, fonts, layout_and_size, contest_introduction, " +
                      "contest_description, content_requirements, general_feedback, create_user, create_date, " +
                      "modify_user, modify_date) " +
                      "VALUES ({0}, ''{1}'', ''{2}'', ''{3}'', ''{4}'', ''{5}'', ''{6}'', ''{7}'', ''{8}'', ''{9}''," +
                      " ''{10}'', ''{11}'', ''{12}'', ''{13}'', ''{14}'', ''{15}'', ''{16}'', " +
                      "{17}, CURRENT, {18}, CURRENT);";
            String sql = MessageFormat.format(SQL_TEMPLATE,
                                              String.valueOf(studioSpecification.getStudioSpecificationId()),
                                              String.valueOf(studioSpecification.getGoals()),
                                              String.valueOf(studioSpecification.getTargetAudience()),
                                              String.valueOf(studioSpecification.getBrandingGuidelines()),
                                              String.valueOf(studioSpecification.getDislikedDesignWebsites()),
                                              String.valueOf(studioSpecification.getOtherInstructions()),
                                              String.valueOf(studioSpecification.getWinningCriteria()),
                                              studioSpecification.getSubmittersLockedBetweenRounds() ? "t" : "f",
                                              String.valueOf(studioSpecification.getRound1Introduction()),
                                              String.valueOf(studioSpecification.getRound2Introduction()),
                                              String.valueOf(studioSpecification.getColors()),
                                              String.valueOf(studioSpecification.getFonts()),
                                              String.valueOf(studioSpecification.getLayoutAndSize()),
                                              String.valueOf(studioSpecification.getContestIntroduction()),
                                              String.valueOf(studioSpecification.getContestDescription()),
                                              String.valueOf(studioSpecification.getContentRequirements()),
                                              String.valueOf(studioSpecification.getGeneralFeedback()),
                                              DEFAULT_PROJECT_AUTHOR, DEFAULT_PROJECT_AUTHOR);
            out.println(sql);
        }
    }

    /**
     * <p>Generates the SQL statements for inserting the test data into database <code>project_file_type_xref</code>
     * table to represent specified project and outputs them to specified destination.</p>
     *
     * @param contest a <code>Project</code> providing the specification of the project to generate SQL statements for.
     * @param out a <code>PrintWriter</code> to write the generated SQL statements to.
     */
    private void insertFileTypes(Project contest, PrintWriter out) {
        List<FileType> fileTypes = contest.getFileTypes();
        if (fileTypes != null) {
            final String SQL_TEMPLATE
                = "INSERT INTO project_file_type_xref (project_id, file_type_id) VALUES ({0}, {1});";
            for (FileType fileType : fileTypes) {
                String sql = MessageFormat.format(SQL_TEMPLATE,
                                                  String.valueOf(contest.getProjectId()),
                                                  String.valueOf(fileType.getFileTypeId()));
                out.println(sql);
            }
        }
    }

    /**
     * <p>Generates the SQL statements for inserting the test data into database <code>prize</code>,
     * <code>project_prize_xref</code> tables to represent specified project and outputs them to specified destination.
     * </p>
     *
     * @param contest a <code>Project</code> providing the specification of the project to generate SQL statements for.
     * @param out a <code>PrintWriter</code> to write the generated SQL statements to.
     */
    private void insertPrizes(Project contest, PrintWriter out) {
        List<Prize> prizes = contest.getPrizes();
        if (prizes != null) {
            final String SQL_TEMPLATE1
                = "INSERT INTO prize (prize_id, project_id, place, prize_amount, prize_type_id, number_of_submissions, " +
                  "                   create_user, create_date, modify_user, modify_date) " +
                  "VALUES ({0}, {7}, {1}, {2}, {3}, {4}, {5}, CURRENT, {6}, CURRENT);";
            for (Prize prize : prizes) {
                String sql = MessageFormat.format(SQL_TEMPLATE1,
                                                  String.valueOf(prize.getPrizeId()),
                                                  String.valueOf(prize.getPlacement()),
                                                  String.valueOf(prize.getAmount()),
                                                  String.valueOf(prize.getType().getPrizeTypeId()),
                                                  String.valueOf(prize.getNumberOfSubmissions()),
                                                  DEFAULT_PROJECT_AUTHOR, DEFAULT_PROJECT_AUTHOR, String.valueOf(
                        contest.getProjectId()));
                out.println(sql);
            }

//            final String SQL_TEMPLATE2
//                = "INSERT INTO project_prize_xref (project_id, prize_id) VALUES ({0}, {1});";
//            for (Prize prize : prizes) {
//                String sql = MessageFormat.format(SQL_TEMPLATE2,
//                                                  String.valueOf(contest.getProjectId()),
//                                                  String.valueOf(prize.getPrizeId()));
//                out.println(sql);
//            }
        }
    }

    private void insertSubmissionsData(PrintWriter out, PrintWriter submissionDeclarationsOut,
                                       Resource resource, Submission[] submissions)
        throws IOException {
        final String SQL_TEMPLATE
            = "INSERT INTO submission (submission_id, upload_id, submission_status_id, screening_score, " +
              "initial_score, final_score, placement, submission_type_id, " +
              "create_user, create_date, modify_user, modify_date, prize_id) " +
              "VALUES ({0}, {1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}, CURRENT, {8}, CURRENT, {9});";
        final String SQL_TEMPLATE2
            = "INSERT INTO resource_submission (resource_id, submission_id, " +
              "create_user, create_date, modify_user, modify_date) " +
              "VALUES ({0}, {1}, {2}, CURRENT, {2}, CURRENT);";
        final String SQL_TEMPLATE3
            = "INSERT INTO screening_task (screening_task_id, upload_id, screening_status_id, screener_id, " +
              "start_timestamp, create_user, create_date, modify_user, modify_date) " +
              "VALUES ({0}, {1}, {2}, {3}, {4}, {5}, CURRENT, {5}, CURRENT);";

        if (submissions != null) {
            for (Submission submission : submissions) {
                String sql = MessageFormat.format(SQL_TEMPLATE,
                                                  String.valueOf(submission.getSubmissionId()),
                                                  String.valueOf(submission.getUpload().getUploadId()),
                                                  String
                                                      .valueOf(submission.getStatus().getSubmissionStatusId()),
                                                  toString(submission.getScreeningScore()),
                                                  toString(submission.getInitialScore()),
                                                  toString(submission.getFinalScore()),
                                                  toString(submission.getPlacement()),
                                                  String.valueOf(submission.getType().getSubmissionTypeId()),
                                                  String.valueOf(resource.getUserId()),
                                                  toString(submission.getPrizeId()));
                out.println(sql);
                sql = MessageFormat.format(SQL_TEMPLATE2,
                                           String.valueOf(resource.getResourceId()),
                                           String.valueOf(submission.getSubmissionId()),
                                           String.valueOf(resource.getUserId()));
                out.println(sql);
                ScreeningTask screeningTask = submission.getScreeningTask();
                if (screeningTask != null) {
                    sql = MessageFormat.format(SQL_TEMPLATE3,
                                               String.valueOf(screeningTask.getScreeningTaskId()),
                                               String.valueOf(screeningTask.getUploadId()),
                                               String.valueOf(screeningTask.getStatus().getScreeningStatusId()),
                                               toString(screeningTask.getScreenerId()),
                                               toString(screeningTask.getStartTimestamp()),
                                               String.valueOf(resource.getUserId()));
                    out.println(sql);
                }
                SubmissionDeclaration declaration = submission.getDeclaration();
                if (declaration != null) {
                    submissionDeclarationsOut.println(declaration.getSubmissionDeclarationId() + "|"
                                                      + submission.getSubmissionId() + "|"
                                                      + declaration.getComment() + "|"
                                                      + (declaration.getHasExternalContent() ? "t" : "f"));
                }
            }
        }
    }

    /**
     * <p>Generates the SQL statements for inserting the test data into database <code>payment</code> table to
     * represent specified project and outputs them to specified destination.</p>
     *
     * @param project a <code>Project</code> providing the specification of the project to generate SQL statements for.
     * @param out     a <code>PrintWriter</code> to write the generated SQL statements to.
     * @since 1.3
     */
    private void insertProjectPayments(Project project, PrintWriter out) {
        List<Payment> payments = project.getPayments();
        if (payments != null) {
            final String SQL_TEMPLATE =
                "INSERT INTO informixoltp:payment_detail (payment_detail_id, component_project_id, payment_type_id, " +
                "installment_number, payment_status_id, payment_desc, total_amount) " +
                "VALUES ({0}, {1}, {2}, 1, {3}, ''{4}'', {5});";

            final String SQL_TEMPLATE2 =
                "INSERT INTO informixoltp:payment (payment_id, most_recent_detail_id, user_id, create_date) " +
                  "VALUES ({0}, {1}, {2}, {3});";


            for (Payment payment : payments) {
                String sql = MessageFormat.format(SQL_TEMPLATE,
                                                  toString(payment.getPaymentDetailId()),
                                                  toString(project.getProjectId()),
                                                  toString(payment.getPaymentType().getPaymentTypeId()),
                                                  toString(payment.getPaymentStatus().getPaymentStatusId()),
                                                  toString(payment.getDescription()),
                                                  toString(payment.getAmount()));
                out.println(sql);

                sql = MessageFormat.format(SQL_TEMPLATE2,
                                                  toString(payment.getPaymentId()),
                                                  toString(payment.getPaymentDetailId()),
                                                  toString(payment.getUserId()),
                                                  toString(project.getCompletionDate()));
                out.println(sql);

            }
        }
    }

    /**
     * <p>Converts the specified offset time to string.</p>
     *
     * @param offset a <code>Long</code> providing the offset to be converted to string.
     * @return a <code>String</code> providing the specification of time.
     */
    private String toOffsetTime(Long offset) {
        if (offset == null) {
            return null;
        } else {
            return "CURRENT + " + offset + " UNITS MINUTE";
        }
    }

    /**
     * <p>Returns second argument if first argument is <code>null</code>.</p>
     *
     * @param value a <code>String</code> providing the text value.
     * @param ifNull a <code>String</code> to be returned if specified value is <code>null</code>.
     * @return a <code>String</code>.
     */
    private String nvl(String value, String ifNull) {
        if (value == null) {
            return ifNull;
        } else {
            return value;
        }
    }

    /**
     * <p>Returns second argument if first argument is <code>null</code>.</p>
     *
     * @param value a <code>String</code> providing the text value.
     * @return a <code>String</code>.
     */
    private String sqlString(String value) {
        if (value == null) {
            return "NULL";
        } else {
            return "'" + value + "'";
        }
    }

    /**
     * <p>Converts specified object to string.</p>
     *
     * @param value an <code>Object</code> to be converted.
     * @return a <code>String</code> providing the converted value.
     */
    private String toString(Object value) {
        if (value == null) {
            return null;
        } else {
            return String.valueOf(value);
        }
    }

    /**
     * <p>Converts specified object to string.</p>
     *
     * @param value an <code>Date</code> to be converted.
     * @return a <code>String</code> providing the converted value.
     */
    private String toString(Date value) {
        final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (value == null) {
            return null;
        } else {
            return "'" + dateFormat.format(value) + "'";
        }
    }

    /**
     * <p>Gets the textual value for project info of specified type.</p>
     *
     * @param isTrue <code>true</code> if project info is set to logical <code>true</code> value; <code>false</code>
     *        otherwise.
     * @param projectInfoType a <code>ProjectInfoType</code> referencing the type of project info.
     * @return a <code>String</code> providing the textual value for project info.
     */
    private String getTextualValue(boolean isTrue, ProjectInfoType projectInfoType) {
        if (isTrue) {
            return projectInfoType.getTrueValue();
        } else {
            return projectInfoType.getFalseValue();
        }
    }
}
