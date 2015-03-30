package framework.pages;

import framework.Framework;
import framework.areas.*;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;


public class UserPage extends  Page {

    public CookiesAcceptArea cookiesAcceptArea = Framework.getArea(CookiesAcceptArea.class);
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
    public UploadArea uploadArea = Framework.getArea(UploadArea.class);
    public EditPhotoArea editPhotoArea = Framework.getArea(EditPhotoArea.class);
    public FirstArticleArea firstArticleArea = Framework.getArea(FirstArticleArea.class);

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

    public void closeSharePopup() {
        this.getElement("upCloseShare").click();
    }

    public void verifyUploadFBShare() {
        System.out.print("FBShare icon: ");
        this.verifyElement("upFBShare");
    }

    public void verifyUploadTwitterShare() {
        System.out.print("TwitterShare icon: ");
        this.verifyElement("upTwitterShare");
    }

    public UserPage createNewArticle (String title, String content) {
        this.pressAddArticleBtn()
                .addTitle(title)
                .addContentText(content)
                .saveArticle();
        return  this;
    }

    public UserPage createArticle (String title, String tag) {
        this.pressAddArticleBtn()
                .addTitle(title)
                .addTag(tag)
                .addContentText("content of the article")
                .saveArticle();
        return  this;
    }

    public UserPage openArticlesTab() {
        this.getElement("upArticles").click();
        return this;
    }

    public UserPage pressAddArticleBtn() {
        this.getElement("upAddArticleBtn").click();
        return this;
    }

    public void saveArticle() {
        this.getElement("upFormAddArticleBtn").click();
        Framework.getInstance().waitWhileLoad();
    }

    public UserPage addContentText (String content) {
        this.switchToFrameByElement("upTextFrame");
        this.getElement("upBodyInFrame").sendKeys(content);
        this.switchBackToDefaultContent();
        return this;
    }

    public UserPage addTitle(String titleText) {
        Framework.getInstance().waitWhileLoad();
        this.getElement("upTitleField").sendKeys(titleText);
        return this;
    }

    public UserPage changeTitle(String newTitle) {
        Framework.getInstance().waitWhileLoad();
        this.getElement("upTitleField").clear();
        this.getElement("upTitleField").sendKeys(newTitle);
        return this;
    }

    public Select getCharityDropdown() {
        Select dropdown = new Select(this.getElement("upCharityBox"));
        return dropdown;
    }

    public UserPage selectCharity (String charityFullName) {
        if (this.isCharityNamePresent(charityFullName)) {
            this.getCharityDropdown().selectByVisibleText(charityFullName);
        } else throw new Error("There is no such charity in the dropdown");
        return this;
    }

    public List<WebElement> getCharityOptions() {

        List<WebElement> options = this.getCharityDropdown().getOptions();
        return options;
    }

    public ArrayList<String> getCharitiesNameList() {
        ArrayList<String> charitiesList = new ArrayList<>();
        for (WebElement option : this.getCharityOptions()) {
            if (option.getText()!= null) {
                charitiesList.add(option.getText());
            } else {
                Framework.getInstance().waitWhileLoad(1000);
                if (option.getText() == null) {
                    throw new Error("Element is not visible");
                }
            }
        }
        return charitiesList;
    }

    public boolean isCharityNamePresent(String charityName) {
        if (this.getCharitiesNameList().contains(charityName)) {
            return true;
        }
        return false;
    }

    public UserPage addTag (String tag) {
        this.getElement("upTagsInput").sendKeys(tag);
        this.getElement("upTagsInput").sendKeys(Keys.ENTER);
        return this;
    }

    public UserPage addTags (String tags) {
        String[] tagsArray = tags.split(" ");
        for (int i = 0; i < tagsArray.length; i ++) {
            addTag(tagsArray[i]);
        }
        return this;
    }

    public void makeSearch (String textToSearch) {
        this.getElement("upArticleSearchField").sendKeys(textToSearch);
        this.getElement("upArticleSearchIcon").click();
        Framework.getInstance().waitWhileLoad();
    }

    public UserPage sortBy (String AlphabetOrNewestOrOldestOrMostViewedOrLeastViewed) {
        Select select = new Select(this.getElement("upSortingDropdown"));
        switch (AlphabetOrNewestOrOldestOrMostViewedOrLeastViewed) {
            case "Alphabetical":
                select.selectByValue("name_asc");
                System.out.println("Selected: Alphabetical");
                break;
            case "Newest":
                select.selectByValue("postdate_desc");
                System.out.println("Selected: Newest");
                break;
            case "Oldest":
                select.selectByValue("postdate_asc");
                System.out.println("Selected: Oldest");
                break;
            case "MostViewed":
                select.selectByValue("views_desc");
                System.out.println("Selected: MostViewed");
                break;
            case "LeastViewed":
                select.selectByValue("views_asc");
                System.out.println("Selected: LeastViewed");
                break;
            default:
                select.selectByValue("postdate_desc");
                System.out.println("Selected: Newest");
        }
        return this;
    }



}
