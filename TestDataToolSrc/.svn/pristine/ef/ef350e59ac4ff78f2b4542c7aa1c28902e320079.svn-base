/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package com.cronos.onlinereview.test.data.tcscatalog;

/**
 * <p>An enumeration over existing file types. Corresponds to <code>tcs_catalog.file_type_lu</code> database table.</p>
 * 
 * @author TCSDEVELOPER
 * @version 1.0 (Release Assembly - TopCoder System Test Data Generator Update 1)
 */
public enum FileType {
    
    MS_WORD(1, "MS Word", false, false, "doc"),
    
    PLAIN_TEXT(2, "Plain Text", false, false, "txt"),
    
    PDF_ADOBE_ACROBAT(3, "PDF - Adobe Acrobat", false, false, "pdf"),
    
    POSTSCRIPT(4, "Postscript", false, false, "ps"),
    
    HTML(5, "HTML", false, false, "html"),
    
    RICH_TEXT(6, "Rich Text", false, false, "rtf"),
    
    JPG(9, "JPG - Image", true, false, "jpg"),
    
    GIF(10, "GIF - Image", true, false, "gif"),
    
    PNG(11, "PNG - Image", true, false, "png"),
    
    BMP(12, "BMP - Image", true, false, "bmp"),
    
    MS_EXCEL(13, "MS Excel", false, false, "xls"),
    
    ZIP_ARCHIVE(14, "ZIP Archive", false, true, "zip"),
    
    MP3(15, "MP3", false, false, "mp3"),
    
    JAVA_ARCHIVE(16, "Java Archive", false, true, "jar"),
    
    PSD(17, "PSD - Photoshop", false, false, "psd"),
    
    AI(18, "AI - Illustrator", false, false, "ai"),
    
    EPS(19, "EPS", false, false, "eps"),
    
    TIFF(20, "TIFF - Image", true, false, "tiff"),
    
    CSS(21, "CSS", false, false, "css"),
    
    FLA(22, "FLA", false, false, "fla"),
    
    SWF(23, "SWF - Shockwave Flash", false, false, "swf"),
    
    INDD(24, "INDD - InDesign", false, false, "indd"),
    
    MS_POWERPOINT(25, "MS PowerPoint", false, false, "ppt"),
    
    ICON(26, "Icon", true, false, "ico"),
    
    FO_DOCUMENT(27, "FO Document", false, false, "fo"),
    
    XML(28, "XML", false, false, "xml"),
    
    MXML(29, "MXML", false, false, "mxml"),
    
    ACTIONSCRIPT(30, "Actionscript", false, false, "as"),
    
    SWC(31, "SWC", false, false, "swc"),
    
    JAVASCRIPT(32, "JS - Javascript", false, false, "js");

    /**
     * <p>A <code>long</code> providing the ID of this file type.</p>
     */
    private long fileTypeId;

    /**
     * <p>A <code>String</code> providing the name of this file type.</p>
     */
    private String name;

    /**
     * <p>A <code>boolean</code> providing the flag indicating whether this file type corresponds to image or not.</p>
     */
    private boolean imageFile;

    /**
     * <p>A <code>boolean</code> providing the flag indicating whether this file type corresponds to bundled file or
     * not.</p>
     */
    private boolean bundledFile;

    /**
     * <p>A <code>String</code> providing the file name extension.</p>
     */
    private String extension;

    /**
     * <p>Constructs new <code>FileType</code> instance.</p>
     * 
     * @param fileTypeId a <code>long</code> providing the ID of this file type.
     * @param name a <code>String</code> providing the name of this file type.
     * @param imageFile a <code>boolean</code> providing the flag indicating whether this file type corresponds to image 
     *                  or not.
     * @param bundledFile a <code>boolean</code> providing the flag indicating whether this file type corresponds to 
     *                    bundled file or not.
     * @param extension a <code>String</code> providing the file name extension.
     */
    private FileType(long fileTypeId, String name, boolean imageFile, boolean bundledFile, String extension) {
        this.fileTypeId = fileTypeId;
        this.name = name;
        this.imageFile = imageFile;
        this.bundledFile = bundledFile;
        this.extension = extension;
    }

    /**
     * <p>Gets the file name extension.</p>
     *
     * @return a <code>String</code> providing the file name extension.
     */
    public String getExtension() {
        return this.extension;
    }

    /**
     * <p>Gets the flag indicating whether this file type corresponds to bundled file or not.</p>
     *
     * @return a <code>boolean</code> providing the flag indicating whether this file type corresponds to bundled file
     *         or not.
     */
    public boolean getBundledFile() {
        return this.bundledFile;
    }

    /**
     * <p>Gets the flag indicating whether this file type corresponds to image or not.</p>
     *
     * @return a <code>boolean</code> providing the flag indicating whether this file type corresponds to image or not.
     */
    public boolean getImageFile() {
        return this.imageFile;
    }

    /**
     * <p>Gets the name of this file type.</p>
     *
     * @return a <code>String</code> providing the name of this file type.
     */
    public String getName() {
        return this.name;
    }

    /**
     * <p>Gets the ID of this file type.</p>
     *
     * @return a <code>long</code> providing the ID of this file type.
     */
    public long getFileTypeId() {
        return this.fileTypeId;
    }

}
