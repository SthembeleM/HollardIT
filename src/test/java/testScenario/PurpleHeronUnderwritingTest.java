package testScenario;

import appModules.LogIn;
import commonClasses.CommonSeleniumTester;
import commonClasses.CommonTestTools;
import commonClasses.LoginPopup;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.util.Arrays;
import java.util.List;

public class PurpleHeronUnderwritingTest extends BaseClass {


    String DataDisplayTypeNewBusiness;
    String DataDisplayTypeEndorsement;
    String DataFirstDueDate;
    String DataSecondDueDate;
    String DataFirstAllocationAmount;
    String DataSecondAllocationAmount;
    String DataFirstAllocation;
    String DataSecondAllocation;
    String DataFirstZeroAllocation;
    String DataSecondZeroAllocation;
    String DataFirstLastAllocation;
    String DataSecondLastAllocation;

    String DataPolicyInformationNoData;
    String DataPolicyInformationDocumentRef;
    String DataPolicyInformationDocumentRefAllocation;
    String DataPolicyInformationNewBusiness;
    String DataPolicyInformationUnderwriting;
    String DataPolicyInformationCancelPolicy;
    String DataPolicyInformation2;
    String DataPolicyInformationUser;
    String DataPolicyInformationGrossPremium;
    String DataPolicyInformationCommission;

    String DataAllocationDetailDocumentSND;
    String DataAllocationDetailDocumentSEC;
    String DataAllocationDetailGrossPremium;
    String DataAllocationDetailCommission;
    String DataAllocationDetailAllocatedUser;
    String DataAllocationDetailTotal;
    String DataAllocationReversal;

    private int count = 0;
    private int rowIteration = 0;


    @Before
    public void init() throws Exception {

        reportColumns = new String[]

                {"Original Policy Number", "Grid", "Insured Name", "DisplayStatus", "Agent Name", "Policy Number", "Document Reference", "Display Type NewBusiness", "Display Type Endorsement", "First Due Date", "Second Due Date", "First Allocation Amount", "Second Allocation Amount", "First Allocation", "Second Allocation", "First Zero Allocation", "Second Zero Allocation", "First Last Allocation", "Second Last Allocation", "Policy Information DocumentRef", "Policy Information DocumentRef Allocation", "Policy Information New Business", "Policy Information Underwriting", "Policy Information Cancel Policy", "Policy Information2", "Policy Information User", "Policy Information Gross Premium", "Policy Information Commission", "Allocation Detail Document SND", "Allocation Detail Document SEC", "Allocation Detail Gross Premium", "Allocation Detail Commission", "Allocation Detail Allocated User", "Allocation Detail Total", "Test Results", "OverAll Test Result", "Reason"};

        setUp();
    }

