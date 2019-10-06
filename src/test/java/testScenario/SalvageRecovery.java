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
import java.sql.DriverManager;
import java.util.Arrays;
import java.util.List;

public class SalvageRecovery  extends BaseClass {


    private int count = 0;
    private int rowIteration = 0;
    static Logger log = Logger.getLogger(testScenario.PurpleHeronUnderwritingTest.class);

    @BeforeMethod
    public void
    init() throws Exception {

        reportColumns = new String[]

                {"Original Policy Number", "Test Results", "OverAll Test Result", "Reason"};

        setUp();
    }

    @Test
    public void SalvageRecovery() throws Exception {

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

                log.info("Reading data sheet");
                testCaseName = CommonTestTools.getCellValue(headers, data[i], "Test Case Name").trim();
                policyNumber = CommonTestTools.getCellValue(headers, data[i], "Policy Number").trim();
                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Original Policy Number"), count, policyNumber);

                WebDriverWait wait = new WebDriverWait(commonSeleniumTester.getDriver(), 75);
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("SearchFor")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("SearchFor")).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("SearchFor2")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("SearchFor2")).click();
                Thread.sleep(3000);

                String BankAmount = CommonTestTools.getCellValue(headers, data[i], "Bank Amount").trim();
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("BankAmount")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("BankAmount")).sendKeys(BankAmount);
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("Search1")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Search1")).click();

                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + "1.Super Inquiry_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);

                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("Narrative")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Narrative")).click();

                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + "2.Distribution_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);

                Thread.sleep(5000);
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("DistributeButton")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("DistributeButton")).click();

                String Comment = CommonTestTools.getCellValue(headers, data[i], "Comment").trim();
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("Comment")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Comment")).sendKeys(Comment);

                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + "3.Distribution Amount_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);

                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("SaveDistribution")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("SaveDistribution")).click();

                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + "3.Credit Control - Details before_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);

                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("Channel")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Channel")).click();

                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("Channel1")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Channel1")).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("RecoveryType")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("RecoveryType")).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("RecoveryType1")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("RecoveryType1")).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("ReceivedFromIcon")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("ReceivedFromIcon")).click();

                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + "4.Receive From/Account before_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);

                Thread.sleep(3000);
                String PartyCode = CommonTestTools.getCellValue(headers, data[i], "Party Code").trim();
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("PartyCode")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("PartyCode")).sendKeys(PartyCode);
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("PartyType")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("PartyType")).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("PartyType1")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("PartyType1")).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("PartySearch")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("PartySearch")).click();

                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + "5.Receive From/Account after_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);

                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("PartyResults")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("PartyResults")).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("ConfirmButton")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("ConfirmButton")).click();
                Thread.sleep(2000);

                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + "6.Credit Control Bordereau_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);

                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("NextButton")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("NextButton")).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("PolicyNo1")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("PolicyNo1")).sendKeys(policyNumber);
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("FindButton")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("FindButton")).click();
                Thread.sleep(7000);

                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + "7.Credit Control Allocate_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);

                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("SearchResultsGrid")).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("ClaimRecoveriesGrid")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("ClaimRecoveriesGrid")).click();

                JavascriptExecutor jse = (JavascriptExecutor)commonSeleniumTester.getDriver();
                String CaseNumber1 = (String) jse.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("CaseNumber1")));

                String ClaimRecoveriesGrid = CommonTestTools.getCellValue(headers, data[i], "Claim Recoveries Grid").trim();
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("ClaimRecoveriesGrid")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("ClaimRecoveriesGrid")).sendKeys(ClaimRecoveriesGrid);

                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + "8.Claim Reserves_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);

                String SReserve = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("RecoverySaveButton")).getText();
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("RecoverySaveButton")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("RecoverySaveButton")).click();
                Thread.sleep(3000);
                SReserve = "";

                SReserve = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("RecoverySaveButton")).getText();
                if(SReserve.contains("Save")){

                    Thread.sleep(3000);
                    wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("RecoverySaveButton")));
                    commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("RecoverySaveButton")).click();

                }


                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + "9.Credit Control Allocate 2 _Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);

                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("BottomDoneButton")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("BottomDoneButton")).click();
                Thread.sleep(2000);

                commonSeleniumTester.getDriver().navigate().refresh();
                Thread.sleep(7000);
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("SearchFor")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("SearchFor")).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("SearchFor1")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("SearchFor1")).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("CaseNum")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("CaseNum")).sendKeys(CaseNumber1);

                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("Search")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Search")).click();

                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 10.Super Inquiry Case Seach_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);

                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'"+ CaseNumber1 +"')]")));
                commonSeleniumTester.getDriver().findElement(By.xpath("//td[contains(text(),'"+ CaseNumber1 +"')]")).click();
//                log.info("Reading data sheet");

                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("Lock")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Lock")).click();

                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("SalvageApplicable1")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("SalvageApplicable1")).click();
                Thread.sleep(3000);
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("Lock")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Lock")).click();

                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 11.Case Information_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);

                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("CaseVersionsTab")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("CaseVersionsTab")).click();

                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 12.Case Version_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);

                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("ClaimPaymentReserveUpdate")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("ClaimPaymentReserveUpdate")).click();

                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 13.Claim Version Details_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);

                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("ClaimVersionDetailsHeader")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("ClaimVersionDetailsHeader")).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("SalvageReceipt")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("SalvageReceipt")).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("SalvageAllocation")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("SalvageAllocation")).click();

                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 14.Claim Version Details 2_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);

                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("ClaimVersionDetailsHeader2")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("ClaimVersionDetailsHeader2")).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("ClaimVersionDetailsHeader")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("ClaimVersionDetailsHeader")).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("ReceiptsTab")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("ReceiptsTab")).click();

                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 15.Receipts Tab_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);

                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("SalvageManagementDisposals")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("SalvageManagementDisposals")).click();
                Thread.sleep(2000);
                JavascriptExecutor js = (JavascriptExecutor)commonSeleniumTester.getDriver();
                String ProcessedFrm = (String) js.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("ProcessedFrm")));
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("Menu")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Menu")).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("MenuCreditControl")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("MenuCreditControl")).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("ReceiptChannel")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("ReceiptChannel")).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("ReceiptChannel1")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("ReceiptChannel1")).click();
                Thread.sleep(2000);
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("ProcessedFrm1")).sendKeys(ProcessedFrm);
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("SearchButton")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("SearchButton")).click();
                Thread.sleep(2000);

                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 16.Credit Control screen _Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);

                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("CreditControlSearchResults")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("CreditControlSearchResults")).click();

                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 17.Bordereau Screen _Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);


                Thread.sleep(2000);
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("NextButton")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("NextButton")).click();

                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 18.Bordereau Screen _Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);


                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("AllocationsResultsGrid")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("AllocationsResultsGrid")).click();

                //commonSeleniumTester.getDriver().findElement(By.xpath("//td[contains(text(),'" + policyNumber + "')]")).click();
                Thread.sleep(2000);
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








