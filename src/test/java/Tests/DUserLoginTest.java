package Tests;

import framework.Framework;
import framework.pages.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import java.util.Collection;


@RunWith(JUnitParamsRunner.class)
public class DUserLoginTest {

    public static Collection testData() {
        return Framework.getInstance().getTestData("LoginTestData.csv");
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
    public void testExistentUserLogin_TC_WChA18() {
        HomePage homePage = Framework.getPage(HomePage.class);
        homePage.clickLoginBtn();

        LoginPage loginPage = Framework.getPage(LoginPage.class);
        loginPage.loginWithValidDefaultCredentials();

        MainPage mainPage = Framework.getPage(MainPage.class);
        try {
            System.out.print("User full name: ");
            mainPage.userBlock.verifyUsername("Autotest", "User");
        } catch (Exception e) {
            System.out.println("Error!! User did not log in");
        }
    }

    @Test
    @Parameters(method = "testData")
    public void testLoginWithInvalidCredentials_TC_WChA19_20 (String[] data) {
        HomePage homePage = Framework.getPage(HomePage.class);
        homePage.clickLoginBtn();

        LoginPage loginPage = Framework.getPage(LoginPage.class);
        loginPage.loginWithInvalidCredentials(data[0], data[1], data[2]);
    }

    @Test
    public void testUserLoginFromPopupInvalidData_TC_WChA21() {
        HomePage homePage = Framework.getPage(HomePage.class);
        homePage.clickExploreBtn();

        MainPage mainPage = Framework.getPage(MainPage.class);
        mainPage.visitorBlock.loginFromPopup("invalidTestUsername", "invalidTestPassword");

        LoginPage loginPage = Framework.getPage(LoginPage.class);
        assertTrue(loginPage.getUsernameValidationMessage()
                .contains("User with the supplied identity could not be found."));
    }

    @Test
    public void testDisabledUserLogin_TC_WChA22() {
        LoginPage loginPage = Framework.getPage(LoginPage.class);
        String testEmail = loginPage.getTestUserEmail();
        String testUsername = loginPage.getTestUsername();
        String testPassword = loginPage.getTestPassword();

        AdminLogin adminLoginPage = Framework.getPage(AdminLogin.class);
        adminLoginPage.open();
        adminLoginPage
                .openAdminPage()
                .openMembers()
                .disableUser(testEmail);

        MainPage mainPage = Framework.getPage(MainPage.class);
        mainPage.open();
        mainPage.visitorBlock.loginFromPopup(testUsername, testPassword);

        assertTrue(loginPage.getUsernameValidationMessage().contains("User was temporary blocked."));

        AdminPage adminPage = Framework.getPage(AdminPage.class);
        adminPage.open();
        adminPage.openMembers().enableUser(testEmail);
    }
}
