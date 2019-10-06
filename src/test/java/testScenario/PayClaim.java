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

public class PayClaim extends BaseClass {

    String CaseNumber;
    String DataClientName;
    String DataAgentBroker;
    String DataPrimaryCause;
    String DataDateOfLossCaseInformation;
    String DataDescriptionCaseInformation;
    String DataSection2;
    String DataGlassCurrentReserve2;
    String DataGlassPaid2;
    String DataGlassRecovered2;
    String DataGlassIncurred2;
    String DataReserveToDate;
    String DataPeril;
    String DataSumInsured;
    String DataCurrency;
    String DataPaidToDate;
    String DataCurrentReserve;
    String DataThisReserveExclVAT;
    String DataVAT;
    String DataThisReserve;
    String DataPaymentVAT;
    String DataThisPayment;
    String DataCreditControlChannel;
    String DataCreditControlPaymentType;
    String DataCreditControlPayeeName;
    String DataCreditControlCurrency;
    String DataCreditControlBankAccount;
    String DataCreditControlAmount;
    String DataPaymentAmount;
    String DataClient;
    String DataPaidVAT;
    String DataVAT2;
    String DataTotalPaid;
    String DataDateOfLoss3;
    String DataSection3;
    String DataGlassCurrentReserve3;
    String DataGlassPaid3;
    String DataGlassRecovered3;
    String DataGlassIncurred3;
    String DataTransactionDesc;
    String DataTotalIncurred;
    String DataTotalOutstanding;
    String DataTotalReserve;
    String DataTotalPayment;
    String DataTotalRecovery;
    String DataTotalReceipt;
    String DataPayee2;
    String DataPayeeType;
    String DataPaymentType;
    String DataMediaType1;
    String DataReference;
    String DataStatus;
    String DataCasePaymentAmount;
    String DataStatus1;



    private int count = 0;
    static Logger log = Logger.getLogger(testScenario.PurpleHeronUnderwritingTest.class);

    @BeforeMethod
    public void
    init() throws Exception {

        reportColumns = new String[]

                {"Original Policy Number", "Client Name", "Agent/Broker", "Primary Cause", "Test Results", "OverAll Test Result", "Reason", "Date Of Loss Case Information", "Description Case Information", "Section2",
                "Glass Current Reserve2", "Glass Paid2", "Glass Recovered2", "Glass Incurred2", "Reserve To Date", "Peril", "Sum Insured", "Currency", "Paid To Date", "Current Reserve", "This Reserve Excl.VAT", "VAT",
                        "This Reserve", "Payment VAT", "This Payment", "Credit Control Channel", "Credit Control Payment Type", "Credit Control Bank Account", "Credit Control Payee Name", "Credit Control Currency", "Credit Control Amount",
                        "Payment Amount", "Client", "Paid VAT", "VAT2", "Total Paid", "Date Of Loss3", "Section3", "Glass Current Reserve3", "Glass Paid3", "Glass Recovered3", "Glass Incurred3", "Transaction Description",
                        "Total Incurred", "Total Outstanding","Total Reserve", "Total Payment", "Total Recovery", "Total Receipt","Payee", "Payee Type", "Payment Type", "Media Type", "Reference", "Status", "Case Payment Amount",
                        "Status1", "", "", "", ""};

        setUp();
    }

