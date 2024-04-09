package tqs.homework1.SeleniumTests;



import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.platform.commons.annotation.Testable;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;


public class HW1Test {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @BeforeAll
  public void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @AfterAll
  public void tearDown() {
    driver.quit();
  }
  @Testable
  public void hW1() {
    driver.get("http://localhost:5000/");
    driver.manage().window().setSize(new Dimension(1920, 1080));
    {
      WebElement dropdown = driver.findElement(By.id("origin"));
      dropdown.findElement(By.xpath("//option[. = 'Viana']")).click();
    }
    {
      WebElement element = driver.findElement(By.id("origin"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).clickAndHold().perform();
    }
    {
      WebElement element = driver.findElement(By.id("origin"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    {
      WebElement element = driver.findElement(By.id("origin"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).release().perform();
    }
    {
      WebElement dropdown = driver.findElement(By.id("destination"));
      dropdown.findElement(By.xpath("//option[. = 'Lisboa']")).click();
    }
    {
      WebElement element = driver.findElement(By.id("destination"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).clickAndHold().perform();
    }
    {
      WebElement element = driver.findElement(By.id("destination"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    {
      WebElement element = driver.findElement(By.id("destination"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).release().perform();
    }
    driver.findElement(By.id("start_date")).click();
    driver.findElement(By.id("start_date")).sendKeys("2024-04-11");
    driver.findElement(By.cssSelector("button")).click();
    driver.findElement(By.cssSelector("tr:nth-child(2) .button-select")).click();
    driver.findElement(By.name("name")).click();
    driver.findElement(By.name("name")).sendKeys("Vasco");
    driver.findElement(By.name("address")).click();
    driver.findElement(By.name("address")).sendKeys("rua nao sei onde");
    driver.findElement(By.name("city")).click();
    driver.findElement(By.name("city")).sendKeys("Viana");
    driver.findElement(By.name("state")).click();
    driver.findElement(By.name("state")).sendKeys("Viana");
    driver.findElement(By.name("zipCode")).click();
    driver.findElement(By.name("zipCode")).sendKeys("1234-789");
    {
      WebElement dropdown = driver.findElement(By.id("cardType"));
      dropdown.findElement(By.xpath("//option[. = 'American Express']")).click();
    }
    {
      WebElement element = driver.findElement(By.id("cardType"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).clickAndHold().perform();
    }
    {
      WebElement element = driver.findElement(By.id("cardType"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    {
      WebElement element = driver.findElement(By.id("cardType"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).release().perform();
    }
    driver.findElement(By.name("creditCardNumber")).click();
    driver.findElement(By.name("creditCardNumber")).sendKeys("1234556788");
    driver.findElement(By.cssSelector(".form-column:nth-child(2) > .form-group")).click();
    driver.findElement(By.name("month")).click();
    driver.findElement(By.name("month")).sendKeys("4");
    driver.findElement(By.name("year")).click();
    driver.findElement(By.name("year")).sendKeys("2003");
    driver.findElement(By.name("nameOnCard")).click();
    driver.findElement(By.name("nameOnCard")).sendKeys("Vasco F");
    driver.findElement(By.cssSelector(".form-column:nth-child(2) > .form-group")).click();
    driver.findElement(By.cssSelector(".button-submit")).click();
    driver.findElement(By.cssSelector("button:nth-child(2)")).click();
    driver.findElement(By.cssSelector(".ticket-item:nth-child(3) > .delete-btn")).click();
    driver.findElement(By.id("confirmDeleteBtn")).click();
    driver.findElement(By.cssSelector(".ticket-item:nth-child(2) > .delete-btn")).click();
    driver.findElement(By.id("confirmDeleteBtn")).click();
  }
}
