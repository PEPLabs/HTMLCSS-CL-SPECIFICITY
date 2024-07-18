import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SeleniumTest {

    private WebDriver webDriver;

    @BeforeEach
     public void setUp() {
        
        // Set up ChromeDriver path
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver");//linux_64

        // Get file
        File file = new File("src/main/java/com/revature/index.html");
        String path = "file://" + file.getAbsolutePath();

        // Create a new ChromeDriver instance
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        webDriver = new ChromeDriver(options);

        // Open the HTML file
        webDriver.get(path);
    }
    
    @AfterEach
    void teardown() {
        webDriver.quit();
    }

        
    

    @Test
    public void testIDSelectorSpecificity() {
        try {
            WebElement elementWithLowSpecificity = webDriver.findElement(By.id("low-specificity"));
            WebElement elementWithHighSpecificity = webDriver.findElement(By.id("high-specificity"));

            String lowSpecificityColor = elementWithLowSpecificity.getCssValue("color");
            String highSpecificityColor = elementWithHighSpecificity.getCssValue("color");
            String lowSpecificityFontSize = elementWithLowSpecificity.getCssValue("font-size");
            String highSpecificityFontSize = elementWithHighSpecificity.getCssValue("font-size");
            String lowSpecificityFontWeight = elementWithLowSpecificity.getCssValue("font-weight");
            String highSpecificityFontWeight = elementWithHighSpecificity.getCssValue("font-weight");

            // Ensure that the element with higher specificity has the expected properties
            Assertions.assertEquals("rgba(255, 0, 0, 1)", highSpecificityColor);
            Assertions.assertEquals("16px", highSpecificityFontSize);
            Assertions.assertEquals("700", highSpecificityFontWeight);

            // Ensure that the element with lower specificity has the expected properties
            Assertions.assertEquals("rgba(0, 0, 255, 1)", lowSpecificityColor);
            Assertions.assertEquals("14px", lowSpecificityFontSize);
            Assertions.assertEquals("400", lowSpecificityFontWeight);
        } catch (NoSuchElementException e) {
            Assertions.fail("Elements with IDs not found on the page.");
        }
    }

    @Test
    public void testClassSelectorSpecificity() {
        try {
            WebElement elementWithClass1 = webDriver.findElement(By.className("class1"));
            WebElement elementWithClass2 = webDriver.findElement(By.className("class2"));

            String class1Color = elementWithClass1.getCssValue("color");
            String class2Color = elementWithClass2.getCssValue("color");
            String class1FontSize = elementWithClass1.getCssValue("font-size");
            String class2FontSize = elementWithClass2.getCssValue("font-size");
            String class1FontWeight = elementWithClass1.getCssValue("font-weight");
            String class2FontWeight = elementWithClass2.getCssValue("font-weight");

            // Ensure that the element with higher specificity has the expected properties
            Assertions.assertEquals("rgba(255, 255, 0, 1)", class2Color);
            Assertions.assertEquals("20px", class2FontSize);
            Assertions.assertEquals("700", class2FontWeight);

            // Ensure that the element with lower specificity has the expected properties
            Assertions.assertEquals("rgba(0, 128, 0, 1)", class1Color);
            Assertions.assertEquals("18px", class1FontSize);
            Assertions.assertEquals("400", class1FontWeight);
        } catch (NoSuchElementException e) {
            Assertions.fail("Elements with classes not found on the page.");
        }
    }
}
