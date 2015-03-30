package Tests;


import framework.Framework;
import framework.pages.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.Description;

public class UserMainPageTest {

    @Rule
    public ScreenShotRule screenShootRule = new ScreenShotRule(Framework.getInstance().getDriver());

    @Before
    public void openBrowser() {
        MainPage mainPage = Framework.getPage(MainPage.class);
        mainPage.open();
        Framework.getInstance().waitWhileLoad();
    }


}


