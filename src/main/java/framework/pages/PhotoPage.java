package framework.pages;


import framework.Framework;
import framework.areas.*;
import org.openqa.selenium.NoSuchElementException;

public class PhotoPage extends Page{

    /**
     * Areas for visitor Photo page
     */
    public VisitorBlockArea visitorBlock = Framework.getArea(VisitorBlockArea.class);
    public LeftMenuArea leftMenu = Framework.getArea(LeftMenuArea.class);
    public RCArea rcArea = Framework.getArea(RCArea.class);
    /**
     * Areas for user Photo page
     */
    public LeftMenuUserAddition leftMenuUser = Framework.getArea(LeftMenuUserAddition.class);
    public UserBlockArea userBlock = Framework.getArea(UserBlockArea.class);


    public PhotoPage() {
        super();
        isSecure = true;
    }

    public void clickMatureYes() {
        this.getElement("ppMatureYes").click();
    }

    public void clickMatureNo() {
        this.getElement("ppMatureNo").click();
    }

    public boolean isMatureWrapperDisplayed() {
        if (this.getElement("ppMatureWrapper").isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean verifyImgHasSource() {
        try{
            this.getElement("ppPhotoImg");
            return true;
        } catch (NoSuchElementException e) {
            if (this.isMatureWrapperDisplayed()) {
                this.clickMatureYes();
                try{
                    this.getElement("ppPhotoImg");
                    return true;
                } catch (NoSuchElementException exception) {
                    return false;
                }
            }
            return false;
        }
    }



}
