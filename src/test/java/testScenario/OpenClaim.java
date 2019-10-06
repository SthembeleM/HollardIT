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

public class OpenClaim extends BaseClass {



    String DataClientName;
    String DataAgentBroker;
    String DataPrimaryCause;
    String DataClaimHandler;
    String DataSecondaryCause;
    String DataCatastrophe;
    String DataPremiumConfirmedBy;
    String DataDateOfLoss;
    String DataClaimStatus;
    String DataSection;
    String DataGlassCurrentReserve;
    String DataGlassPaid;
    String DataGlassRecovered;
    String DataGlassIncurred;
    String DataLossToDate;
    String DataLocation;
    String DataBrokerClaimNumber;
    String DataClaimDescription;
    String DataProgressStatus;
    String DataLastUpdated;
    String DataClaimCloseStatus;
    String DataStatusDescription;
    String DataThirdPartyLiability;
    String DataThirdPartyRecovery;
    String DataMinorClaimant;
    String DataExGratia;
    String DataIsBordereauClaim;
    String DataIsContentiousClaim;
    String DataSalvageApplicable;
    String DataClaimRejected;
    String DataOmbudsmanApproached;
    String DataCompensation;
    String DataAssessmentRequired;
    String DataReopenCaseButton;
    String DataSection2;
    String DataGlassCurrentReserve2;
    String DataGlassPaid2;
    String DataGlassRecovered2;
    String DataGlassIncurred2;
    String DataVersion;
    String DataTransactionDescription;
    String DataUser;
    String DataDate1;
    String DataTotalIncurred;
    String DataTotalOutstanding;
    String DataTotalReserve;
    String DataTotalPayment;
    String DataTotalRecovery;
    String DataTotalReceipt;
    String DataPeril1;
    String DataPeril2;
    String DataExGratiaReserve;
    String DataExGratiaPayment;
    String DataExGratiaRecovery;
    String DataExGratiaReceipt;
    String DataGlassReserve;
    String DataGlassPayment;
    String DataGlassRecovery;
    String DataGlassReceipt;

    private int count = 0;
    private int rowIteration = 0;
    static Logger log = Logger.getLogger(testScenario.PurpleHeronUnderwritingTest.class);

    @BeforeClass
    public void
    init() throws Exception {

        reportColumns = new String[]

                {"Original Policy Number", "Client Name", "ClientName2", "Agent/Broker", "Primary Cause", "Secondary Cause", "Claim Handler", "Catastrophe", "Premium Confirmed By", "Date of Loss", "Date Of Loss2", "Loss To Date", "Broker Claim Number", "Location", "Claim Status",
                        "Section","Section2", "Glass Current Reserve", "Glass Current Reserve2", "Glass Paid", "Glass Paid2", "Claim Description", "Third Party Liability", "Third Party Recovery", "Minor Claimant", "Ex Gratia", "Is Bordereau Claim", "Is Contentious Claim", "Salvage Applicable", "Claim Rejected",
                        "Ombudsman Approached", "Compensation", "Assessment Required", "Re-open Case Button", "Glass Recovered", "Glass Recovered2", "Glass Incurred", "Glass Incurred2", "Progress Status","Last Updated", "Claim Close Status", "Status Description", "Version", "Transaction Description"
                        , "Date1", "User", "Total Incurred", "Total Outstanding","Total Reserve", "Total Payment", "Total Recovery", "Total Receipt", "Policy Number2", "Case Number2", "Peril1", "Peril2", "ExGratia Reserve", "ExGratia Payment","ExGratia Recovery", "ExGratia Receipt", "Glass Reserve", "Glass Payment",
                        "Glass Receipt","Glass Recovery","Test Results", "OverAll Test Result", "Reason"};

        setUp();
    }


