package Tests;


import framework.Framework;
import framework.pages.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(JUnitParamsRunner.class)
public class JArticlesPageTest {

    public static Collection testDataArticle() {
        return Framework.getInstance().getTestData("AddArticleTestData.csv");
    }

    private void loginWithDefaultCredentials () {
        HomePage homePage = Framework.getPage(HomePage.class);
        homePage.clickLoginBtn();
        LoginPage loginPage = Framework.getPage(LoginPage.class);
        loginPage.loginWithValidDefaultCredentials();
    }

    @Rule
    public ScreenShotRule screenShootRule = new ScreenShotRule(Framework.getInstance().getDriver());

    @Before
    public void openBrowser() {
        MainPage mainPage = Framework.getPage(MainPage.class);
        mainPage.open();
        Framework.getInstance().waitWhileLoad();
    }

    @Test
    public void verifyTitleValidation_TC_WChM15_1() {
        this.loginWithDefaultCredentials();
        MainPage mainPage = Framework.getPage(MainPage.class);
        mainPage.userBlock.clickMyArticles();
        UserPage userPage = Framework.getPage(UserPage.class);
        userPage.pressAddArticleBtn()
                .saveArticle();
        assertTrue(userPage.verifyPageContainsValidation("Title must not be empty"));
    }

    @Test
    public void verifyContentValidation_TC_WChM15_2() {
        this.loginWithDefaultCredentials();
        MainPage mainPage = Framework.getPage(MainPage.class);
        mainPage.userBlock.clickMyArticles();
        UserPage userPage = Framework.getPage(UserPage.class);
        userPage.pressAddArticleBtn()
                .saveArticle();
        assertTrue(userPage.verifyPageContainsValidation("Content must not be empty"));
    }

    @Test
    @Parameters(method = "testDataArticle")
    public void verifyValidation_TC_WChM15_3(String[] data) {
        this.loginWithDefaultCredentials();
        MainPage mainPage = Framework.getPage(MainPage.class);
        mainPage.userBlock.clickMyArticles();
        UserPage userPage = Framework.getPage(UserPage.class);
        userPage.pressAddArticleBtn()
                .addTitle(data[0])
                .selectCharity(data[1])
                .addTags(data[2])
                .addContentText(data[3])
                .saveArticle();
        assertTrue(userPage.verifyPageContainsValidation(data[4]));
    }

    @Test
    public void verifyAddingArticle_TC_WChM16() {
        this.loginWithDefaultCredentials();
        MainPage mainPage = Framework.getPage(MainPage.class);
        String title = mainPage.generateTextWithWord("Title ");
        String content = mainPage.generateTextWithWord("Article content  ");
        mainPage.userBlock.clickMyArticles();
        UserPage userPage = Framework.getPage(UserPage.class);
        userPage.pressAddArticleBtn()
                .addTitle(title)
                .addTags("Test tags with keysEnter")
                .addContentText(content);
        userPage.saveArticle();
        assertTrue(userPage.verifyPageContainsValidation("Article saved successfully"));
    }

    @Test
    public void verifyAddingArticleFromAdmin_TC_WChM17() {
        MainPage mainPage = Framework.getPage(MainPage.class);
        String title = mainPage.generateTextWithWord("Title ");
        String content = mainPage.generateTextWithWord("Article content  ");
        /**
         * Add an Article for autotestuser from Admin
         */
        AdminLogin adminLogin = Framework.getPage(AdminLogin.class);
        adminLogin.open();
        adminLogin.openAdminPage().openArticles();
        AdminArticles adminArticles = Framework.getPage(AdminArticles.class);
        adminArticles.addNewArticle(title, content, "autotestuser");
        assertTrue(adminArticles.verifyPageContainsValidation("Article saved successfully"));
        /**
         * Verify the Article is displayed on My Articles tab for autotestuser
         */
        mainPage.open();
        mainPage.visitorBlock.loginFromPopup("autotestuser", "autotestuser");
        mainPage.userBlock.clickMyArticles();
        UserPage userPage = Framework.getPage(UserPage.class);
        assertTrue(userPage.firstArticleArea.verifyContainsTitleIsTrue(title));
        /**
         * Delete the Article from Admin
         */
        AdminPage adminPage = Framework.getPage(AdminPage.class);
        adminPage.open();
        adminPage.clickArticles();
        adminArticles.deleteArticleByTitle(title);
        /**
         * Verify the Article is not displayed on My Articles tab for autotestuser
         */
        mainPage.open();
        mainPage.closeAlertPopup();
        mainPage.userBlock.clickMyArticles();
        assertTrue(userPage.firstArticleArea.verifyContainsTitleIsFalse(title));
    }

