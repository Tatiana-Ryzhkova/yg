package framework.pages;


import framework.Framework;
import framework.areas.LeftMenuArea;
import framework.areas.LeftMenuUserAddition;
import framework.areas.UserBlockArea;

import java.util.NoSuchElementException;

public class FBTerms extends Page {

    public LeftMenuArea leftMenuArea = Framework.getArea(LeftMenuArea.class);
    public LeftMenuUserAddition leftMenuUser = Framework.getArea(LeftMenuUserAddition.class);
    public UserBlockArea userBlock = Framework.getArea(UserBlockArea.class);

    public FBTerms() {
        super();
        isSecure = true;
    }

    public FBTerms verifyTextIsPresent (String text) {
        if (this.pageSource().contains(text)) {
            System.out.println("Text: \"" + text + "\", - is present on the page");
        } else throw new NoSuchElementException("Error!! There is no such text on the page");
        return  this;
    }

    public void confirmYes() {
        this.getElement("fbConfirmYes").click();
    }

    public void confirmNo() {
        this.getElement("fbConfirmNo").click();
    }


}
