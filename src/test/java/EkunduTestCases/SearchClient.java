package EkunduTestCases;

import com.aventstack.extentreports.Status;
import commonClasses.CommonSeleniumTester;
import commonClasses.CommonTestTools;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.util.Arrays;

public class SearchClient extends BaseClass {

    ITestResult result;

    String DataClientName;


    private int count = 0;
    //static Logger log;
    static Logger log = Logger.getLogger(BasicClaims.OpenClaim.class);

    @BeforeMethod
    public void init() throws Exception {

        reportColumns = new String[]

                {"Original Policy Number","Test Results", "OverAll Test Result", "Reason"};

        setUp();

    }


    @Test
    public void SearchClient() throws Exception {
        //this.log = Logger.getLogger(OpenClaim.class);
        log.info("Start @Test method");
        String[][] data = null;
        byte[] screenshot;


        commonSeleniumTester = new CommonSeleniumTester();
        prop.getProperty(dataSheetName= "Ekundu");
        prop.getProperty(sheetName="SearchClient");

        try {

            data = getDataSheetArray(testDataDir, dataSheetName, sheetName);
            headers = data[0];
            data = Arrays.copyOfRange(data, 1, data.length);


        } catch (Exception ex) {
            commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Test Results"), 1, "FAILED");
            commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Reason"), 1, ex.getMessage());
            Assert.fail(ex.getMessage());
        }


        generateReport();


        for (int i = 0; i < data.length; i++) {

            setUpLaunchBrowser();

            String policyNumber;

            count++;


            try {

                String testCaseName;


                DataClientName = CommonTestTools.getCellValue(headers, data[i], "Client Name").trim();

                testCaseName = CommonTestTools.getCellValue(headers, data[i], "Test Case Name").trim();
                WebDriverWait wait = new WebDriverWait(commonSeleniumTester.getDriver(), 75);

                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("FindClientName")).sendKeys(DataClientName);
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("EkunduSearchButton")).click();

                 //Client name search validation
                String BusinessName = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement( "BusinessName")).getText();
                Assert.assertEquals( BusinessName, "Iqbal Tradingg");




            }catch (Exception ex){
                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "OverAll Test Result"), count, "FAILED");
                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Reason"), count, ex.getMessage());
                Assert.fail(ex.getMessage());
            }

        }

    }


    public void CaseDetailsValidations(){



        try {




        }catch (Exception ex){
            commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "OverAll Test Result"), count, "FAILED");
            commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Reason"), count, ex.getMessage());
            Assert.fail(ex.getMessage());
        }

    }




    @AfterMethod
    public void getResult(ITestResult result) throws Exception {

        getResults(result);
    }

    @AfterTest
    public void CloseBrowser(){

        extentReport.flush();
    }



}










