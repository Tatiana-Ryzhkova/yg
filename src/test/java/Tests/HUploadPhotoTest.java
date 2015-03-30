package Tests;


import framework.Framework;
import framework.pages.HomePage;
import framework.pages.LoginPage;
import framework.pages.MainPage;
import framework.pages.UserPage;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.server.handler.SendKeys;

import java.awt.*;
import java.io.IOException;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(JUnitParamsRunner.class)
public class HUploadPhotoTest {

    public static Collection testData() {
        return Framework.getInstance().getTestData("PhotoUploadTestData3.csv");
    }

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
    public void verifyLabels_TC_WChM1() {
        MainPage mainPage = Framework.getPage(MainPage.class);
        mainPage.userBlock.clickUploadBtn();
        mainPage.uploadArea.selectFile("600x800");
        assertTrue(mainPage.uploadArea.verifyLabels());
    }

    /*@Test
    public void uploadAndDeletePhoto_TC_WChM2() {
        MainPage mainPage = Framework.getPage(MainPage.class);
        mainPage.userBlock.clickUploadBtn();
        mainPage.uploadArea.selectFile("600x800")
                .deleteUploadedPhoto();
        assertTrue(mainPage.verifyCurrentPageUrl("https://preprod.yougossip.co.uk/"));
        assertTrue(!mainPage.pageSourceContainText("Your upload is now complete! Tell the world about it!"));
    }

    @Test
    @Parameters(method = "testData")
    public void uploadPhoto_TC_WChM3(String[] data) {
        MainPage mainPage = Framework.getPage(MainPage.class);
        mainPage.userBlock.clickUploadBtn();
        mainPage.uploadArea.selectFile(data[0])
                .addDescription(data[1])
                .addTags(data[2])
                .setFlags(data[3], data[4], data[5])
                .selectCharity(data[6])
                .clickSaveBtn();
        Framework.getInstance().sleep(5000);
        assertTrue(mainPage.pageSourceContainText("Your upload is now complete! Tell the world about it!"));
    }

    @Test
    public void verifyTags_TC_WChM4() {
        String firstTag = "FirstTag";
        MainPage mainPage = Framework.getPage(MainPage.class);
        mainPage.userBlock.clickUploadBtn();
        mainPage.uploadArea.selectFile("269")
                .addTag(firstTag)
                .addTags("Test upload photo: tags")
                .clickSaveBtn();
        UserPage userPage = Framework.getPage(UserPage.class);
        userPage.leftMenuArea.sortMostRecent();
        userPage.firstPhoto.clickEditIcon();
        assertTrue(userPage.editPhotoArea.getFirstTagText().equals(firstTag));
        userPage.editPhotoArea.removeAllTags()
                .saveChanges();
        assertTrue(userPage.pageSourceContainText("Photo updated successfully."));
        userPage.open();
        Framework.getInstance().sleep(5000);
        userPage.firstPhoto.clickEditIcon();
        assertTrue(!(userPage.editPhotoArea.tagPresent()));
    }

    @Test
    public void verifySharePopup_TC_WChM5() {
        MainPage mainPage = Framework.getPage(MainPage.class);
        mainPage.userBlock.clickUploadBtn();
        mainPage.uploadArea.selectFile("728x90")
                .addDescription("Autotest description")
                .setWatermarkFlag()
                .clickSaveBtn();
        UserPage userPage = Framework.getPage(UserPage.class);
        userPage.verifyUploadFBShare();
        userPage.verifyUploadTwitterShare();
        userPage.closeSharePopup();
    }

    @Test
    public void verifyDescription_TC_WChM5_2() {
        MainPage mainPage = Framework.getPage(MainPage.class);
        String description = mainPage.generateText();
        mainPage.userBlock.clickUploadBtn();
        mainPage.uploadArea.selectFile("728x90")
                .addDescription(description)
                .setWatermarkFlag()
                .clickSaveBtn();
        UserPage userPage = Framework.getPage(UserPage.class);
        userPage.leftMenuArea.sortMostRecent();
        assertTrue(userPage.firstPhoto.verifyDescription(description));
        String time = userPage.firstPhoto.getPhotoUploadDate();
        userPage.firstPhoto.clickDeleteIcon();
        userPage.confirm();
        Framework.getInstance().sleep(5000);
        assertTrue(!(userPage.firstPhoto.verifyDescription(description)));
    }

    @Test
    public void verifyMatureCheckbox_TC_WChM6() {
        MainPage mainPage = Framework.getPage(MainPage.class);
        mainPage.userBlock.clickUploadBtn();
        mainPage.uploadArea.selectFile("300x250")
                .setMatureFlag()
                .clickSaveBtn();
        UserPage userPage = Framework.getPage(UserPage.class);
        assertTrue(userPage.pageSourceContainText("Your upload is now complete! Tell the world about it!"));
        userPage.leftMenuArea.sortMostRecent();
        assertTrue(userPage.firstPhoto.isMatureTrue());
    }

    @Test
    public void verifyPrivateCheckbox_TC_WChM7() {
        String description = "Test to verify Private checkbox";
        MainPage mainPage = Framework.getPage(MainPage.class);
        mainPage.userBlock.clickUploadBtn();
        mainPage.uploadArea.selectFile("160x600")
                .addDescription(description)
                .setPrivateFlag()
                .clickSaveBtn();
        UserPage userPage = Framework.getPage(UserPage.class);
        userPage.leftMenuArea.sortMostRecent();
        assertTrue(userPage.firstPhoto.verifyDescription(description));
        userPage.leftMenuArea.clickOnLogo();
        mainPage.leftMenuArea.sortMostRecent();
        assertTrue(!(mainPage.firstPhoto.verifyDescription(description)));
    }

    @Test
    public void verifyEditPrivate_TC_WChM8() {
        MainPage mainPage = Framework.getPage(MainPage.class);
        String description = mainPage.generateText();
        mainPage.userBlock.clickUploadBtn();
        mainPage.uploadArea.selectFile("1234")
                .addDescription(description)
                .clickSaveBtn();
        UserPage userPage = Framework.getPage(UserPage.class);
        userPage.leftMenuArea.clickOnLogo();
        mainPage.leftMenuArea.sortMostRecent();
        assertTrue(mainPage.firstPhoto.verifyDescription(description));
        String uploadDate = mainPage.firstPhoto.getPhotoUploadDate();
        mainPage.firstPhoto.clickEditIcon();
        mainPage.editPhotoArea.makePrivate()
                .saveChanges();
        Framework.getInstance().sleep(5000);
        assertTrue(mainPage.pageSourceContainText("Photo updated successfully."));
        mainPage.leftMenuArea.clickOnLogo();
        assertTrue(!(mainPage.firstPhoto.getPhotoUploadDate().equals(uploadDate)));
    }*/

    @Test
    public void verifyCharity_TC_WChM9() {
        String charity = "Marie Curie Cancer Care";
        MainPage mainPage = Framework.getPage(MainPage.class);
        mainPage.userBlock.clickUploadBtn();
        mainPage.uploadArea.selectFile("403x403")
                .selectCharity(charity)
                .clickSaveBtn();
        UserPage userPage = Framework.getPage(UserPage.class);
        userPage.leftMenuArea.sortMostRecent();
        userPage.firstPhoto.clickEditIcon();
        assertTrue(userPage.editPhotoArea.verifySelectedCharity(charity));

    }

    @Test
    public void verifyUploadMore_TC_WChM10() {
        MainPage mainPage = Framework.getPage(MainPage.class);
        mainPage.userBlock.clickUploadBtn();
        mainPage.uploadArea.selectFile("drakon")
                .clickSaveBtn();
        UserPage userPage = Framework.getPage(UserPage.class);
        userPage.leftMenuArea.sortMostRecent();
        userPage.firstPhoto.clickEditIcon();

    }

}
