package framework.areas;

import framework.Framework;
import org.openqa.selenium.*;

import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.NoSuchElementException;

public class Area {
    protected WebDriver driver;
    protected Framework framework;
    protected Properties commonProperties;
    protected Properties currentProperties;
    private Robot robot;

    protected String[] firstPart =
            {"ByClassName", "ByCssSelector", "ById", "ByLinkText", "ByName",
                    "ByPartialLink", "ByTagName", "ByXpath"};


    public Area build (WebDriver driver, Framework framework) {
        this.driver = driver;
        this.framework = framework;
        this.currentProperties = this.framework.getProperty(this.getClass().getSimpleName());
        this.commonProperties = this.framework.getProperty("Default");
        return this;
    }

    public Robot getRobot() {
        if (this.robot == null) {
            try {
                this.robot = new Robot();
            } catch (AWTException e) {
                throw new Error("Cannot create new Robot object. See log");
            }
        }
        return this.robot;
    }

    /**
     *  Get value for key from properties. Throw NoSuchElementException if there is non-existent key
     *  @param key - it is a key in the properties
     *  @return value for the key
     */
    public String getPropertyValue(String key) {
        if (currentProperties.getProperty(key) != null) {
            return currentProperties.getProperty(key);
        } else if (commonProperties.getProperty(key) != null) {
            return commonProperties.getProperty(key);
        } else {
            throw new NoSuchElementException("There is no such element in the properties file");
        }
    }

    /**
     * Find element on the page using Selenium findBy method by locator from property file
     * @param name - it is a key in the properties
     * @return webelement
     */
    public WebElement getElement (String name) {

        if(!this.getPropertyValue(name).contains("{@}")) {
            throw new IllegalArgumentException("Property value for " + name + " does not contain {@}");
        }

        WebElement element = null;
        String[] parts = this.getPropertyValue(name).split("\\{\\@\\}");

        if (!Arrays.asList(this.firstPart).contains(parts[0])) {
            throw new IllegalArgumentException("This is illegal name for first part of property value: " + parts[0]);
        }

        try {
            if (parts[0].compareTo(firstPart[0]) == 0) {
                element =  driver.findElement(By.className(parts[1]));
            } else if(parts[0].compareTo(firstPart[1]) == 0) {
                element =  driver.findElement(By.cssSelector(parts[1]));
            } else if(parts[0].compareTo(firstPart[2]) == 0) {
                element =  driver.findElement(By.id(parts[1]));
            } else if(parts[0].compareTo(firstPart[3]) == 0) {
                element =  driver.findElement(By.linkText(parts[1]));
            } else if(parts[0].compareTo(firstPart[4]) == 0) {
                element =  driver.findElement(By.name(parts[1]));
            } else if(parts[0].compareTo(firstPart[5]) == 0) {
                element =  driver.findElement(By.partialLinkText(parts[1]));
            } else if(parts[0].compareTo(firstPart[6]) == 0) {
                element =  driver.findElement(By.tagName(parts[1]));
            } else if(parts[0].compareTo(firstPart[7]) == 0) {
                element =  driver.findElement(By.xpath(parts[1]));
            }
        } catch (NoSuchElementException e) {

        }

        return element;
    }

    public Framework getFramework()
    {
        return this.framework;
    }

    public WebDriver getDriver()
    {
        return this.driver;
    }

    public void setDataToElement (String elementName, String value) {
        WebElement element = this.getElement(elementName);
        element.sendKeys(value);
    }

    public Area verifyElement (String element) {
        WebElement _element = this.getElement(element);
        if (_element.isDisplayed()) {
            System.out.println("Element is visible and displayed properly");
        } else {
            Framework.getInstance().waitWhileLoad();
            if (_element.isDisplayed()) {
                System.out.println("Element is visible and displayed properly");
            } else {
                throw new ElementNotVisibleException("Error!! Element is not visible");
            }
        }
        return this;
    }

    public Alert switchToAlertPopup () {
        return this.getDriver().switchTo().alert();
    }

    public String getTextFromAlert (Alert alertPopup) {
        return alertPopup.getText();
    }

    public void dismissAlertPopup (Alert alertPopup) {
        alertPopup.dismiss();

    }

    public void closeAlertPopup() {
        try {
            this.dismissAlertPopup(this.switchToAlertPopup());
            this.switchBackToDefaultContent();
        } catch (NoAlertPresentException e) {
            System.out.println("No alert is present.");
        }
    }

    public void switchToFrameByNameOrId (String frameNameOrId) {
        this.driver.switchTo().frame(frameNameOrId);
    }

    public void switchToFrameByElement (String element) {
        this.driver.switchTo().frame(this.getElement(element));
    }

    public void switchBackToDefaultContent() {
        this.driver.switchTo().defaultContent();
    }

    public void acceptAlertPopup (Alert alertPopup) {
        alertPopup.accept();
    }

    public void confirm() {
        this.switchToAlertPopup().accept();
    }

    public String getElementValue (String elementLocator) {
        return this.getElement(elementLocator).getText();
    }

    public int getElementValueToInt (String elementLocator) {
        try {
            return Integer.parseInt(this.getElement(elementLocator).getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public Date getDateFromString (String dateString, String format) {
        Date date = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat(format, Locale.ENGLISH);
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Error:  /n"+ e.getStackTrace());
        }
        return date;
    }

    public int compareDates (Date firstDate, Date compareWithDate) {
        return firstDate.compareTo(compareWithDate);
    }

    public boolean isOlderDate (Date firstDate, Date compareWithDate) {
        if (this.compareDates(firstDate, compareWithDate) < 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isNewerDate (Date firstDate, Date compareWithDate) {
        if (this.compareDates(firstDate, compareWithDate) > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isTheSameDate (Date firstDate, Date compareWithDate) {
        if (this.compareDates(firstDate, compareWithDate) == 0) {
            return true;
        } else {
            return false;
        }
    }

}