    @Test
    public void OpenClaim() throws Exception {

        log.info("Start @Test method");
        String[][] data = null;
        byte[] screenshot;


        commonSeleniumTester = new CommonSeleniumTester();
        prop.getProperty(dataSheetName= "Claims");
        prop.getProperty(sheetName="OpenClaim");

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
                DataSecondaryCause = CommonTestTools.getCellValue(headers, data[i], "Secondary Cause").trim();
                DataClaimHandler = CommonTestTools.getCellValue(headers, data[i], "Claim Handler").trim();
                DataCatastrophe = CommonTestTools.getCellValue(headers, data[i], "Catastrophe").trim();
                DataPremiumConfirmedBy = CommonTestTools.getCellValue(headers, data[i], "Premium Confirmed By").trim();
                DataDateOfLoss = CommonTestTools.getCellValue(headers, data[i], "Date of Loss").trim();
                DataLossToDate = CommonTestTools.getCellValue(headers, data[i], "Loss To Date").trim();
                DataClaimStatus = CommonTestTools.getCellValue(headers, data[i], "Claim Status").trim();
                DataLocation = CommonTestTools.getCellValue(headers, data[i], "Location").trim();
                DataBrokerClaimNumber = CommonTestTools.getCellValue(headers, data[i], "Broker Claim Number").trim();
                DataClaimDescription = CommonTestTools.getCellValue(headers, data[i], "Claim Description").trim();
                DataProgressStatus = CommonTestTools.getCellValue(headers, data[i], "Progress Status").trim();
                DataLastUpdated  = CommonTestTools.getCellValue(headers, data[i], "Last Updated").trim();
                DataClaimCloseStatus = CommonTestTools.getCellValue(headers, data[i], "Claim Close Status").trim();
                DataStatusDescription = CommonTestTools.getCellValue(headers, data[i], "Status Description").trim();
                DataThirdPartyLiability = CommonTestTools.getCellValue(headers, data[i], "Third Party Liability").trim();
                DataThirdPartyRecovery = CommonTestTools.getCellValue(headers, data[i], "Third Party Recovery").trim();
                DataMinorClaimant = CommonTestTools.getCellValue(headers, data[i], "Minor Claimant").trim();
                DataExGratia = CommonTestTools.getCellValue(headers, data[i], "Ex Gratia").trim();
                DataIsBordereauClaim = CommonTestTools.getCellValue(headers, data[i], "Is Bordereau Claim").trim();
                DataIsContentiousClaim = CommonTestTools.getCellValue(headers, data[i], "Is Contentious Claim").trim();
                DataSalvageApplicable = CommonTestTools.getCellValue(headers, data[i], "Salvage Applicable").trim();
                DataClaimRejected = CommonTestTools.getCellValue(headers, data[i], "Claim Rejected").trim();
                DataOmbudsmanApproached = CommonTestTools.getCellValue(headers, data[i], "Ombudsman Approached").trim();
                DataCompensation = CommonTestTools.getCellValue(headers, data[i], "Compensation").trim();
                DataAssessmentRequired = CommonTestTools.getCellValue(headers, data[i], "Assessment Required").trim();
                DataReopenCaseButton = CommonTestTools.getCellValue(headers, data[i], "Re-open Case Button").trim();

                DataSection = CommonTestTools.getCellValue(headers, data[i], "Section").trim();
                DataGlassCurrentReserve = CommonTestTools.getCellValue(headers, data[i], "Glass Current Reserve").trim();
                DataGlassPaid = CommonTestTools.getCellValue(headers, data[i], "Glass Paid").trim();
                DataGlassRecovered = CommonTestTools.getCellValue(headers, data[i], "Glass Recovered").trim();
                DataGlassIncurred = CommonTestTools.getCellValue(headers, data[i], "Glass Incurred").trim();

                DataSection2 = CommonTestTools.getCellValue(headers, data[i], "Section2").trim();
                DataGlassCurrentReserve2 = CommonTestTools.getCellValue(headers, data[i], "Glass Current Reserve2").trim();
                DataGlassPaid2 = CommonTestTools.getCellValue(headers, data[i], "Glass Paid2").trim();
                DataGlassRecovered2 = CommonTestTools.getCellValue(headers, data[i], "Glass Recovered2").trim();
                DataGlassIncurred2 = CommonTestTools.getCellValue(headers, data[i], "Glass Incurred2").trim();
                DataVersion = CommonTestTools.getCellValue(headers, data[i], "Version").trim();
                DataTransactionDescription = CommonTestTools.getCellValue(headers, data[i], "Transaction Description").trim();
                DataUser = CommonTestTools.getCellValue(headers, data[i], "User").trim();
                DataDate1 = CommonTestTools.getCellValue(headers, data[i], "Date1").trim();
                DataTotalIncurred = CommonTestTools.getCellValue(headers, data[i], "Total Incurred").trim();
                DataTotalOutstanding = CommonTestTools.getCellValue(headers, data[i], "Total Outstanding").trim();
                DataTotalReserve = CommonTestTools.getCellValue(headers, data[i], "Total Reserve").trim();
                DataTotalPayment = CommonTestTools.getCellValue(headers, data[i], "Total Payment").trim();
                DataTotalRecovery = CommonTestTools.getCellValue(headers, data[i], "Total Recovery").trim();
                DataTotalReceipt = CommonTestTools.getCellValue(headers, data[i], "Total Receipt").trim();
                DataPeril1 = CommonTestTools.getCellValue(headers, data[i], "Peril1").trim();
                DataPeril2 = CommonTestTools.getCellValue(headers, data[i], "Peril2").trim();
                DataExGratiaReserve = CommonTestTools.getCellValue(headers, data[i], "ExGratia Reserve").trim();
                DataExGratiaPayment = CommonTestTools.getCellValue(headers, data[i], "ExGratia Payment").trim();
                DataExGratiaRecovery = CommonTestTools.getCellValue(headers, data[i], "ExGratia Recovery").trim();
                DataExGratiaReceipt = CommonTestTools.getCellValue(headers, data[i], "ExGratia Receipt").trim();
                DataGlassReserve = CommonTestTools.getCellValue(headers, data[i], "Glass Reserve").trim();
                DataGlassPayment = CommonTestTools.getCellValue(headers, data[i], "Glass Payment").trim();
                DataGlassRecovery = CommonTestTools.getCellValue(headers, data[i], "Glass Recovery").trim();
                DataGlassReceipt = CommonTestTools.getCellValue(headers, data[i], "Glass Receipt").trim();

                log.info("Reading data sheet");
                testCaseName = CommonTestTools.getCellValue(headers, data[i], "Test Case Name").trim();
                policyNumber = CommonTestTools.getCellValue(headers, data[i], "Policy Number").trim();
                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Original Policy Number"), count, policyNumber);

                WebDriverWait wait = new WebDriverWait(commonSeleniumTester.getDriver(), 75);
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("PolicyNo"))).sendKeys(policyNumber);
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("Search")));

                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Search")).click();
                //Thread.sleep(8000);


                log.info("Reading data sheet");
                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 1.Loading_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);

                // click Super Inquiry Search Results - Policy
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='" + policyNumber + "']/td[1]")));
                commonSeleniumTester.getDriver().findElement(By.xpath("//*[@id='" + policyNumber + "']/td[1]")).click();
                //Thread.sleep(6000);

                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 2.Policy information_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);

                // click on claimTab and click on New button to create new claim
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("ClaimTab")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("ClaimTab")).click();
                //Thread.sleep(2000);
                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 3.Claims Tab_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("NewClaimButton")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("NewClaimButton")).click();

                //Enter Claim Details
                String DateOfLoss1 = CommonTestTools.getCellValue(headers, data[i], "Date of Loss").trim();
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("DateOfLoss")));
                WebElement date = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("DateOfLoss"));
                date.clear();
                //Thread.sleep(5000);
                date.sendKeys(DateOfLoss1);
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("TimeOfIncident")).click();
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("PrimaryCause1")).click();
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("PrimaryCause2")).click();
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("SecondaryCause1")).click();
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("SecondaryCause2")).click();
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("PremiumConfirmedBy")).click();
                Select dropdown = new Select(commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("PremiumConfirmedBy")));
                dropdown.selectByVisibleText("Broker Consultant");
                String ClaimDesc = CommonTestTools.getCellValue(headers, data[i], "Claim Description").trim();
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("ClaimDescription")).sendKeys(ClaimDesc);

                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 4.First Notification of Loss_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("SaveButton")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("SaveButton")).click();
                Thread.sleep(10000);

                CaseInformationValidation();
                CaseDetailsValidations();

                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 5.Case Information_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);

                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("ExpandGlass")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("ExpandGlass")).click();
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("ExpandGlass1")).click();
                //Thread.sleep(5000);
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("OwnDamageReserve")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("OwnDamageReserve")).sendKeys("10000");
                //Thread.sleep(5000);
                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 6.Claims Reserves Update_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);

                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("SaveReserve")));
                String SReserve = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("SaveReserve")).getText();
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("SaveReserve")).click();
                Thread.sleep(5000);
                SReserve = "";

                SReserve = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("SaveReserve")).getText();
                if(SReserve.contains("Save")){

                    Thread.sleep(5000);
                    commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("SaveReserve")).click();

                }

                Thread.sleep(10000);

                PolicySection();

                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 7.Case Information after adding Reserve_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);

                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("GlassCurrentReserve2")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("ClaimVersion")).click();
                Thread.sleep(2000);
                CaseVersionDescDetailsValidations();

                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("ClaimVersion2")));
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("ClaimVersion2")).click();
                Thread.sleep(5000);
                CaseVersionDetailsValidations();

                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 8.Case Version Details_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);

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

            JavascriptExecutor jse = (JavascriptExecutor)commonSeleniumTester.getDriver();

            String ClientName = (String) jse.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("ClientName")));


            if(ClientName.equalsIgnoreCase(DataClientName)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Client Name"), count, "PASSED");
            }else{

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Client Name"), count, "FAILED");
            }


            String AgentBroker = (String) jse.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("AgentBroker")));

            if (AgentBroker.equalsIgnoreCase(DataAgentBroker)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Agent/Broker"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Agent/Broker"), count, "FAILED");
            }


            String PrimaryCause = (String) jse.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("PrimaryCause")));

            if (PrimaryCause.equalsIgnoreCase(DataPrimaryCause)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Primary Cause"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Primary Cause"), count, "FAILED");
            }

            String SecondaryCause = (String) jse.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("SecondaryCause")));

            if (SecondaryCause.equalsIgnoreCase(DataSecondaryCause)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Secondary Cause"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Secondary Cause"), count, "FAILED");
            }


           String ClaimHandler = (String) jse.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("ClaimHandler")));


            if (ClaimHandler.contains(DataClaimHandler)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Claim Handler"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Claim Handler"), count, "FAILED");
            }


            String Catastrophe = (String) jse.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Catastrophe1")));

            if (Catastrophe.equalsIgnoreCase(DataCatastrophe)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Catastrophe"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Catastrophe"), count, "FAILED");
            }

            String PremiumConfirmedBy = (String) jse.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("PremiumConfirmedBy"))).toString();


            if (PremiumConfirmedBy.contains(DataPremiumConfirmedBy)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Premium Confirmed By"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Premium Confirmed By"), count, "FAILED");
            }

            String DateOfLoss = (String) jse.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("DateOfLoss")));

            if (DateOfLoss.equalsIgnoreCase(DataDateOfLoss)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Date of Loss"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Date of Loss"), count, "FAILED");
            }

            String LossToDate = (String) jse.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("LossToDate")));

            if (LossToDate.equalsIgnoreCase(DataLossToDate)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Loss To Date"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Loss To Date"), count, "FAILED");
            }

            String Location = (String) jse.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Location")));

            if (Location.equalsIgnoreCase(DataLocation)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Location"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Location"), count, "FAILED");
            }

            String BrokerClaimNumber = (String) jse.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("BrokerClaimNumber")));

            if (BrokerClaimNumber.equalsIgnoreCase(DataBrokerClaimNumber)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Broker Claim Number"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Broker Claim Number"), count, "FAILED");
            }


            String ClaimStatus = (String) jse.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("ClaimStatus")));

            if (ClaimStatus.equalsIgnoreCase(DataClaimStatus)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Claim Status"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Claim Status"), count, "FAILED");
            }

            String ClaimDescription = (String) jse.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("ClaimDescription")));

            if (ClaimDescription.equalsIgnoreCase(DataClaimDescription)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Claim Description"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Claim Description"), count, "FAILED");
            }

            String ProgressStatus = (String) jse.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("ProgressStatus")));

            if (ProgressStatus.equalsIgnoreCase(DataProgressStatus)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Progress Status"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Progress Status"), count, "FAILED");
            }

            String LastUpdated = (String) jse.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("LastUpdated")));

            if (LastUpdated.equalsIgnoreCase(DataLastUpdated)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Last Updated"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Last Updated"), count, "FAILED");

            }

            String ClaimCloseStatus = (String) jse.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("ClaimCloseStatus")));

            if (ClaimCloseStatus.equalsIgnoreCase(DataClaimCloseStatus)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Claim Close Status"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Claim Close Status"), count, "FAILED");

            }

            String StatusDescription = (String) jse.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("StatusDescription")));

            if (StatusDescription.equalsIgnoreCase(DataStatusDescription)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Status Description"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Status Description"), count, "FAILED");

            }


            String ThirdPartyLiability = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("ThirdPartyLiability")).getText();

            if (ThirdPartyLiability.equalsIgnoreCase(DataThirdPartyLiability)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Third Party Liability"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Third Party Liability"), count, "FAILED");

            }

            String ThirdPartyRecovery = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("ThirdPartyRecovery")).getText();

            if (ThirdPartyRecovery.equalsIgnoreCase(DataThirdPartyRecovery)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Third Party Recovery"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Third Party Recovery"), count, "FAILED");

            }


            String MinorClaimant = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("MinorClaimant")).getText();

            if (MinorClaimant.equalsIgnoreCase(DataMinorClaimant)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Minor Claimant"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Minor Claimant"), count, "FAILED");

            }


            String ExGratia = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("ExGratia")).getText();

            if (ExGratia.equalsIgnoreCase(DataExGratia)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Ex Gratia"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Ex Gratia"), count, "FAILED");

            }


            String IsBordereauClaim = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("IsBordereauClaim")).getText();

            if (IsBordereauClaim.equalsIgnoreCase(DataIsBordereauClaim)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Is Bordereau Claim"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Is Bordereau Claim"), count, "FAILED");

            }


            String IsContentiousClaim = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("IsContentiousClaim")).getText();

            if (IsContentiousClaim.equalsIgnoreCase(DataIsContentiousClaim)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Is Contentious Claim"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Is Contentious Claim"), count, "FAILED");

            }


            String SalvageApplicable = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("SalvageApplicable")).getText();

            if (SalvageApplicable.equalsIgnoreCase(DataSalvageApplicable)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Salvage Applicable"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Salvage Applicable"), count, "FAILED");

            }


            String ClaimRejected = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("ClaimRejected")).getText();

            if (ClaimRejected.equalsIgnoreCase(DataClaimRejected)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Claim Rejected"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Claim Rejected"), count, "FAILED");

            }


            String OmbudsmanApproached = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("OmbudsmanApproached")).getText();

            if (OmbudsmanApproached.equalsIgnoreCase(DataOmbudsmanApproached)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Ombudsman Approached"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Ombudsman Approached"), count, "FAILED");

            }


            String Compensation = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Compensation")).getText();

            if (Compensation.equalsIgnoreCase(DataCompensation)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Compensation"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Compensation"), count, "FAILED");

            }


            String AssessmentRequired = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("AssessmentRequired")).getText();

            if (AssessmentRequired.equalsIgnoreCase(DataAssessmentRequired)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Assessment Required"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Assessment Required"), count, "FAILED");

            }


            String ReopenCaseButton = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("ReopenCaseButton")).getText();
            if (ReopenCaseButton.equalsIgnoreCase(DataReopenCaseButton)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Re-open Case Button"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Re-open Case Button"), count, "FAILED");

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

            if (Section.equalsIgnoreCase(DataSection)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Section"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Section"), count, "FAILED");
            }

            String GlassCurrentReserve = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("GlassCurrentReserve")).getText();

            if (GlassCurrentReserve.contains(DataGlassCurrentReserve)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Glass Current Reserve"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Glass Current Reserve"), count, "FAILED");
            }

            String GlassPaid = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("GlassPaid")).getText();

            if (GlassPaid.contains(DataGlassPaid)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Glass Paid"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Glass Paid"), count, "FAILED");
            }

            String GlassRecovered= commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("GlassRecovered")).getText();

            if (GlassRecovered.contains(DataGlassRecovered)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Glass Recovered"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Glass Recovered"), count, "FAILED");
            }

            String GlassIncurred= commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("GlassIncurred")).getText();

            if (GlassIncurred.contains(DataGlassIncurred)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Glass Incurred"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Glass Incurred"), count, "FAILED");
            }




        }catch (Exception ex){
            commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "OverAll Test Result"), count, "FAILED");
            commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Reason"), count, ex.getMessage());
            Assert.fail(ex.getMessage());
        }

    }

    public void PolicySection() {

        try {

            String Section = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Section")).getText();

            if(Section.equalsIgnoreCase(DataSection2)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Section2"), count, "PASSED");
            }else{

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

            String GlassRecovered = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("GlassRecovered2")).getText();

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

    public void CaseVersionDescDetailsValidations() {

        try {


            String Version = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Version")).getText();

            if (Version.contains(DataVersion)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Version"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Version"), count, "FAILED");
            }

            String TransactionDescription = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("TransactionDescription")).getText();

            if (TransactionDescription.contains(DataTransactionDescription)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Transaction Description"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Transaction Description"), count, "FAILED");
            }

            String User = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("User")).getText();

            if (User.contains(DataUser)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "User"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "User"), count, "FAILED");
            }

