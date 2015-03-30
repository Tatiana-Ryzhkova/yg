package framework.pages;

import framework.Framework;
import framework.areas.CookiesAcceptArea;
import framework.areas.LeftMenuArea;
import framework.areas.VisitorBlockArea;
import org.openqa.selenium.ElementNotVisibleException;

import java.util.NoSuchElementException;

public class LoginPage extends Page{

    public VisitorBlockArea visitorBlock = Framework.getArea(VisitorBlockArea.class);
    public LeftMenuArea leftMenuArea = Framework.getArea(LeftMenuArea.class);
    public CookiesAcceptArea cookiesAcceptArea = Framework.getArea(CookiesAcceptArea.class);

    public LoginPage() {
        super();
        isSecure = true;
    }

    /**
     * Fill username field with desired data
     * @param username
     * @return
     */
    public LoginPage fillUsername(String username) {
        this.setDataToElement("lpUsernameFld", username);
        return this;
    }

    /**
     * Fill password field with desired data
     * @param password
     * @return
     */
    public LoginPage fillPassword(String password) {
        this.setDataToElement("lpPasswordFld", password);
        return this;
    }

    /**
     * Click Login button
     */
    public void submitLogin() {
        this.getElement("lpLoginBtn").click();
    }

    /**
     * Login with desired invalid data and verify validation message (used for parametrized test)
     * @param username
     * @param password
     * @param validationMessage
     * @return
     */
    public LoginPage loginWithInvalidCredentials(String username, String password,
                                                 String validationMessage) {
        this.fillUsername(username).fillPassword(password).submitLogin();
        Framework.getInstance().sleep(1000);
        if (!this.verifyPageContainsValidation(validationMessage)) {
            throw new NoSuchElementException("\"Error!! " +
                    "There is no such validation message on the page\"");
        }
        return this;
    }

    /**
     * Login with desired valid credentials
     * @param username
     * @param password
     */
    public void loginWithValidCredentials(String username, String password) {
        this.fillUsername(username).fillPassword(password).submitLogin();
        if(!this.driver.getTitle().contains("YouGossip Home")) {
            throw new ElementNotVisibleException("\"Error!! " +
                    "User is not logged in\"");
        }
    }

    /**
     * Login with valid default credentials from properties file
     */
    public void loginWithValidDefaultCredentials() {
        this
                .fillUsername(this.getTestUsername())
                .fillPassword(this.getTestPassword())
                .submitLogin();
        Framework.getInstance().sleep(3000);
        if(!this.driver.getTitle().contains("YouGossip Home")) {
            throw new ElementNotVisibleException("\"Error!! " +
                    "User is not logged in\"");
        }
    }

    public String getUsernameValidationMessage() {
        return this.getValidationMessage("lpUsernameValidation");
    }

    public String getTestUserEmail() {
        return this.getPropertyValue("lpEmailDefault");
    }

    public String getTestUserId() {
        return this.getPropertyValue("lpUserIdDefault");
    }

    public String getTestUsername() {
        return this.getPropertyValue("lpUsernameDefault");
    }

    public String getTestPassword() {
        return this.getPropertyValue("lpPasswordDefault");
    }

    public void clickForgotPassword() {
        this.getElement("lpForgotBtn").click();
    }
}
