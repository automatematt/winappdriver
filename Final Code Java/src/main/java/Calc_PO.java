import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.WebElement;

public class Calc_PO {
    private WindowsDriver driver = null;
    public Calc_PO(WindowsDriver wd){
        driver = wd;

    }

    public WebElement One(){
        return driver.findElementByName("one");
    }

    public WebElement Two(){
        return driver.findElementByName("Two");
    }

    public WebElement Three(){
        return driver.findElementByName("Three");
    }

    public WebElement Four(){
        return driver.findElementByName("Four");
    }

    public WebElement Plus(){
        return driver.findElementByName("Plus");
    }

    public WebElement Equals(){
        return driver.findElementByName("Equals");
    }

    public String GetDisplayResult(){
        return driver.findElementByAccessibilityId("CalculatorResults").getText().replace("Display is", "").trim();
    }
}
