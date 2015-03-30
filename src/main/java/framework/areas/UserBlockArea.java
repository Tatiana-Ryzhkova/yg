package framework.areas;


import framework.Framework;

public class UserBlockArea extends Area {

    public void clickUploadBtn () {
        this.getElement("ubaUploadBtn").click();
    }

    public String getUsername () {
        return this.getElement("ubaUsername").getText();
    }

    public void verifyUsername (String firstName, String secondName) {
        String expName = firstName + " " + secondName;
        String actName = this.getElement("ubaUsername").getText();
        if (!actName.contains(expName)) {
            try {
                throw new Exception ();
            } catch (Exception e) {
                System.out.println("Error! Actual username does not contain expected username");
                e.printStackTrace();
            }
        } else {
            System.out.println("Actual username contains expected username");
        }
    }

    public void openUserMenu () {
        this.getElement("ubaUsername").click();
    }

    public void clickMyProfile () {
        this.openUserMenu();
        this.getElement("ubaMyProfileItem").click();
    }

    /**
     * there is not valid. the item was deleted from menu
     */
    /*public void clickUpload () {
        this.openUserMenu();
        this.getElement("ubaUploadItem").click();
    }*/

    public void clickMyPhotos () {
        this.openUserMenu();
        this.getElement("ubaMyPhotosItem").click();
    }

    public void clickFindFriends () {
        this.openUserMenu();
        this.getElement("ubaFindFriendsItem").click();
    }

    public void clickMyArticles () {
        this.openUserMenu();
        //FF default popup need to be closed
        this.closeAlertPopup();
        this.getElement("ubaMyArticlesItem").click();
        this.closeAlertPopup();
        Framework.getInstance().waitWhileLoad();
    }

    public void clickReport () {
        this.openUserMenu();
        this.getElement("ubaReportItem").click();
    }

    public void clickLogout () {
        this.closeAlertPopup();
        this.openUserMenu();
        this.getElement("ubaLogoutItem").click();
        Framework.getInstance().sleep(3000);
    }

}