    @Test
    public void PayClaim() throws Exception {

        log.info("Start @Test method");
        String[][] data = null;
        byte[] screenshot;

        commonSeleniumTester = new CommonSeleniumTester();
        LoginPopup logIn = new LoginPopup();

        prop.getProperty(dataSheetName= "Claims");
        prop.getProperty(sheetName="PayClaim");


        
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
                DataDateOfLossCaseInformation = CommonTestTools.getCellValue(headers, data[i], "Date Of Loss Case Information").trim();
                DataDescriptionCaseInformation = CommonTestTools.getCellValue(headers, data[i], "Description Case Information").trim();
                DataSection2 = CommonTestTools.getCellValue(headers, data[i], "Section2").trim();
                DataGlassCurrentReserve2 = CommonTestTools.getCellValue(headers, data[i], "Glass Current Reserve2").trim();
                DataGlassPaid2 = CommonTestTools.getCellValue(headers, data[i], "Glass Paid2").trim();
                DataGlassRecovered2 = CommonTestTools.getCellValue(headers, data[i], "Glass Recovered2").trim();
                DataGlassIncurred2 = CommonTestTools.getCellValue(headers, data[i], "Glass Incurred2").trim();
                DataReserveToDate = CommonTestTools.getCellValue(headers, data[i], "Reserve To Date").trim();

                DataPeril = CommonTestTools.getCellValue(headers, data[i], "Peril").trim();
                DataSumInsured = CommonTestTools.getCellValue(headers, data[i], "Sum Insured").trim();
                DataCurrency = CommonTestTools.getCellValue(headers, data[i], "Currency").trim();
                DataPaidToDate = CommonTestTools.getCellValue(headers, data[i], "Paid To Date").trim();
                DataCurrentReserve = CommonTestTools.getCellValue(headers, data[i], "Current Reserve").trim();
                DataThisReserveExclVAT = CommonTestTools.getCellValue(headers, data[i], "This Reserve Excl.VAT").trim();
                DataVAT = CommonTestTools.getCellValue(headers, data[i], "VAT").trim();
                DataThisReserve = CommonTestTools.getCellValue(headers, data[i], "This Reserve").trim();
                DataPaymentVAT = CommonTestTools.getCellValue(headers, data[i], "Payment VAT").trim();
                DataThisPayment = CommonTestTools.getCellValue(headers, data[i], "This Payment").trim();

                DataCreditControlChannel = CommonTestTools.getCellValue(headers, data[i], "Credit Control Channel").trim();
                DataCreditControlPaymentType = CommonTestTools.getCellValue(headers, data[i], "Credit Control Payment Type").trim();
                DataCreditControlPayeeName = CommonTestTools.getCellValue(headers, data[i], "Credit Control Payee Name").trim();
                DataCreditControlCurrency = CommonTestTools.getCellValue(headers, data[i], "Credit Control Currency").trim();
                DataCreditControlBankAccount = CommonTestTools.getCellValue(headers, data[i], "Credit Control Bank Account").trim();
                DataCreditControlAmount = CommonTestTools.getCellValue(headers, data[i], "Credit Control Amount").trim();

                DataPaymentAmount = CommonTestTools.getCellValue(headers, data[i], "Payment Amount").trim();
                DataClient = CommonTestTools.getCellValue(headers, data[i], "Client").trim();
                DataPaidVAT = CommonTestTools.getCellValue(headers, data[i], "Paid VAT").trim();
                DataVAT2 = CommonTestTools.getCellValue(headers, data[i], "VAT2").trim();
                DataTotalPaid = CommonTestTools.getCellValue(headers, data[i], "Total Paid").trim();
                DataDateOfLoss3 = CommonTestTools.getCellValue(headers, data[i], "Date Of Loss3").trim();

                DataSection3 = CommonTestTools.getCellValue(headers, data[i], "Section3").trim();
                DataGlassCurrentReserve3 = CommonTestTools.getCellValue(headers, data[i], "Glass Current Reserve3").trim();
                DataGlassPaid3= CommonTestTools.getCellValue(headers, data[i], "Glass Paid3").trim();
                DataGlassRecovered3 = CommonTestTools.getCellValue(headers, data[i], "Glass Recovered3").trim();
                DataGlassIncurred3 = CommonTestTools.getCellValue(headers, data[i], "Glass Incurred3").trim();

                DataTransactionDesc = CommonTestTools.getCellValue(headers, data[i], "Transaction Description").trim();
                DataTotalIncurred = CommonTestTools.getCellValue(headers, data[i], "Total Incurred").trim();
                DataTotalOutstanding = CommonTestTools.getCellValue(headers, data[i], "Total Outstanding").trim();
                DataTotalReserve = CommonTestTools.getCellValue(headers, data[i], "Total Reserve").trim();
                DataTotalPayment = CommonTestTools.getCellValue(headers, data[i], "Total Payment").trim();
                DataTotalRecovery = CommonTestTools.getCellValue(headers, data[i], "Total Recovery").trim();
                DataTotalReceipt = CommonTestTools.getCellValue(headers, data[i], "Total Receipt").trim();

                DataPayee2 = CommonTestTools.getCellValue(headers, data[i], "Payee").trim();
                DataPayeeType = CommonTestTools.getCellValue(headers, data[i], "Payee Type").trim();
                DataPaymentType = CommonTestTools.getCellValue(headers, data[i], "Payment Type").trim();
                DataMediaType1 = CommonTestTools.getCellValue(headers, data[i], "Media Type").trim();
                DataReference = CommonTestTools.getCellValue(headers, data[i], "Reference").trim();
                DataStatus = CommonTestTools.getCellValue(headers, data[i], "Status").trim();
                DataCasePaymentAmount = CommonTestTools.getCellValue(headers, data[i], "Case Payment Amount").trim();
                DataStatus1 = CommonTestTools.getCellValue(headers, data[i], "Status1").trim();

                log.info("Reading data sheet");
                testCaseName = CommonTestTools.getCellValue(headers, data[i], "Test Case Name").trim();
                policyNumber = CommonTestTools.getCellValue(headers, data[i], "Policy Number").trim();
                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Original Policy Number"), count, policyNumber);


                WebDriverWait wait = new WebDriverWait(commonSeleniumTester.getDriver(), 75);
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("PolicyNo"))).sendKeys(policyNumber);
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("Search")));

                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Search")).click();
                //Thread.sleep(10000);


                log.info("Reading data sheet");
                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 1.Loading_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);

                // click Super Inquiry Search Results - Policy
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='" + policyNumber + "']/td[1]")));
                commonSeleniumTester.getDriver().findElement(By.xpath("//*[@id='" + policyNumber + "']/td[1]")).click();
                //Thread.sleep(5000);

                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 2.Policy information_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);


                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("ClaimTab")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("ClaimTab")).click();
                Thread.sleep(5000);

                CaseInformationValidation();

                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 3.Claim Tab_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);

                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Claim1")).click();
                Thread.sleep(5000);
                CaseDetailsValidations();
                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 4.Case Information_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);

                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("PaymentsTab")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("PaymentsTab")).click();
                Thread.sleep(5000);
                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 5.Payments Tab_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);

                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("CasePaymentType1")).click();
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("CasePaymentType2")).click();
                Thread.sleep(5000);

                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("CasePaymentTypeButton")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("CasePaymentTypeButton")).click();

                Thread.sleep(2000);
                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 6.Case Information 2_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);

                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("ExpandGlass")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("ExpandGlass")).click();
                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 7.Claimed Sections 2_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("ExpandGlass2")).click();
                Thread.sleep(2000);


                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("ClaimPaymentAmount")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("ClaimPaymentAmount")).sendKeys("10000");
                Thread.sleep(1000);
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("ThisReserve")).click();
                ClaimPaymentValidation();
                Thread.sleep(5000);
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("SavePayment")).click();

                Thread.sleep(3000);
                CreditControlDetailsValidation();
                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 8.Claim Payment_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);

                Thread.sleep(2000);
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("PayButton")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("PayButton")).click();
                PaymentScreenValidation();
                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 9.Payment_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);

                Thread.sleep(4000);
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("PaymentReference")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("PaymentReference")).sendKeys("Test EFT Client Payment");
                Thread.sleep(5000);
                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 10.Payment Details_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);

                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("RequestPaymentButton")).click();
                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 11.Case Information_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);


                JavascriptExecutor jse = (JavascriptExecutor)commonSeleniumTester.getDriver();
                CaseNumber = (String) jse.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("CaseNumber")));

                commonSeleniumTester.getDriver().navigate().refresh();
                Thread.sleep(8000);

                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("SearchFor")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("SearchFor")).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("SearchFor1")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("SearchFor1")).click();
                //Thread.sleep(5000);
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("CaseNum")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("CaseNum")).sendKeys(CaseNumber);
                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 12.Super Inquiry Search for Case_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);

                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Search")).click();
                Thread.sleep(2000);
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'"+ CaseNumber +"')]")));
                commonSeleniumTester.getDriver().findElement(By.xpath("//td[contains(text(),'"+ CaseNumber +"')]")).click();
                Thread.sleep(2000);

                CaseDetailsValidations2();

                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("CaseVersionsTab")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("CaseVersionsTab")).click();

                CaseVersionValidations();

                Thread.sleep(2000);
                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 13.Case Versions Tab_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);

                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("PaymentsTab")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("PaymentsTab")).click();
                Thread.sleep(2000);

                PaymentRequestValidations();

                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 14.Payments Tab_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);

                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("CasePaymentGrid")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("CasePaymentGrid")).click();

                ClaimBordereauValidation();

                Thread.sleep(2000);
                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 15.Payments Tab 2_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);


                commonSeleniumTester.getDriver().quit();


            } catch (Exception ex) {
                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "OverAll Test Result"), count, "FAILED");
                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Reason"), count, ex.getMessage());
                Assert.fail(ex.getMessage());

            }

        }

    }

    public void CaseInformationValidation() {

        try {

            String DateOfLossCaseInformation = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("DateOfLossCaseInformation")).getText();

            if (DateOfLossCaseInformation.equalsIgnoreCase(DataDateOfLossCaseInformation)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Date Of Loss Case Information"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Date Of Loss Case Information"), count, "FAILED");

            }


            String DescriptionCaseInformation = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("DescriptionCaseInformation")).getText();

            if (DescriptionCaseInformation.equalsIgnoreCase(DataDescriptionCaseInformation)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Description Case Information"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Description Case Information"), count, "FAILED");

            }

        }catch (Exception ex){
            commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "OverAll Test Result"), count, "FAILED");
            commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Reason"), count, ex.getMessage());
            Assert.fail(ex.getMessage());
        }


    }

    public void CaseDetailsValidations(){



        try {


            String Section = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Section3")).getText();

            if (Section.equalsIgnoreCase(DataSection2)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Section2"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Section2"), count, "FAILED");
            }

            String GlassCurrentReserve = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("GlassCurrentReserve2")).getText();

            if (GlassCurrentReserve.contains(DataGlassCurrentReserve2)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Glass Current Reserve2"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Glass Current Reserve2"), count, "FAILED");
            }

            String GlassPaid = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("GlassPaid2")).getText();

            if (GlassPaid.contains(DataGlassPaid2)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Glass Paid2"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Glass Paid2"), count, "FAILED");
            }

            String GlassRecovered= commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("GlassRecovered2")).getText();

            if (GlassRecovered.contains(DataGlassRecovered2)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Glass Recovered2"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Glass Recovered2"), count, "FAILED");
            }

            String GlassIncurred = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("GlassIncurred2")).getText();

            if (GlassIncurred.contains(DataGlassIncurred2)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Glass Incurred2"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Glass Incurred2"), count, "FAILED");
            }




        }catch (Exception ex){
            commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "OverAll Test Result"), count, "FAILED");
            commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Reason"), count, ex.getMessage());
            Assert.fail(ex.getMessage());
        }

    }

    public void ClaimPaymentValidation() {

        try {


            String Peril = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Peril")).getText();

            if (Peril.equalsIgnoreCase(DataPeril)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Peril"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Peril"), count, "FAILED");

            }

            String SumInsured = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("SumInsured")).getText();

            if (SumInsured.equalsIgnoreCase(DataSumInsured)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Sum Insured"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Sum Insured"), count, "FAILED");

            }

            String Currency1 = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Currency1")).getText();

            if (Currency1.equalsIgnoreCase(DataCurrency)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Currency"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Currency"), count, "FAILED");

            }

            String ReserveToDate = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("ReserveToDate")).getText();

            if (ReserveToDate.equalsIgnoreCase(DataReserveToDate)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Reserve To Date"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Reserve To Date"), count, "FAILED");

            }


            String PaidToDate = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("PaidToDate")).getText();

            if (PaidToDate.equalsIgnoreCase(DataPaidToDate)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Paid To Date"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Paid To Date"), count, "FAILED");

            }

            String CurrentReserve = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("CurrentReserve")).getText();

            if (CurrentReserve.equalsIgnoreCase(DataCurrentReserve)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Current Reserve"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Current Reserve"), count, "FAILED");

            }

            String ThisReserveExclVAT = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("ThisReserveExclVAT")).getText();

            if (ThisReserveExclVAT.equalsIgnoreCase(DataThisReserveExclVAT)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "This Reserve Excl.VAT"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "This Reserve Excl.VAT"), count, "FAILED");

            }

            String VAT = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("VAT")).getText();

            if (VAT.equalsIgnoreCase(DataVAT)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "VAT"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "VAT"), count, "FAILED");

            }

            String ThisReserve = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("ThisReserve")).getText();

            if (ThisReserve.equalsIgnoreCase(DataThisReserve)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "This Reserve"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "This Reserve"), count, "FAILED");

            }

            String PaymentVAT = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("PaymentVAT")).getText();

            if (PaymentVAT.equalsIgnoreCase(DataPaymentVAT)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Payment VAT"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Payment VAT"), count, "FAILED");

            }


            JavascriptExecutor jse = (JavascriptExecutor)commonSeleniumTester.getDriver();

            String ThisPayment = (String) jse.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("ThisPayment")));


            if(ThisPayment.contains(DataThisPayment)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "This Payment"), count, "PASSED");
            }else{

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "This Payment"), count, "FAILED");
            }

        }catch (Exception ex){
            commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "OverAll Test Result"), count, "FAILED");
            commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Reason"), count, ex.getMessage());
            Assert.fail(ex.getMessage());
        }


    }

    public void CreditControlDetailsValidation() {

        try {


            String CreditControlChannel = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("CreditControlChannel")).getText();

            if (CreditControlChannel.equalsIgnoreCase(DataCreditControlChannel)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Credit Control Channel"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Credit Control Channel"), count, "FAILED");

            }

            String CreditControlPaymentType = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("CreditControlPaymentType")).getText();

            if (CreditControlPaymentType.equalsIgnoreCase(DataCreditControlPaymentType)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Credit Control Payment Type"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Credit Control Payment Type"), count, "FAILED");

            }

            JavascriptExecutor jse = (JavascriptExecutor)commonSeleniumTester.getDriver();

            String CreditControlPayeeName = (String) jse.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("CreditControlPayeeName")));


            if(CreditControlPayeeName.equalsIgnoreCase(DataCreditControlPayeeName)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Credit Control Payee Name"), count, "PASSED");
            }else{

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Credit Control Payee Name"), count, "FAILED");
            }


            String CreditControlCurrency = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("CreditControlCurrency")).getText();

            if (CreditControlCurrency.equalsIgnoreCase(DataCreditControlCurrency)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Credit Control Currency"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Credit Control Currency"), count, "FAILED");

            }

            String CreditControlBankAccount = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("CreditControlBankAccount")).getText();

            if (CreditControlBankAccount.equalsIgnoreCase(DataCreditControlBankAccount)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Credit Control Bank Account"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Credit Control Bank Account"), count, "FAILED");

            }

            String CreditControlAmount = (String) jse.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("CreditControlAmount")));


            if(CreditControlAmount.equalsIgnoreCase(DataCreditControlAmount)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Credit Control Amount"), count, "PASSED");
            }else{

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Credit Control Amount"), count, "FAILED");
            }



        }catch (Exception ex){
            commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "OverAll Test Result"), count, "FAILED");
            commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Reason"), count, ex.getMessage());
            Assert.fail(ex.getMessage());
        }



    }

    public void PaymentScreenValidation(){


        JavascriptExecutor jse = (JavascriptExecutor)commonSeleniumTester.getDriver();

        String PaymentAmount = (String) jse.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("PaymentAmount")));

        if (PaymentAmount.equalsIgnoreCase(DataPaymentAmount)){

            commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Payment Amount"), count, "PASSED");
        }else {

            commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Payment Amount"), count, "FAILED");

        }


        String Client = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Client")).getText();

        if (Client.equalsIgnoreCase(DataClient)){

            commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Client"), count, "PASSED");
        }else {

            commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Client"), count, "FAILED");

        }


        String PaidVAT = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("PaidVAT")).getText();

        if (PaidVAT.equalsIgnoreCase(DataPaidVAT)){

            commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Paid VAT"), count, "PASSED");
        }else {

            commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Paid VAT"), count, "FAILED");

        }

        String VAT2 = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("VAT2")).getText();

        if (VAT2.equalsIgnoreCase(DataVAT2)){

            commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "VAT2"), count, "PASSED");
        }else {

            commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "VAT2"), count, "FAILED");

        }

        String TotalPaid = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("TotalPaid")).getText();

        if (TotalPaid.equalsIgnoreCase(DataTotalPaid)){

            commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Total Paid"), count, "PASSED");
        }else {

            commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Total Paid"), count, "FAILED");

        }


        String DateOfLoss3 = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("DateOfLoss3")).getText();

        if (DateOfLoss3.equalsIgnoreCase(DataDateOfLoss3)){

            commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Date Of Loss3"), count, "PASSED");
        }else {

            commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Date Of Loss3"), count, "FAILED");

        }



    }

    public void CaseDetailsValidations2(){



        try {


            String Section = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Section3")).getText();

            if (Section.equalsIgnoreCase(DataSection3)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Section3"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Section3"), count, "FAILED");
            }

            String GlassCurrentReserve = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("GlassCurrentReserve2")).getText();

            if (GlassCurrentReserve.contains(DataGlassCurrentReserve3)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Glass Current Reserve3"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Glass Current Reserve3"), count, "FAILED");
            }

            String GlassPaid = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("GlassPaid2")).getText();

            if (GlassPaid.contains(DataGlassPaid3)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Glass Paid3"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Glass Paid3"), count, "FAILED");
            }

            String GlassRecovered= commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("GlassRecovered2")).getText();

            if (GlassRecovered.contains(DataGlassRecovered3)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Glass Recovered3"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Glass Recovered3"), count, "FAILED");
            }

            String GlassIncurred = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("GlassIncurred2")).getText();

            if (GlassIncurred.contains(DataGlassIncurred3)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Glass Incurred3"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Glass Incurred3"), count, "FAILED");
            }




        }catch (Exception ex){
            commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "OverAll Test Result"), count, "FAILED");
            commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Reason"), count, ex.getMessage());
            Assert.fail(ex.getMessage());
        }

    }

    public void CaseVersionValidations(){



        try {


//           String TransactionDesc = commonSeleniumTester.getDriver().findElement(By.xpath("//td[contains(text(),'Requested Payment "+CaseNumber+"')]")).getText();
//
//            if (TransactionDesc.contains(DataTransactionDesc)){
//
//                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Transaction Description"), count, "PASSED");
//            }else {
//
//                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Transaction Description"), count, "FAILED");
//            }

            String TotalIncurred = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("TotalIncurred")).getText();

            if (TotalIncurred.contains(DataTotalIncurred)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Total Incurred"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Total Incurred"), count, "FAILED");
            }

            String TotalOutstanding = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("TotalOutstanding")).getText();

            if (TotalOutstanding.contains(DataTotalOutstanding)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Total Outstanding"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Total Outstanding"), count, "FAILED");
            }

            String TotalReserve = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("TotalReserve")).getText();

            if (TotalReserve.contains(DataTotalReserve)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Total Reserve"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Total Reserve"), count, "FAILED");
            }

            String TotalPayment = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("TotalPayment")).getText();

            if (TotalPayment.contains(DataTotalPayment)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Total Payment"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Total Payment"), count, "FAILED");
            }

            String TotalRecovery = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("TotalRecovery")).getText();

            if (TotalRecovery.contains(DataTotalRecovery)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Total Recovery"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Total Recovery"), count, "FAILED");
            }

            String TotalReceipt = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("TotalReceipt")).getText();

            if (TotalReceipt.contains(DataTotalReceipt)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Total Receipt"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Total Receipt"), count, "FAILED");
            }




        }catch (Exception ex){
            commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "OverAll Test Result"), count, "FAILED");
            commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Reason"), count, ex.getMessage());
            Assert.fail(ex.getMessage());
        }

    }

    public void PaymentRequestValidations(){



        try {


            String Payee2 = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Payee2")).getText();

            if (Payee2.equalsIgnoreCase(DataPayee2)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Payee"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Payee"), count, "FAILED");
            }

            String PayeeType = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("PayeeType")).getText();

            if (PayeeType.equalsIgnoreCase(DataPayeeType)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Payee Type"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Payee Type"), count, "FAILED");
            }


            String PaymentType = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("PaymentType")).getText();

            if (PaymentType.equalsIgnoreCase(DataPaymentType)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Payment Type"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Payment Type"), count, "FAILED");
            }

            String MediaType1 = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("MediaType1")).getText();

            if (MediaType1.equalsIgnoreCase(DataMediaType1)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Media Type"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Media Type"), count, "FAILED");
            }

            String Reference = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Reference")).getText();

            if (Reference.equalsIgnoreCase(DataReference)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Reference"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Reference"), count, "FAILED");
            }

            String Status = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Status")).getText();

            if (Status.equalsIgnoreCase(DataStatus)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Status"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Status"), count, "FAILED");
            }

            String CasePaymentAmount = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("CasePaymentAmount")).getText();

            if (CasePaymentAmount.equalsIgnoreCase(DataCasePaymentAmount)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Case Payment Amount"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Case Payment Amount"), count, "FAILED");
            }







        }catch (Exception ex){
            commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "OverAll Test Result"), count, "FAILED");
            commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Reason"), count, ex.getMessage());
            Assert.fail(ex.getMessage());
        }

    }

    public void ClaimBordereauValidation(){



        try {

            JavascriptExecutor jse = (JavascriptExecutor)commonSeleniumTester.getDriver();

            //String Status1 = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Status1")).getText();
            String Status1 = (String) jse.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Status1")));

            if (Status1.contains(DataStatus1)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Status1"), count, "PASSED");

            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Status1"), count, "FAILED");
            }



        }catch (Exception ex){
            commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "OverAll Test Result"), count, "FAILED");
            commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Reason"), count, ex.getMessage());
            Assert.fail(ex.getMessage());
        }

    }

}








