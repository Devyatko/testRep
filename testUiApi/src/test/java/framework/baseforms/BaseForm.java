package framework.baseforms;

import framework.baseelements.Label;

public abstract class BaseForm {
    private Label uniqueEl;

    public BaseForm(Label uniqueEl){
        this.uniqueEl = uniqueEl;
    }

    public boolean isOpen() {
        return uniqueEl.getSizeFindElements() > 0;
    }
}
