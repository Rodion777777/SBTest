import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;

public class SBTest {

    private WebDriver driver;
    private String baseUrl;


    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "D:/Desktop/WebDrivers/chromedriver.exe");
        driver = new ChromeDriver();
        baseUrl = "http://www.sberbank.ru/ru/person/";
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
    }

    @Test
    public void testRegion() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver,30);
        driver.get(baseUrl);

        driver.findElement(By.xpath("//div[contains(@class,'paste-region__region header__region header__region_77')]//div//div//span")).click();
        driver.findElement(By.xpath("//input[contains(@placeholder,'Москва')]")).sendKeys("Нижегородская область\n");

        assertEquals("Ожидаемая область отличается от фактической", "Нижегородская область", driver.findElement(By.xpath("//div[@class='paste-region__region header__region header__region_52']//div//div//span")).getText());
        driver.findElement(By.tagName("body")).sendKeys(Keys.END);
        driver.findElement(By.xpath("//ul[@class='footer__social']")).isDisplayed();

    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

}
