package Tests;


import framework.Framework;
import framework.areas.Area;
import framework.areas.RCArea;
import framework.pages.*;
import org.junit.*;

public class HomePageTest {

    @Rule
    public ScreenShotRule screenShootRule = new ScreenShotRule(Framework.getInstance().getDriver());

    @Before
    public void openBrowser() {
        MainPage mainPage = Framework.getPage(MainPage.class);
        mainPage.open();
    }

    @Test
    public void verifyText_TC_WChV1() {
        HomePage homePage = Framework.getPage(HomePage.class);
        homePage
                .verifyTextIsPresent("BEING A PHILANTHROPIST HAS NEVER BEEN SO EASY!")
                .verifyTextIsPresent("Use YouGossip to share your photo")
                .verifyTextIsPresent("like you normally do")
                .verifyTextIsPresent("Only this time, every picture you upload and share, " +
                        "you raise money for your favourite charity!")
                .verifyHPElement("hpSmileIcon")
                .verifyHPElement("hpMessageIcon")
                .verifyHPElement("hpPhotoIcon")
                .verifyHPElement("hpAnswerIcon")
                .verifyHPElement("hpLockIcon");
    }

    @Test
    public void verifyAppleStoreLink_TC_WChV2() {
        HomePage homePage = Framework.getPage(HomePage.class);
        System.out.print("Verify link to the appStore: ");
        homePage.verifyLink("hpAppStore", "https://itunes.apple.com/us/app/yougossip/id571128312?mt=8");
    }

    @Test
    public void verifyGoogleAppLink_TC_WChV3() {
        HomePage homePage = Framework.getPage(HomePage.class);
        System.out.print("Verify link to the googlePlay: ");
        homePage.verifyLink("hpGooglePlay", "https://play.google.com/store/apps/details?id=com.yougossip");
    }

    @Test
    public void verifySignUpBtn_TC_WChV4() {
        HomePage homePage = Framework.getPage(HomePage.class);
        System.out.print("Verify SignUp button link: ");
        homePage.verifyLink("hpSignUpBtn", "https://preprod.yougossip.co.uk/members/signup");
    }

    @Test
    public void verifyLoginBtn_TC_WChV5() {
        HomePage homePage = Framework.getPage(HomePage.class);
        System.out.print("Verify Login button link: ");
        homePage.verifyLink("hpLoginBtn", "https://preprod.yougossip.co.uk/login");
    }

    @Test
    public void verifyExploreBtn_TC_WChV6() {
        HomePage homePage = Framework.getPage(HomePage.class);
        System.out.print("Verify Explore button link: ");
        homePage.verifyLink("hpExploreBtn", "https://preprod.yougossip.co.uk/");
    }

    @Test
    public void verifyFacebookSignUpBtn_TC_WChV7() {
        HomePage homePage = Framework.getPage(HomePage.class);
        System.out.print("Verify Facebook button: ");
        homePage.verifyHPElement("hpFacebookBtn");
    }


}
