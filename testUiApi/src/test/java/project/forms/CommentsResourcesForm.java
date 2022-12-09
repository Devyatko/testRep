package project.forms;

import framework.baseelements.Label;
import framework.baseforms.BaseForm;
import org.openqa.selenium.By;

public class CommentsResourcesForm extends BaseForm {
    private CommentsResourcesForm(){
        super(new Label(By.xpath("//pre[contains(text(),'postId')]"), "comments resources form"));
    }

    public static CommentsResourcesForm getInstance() {
        return new CommentsResourcesForm();
    }

    public String getText(){
        Label label = new Label(By.xpath("//pre"), "resources text");
        return label.getText();
    }
}
