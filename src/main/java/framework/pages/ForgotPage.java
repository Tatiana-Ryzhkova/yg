package framework.pages;


import framework.Framework;
import framework.areas.CookiesAcceptArea;
import framework.areas.LeftMenuArea;
import framework.areas.VisitorBlockArea;
import framework.helpers.FBHelper;

public class ForgotPage extends Page {

    public VisitorBlockArea visitorBlock = Framework.getArea(VisitorBlockArea.class);
    public LeftMenuArea leftMenuArea = Framework.getArea(LeftMenuArea.class);
    public CookiesAcceptArea cookiesAcceptArea = Framework.getArea(CookiesAcceptArea.class);

    public ForgotPage() {
        super();
        isSecure = true;
    }

    public void submitEmail (String email) {
        this.enterEmail(email);
        this.clickSendBtn();
    }

    public ForgotPage enterFBUserEmail() {
        FBHelper fbClass = new FBHelper();
        this.enterEmail(fbClass.fbTestUsername());
        return this;
    }

    public ForgotPage enterUserEmail(String testEmail) {
        this.enterEmail(testEmail);
        return this;
    }

    public void enterEmail(String testEmail) {
        this.getElement("rpPswrdField").sendKeys(testEmail);
    }

    public void clickSendBtn() {
        this.getElement("rpSendBtn").click();
    }

    public String getValidationMessage() {
        return this.getElementValue("rpValidation");
    }

    public String getValidation() {
        return this.getElementValue("rpValidationSent");
    }
}
