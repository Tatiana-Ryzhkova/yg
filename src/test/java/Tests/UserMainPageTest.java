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
    }

    /*@Test
    public void test() {
        AdminPage a = Framework.getPage(AdminPage.class);
        a.open();
        AdminMembers m = Framework.getPage(AdminMembers.class);
        m.selectChangeStatusBox("okorobeinyk+1418211439068@hypnorep.com");
        System.out.println("Enable option path: " + m.getEnableOptionPath("okorobeinyk+1418211439068@hypnorep.com"));
        System.out.println("Disable option path: " + m.getDisableOptionPath("okorobeinyk+1418211439068@hypnorep.com"));

    }*/
}


