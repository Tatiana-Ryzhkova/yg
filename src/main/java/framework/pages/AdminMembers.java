package framework.pages;


import framework.Framework;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.security.Credentials;

import java.util.NoSuchElementException;

public class AdminMembers extends Page{

    protected final String partialPath = "//div[@id='body']//tr[td[3][text()='";
    protected final String enabledStatus = "Active";
    protected final String disabledStatus = " Disabled ";

    public AdminMembers() {
        super();
        isSecure = true;
    }

    public String getPartialPathByEmail (String userEmail) {
        return this.partialPath + userEmail;
    }
    public String getPathForDeleteBtnByEmail (String userEmail) {
        return this.getPartialPathByEmail(userEmail) + "']]/td[6]//a[@class ='btn btn-danger']";
    }
    public String getPathForUserIdByEmail (String userEmail) {
        return this.getPartialPathByEmail(userEmail) + "']]/td[1]";
    }
    public String getPathForStatus (String userEmail) {
        return this.getPartialPathByEmail(userEmail) + "']]/td[5]//a[@data-title='Change Status']";
    }
    public String getPathForChangeStatusBox (String userEmail) {
        return this.getPartialPathByEmail(userEmail) + "']]/td[5]//div[@class='editable-input']";
    }
    public String getDisableOptionPath(String userEmail) {
        return this.getPathForChangeStatusBox(userEmail) + "/select/option[2]";
    }
    public String getEnableOptionPath(String userEmail) {
        return this.getPathForChangeStatusBox(userEmail) + "/select/option[1]";
    }
    public String getPathForSubmitStatusBtn (String userEmail) {
        return this.getPartialPathByEmail(userEmail)
                + "']]/td[5]//div[@class='editable-buttons']//button[@type='submit']";
    }

    public String getUserStatus (String username) {
        String path = this.getPathForStatus(username) + "/span";
        try {
            this.getDriver().findElement(By.xpath(path));
        } catch (org.openqa.selenium.NoSuchElementException e) {

        }
        return this.getDriver().findElement(By.xpath(path)).getText();
    }

    public AdminMembers verifyElementInTheGrid(String tdTitle) {
        String selector = "//" + "td[@title='" + tdTitle + "']";
        try {
            if (this.driver.findElement(By.xpath(selector)).isDisplayed()) {
                System.out.println("User has been saved to the DB");
            } else throw new NoSuchElementException("There is no such username in the DB");
        } catch (Exception e) {
            System.out.println("User signup failed");
        }
        return this;
    }

    public boolean isUserInTheGrid (String userEmail) {
        this.filterUserByEmail(userEmail);
        try {
            this.getDriver().findElement(By.xpath(getPathForUserIdByEmail(userEmail)));
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            System.out.println("WARNING!! There is no user with such email in the grid");
            return false;
        }
    }

    public String getUserIdFromGridByEmail (String userEmail) {
        String userId = null;
        if (this.isUserInTheGrid(userEmail)) {
            userId = this.getDriver().findElement(By.xpath(getPathForUserIdByEmail(userEmail))).getText();
        }
        return userId;
    }

    public void deleteUserFromDB (String userEmail) {
        String url = this.pageURL + "/delete/" + this.getUserIdFromGridByEmail(userEmail) + "?completly=1";
        this.getDriver().get(url);
    }

    public void filterUserByEmail(String userEmail) {
        try {
            this.getElement("amEmailFilterAll");
        } catch (org.openqa.selenium.NoSuchElementException e) {

        }
        Framework.getInstance().sleep(1000);
        this.getElement("amEmailFilterAll").click();
        try {
            this.getElement("amEmailFilterInput");
        } catch (org.openqa.selenium.NoSuchElementException e) {

        }
        this.getElement("amEmailFilterInput").sendKeys(userEmail);
        this.getElement("amEmailFilterSubmit").click();
    }

    public void clearFilter() {
        this.getElement("amEmailFilterAll").click();
        this.getElement("amEmailFilterInput").clear();
    }

    public void selectChangeStatusBox (String userEmail) {
        try {
            this.getDriver().findElement(By.xpath(getPathForStatus(userEmail))).click();
        } catch (NoSuchElementException e) {
            System.out.println("ERROR! Wrong element locator. Verify element Xpath");
        }
        try {
            this.getDriver().findElement(By.xpath(getPathForChangeStatusBox(userEmail))).click();
        } catch (NoSuchElementException e) {
            System.out.println("ERROR! Wrong element locator. Verify element Xpath");
        }
    }

    public void submitChangeStatus (String userEmail) {
        try {
            this.getDriver().findElement(By.xpath(getPathForSubmitStatusBtn(userEmail))).click();
        } catch (NoSuchElementException e) {
            System.out.println("ERROR! Wrong element locator. Verify element Xpath");
        }
        this.refresh();
    }

    public void changeUserStatus (String userEmail, String optionPath, String status) {
        this.filterUserByEmail(userEmail);
        this.clearFilter();
        this.selectChangeStatusBox(userEmail);
        try {
            this.getDriver().findElement(By.xpath(optionPath)).click();
        } catch (NoSuchElementException e) {
            System.out.println("ERROR! Wrong element locator. Verify element Xpath");
        }
        this.submitChangeStatus(userEmail);
        /*this.selectChangeStatusBox(userEmail);
        if(!(this.getUserStatus(userEmail) == status)) {
            throw new NoSuchElementException("Status has not been changed");
        }*/
    }

    public void disableUser (String userEmail) {
        this.changeUserStatus(userEmail, this.getDisableOptionPath(userEmail), this.disabledStatus);
    }

    public void enableUser (String userEmail) {
        this.changeUserStatus(userEmail, this.getEnableOptionPath(userEmail), this.enabledStatus);
    }

    public void deleteUser (String userEmail) {
        this.filterUserByEmail(userEmail);
        try {
            this.getDriver().findElement(By.xpath(this.getPathForDeleteBtnByEmail(userEmail))).click();
        } catch (NoSuchElementException e) {
            System.out.println("ERROR! Wrong element locator. Verify element Xpath");
        }
        Alert alert = this.switchToAlertPopup();
        alert.accept();
    }

}
