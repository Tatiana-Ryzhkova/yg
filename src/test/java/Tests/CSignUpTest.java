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

import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class CSignUpTest {

    public static Collection testDataEmail() {
        return Framework.getInstance().getTestData("SignUpEmailTestData.csv");
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
    @Parameters(method = "testDataEmail")
    public void SignUp_verifyEmail_TC_WChA3_4(String[] data) {
        HomePage homePage = Framework.getPage(HomePage.class);
        homePage.clickSignUpBtn();
        SignUp signUp = Framework.getPage(SignUp.class);
        signUp.byDefaultWithEmail(data[0]);
        assertTrue(signUp.getValidationMessage("suForEmail")
                .equals(data[1]));
    }

    /*@Test
    public void SignUp_verifyEmail_TC_WChA5() {
        HomePage homePage = Framework.getPage(HomePage.class);
        homePage.clickSignUpBtn();
        SignUp signUp = Framework.getPage(SignUp.class);

        String email = signUp.generateEmail();
        signUp.byDefaultWithEmail(email);

        WelcomePopup welcomePopup = Framework.getPage(WelcomePopup.class);
        welcomePopup.close();

        MainPage mainPage = Framework.getPage(MainPage.class);
        mainPage.userBlock.clickLogout();
        mainPage.visitorBlock.clickSignUpBtn();

        signUp.byDefaultWithEmail(email);
        assertTrue(signUp.getValidationMessage("suForEmail")
                .contains("This email already exists."));

    }*/

    @Test
    public void SignUp_verifyUsername_TC_WChA6() {
        HomePage homePage = Framework.getPage(HomePage.class);
        homePage.clickSignUpBtn();
        SignUp signUp = Framework.getPage(SignUp.class);
        signUp.byDefaultWithUsername("qa1");

        assertTrue(signUp.getValidationMessage("suForUsername")
                .contains("Username must be at least 4 characters long"));

    }

    @Test
    public void SignUp_verifyUsername_TC_WChA7() {
        HomePage homePage = Framework.getPage(HomePage.class);
        homePage.clickSignUpBtn();
        SignUp signUp = Framework.getPage(SignUp.class);

        String username = signUp.generateUsername();
        signUp.byDefaultWithUsername(username);

        AdminLogin adminLoginPage = Framework.getPage(AdminLogin.class);
        adminLoginPage.open();
        adminLoginPage.openAdminPage()
                .openMembers()
                .verifyElementInTheGrid(username);

        MainPage mainPage = Framework.getPage(MainPage.class);
        mainPage.open();
        mainPage.userBlock.clickLogout();

        mainPage.visitorBlock.clickSignUpBtn();

        signUp.byDefaultWithUsername(username);

        assertTrue(signUp.getValidationMessage("suForUsername")
                .contains("This username already exists."));
    }

    @Test
    public void SignUp_verifyPassword_TC_WChA8() {
        HomePage homePage = Framework.getPage(HomePage.class);
        homePage.clickSignUpBtn();
        SignUp signUpPage = Framework.getPage(SignUp.class);

        signUpPage
                .setFullName()
                .fillEmail()
                .setBirthdayDate()
                .fillUsername()
                .fillPassword("12345")
                .fillConfirmPassword("12345");
        //signUpPage.flagTermsAndCondition();
        signUpPage.submit();

        assertTrue(signUpPage.getValidationMessage("suForPassword")
                .contains("Password must be at least 6 characters long"));

    }

    @Test
    public void SignUp_verifyPassword_TC_WChA9() {
        HomePage homePage = Framework.getPage(HomePage.class);
        homePage.clickSignUpBtn();
        SignUp signUpPage = Framework.getPage(SignUp.class);

        signUpPage
                .setFullName()
                .fillEmail()
                .setBirthdayDate()
                .fillUsername()
                .fillPassword("123456")
                .fillConfirmPassword("012345");
        //signUpPage.flagTermsAndCondition();
        signUpPage.submit();

        assertTrue(signUpPage.getValidationMessage("suForConfirm")
                .contains("Confirm must be the same as Password"));

    }

    /*@Test
    public void SignUp_verifySignUP_TC_WChA10() {
        HomePage homePage = Framework.getPage(HomePage.class);
        homePage.clickSignUpBtn();
        SignUp signUpPage = Framework.getPage(SignUp.class);

        signUpPage
                .setFullName()
                .fillEmail()
                .setBirthdayDate()
                .fillUsername()
                .fillPasswordFields();
        //signUpPage.flagTermsAndCondition();
        signUpPage.submit();
        signUpPage.checkSuccess();

        WelcomePopup welcomePopup = Framework.getPage(WelcomePopup.class);
        welcomePopup.clickOutPopup();
        MainPage mainPage = Framework.getPage(MainPage.class);
        System.out.print("Content: ");
        mainPage.verifyElement("mpContent");
    }

    @Test
    public void SignUp_verifySignUP_TC_WChA11() {
        HomePage homePage = Framework.getPage(HomePage.class);
        homePage.clickSignUpBtn();
        SignUp signUpPage = Framework.getPage(SignUp.class);

        signUpPage
                .setFullName()
                .fillEmail()
                .setBirthdayDate()
                .fillUsername()
                .fillPasswordFields();
        //signUpPage.flagTermsAndCondition();
        signUpPage.submit();
        signUpPage.checkSuccess();

        WelcomePopup welcomePopup = Framework.getPage(WelcomePopup.class);
        // is not valid now
        // welcomePopup.tryUpload();
        welcomePopup.clickOutPopup();
        MainPage mainPage = Framework.getPage(MainPage.class);
        System.out.print("Upload button: ");
        mainPage.userBlock.verifyElement("ubaUploadBtn");
    }*/
}
