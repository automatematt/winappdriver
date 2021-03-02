import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Calculator {
    private WindowsDriver calcsession = null;
    private WebElement calcresult = null;
    @BeforeClass
    public void setup(){
        System.out.println("setup");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app","Microsoft.WindowsCalculator_8wekyb3d8bbwe!App");
        try {
            calcsession = new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void tearDown(){
        System.out.println("Teardown");
        calcsession.quit();
    }

    @BeforeMethod
    public void clear(){
        System.out.println("CLEAR");
        calcsession.findElementByName("Clear").click();
    }

    @Test
    public void addition(){
        System.out.println("running addition");
        calcsession.findElementByName("One").click();
        calcsession.findElementByName("Two").click();
        calcsession.findElementByName("Plus").click();
        calcsession.findElementByName("Nine").click();
        calcsession.findElementByName("Equals").click();
        Assert.assertEquals(GetDisplayResult(), "21");
    }
    @Test
    public void multiplication(){
        System.out.println("running multiplication");
        calcsession.findElementByName("Three").click();
        calcsession.findElementByName("Three").click();
        calcsession.findElementByName("Multiply by").click();
        calcsession.findElementByName("Three").click();
        calcsession.findElementByName("Equals").click();
        Assert.assertEquals(GetDisplayResult(), "99");
    }
    @Test
    public void subtraction(){
        System.out.println("running subtraction");
        calcsession.findElementByName("Nine").click();
        calcsession.findElementByName("Three").click();
        calcsession.findElementByName("Minus").click();
        calcsession.findElementByName("Three").click();
        calcsession.findElementByName("Equals").click();
        Assert.assertEquals(GetDisplayResult(), "90");
    }
    @Test
    public void division(){
        System.out.println("running division");
        calcsession.findElementByName("Three").click();
        calcsession.findElementByName("Three").click();
        calcsession.findElementByName("Divide by").click();
        calcsession.findElementByName("Three").click();
        calcsession.findElementByName("Equals").click();
        Assert.assertEquals(GetDisplayResult(), "11");
    }

    public String GetDisplayResult(){
        calcresult = calcsession.findElementByAccessibilityId("CalculatorResults");
        return calcresult.getText().replace("Display is", "").trim();
    }

    public void ChooseCalculator(String locator){
        System.out.println(locator);
        calcsession.findElementByAccessibilityId("TogglePaneButton").click();
        List <WebElement> listofelements = calcsession.findElementsByClassName("ListViewItem");
        System.out.println(listofelements.size());
        for (int i=0; i<listofelements.size(); i++){
            if (listofelements.get(i).getAttribute("Name").equals(locator)){
                listofelements.get(i).click();
                break;
            }
        }
    }

    @Test
    public void SelectAnotherCalculator(){
        System.out.println("Selecting Anther Calculator");
        ChooseCalculator("Scientific Calculator");
    }


    public void ChooseCalculatorXpath(String locator){
        System.out.println(locator);
        calcsession.findElementByAccessibilityId("TogglePaneButton").click();
        List <WebElement> listofelements = calcsession.findElementsByXPath("//ListItem");
        System.out.println(listofelements.size());
        for (int i=0; i<listofelements.size(); i++){
            if (listofelements.get(i).getAttribute("Name").equals(locator)){
                listofelements.get(i).click();
                break;
            }
        }
    }

    @Test
    public void SelectAnotherCalculatorXpath(){
        System.out.println("Selecting Anther Calculator");
        ChooseCalculatorXpath("Scientific Calculator");
    }

    public void ChooseCalculatorXpathSimple(String locator){
        System.out.println(locator);
        calcsession.findElementByAccessibilityId("TogglePaneButton").click();
        calcsession.findElementByXPath("//ListItem[contains(@Name,\"" + locator + "\")]").click();
    }

    @Test
    public void SelectAnotherCalculatorXpathSimple(){
        System.out.println("Selecting Anther Calculator");
        ChooseCalculatorXpathSimple("Scientific Calculator");
    }

}
