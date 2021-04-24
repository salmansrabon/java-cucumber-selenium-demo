import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.Login;

import java.io.IOException;

public class StepDefinitions {
    public WebDriver driver;
    WebDriverWait wait;
    @Given("^User visits e-commerce website$")
    public void user_visits_e_commerce_website() throws Exception {
        System.setProperty("webdriver.gecko.driver", "./src/test/resources/geckodriver.exe");
        FirefoxOptions ops=new FirefoxOptions();
        //ops.addArguments("-headless");
        driver = new FirefoxDriver(ops);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver,5);
        driver.get("http://automationpractice.com");

    }

    @When("^User enters valid \\\"([^\\\"]*)\\\" and \\\"([^\\\"]*)\\\"$")
    public void user_enters_valid_credentials(String username, String password) throws Exception {
        wait.until(ExpectedConditions.presenceOfElementLocated (By.className("login")));
        Login login=new Login(driver);
        login.doLogin(username,password);
    }

    @Then("^User can logged in successfully$")
    public void user_can_logged_in_successfully() throws Exception {
        WebElement lblUserName=driver.findElement(By.xpath("//span[contains(text(),'Test User')]"));
        Assert.assertEquals(lblUserName.getText(),"Test User");
        driver.close();

    }
}