/*
            String Date = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Date")).getText();

            if (Date.contains(DataDate1)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Date1"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Date1"), count, "FAILED");
            }
*/

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

    public void CaseVersionDetailsValidations() {

        try {

            JavascriptExecutor jse = (JavascriptExecutor)commonSeleniumTester.getDriver();

            String ClientName = (String) jse.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("ClientName")));
            String ClientName2 = (String) jse.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("ClientName2")));

            if (ClientName2.equals(ClientName)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "ClientName2"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "ClientName2"), count, "FAILED");
            }

            String policyNumber2 = (String) jse.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("PolicyNumber2")));
            String policyNumber = (String) jse.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("PolicyNumber1")));

            if (policyNumber.equals(policyNumber2)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Policy Number2"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Policy Number2"), count, "FAILED");
            }

            String CaseNumber= (String) jse.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("CaseNumber")));
            String CaseNumber2 = (String) jse.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("CaseNumber2")));

            if (CaseNumber.equals(CaseNumber2)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Case Number2"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Case Number2"), count, "FAILED");
            }

            String DateOfLoss2 = (String) jse.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("DateOfLoss2")));
            String DateOfLoss = (String) jse.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("DateOfLoss")));

            if (DateOfLoss2.equals(DateOfLoss)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Date Of Loss2"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Date Of Loss2"), count, "FAILED");
            }


            String Peril1 = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Peril1")).getText();

            if (Peril1.equalsIgnoreCase(DataPeril1)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Peril1"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Peril1"), count, "FAILED");
            }

            String Peril2 = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Peril2")).getText();

            if (Peril2.equalsIgnoreCase(DataPeril2)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Peril2"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Peril2"), count, "FAILED");
            }


            String ExGratiaReserve = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("ExGratiaReserve")).getText();

            if (ExGratiaReserve.equalsIgnoreCase(DataExGratiaReserve)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "ExGratia Reserve"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "ExGratia Reserve"), count, "FAILED");
            }


            String ExGratiaPayment = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("ExGratiaPayment")).getText();

            if (ExGratiaPayment.equalsIgnoreCase(DataExGratiaPayment)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "ExGratia Payment"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "ExGratia Payment"), count, "FAILED");
            }


            String ExGratiaRecovery = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("ExGratiaRecovery")).getText();

            if (ExGratiaRecovery.equalsIgnoreCase(DataExGratiaRecovery)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "ExGratia Recovery"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "ExGratia Recovery"), count, "FAILED");
            }

            String ExGratiaReceipt = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("ExGratiaReceipt")).getText();

            if (ExGratiaReceipt.equalsIgnoreCase(DataExGratiaReceipt)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "ExGratia Receipt"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "ExGratia Receipt"), count, "FAILED");
            }

            String GlassReserve = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("GlassReserve")).getText();

            if (GlassReserve.equalsIgnoreCase(DataGlassReserve)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Glass Reserve"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Glass Reserve"), count, "FAILED");
            }

            String GlassPayment = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("GlassPayment")).getText();

            if (GlassPayment.equalsIgnoreCase(DataGlassPayment)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Glass Payment"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Glass Payment"), count, "FAILED");
            }

            String GlassRecovery = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("GlassRecovery")).getText();

            if (GlassRecovery.equalsIgnoreCase(DataGlassRecovery)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Glass Recovery"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Glass Recovery"), count, "FAILED");
            }

            String GlassReceipt = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("GlassReceipt")).getText();

            if (GlassReceipt.equalsIgnoreCase(DataGlassReceipt)){

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Glass Receipt"), count, "PASSED");
            }else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Glass Receipt"), count, "FAILED");
            }
//
//

        }catch (Exception ex){
            commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "OverAll Test Result"), count, "FAILED");
            commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Reason"), count, ex.getMessage());
            Assert.fail(ex.getMessage());
        }

    }

}










