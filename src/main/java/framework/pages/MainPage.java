package framework.pages;


import framework.Framework;
import framework.areas.*;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class MainPage extends Page {

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
    public LeftMenuUserAddition leftMenuUser = Framework.getArea(LeftMenuUserAddition.class);
    public UserBlockArea userBlock = Framework.getArea(UserBlockArea.class);
    public UploadArea uploadArea = Framework.getArea(UploadArea.class);
    public EditPhotoArea editPhotoArea = Framework.getArea(EditPhotoArea.class);

    public MainPage() {
        super();
        isSecure = true;
    }

    /**
     * Click on firstPhoto with description "testDescription"
     * (this description is being used for element locator in properties file)
     */
    public void openPhotoWithTestDescription() {
        this.getElement("mpPhotoByTestDescription").click();
    }

    /**
     * Stop trending flipping on specified user (1, 2 or 3)
     * @param stopOnTrendingPageNumber
     */
    public void stopTrendFlipping (int stopOnTrendingPageNumber) {
        switch(stopOnTrendingPageNumber) {
            case 2:
                this.getElement("mpPaginationSecond").click();
                break;
            case 3:
                this.getElement("mpPaginationThird").click();
                break;
            default:
                this.getElement("mpPaginationFirst").click();
        }
    }

    /**
     * Get full user name according to its trending position
     * @param trendingUserPosition
     * @return full user name
     */
    public String getTrendingUsername (int trendingUserPosition) {
        this.stopTrendFlipping(trendingUserPosition);
        String username;
        switch (trendingUserPosition) {
            case 2:
                username = this.getElementValue("mpTrendSecondUsername");
                break;
            case 3:
                username = this.getElementValue("mpTrendThirdUsername");
                break;
            default:
                username = this.getElementValue("mpTrendFirstUsername");
        }
        return username;
    }

    public boolean verifyFollowingIsTrue (int trendingUserPosition) {
        this.stopTrendFlipping(trendingUserPosition);
        boolean isFollowing;
        try {
            switch (trendingUserPosition) {
                case 2:
                    isFollowing = this.getElement("mpTrendSecondFollowingBtn").isDisplayed();
                    break;
                case 3:
                    isFollowing = this.getElement("mpTrendThirdFollowingBtn").isDisplayed();
                    break;
                default:
                    isFollowing = this.getElement("mpTrendFirstFollowingBtn").isDisplayed();
            }
        } catch (NoSuchElementException e) {
            isFollowing = false;
        }
        return isFollowing;
    }

    public boolean verifyFollowBtn(int trendingUserPosition) {
        boolean isPresent;
        try{
            switch (trendingUserPosition) {
                case 2:
                    isPresent = this.getElement("mpTrendSecondFollowBtn").isDisplayed();
                    break;
                case 3:
                    isPresent = this.getElement("mpTrendThirdFollowBtn").isDisplayed();
                    break;
                default:
                    isPresent = this.getElement("mpTrendFirstFollowBtn").isDisplayed();
            }
        } catch (NoSuchElementException e) {
            isPresent = false;
        }
        return isPresent;
    }

    public void followTrendUser (int trendingUserPosition) {
        this.stopTrendFlipping(trendingUserPosition);
        if(this.verifyFollowBtn(trendingUserPosition)) {
            switch (trendingUserPosition) {
                case 2:
                    this.getElement("mpTrendSecondFollowBtn").click();
                    break;
                case 3:
                    this.getElement("mpTrendThirdFollowBtn").click();
                    break;
                default:
                    this.getElement("mpTrendFirstFollowBtn").click();
            }
        } else if (this.verifyFollowingIsTrue(trendingUserPosition)){
            System.out.println("The user has been already followed.");
        } else {
            throw new NoSuchElementException("Error!!! There is no following section for this user.");
        }
    }

    public void unfollowTrendUser (int trendingUserPosition) {
        this.stopTrendFlipping(trendingUserPosition);
        if(this.verifyFollowingIsTrue(trendingUserPosition)) {
            switch (trendingUserPosition) {
                case 2:
                    this.getElement("mpTrendSecondFollowingBtn").click();
                    break;
                case 3:
                    this.getElement("mpTrendThirdFollowingBtn").click();
                    break;
                default:
                    this.getElement("mpTrendFirstFollowingBtn").click();
            }
        } else if(this.verifyFollowBtn(trendingUserPosition)) {
            System.out.println("The user has not been followed yet.");
        } else throw new NoSuchElementException("Error!!! There is no following section for this user.");
    }

}
