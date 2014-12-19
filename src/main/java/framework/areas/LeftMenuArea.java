package framework.areas;


public class LeftMenuArea extends Area {

    public LeftMenuArea setDataIntoSearchField(String searchTerm) {
        this.setDataToElement("lmaSearchField", searchTerm);
        return this;
    }

    public void clickSearchBtn() {
        this.getElement("lmaSearchBtn").click();
    }

    public void makeSearch (String searchTerm) {
        this.setDataIntoSearchField(searchTerm).clickSearchBtn();
    }

    public void clickOnLogo() {
        this.getElement("lmaLogo").click();
    }

    public void clickLiveFeedBtn() {
        this.getElement("lmaLiveFeed").click();
    }

    public void clickArticlesBtn() {
        this.getElement("lmaArticles").click();
    }

    public LeftMenuArea clickFilterBtn() {
        this.getElement("lmaFilters").click();
        return this;
    }

    public void sortMostRecent() {
        this.clickFilterBtn().getElement("lmaSortRecent").click();
    }

    public void sortMostViewed() {
        this.clickFilterBtn().getElement("lmaSortViewed").click();
    }

    public LeftMenuArea openFilterTimePopup() {
        this.getElement("lmaSortTime").click();
        return this;
    }

    public void filterAllTime() {
        this.clickFilterBtn().openFilterTimePopup().getElement("lmaSortAllTime").click();
    }

    public void filterToday() {
        this.clickFilterBtn().openFilterTimePopup().getElement("lmaSortToday").click();
    }

    public void filterThisWeek() {
        this.clickFilterBtn().openFilterTimePopup().getElement("lmaSortThisWeek").click();
    }

    public void filterLastWeek() {
        this.clickFilterBtn().openFilterTimePopup().getElement("lmaSortLastWeek").click();
    }

    public void filterThisMonth() {
        this.clickFilterBtn().openFilterTimePopup().getElement("lmaSortThisMonth").click();
    }

    public void filterLastMonth() {
        this.clickFilterBtn().openFilterTimePopup().getElement("lmaSortLastMonth").click();
    }

    public void filterThisYear() {
        this.clickFilterBtn().openFilterTimePopup().getElement("lmaSortThisYear").click();
    }

    public LeftMenuArea openLeftMenu() {
        this.getElement("lmaMenuOpen").click();
        return this;
    }

    public void closeLeftMenu() {
        this.getElement("lmaMenuClose").click();
    }

    public void clickHowItWorks() {
        this.getElement("lmaHowItWorks").click();
    }

    public void clickCharities() {
        this.getElement("lmaCharities").click();
    }

    public void clickAboutUs() {
        this.getElement("lmaAboutUs").click();
    }

    public void clickPrivacyPolicy() {
        this.getElement("lmaPrivacyPolicy").click();
    }

    public void clickTermsOfService() {
        this.getElement("lmaTermsOfService").click();
    }

    public void clickAppStoreInMenu() {
        this.getElement("lmaAppStoreInMenu").click();
    }

    public void clickGooglePlayInMenu() {
        this.getElement("lmaGooglePlayInMenu").click();
    }

    public void clickFBIcon() {
        this.getElement("lmaFBIcon").click();
    }

    public void clickTwitterIcon() {
        this.getElement("lmaTwitterIcon").click();
    }

    /**
     * Additional Left menu items when browser is resizing
     */
    public void clickLiveFeedInMenu() {
        this.getElement("lmaLiveFeedInMenu").click();
    }

    public void clickArticlesInMenu() {
        this.getElement("lmaArticlesInMenu").click();
    }

    public LeftMenuArea setDataIntoSearchFieldInMenu (String searchTerm) {
        this.setDataToElement("lmaSearchFieldInMenu", searchTerm);
        return this;
    }

    public void clickSearchBtnInMenu() {
        this.getElement("lmaSearchBtnInMenu").click();
    }

    public void makeSearchFromMenu (String searchTerm) {
        this.setDataIntoSearchFieldInMenu(searchTerm).clickSearchBtnInMenu();
    }

    /**
     * Mobile application icons are shown on the left bottom when browser is resizing
     */
    public void clickAppStore() {
        this.getElement("lmaAppStore").click();
    }

    public void clickGooglePlay() {
        this.getElement("lmaGooglePlay").click();
    }
}