    @Test
    public void verifyEditingArticle_TC_WChM18() {
        this.loginWithDefaultCredentials();
        MainPage mainPage = Framework.getPage(MainPage.class);
        mainPage.userBlock.clickMyArticles();
        UserPage userPage = Framework.getPage(UserPage.class);
        String title = userPage.generateTextWithWord("Article Title ");
        String content = userPage.generateTextWithWord("Article Content ");
        userPage.createNewArticle(title, content);
        assertTrue(userPage.verifyPageContainsValidation("Article saved successfully"));
        userPage.firstArticleArea.clickEditIcon();
        String newTitle = userPage.generateTextWithWord("Article new Title ");
        userPage.changeTitle(newTitle).saveArticle();
        assertTrue(userPage.verifyPageContainsValidation("Article saved successfully"));
        assertTrue(userPage.firstArticleArea.verifyContainsTitleIsTrue(newTitle));
    }

    @Test
    public void verifyDeletionOfArticle_TC_WChM19() {
        this.loginWithDefaultCredentials();
        MainPage mainPage = Framework.getPage(MainPage.class);
        mainPage.userBlock.clickMyArticles();
        UserPage userPage = Framework.getPage(UserPage.class);
        String title = userPage.generateTextWithWord("Article Title ");
        String content = userPage.generateTextWithWord("Article Content ");
        userPage.createNewArticle(title, content);
        assertTrue(userPage.verifyPageContainsValidation("Article saved successfully"));
        userPage.firstArticleArea.deleteArticle();
        assertTrue(userPage.verifyPageContainsValidation("Article deleted successfully."));
        assertTrue(userPage.firstArticleArea.verifyContainsTitleIsFalse(title));
    }

    @Test
    public void verifySearchByTitle_TC_WChM20() {
        this.loginWithDefaultCredentials();
        MainPage mainPage = Framework.getPage(MainPage.class);
        mainPage.userBlock.clickMyArticles();
        UserPage userPage = Framework.getPage(UserPage.class);
        String titleForSearch = userPage.generateTextWithWord("Title");
        userPage.createNewArticle(titleForSearch, "test search verifying");
        String title = userPage.generateText();
        userPage.createNewArticle(title, "test search verifying");
        assertTrue(userPage.firstArticleArea.verifyContainsTitleIsTrue(title));
        userPage.makeSearch(titleForSearch);
        assertTrue(userPage.firstArticleArea.verifyContainsTitleIsTrue(titleForSearch));
    }

    @Test
    public void verifySearchByContent_TC_WChM21() {
        this.loginWithDefaultCredentials();
        MainPage mainPage = Framework.getPage(MainPage.class);
        mainPage.userBlock.clickMyArticles();
        UserPage userPage = Framework.getPage(UserPage.class);
        String contentForSearch = userPage.generateTextWithWord("content");
        userPage.createNewArticle("Test search verifying", contentForSearch);
        String content = userPage.generateText();
        userPage.createNewArticle("Test search verifying", content);
        assertTrue(userPage.firstArticleArea.verifyContent(content));
        userPage.makeSearch(contentForSearch);
        assertTrue(userPage.firstArticleArea.verifyContent(contentForSearch));
    }

