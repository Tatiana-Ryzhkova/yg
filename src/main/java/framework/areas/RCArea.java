package framework.areas;


public class RCArea extends Area {

    /**
     * Click on the avatar of the author
     */
    public void clickOnAuthorAvatar() {
        this.getElement("rcaAuthorAvatar").click();
    }

    /**
     * Get the name of the author
     */
    public String getAuthorName() {
        return this.getElementValue("rcaAuthorName");
    }

    /**
     * Verify the author's full name
     * @param expectedFullName
     */
    public boolean verifyAuthorName (String expectedFullName) {
        if (!this.getAuthorName().contains(expectedFullName)) {
            System.out.println("Error! The author's name does not contain expected value");
            return false;
        } else {
            return true;
        }
    }

    public int getViewsCounter() {
        return this.getElementValueToInt("rcaViews");
    }

    public boolean verifyViewsCounter (int expectedNumber) {
        if(this.getViewsCounter() == expectedNumber ) {
            return true;
        } else {
            System.out.println("Error! View counter is equal " + this.getViewsCounter());
            return false;
        }
    }

    /**
     * Like the item
     */
    public void like() {
        this.getElement("rcaLikeIcon").click();
    }

    public int getLikesCounter() {
        if (this.getElementValueToInt("rcaLikeCounter") == 0) {
            System.out.println("There are no likes yet");
        }
        return this.getElementValueToInt("rcaLikeIcon");
    }

    public boolean verifyLikesCounter(int expectedNumber) {
        if(this.getLikesCounter() == expectedNumber ) {
            return true;
        } else {
            System.out.println("Error! There are " + this.getLikesCounter() + "likes");
            return false;
        }
    }

    /**
     * Get the description of the photo
     */
    public String getDescription() {
        try {
            return "The photo description: \"" + this.getElementValue("rcaDescription") + "\"";
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return "There is no description on the photo";
        }
    }

    /**
     * Verify the description of the photo
     * @param description
     */
    public boolean verifyDescription (String description) {
        if (!this.getDescription().contains(description)) {
            System.out.println("Error! Photo description does not contain expected value");
            return false;
        } else {
            return true;
        }
    }

    public void clickComments() {
        this.getElement("rcaCommentIcon").click();
    }

    public int getCommentsCounter() {
        int counter = this.getElementValueToInt("rcaCommentCounter");
        if (counter == 0) {
            System.out.println("There are no comments yet");
        } else {
            System.out.println("There are " +  counter + " comments");
        }
        return counter;
    }

    public boolean verifyCommentsCounter (int expectedNumber) {
        if(this.getCommentsCounter() == expectedNumber ) {
            return true;
        } else {
            System.out.println("Error! There are " + this.getCommentsCounter() + "comments on this photo");
            return false;
        }
    }

    public void followUser() {
        try {
            this.getElement("rcaFollowBtn").click();
        } catch (Exception e) {
            System.out.println("You have already been followed this user");
            e.printStackTrace();
        }
    }

    public void unfollowUser() {
        try {
            this.getElement("rcaFollowingBtn").click();
        } catch (Exception e) {
            System.out.println("You should follow this user first");
            e.printStackTrace();
        }
    }

    public void clickFirstRCImage() {
        this.getElement("rcaBlockFirstPhoto").click();
    }

    /*public void clickRCImage(int photoNumber) {
        String path = this.getElementValue("rcaBlockFirstPhoto");
        int index = path.indexOf("1");
        String newPath = path.substring(0, index) + photoNumber + path.substring(index+1, path.length()-1);
        this.getElement(newPath).click();
    }*/

}
