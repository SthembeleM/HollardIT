package testScenario;

import commonClasses.CommonSeleniumTester;
import commonClasses.CommonTestTools;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
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

public class ClaimPaymentVerification  extends BaseClass {


    private int count = 0;
    private int rowIteration = 0;
    static Logger log = Logger.getLogger(testScenario.PurpleHeronUnderwritingTest.class);

    @BeforeMethod
    public void
    init() throws Exception {

        reportColumns = new String[]

                {"Original Policy Number","Test Results", "OverAll Test Result", "Reason"};

        setUp();
    }

    @Test
    public void ClaimPaymentVerification() throws Exception {

        log.info("Start @Test method");
        String[][] data = null;
        byte[] screenshot;

        commonSeleniumTester = new CommonSeleniumTester();
//        LoginPopup logIn = new LoginPopup();


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

            count++;

            try {

                String testCaseName;

                log.info("Reading data sheet");
                testCaseName = CommonTestTools.getCellValue(headers, data[i], "Test Case Name").trim();
                policyNumber = CommonTestTools.getCellValue(headers, data[i], "Policy Number").trim();
                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Original Policy Number"), count, policyNumber);

                WebDriverWait wait = new WebDriverWait(commonSeleniumTester.getDriver(), 75);
                //Thread.sleep(10000);
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("ViewInbox"))).getText();
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("ViewInbox")).click();
                Thread.sleep(8000);
               // commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("NotificationInbox")).click();
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Read")).click();
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Read")).click();
                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 1.Notification Inbox_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);

                Thread.sleep(5000);
                commonSeleniumTester.getDriver().navigate().refresh();

                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("PolicyNo"))).sendKeys(policyNumber);
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("Search")));

                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Search")).click();
                Thread.sleep(10000);


                log.info("Reading data sheet");
                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 2.Loading_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);

                // click Super Inquiry Search Results - Policy
                commonSeleniumTester.getDriver().findElement(By.xpath("//*[@id='" + policyNumber + "']/td[1]")).click();
                Thread.sleep(5000);

                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 3.Policy information_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);

                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("ClaimTab")).click();
                Thread.sleep(2000);
                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 4.Claim Tab_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);

                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Claim1")).click();
                Thread.sleep(10000);
                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 5.Case Information_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);

                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("PaymentsTab")).click();
                Thread.sleep(5000);
                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 6.Payments Tab_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);


                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Payee")).click();
                Thread.sleep(5000);
                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 7.Payee Details_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);

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








