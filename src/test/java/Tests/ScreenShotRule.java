package Tests;

import framework.Framework;
import org.apache.commons.io.FileUtils;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenShotRule extends TestWatcher {

    private WebDriver browser;
    protected final String scrFolderPathname = "target/screens";

    public ScreenShotRule(WebDriver browser) {
        this.browser =  browser;
    }

    @Override
    protected void failed(Throwable e, Description description) {
        String mName = description.getDisplayName();
        System.out.println("FAILED!! TEST: " + mName);
        int index = mName.indexOf("(");
        String methodName = description.getDisplayName().substring(0, index);
        this.saveScreenshot(methodName);
    }

    @Override
    protected void finished(Description description) {
        Framework.getInstance().quit();
    }

    public void createFolder(String pathname) {
        File folder = new File(pathname);
        if(!folder.exists()) {
            folder.mkdir();
        }
    }

    public String generateScrName(String testMethodName) {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy_hh.mm.ss");
        Date date = new Date();
        return dateFormat.format(date) + "_" + testMethodName + ".png";
    }

    public File takeScreenShot() {
        File scrFile = null;
        try {
            scrFile = ((TakesScreenshot)this.browser).getScreenshotAs(OutputType.FILE);
        } catch (RuntimeException e) {
            System.out.println("Error! Can not take screenshot" + e);
            throw new RuntimeException(e);
        }
        return scrFile;
    }

    public void saveScreenshot(String testMethodName) {
        this.createFolder(this.scrFolderPathname);
        File srcFile = this.takeScreenShot();
        String name = this.generateScrName(testMethodName);
        File destFile = new File(this.scrFolderPathname, this.generateScrName(testMethodName));
        try {
            FileUtils.copyFile(srcFile, destFile);
            System.out.println("Test failed. See screenshot: " + name + " in the directory: " + this.scrFolderPathname);
        } catch (IOException e) {
            System.out.println("Error! Can not save screenshot. \n" + e);
            throw new RuntimeException(e);
        }

    }

}