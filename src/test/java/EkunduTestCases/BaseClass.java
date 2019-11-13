package EkunduTestCases;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import commonClasses.*;
import objectRepository.PageObjects;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.NPOIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;

import javax.rmi.CORBA.Util;
import java.io.File;
import java.io.IOException;
import java.util.*;


public class BaseClass {


    public final String propertiesFilePath = "Resources" + File.separator + "Environment.properties";
    public final String testDataDir = "TestData";
    public final String reportsDir = "Reports";
    public final String uploadDirectory = testDataDir + File.separator + "files";
    public final String screenShotsDir = "screenshots";
    public final String ExtentReport = "ExtentReport";
    ExtentReports extentReport;
    ExtentTest ext;

    public Properties prop;
    public String environment = "";
    public String ExtentReportFile;
    public String ScreenShotsFile;
    public String reportFile;
    public String[] headers;
    public String dataSheetName;
    public String sheetName;
    public String[] reportColumns;
    public String dbSchema = "";
    public String pageObjectFileName = "";
    CommonDBTester commonDBTester;
    CommonExcelTester commonExcelTester;
    CommonFileTester commonFileTester;

    CommonSeleniumTester commonSeleniumTester;
    static PageObjects pageObjects;
    static String reporter;



    Logger log = Logger.getLogger( BaseClass.class);

    public BaseClass(){}

    public void setUp()throws Exception{

        log.info("SetUp method");

        commonExcelTester = new CommonExcelTester();
        commonSeleniumTester = new CommonSeleniumTester();

        commonDBTester = new CommonDBTester();
        commonFileTester = new CommonFileTester();
        pageObjects = new PageObjects();
        headers = null;

        prop = CommonTestTools.getPropInstance(propertiesFilePath);
        if (prop.size() < 2) {
            throw new Exception("Could not get environment properties, please check your properties file\t:"+propertiesFilePath);
        }

        pageObjectFileName = prop.getProperty("ObjectRepositoryFile");
        if (pageObjectFileName == null || pageObjectFileName.isEmpty()) {
            throw new Exception("Page Object file name not specified");
        }

        pageObjects.getDOM(pageObjectFileName);
        pageObjects.setXmlElementNode("element");

        environment = prop.getProperty("ENV").trim();
        if (!(environment.matches("QA") || environment.matches("UAT") || environment.matches("PER") || environment.matches("TST") || environment.matches("SUPP") || environment.matches("EkunduUAT") || environment.matches( "EkunduSUPP" ))) {            //validate input environment
            throw new Exception("Incorrect Test Environment specified");
        }

        environment = environment + "_";
        dataSheetName = prop.getProperty("DataSheetName");
        sheetName = prop.getProperty("SheetName");
        dbSchema = prop.getProperty(environment + "DB_Schema");

        log.info("Test Data Info");
        log.info("DataSheet\t:" + dataSheetName);
        log.info("Sheet Name\t:" + sheetName);

        File report = new File(reportsDir);
        if(!report.exists()){
            report.mkdir();
            report.mkdir();
        }
        reportFile = "Reports" + File.separator + "Report_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + dataSheetName + ".xls";
        log.info("Report\t:" + reportFile);

        File file = new File(uploadDirectory);
        commonFileTester.deleteFiles(file);                                     //delete the message files
        Thread.sleep(2000);
        file.mkdir();                                                //re-create new directory to contain message files
        file.mkdir();

//        File screenShotFile = new File(screenShotsDir);
//        commonFileTester.deleteFiles(screenShotFile);                                     //delete the screenshots files
//        screenShotFile.mkdir();                                                //re-create new directory to contain screenshots
//        screenShotFile.mkdir();

        log.info("Creating Excel Report");
        commonExcelTester.CreateExcelDoc(reportFile);
        commonExcelTester.createReport(reportColumns);
        log.info("End @Before method\n\n");

    }



    public void connectDB(String environment, Properties prop) throws Exception {

        String driverName = prop.getProperty("OracleDriver");
        Class.forName(driverName);

        String serverName = prop.getProperty(environment + "DB_Hostname");
        String serverPort = prop.getProperty(environment + "DB_Port");
        String sid = prop.getProperty(environment + "DB_SID");
        String url = "jdbc:oracle:thin:@" + serverName + ":" + serverPort + ":" + sid;
        String username = prop.getProperty(environment + "DB_Username").toString();
        String password = prop.getProperty(environment + "DB_Password").toString();

        commonDBTester = new CommonDBTester(url, username, password, driverName);

    }

