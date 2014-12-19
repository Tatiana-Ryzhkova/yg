package Tests;


import framework.Framework;
import framework.pages.HomePage;
import framework.pages.LoginPage;
import framework.pages.MainPage;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.awt.*;
import java.util.Collection;

@RunWith(JUnitParamsRunner.class)
public class UploadPhotoTest {

    public static Collection testData() {
        return Framework.getInstance().getTestData("PhotoUploadTestData2.csv");
    }

    @Rule
    public ScreenShotRule screenShootRule = new ScreenShotRule(Framework.getInstance().getDriver());

    @Before
    public void openBrowser() {
        MainPage mainPage = Framework.getPage(MainPage.class);
        mainPage.open();
    }

    @Test
    @Parameters(method = "testData")
    public void testUploadPhotoAreaMethods1(String[] data) {
        HomePage homePage = Framework.getPage(HomePage.class);
        homePage.clickLoginBtn();
        LoginPage loginPage = Framework.getPage(LoginPage.class);
        loginPage.loginWithValidDefaultCredentials();
        MainPage mainPage = Framework.getPage(MainPage.class);
        mainPage.userBlock.clickUploadBtn();
        mainPage.uploadArea.selectFile(data[0])
                .addDescription(data[1])
                .addTags(data[2])
                .setFlags(data[3], data[4], data[5])
                .clickSaveBtn();
        System.out.println("test");
    }

    @Test
    public void testUploadPhotoAreaMethods2() {
        HomePage homePage = Framework.getPage(HomePage.class);
        homePage.clickLoginBtn();
        LoginPage loginPage = Framework.getPage(LoginPage.class);
        loginPage.loginWithValidDefaultCredentials();
        MainPage mainPage = Framework.getPage(MainPage.class);
        mainPage.userBlock.clickUploadBtn();
        mainPage.uploadArea.selectFile("woman")
                .addTags("tag1 tag2 tag3 tag4 tag5")
                .removeFirstTag()
                .setWatermarkFlag()
                .clickSaveBtn();
        System.out.println("test");
    }
}
