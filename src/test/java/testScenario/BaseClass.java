package testScenario;


import commonClasses.*;
import objectRepository.PageObjects;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.NPOIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.util.*;


public class BaseClass {


    public final String propertiesFilePath = "Resources" + File.separator + "Environment.properties";
    public final String testDataDir = "TestData";
    public final String reportsDir = "Reports";
    public final String uploadDirectory = testDataDir + File.separator + "files";
    public final String screenShotsDir = "screenshots";


    public Properties prop;
    public String environment = "";
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
    LoginPopup loginPopup;
    static PageObjects pageObjects;

    Logger log = Logger.getLogger(testScenario.BaseClass.class);

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

        log.info("Test Information" + "\nOS\t:" + System.getProperty("os.name") +
                "\nUser\t:" + System.getProperty("user.name") +
                "\nBrowser\t:" + prop.get("BrowserName") +
                "\nEnvironment\t:" + environment +
                "\nURL\t:" + prop.getProperty(environment + "URL"));

//        String pageObjectFileName = prop.getProperty("ObjectRepositoryFile");
//        if (pageObjectFileName == null || pageObjectFileName.isEmpty()) {
//            throw new Exception("Page Object file name not specified");
//        }
        pageObjects.getDOM(pageObjectFileName);
        pageObjects.setXmlElementNode("element");

        environment = prop.getProperty("ENV").trim();
        if (!(environment.matches("QA") || environment.matches("DEV") || environment.matches("INT") || environment.matches("QA_via_JCAPS") || environment.matches("MMI_QA") || environment.matches("MMI_STRESS"))) {            //validate input environment
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
        reportFile = "Reports" + File.separator + "Report_" + CommonTestTools.formatDate(CommonTestTools.changeDate(0), "YY_MM_dd_hh_mm_ss") + "_" + environment + sheetName + ".xls";
        log.info("Report\t:" + reportFile);

        File file = new File(uploadDirectory);

        commonFileTester.deleteFiles(file);                                     //delete the message files
        Thread.sleep(2000);
        file.mkdir();                                                //re-create new directory to contain message files
        file.mkdir();

        File screenShotFile = new File(screenShotsDir);
        commonFileTester.deleteFiles(screenShotFile);                                     //delete the screenshots files
        screenShotFile.mkdir();                                                //re-create new directory to contain screenshots
        screenShotFile.mkdir();

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

       // DesiredCapabilities dCaps;
        log.info("Setting the capabilities and driver configurations");
        if (prop.getProperty("BrowserName").trim().toLowerCase().contains("chrome")) {
            System.setProperty("webdriver.chrome.driver", "Resources" + File.separator + "chromedriver.exe");
            //dCaps = new DesiredCapabilities().chrome();
            //String[] switches = {"--ignore-certificate-errors", "--disable-popup-blocking", "--disable-translate", "--incognito"};
            //dCaps.setCapability("chrome.switches", Arrays.asList(switches));
            ChromeOptions options = new ChromeOptions();
            options.addExtensions(new File("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe"));
//            DesiredCapabilities capabilities = new DesiredCapabilities();
//            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//            ChromeDriver driver = new ChromeDriver(options);



//            options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
//                    UnexpectedAlertBehaviour.IGNORE);
//            options.addArguments("test-type");


        } else if (prop.getProperty("BrowserName").trim().toLowerCase().contains("internet")) {
            System.setProperty("webdriver.ie.driver", "Resources" + File.separator + "IEDriverServer.exe");
            //dCaps = new DesiredCapabilities().internetExplorer();
        } else if (prop.getProperty("BrowserName").trim().toLowerCase().contains("firefox")) {
           // dCaps = new DesiredCapabilities().firefox();
        } else {
            //dCaps = new DesiredCapabilities();
        }
        //dCaps.setPlatform(Platform.VISTA);

        //dCaps.setJavascriptEnabled(true);

        //log.info("Driver Capabilities\t:" + dCaps);
        log.info("Initializing the driver");
        commonSeleniumTester = new CommonSeleniumTester(prop.getProperty("BrowserName"));
        log.info("driver configurations\t" + commonSeleniumTester.getDriver());
        log.info("Launching the browser");
        commonSeleniumTester.launchBrowser(prop.getProperty(environment + "URL"));
        commonSeleniumTester.launchMybrowser();
//        prop.getProperty(environment + "URL");

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
            //for (int i = 0; i <= rowCount; i++) {
            // row = sheet.getRow(i);
            // if(row == null){             //break on empty row
            //break;
            // }

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

}
