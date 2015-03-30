package framework.areas;


import framework.Framework;

public class VisitorBlockArea extends  LeftMenuArea {

    public void clickUploadBtn() {
        this.getElement("vbaUploadBtn").click();
    }

    public void clickSignUpBtn() {
        this.getElement("vbaSignUpBtn").click();
    }

    public void clickLoginBtn() {
        this.getElement("vbaLoginBtn").click();
    }

    public void fillPopupUsername(String username) {
        this.setDataToElement("popupUsernameFld", username);
    }

    public void fillPopupPassword(String password) {
        this.setDataToElement("popupPasswordFld", password);
    }

    public void clickPopupLoginBtn() {
        this.getElement("popupLoginBtn").click();
    }

    public void clickPopupFacebookBtn() {
        this.getElement("popupFacebookBtn").click();
    }

    public void clickPopupForgot() {
        this.getElement("popupForgotPswrd").click();
    }

    public void clickPopupSignUp() {
        this.getElement("popupSignUp").click();
    }

    public void loginFromPopup (String username, String password) {
        this.clickLoginBtn();
        this.fillPopupUsername(username);
        this.fillPopupPassword(password);
        Framework.getInstance().sleep(500);
        this.clickPopupLoginBtn();
    }

    public String getUsernameFldLabel()
    {
        return this.getElement("popupLabelUsername").getText();
    }
}
