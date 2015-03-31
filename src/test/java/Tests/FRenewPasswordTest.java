package Tests;


import framework.Framework;
import framework.helpers.EmailHelper;
import framework.pages.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class FRenewPasswordTest {

    public static Collection testData() {
        return Framework.getInstance().getTestData("ForgotPasswordTestData.csv");
    }

    public AdminMembers openAdminMembersPage() {
        AdminLogin adminLoginPage = Framework.getPage(AdminLogin.class);
        adminLoginPage.open();
        return adminLoginPage.openAdminPage().openMembers();

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
    public void testFBUserEmail_TC_WChA23() {
        HomePage homePage = Framework.getPage(HomePage.class);
        homePage.clickLoginBtn();

        LoginPage loginPage = Framework.getPage(LoginPage.class);
        loginPage.clickForgotPassword();

        ForgotPage forgotPage = Framework.getPage(ForgotPage.class);
        forgotPage
                .enterFBUserEmail()
                .clickSendBtn();

        assertTrue(forgotPage.getValidationMessage()
                .contains("Oops, your email soni4kad34@meta.ua used for Facebook signup. " +
                        "Please, follow to login page & auth by your Facebook account."));
    }

    @Test
    public void testDeletedUserEmail_TC_WChA24() {
        SignUp signUp = Framework.getPage(SignUp.class);
        String testEmail = signUp.generateEmail();

        HomePage homePage = Framework.getPage(HomePage.class);
        homePage.clickSignUpBtn();
        signUp.byDefaultWithEmail(testEmail);

        openAdminMembersPage()
                .deleteUserFromDB(testEmail);

        ForgotPage forgotPage = Framework.getPage(ForgotPage.class);
        forgotPage.open();
        forgotPage.submitEmail(testEmail);

        assertTrue(forgotPage.getValidationMessage().contains("No member found."));

    }

    @Test
    @Parameters(method = "testData")
    public void testInvalidEmail_TC_WChA25_26 (String[] data) {
        HomePage homePage = Framework.getPage(HomePage.class);
        homePage.clickLoginBtn();

        LoginPage loginPage = Framework.getPage(LoginPage.class);
        loginPage.clickForgotPassword();

        ForgotPage forgotPage = Framework.getPage(ForgotPage.class);
        forgotPage.submitEmail(data[0]);

        assertTrue(forgotPage.getValidationMessage().contains(data[1]));
    }

    @Test
    public void testValidUserEmail_TC_WChA27() {
        HomePage homePage = Framework.getPage(HomePage.class);
        homePage.clickSignUpBtn();

        SignUp signUp = Framework.getPage(SignUp.class);
        String testEmail = signUp.generateEmail();
        String testUsername = signUp.generateUsername();
        signUp.byDefault(testEmail, testUsername);

        FeaturedMembers featuredMembers = Framework.getPage(FeaturedMembers.class);
        featuredMembers.clickDoneBtn().close();

        MainPage mainPage = Framework.getPage(MainPage.class);
        mainPage.userBlock.clickLogout();

        ForgotPage forgotPage = Framework.getPage(ForgotPage.class);
        forgotPage.open();
        Framework.getInstance().sleep(5000);
        forgotPage.submitEmail(testEmail);
        forgotPage.verifyElement("rpValidationSent");

        EmailHelper helper = new EmailHelper();
        String tempPasswd = helper.getTempPassword();
        Framework.getInstance().sleep(5000);
        mainPage.open();
        helper.acceptAlert(mainPage);
        Framework.getInstance().sleep(500);
        mainPage.visitorBlock.loginFromPopup(testUsername, tempPasswd);

        System.out.print("Username: ");
        mainPage.userBlock.verifyElement("ubaUsername");
        assertTrue(mainPage.userBlock.getUsername().contains("Doe"));

    }

    @Test
    public void testDisabledUserEmail_TC_WChA28() {
        HomePage homePage = Framework.getPage(HomePage.class);
        homePage.clickSignUpBtn();

        SignUp signUp = Framework.getPage(SignUp.class);
        String testEmail = signUp.generateEmail();
        String testUsername = signUp.generateUsername();
        signUp.byDefault(testEmail, testUsername);

        FeaturedMembers featuredMembers = Framework.getPage(FeaturedMembers.class);
        featuredMembers.clickDoneBtn().close();

        MainPage mainPage = Framework.getPage(MainPage.class);
        mainPage.userBlock.clickLogout();

        openAdminMembersPage()
                .disableUser(testEmail);

        ForgotPage forgotPage = Framework.getPage(ForgotPage.class);
        forgotPage.open();

        forgotPage.submitEmail(testEmail);
        Framework.getInstance().sleep(3000);
        System.out.print("Validation mesage: ");
        forgotPage.verifyElement("rpValidation");
        assertTrue(forgotPage.getValidationMessage().contains("User was temporary blocked."));

        AdminMembers members = Framework.getPage(AdminMembers.class);
        members.open();
        members.enableUser(testEmail);

    }


}
