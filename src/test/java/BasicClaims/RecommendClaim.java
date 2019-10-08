package BasicClaims;

import commonClasses.CommonSeleniumTester;
import commonClasses.CommonTestTools;
import commonClasses.LoginPopup;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.util.Arrays;

public class RecommendClaim  extends BaseClass {

    String DataPayeeName;
    String DataDiscountAgreement;
    String DataPayeeIsDomiciledforTax;
    String DataDiscountDays;
    String DataDiscountPercentage;
    String DataPaymentType;
    String DataPaymentReference;
    String DataCurrency;
    String DataInvoiceDate;
    String DataPaidFromAccount;
    String DataMediaType;
    String DataCreatedDate;
    String DataCreatedBy;
    String DataPayee;
    String DataBank;
    String DataBankAccount;
    String DataBankCode;
    String DataDiscountApplied;
    String DataTotalInvoiceAmount;
    String DataDiscountPercentApplied;
    String DataDiscountAmount;
    String DataNumberOfDays;
    String DataAmount;



    private int count = 0;
    private int rowIteration = 0;
    static Logger log = Logger.getLogger(BasicClaims.PurpleHeronUnderwritingTest.class);

    @BeforeMethod

    public void
    init() throws Exception {

        reportColumns = new String[]

                {"Original Policy Number", "Payee Name", "Discount Agreement","Payee Is Domiciled for Tax", "Discount Days", "Discount Percentage", "Discount Percentage", "Payment Type", "Payment Reference", "Currency", "Invoice Date",
                        "Created By","Created Date","Media Type", "Payee","Paid From Account","Bank", "Bank Account", "Bank Code", "Discount Applied", "Total Invoice Amount", "Discount Percent Applied", "Discount Amount", "Number Of Days",
                        "Amount", "Test Results", "OverAll Test Result", "Reason"};

        setUp();
    }

    @Test
    public void RecommendClaim() throws Exception {

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

                DataPayeeName = CommonTestTools.getCellValue(headers, data[i], "Payee Name").trim();
                DataDiscountAgreement = CommonTestTools.getCellValue(headers, data[i], "Discount Agreement").trim();
                DataPayeeIsDomiciledforTax = CommonTestTools.getCellValue(headers, data[i], "Payee Is Domiciled for Tax").trim();
                DataDiscountDays = CommonTestTools.getCellValue(headers, data[i], "Discount Days").trim();
                DataDiscountPercentage = CommonTestTools.getCellValue(headers, data[i], "Discount Percentage").trim();
                DataPaymentType = CommonTestTools.getCellValue(headers, data[i], "Payment Type").trim();
                DataPaymentReference = CommonTestTools.getCellValue(headers, data[i], "Payment Reference").trim();
                DataCurrency = CommonTestTools.getCellValue(headers, data[i], "Currency").trim();
                DataInvoiceDate = CommonTestTools.getCellValue(headers, data[i], "Invoice Date").trim();
                DataPaidFromAccount = CommonTestTools.getCellValue(headers, data[i], "Paid From Account").trim();
                DataCreatedDate = CommonTestTools.getCellValue(headers, data[i], "Created Date").trim();
                DataCreatedBy = CommonTestTools.getCellValue(headers, data[i], "Created By").trim();
                DataPayee = CommonTestTools.getCellValue(headers, data[i], "Payee").trim();
                DataBank = CommonTestTools.getCellValue(headers, data[i], "Bank").trim();
                DataBankCode = CommonTestTools.getCellValue(headers, data[i], "Bank Code").trim();
                DataBankAccount = CommonTestTools.getCellValue(headers, data[i], "Bank Account #").trim();
                DataMediaType = CommonTestTools.getCellValue(headers, data[i], "Media Type").trim();
                DataDiscountApplied = CommonTestTools.getCellValue(headers, data[i], "Discount Applied").trim();
                DataTotalInvoiceAmount = CommonTestTools.getCellValue(headers, data[i], "Total Invoice Amount").trim();
                DataDiscountPercentApplied = CommonTestTools.getCellValue(headers, data[i], "Discount Percent Applied").trim();
                DataDiscountAmount = CommonTestTools.getCellValue(headers, data[i], "Discount Amount").trim();
                DataNumberOfDays = CommonTestTools.getCellValue(headers, data[i], "Number Of Days").trim();
                DataAmount = CommonTestTools.getCellValue(headers, data[i], "Amount").trim();

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
                ClaimBordereauValidation();
                screenshot = ((TakesScreenshot) commonSeleniumTester.getDriver()).getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenShotsDir + File.separator + testCaseName + " 6.Payee Details_Page_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".png"), screenshot);


                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("RecommendButton")).click();
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


