import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DSL {

    private WebDriver driver;

    public DSL(WebDriver driver) {
        this.driver = driver;
    }

    public void escreve(String campo, String texto){
        driver.findElement(By.id(campo)).sendKeys(texto);
    }

    public void clicaBotao(String campo){
        driver.findElement(By.id(campo)).click();
    }

    public String obterTexto(String campo){
        return driver.findElement(By.id(campo)).getText();
    }

    public void  (String nome) {
        String path = ".//table[@id='elementosForm:tableUsuarios']//td[.='" +nome+ "']/..//input[1]";
        driver.findElement(By.xpath(path)).click();
    }
}