    @Test
    public void verifySearchByTag_TC_WChM22() {
        this.loginWithDefaultCredentials();
        MainPage mainPage = Framework.getPage(MainPage.class);
        mainPage.userBlock.clickMyArticles();
        UserPage userPage = Framework.getPage(UserPage.class);
        String tagForSearch = userPage.generateTextWithWord("Tag");
        userPage.createArticle("Test search verifying by tag", tagForSearch);
        String tag = userPage.generateText();
        userPage.createArticle("New Title", tag);
        assertTrue(userPage.firstArticleArea.verifyContainsTitleIsTrue("New Title"));
        userPage.makeSearch(tagForSearch);
        assertTrue(userPage.firstArticleArea.verifyContainsTitleIsTrue("Test search verifying by tag"));
    }

    @Test
    public void verifySortingAlphabetical_WChM23() {
        this.loginWithDefaultCredentials();
        MainPage mainPage = Framework.getPage(MainPage.class);
        mainPage.userBlock.clickMyArticles();
        UserPage userPage = Framework.getPage(UserPage.class);
        String title = userPage.generateTextWithWord("Z-title");
        userPage.createNewArticle(title, "verify sorting of article");
        assertTrue(userPage.firstArticleArea.verifyContainsTitleIsTrue(title));
        userPage.sortBy("Alphabetical");
        assertTrue(userPage.firstArticleArea.verifyContainsTitleIsFalse(title));
    }

   @Test
    public void verifySortingByDateOldest_WChM24() {
        this.loginWithDefaultCredentials();
        MainPage mainPage = Framework.getPage(MainPage.class);
        mainPage.userBlock.clickMyArticles();
        UserPage userPage = Framework.getPage(UserPage.class);
        String title = userPage.generateTextWithWord("Date-title");
        userPage.createNewArticle(title, "verify sorting of article");
        userPage.firstArticleArea.getDateString();
        assertTrue(userPage.firstArticleArea.verifyContainsTitleIsTrue(title));
        userPage.sortBy("Oldest");
        assertTrue(userPage.firstArticleArea.verifyContainsTitleIsFalse(title));
    }

    @Test
    public void verifySortingByDateLatest_WChM25() {
        this.loginWithDefaultCredentials();
        MainPage mainPage = Framework.getPage(MainPage.class);
        mainPage.userBlock.clickMyArticles();
        UserPage userPage = Framework.getPage(UserPage.class);
        String title = userPage.generateTextWithWord("Z-title");
        userPage.createNewArticle(title, "verify sorting of article");
        assertTrue(userPage.firstArticleArea.verifyContainsTitleIsTrue(title));
        userPage.sortBy("Oldest");
        assertTrue(userPage.firstArticleArea.verifyContainsTitleIsFalse(title));
    }

    @Test
    public void verifySortingMostViewed_WChM26() {
        this.loginWithDefaultCredentials();
        MainPage mainPage = Framework.getPage(MainPage.class);
        mainPage.userBlock.clickMyArticles();
        UserPage userPage = Framework.getPage(UserPage.class);
        String title = userPage.generateTextWithWord("Z-title");
        userPage.createNewArticle(title, "verify sorting of article");
        assertTrue(userPage.firstArticleArea.verifyContainsTitleIsTrue(title));
        userPage.sortBy("Oldest");
        assertTrue(userPage.firstArticleArea.verifyContainsTitleIsFalse(title));
    }

    @Test
    public void verifySortingLeastViewed_WChM27() {
        this.loginWithDefaultCredentials();
        MainPage mainPage = Framework.getPage(MainPage.class);
        mainPage.userBlock.clickMyArticles();
        UserPage userPage = Framework.getPage(UserPage.class);
        String title = userPage.generateTextWithWord("Z-title");
        userPage.createNewArticle(title, "verify sorting of article");
        assertTrue(userPage.firstArticleArea.verifyContainsTitleIsTrue(title));
        userPage.sortBy("Oldest");
        assertTrue(userPage.firstArticleArea.verifyContainsTitleIsFalse(title));
    }


}
