package framework.pages;


import framework.Framework;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class AdminArticles extends Page {

    protected final String partialPath = "//div[@id='body']//tr[td[2][text()='";

    public AdminArticles() {
        super();
        isSecure = true;
    }

    private String defaultUserId = Framework.getPage(LoginPage.class).getTestUserId();

    public void addNewArticle (String title, String content, String username) {
        this.pressCreateArticleBtn().addTitle(title);
        Framework.getPage(UserPage.class).addContentText(content);
        if (username.equals("autotestuser")) {
            this.setDefaultUser();
        } else {
            this.setUserByUsername(username);
        }
        this.submitArticle();
    }

    public AdminArticles pressCreateArticleBtn() {
        this.getElement("aaCreateArticleBtn").click();
        return this;
    }

    public AdminArticles addTitle (String title) {
        this.getElement("aaTitleField").sendKeys(title);
        return this;
    }

    public AdminArticles setDefaultUser () {
        Select byId = new Select(this.getElement("aaSelectUserDropdown"));
        byId.selectByValue(defaultUserId);
        return this;
    }

    public AdminArticles setUserByID (String userID) {
        Select byId = (Select) this.getElement("aaSelectUserDropdown");
        byId.deselectByValue(userID);
        return this;
    }

    public AdminArticles setUserByUsername (String username) {
        Select byId = (Select) this.getElement("aaSelectUserDropdown");
        byId.deselectByValue(username);
        return this;
    }

    public AdminArticles submitArticle() {
        this.getElement("aaSubmitBtn").click();
        return this;
    }

    public void deleteArticleByTitle (String articleTitle) {
        this.getDriver().findElement(By.xpath(getPathForDeleteArticleBtnByTitle(articleTitle))).click();
        this.switchToAlertPopup().accept();

    }

    public void deleteFirstArticleInTheGrid () {
        this.getDriver().findElement(By.xpath("aaFirstDeleteBtn")).click();
        this.switchToAlertPopup().accept();

    }

    public boolean isArticleInTheGrid (String articleTitle) {
        this.filterArticleByTitle(articleTitle);
        try {
            this.getDriver().findElement(By.xpath(getPathForArticleByTitle(articleTitle)));
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            System.out.println("WARNING!! There is no article with such title in the grid");
            return false;
        }
    }

    public String getPathForArticleByTitle (String articleTitle) {
        return this.getPartialPathByTitle(articleTitle) + "']]/td[2]";
    }

    public String getPathForDeleteArticleBtnByTitle (String articleTitle) {
        return this.getPartialPathByTitle(articleTitle) + "']]/td[6]/a[contains(@class,'btn-danger')]";
    }

    public String getPartialPathByTitle (String articleTitle) {
        return this.partialPath + articleTitle;
    }

    public void filterArticleByTitle(String articleTitle) {
        try {
            this.getElement("aaNameFilterAll");
        } catch (org.openqa.selenium.NoSuchElementException e) {
            System.out.println("Filter by Name element is not visible. One more time trying to locate the element");
            Framework.getInstance().sleep(1000);
            this.getElement("aaNameFilterAll").click();
        }
        try {
            this.getElement("aaNameFilterInput");
        } catch (org.openqa.selenium.NoSuchElementException e) {
            System.out.println("Filter input by Name element is not visible. " +
                    "One more time trying to locate the element");
            Framework.getInstance().sleep(1000);
            this.getElement("aaNameFilterInput");
        }
        this.getElement("aaNameFilterInput").sendKeys(articleTitle);
        this.getElement("aaNameFilterSubmit").click();
    }

    public void clearFilter() {
        this.getElement("aaNameFilterAll").click();
        this.getElement("aaNameFilterInput").clear();
    }
}
