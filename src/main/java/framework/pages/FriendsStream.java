package framework.pages;


import framework.Framework;
import framework.areas.*;

public class FriendsStream extends Page {

    public CookiesAcceptArea cookiesAcceptArea = Framework.getArea(CookiesAcceptArea.class);
    /**
     * Areas for visitor main page
     */
    public VisitorBlockArea visitorBlock = Framework.getArea(VisitorBlockArea.class);
    public LeftMenuArea leftMenuArea = Framework.getArea(LeftMenuArea.class);
    public FirstPhotoArea firstPhoto = Framework.getArea(FirstPhotoArea.class);
    /**
     * Areas for user main page
     */
    public UserBlockArea userBlock = Framework.getArea(UserBlockArea.class);
    public LeftMenuUserAddition leftMenuUser = Framework.getArea(LeftMenuUserAddition.class);
    public UploadArea uploadArea = Framework.getArea(UploadArea.class);
    public EditPhotoArea editPhotoArea = Framework.getArea(EditPhotoArea.class);

    public FriendsStream() {
        super();
        isSecure = true;
    }


}
