package framework.helpers;


import framework.Framework;
import framework.pages.MainPage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

public class EmailHelper {

    private final String emailUrl  = "https://mail.google.com";
    private final String emailFieldLocator  = "//input[@id='Email']";
    private final String pswrdFieldLocator  = "//input[@id='Passwd']";
    private final String signInBtnLocator  = "//input[@id='signIn']";

    private final String emailByTitleLocator = "//span[b[text()='YouGossip Password Reset']]/b";
    private final String tempPasswdLocator = "//p[text()='Your new password is: ']/b";
    private final String deleteBtnLocator = "//*[@id=\":5\"]/div[2]/div[1]/div/div[2]/div[3]";

    public WebDriver driver() {
        return Framework.getInstance().getDriver();
    }

    public void emailAuth() {
        this.openEmailAuth();
        this
            .enterEmail()
            .enterPswrd()
            .submitForm();
    }

    public String getValidEmail() {
        return Framework.getInstance().getConfig("testEmail");
    }

    public String getEmailPassword() {
        return Framework.getInstance().getConfig("testEmailPassword");
    }

    public void openEmailAuth() {
        this.driver().get(this.emailUrl);
    }

    public EmailHelper enterEmail () {
        try {
            this.driver().findElement(By.xpath(this.emailFieldLocator));
        } catch (org.openqa.selenium.NoSuchElementException e) {

        }
        this.driver().findElement(By.xpath(this.emailFieldLocator)).sendKeys(getValidEmail());
        return this;
    }

    public EmailHelper enterPswrd () {
        try {
            this.driver().findElement(By.xpath(this.pswrdFieldLocator));
        }  catch (org.openqa.selenium.NoSuchElementException e) {

        }
        this.driver().findElement(By.xpath(this.pswrdFieldLocator)).sendKeys(getEmailPassword());
        return this;
    }

    public void submitForm() {
        this.driver().findElement(By.xpath(this.signInBtnLocator)).click();
    }

    public void openResetEmail() {
        try {
            this.driver().findElement(By.xpath(this.emailByTitleLocator));
        } catch (org.openqa.selenium.NoSuchElementException e) {

        }
        this.driver().findElement(By.xpath(this.emailByTitleLocator)).click();
    }

    public String getPassword() {
        return this.driver().findElement(By.xpath(this.tempPasswdLocator)).getText();
    }

    public void deleteResetEmail() {
        this.driver().findElement(By.xpath(this.deleteBtnLocator)).click();
    }

    public String getTempPassword() {
        this.emailAuth();
        this.waitForEmail();
        String tempPasswd = this.getPassword();
        Framework.getInstance().sleep(3000);
        this.deleteResetEmail();
        return tempPasswd;
    }

    public void waitForEmail() {
        boolean isDisplayed = false;
        int counter = 0;
        while (!isDisplayed) {
            try {
                this.driver().findElement(By.xpath(this.emailByTitleLocator));
                if(this.driver().findElement(By.xpath(this.emailByTitleLocator)).isDisplayed()) {
                    isDisplayed = true;
                }
            } catch (Exception e) {
                Framework.getInstance().sleep(5000);
                Framework.getInstance().refresh();
                counter ++;
            }
            if (counter>=5) {
                break;
            }
        }
        this.openResetEmail();
    }

    public void acceptAlert(MainPage inst) {
        MainPage mainPage = inst;
        try {
            Alert alertPopup = mainPage.switchToAlertPopup();
            mainPage.acceptAlertPopup(alertPopup);
        } catch (NoAlertPresentException e) {

        }
    }
}
