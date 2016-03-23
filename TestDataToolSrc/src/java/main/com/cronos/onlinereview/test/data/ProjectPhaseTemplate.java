/*
 * Copyright (C) 2011-2012 TopCoder Inc., All Rights Reserved.
 */
package com.cronos.onlinereview.test.data;

import com.cronos.onlinereview.test.data.tcscatalog.PhaseType;

import java.util.Arrays;
import java.util.List;

/**
 * <p>An enumeration over project phase templates.</p>
 *
 * <p>
 * Version 1.1 (Release Assembly - TopCoder System Test Data Generator Update 2 Assembly 1.0) Change notes:
 *   <ol>
 *     <li>Added {@link #COPILOT_POSTING} item. (1.1.4#2)</li>
 *   </ol>
 * </p>
 * 
 * @author isv
 * @version 1.1 (Release Assembly - TopCoder System Test Data Generator Update 1)
 */
public enum ProjectPhaseTemplate {
    
    DESIGN(Arrays.asList(new Item(48, PhaseType.SPECIFICATION_SUBMISSION),
                         new Item(2, PhaseType.SPECIFICATION_REVIEW),
                         new Item(48, PhaseType.REGISTRATION),
                         new Item(96, PhaseType.SUBMISSION),
                         new Item(12, PhaseType.SCREENING),
                         new Item(48, PhaseType.REVIEW),
                         new Item(24, PhaseType.APPEALS),
                         new Item(12, PhaseType.APPEALS_RESPONSE),
                         new Item(12, PhaseType.AGGREGATION),
                         new Item(24, PhaseType.FINAL_FIX),
                         new Item(12, PhaseType.FINAL_REVIEW),
                         new Item(120, PhaseType.APPROVAL))),

    DEVELOPMENT(Arrays.asList(new Item(48, PhaseType.SPECIFICATION_SUBMISSION),
                              new Item(2, PhaseType.SPECIFICATION_REVIEW),
                              new Item(48, PhaseType.REGISTRATION),
                              new Item(96, PhaseType.SUBMISSION),
                              new Item(12, PhaseType.SCREENING),
                              new Item(48, PhaseType.REVIEW),
                              new Item(24, PhaseType.APPEALS),
                              new Item(12, PhaseType.APPEALS_RESPONSE),
                              new Item(12, PhaseType.AGGREGATION),
                              new Item(24, PhaseType.FINAL_FIX),
                              new Item(12, PhaseType.FINAL_REVIEW),
                              new Item(120, PhaseType.APPROVAL))),

    APPLICATION(Arrays.asList(new Item(48, PhaseType.SPECIFICATION_SUBMISSION),
                              new Item(2, PhaseType.SPECIFICATION_REVIEW),
                              new Item(48, PhaseType.REGISTRATION),
                              new Item(120, PhaseType.SUBMISSION),
                              new Item(12, PhaseType.SCREENING),
                              new Item(48, PhaseType.REVIEW),
                              new Item(24, PhaseType.APPEALS),
                              new Item(12, PhaseType.APPEALS_RESPONSE),
                              new Item(12, PhaseType.AGGREGATION),
                              new Item(24, PhaseType.FINAL_FIX),
                              new Item(12, PhaseType.FINAL_REVIEW),
                              new Item(120, PhaseType.APPROVAL))),

    APPLICATION_WITH_MILESTONE(Arrays.asList(new Item(48, PhaseType.SPECIFICATION_SUBMISSION),
                                             new Item(2, PhaseType.SPECIFICATION_REVIEW),
                                             new Item(48, PhaseType.REGISTRATION),
                                             new Item(72, PhaseType.MILESTONE_SUBMISSION),
                                             new Item(24, PhaseType.MILESTONE_REVIEW),
                                             new Item(120, PhaseType.SUBMISSION),
                                             new Item(12, PhaseType.SCREENING),
                                             new Item(48, PhaseType.REVIEW),
                                             new Item(24, PhaseType.APPEALS),
                                             new Item(12, PhaseType.APPEALS_RESPONSE),
                                             new Item(12, PhaseType.AGGREGATION),
                                             new Item(24, PhaseType.FINAL_FIX),
                                             new Item(12, PhaseType.FINAL_REVIEW),
                                             new Item(120, PhaseType.APPROVAL))),

