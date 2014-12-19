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


}
