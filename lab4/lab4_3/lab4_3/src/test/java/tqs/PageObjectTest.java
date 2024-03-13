package tqs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SeleniumJupiter.class)
public class PageObjectTest {

  private ChromeDriver driver;


  @FindBy(name="fromPort")
  private WebElement fromPort;

  @FindBy(xpath = "//option[. = 'Paris']")
  private WebElement fromPortOption;

  @FindBy(name="toPort")
  private WebElement toPort;

  @FindBy(xpath = "//option[. = 'Berlin']")
  private WebElement toPortOption;

  @FindBy(css=".btn-primary")
  private WebElement btn1;

  @FindBy(css="tr:nth-child(2) .btn")
  private WebElement btn2;

  @FindBy(name="inputName")
  private WebElement inputName;

  @FindBy(name="address")
  private WebElement address;

  @FindBy(name="city")
  private WebElement city;

  @FindBy(name="state")
  private WebElement state;

  @FindBy(name="zipCode")
  private WebElement zipCode;

  @FindBy(name="cardType")
  private WebElement cardType;

  @FindBy(css="option:nth-child(2)")
  private WebElement btn3;

  @FindBy(xpath = "//option[. = 'American Express']")
  private WebElement cardTypeOption;

  @FindBy(name="creditCardNumber")
  private WebElement creditCardNumber;

  @FindBy(name="nameOnCard")
  private WebElement nameOnCard;

  @FindBy(css=".checkbox")
  private WebElement btn4;

  @Test
  public void lab42() {

     WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();  
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);

    driver.get("https://blazedemo.com/");


    PageFactory.initElements(driver, this);
    
    fromPortOption.click();
    toPortOption.click();
    btn1.click();
    btn2.click();
    inputName.sendKeys("Vaskiris");
    address.sendKeys("rua nao sei onde");
    city.sendKeys("Man UTD");
    state.sendKeys("Man City");
    zipCode.sendKeys("12345");
    cardTypeOption.click();
    btn3.click();
    creditCardNumber.sendKeys("12345");
    nameOnCard.sendKeys("treble");
    btn4.click();
    btn1.click();
    assertThat(driver.getTitle()).isEqualTo("BlazeDemo Confirmation");
  }
}