    public void ClaimBordereauValidation() {

       try {

           JavascriptExecutor jse = (JavascriptExecutor)commonSeleniumTester.getDriver();

           String PayeeName = (String) jse.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("PayeeName")));


          // String PayeeName = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("PayeeName")).getText();

            if (PayeeName.equalsIgnoreCase(DataPayeeName)) {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Payee Name"), count, "PASSED");
            } else {

                commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Payee Name"), count, "FAILED");
           }

           String DiscountAgreement = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("DiscountAgreement")).getText();

           if (DiscountAgreement.equalsIgnoreCase(DataDiscountAgreement)) {

               commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Discount Agreement"), count, "PASSED");
           } else {

               commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Discount Agreement"), count, "FAILED");
           }

           String PayeeIsDomiciledforTax = (String) jse.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("PayeeIsDomiciledforTax")));

           if (PayeeIsDomiciledforTax.equalsIgnoreCase(DataPayeeIsDomiciledforTax)) {

               commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Payee Is Domiciled for Tax"), count, "PASSED");
           } else {

               commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Payee Is Domiciled for Tax"), count, "FAILED");
           }

           String DiscountDays = (String) jse.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("DiscountDays")));

           if (DiscountDays.equalsIgnoreCase(DataDiscountDays)) {

               commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Discount Days"), count, "PASSED");
           } else {

               commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Discount Days"), count, "FAILED");
           }

           String DiscountPercentage = (String) jse.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("DiscountPercentage")));
          // String DiscountPercentage = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("DiscountPercentage")).getText();

           if (DiscountPercentage.equalsIgnoreCase(DataDiscountPercentage)) {

               commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Discount Percentage"), count, "PASSED");
           } else {

               commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Discount Percentage"), count, "FAILED");
           }

          // String PaymentType = (String) jse.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("PaymentType")));
         String PaymentType = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("PaymentType")).getText();

           if (PaymentType.contains(DataPaymentType)) {

               commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Payment Type"), count, "PASSED");
           } else {

               commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Payment Type"), count, "FAILED");
           }

           String PaymentReference = (String) jse.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("PaymentReference1")));
           //String PaymentReference = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("PaymentReference1")).getText();

           if (PaymentReference.contains(DataPaymentReference)) {

               commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Payment Reference"), count, "PASSED");
           } else {

               commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Payment Reference"), count, "FAILED");
           }

           //String Currency = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Currency")).getText();
           String Currency = (String) jse.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Currency")));

           if (Currency.equalsIgnoreCase(DataCurrency)) {

               commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Currency"), count, "PASSED");
           } else {

               commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Currency"), count, "FAILED");
           }


           //String InvoiceDate = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("InvoiceDate")).getText();
           String InvoiceDate = (String) jse.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("InvoiceDate")));

           if (InvoiceDate.equalsIgnoreCase(DataInvoiceDate)) {

               commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Invoice Date"), count, "PASSED");
           } else {

               commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Invoice Date"), count, "FAILED");
           }

           String PaidFromAccount = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("PaidFromAccount")).getText();
           //String PaidFromAccount = (String) jse.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("PaidFromAccount")));

           if (PaidFromAccount.equalsIgnoreCase(DataPaidFromAccount)) {

               commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Paid From Account"), count, "PASSED");
           } else {

               commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Paid From Account"), count, "FAILED");
           }

           //String MediaType = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("MediaType")).getText();
           String MediaType = (String) jse.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("MediaType")));

           if (MediaType.equalsIgnoreCase(DataMediaType)) {

               commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Media Type"), count, "PASSED");
           } else {

               commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Media Type"), count, "FAILED");
           }

          // String CreatedDate = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("CreatedDate")).getText();
           String CreatedDate = (String) jse.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("CreatedDate")));

           if (CreatedDate.equalsIgnoreCase(DataCreatedDate)) {

               commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Created Date"), count, "PASSED");
           } else {

               commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Created Date"), count, "FAILED");
           }

           //String CreatedBy = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("CreatedBy")).getText();
           String CreatedBy = (String) jse.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("CreatedBy")));

           if (CreatedBy.equalsIgnoreCase(DataCreatedBy)) {

               commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Created By"), count, "PASSED");
           } else {

               commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Created By"), count, "FAILED");
           }

           String Payee = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Payee1")).getText();
           //String Payee = (String) jse.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Payee")));


           if (Payee.equalsIgnoreCase(DataPayee)) {

               commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Payee"), count, "PASSED");
           } else {

               commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Payee"), count, "FAILED");
           }

           //String Bank = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Bank")).getText();
           String Bank = (String) jse.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Bank")));

           if (Bank.equalsIgnoreCase(DataBank)) {

               commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Bank"), count, "PASSED");
           } else {

               commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Bank"), count, "FAILED");
           }

          // String BankAccount = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("BankAccount")).getText();
           String BankAccount = (String) jse.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("BankAccount")));


           if (BankAccount.equalsIgnoreCase(DataBankAccount)) {

               commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Bank Account"), count, "PASSED");
           } else {

               commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Bank Account"), count, "FAILED");
           }

          // String BankCode = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("BankCode")).getText();
           String BankCode = (String) jse.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("BankCode")));


           if (BankCode.equalsIgnoreCase(DataBankCode)) {

               commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Bank Code"), count, "PASSED");
           } else {

               commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Bank Code"), count, "FAILED");
           }

           String DiscountApplied = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("DiscountApplied")).getText();

           if (DiscountApplied.equalsIgnoreCase(DataDiscountApplied)) {

               commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Discount Applied"), count, "PASSED");
           } else {

               commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Discount Applied"), count, "FAILED");
           }

           //String TotalInvoiceAmount = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("TotalInvoiceAmount")).getText();
           String TotalInvoiceAmount = (String) jse.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("TotalInvoiceAmount")));


           if (TotalInvoiceAmount.equalsIgnoreCase(DataTotalInvoiceAmount)) {

               commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Total Invoice Amount"), count, "PASSED");
           } else {

               commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Total Invoice Amount"), count, "FAILED");
           }

           //String DiscountPercentApplied = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("DiscountPercentApplied")).getText();
           String DiscountPercentApplied = (String) jse.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("DiscountPercentApplied")));


           if (DiscountPercentApplied.equalsIgnoreCase(DataDiscountPercentApplied)) {

               commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Discount Percent Applied"), count, "PASSED");
           } else {

               commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Discount Percent Applied"), count, "FAILED");
           }

           String DiscountAmount = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("DiscountAmount")).getText();

           if (DiscountAmount.equalsIgnoreCase(DataDiscountAmount)) {

               commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Discount Amount"), count, "PASSED");
           } else {

               commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Discount Amount"), count, "FAILED");
           }

           String NumberOfDays = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("NumberOfDays")).getText();

           if (NumberOfDays.equalsIgnoreCase(DataNumberOfDays)) {

               commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Number Of Days"), count, "PASSED");
           } else {

               commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Number Of Days"), count, "FAILED");
           }

           //String Amount = commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Amount")).getText();
           String Amount = (String) jse.executeScript("return arguments[0].value;",commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Amount")));


           if (Amount.equalsIgnoreCase(DataAmount)) {

               commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Amount"), count, "PASSED");
           } else {

               commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Amount"), count, "FAILED");
           }



       } catch (Exception ex) {
           commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "OverAll Test Result"), count, "FAILED");
            commonExcelTester.writeToReport(reportFile, CommonTestTools.getColumnIndex(reportColumns, "Reason"), count, ex.getMessage());
           Assert.fail(ex.getMessage());

        }

    }
}