    @Test
    public void CreateQuote1() throws Exception {

        log.info("Start @Test method");
        String[][] data = null;
        //  AgentWebAuthentication agentWebAuthentication = new AgentWebAuthentication();
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

            count++;

            try {

                String testCaseName;
                String policyGrid;
                String grid;
                String InsuredN;
                String InsuredName;
                String AgentN;
                String AgentName;
                String DisplayS;
                String DisplayStatus;
                String PolicyN;
                String PolicyInformationNoData;
                String username;
                String password;
                username = CommonTestTools.getCellValue(headers, data[i], "Username").trim();
                password=CommonTestTools.getCellValue(headers, data[i], "Password").trim();

                loginPopup.login(commonSeleniumTester,username,password);

                DataPolicyInformationNoData = CommonTestTools.getCellValue(headers, data[i], "Underwriting Grid").trim();
                DataDisplayTypeNewBusiness = CommonTestTools.getCellValue(headers, data[i], "Display Type NewBusiness").trim();
                DataDisplayTypeEndorsement = CommonTestTools.getCellValue(headers, data[i], "Display Type Endorsement").trim();
                DataFirstDueDate = CommonTestTools.getCellValue(headers, data[i], "First Due Date").trim();
                DataSecondDueDate = CommonTestTools.getCellValue(headers, data[i], "Second Due Date").trim();
                DataFirstAllocationAmount = CommonTestTools.getCellValue(headers, data[i], "First Allocation Amount").trim();
                DataSecondAllocationAmount = CommonTestTools.getCellValue(headers, data[i], "Second Allocation Amount").trim();
                DataFirstAllocation = CommonTestTools.getCellValue(headers, data[i], "First Allocation").trim();
                DataSecondAllocation = CommonTestTools.getCellValue(headers, data[i], "Second Allocation").trim();
                DataFirstZeroAllocation = CommonTestTools.getCellValue(headers, data[i], "First Zero Allocation").trim();
                DataSecondZeroAllocation = CommonTestTools.getCellValue(headers, data[i], "Second Zero Allocation").trim();
                DataFirstLastAllocation = CommonTestTools.getCellValue(headers, data[i], "First Last Allocation").trim();
                DataSecondLastAllocation = CommonTestTools.getCellValue(headers, data[i], "Second Last Allocation").trim();


                DataPolicyInformationDocumentRef = CommonTestTools.getCellValue(headers, data[i], "Policy Information DocumentRef").trim();
                DataPolicyInformationDocumentRefAllocation = CommonTestTools.getCellValue(headers, data[i], "Policy Information DocumentRef Allocation").trim();
                DataPolicyInformationNewBusiness = CommonTestTools.getCellValue(headers, data[i], "Policy Information New Business").trim();
                DataPolicyInformationUnderwriting = CommonTestTools.getCellValue(headers, data[i], "Policy Information Underwriting").trim();
                DataPolicyInformationCancelPolicy = CommonTestTools.getCellValue(headers, data[i], "Policy Information Cancel Policy").trim();
                DataPolicyInformation2 = CommonTestTools.getCellValue(headers, data[i], "Policy Information2").trim();
                DataPolicyInformationUser = CommonTestTools.getCellValue(headers, data[i], "Policy Information User").trim();
                DataPolicyInformationGrossPremium = CommonTestTools.getCellValue(headers, data[i], "Policy Information Gross Premium").trim();
                DataPolicyInformationCommission = CommonTestTools.getCellValue(headers, data[i], "Policy Information Commission").trim();
                DataAllocationDetailDocumentSND = CommonTestTools.getCellValue(headers, data[i], "Allocation Detail Document SND").trim();
                DataAllocationDetailDocumentSEC = CommonTestTools.getCellValue(headers, data[i], "Allocation Detail Document SEC").trim();
                DataAllocationDetailGrossPremium = CommonTestTools.getCellValue(headers, data[i], "Allocation Detail Gross Premium").trim();
                DataAllocationDetailCommission = CommonTestTools.getCellValue(headers, data[i], "Allocation Detail Commission").trim();
                DataAllocationDetailAllocatedUser = CommonTestTools.getCellValue(headers, data[i], "Allocation Detail Allocated User").trim();
                DataAllocationDetailTotal = CommonTestTools.getCellValue(headers, data[i], "Allocation Detail Total").trim();
                DataAllocationReversal=CommonTestTools.getCellValue(headers, data[i], "Action").trim();



                testCaseName = CommonTestTools.getCellValue(headers, data[i], "Test Case Name").trim();
                policyNumber = CommonTestTools.getCellValue(headers, data[i], "Policy Number").trim();
                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Original Policy Number"), count, policyNumber);

                WebDriverWait wait = new WebDriverWait(commonSeleniumTester.getDriver(), 75);
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("PolicyNo"))).sendKeys(policyNumber);
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.getByElement("Search")));

                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Search")).click();
                Thread.sleep(10000);

                policyGrid = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("PolicyGrid")).getText();
                grid = CommonTestTools.getCellValue(headers, data[i], "Grid").trim();

                //check grid text
                if (policyGrid.equalsIgnoreCase(grid)) {

                    commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Grid"), count, "PASSED");
                    commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Reason"), count, "Grid text matches");

                } else {

                    commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Grid"), count, "FAIL");
                    commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Reason"), count, "Grid text does not match");
                }

                //Check Insured Name
                InsuredN = commonSeleniumTester.getDriver().findElement(By.xpath("//*[@id='" + policyNumber + "']/td[1]")).getText();
                InsuredName = CommonTestTools.getCellValue(headers, data[i], "Insured Name").trim();

                if (InsuredN.equalsIgnoreCase(InsuredName)) {
                    commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Insured Name"), count, "PASSED");
                    commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Reason"), count, "Insured Name text matches");
                } else {
                    commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Insured Name"), count, "FAILED");
                    commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Reason"), count, "Insured Name text does not match");
                }

                //Check Agent Name
                AgentN = commonSeleniumTester.getDriver().findElement(By.xpath("//*[@id='" + policyNumber + "']/td[3]")).getText();
                AgentName = CommonTestTools.getCellValue(headers, data[i], "Agent Name").trim();

                if (AgentN.equalsIgnoreCase(AgentName)) {

                    commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Agent Name"), count, "PASSED");
                    commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Reason"), count, "Agent Name text matches");

                } else {

                    commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Agent Name"), count, "Failed");
                    commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Reason"), count, "Agent Name text does not matches");
                }

                //Check Po;icy Number
                PolicyN = commonSeleniumTester.getDriver().findElement(By.xpath("//*[@id='" + policyNumber + "']/td[2]")).getText();


                if (PolicyN.equalsIgnoreCase(policyNumber)) {

                    commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Policy Number"), count, "PASSED");
                    commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Reason"), count, "PolicyNumber matches");

                } else {

                    commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Policy Number"), count, "FAILED");
                    commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Reason"), count, "PolicyNumber does not matches");
                }

                //Check Display Status
                DisplayS = commonSeleniumTester.getDriver().findElement(By.xpath("//*[@id='" + policyNumber + "']/td[4]")).getText();
                DisplayStatus = CommonTestTools.getCellValue(headers, data[i], "DisplayStatus");

                if (DisplayS.equalsIgnoreCase(DisplayStatus)) {

                    commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "DisplayStatus"), count, "PASSED");
                    commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Reason"), i, "DisplayStatus matches");

                } else {

                    commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "DisplayStatus"), count, "Failed");
                    commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Reason"), count, "DisplayStatus does not matches");
                }


                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + "Loading_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);

                // click Super Inquiry Search Results - Policy
                commonSeleniumTester.getDriver().findElement(By.xpath("//*[@id='" + policyNumber + "']/td[1]")).click();
                Thread.sleep(10000);


                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + "Policy information_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);

                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("underwriter_button")).click();
                Thread.sleep(5000);

                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + "Policy_underwriting_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);
                Thread.sleep(10000);


                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("DocumentRef")).click();

                Thread.sleep(1000);
                AllocationValidation();

                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("AllocationSave")).click();

                Thread.sleep(10000);

                PolicyInformationNoData = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("PolicyInformationNoData")).getText();

                if (PolicyInformationNoData.equalsIgnoreCase(DataPolicyInformationNoData)) {

                    commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Display Type NewBusiness"), count, "PASSED");


                } else {

                    commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Display Type NewBusiness"), count, "FAILED");

                }

                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Underwriting3")).click();
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Underwriting2")).click();
                Thread.sleep(5000);
                PolicyInformationValidation();

                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("PolicyInformationDocumentRefAllocation")).click();
                //Thread.sleep(10000);
                //AllocationDetailValidation();
                Thread.sleep(15000);


            if (!DataAllocationReversal.isEmpty()&& DataAllocationReversal.equalsIgnoreCase("Reversal")){

                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Reversal")).click();
                Thread.sleep(10000);
//                Alert alt = commonSeleniumTester.getDriver().switchTo().alert();
//                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("ReversalContent")).sendKeys(Keys.TAB);
//                alt.accept();


            }
                commonSeleniumTester.getDriver().quit();



            } catch (Exception ex) {
                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "OverAll Test Result"), count, "FAILED");
                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Reason"), count, ex.getMessage());
                Assert.fail(ex.getMessage());

            }

        }

    }

    public void AllocationValidation() {

        try {

            String FirstTableSND;
            String FirstTableSEC;
            String DisplayTypeNewBusiness;
            String DisplayTypeEndorsement;
            String FirstDueDate;
            String SecondDueDate;
            String FirstAllocationAmount;
            String SecondAllocationAmount;
            String FirstAllocation;
            String SecondAllocation;
            String FirstZeroAllocation;
            String SecondZeroAllocation;
            String FirstLastAllocation;
            String SecondLastAllocation;


            FirstTableSND = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("FirstTableSND")).getText();
            FirstTableSEC = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("FirstTableSEC")).getText();
            DisplayTypeNewBusiness = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("DisplayTypeNewBusiness")).getText();
            DisplayTypeEndorsement = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("DisplayTypeEndorsement")).getText();
            FirstDueDate = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("FirstDueDate")).getText();
            SecondDueDate = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("SecondDueDate")).getText();
            FirstAllocationAmount = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("FirstAllocationAmount")).getText();
            SecondAllocationAmount = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("SecondAllocationAmount")).getText();
            FirstAllocation = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("FirstAllocation")).getText();
            SecondAllocation = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("SecondAllocation")).getText();
            FirstZeroAllocation = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("FirstZeroAllocation")).getText();
            SecondZeroAllocation = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("SecondZeroAllocation")).getText();
            FirstLastAllocation = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("FirstLastAllocation")).getText();
            SecondLastAllocation = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("SecondLastAllocation")).getText();



            if (FirstTableSND.contains("SND") && FirstTableSEC.contains("SEC")) {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Document Reference"), count, "PASSED");


            } else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Document Reference"), count, "FAILED");

            }

            //Allocation

            if (DisplayTypeNewBusiness.contains(DataDisplayTypeNewBusiness)) {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Display Type NewBusiness"), count, "PASSED");


            } else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Display Type NewBusiness"), count, "FAILED");

            }

            if (DisplayTypeEndorsement.contains(DataDisplayTypeEndorsement)) {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Display Type Endorsement"), count, "PASSED");

            } else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Display Type Endorsement"), count, "FAILED");

            }

            if (FirstDueDate.contains(DataFirstDueDate)) {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "First Due Date"), count, "PASSED");


            } else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "First Due Date"), count, "FAILED");

            }

            if (SecondDueDate.contains(DataSecondDueDate)) {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Second Due Date"), count, "PASSED");

            } else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Second Due Date"), count, "FAILED");

            }

            if (FirstAllocationAmount.contains(DataFirstAllocationAmount)) {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "First Allocation Amount"), count, "PASSED");


            } else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "First Allocation Amount"), count, "FAILED");

            }

            if (SecondAllocationAmount.contains(DataSecondAllocationAmount)) {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Second Allocation Amount"), count, "PASSED");

            } else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Second Allocation Amount"), count, "FAILED");

            }

            if (FirstAllocation.contains(DataFirstAllocation)) {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "First Allocation"), count, "PASSED");


            } else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "First Allocation"), count, "FAILED");

            }

            if (SecondAllocation.contains(DataSecondAllocation)) {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Second Allocation"), count, "PASSED");

            } else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Second Allocation"), count, "FAILED");

            }

            if (FirstZeroAllocation.contains(DataFirstZeroAllocation)) {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "First Zero Allocation"), count, "PASSED");

            } else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "First Zero Allocation"), count, "FAILED");

            }

            if (SecondZeroAllocation.contains(DataSecondZeroAllocation)) {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Second Zero Allocation"), count, "PASSED");

            } else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Second Zero Allocation"), count, "FAILED");

            }

            if (FirstLastAllocation.contains(DataFirstLastAllocation)) {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "First Last Allocation"), count, "PASSED");


            } else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "First Last Allocation"), count, "FAILED");

            }

            if (SecondLastAllocation.contains(DataSecondLastAllocation)) {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Second Last Allocation"), count, "PASSED");


            } else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Second Last Allocation"), count, "FAILED");

            }
        } catch (Exception ex) {
            commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "OverAll Test Result"), count, "FAILED");
            commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Reason"), count, ex.getMessage());
            Assert.fail(ex.getMessage());

        }

    }

    public void PolicyInformationValidation() {

        try {


            String PolicyInformationDocumentRef;
            String PolicyInformationDocumentRefAllocation;
            String PolicyInformationNewBusiness;
            String PolicyInformationUnderwriting;
            String PolicyInformationCancelPolicy;
            String PolicyInformation2;
            String PolicyInformationUser;
            String PolicyInformationGrossPremium;
            String PolicyInformationCommission;

            PolicyInformationDocumentRef = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("PolicyInformationDocumentRef")).getText();
            PolicyInformationDocumentRefAllocation = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("PolicyInformationDocumentRefAllocation")).getText();
            PolicyInformationNewBusiness = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("PolicyInformationNewBusiness")).getText();
            PolicyInformationUnderwriting = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("PolicyInformationUnderwriting")).getText();
            PolicyInformationCancelPolicy = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("PolicyInformationCancelPolicy")).getText();
            PolicyInformation2 = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("PolicyInformation2")).getText();
            PolicyInformationUser = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("PolicyInformationUser")).getText();


            if (PolicyInformationDocumentRef.contains(DataPolicyInformationDocumentRef)) {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Policy Information DocumentRef"), count, "PASSED");


            } else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Policy Information DocumentRef"), count, "FAILED");

            }


            if (PolicyInformationDocumentRefAllocation.contains(DataPolicyInformationDocumentRefAllocation)) {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Policy Information DocumentRef Allocation"), count, "PASSED");


            } else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Policy Information DocumentRef Allocation"), count, "FAILED");

            }


            if (PolicyInformationNewBusiness.equalsIgnoreCase(DataPolicyInformationNewBusiness)) {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Policy Information New Business"), count, "PASSED");


            } else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Policy Information New Business"), count, "FAILED");

            }


            if (PolicyInformationUnderwriting.equalsIgnoreCase(DataPolicyInformationUnderwriting)) {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Policy Information Underwriting"), count, "PASSED");


            } else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Policy Information Underwriting"), count, "FAILED");

            }


            if (PolicyInformationUnderwriting.equalsIgnoreCase(DataPolicyInformationUnderwriting)) {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Policy Information Underwriting"), count, "PASSED");


            } else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Policy Information Underwriting"), count, "FAILED");

            }

            if (PolicyInformationCancelPolicy.equalsIgnoreCase(DataPolicyInformationCancelPolicy)) {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Policy Information Cancel Policy"), count, "PASSED");


            } else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Policy Information Cancel Policy"), count, "FAILED");

            }

            if (PolicyInformation2.equalsIgnoreCase(DataPolicyInformation2)) {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Policy Information2"), count, "PASSED");


            } else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Policy Information2"), count, "FAILED");

            }

            if (PolicyInformationUser.equalsIgnoreCase(DataPolicyInformationUser)) {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Policy Information User"), count, "PASSED");


            } else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Policy Information User"), count, "FAILED");

            }

