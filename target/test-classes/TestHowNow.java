import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class TestHowNow {

    WebDriver driver;

    @Given("^I navigate to the \"([^\"]*)\" link$")
    public void i_navigate_to_the_link(String link) throws Throwable {

        File f = new File("src\\test\\Resoruces\\Config.properties");
        FileInputStream fs = new FileInputStream(f);
        Properties properties = new Properties();
        properties.load(fs);
        String url = properties.getProperty(link).toString();

        System.setProperty("webdriver.chrome.driver", "src\\test\\Resources\\Drivers\\chromerdriver.exe")
        WebDriver driver = new ChromeDriver();
        driver.get(url);

    }

}
