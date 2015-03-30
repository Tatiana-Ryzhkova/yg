package framework.areas;

public class CookiesAcceptArea extends Area {

    public void readTerms() {
        this.getElement("caReadMore").click();
    }

    public void acceptCookies() {
        this.getElement("caAcceptBtn").click();
    }
}
