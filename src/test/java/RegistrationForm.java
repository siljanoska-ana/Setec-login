import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.Random;

public class RegistrationForm {
    WebDriver driver;
    SoftAssert softAssert = new SoftAssert();
    Random randomGenerator = new Random();
    int randomInt = randomGenerator.nextInt(1000);

    @BeforeClass
    public void driverSetProperty() {
        System.setProperty("webdriver.chrome.driver", "C:\\Selenium Web Drivers\\ChromeDrivers\\chromedriver 83.0.4103.39.exe");
        driver = new ChromeDriver();
    }

    @BeforeMethod
    public void initializeWebDriver() {
        driver.get("https://setec.mk/index.php");
        driver.manage().window().maximize();
    }

    @Test (priority = 1)
    public void testRegistration (){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("my-account"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Регистрирај се"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-firstname"))).sendKeys("Aaa");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-lastname"))).sendKeys("Bbb");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-email"))).sendKeys("username"+ randomInt +"@yahoo.com");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-telephone"))).sendKeys("070112233");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-password"))).sendKeys("123456");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-confirm"))).sendKeys("123456");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/form/fieldset[3]/div/div/label[1]/input"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/form/div/div/input[1]"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/form/div/div/input[2]"))).click();
        softAssert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"title-page\"]"))).getText(),"Успешно");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Одјави се"))).click();
    }

    @Test (priority = 2)
    public void testLogIn () {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"header-left\"]/div/a/img"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("my-account"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Логирај се"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-email"))).sendKeys("username"+ randomInt + "@yahoo.com");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-password"))).sendKeys("123456");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div[2]/div/form/input"))).click();
        softAssert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"title-page\"]"))).getText(), "Сметка");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Променете информации"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-firstname"))).clear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-firstname"))).sendKeys("Ccc");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-lastname"))).clear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-lastname"))).sendKeys("Ddd");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-email"))).clear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-email"))).sendKeys("proba@gmail.com");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-telephone"))).clear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-telephone"))).sendKeys("075445566");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/form/div/div[2]/input"))).click();
        softAssert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div"))).getText(), "Вашата сметка е успешно ажурирана.");
    }

    @AfterClass
    public void quitDriver(){
        driver.quit();
    }
}
