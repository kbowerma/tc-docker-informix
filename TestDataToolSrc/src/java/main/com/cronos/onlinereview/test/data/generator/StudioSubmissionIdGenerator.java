/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package com.cronos.onlinereview.test.data.generator;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>A generator for the IDs to be used when generating the test data for Studio submissions. The generator is 
 * initialized with the list of available IDs which are read from the file.</p>
 * 
 * @author isv
 * @version 1.0 (Release Assembly - TopCoder System Test Data Generator Update 1)
 */
public class StudioSubmissionIdGenerator implements IdGenerator {

    /**
     * <p>A <code>long</code> array providing the available submission IDs.</p>
     */
    private List<Long> submissionIds;

    /**
     * <p>An <code>int</code> providing the index for next available submission ID.</p>
     */
    private int currentIdIndex;

    /**
     * <p>Constructs new <code>StudioSubmissionIdGenerator</code> instance for using the submission IDs provided by the
     * specified file.</p>
     *  
     * @param fileName a <code>String</code> providing the name of the file with submission IDs.
     * @throws IOException if an unexpected error occurs.
     */
    public StudioSubmissionIdGenerator(String fileName) throws IOException {
        this.submissionIds = new ArrayList<Long>();
        RandomAccessFile raf = new RandomAccessFile(fileName, "r");
        try {
            String line;
            while ((line = raf.readLine()) != null) {
                line = line.trim();
                if (line.length() > 0) {
                    Long submissionId = new Long(line);
                    if (!this.submissionIds.contains(submissionId)) {
                        this.submissionIds.add(submissionId);
                    } else {
                        // System.out.println("WARNING! Duplicate Studio submission ID: " + submissionId);
                    }
                }
            }
        } finally {
            raf.close();
        }
        this.currentIdIndex = 0;
    }

    /**
     * <p>Gets the next ID to be used in data generation.</p>
     *
     * @return a <code>long</code> providing the next ID.
     */
    public long getNextId() {
        return this.submissionIds.get(this.currentIdIndex++);
    }

    /**
     * <p>Checks if there is next ID available.</p>
     *
     * @return <code>true</code> if there is next ID available; <code>false</code> otherwise.
     */
    public boolean isAvailable() {
        if (this.currentIdIndex >= this.submissionIds.size()) {
            // System.out.println("WARNING! Studio submission IDs are exhausted");
        }
        return this.currentIdIndex < this.submissionIds.size() ;
    }
}
