import com.google.common.annotations.VisibleForTesting;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestGoogle {

    @Test
    public void teste() {
//        System.setProperty("webdriver.gecko.driver", "C:/Users/luizc/Documents/Driver/geckodriver.exe");
//        WebDriver driver = new FirefoxDriver();
        System.setProperty("webdriver.chrome.driver", "C:/Users/luizc/Documents/Driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
//        driver.manage().window().setSize(new Dimension(1024,768));
//        driver.manage().window().maximize();
        driver.get("https://google.com.br");
        Assert.assertEquals("Google",driver.getTitle());
        driver.quit();
    }
}
