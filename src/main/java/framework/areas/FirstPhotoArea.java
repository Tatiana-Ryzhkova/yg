package framework.areas;


import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.NoSuchElementException;

public class FirstPhotoArea extends  Area {

    /**
     * Click on the first photo in the grid
     */
    public void click() {
        this.getElement("fpaPhoto").click();
    }

    /**
     * Like the first photo in the grid
     */
    public void like() {
        this.getElement("fpaPhotoLikeIcon").click();
    }

    public int getLikesCounter() {
        if (this.getElementValueToInt("fpaPhotoLikeCounter") == 0) {
            System.out.println("There are no likes yet");
        }
        return this.getElementValueToInt("fpaPhotoLikeCounter");
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
     * Get the name of the author of the first photo in the grid
     */
    public String getAuthorName() {
        return this.getElementValue("fpaPhotoAuthor");
    }

    public void clickOnAuthorName() {
        this.getElement("fpaPhotoAuthor").click();
    }

    /**
     * Verify the full name of the author of the first photo in the grid
     * @param expectedFullName
     */
    public boolean verifyAuthorName (String expectedFullName) {
        if (!this.getAuthorName().contains(expectedFullName)) {
            System.out.println("Error! Photo author name does not contain expected value");
            return false;
        } else {
            return true;
        }
    }

    public int getViewsCounter() {
        return this.getElementValueToInt("fpaPhotoViews");
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
     * Get the description of the first photo in the grid
     */
    public String getDescription() {
        try {
            return "The photo description: \"" + this.getElementValue("fpaPhotoDescription") + "\"";
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return "There is no description on the photo";
        }
    }

    /**
     * Verify the description of the first photo in the grid
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

    public String getPhotoUploadDate() {
        return this.getElementValue("fpaPhotoDate");
    }

    public void clickComments() {
        this.getElement("fpaCommentsIcon").click();
    }

    public int getCommentsCounter() {
        int counter = this.getElementValueToInt("fpaCommentsCounter");
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

    public boolean verifyAddCommentIsDisplayed() {
        return this.getElement("fpaAddCommentArea").isDisplayed();
    }

    public void clickDeleteIcon() {
        this.getElement("fpaDeleteIcon").click();
    }

    public void clickEditIcon() {
        this.getElement("fpaEditIcon").click();
    }

    public boolean isMatureTrue() {
        boolean mature = true;
        try {
            this.getElement("fpaMature").isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            mature = false;
        }
        return mature;
    }

}
