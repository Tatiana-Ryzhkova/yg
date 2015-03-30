package framework.areas;


import org.openqa.selenium.NoSuchElementException;

import java.awt.*;
import java.awt.event.KeyEvent;

public class EditPhotoArea extends  Area {

    private Robot robot = this.getRobot();

    public EditPhotoArea saveChanges() {
        this.getElement("epaSaveBtn").click();
        return this;
    }

    public EditPhotoArea addTag (String tag) {
        this.getElement("epaAddTag").sendKeys(tag);
        this.robot.keyPress(KeyEvent.VK_ENTER);
        this.robot.keyRelease(KeyEvent.VK_ENTER);
        return this;
    }

    public EditPhotoArea selectFirstTag() {
        this.getElement("epaFirstAddedTag").click();
        return this;
    }

    public String getFirstTagText() {
        String tagText = this.getElement("epaFirstAddedTag").getText();
        //Because of DeleteTag functionality tagText is in format "tag X" and we need only text
        int index = tagText.indexOf(" ");
        return tagText.substring(0, index);
    }

    public EditPhotoArea removeFirstTag() {
        this.getElement("epaFirstAddedTagRemove").click();
        return this;
    }

    public EditPhotoArea removeAllTags() {
        boolean notLast = true;
        while (notLast) {
            try {
                this.removeFirstTag();
            } catch (NoSuchElementException e) {
                notLast = false;
            }
        }
        return this;
    }

    public EditPhotoArea addTags (String tags) {
        String[] tagsArray = tags.split(" ");
        for (int i = 0; i < tagsArray.length; i ++) {
            addTag(tagsArray[i]);
        }
        return this;
    }

    public boolean tagPresent() {
        boolean tag = true;
        try {
            this.selectFirstTag();
        } catch (NoSuchElementException e) {
            tag = false;
        }
        return tag;
    }

    public EditPhotoArea makePrivate() {
        this.getElement("epaPrivate").click();
        return this;
    }

    public String getSelectedCharity() {
        return this.getElement("epaSelectedCharity").getText();
    }

    public boolean verifySelectedCharity (String expectedCharityName) {
        if (this.getSelectedCharity().equals(expectedCharityName)) {
            return true;
        } else {
            return false;
        }
    }

    public EditPhotoArea addDescription (String description) {
        this.getElement("epaDescription").sendKeys(description);
        return this;
    }

    public EditPhotoArea deleteDescription () {
        this.getElement("epaDescription").clear();
        return this;
    }

}
