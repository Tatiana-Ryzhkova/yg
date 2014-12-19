package framework;

import framework.areas.Area;
import framework.pages.Page;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.awt.*;
import java.awt.datatransfer.Transferable;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Framework {

    public static Framework instance;

    protected Properties configProperty;
    protected final String mainPath = "src/main/java/";
    protected final String propPath = "framework/properties/";
    protected final String testDataPath = "framework/testdata/";
    protected String defaultPropName = "Default";

    protected WebDriver driver = null;
    protected HashMap<String, Properties> propertiesList = new HashMap<String, Properties>();

    protected Framework () {
        this.configProperty = this.loadProperties("Config");
        this.propertiesList.put(this.defaultPropName, this.loadProperties(this.defaultPropName));
    }

    public static Framework getInstance() {
        if (Framework.instance == null) {
            Framework.instance = new Framework();
        }
        return Framework.instance;
    }

    public static <T> T getArea(Class<T> classObj) {
        Area pp = null;
        Framework frameworkInstance = Framework.getInstance();
        frameworkInstance.loadPropertiesByClass(classObj);

        try {
            T instance = classObj.newInstance();
            pp = (Area) instance;
            pp.build(frameworkInstance.getDriver(), frameworkInstance);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classObj.cast(pp);
    }

    public static <T> T getPage(Class<T> classObj) {
        Page pp = null;
        Framework frameworkInstance = Framework.getInstance();
        frameworkInstance.loadPropertiesByClass(classObj);

        try {
            T instance = classObj.newInstance();
            pp = (Page) instance;
            pp.build(frameworkInstance.getDriver(), frameworkInstance);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classObj.cast(pp);
    }

    public Properties loadPropertiesByClass(Class className) {
        if (!this.propertiesList.containsKey(className.getSimpleName())) {
            this.propertiesList.put(
                    className.getSimpleName(),
                    this.loadProperties(className.getSimpleName())
            );
        }
        return this.propertiesList.get(className.getSimpleName());
    }

    public Properties loadProperties(String name) {
        String path = this.getPropertyDirPath() + name + ".properties";
        Properties properties = new Properties();
        try {
            FileInputStream in = new FileInputStream(path);
            properties.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }

    public String getPropertyDirPath()
    {
        return this.mainPath + this.propPath;
    }

    public WebDriver getDriver() {
        if(this.driver == null) {
            if(this.getConfig("browser").equals("ie")) {
                this.driver = new InternetExplorerDriver();
            } else if(this.getConfig("browser").equals("chrome")) {
                this.driver = new ChromeDriver();
            } else {

                this.driver = new FirefoxDriver();
            }
            this.driver.manage().window().maximize();
            Framework.getInstance().waitWhileLoad();
        }
        return this.driver;
    }

    public  void quit () {
        this.driver.quit();
        this.driver = null;
    }

    public  void waitWhileLoad() {
        this.getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public void sleep() {
        this.sleep(5000);
    }

    public void deleteCookies() {
        this.getDriver().manage().deleteAllCookies();
    }

    public WebDriver switchBackToWindow (String name) {
        return this.getDriver().switchTo().window(name);
    }

    public void resizeWindow(int desiredWidth, int desiredHeight) {
        Dimension newWindowSize = new Dimension(desiredWidth, desiredHeight);
        this.getDriver().manage().window().setSize(newWindowSize);
    }

    public String getConfig(String key) {
        if (configProperty.getProperty(key) != null) {
            return configProperty.getProperty(key);
        } else {
            throw new NoSuchElementException("There is no such element in the properties");
        }
    }

    public Properties getProperty(String name) {
        if (this.propertiesList.get(name) != null) {
            return this.propertiesList.get(name);
        } else {
            throw new NoSuchElementException("There is no such Property");
        }
    }

    public String getTestDataPath() {
        return this.mainPath + this.testDataPath;
    }

    /**
     * This method is used for parametrized tests
     * @param fileName
     * @return
     */
    public static List<String[]> getTestData(String fileName) {
        String path = Framework.getInstance().getTestDataPath() + fileName;
        List<String[]> records = new ArrayList<String[]>();
        String record;
        try {
            BufferedReader file = new BufferedReader(new FileReader(path));
            while ((record = file.readLine()) != null) {
                String fields[] = record.split(",");
                records.add(fields);
            }
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return records;
    }

    public void refresh() {
        this.getDriver().navigate().refresh();
    }

    public void sleep (long millisecond) {
        try {
            Thread.sleep(millisecond);
        } catch (InterruptedException e) {

        }
    }

    public void setContentsToClipboard (Transferable contentsToClipboard) {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(contentsToClipboard, null);
    }


}
