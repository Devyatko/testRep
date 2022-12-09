package project.forms;

import framework.baseelements.Label;
import framework.baseelements.Link;
import framework.baseforms.BaseForm;
import org.openqa.selenium.By;

public class HomePage extends BaseForm {
    private HomePage(){
        super(new Label(By.xpath("//span[contains(text(),'Placeholder')]"), "unique element home page"));
    }
    public static HomePage getInstance() {
        return new HomePage();
    }

    public void goToGuidePage(){
        Link link = new Link(By.xpath("//a[contains(text(),'Guide')]"), "guide link");
        link.click();
    }

    public void clickPostsLink(){
        Link link = new Link(By.cssSelector("a[href='/posts']"), "posts link");
        link.click();
    }

    public void clickCommentsLink(){
        Link link = new Link(By.cssSelector("a[href='/comments']"), "comments link");
        link.click();
    }

    public Integer getNumberOfPosts(){
        Label numberOfPostsLabel = new Label(By.xpath("//td[contains(text(),' posts')]"), "number of posts label");
        return Integer.parseInt(numberOfPostsLabel.getText().replaceAll("\\D+",""));
    }

    public Integer getNumberOfComments(){
        Label numberOfCommentsLabel = new Label(By.xpath("//td[contains(text(),' comments')]"), "number of comments label");
        return Integer.parseInt(numberOfCommentsLabel.getText().replaceAll("\\D+",""));
    }
}
