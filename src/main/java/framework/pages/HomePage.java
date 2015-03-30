package framework.pages;

import framework.Framework;
import org.openqa.selenium.ElementNotVisibleException;

import java.util.NoSuchElementException;

public class HomePage extends Page {

    public HomePage() {
        super();
        isSecure = true;
    }

    public void clickSignUpBtn() {
        this.getElement("hpSignUpBtn").click();
    }

    public void clickLoginBtn() {
        this.getElement("hpLoginBtn").click();
    }

    public void clickFBLoginBtn() {
        this.getElement("hpFacebookBtn").click();
    }

    public void clickExploreBtn() {
        this.getElement("hpExploreBtn").click();
    }

    public HomePage verifyHPElement(String element) {
        if (this.getElement(element).isDisplayed()) {
            System.out.println("Element \"" + element +
                    "\" is visible and displayed properly");
        } else throw new ElementNotVisibleException("Error!! Element is not visible");
        return this;
    }

    public HomePage verifyTextIsPresent (String text) {
        try{
            this.pageSourceContainText(text);
        } catch (org.openqa.selenium.NoSuchElementException e){
            Framework.getInstance().waitWhileLoad();
        }
        if (this.pageSourceContainText(text)) {
            System.out.println("Text: \"" + text + "\", - is present on the page");
        } else throw new NoSuchElementException("Error!! There is no such text on the page");
        return  this;
    }

    public HomePage verifyLink(String element, String link) {
        if (!(this.getElement(element).getAttribute("href").compareTo(link) == 0)) {
            throw new NoSuchElementException("Error!! There is no such link on the page");
        } else {
            System.out.println("Link is present");
        }
        return this;
    }
}