//            if (PolicyInformationGrossPremium.equalsIgnoreCase(DataPolicyInformationGrossPremium)) {
//
//                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Policy Information Gross Premium"), count, "PASSED");
//
//
//            } else {
//
//                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Policy Information Gross Premium"), count, "FAILED");
//
//            }

//            if (PolicyInformationGrossPremium.equalsIgnoreCase(DataPolicyInformationGrossPremium)) {
//
//                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Policy Information Gross Premium"), count, "PASSED");
//
//
//            } else {
//
//                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Policy Information Gross Premium"), count, "FAILED");
//
//            }

//
//            if (PolicyInformationCommission.equalsIgnoreCase(DataPolicyInformationCommission)) {
//
//                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Policy Information Commission"), count, "PASSED");
//
//
//            } else {
//
//                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Policy Information Commission"), count, "FAILED");
//
//            }
        } catch (Exception ex) {
            commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "OverAll Test Result"), count, "FAILED");
            commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Reason"), count, ex.getMessage());
            Assert.fail(ex.getMessage());

        }

    }


    public void AllocationDetailValidation() {

        try {
            String AllocationDetailDocumentSND;
            String AllocationDetailDocumentSEC;
            String AllocationDetailGrossPremium;
            String AllocationDetailCommission;
            String AllocationDetailAllocatedUser;
            String AllocationDetailTotal;

            GetTableData();

            JavascriptExecutor jse = (JavascriptExecutor)commonSeleniumTester.getDriver();
            AllocationDetailDocumentSND = (String) jse.executeScript("return arguments[0].text;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("AllocationDetailDocumentSND")));
            AllocationDetailDocumentSEC = (String) jse.executeScript("return arguments[0].innerHTML;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("AllocationDetailDocumentSEC")));
            AllocationDetailGrossPremium = (String) jse.executeScript("return arguments[0].innerHTML;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("AllocationDetailGrossPremium")));
            AllocationDetailCommission = (String) jse.executeScript("return arguments[0].innerHTML;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("AllocationDetailCommission")));
            AllocationDetailAllocatedUser = (String) jse.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("AllocationDetailAllocatedUser")));
            AllocationDetailTotal = (String) jse.executeScript("return arguments[0].innerHTML;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("AllocationDetailTotal")));



            if (AllocationDetailDocumentSND.contains(DataAllocationDetailDocumentSND)) {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Allocation Detail Document SND"), count, "PASSED");


            } else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Allocation Detail Document SND"), count, "FAILED");

            }

            if (AllocationDetailDocumentSEC.contains(DataAllocationDetailDocumentSEC)) {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Allocation Detail Document SEC"), count, "PASSED");


            } else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Allocation Detail Document SEC"), count, "FAILED");

            }

            if (AllocationDetailGrossPremium.equalsIgnoreCase(DataAllocationDetailGrossPremium)) {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Allocation Detail Gross Premium"), count, "PASSED");


            } else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Allocation Detail Gross Premium"), count, "FAILED");

            }

            if (AllocationDetailCommission.equalsIgnoreCase(DataAllocationDetailCommission)) {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Allocation Detail Commission"), count, "PASSED");


            } else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Allocation Detail Commission"), count, "FAILED");

            }


            if (AllocationDetailAllocatedUser.equalsIgnoreCase(DataAllocationDetailAllocatedUser)) {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Allocation Detail Allocated User"), count, "PASSED");


            } else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Allocation Detail Allocated User"), count, "FAILED");

            }

            if (AllocationDetailTotal.equalsIgnoreCase(DataAllocationDetailTotal)) {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Allocation Detail Allocated User"), count, "PASSED");


            } else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Allocation Detail Allocated User"), count, "FAILED");

            }


        } catch (Exception ex) {
            commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "OverAll Test Result"), count, "FAILED");
            commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Reason"), count, ex.getMessage());
            Assert.fail(ex.getMessage());

        }
    }

    public void GetTableData() {

      int Column=0;
      int rows=1;

      String snd="";
      String sec="";

        WebElement customtable = commonSeleniumTester.getDriver().findElement(By.id("adgAllocTranGrid_TABLE"));
        List<WebElement> r = customtable.findElements(By.tagName("tr"));
        for (WebElement row : r) {
            List<WebElement> d = row.findElements(By.tagName("td"));
            for(int i = 0; i<d.size(); i++) {
                if(i==0)  {
                    WebElement x =d.get(i);
                    JavascriptExecutor js = (JavascriptExecutor) commonSeleniumTester.getDriver();
                    js.executeScript("arguments[0].scrollIntoView();", x);
                    System.out.println(i+"."+d.get(i).getText()+"\n");

                   if (Column==2&&rows==13){

                       snd=  d.get(i).getText();

                   }

                    if (Column==3&&rows==13){

                        sec=  d.get(i).getText();

                    }

                    if(d.get(i).getText().contains("SND")) {
                        System.out.println(i+".yes\n");
                        System.out.println(Column);
                        System.out.println(rows);
                    }
                    else
                    {
                        System.out.println("No\n");
                    }

                }
                rows++;
            }
            Column++;
        }

    }

}






