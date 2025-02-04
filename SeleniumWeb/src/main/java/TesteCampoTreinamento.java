import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteCampoTreinamento {
	
	private WebDriver driver;
	private DSL dsl;

	@Before
	public void inicializa() {
		
		System.setProperty("webdriver.chrome.driver", 
				"/Users/Bruno/Desktop/Projetos/Testes Funcionais com Selenium WebDriver/course-functional-tests-udemy/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
	}
	
	@After
	public void finaliza() {
		
		driver.quit();
	}

	@Test
	public void testeTextField() {
		
		/* page.setNome("Teste de escrita");
		// dsl.escreve("elementosForm:nome", "Teste de escrita");
		// driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste de escrita");
		Assert.assertEquals("Teste de escrita", dsl.obterValorCampo("elementosForm:nome"));
		// Assert.assertEquals("Teste de escrita", driver.findElement(By.id("elementosForm:nome")).getAttribute("value")); */
	
		dsl.escrever("elementosForm:nome", "Teste de escrita");
		Assert.assertEquals("Teste de escrita", dsl.obterValorCampo("elementosForm:nome"));
	}
	
	@Test
	public void testTextFieldDuplo(){
		dsl.escrever("elementosForm:nome", "Bruno");
		Assert.assertEquals("Bruno", dsl.obterValorCampo("elementosForm:nome"));
		dsl.escrever("elementosForm:nome", "Sicchieri");
		Assert.assertEquals("Sicchieri", dsl.obterValorCampo("elementosForm:nome"));
	}
	
	@Test
	public void deveInterafirComTextArea() {
		
		/* dsl.escreve("elementosForm:sugestoes", "teste\nteste");
		// driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("teste\nteste");
		Assert.assertEquals("teste\nteste", dsl.obterValorCampo("elementosForm:sugestoes"));
		// Assert.assertEquals("teste\nteste", driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value")); */
		
		dsl.escrever("elementosForm:sugestoes", "teste\n\naasldjdlks\nUltima linha");
		Assert.assertEquals("teste\n\naasldjdlks\nUltima linha", dsl.obterValorCampo("elementosForm:sugestoes"));
	}
	
	@Test
	public void deveInterafirComRadioButton() {

		/* dsl.clicarRadio("elementosForm:sexo:0");
		// driver.findElement(By.id("elementosForm:sexo:0")).click();
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));
		// Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected()); */
		
		dsl.clicarRadio("elementosForm:sexo:0");
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));
	}	
	
	@Test
	public void deveInterafirComCheckBox() {
		
		/* dsl.clicarRadio("elementosForm:comidaFavorita:2");
		// driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:comidaFavorita:2"));
		// Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected()); */
		
		dsl.clicarCheck("elementosForm:comidaFavorita:2");
		Assert.assertTrue(dsl.isCheckMarcado("elementosForm:comidaFavorita:2"));
	}	
	
	@Test
	public void deveInterafirComCombo() {	
		
		/* dsl.selecionarCombo("elementosForm:escolaridade", "Mestrado");
		// WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));	
		// Select combo = new Select(element);
		// combo.selectByVisibleText("Mestrado");
		Assert.assertEquals("Mestrado", dsl.obterValorCombo("elementosForm:escolaridade"));
		// Assert.assertEquals("Mestrado", combo.getFirstSelectedOption().getText()); */
		
		dsl.selecionarCombo("elementosForm:escolaridade", "2o grau completo");
		Assert.assertEquals("2o grau completo", dsl.obterValorCombo("elementosForm:escolaridade"));
	}	
	
	@Test
	public void deveVerificarValoresCombo() {
		
		/* WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		Assert.assertEquals(8, options.size());			
		boolean encontrou = false;
		for(WebElement option: options) {
			if(option.getText().equals("Mestrado")) {
				encontrou = true;
				break;
			}
		}
		Assert.assertTrue(encontrou); */
		
		Assert.assertEquals(8, dsl.obterQuantidadeOpcoesCombo("elementosForm:escolaridade"));
		Assert.assertTrue(dsl.verificarOpcaoCombo("elementosForm:escolaridade", "Mestrado"));
	}	
	
	@Test
	public void deveVerificarValoresComboMultiplo() {
		
		/* dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		// combo.selectByVisibleText("Natacao");
		// combo.selectByVisibleText("Corrida");
		// combo.selectByVisibleText("O que eh esporte?");
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(3, allSelectedOptions.size());
		combo.deselectByVisibleText("Corrida");
		allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(2, allSelectedOptions.size()); */
		
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");

		List<String> opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(3, opcoesMarcadas.size());
		
		dsl.deselecionarCombo("elementosForm:esportes", "Corrida");
		opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(2, opcoesMarcadas.size());
		Assert.assertTrue(opcoesMarcadas.containsAll(Arrays.asList("Natacao", "O que eh esporte?")));
	}	

	@Test
	public void deveInteragirComBotoes() {

		/* dsl.clicarBotao("buttonSimple");
		WebElement botao = driver.findElement(By.id("buttonSimple"));
		// botao.click();
		Assert.assertEquals("Obrigado!", botao.getAttribute("value")); */
		
		dsl.clicarBotao("buttonSimple");
		Assert.assertEquals("Obrigado!", dsl.obterValueElemento("buttonSimple"));
	}
	
	@Test
	public void deveInteragirComLinks() {

		/* dsl.clicarLink("Voltar");
		// driver.findElement(By.linkText("Voltar")).click();	
		Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
		// Assert.assertEquals("Voltou!", driver.findElement(By.id("resultado")).getText()); */
		
		dsl.clicarLink("Voltar");
		Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
	}
	
	@Test
	public void deveBuscarTextos() {
		
		/* // System.out.println(driver.findElement(By.tagName("body")).getText());		
		// Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Campo de Treinamento"));		
		// Assert.assertEquals("Campo de Treinamento", driver.findElement(By.tagName("h3")).getText());
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facilAchar")));
		// Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", driver.findElement(By.className("facilAchar")).getText()); */
		
		Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facilAchar")));
	}	

	@Test
	public void testJavaScript() {
		
		/* JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("alert('Testando JS via Selenium')");
		js.executeScript("document.getElementById('elementosForm:nome').value = 'Escrito via JS'");
		// js.executeScript("document.getElementById('elementosForm:sobrenome').type = 'radio'");
		
		WebElement element = driver.findElement(By.id("elementosForm:nome"));
		js.executeScript("arguments[0].style.border = arguments[1]", element, "solid 4px red"); */
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("alert('Testando js via selenium')");
		js.executeScript("document.getElementById('elementosForm:nome').value = 'Escrito via js'");
		js.executeScript("document.getElementById('elementosForm:sobrenome').type = 'radio'");
		
		WebElement element = driver.findElement(By.id("elementosForm:nome"));
		js.executeScript("arguments[0].style.border = arguments[1]", element, "solid 4px red");
	}
	
	@Test
	public void deveClicarBotaoTabela() {
		dsl.clicarBotaoTabela("Nome", "Maria", "Botao", "elementosForm:tableUsuarios");
	}
}