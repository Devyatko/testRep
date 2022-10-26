package framework.baseelements;

import org.openqa.selenium.By;

public class Link extends BaseElement{
    public Link(By byLocator, String nameElement){
        super(byLocator, nameElement);
    }

    public String getHref(){
        return getAttribute("href");
    }
}
