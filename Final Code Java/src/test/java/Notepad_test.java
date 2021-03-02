import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class Notepad_test {
    private WindowsDriver notepad = null;
    private Notepad_PO np = null;
    @BeforeClass
    public void setup(){
        System.out.println("setup");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app","c:\\windows\\system32\\notepad.exe");
        try {
            notepad = new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);
            np = new Notepad_PO(notepad);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void teardown(){
        System.out.println("teardown");
        notepad.quit();
    }

    @Test
    public void testNotepad(){
        System.out.println("test");
        np.textArea().sendKeys("Windriver is awesome");
        Assert.assertEquals(np.textArea().getText(), "Windriver is awesome");
        np.textArea().sendKeys(Keys.ALT, Keys.F4);
        np.dialogCancel().click();
        np.textArea().sendKeys(Keys.ALT, Keys.F4);
        np.dialogDontSave().click();
    }
}