    public void setUpLaunchBrowser()throws Exception{

        log.info("Setting the capabilities and driver configurations");
        if (prop.getProperty("BrowserName").trim().toLowerCase().contains("chrome")) {
            System.setProperty("webdriver.chrome.driver", "Resources" + File.separator + "chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addExtensions(new File("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe"));
            options.getBrowserName();


        } else if (prop.getProperty("BrowserName").trim().toLowerCase().contains("internet")) {
            System.setProperty("webdriver.ie.driver", "Resources" + File.separator + "IEDriverServer.exe");

        } else if (prop.getProperty("BrowserName").trim().toLowerCase().contains("firefox")) {

        }

//        log.info("Initializing the driver");
        commonSeleniumTester = new CommonSeleniumTester(prop.getProperty("BrowserName"));
//        log.info("driver configurations\t" + commonSeleniumTester.getDriver());
//        log.info("Launching the browser");
        commonSeleniumTester.launchBrowser(prop.getProperty(environment + "URL"));
        commonSeleniumTester.launchMybrowser();

    }

    public String[][] getDataSheetArray(String filePath, String fileName, String sheetName) throws IOException {

        List<List<String>>  listOfLists = getDataSheetList(filePath,fileName,sheetName);
        String[][] datasheetArray = new String[listOfLists.size()][];     //Create a 2 dimensional String array
        int i = 0;
        for (List<String> list : listOfLists) {
            if(list.isEmpty() || (list.get(0).isEmpty() && list.get(1).isEmpty()))          //check if row is empty
                break;
            datasheetArray[i++] = list.toArray(new String[list.size()]);
        }
        return Arrays.copyOf(datasheetArray,i);  //Exclude empty rows
    }

    public  List<List<String>>  getDataSheetList(String filePath, String fileName, String sheetName) throws IOException {

        List<List<String>>  listOfLists = new ArrayList<List<String>>();   //Create a multidemensional ArrayList to hold String values
        File file;
        Workbook workbook = null;
        Sheet sheet;
        try {

            if(!(fileName.contains(".xls") || fileName.contains(".xlsx"))) {               //When file extension is not specified

                file = new File(filePath + File.separator+fileName+".xlsx");
                if(!file.exists()){

                    file = new File(filePath + File.separator+fileName+".xls");
                    if(!file.exists()) {
                        throw new IOException("The file does not exist "+file.getAbsolutePath());
                    }
                    fileName+=".xls";
                } else {
                    fileName += ".xlsx";
                }
            } else{
                file = new File(filePath + File.separator+fileName);
                if(!file.exists()) {
                    throw new IOException("The file does not exist "+file.getAbsolutePath());
                }
            }

            //InputStream inputStream = new FileInputStream(file);
            // String fileExtension = fileName.substring(fileName.indexOf("."));           //Find the file extension by spliting file name in substring and getting only extension name

            //file.setReadOnly();
            // file.setReadable(true);
            String fileExtension = getFileExtension(fileName);


            //  XSSFWorkbook workbook = null;
            // WorkbookFactory.create(file);
           // XSSFWorkbook workbook = null;
            if(fileExtension.equals("xls")){

                NPOIFSFileSystem fs = new NPOIFSFileSystem(file);
                long lStartTime = System.currentTimeMillis();
                 workbook = new HSSFWorkbook(fs.getRoot(), true);

                //  workbook = new HSSFWorkbook(file);                             //If it is xls file then create object of XSSFWorkbook class
                long lEndTime = System.currentTimeMillis();
                long difference = lEndTime - lStartTime;
                System.out.println("Elapsed milliseconds: " + difference+"\nStart\t:"+Long.toString(lStartTime)+"\nEnd\t:"+Long.toString(lEndTime)+"\nSeconds\t:"+Long.toString(difference / 1000));
                fs.close();

            } else if(fileExtension.equals("xlsx")){

                //  PackageAccess pa = PackageAccess.READ;
               // new XSSFWorkbook(new FileInputStream(file));
                OPCPackage pkg = OPCPackage.open(file.getAbsolutePath());
                // pkg.getPackageAccess().
                long lStartTime = System.currentTimeMillis();
                //  workbook = new XSSFWorkbook(file);                             //If it is xlsx file then create object of XSSFWorkbook class

               // new XSSFWorkbook();

                workbook = new XSSFWorkbook(pkg);

                //workbook = new XSSFWorkbook(pkg);

                long lEndTime = System.currentTimeMillis();
                long difference = lEndTime - lStartTime;
                System.out.println("Elapsed milliseconds: " + difference+"\nStart\t:"+Long.toString(lStartTime)+"\nEnd\t:"+Long.toString(lEndTime)+"\nSeconds\t:"+Long.toString(difference / 1000));

                pkg.close();
                // workbook = new XSSFWorkbook(inputStream);                             //If it is xlsx file then create object of XSSFWorkbook class

            } else{
                throw new IOException("The file extension "+fileExtension+" is not supported for the file");
            }

            //workbook.getNumberOfSheets();
            sheet = workbook.getSheet(sheetName);
            int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();

            Row row;
            List<String> rowData;
            String cellValue;
            Cell cell;

            Iterator<Row> rowIterator = sheet.iterator();

            while(rowIterator.hasNext()){

                listOfLists.add(new ArrayList<String>());
                rowData = new ArrayList<>();           //To store the row data  - columns
                row = rowIterator.next();
                for (int t = 0; t < row.getLastCellNum(); t++) {

                    cell = row.getCell(t);
                    cellValue = "";

                    if(cell !=null) {

                        int cellType = cell.getCellType();
                        switch(cellType) {
                            case Cell.CELL_TYPE_STRING:               //CELL_TYPE_STRING
                                cellValue = cell.getStringCellValue().trim();
                                break;
                            case Cell.CELL_TYPE_NUMERIC:       //CELL_TYPE_NUMERIC
                                cellValue = String.valueOf(cell.getNumericCellValue()).trim();
                                cellValue = cellValue.split("\\.")[0];
                                break;
                            case Cell.CELL_TYPE_BOOLEAN:           //CELL_TYPE_BOOLEAN
                                cellValue = String.valueOf(cell.getBooleanCellValue()).trim();
                                break;
                            case Cell.CELL_TYPE_FORMULA:          //CELL_TYPE_FORMULA
                                cellValue = cell.getCellFormula().trim();
                                break;
                            default:
                                cellValue = "";
                                break;
                        }
                    }
                    rowData.add(cellValue);

                }
                listOfLists.get(listOfLists.size() - 1).addAll(rowData);
               // listOfLists.get(i).addAll(rowData);
                rowData.clear();
            }

        } catch (Exception errMsg) {
            throw new IOException("Datasheet load error\t"+errMsg.getMessage());
        }
        return listOfLists;
    }


    public static String getFileExtension(String fileName) {
        int dotInd = fileName.lastIndexOf('.');

        // if dot is in the first position,
        // we are dealing with a hidden file rather than an extension
        return (dotInd > 0 && dotInd < fileName.length()) ? fileName
                .substring(dotInd + 1) : "";
    }

    public void generateReport() {

        File extent = new File(ExtentReport);
        if(!extent.exists()){
            extent.mkdir();
            extent.mkdir();
        }
        ExtentReportFile = "ExtentReport" + File.separator + dataSheetName +"Report_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".html";

        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(ExtentReportFile);
        htmlReporter.config().setReportName(sheetName + "Report");
        htmlReporter.config().setDocumentTitle(dataSheetName + "Report");
        extentReport = new ExtentReports();
        extentReport.attachReporter(htmlReporter);

        ext = extentReport.createTest(sheetName +  " Report");
        ext.assignAuthor("Mpumelelo Dube");
        ext.info("Loading " + sheetName + " Test cases");

    }

    public void getResults(ITestResult result) throws Exception {
        if (result.getStatus() == ITestResult.FAILURE) {
            String SS = getScreenshots(commonSeleniumTester, result.getName());
            ext.fail("Snapshot below: " + ext.addScreenCaptureFromPath(SS));
            ext.fail(result.getThrowable().getMessage());
            ext.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " Test case FAILED due to below issues:", ExtentColor.RED));
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            ext.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
        }else {

            //ext.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " Test Case SKIPPED"), ExtentColor.YELLOW);
        }

    }


    private static String getScreenshots(CommonSeleniumTester commonSeleniumTester, String screenshotName) throws IOException {

        TakesScreenshot ts = (TakesScreenshot) commonSeleniumTester.getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);

        String destination = System.getProperty("user.dir") + "/screenshots/" + screenshotName + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" +".png";
        File finalDestination = new File(destination);
        //FileUtils.deleteQuietly(source);
        FileUtils.copyFile(source, finalDestination);
        return destination;

    }

}
