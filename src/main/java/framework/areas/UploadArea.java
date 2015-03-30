package framework.areas;


import framework.Framework;
import framework.pages.MainPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UploadArea extends  Area {

    private Robot robot = this.getRobot();

    /**
     * @param fileName is a simple name of the file in the testdata folder
     * @param fileName
     */
    public UploadArea selectFile(String fileName) {
        //Framework.getInstance().sleep(1000);
        String photoPath = Framework.getInstance().getTestDataPath() + "/" + fileName;
        File file = new File(photoPath);
        StringSelection stringSelection = new StringSelection(file.getAbsolutePath());
        Framework.getInstance().setContentsToClipboard(stringSelection);
        /*Actions builder = new Actions(this.getDriver());
        builder.keyDown(Keys.CONTROL).sendKeys("\u0076").keyUp(Keys.CONTROL).build().perform();*/
        /*Framework.getInstance().sleep(1000);
        this.robot.keyPress(KeyEvent.VK_ENTER);
        this.robot.keyRelease(KeyEvent.VK_ENTER);*/
        this.robot.delay(1000);
        this.robot.keyPress(KeyEvent.VK_CONTROL);
        this.robot.keyPress(KeyEvent.VK_V);
        Framework.getInstance().sleep(500);
        this.robot.keyRelease(KeyEvent.VK_V);
        this.robot.keyRelease(KeyEvent.VK_CONTROL);
        Framework.getInstance().sleep(1000);
        this.robot.keyPress(KeyEvent.VK_ENTER);
        Framework.getInstance().sleep(500);
        this.robot.keyRelease(KeyEvent.VK_ENTER);
        Framework.getInstance().sleep(1000);
        return this;
    }

    public void closeUploadPopup() {
        this.getElement("uaClosePopupBtn").click();
    }

    public void clickSaveBtn() {
        this.getElement("uaSaveBtn").click();
    }

    public void deleteUploadedPhoto() {
        this.getElement("uaDeletePhotoIcon").click();
    }

    public UploadArea addDescription (String description) {
        this.getElement("uaDescription").sendKeys(description);
        return this;
    }

    public UploadArea addTag (String tag) {
        this.getElement("uaTagsInput").sendKeys(tag);
        this.getElement("uaTagsInput").sendKeys(Keys.ENTER);
        return this;
    }

    public UploadArea addTags (String tags) {
        String[] tagsArray = tags.split(" ");
        for (int i = 0; i < tagsArray.length; i ++) {
            addTag(tagsArray[i]);
        }
        return this;
    }

    public boolean verifyMatureLabel() {
        if (this.getElement("uaMatureLabel").getAttribute("title")
                .equals("Viewers under 18 years old will not see this photo")) {
            return true;
        } else {
            System.out.println("There is no label for Mature checkbox");
            return false;
        }
    }

    public boolean verifyWatermarkLabel() {
        if (this.getElement("uaWatermarkLabel").getAttribute("title")
                .equals("Adds full photo watermark for enhanced security")) {
            return true;
        } else {
            System.out.println("There is no label for Watermark checkbox");
            return false;
        }
    }

    public boolean verifyPrivateLabel() {
        if (this.getElement("uaPrivateLabel").getAttribute("title")
                .equals("You can change this later")) {
            return true;
        } else {
            System.out.println("There is no label for Private checkbox");
            return false;
        }
    }

    public boolean verifyLabels() {
        if (this.verifyMatureLabel() && this.verifyWatermarkLabel()
                && this.verifyPrivateLabel()) {
            return true;
        } else {
            return false;
        }
    }

    public UploadArea setMatureFlag() {
        this.getElement("uaMatureCheckbox").click();
        return this;
    }

    public UploadArea setWatermarkFlag() {
        this.getElement("uaWatermarkCheckbox").click();
        return this;
    }

    public UploadArea setPrivateFlag() {
        this.getElement("uaPrivateCheckbox").click();
        return this;
    }

    public UploadArea setFlags (String isMature, String isWatermark, String isPrivate) {
        if (isMature.equalsIgnoreCase("true")) {
            this.setMatureFlag();
        }
        if (isWatermark.equalsIgnoreCase("true")) {
            this.setWatermarkFlag();
        }
        if (isPrivate.equalsIgnoreCase("true")) {
            this.setPrivateFlag();
        }
        return this;
    }

    public Select getCharityDropdown() {
        Select dropdown = new Select(this.getElement("uaCharityBox"));
        return dropdown;
    }

    public UploadArea selectCharity (String charityFullName) {
        if (this.isCharityNamePresent(charityFullName)) {
            this.getCharityDropdown().selectByVisibleText(charityFullName);
        } else throw new Error("There is no such charity in the dropdown");
        return this;
    }

    public List<WebElement> getCharityOptions() {

        List<WebElement> options = this.getCharityDropdown().getOptions();
        return options;
    }

    public ArrayList<String> getCharitiesNameList() {
        ArrayList<String> charitiesList = new ArrayList<>();
        for (WebElement option : this.getCharityOptions()) {
            if (option.getText()!= null) {
                charitiesList.add(option.getText());
            } else {
                Framework.getInstance().waitWhileLoad();
                if (option.getText() == null) {
                    throw new Error("Element is not visible");
                }
            }
        }
        return charitiesList;
    }

    public String[] getCharitiesNameArray() {
        String[] charitiesNameArray = new String[this.getCharitiesNameList().size()];
        charitiesNameArray = this.getCharitiesNameList().toArray(charitiesNameArray);
        return charitiesNameArray;
    }

    public boolean isCharityNamePresent(String charityName) {
        if (this.getCharitiesNameList().contains(charityName)) {
            return true;
        }
        return false;
    }

}
