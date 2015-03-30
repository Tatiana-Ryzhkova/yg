package framework.pages;

import framework.Framework;

public class AdminPage extends Page {

    public AdminPage() {
        super();
        isSecure = true;
    }

    public AdminMembers openMembers() {
        this.clickMembers();
        return Framework.getPage(AdminMembers.class);
    }

    public void clickMembers() {
        this.getElement("apMembers").click();
    }

    public void clickArticles() {
        this.getElement("apArticles").click();
    }

    public AdminArticles openArticles() {
        this.clickArticles();
        return Framework.getPage(AdminArticles.class);
    }

    public AdminPage clickSearchBtn() {
        this.getElement("apSearchBtn").click();
        Framework.getInstance().sleep(2000);
        return this;
    }

    public AdminPage searchPhoto (String searchCriteria) {
        this.getElement("apSearchBox").sendKeys(searchCriteria);
        this.clickSearchBtn();
        return this;
    }

    public AdminPage clickFirstPhotoEdit() {
        this.getElement("apEditBtn").click();
        return this;
    }

    public AdminPage addTag (String tag) {
        this.getElement("apTagsField").sendKeys(tag);
        return this;
    }

    public AdminPage addTags (String tags) {
        String[] tagsArray = tags.split(" ");
        for (int i = 0; i < tagsArray.length; i ++) {
            addTag(tagsArray[i]);
        }
        return this;
    }

    public AdminPage saveChanges() {
        this.getElement("apSaveBtn").click();
        return this;
    }
}