    STUDIO(Arrays.asList(new Item(48, PhaseType.REGISTRATION), new Item(144, PhaseType.SUBMISSION), 
                         new Item(12, PhaseType.SCREENING), new Item(144, PhaseType.REVIEW))),
    
    STUDIO_WITH_MILESTONE(Arrays.asList(new Item(48, PhaseType.REGISTRATION), 
                                        new Item(72, PhaseType.MILESTONE_SUBMISSION),
                                        new Item(12, PhaseType.MILESTONE_SCREENING),
                                        new Item(24, PhaseType.MILESTONE_REVIEW),
                                        new Item(168, PhaseType.SUBMISSION),
                                        new Item(12, PhaseType.SCREENING), new Item(96, PhaseType.REVIEW))),

    COPILOT_POSTING(Arrays.asList(new Item(48, PhaseType.REGISTRATION), 
                                  new Item(72, PhaseType.SUBMISSION), 
                                  new Item(48, PhaseType.REVIEW)));
    
    /**
     * <p>A class representing a single item in project phase template.</p>
     * 
     * @author isv
     * @version 1.0
     */
    public static class Item {

        /**
         * <p>A <code>long</code> providing the duration of phase in hours.</p>
         */
        private long duration;

        /**
         * <p>A <code>PhaseType</code> providing the type of the phase.</p>
         */
        private PhaseType phaseType;

        /**
         * <p>Constructs new <code>ProjectPhaseTemplate$Item</code> instance.</p>
         * 
         * @param duration a <code>long</code> providing the duration of phase in hours.
         * @param phaseType a <code>PhaseType</code> providing the type of the phase.
         */
        private Item(long duration, PhaseType phaseType) {
            this.duration = duration;
            this.phaseType = phaseType;
        }

        /**
         * <p>Gets the type of the phase.</p>
         *
         * @return a <code>PhaseType</code> providing the type of the phase.
         */
        public PhaseType getPhaseType() {
            return this.phaseType;
        }

        /**
         * <p>Gets the duration of phase in hours.</p>
         *
         * @return a <code>long</code> providing the duration of phase in hours.
         */
        public long getDuration() {
            return this.duration;
        }

        @Override
        public String toString() {
            return "Item{" +
                   "duration=" + duration +
                   ", phaseType=" + phaseType +
                   '}';
        }
    }

    /**
     * <p>A <code>List<Item></code> providing the items of this project phases template.</p>
     */
    private List<Item> items;

    /**
     * <p>Constructs new <code>ProjectPhaseTemplate</code> instance.</p>
     * 
     * @param items a <code>List<Item></code> providing the items of this project phases template.
     */
    private ProjectPhaseTemplate(List<Item> items) {
        this.items = items;
    }

    /**
     * <p>Gets the items of this project phases template.</p>
     *
     * @return a <code>List<Item></code> providing the items of this project phases template.
     */
    public List<Item> getItems() {
        return this.items;
    }

    /**
     * <p>Checks if this template contains the specified phase type.</p>
     * 
     * @param phaseType a <code>PhaseType</code> specifying the phase type to check. 
     * @return <code>true</code> if specified phase type is contained by the template; <code>false</code> otherwise.
     */
    public boolean contains(PhaseType phaseType) {
        return getItem(phaseType) != null;
    }

    /**
     * <p>Checks if this template contains the specified phase type.</p>
     * 
     * @param phaseType a <code>PhaseType</code> specifying the phase type to check. 
     * @return an <code>Item</code> providing the data for the specified phase type or <code>null</code> if such a phase
     *         type is not found in this template. 
     */
    public Item getItem(PhaseType phaseType) {
        for (Item item : getItems()) {
            if (item.getPhaseType() == phaseType) {
                return item;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "ProjectPhaseTemplate{" +
               "items=" + items +
               '}';
    }
}
