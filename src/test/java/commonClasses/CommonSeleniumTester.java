package commonClasses;

import java.io.File;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.openqa.selenium.phantomjs.PhantomJSDriver;
//import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 *
 * @author f3307530
 */

public class CommonSeleniumTester {


    private WebDriver driver = null;

    public CommonSeleniumTester(){};

    public CommonSeleniumTester(String browserDriver)throws Exception{

        try {

            DesiredCapabilities dCaps = new DesiredCapabilities();

            if(browserDriver.trim().toLowerCase().contains("internet explorer") || browserDriver.trim().toLowerCase().contains("internetexplorer")){
                driver = new InternetExplorerDriver();
            }

            else if(browserDriver.trim().toLowerCase().contains("chrome")){
                driver = new ChromeDriver();
            }

            else if(browserDriver.trim().toLowerCase().contains("firefox")){
                driver = new FirefoxDriver();
            }

//            else if(browserDriver.trim().toLowerCase().contains("phantomjs")){
//                driver = new PhantomJSDriver(dCaps);
//            }

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

    public CommonSeleniumTester(String browserDriver, DesiredCapabilities dCaps)throws Exception{

        try {


        if(browserDriver.trim().toLowerCase().contains("internet explorer") || browserDriver.trim().toLowerCase().contains("internetexplorer")){
            driver = new InternetExplorerDriver(dCaps);
        }

        else if(browserDriver.trim().toLowerCase().contains("chrome")){
            driver = new ChromeDriver(dCaps);
        }

        else if(browserDriver.trim().toLowerCase().contains("firefox")){
            driver = new FirefoxDriver(dCaps);
        }

//        else if(browserDriver.trim().toLowerCase().contains("phantomjs")){
//            driver = new PhantomJSDriver(dCaps);
//        }

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

    public void launchBrowser(String url){
       // driver.get(url);
        //driver.navigate().to(url);
        driver.get(url);
       // driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }


//    public void launchMybrowser(){
//
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("start-maximized");
//        options.addArguments("disable-infobars");
//        options.addArguments("--disable-extensions");
//        WebDriver driver =  new ChromeDriver(options);
//        driver.navigate().to("http://mpumelelod:Support2@hsshiuatweb01/PurpleHeron.Web");
//
//    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }


    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
    
    public void getFrameDriver(String frameName){
        try {
            driver.switchTo().frame(frameName);
        }   catch (Exception ex){
             ex.printStackTrace();
        }
    }
    
    public int getNumberOfWindowsDisplayed(){
        Set<String> availableWindows = driver.getWindowHandles();
        int size = 0;
        try {
            size = availableWindows.size();

        }   catch (Exception e){
            e.printStackTrace();
        }
        return size;
    }
    
    public void getDriverOfNotWindowHandler(String currentWindow){

        try {
            Set<String> availableWindows = driver.getWindowHandles();
            if (!availableWindows.isEmpty()){
                for (String windowId : availableWindows){
                    if (!windowId.equals(currentWindow)){
                        driver.switchTo().window(windowId);
                        break;
                    }
                }
            }
        }   catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void getDriverOfCurrentWindowHandler(){

        try {
            String currentWindow = driver.getWindowHandle();
            Set<String> availableWindows = driver.getWindowHandles();
            if (!availableWindows.isEmpty()){
                for (String windowId : availableWindows){
                    if (windowId.equals(currentWindow)){
                        driver.switchTo().window(windowId);
                        break;
                    }
                }
            }

        }   catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void getDriverOfCurrentWindowHandler(String currentWindow){

        try {
            Set<String> availableWindows = driver.getWindowHandles();
            if (!availableWindows.isEmpty()){
                for (String windowId : availableWindows){
                    if (windowId.equals(currentWindow)){
                        driver.switchTo().window(windowId);
                        break;
                    }
                }
            }

        }   catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public boolean goToWindowUsingTitle(String windowTitle){

        if(getNumberOfWindowsDisplayed() > 1 ){
            Set<String> availableWindows = driver.getWindowHandles();

            for(int  i = 0; i < getNumberOfWindowsDisplayed(); i++){


                if (!availableWindows.isEmpty()){
                    for (String windowId : availableWindows){
                        driver.switchTo().window(windowId);
                        availableWindows.remove(windowId);
                        break;
                    }     // end if statement
                }     //    end for loop


                try{

                    if(driver.getTitle().contains(windowTitle))
                        return true;

                } catch(Exception e){
                    e.getMessage();
                }
            }
        }
        return false;
    }

 public static  void  main(String [] args)throws Exception{



     // CommonSeleniumTester commonSeleniumTester = new CommonSeleniumTester();
      /*  System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");


        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_settings.popups", 0);
        options.setExperimentalOption("prefs", prefs);
        */
 DesiredCapabilities dCaps = new DesiredCapabilities();
     dCaps.setJavascriptEnabled(true);
     dCaps.setCapability("takesScreenshot", false);

//     File phantomjs = new File("phantomjs-1.9.7-windows"+File.separator+"phantomjs.exe");
//   dCaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, phantomjs.getAbsolutePath());
//        CommonSeleniumTester  commonSeleniumTester = new CommonSeleniumTester("phantomJS", dCaps);
//        commonSeleniumTester.launchBrowser("http://172.20.70.227:8181/ipc/messages?view=messages&useOldData=true");
//     commonSeleniumTester.getDriver().quit();
    }

    public void launchMybrowser() {
    }
}
