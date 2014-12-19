package framework.areas;


import framework.Framework;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;

public class UploadArea extends  Area {

    private Robot robot = this.getRobot();

    /**
     * @param fileName is a simple name of the file in the testdata folder
     * @param fileName
     */
    public UploadArea selectFile(String fileName) {
        String photoPath = Framework.getInstance().getTestDataPath() + "/" + fileName;
        File file = new File(photoPath);
        StringSelection stringSelection = new StringSelection(file.getAbsolutePath());
        Framework.getInstance().setContentsToClipboard(stringSelection);
        this.robot.keyPress(KeyEvent.VK_ENTER);
        this.robot.keyRelease(KeyEvent.VK_ENTER);
        this.robot.delay(1000);
        this.robot.keyPress(KeyEvent.VK_CONTROL);
        this.robot.keyPress(KeyEvent.VK_V);
        this.robot.keyRelease(KeyEvent.VK_V);
        this.robot.keyRelease(KeyEvent.VK_CONTROL);
        this.robot.delay(1000);
        this.robot.keyPress(KeyEvent.VK_ENTER);
        this.robot.keyRelease(KeyEvent.VK_ENTER);
        this.robot.delay(1000);
        return this;
    }

    public void closeUploadPopup() {
        this.getElement("upClosePopupBtn").click();
    }

    public void clickSaveBtn() {
        this.getElement("upSaveBtn").click();
    }

    public UploadArea addDescription (String description) {
        this.getElement("upDescription").sendKeys(description);
        return this;
    }

    public UploadArea addTag (String tag) {
        this.getElement("upTagsInput").sendKeys(tag);
        this.robot.keyPress(KeyEvent.VK_ENTER);
        this.robot.keyRelease(KeyEvent.VK_ENTER);
        return this;
    }

    public UploadArea selectFirstTag() {
        this.getElement("upFirstAddedTag").click();
        return this;
    }

    public UploadArea removeFirstTag() {
        this.getElement("upFirstAddedTagRemove").click();
        return this;
    }

    public UploadArea addTags (String tags) {
        String[] tagsArray = tags.split(" ");
        for (int i = 0; i < tagsArray.length; i ++) {
            addTag(tagsArray[i]);
        }
        return this;
    }

    public void deleteUploadedPhoto() {
        this.getElement("upDeletePhotoIcon").click();
    }

    public UploadArea setMatureFlag() {
        this.getElement("upMatureCheckbox").click();
        return this;
    }

    public UploadArea setWatermarkFlag() {
        this.getElement("upWatermarkCheckbox").click();
        return this;
    }

    public UploadArea setPrivateFlag() {
        this.getElement("upPrivateCheckbox").click();
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




}
