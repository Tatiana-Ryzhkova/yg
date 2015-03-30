package framework.helpers;


import framework.Framework;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.util.Set;

public class FBHelper {

    String testFBEmail = "soni4kad34@meta.ua";
    String testFBPswrd = "******";
    String newFBUsrnm = "okorobeinyk+1@hypnorep.com";
    String newFBPswrd = "*******";

    WebDriver driver =  Framework.getInstance().getDriver();

    public WebDriver getDriver() {
        return this.driver;
    }
    public String fbTestUsername() {
        return this.testFBEmail;
    }
    public String fbTestPassword() {
        return this.testFBPswrd;
    }
    public String newFBUsername() {
        return this.newFBUsrnm;
    }
    public String newFBPassword() {
        return this.newFBPswrd;
    }

    public Set <String> getWindowHandles() {
        return this.driver.getWindowHandles();
    }

    public String getWindowHandle() {
        return this.driver.getWindowHandle();
    }

    public WebDriver switchToWindow(String windowName) {
        return this.driver.switchTo().window(windowName);
    }

    public FBHelper fillEmail(String emailOrPhone) {
        try {
            this.driver.findElement(By.id("email"));
        } catch (NoSuchElementException e) {

        }
        this.driver.findElement(By.id("email")).sendKeys(emailOrPhone);
        return this;
    }

    public FBHelper fillPassword(String password) {
        try {
            this.driver.findElement(By.id("pass"));
        } catch (NoSuchElementException e) {

        }
        this.driver.findElement(By.id("pass")).sendKeys(password);
        return this;
    }

    public void submitFBLogin() {
        try {
            this.driver.findElement(By.name("login"));
        } catch (NoSuchElementException e) {

        }
        this.driver.findElement(By.name("login")).click();
    }

    public void submitLoginForm(String emailOrPhone, String password) {
        this.fillEmail(emailOrPhone);
        this.fillPassword(password);
        this.submitFBLogin();
    }

}
