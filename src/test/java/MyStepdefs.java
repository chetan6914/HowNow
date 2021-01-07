import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

public class MyStepdefs {

    By email = By.xpath("//input[@placeholder='Email Address']");
    By pass = By.xpath("//input[@placeholder='Password']");
    By menuicon = By.xpath("(//div[@class='header-wrapper']/div/i)[1]");
//    By studiolink = By.xpath("//li[@class='studio_page']/a[@href='/studio']");
    By studiolink = By.xpath("//a[@href='/studio/w']");
    By login = By.xpath("//input[@value='Login']");
    By webinar_name = By.xpath("//label[text()='Webinar Name']/following-sibling::input");
    By channel = By.xpath("//div[@id='s2id_skill_id']");
    By Save_Cont = By.xpath("//a/p[text()='Save & Continue']");
    By selectchannel(String inp){
        return By.xpath("//div/ul/li/div/span[text()='"+inp+"']");
    }


    WebDriver driver;
    WebDriverWait wait;

    @Given("^I navigate to the \"([^\"]*)\" link$")
    public void i_navigate_to_the_link(String link) throws Throwable {

        File f = new File("src\\test\\Resources\\Config.properties");
        FileInputStream fs = new FileInputStream(f);
        Properties properties = new Properties();
        properties.load(fs);
        String url = properties.getProperty(link).toString();
        System.out.println("");

        System.setProperty("webdriver.chrome.driver", "src\\test\\Resources\\Drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(url);
//        driver.manage().window().maximize();
    }


    @Then("^I enter username \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void iEnterUsernameAndPassword(String emailadd, String password) throws Throwable {
        wait  = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(email)).sendKeys(emailadd);
        wait.until(ExpectedConditions.elementToBeClickable(pass)).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(login)).click();

    }

    @And("^I click on menu icon and navigate to studio tab$")
    public void iClickOnMenuIconAndNavigateToStudioTab() {

     wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(menuicon)).click();
//        wait.until(ExpectedConditions.elementToBeClickable(studiolink)).click();
        driver.navigate().to("https://team.hownow.cloud/studio");
    }

    @Then("^I click on Webinar and enter the details$")
    public void iClickOnWebinarAndEnterTheDetails(DataTable table) {

        wait = new WebDriverWait(driver, 20);

        List <Testdata> list = table.asList(Testdata.class);
        for (Testdata td : list)
        {
//            wait.until(ExpectedConditions.elementToBeClickable(webinar_name)).click();
            driver.navigate().to("https://team.hownow.cloud/studio/w/6625");
            wait.until(ExpectedConditions.elementToBeClickable(channel)).click();
//            wait.until(ExpectedConditions.elementToBeClickable(channel)).sendKeys(td.testtoselect);
            wait.until(ExpectedConditions.elementToBeClickable(selectchannel(td.testtoselect))).click();
            wait.until(ExpectedConditions.elementToBeClickable(Save_Cont)).click();
        }

    }

    public class Testdata
    {
        String webinarname;
        String testtoselect;

        public Testdata(String webinarname, String testtoselect) {
            this.webinarname = webinarname;
            this.testtoselect = testtoselect;
        }
    }
}
