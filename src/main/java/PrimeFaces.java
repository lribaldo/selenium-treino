import org.apache.maven.shared.utils.io.FileUtils;
import org.junit.*;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class PrimeFaces {
    private WebDriver driver;
    private DSL dsl;

    @Rule
    public TestName testName= new TestName();

    @Before
    public void inicializa() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/luizc/Documents/Driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        dsl = new DSL(driver);
    }

    @After
    public void finaliza() throws IOException {
        TakesScreenshot ss = (TakesScreenshot) driver;
        File arquivo = ss.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(arquivo, new File("target" + File.separator + "screenshot/" + testName.getMethodName() + ".jpg"));
        driver.quit();
    }

    @Test
    public void selecionaBasic() {
        driver.get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml");
        driver.findElement(By.id("j_idt726:console_label")).click();
        driver.findElement(By.xpath("//li[@data-label=\"PS4\"]")).click();
        driver.findElement(By.xpath("//button[@id=\"j_idt726:j_idt772\"]")).click();
    }

    @Test
    public void testAjax() {
        driver.get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml");
        dsl.escreve("j_idt725:name","Teste Ajax");
        driver.findElement(By.id("j_idt725:j_idt728")).click();
        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until(ExpectedConditions.textToBe(By.id("j_idt725:display"),"Teste Ajax"));
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("j_idt803")));
        Assert.assertEquals("Teste Ajax",driver.findElement(By.id("j_idt725:display")).getText());

    }
}
