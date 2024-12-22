package web;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("Amazon Home Page Testing")
@Feature("Functional and Performance Tests")
public class AmazonPageTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    @Step("Setting up the test environment")
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers/Chrome-chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test(priority = 1)
    @Story("Search Suggestions Functionality")
    @Description("Verify that the autocomplete suggestions are displayed as the user types in the search bar.")
    @Severity(SeverityLevel.CRITICAL)
    public void testSearchSuggestionsAutocomplete() {
        Given("I am on the Amazon home page", () -> driver.get("https://www.amazon.com.br"));

        When("I type 'pla' into the search bar", () -> {
            WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
            searchBox.sendKeys("pla");
        });

        Then("I should see autocomplete suggestions related to 'playstation 5'", () -> {
            List<WebElement> suggestions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                    By.cssSelector("div.s-suggestion")));

            Assert.assertFalse(suggestions.isEmpty(), "Autocomplete suggestions not displayed");
            for (WebElement suggestion : suggestions) {
                Assert.assertTrue(suggestion.getText().toLowerCase().contains("playstation 5"), "Suggestion does not match input");
            }
        });

        captureScreenshot("testSearchSuggestionsAutocomplete");
    }

    @Test(priority = 2)
    @Story("Navigation Menu Functionality")
    @Description("Verify that the navigation menu is responsive and functional.")
    @Severity(SeverityLevel.NORMAL)
    public void testResponsiveNavigationMenu() {
        Given("I am on the Amazon home page", () -> driver.get("https://www.amazon.com.br"));

        When("I click on the navigation menu button", () -> {
            WebElement menuButton = driver.findElement(By.id("nav-hamburger-menu"));
            menuButton.click();
        });

        Then("The navigation menu should be displayed with items", () -> {
            WebElement menuContent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("hmenu-content")));
            Assert.assertTrue(menuContent.isDisplayed(), "Navigation menu did not open as expected");

            List<WebElement> menuItems = menuContent.findElements(By.tagName("a"));
            Assert.assertFalse(menuItems.isEmpty(), "Menu items not found");
        });

        captureScreenshot("testResponsiveNavigationMenu");
    }

    @Test(priority = 3)
    @Story("Performance Testing")
    @Description("Measure the page load time to ensure it meets performance standards.")
    @Severity(SeverityLevel.MINOR)
    public void testPageLoadPerformance() {
        Given("I want to measure the page load time", () -> {
            long startTime = System.currentTimeMillis();
            driver.get("https://www.amazon.com.br");
            long endTime = System.currentTimeMillis();

            long loadTime = endTime - startTime;
            System.out.println("Page load time: " + loadTime + "ms");

            Then("The page load time should be less than 3000ms", () -> {
                Assert.assertTrue(loadTime < 3000, "Page load time is too high");
            });
        });

        captureScreenshot("testPageLoadPerformance");
    }

    @AfterClass
    @Step("Tearing down the test environment")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void Given(String description, Runnable step) {
        System.out.println("Given: " + description);
        step.run();
    }

    private void When(String description, Runnable step) {
        System.out.println("When: " + description);
        step.run();
    }

    private void Then(String description, Runnable step) {
        System.out.println("Then: " + description);
        step.run();
    }

    private void captureScreenshot(String testName) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.createDirectories(Paths.get("screenshots"));
            Files.copy(screenshot.toPath(), Paths.get("screenshots", testName + ".png"));
        } catch (IOException e) {
            System.err.println("Failed to save screenshot for " + testName + ": " + e.getMessage());
        }
    }
}
