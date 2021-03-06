package framework.pages;

import framework.Framework;
import framework.areas.CookiesAcceptArea;
import framework.areas.LeftMenuArea;
import framework.areas.LeftMenuUserAddition;
import framework.areas.UserBlockArea;
import org.openqa.selenium.WebElement;

public class WelcomePopup extends  Page {

    public LeftMenuArea leftMenuArea = Framework.getArea(LeftMenuArea.class);
    public LeftMenuUserAddition leftMenuUser = Framework.getArea(LeftMenuUserAddition.class);
    public UserBlockArea userBlock = Framework.getArea(UserBlockArea.class);
    public CookiesAcceptArea cookiesAcceptArea = Framework.getArea(CookiesAcceptArea.class);

    public WelcomePopup() {
        super();
        isSecure = true;
    }

    public void close() {
        this.getElement("welcomeCloseBtn").click();
    }

    //is not available feature now
    /*
    public void clickOutPopup() {
        this.getElement("wpOutPopup").click();
    }
    */
    public void tryUpload() {
        this.getElement("wpTryUpload").click();
    }



}
