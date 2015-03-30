package framework.areas;


import framework.Framework;

import java.util.Date;

public class FirstArticleArea  extends  Area {

    /**
     * Click "Read more on the first article in the grid
     */
    public void readMore() {
        this.getElement("faaReadMore").click();
    }

    /**
     * Like the first article in the grid
     */
    public void like() {
        this.getElement("faaArticleLikeIcon").click();
    }

    public int getLikesCounter() {
        int counter = this.getElementValueToInt("faaArticleLikeCounter");
        if (counter == 0) {
            System.out.println("There are no likes yet");
        }
        return counter;
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
     * Get the date the first article in the grid was added
     */
    public String getDateString() {
        return this.getElementValue("faaArticleDate");
    }

    public Date getDate (String date) {
        return this.getDateFromString(date, "EEEE, MMMM dd, yyyy");
    }

    public boolean verifyNewerDate (String dateString, String secondDateString) {
        if (this.isNewerDate(this.getDate(dateString), this.getDate(secondDateString))) {
            return true;
        } else {
            return false;
        }
    }

    public boolean verifyOlderDate (String dateString, String secondDateString) {
        if (this.isNewerDate(this.getDate(dateString), this.getDate(secondDateString))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Get the name of the author of the first article in the grid
     */
    public String getAuthorName() {
        return this.getElementValue("faaArticleAuthor");
    }

    /**
     * Verify the full name of the author of the first article in the grid
     * @param expectedFullName
     */
    public boolean verifyAuthorName (String expectedFullName) {
        if (!this.getAuthorName().contains(expectedFullName)) {
            System.out.println("Error! Article's author name does not contain expected value");
            return false;
        } else {
            return true;
        }
    }

    public int getViewsCounter() {
        return this.getElementValueToInt("faaArticleViews");
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
     * Get the text of the first article in the grid
     */
    public String getContent() {
        return this.getElementValue("faaArticleContent");
    }

    /**
     * Verify the content of the first article in the grid
     * @param articleText
     */
    public boolean verifyContent (String articleText) {
        if (!this.getContent().contains(articleText)) {
            System.out.println("Error! The article does not contain expected content");
            return false;
        } else {
            return true;
        }
    }

    /**
     * Get the title of the first article in the grid
     */
    public String getTitle() {
        return this.getElementValue("faaArticleTitle");
    }

    /**
     * Verify the title of the first article in the grid
     * @param articleTitle
     */
    public boolean verifyContainsTitleIsTrue (String articleTitle) {
        this.closeAlertPopup();
        Framework.getInstance().waitWhileLoad();
        if (!(this.getTitle().contains(articleTitle))) {
            System.out.println("Warning! The article title does not contain expected content");
            return false;
        } else {
            return true;
        }
    }

    public boolean verifyContainsTitleIsFalse (String articleTitle) {
        if (this.getTitle().contains(articleTitle)) {
            return false;
        } else {
            return true;
        }
    }

    public void clickComments() {
        this.getElement("faaCommentsIcon").click();
    }

    public void clickEditIcon() {
        this.getElement("faaEditIcon").click();
    }

    public void clickDeleteIcon() {
        this.getElement("faaDeleteIcon").click();
    }

    public void deleteArticle() {
        this.clickDeleteIcon();
        this.switchToAlertPopup().accept();
        Framework.getInstance().waitWhileLoad();
    }

    public int getCommentsCounter() {
        int counter = this.getElementValueToInt("faaCommentsCounter");
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
            System.out.println("Error! There are " + this.getCommentsCounter() + "comments on this article");
            return false;
        }
    }

    /*public boolean verifyAddCommentIsDisplayed() {
        return this.getElement("").isDisplayed();

    }*/

}
