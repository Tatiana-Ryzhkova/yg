package Tests;


import framework.Framework;
import framework.pages.*;
import org.junit.*;
import org.openqa.selenium.Alert;


import static org.junit.Assert.*;

public class VisitorMainPageTest {

    @Rule
    public ScreenShotRule screenShootRule = new ScreenShotRule(Framework.getInstance().getDriver());

    @Before
    public void openBrowser() {
        MainPage mainPage = Framework.getPage(MainPage.class);
        mainPage.open();
        HomePage homePage = Framework.getPage(HomePage.class);
        homePage.clickExploreBtn();

    }

    @Test
    public void testLogo_TC_WChV12() {
        MainPage mainPage = Framework.getPage(MainPage.class);
        int photoViewsCounter = mainPage.firstPhoto.getViewsCounter();
        mainPage.firstPhoto.click();
        PhotoPage photoPage = Framework.getPage(PhotoPage.class);
        assertTrue(photoPage.verifyImgHasSource());
        photoPage.leftMenu.clickOnLogo();
        assertTrue(mainPage.verifyCurrentPageUrl("https://preprod.yougossip.co.uk/"));
        int newPhotoViewsCounter = mainPage.firstPhoto.getViewsCounter();
        assertTrue(newPhotoViewsCounter > photoViewsCounter);
    }

    @Test
    public void testLikePhoto_TC_WChV16() {
        MainPage mainPage = Framework.getPage(MainPage.class);
        mainPage.firstPhoto.like();
        Alert alert = mainPage.switchToAlertPopup();
        assertTrue(mainPage.getTextFromAlert(alert).contains("Please login to like photos"));
    }

    @Test
    public void testSignUpBtnLink_TC_WChV18() {
        MainPage mainPage = Framework.getPage(MainPage.class);
        mainPage.visitorBlock.clickSignUpBtn();
        SignUp signUp = Framework.getPage(SignUp.class);
        assertTrue(signUp.getContentText().contains("CREATE A YOUGOSSIP ACCOUNT"));
    }

    @Test
    public void testLoginPopup_TC_WChV19() {
        MainPage mainPage = Framework.getPage(MainPage.class);
        mainPage.visitorBlock.loginFromPopup("autotestuser", "autotestuser");
        assertTrue(mainPage.userBlock.getUsername().contains("Autotest User"));
    }

    /**
     * test is not valid. there is new design: there is no upload page, only upload popup
     */
/*    @Test
    public void testUploadBtn_TC_WChV20() {
        MainPage mainPage = Framework.getPage(MainPage.class);
        mainPage.visitorBlock.clickUploadBtn();
        assertTrue(mainPage.visitorBlock.getUsernameFldLabel().contains("Username"));
    }*/

    @Test
    public void testCommentPhoto_TC_WChV21() {
        MainPage mainPage = Framework.getPage(MainPage.class);
        if (mainPage.firstPhoto.getCommentsCounter() == 0) {
            mainPage.firstPhoto.clickComments();
            Alert alert = mainPage.switchToAlertPopup();
            assertTrue(mainPage.getTextFromAlert(alert).contains("Please login to comment"));
        } else {
            assertTrue(!mainPage.firstPhoto.verifyAddCommentIsDisplayed());
        }
    }



}
