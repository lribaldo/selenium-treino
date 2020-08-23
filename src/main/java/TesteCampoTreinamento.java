import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class TesteCampoTreinamento {

    @Test
    public void testeCampo(){
        System.setProperty("webdriver.chrome.driver", "C:/Users/luizc/Documents/Driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost/selenium/componentes.html");
        Assert.assertEquals("Campo de Treinamento",driver.getTitle());
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Nome Teste");
        Assert.assertEquals("Nome Teste",driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
        driver.quit();
    }

    @Test
    public void testeTextArea(){
        System.setProperty("webdriver.chrome.driver", "C:/Users/luizc/Documents/Driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost/selenium/componentes.html");
        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Text Area Teste\nSegunda Linha\nTerceira Linha");
        Assert.assertEquals("Text Area Teste\nSegunda Linha\nTerceira Linha",driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
        driver.quit();
    }

    @Test
    public void testeRadioButton(){
        System.setProperty("webdriver.chrome.driver", "C:/Users/luizc/Documents/Driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost/selenium/componentes.html");
        driver.findElement(By.id("elementosForm:sexo:0")).click();
        Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
        driver.quit();
    }

    @Test
    public void testeSelectionButton(){
        System.setProperty("webdriver.chrome.driver", "C:/Users/luizc/Documents/Driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost/selenium/componentes.html");
        driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
        Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());
        driver.quit();
    }

    @Test
    public void testeComboBox(){
        System.setProperty("webdriver.chrome.driver", "C:/Users/luizc/Documents/Driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost/selenium/componentes.html");
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(element);
        combo.selectByVisibleText("2o grau incompleto");
        System.out.println(combo.getAllSelectedOptions());
        Assert.assertEquals("2o grau incompleto", combo.getFirstSelectedOption().getText());
        driver.quit();
    }

    @Test
    public void testeValoresComboBox(){
        System.setProperty("webdriver.chrome.driver", "C:/Users/luizc/Documents/Driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost/selenium/componentes.html");
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(element);
        List<WebElement> options = combo.getOptions();
        System.out.println(combo.getAllSelectedOptions());
        Assert.assertEquals(8, options.size());

        // checar se o valor esta na combobox como uma opção
        boolean encontrou = false;
        for(WebElement option:options) {
            if(option.getText().equals("Mestrado")){
                encontrou = true;
                break;
            }
        }
        Assert.assertTrue(encontrou);
        driver.quit();
    }

    @Test
    public void testeComboMultiplo(){
        System.setProperty("webdriver.chrome.driver", "C:/Users/luizc/Documents/Driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost/selenium/componentes.html");
        WebElement element = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(element);
        List<WebElement> options = combo.getOptions();
        combo.selectByVisibleText("Natacao");
        combo.selectByVisibleText("Karate");
        System.out.println(combo.getAllSelectedOptions());
        Assert.assertEquals(5, options.size());

        // checar se o valor esta na combobox como uma opção
        boolean encontrou = false;
        for(WebElement option:options) {
            if(option.getText().equals("O que eh esporte?")){
                encontrou = true;
                break;
            }
        }
        Assert.assertTrue(encontrou);
        driver.quit();
    }

    @Test
    public void testeBotaoMudancaNome(){
        System.setProperty("webdriver.chrome.driver", "C:/Users/luizc/Documents/Driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost/selenium/componentes.html");
        driver.findElement(By.id("buttonSimple")).click();
        Assert.assertEquals("Obrigado!",driver.findElement(By.id("buttonSimple")).getAttribute("value"));
        driver.quit();
    }

    @Test
    public void testeLink(){
        System.setProperty("webdriver.chrome.driver", "C:/Users/luizc/Documents/Driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost/selenium/componentes.html");
        driver.findElement(By.linkText("Voltar")).click();
        Assert.assertEquals("Voltou!", driver.findElement(By.id("resultado")).getText());
        driver.quit();
    }

    @Test
    public void testeProcuraTextoNaPagina(){
        System.setProperty("webdriver.chrome.driver", "C:/Users/luizc/Documents/Driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost/selenium/componentes.html");
        Assert.assertTrue(driver.findElement(By.tagName("body"))
                .getText().contains("Campo de Treinamento"));
        driver.quit();
    }

    @Test
    public void testeAlertSimples(){
        System.setProperty("webdriver.chrome.driver", "C:/Users/luizc/Documents/Driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost/selenium/componentes.html");
        driver.findElement(By.id("alert")).click();
        Alert alerta = driver.switchTo().alert();
        Assert.assertEquals("Alert Simples",alerta.getText());
        alerta.accept();
        driver.quit();
    }

    @Test
    public void testeAlertOpcao(){
        System.setProperty("webdriver.chrome.driver", "C:/Users/luizc/Documents/Driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost/selenium/componentes.html");
        driver.findElement(By.id("confirm")).click();
        Alert alerta = driver.switchTo().alert();
        Assert.assertEquals("Confirm Simples",alerta.getText());
        alerta.dismiss();
        Assert.assertEquals("Negado",alerta.getText());
        alerta.accept();
        driver.quit();
    }
}
