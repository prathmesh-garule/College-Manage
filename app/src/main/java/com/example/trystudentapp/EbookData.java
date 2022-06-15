package com.example.trystudentapp;

public class EbookData {
    private String pdfTitle,pdfURL;
    public EbookData() {

    }

    public EbookData(String pdfTitle, String pdfURL) {
        this.pdfTitle = pdfTitle;
        this.pdfURL = pdfURL;
    }

    public String getPdfTitle() {
        return pdfTitle;
    }

    public void setPdfTitle(String pdfTitle) {
        this.pdfTitle = pdfTitle;
    }

    public String getPdfURL() {
        return pdfURL;
    }

    public void setPdfURL(String pdfURL) {
        this.pdfURL = pdfURL;
    }
}
