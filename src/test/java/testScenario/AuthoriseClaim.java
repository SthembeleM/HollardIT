package testScenario;

import commonClasses.CommonSeleniumTester;
import commonClasses.CommonTestTools;
import commonClasses.LoginPopup;
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
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import javax.swing.*;
import javax.xml.crypto.Data;
import java.io.File;
import java.util.Arrays;
import java.util.List;

public class AuthoriseClaim  extends BaseClass {

    String DataClientName;
    String DataAgentBroker;
    String DataPrimaryCause;

    private int count = 0;
    private int rowIteration = 0;
    static Logger log = Logger.getLogger(testScenario.PurpleHeronUnderwritingTest.class);

    @BeforeMethod
    public void
    init() throws Exception {

        reportColumns = new String[]

                {"Original Policy Number", "Client Name", "Agent/Broker", "Primary Cause", "Test Results", "OverAll Test Result", "Reason"};

        setUp();
    }

    @Test
    public void AuthoriseClaim() throws Exception {

        log.info("Start @Test method");
        String[][] data = null;
        byte[] screenshot;

        commonSeleniumTester = new CommonSeleniumTester();
        LoginPopup logIn = new LoginPopup();



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

                DataClientName = CommonTestTools.getCellValue(headers, data[i], "Client Name").trim();
                DataAgentBroker = CommonTestTools.getCellValue(headers, data[i], "Agent/Broker").trim();
                DataPrimaryCause = CommonTestTools.getCellValue(headers, data[i], "Primary Cause").trim();

                log.info("Reading data sheet");
                testCaseName = CommonTestTools.getCellValue(headers, data[i], "Test Case Name").trim();
                policyNumber = CommonTestTools.getCellValue(headers, data[i], "Policy Number").trim();
                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Original Policy Number"), count, policyNumber);


                WebDriverWait wait = new WebDriverWait(commonSeleniumTester.getDriver(), 75);
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("PolicyNo"))).sendKeys(policyNumber);
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("Search")));

                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Search")).click();
                Thread.sleep(10000);


                log.info("Reading data sheet");
                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 1.Loading_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);

                // click Super Inquiry Search Results - Policy
                commonSeleniumTester.getDriver().findElement(By.xpath("//*[@id='" + policyNumber + "']/td[1]")).click();
                Thread.sleep(5000);

                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 2.Policy information_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);

                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("ClaimTab")).click();
                Thread.sleep(2000);
                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 3.Claim Tab_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);

                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Claim1")).click();
                Thread.sleep(5000);
                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 4.Case Information_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);

                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("PaymentsTab")).click();
                Thread.sleep(5000);
                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 5.Payments Tab_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);


                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Payee")).click();
                Thread.sleep(5000);
                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 6.Payee Details_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);


                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("AuthoriseButton")).click();
                Thread.sleep(5000);
                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 7.Claim Bordereau_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);

                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("PaymentsTab")).click();
                Thread.sleep(5000);
                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 8.Payments Tab_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);


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








