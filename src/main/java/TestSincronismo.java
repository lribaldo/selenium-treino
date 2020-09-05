import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class TestSincronismo {
    private WebDriver driver;
    private DSL dsl;

    @Before
    public void inicializa() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/luizc/Documents/Driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost/selenium/componentes.html");
        dsl = new DSL(driver);
    }

    @After
    public void finaliza() {
//        driver.quit();
    }

    @Test
    public void clicaBotaoDelayImplicito() throws InterruptedException {
        dsl.clicaBotao("buttonDelay");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.id("novoCampo")).sendKeys("Testing");
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    @Test
    public void clicaBotaoDelayExplicito() throws InterruptedException {
        dsl.clicaBotao("buttonDelay");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
        driver.findElement(By.id("novoCampo")).sendKeys("Testing");
    }

}
