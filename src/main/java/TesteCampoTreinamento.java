import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class TesteCampoTreinamento {

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
        //driver.quit();
    }

    @Test
    public void testeCampo(){
        Assert.assertEquals("Campo de Treinamento",driver.getTitle());
        dsl.escreve("elementosForm:nome","Nome Teste");
        Assert.assertEquals("Nome Teste",driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
        
    }

    @Test
    public void testeTextArea(){
        dsl.escreve("elementosForm:sugestoes","Text Area Teste\nSegunda Linha\nTerceira Linha");
        Assert.assertEquals("Text Area Teste\nSegunda Linha\nTerceira Linha",driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
    }

    @Test
    public void testeRadioButton(){
        dsl.clicaBotao("elementosForm:sexo:0");
        Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
    }

    @Test
    public void testeSelectionButton(){
        dsl.clicaBotao("elementosForm:comidaFavorita:2");
        Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());
    }

    @Test
    public void testeComboBox(){
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(element);
        combo.selectByVisibleText("2o grau incompleto");
        System.out.println(combo.getAllSelectedOptions());
        Assert.assertEquals("2o grau incompleto", combo.getFirstSelectedOption().getText());
    }

    @Test
    public void testeValoresComboBox(){
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
    }

    @Test
    public void testeComboMultiplo(){
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
    }

    @Test
    public void testeBotaoMudancaNome(){
        dsl.clicaBotao("buttonSimple");
        Assert.assertEquals("Obrigado!",driver.findElement(By.id("buttonSimple")).getAttribute("value"));
    }

    @Test
    public void testeLink(){
        driver.findElement(By.linkText("Voltar")).click();
        Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
        
    }

    @Test
    public void testeProcuraTextoNaPagina(){
        Assert.assertTrue(driver.findElement(By.tagName("body"))
                .getText().contains("Campo de Treinamento"));
        
    }

    @Test
    public void testeAlertSimples(){
        driver.findElement(By.id("alert")).click();
        Alert alerta = driver.switchTo().alert();
        Assert.assertEquals("Alert Simples",alerta.getText());
        alerta.accept();
        
    }

    @Test
    public void testeAlertOpcao(){
        driver.findElement(By.id("confirm")).click();
        Alert alerta = driver.switchTo().alert();
        Assert.assertEquals("Confirm Simples",alerta.getText());
        alerta.dismiss();
        Assert.assertEquals("Negado",alerta.getText());
        alerta.accept();
        
    }

    @Test
    public void testeFrame(){
        driver.switchTo().frame("frame1");
        dsl.clicaBotao("frameButton");

        Alert alerta = driver.switchTo().alert();
        Assert.assertEquals("Frame OK!", alerta.getText());
        alerta.accept();

        
    }

    @Test
    public void testePopup(){
        driver.findElement(By.id("buttonPopUpEasy")).click();
        driver.switchTo().window("Popup");
        driver.findElement(By.tagName("textarea")).sendKeys("Test\nTest2");
        driver.close();
        driver.switchTo().window("");
        driver.findElement(By.tagName("textarea")).sendKeys("Test\nTest2");
        
    }

    @Test
    public void testePopupSemTitulo(){
        driver.findElement(By.id("buttonPopUpHard")).click();
        driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
        driver.findElement(By.tagName("textarea")).sendKeys("Test\nTest2");
        driver.switchTo().window("");
        driver.findElement(By.tagName("textarea")).sendKeys("Test\nTest2");
        
    }

    @Test
    public void clicarBotaoTabela(){
        dsl.clicarBotaoTabela("Maria");
    }
}
