package framework.areas;


public class LeftMenuUserAddition extends LeftMenuArea {

    public void clickFriendStream() {
        this.getElement("ulmaFriendsStream").click();
    }

    public void clickFriendStreamInMenu() {
        this.openLeftMenu().getElement("ulmaFriendsStreamInMenu").click();
    }

}
