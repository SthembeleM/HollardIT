package EkunduTestCases;

import commonClasses.CommonSeleniumTester;
import commonClasses.CommonTestTools;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.util.Arrays;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.testng.Assert.*;

public class SearchClient extends BaseClass {

    String DataClientName, DataPolicyNumber;

    private int count = 0;

    @BeforeSuite
    public void GenerateReport(){

       dataSheetName= "Ekundu";
       sheetName="SearchClient";

        generateReport();
    }


    @BeforeMethod
    public void init() throws Exception {

        reportColumns = new String[]

                {"Original Policy Number","Test Results", "OverAll Test Result", "Reason"};

        setUp();

    }


    @Test
    public void SearchByPolicyNumber() throws Exception {

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
            fail(ex.getMessage());
        }



        for (int i = 0; i < data.length; i++) {

            setUpLaunchBrowser();

            count++;

            try {

                String testCaseName;


                DataClientName = CommonTestTools.getCellValue(headers, data[i], "Client Name").trim();
                DataPolicyNumber = CommonTestTools.getCellValue(headers, data[i], "Policy Number").trim();

                testCaseName = CommonTestTools.getCellValue(headers, data[i], "Test Case Name").trim();
                WebDriverWait wait = new WebDriverWait(commonSeleniumTester.getDriver(), 75);

                //Enter Policy Number
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("EkunduPolicyNo")).sendKeys(DataPolicyNumber);
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("EkunduSearchButton")).click();

                //Client name search validation
                String BusinessName = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement( "BusinessName")).getText();
                assertEquals(BusinessName, "ALBERTON LEWENSSENTRUM");

                //Select Client
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("SelectClient")).click();

                //Company name validation
                String CompanyName = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement( "CompanyName")).getText();
                assertEquals(CompanyName, "Iqbal Trading");

                //Close browser
                commonSeleniumTester.getDriver().close();

            }catch (Exception ex){
                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "OverAll Test Result"), count, "FAILED");
                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Reason"), count, ex.getMessage());
                fail(ex.getMessage());
            }


        }

    }

    @Test
    public void SearchByName() throws Exception {
        this.log = Logger.getLogger(SearchClient.class);
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
            fail(ex.getMessage());
        }


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
                Assert.assertEquals(BusinessName, "Iqbal Trading");

                //Select Client
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("SelectClient")).click();

                //Company name validation
                String CompanyName = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement( "CompanyName")).getText();
                Assert.assertEquals(CompanyName, "Iqbal Trading");


                commonSeleniumTester.getDriver().close();



            }catch (Exception ex){
                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "OverAll Test Result"), count, "FAILED");
                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Reason"), count, ex.getMessage());
                fail(ex.getMessage());
            }


        }

    }


    @AfterMethod
    private void getResult(ITestResult result) throws Exception {

        getResults(result);
    }

    @AfterSuite
    public void FlushReport(){

        extentReport.flush();
    }

}










