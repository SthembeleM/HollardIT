package commonClasses;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author f3307530
 */
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Arrays;

public class CommonExcelTester {

    private WritableWorkbook reportWorkbook;
    private WritableSheet reportSheet;
    static String Reason="";
    private String messageFlow = "";


    public CommonExcelTester(){}

    public void CreateExcelDoc(String reportFile)throws  Exception{

        if(reportWorkbook == null){
            reportWorkbook = Workbook.createWorkbook(new File(reportFile));
            reportSheet = reportWorkbook.createSheet("Report_Sheet",0);
        }

    }

    public void writeToReport(String reportFile,int column, int row, String Value) {

        Workbook workbook = null;

        try {
            workbook = Workbook.getWorkbook(new File(reportFile));
            reportWorkbook = Workbook.createWorkbook(new File(reportFile), workbook);
            reportSheet = reportWorkbook.getSheet(0);

            Label TestCaseNo = new Label(column,row, Value);
            reportSheet.addCell(TestCaseNo);

            reportWorkbook.write();
            reportWorkbook.close();

        }   catch (Exception e){
            e.printStackTrace();

        } finally {
            workbook = null;
        }

   }


    public void createReport(String [] columnNames){

        try {

            Label label;
            for(int colCount = 0; colCount < columnNames.length; colCount++) {
                label = new Label(colCount, 0, columnNames[colCount].toString());
                reportSheet.addCell(label);
            }
            reportWorkbook.write();
            reportWorkbook.close();

        }   catch (Exception e){
            e.printStackTrace();

        }

    }


}
