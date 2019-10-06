package testScenario;

import commonClasses.CommonSeleniumTester;
import commonClasses.CommonTestTools;
import commonClasses.LoginPopup;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.stringtemplate.v4.ST;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import javax.swing.*;
import javax.xml.crypto.Data;
import java.io.File;
import java.util.Arrays;
import java.util.List;

public class CloseCase extends BaseClass {

    String DataClientName;
    String DataAgentBroker;
    String DataPrimaryCause;

    private int count = 0;
    private int rowIteration = 0;
    //Logger log = Logger.getLogger(CloseCase.class);

    @BeforeMethod
    public void
    init() throws Exception {

        reportColumns = new String[]

                {"Original Policy Number", "Client Name", "Agent/Broker", "Primary Cause", "Test Results", "OverAll Test Result", "Reason"};

        setUp();
    }

    @Test
    public void CloseCase() throws Exception {
        BasicConfigurator.configure();

        log.info("Start @Test method");
        String[][] data = null;
        byte[] screenshot;

        commonSeleniumTester = new CommonSeleniumTester();
        loginPopup = new LoginPopup();




        try {
            data = getDataSheetArray(testDataDir, dataSheetName, sheetName);
            headers = data[0];
            data = Arrays.copyOfRange(data, 1, data.length);


        } catch (Exception ex) {
            commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Test Results"), 1, "FAILED");
            commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Reason"), 1, ex.getMessage());
            Assert.fail(ex.getMessage());
        }

        setUpLaunchBrowser();


        for (int i = 0; i < data.length; i++) {

            String policyNumber;
            String username = CommonTestTools.getCellValue(headers, data[i], "Username").trim();
            String password=CommonTestTools.getCellValue(headers, data[i], "Password").trim();

            loginPopup.login(commonSeleniumTester,username,password);

            count++;

            try {

                String testCaseName;

                DataClientName = CommonTestTools.getCellValue(headers, data[i], "Client Name").trim();
                DataAgentBroker = CommonTestTools.getCellValue(headers, data[i], "Agent/Broker").trim();
                DataPrimaryCause = CommonTestTools.getCellValue(headers, data[i], "Primary Cause").trim();


                log.info("Testing");
                testCaseName = CommonTestTools.getCellValue(headers, data[i], "Test Case Name").trim();
                policyNumber = CommonTestTools.getCellValue(headers, data[i], "Policy Number").trim();
                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Original Policy Number"), count, policyNumber);

                log.info("Testing2");
                WebDriverWait wait = new WebDriverWait(commonSeleniumTester.getDriver(), 75);
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("SearchFor")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("SearchFor")).click();

                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("SearchFor1")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("SearchFor1")).click();

                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("PolicyNo"))).sendKeys(policyNumber);
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("Search")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Search")).click();

                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'" + policyNumber + "')]")));
                commonSeleniumTester.getDriver().findElement(By.xpath("//td[contains(text(),'" + policyNumber + "')]")).click();

                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("CloseCaseButton")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("CloseCaseButton")).click();
                Thread.sleep(2000);
                //wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("CloseCaseReason")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("CloseCaseReason")).click();

                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("CloseCaseReason1")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("CloseCaseReason1")).click();

                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("CloseCaseReasonOK")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("CloseCaseReasonOK")).click();

                Thread.sleep(7000);
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("CaseVersionsTab")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("CaseVersionsTab")).click();

                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("ClaimVersionMatterSettled")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("ClaimVersionMatterSettled")).click();


                String CaseNumber1 = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("CaseNumber2")).getText();

                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("ClaimVersionDetailsHeader")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("ClaimVersionDetailsHeader")).click();

                commonSeleniumTester.getDriver().navigate().refresh();
                Thread.sleep(8000);

                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("SearchFor")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("SearchFor")).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("SearchFor1")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("SearchFor1")).click();

                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("CaseNum")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("CaseNum")).sendKeys(CaseNumber1);


                Thread.sleep(5000);


//                log.info("Reading data sheet");
//                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
//                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 1.Loading_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);
//
//                // click Super Inquiry Search Results - Policy
//                commonSeleniumTester.getDriver().findElement(By.xpath("//*[@id='" + policyNumber + "']/td[1]")).click();
//                Thread.sleep(5000);
//
//                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
//                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 2.Policy information_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);
//
//                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("ClaimTab")).click();
//                Thread.sleep(2000);
//                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
//                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 3.Claim Tab_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);
//
//                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Claim1")).click();
//                Thread.sleep(5000);
//                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
//                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 4.Case Information_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);
//
//                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("PaymentsTab")).click();
//                Thread.sleep(5000);
//                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
//                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 5.Payments Tab_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);
//
//
//                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Payee")).click();
//                Thread.sleep(5000);
//                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
//                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 6.Payee Details_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);
//
//
//                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("AuthoriseButton")).click();
//                Thread.sleep(5000);
//                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
//                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 7.Claim Bordereau_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);
//
//                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("PaymentsTab")).click();
//                Thread.sleep(5000);
//                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
//                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 8.Payments Tab_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);


                commonSeleniumTester.getDriver().quit();


            } catch (Exception ex) {
                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "OverAll Test Result"), count, "FAILED");
                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Reason"), count, ex.getMessage());
                Assert.fail(ex.getMessage());

            }

        }

    }

    public void CaseInformationValidation() {



    }

}








