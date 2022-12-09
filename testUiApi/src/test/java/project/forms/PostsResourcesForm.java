package project.forms;

import framework.baseelements.Label;
import framework.baseforms.BaseForm;
import org.openqa.selenium.By;

public class PostsResourcesForm extends BaseForm {
    private PostsResourcesForm(){
        super(new Label(By.xpath("//pre[contains(text(),'id') and contains(text(),'title') and contains(text(),'body')]"),
                "posts resources form"));
    }

    public static PostsResourcesForm getInstance() {
        return new PostsResourcesForm();
    }

    public String getText(){
        Label label = new Label(By.xpath("//pre"), "resources text");
        return label.getText();
    }
}
