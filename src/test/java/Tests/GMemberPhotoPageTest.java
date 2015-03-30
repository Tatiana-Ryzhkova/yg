package Tests;

import framework.Framework;
import framework.pages.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GMemberPhotoPageTest {

    @Rule
    public ScreenShotRule screenShootRule = new ScreenShotRule(Framework.getInstance().getDriver());

    @Before
    public void openBrowser() {
        MainPage mainPage = Framework.getPage(MainPage.class);
        mainPage.open();
        Framework.getInstance().waitWhileLoad();
        HomePage homePage = Framework.getPage(HomePage.class);
        homePage.clickLoginBtn();
        LoginPage loginPage = Framework.getPage(LoginPage.class);
        loginPage.loginWithValidDefaultCredentials();
    }

    @Test
    public void verifySearchByDescription_TC_WChM11() {
        MainPage mainPage = Framework.getPage(MainPage.class);
        String description = mainPage.generateText();
        mainPage.userBlock.clickUploadBtn();
        mainPage.uploadArea.selectFile("images")
                .addDescription(description)
                .clickSaveBtn();
        UserPage userPage = Framework.getPage(UserPage.class);
        Framework.getInstance().sleep(3000);
        userPage.closeSharePopup();
        userPage.leftMenuArea.setDataIntoSearchField(description);
        userPage.leftMenuArea.clickSearchBtn();
        assertTrue(userPage.firstPhoto.verifyDescription(description));
    }

    @Test
     public void verifySortingOldest_TC_WChM12() {
        UserPage userPage = Framework.getPage(UserPage.class);
        userPage.open();
        userPage.firstPhoto.click();
        PhotoPage photoPage = Framework.getPage(PhotoPage.class);
        photoPage.userBlock.clickMyPhotos();
        userPage.userBlock.clickUploadBtn();
        userPage.uploadArea.selectFile("01")
                .addDescription("Test")
                .clickSaveBtn();
        int views = userPage.firstPhoto.getViewsCounter();
        userPage.leftMenuArea.clickMostViewed();
        assertTrue(userPage.firstPhoto.getViewsCounter() > views);
    }

    @Test
    public void editPhotoDescription_TC_WChM13() {
        MainPage mainPage = Framework.getPage(MainPage.class);
        String description = mainPage.generateText();
        mainPage.userBlock.clickUploadBtn();
        mainPage.uploadArea.selectFile("1234.jpg")
                .addDescription(description)
                .clickSaveBtn();
        UserPage userPage = Framework.getPage(UserPage.class);
        userPage.closeSharePopup();
        userPage.leftMenuArea.sortMostRecent();
        assertTrue(userPage.firstPhoto.getDescription().contains(description));
        userPage.firstPhoto.clickEditIcon();
        String newDescription = userPage.generateText();
        userPage.editPhotoArea.deleteDescription()
                .addDescription(newDescription)
                .saveChanges();
        userPage.leftMenuArea.clickOnLogo();
        mainPage.leftMenuArea.sortMostRecent();
        assertTrue(mainPage.firstPhoto.getDescription().contains(newDescription));
    }

    @Test
    public void editPhotoTag_TC_WChM14() {
        MainPage mainPage = Framework.getPage(MainPage.class);
        String description = mainPage.generateText();
        String tag = mainPage.generateTextWithWord("tag");
        mainPage.userBlock.clickUploadBtn();
        mainPage.uploadArea.selectFile("sword")
                .addDescription(description)
                .clickSaveBtn();
        UserPage userPage = Framework.getPage(UserPage.class);
        assertTrue(userPage.pageSourceContainText("Your upload is now complete! Tell the world about it!"));
        AdminLogin adminLogin = Framework.getPage(AdminLogin.class);
        adminLogin.open();
        adminLogin.openAdminPage()
                .searchPhoto(description)
                .clickFirstPhotoEdit()
                .addTag(tag)
                .saveChanges();
        mainPage.open();
        assertTrue(mainPage.firstPhoto.getDescription().contains(description));
        mainPage.firstPhoto.clickEditIcon();
        assertTrue(mainPage.editPhotoArea.getFirstTagText().contains(tag));
    }

}
