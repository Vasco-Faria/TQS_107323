package tqs;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.seljup.DockerBrowser;
import io.github.bonigarcia.seljup.SeleniumJupiter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.github.bonigarcia.seljup.BrowserType;

@ExtendWith(SeleniumJupiter.class)
public class DockerTest {
   
    @Test
    public void test(@DockerBrowser(type = BrowserType.CHROME) ChromeDriver driver) {
        driver.get("https://blazedemo.com/");
        driver.manage().window().setSize(new Dimension(775, 695));
        driver.findElement(By.name("fromPort")).click();
        {
            WebElement dropdown = driver.findElement(By.name("fromPort"));
            dropdown.findElement(By.xpath("//option[. = 'Philadelphia']")).click();
        }
        {
            WebElement element = driver.findElement(By.name("fromPort"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).clickAndHold().perform();
        }
        {
            WebElement element = driver.findElement(By.name("fromPort"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        {
            WebElement element = driver.findElement(By.name("fromPort"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).release().perform();
        }
        driver.findElement(By.cssSelector("form")).click();
        {
            WebElement dropdown = driver.findElement(By.name("toPort"));
            dropdown.findElement(By.xpath("//option[. = 'New York']")).click();
        }
        {
            WebElement element = driver.findElement(By.name("toPort"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).clickAndHold().perform();
        }
        {
            WebElement element = driver.findElement(By.name("toPort"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        {
            WebElement element = driver.findElement(By.name("toPort"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).release().perform();
        }
        driver.findElement(By.cssSelector(".btn-primary")).click();
        driver.findElement(By.cssSelector("tr:nth-child(2) .btn")).click();
        driver.findElement(By.id("inputName")).click();
        driver.findElement(By.id("inputName")).sendKeys("Vasco");
        driver.findElement(By.cssSelector(".control-group:nth-child(3)")).click();
        driver.findElement(By.id("address")).click();
        driver.findElement(By.id("address")).sendKeys("rua nao sei onde");
        driver.findElement(By.id("city")).click();
        driver.findElement(By.id("city")).sendKeys("Meadela");
        driver.findElement(By.id("state")).click();
        driver.findElement(By.id("state")).sendKeys("Viana do Castelo");
        driver.findElement(By.id("zipCode")).click();
        driver.findElement(By.id("zipCode")).sendKeys("12345");
        driver.findElement(By.id("creditCardNumber")).click();
        driver.findElement(By.id("creditCardNumber")).sendKeys("12345");
        driver.findElement(By.id("creditCardYear")).click();
        driver.findElement(By.id("creditCardYear")).sendKeys("2023");
        driver.findElement(By.id("nameOnCard")).click();
        driver.findElement(By.id("nameOnCard")).sendKeys("Vasco F");
        driver.findElement(By.cssSelector(".checkbox")).click();
        driver.findElement(By.cssSelector(".btn-primary")).click();
    }

    
    // docker run -d -p 4444:4444 -p 7900:7900 --shm-size="2g" --name chrome selenium/standalone-chrome

    //imagem docker do firefox nao funciona...
    //mesmo com esta do chrome nao esta a funcionar tambem

    /*
     * [ERROR] Errors: 
     *   [ERROR]   DockerTest.test(ChromeDriver) » ParameterResolution Failed to resolve parameter [org.openqa.selenium.chrome.ChromeDriver arg0] in method [public void tqs.DockerTest.test(org.openqa.selenium.chrome.ChromeDriver)]: There was an error creating WebDriver object for Chrome

     */

     
}