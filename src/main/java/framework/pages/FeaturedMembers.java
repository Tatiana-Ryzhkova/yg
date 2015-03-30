package framework.pages;


import framework.Framework;
import framework.areas.*;

public class FeaturedMembers extends Page{

    public CookiesAcceptArea cookiesAcceptArea = Framework.getArea(CookiesAcceptArea.class);
    public LeftMenuArea leftMenuArea = Framework.getArea(LeftMenuArea.class);
    public LeftMenuUserAddition leftMenuUser = Framework.getArea(LeftMenuUserAddition.class);
    public UserBlockArea userBlock = Framework.getArea(UserBlockArea.class);
    public UploadArea uploadArea = Framework.getArea(UploadArea.class);

    public FeaturedMembers() {
        super();
        isSecure = true;
    }

    public WelcomePopup clickDoneBtn() {
        this.getElement("fmDoneBtn").click();
        return Framework.getPage(WelcomePopup.class);
    }


}
