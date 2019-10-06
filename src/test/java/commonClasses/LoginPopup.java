package commonClasses;

import org.openqa.selenium.Alert;


public class LoginPopup {



    public void login(CommonSeleniumTester commonSeleniumTester,String username,String password){

        //PageObjects pageObjects = new PageObjects();


        try{

            Alert alert = commonSeleniumTester.getDriver().switchTo().alert();
            alert.sendKeys(username);
            alert.sendKeys(password);
            alert.accept();

           // commonSeleniumTester.getDriver().findElement(pageObjects.getByElement())


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
