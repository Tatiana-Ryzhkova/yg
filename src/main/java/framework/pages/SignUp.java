package framework.pages;

import framework.Framework;
import framework.areas.CookiesAcceptArea;
import framework.areas.LeftMenuArea;
import framework.areas.VisitorBlockArea;
import org.openqa.selenium.WebElement;

import java.util.Date;
import java.util.NoSuchElementException;

public class SignUp extends Page{

    public VisitorBlockArea visitorBlock = Framework.getArea(VisitorBlockArea.class);
    public LeftMenuArea leftMenuArea = Framework.getArea(LeftMenuArea.class);
    public CookiesAcceptArea cookiesAcceptArea = Framework.getArea(CookiesAcceptArea.class);

    public SignUp() {
        super();
        isSecure = true;
    }

    /**
     * SignUp using email and username, all other parameters are used By default
     * @param email
     * @param username
     */
    public void byDefault (String email, String username) {
        this
                .setFullName()
                .fillEmail(email)
                .setBirthdayDate()
                .fillUsername(username)
                .fillPasswordFields();
        //this.flagTermsAndCondition();
        this.submit();
    }

    /**
     * SignUp using email, all other parameters are used By default
     * @param email
     */
    public void byDefaultWithEmail (String email) {
        this
                .setFullName()
                .fillEmail(email)
                .setBirthdayDate()
                .fillUsername()
                .fillPasswordFields();
        //this.flagTermsAndCondition();
        this.submit();
    }

    /**
     * SignUp using username, all other parameters are used By default
     * @param username
     */
    public void byDefaultWithUsername (String username) {
        this
                .setFullName()
                .fillEmail()
                .setBirthdayDate()
                .fillUsername(username)
                .fillPasswordFields();
        //this.flagTermsAndCondition();
        this.submit();
    }

    /**
     * Set desired First Name
     * @param firstname
     */
    public SignUp fillFirstname(String firstname) {
        this.setDataToElement("suFirstname", firstname);
        return this;
    }

    /**
     * Set desired Last Name
     * @param lastname
     */
    public SignUp fillLastname(String lastname) {
        this.setDataToElement("suLastname", lastname);
        return this;
    }

    /**
     * Fill First and Last Name fields with auto generated data
     * @return
     */
    public SignUp setFullName() {
        this.fillFirstname(this.generateFirstName());
        this.fillLastname(this.generateLastName());
        return this;
    }

    /**
     * Fill Email field with auto generated email
     * @return
     */
    public SignUp fillEmail() {
        this.fillEmail(this.generateEmail());
        return this;
    }

    /**
     * Fill Email field with desired email
     * @param email
     * @return
     */
    public SignUp fillEmail(String email) {
        this.setDataToElement("suEmail", email);
        return this;
    }

    /**
     * Fill Birthday day field with desired data
     * @param day
     * @return
     */
    public SignUp fillBirthdayDay(String day) {
        this.setDataToElement("suBirthdateDay", day);
        return this;
    }

    /**
     * Fill Birthday month field with desired data
     * @param month
     * @return
     */
    public SignUp fillBirthdayMonth(String month) {
        this.setDataToElement("suBirthdateMonth", month);
        return this;
    }

    /**
     * Fill Birthday year field with desired data
     * @param year
     * @return
     */
    public SignUp fillBirthdayYear(String year) {
        this.setDataToElement("suBirthdateYear", year);
        return this;
    }

    /**
     * Fill Birthday fields with desired data
     * @param day
     * @param month
     * @param year
     * @return
     */
    public SignUp setBirthdayDate (String day, String month, String year) {
        fillBirthdayDay(day);
        fillBirthdayMonth(month);
        fillBirthdayYear(year);
        return  this;
    }

    /**
     * Fill Birthday fields with default data from properties
     * @return
     */
    public SignUp setBirthdayDate () {
        fillBirthdayDay(this.getPropertyValue("birthDay"));
        fillBirthdayMonth(this.getPropertyValue("birthMonth"));
        fillBirthdayYear(this.getPropertyValue("birthYear"));
        return  this;
    }

    /**
     * Fill twitter username field with desired data
     * @param twitterUsername
     * @return
     */
    public SignUp fillTwitterUsername(String twitterUsername) {
        this.setDataToElement("suTwitter_username", twitterUsername);
        return this;
    }

    /**
     * Fill username field with desired data
     * @param username
     * @return
     */
    public SignUp fillUsername(String username){
        this.setDataToElement("suUsername", username);
        return this;
    }

    /**
     * Fill username field with auto generated data
     * @return
     */
    public SignUp fillUsername(){
        return this.fillUsername(this.generateUsername());
    }

    /**
     * Fill password field with desired data
     * @param password
     * @return
     */
    public SignUp fillPassword(String password) {
        this.setDataToElement("suPassword", password);
        return this;
    }

    /**
     * Fill Confirm password field with desired data
     * @param password
     * @return
     */
    public SignUp fillConfirmPassword(String password) {
        this.setDataToElement("suConfirm", password);
        return this;
    }

    /**
     * Fill Password and Confirm password fields with the same desired data
     * @param password
     * @return
     */
    public SignUp fillPasswordAndConfirm(String password) {
        this.fillPassword(password);
        this.fillConfirmPassword(password);
        return this;
    }

    /**
     * Fill Password and Confirm password fields with default data from properties
     * @return
     */
    public SignUp fillPasswordFields() {
        this.fillPassword(this.getPropertyValue("testPassword"));
        this.fillConfirmPassword(this.getPropertyValue("testPassword"));
        return this;
    }

    public String generateFirstName() {
        String[] nameParts = this.getPropertyValue("testName").split("@");
        return nameParts[0] + new Date().getTime();
    }

    public String generateLastName() {
        String[] nameParts = this.getPropertyValue("testName").split("@");
        return nameParts[1] + new Date().getTime();
    }

    /**
     * Set flag to the Terms and Condition checkbox
     * @return
     */
    public SignUp flagTermsAndCondition() {
        this.getElement("suTermsCheckbox").click();
        return this;
    }

    /**
     * Submit SignUp from
     */
    public void submit() {
        this.getElement("suSingUpButton").click();
    }

    public String getValidationPopupMessage() {
        String message = this.getDriver().switchTo().alert().getText();
        return message;
    }

    /**
     * Verify Welcome popup
     */
    public void checkSuccess() {
        WebElement element = Framework.getPage(WelcomePopup.class).getElement("welcomeCloseBtn");
        if (element.isDisplayed()) {
            System.out.println("User has been signed up successfully");
        } else throw new NoSuchElementException("Error!! User has not been signed up");
    }

    public String getContentText() {
        return this.getElement("suContentText").getText();
    }

}
