package framework.pages;


import framework.Framework;
import org.openqa.selenium.NoSuchElementException;

public class AdminLogin extends Page{

    public AdminLogin() {
        super();
        isSecure = true;
    }

    public AdminPage openAdminPage() {
        this.login();
        return Framework.getPage(AdminPage.class);
    }

    public void login() {
        try {
            this.getElement("alLoginField");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Element " + "\"LoginField\"" + " is not visible");
        }
        this.setDataToElement("alLoginField", this.getPropertyValue("alTestLogin"));
        this.setDataToElement("alPswrdField", this.getPropertyValue("alTestPswrd"));
        this.getElement("alLoginBtn").click();
        Framework.getInstance().sleep(2000);
    }


}
