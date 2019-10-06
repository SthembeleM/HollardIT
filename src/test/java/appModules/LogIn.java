package appModules;


import objectRepository.PageObjects;
import org.apache.log4j.Logger;
import commonClasses.CommonSeleniumTester;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


/**
 * Created with IntelliJ IDEA.
 * User: F4703510
 * Date: 11/21/14
 * Time: 12:16 PM
 * To change this template use File | Settings | File Templates.
 */


public class LogIn {

    private String logMessage;
    static PageObjects pageObjects = new PageObjects();
    Logger log = Logger.getLogger(LogIn.class);

    public String getLogMessage() {
        return logMessage;
    }

    public void setLogMessage(String logMessage) {this.logMessage = logMessage;
    }

    public LogIn(String pageObjectFileName)throws Exception{
        logMessage="";
        pageObjects.getDOM(pageObjectFileName);
        pageObjects.setXmlElementNode("element");
    }


    //  login method
    public boolean logIn(CommonSeleniumTester commonSeleniumTester, PageObjects pageObjects, String Username, String Password){

        try{
            log.info("Logging In");

            commonSeleniumTester.getDriver().manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
            if(commonSeleniumTester.isElementPresent(pageObjects.getByElement("Username")) && commonSeleniumTester.isElementPresent(pageObjects.getByElement("Password"))){
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Username")).clear();
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Username")).sendKeys(Username);
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Password")).clear();
                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Password")).sendKeys(Password);
                log.info("Clicking Login button");
                Thread.sleep(2000);


                commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("MyLogin")).click();

                if (commonSeleniumTester.isElementPresent(pageObjects.getByElement("Logout"))){
                    return true;
                }  else{
                    logMessage =  "Could not login successfully";
                    return  false;
                }

            }else{
                logMessage =  "Could not find the username and password field";
                return false;
            }

        }catch (Exception e){
            logMessage = e.getMessage();
            log.info("Logging In Error\t"+e.getMessage());
            return  false;
        }
    }

    // logout method
    public void logout(CommonSeleniumTester commonSeleniumTester, PageObjects pageObjects)throws IOException{
        log.info("Logging Out");
       // commonSeleniumTester.getDriver().manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        if(commonSeleniumTester.isElementPresent(pageObjects.getByElement("Logout"))){
            log.info("Clicking Log out button");
            commonSeleniumTester.getDriver().findElement(pageObjects.getByElement("Logout")).click();
        }else{
            logMessage =  "Could not logout successfully";
        }
        log.info("Quitting the driver");
        commonSeleniumTester.getDriver().quit();

		

    }




}

