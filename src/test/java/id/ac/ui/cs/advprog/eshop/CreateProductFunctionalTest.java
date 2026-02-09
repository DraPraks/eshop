package id.ac.ui.cs.advprog.eshop;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.time.Duration;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CreateProductFunctionalTest {

    @LocalServerPort
    private int port;

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void createProductShowsInList() {
        String productId = "P-" + UUID.randomUUID();
        String productName = "Test Product";
        String productQty = "7";

        driver.get(baseUrl("/products/create"));

        WebElement idInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("idInput")));
        WebElement nameInput = driver.findElement(By.id("nameInput"));
        WebElement quantityInput = driver.findElement(By.id("quantityInput"));

        idInput.sendKeys(productId);
        nameInput.sendKeys(productName);
        quantityInput.sendKeys(productQty);

        quantityInput.submit();

        wait.until(ExpectedConditions.urlContains("/products/list"));
        String pageSource = driver.getPageSource();

        assertTrue(pageSource.contains(productId));
        assertTrue(pageSource.contains(productName));
        assertTrue(pageSource.contains(productQty));
    }

    private String baseUrl(String path) {
        return "http://localhost:" + port + path;
    }
}
