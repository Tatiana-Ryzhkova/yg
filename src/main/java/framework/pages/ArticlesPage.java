package framework.pages;


import framework.Framework;
import framework.areas.*;

public class ArticlesPage extends Page {

    public CookiesAcceptArea cookiesAcceptArea = Framework.getArea(CookiesAcceptArea.class);
    /**
     * Areas for visitor Articles page
     */
    public VisitorBlockArea visitorBlock = Framework.getArea(VisitorBlockArea.class);
    public LeftMenuArea leftMenuArea = Framework.getArea(LeftMenuArea.class);
    public FirstArticleArea firstArticleArea = Framework.getArea(FirstArticleArea.class);
    /**
     * Areas for user Articles page
     */
    public LeftMenuUserAddition leftMenuUser = Framework.getArea(LeftMenuUserAddition.class);
    public UserBlockArea userBlock = Framework.getArea(UserBlockArea.class);
    public UploadArea uploadArea = Framework.getArea(UploadArea.class);
    public EditPhotoArea editPhotoArea = Framework.getArea(EditPhotoArea.class);

    public ArticlesPage() {
        super();
        isSecure = true;
    }





}
