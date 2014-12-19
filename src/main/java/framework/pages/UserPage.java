package framework.pages;

import framework.Framework;
import framework.areas.*;


public class UserPage extends  Page {

    /**
     * Areas for visitor
     */
    public VisitorBlockArea visitorBlock = Framework.getArea(VisitorBlockArea.class);
    public LeftMenuArea leftMenuArea = Framework.getArea(LeftMenuArea.class);
    public FirstPhotoArea firstPhoto = Framework.getArea(FirstPhotoArea.class);
    /**
     * Areas for user
     */
    public UserBlockArea userBlock = Framework.getArea(UserBlockArea.class);
    public LeftMenuUserAddition leftMenuUser = Framework.getArea(LeftMenuUserAddition.class);

    public UserPage() {
        super();
        isSecure = true;
    }

    public String getUsername() {
        return this.getElementValue("upUsername");
    }

    public void clickEditProfile() {
        this.getElement("upEditProfile").click();
    }

    public void clickFollowBtn() {
        this.getElement("upFollowBtn").click();
    }

    public void clickToUnfollow() {
        this.getElement("upFollowingBtn").click();
    }

    public int getPhotosCounter() {
        return this.getElementValueToInt("upPhotosCounter");
    }

    public int getFollowingCounter() {
        return this.getElementValueToInt("upFollowingCounter");
    }

    public int getFollowersCounter() {
        return this.getElementValueToInt("upFollowersCounter");
    }

    public void openFollowingTab() {
        this.getElement("upFollowing").click();
    }

    public void openFollowersTab() {
        this.getElement("upFollowers").click();
    }



}
