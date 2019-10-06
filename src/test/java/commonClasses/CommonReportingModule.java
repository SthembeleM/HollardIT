/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commonClasses;

import java.io.*;

/**
 * Created by f3307530 on 2015/02/17.
 */
public class CommonReportingModule {

    public StringBuilder html = new StringBuilder();

    public CommonReportingModule(String title,String fnbLogoPath) {
        html.append("<!doctype html>\n");
        html.append("<html lang='en'>\n");
        html.append("<head>\n");
        html.append("<meta charset='utf-8'>\n");
        html.append("<title>" + title + "</title>\n");
        html.append("</head>\n\n");
        html.append("<body>\n");
        html.append("<img src=\'"+fnbLogoPath+"\' alt=\"FNB Logo\" height=\"143\" width=\"352\">\n");
      //  html.append("<img src='./images/FNBLogo.png' alt=\"FNB Logo\" height=\"143\" width=\"352\">");

        html.append("<h1>" + title + "</h1>\n");
        //html.append("<h2>" + "Test Summary" + "</h2>\n");
        html.append("<table border=\"2px solid black\" style=\"width:50%\"  cellpadding=\"2\" cellspacing=\"0\">\n");
        html.append("<caption>Test Results Summary</caption>\n");

        //html.append( "</body>\n\n");
        //html.append( "</html>" );
    }

    public void appendTable(String csvString) {
        String[] rowValues = csvString.split(",");
        html.append("<tr>\n");
        for (int i = 0; i < rowValues.length; i++) {
            html.append("<td><font size=\"3\">" + rowValues[i] + "</td>\n");
        }
        html.append("</tr>\n");
    }

    public void appendTable(String csvString, String [] messageFiles) {
        String[] rowValues = csvString.split(",");
        html.append("<tr>\n");
        for (int i = 0; i < rowValues.length; i++) {
            html.append("<td text-align:center><font size=\"3\">" + rowValues[i] + "</td>\n");
        }

        File f;
        for(String fileName : messageFiles) {
            f =new File(fileName);
            if (!fileName.isEmpty())
                html.append("<td><a href=\'" + fileName + "\'>" + f.getName() + "</a></td>\n");
        }
        html.append("</tr>\n");
    }

    public void closeTable() {
        html.append("</table>\n");
        html.append("<br/>\n");
        html.append("<br/>\n");
    }

    public void appendStats(String overallStatus, String numberOfTests, String failedTests, String passedTests, String passPercentage) {

        //html.append("<h3>" + "Test Results" + "</h3>\n");
        html.append("<table border=\"2px solid black\" style=\"width:50%\"  cellpadding=\"3\" cellspacing=\"0\">\n");
        html.append("<caption>Test Results</caption>\n");
        html.append("<tr>\n");
        html.append("<td><font size=\"3\">" + "Overall Test Status" + "</td>\n");
        html.append("<td><font size=\"3\">" + overallStatus + "</td>\n");
        html.append("</tr>\n");
        html.append("<tr>\n");
        html.append("<td><font size=\"3\">" + "Total Number of Tests" + "</td>\n");
        html.append("<td><font size=\"3\">" + numberOfTests + "</td>\n");
        html.append("</tr>\n");
        html.append("<tr>\n");
        html.append("<td><font size=\"3\">" + "Failed Tests" + "</td>\n");
        html.append("<td><font size=\"3\">" + failedTests + "</td>\n");
        html.append("</tr>\n");
        html.append("<tr>\n");
        html.append("<td><font size=\"3\">" + "Passed Tests" + "</td>\n");
        html.append("<td><font size=\"3\">" + passedTests + "</td>\n");
        html.append("</tr>\n");
        html.append("<tr>\n");
        html.append("<td><font size=\"3\">" + "Pass Percentage" + "</td>\n");
        html.append("<td><font size=\"3\">" + passPercentage + "</td>\n");
        html.append("</tr>\n");
        html.append("</table>\n");
        //html.append("<table border=\"4px solid black\" style=\"width:50%\"  cellpadding=\"3\" cellspacing=\"0\">\n");

    }

    public void appendHeadings(String csvString) {
        String[] rowValues = csvString.split(",");
        html.append("<tr>\n");
        for (int i = 0; i < rowValues.length; i++) {
            html.append("<th><font size=\"4\">" + rowValues[i] + "</th>\n");
        }
        html.append("</tr>\n");
    }

    public boolean isFileLocked(File file) {

        boolean isFileUnlocked = false;
        try  {
            org.apache.commons.io.FileUtils.touch(file);
            isFileUnlocked = true;
        } catch(IOException e) {
            isFileUnlocked = false;
        }
        return  isFileUnlocked;
    }


    public void closeReport(String reportName) {
        html.append("</table>\n");
        html.append("</body>\n</html>");

        try {
            File file = new File(reportName);
            if(!file.exists())
                file.createNewFile();

            // if(isFileLocked(file)){
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(html.toString());
            writer.close();
            System.out.println("Done Writing");
           /* }
            else{
                throw  new IOException("Cannot write to the file, file is open by another process");
            }*/
        /*    if (file.createNewFile()) {
                System.out.println("File is created!");
            } else {
                System.out.println("File already exists.");
            }*/

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void main(String [] args){
       CommonReportingModule commonReportingModule = new CommonReportingModule("CLS FX Trades Report","./images/FNBLogo.png");
        commonReportingModule.appendHeadings("Test case number, Scenario, Overall status, Message");
        String  [] message = new String[]{"file:///D:/Projects/Incentage/selenium-test/CLSTest/TestData/files/Sample_fxtr.008.001.04.txt"};
        String response = "file:///D:/Projects/Incentage/selenium-test/CLSTest/TestData/files/Sample_fxtr.008.001.04.txt";
        String resposeType = "ResponseNack"; //ResponseError, ResponseInBoundMessage
        commonReportingModule.appendTable("1, loyiso, pass", message);
        commonReportingModule.closeTable();
        commonReportingModule.appendStats("0","1","0","1","1");
        commonReportingModule.closeReport("Report.html");
    }
}
