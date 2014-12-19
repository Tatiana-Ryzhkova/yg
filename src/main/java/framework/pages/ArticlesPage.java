package framework.pages;


import framework.Framework;
import framework.areas.*;

public class ArticlesPage extends Page {

    /**
     * Areas for visitor Articles page
     */
    public VisitorBlockArea visitorBlock = Framework.getArea(VisitorBlockArea.class);
    public LeftMenuArea leftMenuArea = Framework.getArea(LeftMenuArea.class);
    /**
     * Areas for user Articles page
     */
    public LeftMenuUserAddition leftMenuUser = Framework.getArea(LeftMenuUserAddition.class);
    public UserBlockArea userBlock = Framework.getArea(UserBlockArea.class);

    public ArticlesPage() {
        super();
        isSecure = true;
    }

    /**
     * Click "Read more on the first article in the grid
     */
    public void readMore() {
        this.getElement("apReadMore").click();
    }

    /**
     * Like the first article in the grid
     */
    public void like() {
        this.getElement("apArticleLikeIcon").click();
    }

    public int getLikesCounter() {
        int counter = this.getElementValueToInt("apArticleLikeCounter");
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
    public String getDate() {
        return this.getElementValue("apArticleDate");
    }

    /**
     * Get the name of the author of the first article in the grid
     */
    public String getAuthorName() {
        return this.getElementValue("apArticleAuthor");
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
        return this.getElementValueToInt("apArticleViews");
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
        return this.getElementValue("apArticle");
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

    public void clickComments() {
        this.getElement("apCommentsIcon").click();
    }

    public int getCommentsCounter() {
        int counter = this.getElementValueToInt("apCommentsCounter");
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

    public boolean verifyAddCommentIsDisplayed() {
        return this.getElement("apAddCommentArea").isDisplayed();
    }



}
