package Tests;

import framework.Framework;
import framework.helpers.FBHelper;
import framework.pages.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.Alert;

import java.util.Set;

import static org.junit.Assert.*;

public class EFBLoginTest {

    public void fbLoginMethod(FBHelper fbInstance, String email, String password) {
        HomePage homePage = Framework.getPage(HomePage.class);

        FBHelper fb = fbInstance;
        String parentWindowHandle = fb.getWindowHandle();
        Set<String> oldWindowHandles = fb.getWindowHandles();

        homePage.clickFBLoginBtn();
        Set<String>  newWindowHandles = fb.getWindowHandles();
        for (String windowHandler : newWindowHandles) {
            if(!oldWindowHandles.contains(windowHandler)){
                fb.switchToWindow(windowHandler);
                break;
            }
        }

        fb.submitLoginForm(email, password);
        fb.switchToWindow(parentWindowHandle);
    }

    public AdminMembers openAdminMembersPage() {
        AdminLogin adminLoginPage = Framework.getPage(AdminLogin.class);
        Framework.getInstance().sleep(5000);
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

    /*@Test
    public void testFirstFBLogin_TC_WChA13() {
        FBHelper fb = new FBHelper();
        this.fbLoginMethod(fb, fb.newFBUsername(), fb.newFBPassword());

        FBTerms fbTerms = Framework.getPage(FBTerms.class);
        System.out.print("Confirmation popup: ");
        fbTerms.verifyElement("fbConfirmNo");
        fbTerms.confirmNo();

        MainPage mainPage = Framework.getPage(MainPage.class);
        System.out.print("Sign Up button: ");
        mainPage.visitorBlock.verifyElement("vbaSignUpBtn");

        this.openAdminMembersPage()
                .deleteUserFromDB(fb.newFBUsername());
    }

    @Test
    public void testFirstFBLogin_TC_WChA14() {
        FBHelper fb = new FBHelper();
        this.fbLoginMethod(fb, fb.newFBUsername(), fb.newFBPassword());

        FBTerms fbTerms = Framework.getPage(FBTerms.class);
        System.out.print("Confirmation popup: ");
        fbTerms.verifyElement("fbConfirmYes");
        fbTerms.confirmYes();

        WelcomePopup popup = Framework.getPage(WelcomePopup.class);
        System.out.print("Welcome popup: ");
        popup.verifyElement("welcomeCloseBtn");
        MainPage mainPage = Framework.getPage(MainPage.class);
        System.out.print("User full name: ");
        mainPage.userBlock.verifyUsername("Frederic", "Gerard");

        this.openAdminMembersPage()
                .deleteUserFromDB(fb.newFBUsername());
    }*/

    @Test
    public void testFBLogin_TC_WChA15() {
        FBHelper fb = new FBHelper();
        this.openAdminMembersPage()
                .enableUser(fb.fbTestUsername());
        Framework.getInstance().deleteCookies();
        MainPage mainPage = Framework.getPage(MainPage.class);
        mainPage.open();
        this.fbLoginMethod(fb, fb.fbTestUsername(), fb.fbTestPassword());
        System.out.print("User full name: ");
        mainPage.userBlock.verifyUsername("Sonia", "Krivolka");
    }

    @Test
    public void testFBLogin_TC_WChA16() {
        FBHelper fb = new FBHelper();
        this.openAdminMembersPage()
                .disableUser(fb.fbTestUsername());
        Framework.getInstance().deleteCookies();

        MainPage mainPage = Framework.getPage(MainPage.class);
        mainPage.open();

        this.fbLoginMethod(fb, fb.fbTestUsername(), fb.fbTestPassword());

        String windowHandle = fb.getWindowHandle();
        Alert alertPopup = mainPage.switchToAlertPopup();
        assertTrue(mainPage.getTextFromAlert(alertPopup)
                .contains("Authentication Unsuccessful!"));
        mainPage.acceptAlertPopup(alertPopup);
        Framework.getInstance().switchBackToWindow(windowHandle);

        this.openAdminMembersPage()
                .enableUser(fb.fbTestUsername());
    }

}
