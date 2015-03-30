package framework.pages;

import framework.Framework;
import framework.areas.Area;
import org.openqa.selenium.*;

import java.util.*;


public class Page extends Area {

    public List pageAreaList = new ArrayList();

    Properties commonProperty, pageProperty;
    protected String baseUrl;
    protected String pageURL;
    protected boolean isSecure = false;

    public String getProtocol()
    {
        return (this.isSecure == true) ? "https://": "http://";
    }

    public Page build (WebDriver driver, Framework framework) {
        super.build(driver, framework);

        this.baseUrl = this.framework.getConfig("baseUrl");
        this.pageURL = this.getProtocol() + this.baseUrl +
                this.framework.getConfig(this.getClass().getSimpleName() + "Url");
        return this;
    }

    public void open() {
        try {
            this.driver.navigate().to(this.pageURL);
        } catch (Exception e) {
            System.out.println("Can't open page with url :" + this.pageURL );
        }
        Framework.getInstance().sleep(2000);
    }

    /**
     * Parametrized "open" method.
     * As additionUrl use all characters in the URL after baseUrl+this.PageUrl (see Config)
     * @param additionUrl
     */
    public void openWithSpecificUrl(String additionUrl) {
        String fullUrl = this.pageURL + additionUrl;
        try {
            this.driver.navigate().to(fullUrl);
        } catch (Exception e) {
            System.out.println("Can't open page with url :" + this.pageURL );
        }
    }

    public String generateEmail() {
        String[] mailParts = this.getFramework().getConfig("testEmail").split("@");
        return mailParts[0] + "+" + new Date().getTime() + "@" + mailParts[1];
    }

    public String generateUsername() {
        return "user" + new Date().getTime();
    }

    public String generateText() {
        return "uniqueText" + new Date().getTime();
    }

    public String generateTextWithWord (String word) {
        return word + new Date().getTime();
    }

    public String getValidationMessage(String forElement) {
        String message = this.getElement(forElement).getText();
        return message;
    }

    public Boolean verifyPageContainsValidation (String validationMessage) {
        Framework.getInstance().waitWhileLoad();
        if (this.pageSourceContainText(validationMessage)) {
            return true;
        } else {
            return false;
        }
    }

    public void refresh() {
        Framework.getInstance().refresh();
    }

    public void sleep (long millisecond) {
        Framework.getInstance().sleep(millisecond);
    }

    public boolean verifyCurrentPageUrl (String url) {
        if(url.compareTo(this.getDriver().getCurrentUrl()) == 0) {
            return true;
        } else {
            return false;
        }
    }

    public String pageSource() {
        return this.getDriver().getPageSource();
    }

    public boolean pageSourceContainText(String text) {
        Framework.getInstance().waitWhileLoad();
        if (this.pageSource().contains(text)) {
            return true;
        } else {
            return false;
        }
    }

}