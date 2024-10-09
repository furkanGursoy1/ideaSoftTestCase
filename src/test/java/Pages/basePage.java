package Pages;

import Utilities.Driver;
import org.openqa.selenium.support.PageFactory;

public class basePage {
    public basePage() {
        PageFactory.initElements(Driver.get(), this);
    }

}
