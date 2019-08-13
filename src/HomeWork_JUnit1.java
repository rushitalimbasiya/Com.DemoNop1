import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/*
1.	http://demo.nopcommerce.com/, Click on Appare lclick on clothing from left side menu sort by low to high price
 display 3 per page click on list(view) take a screen shot add to comparison custom t-shirt and oversized women t-shirt
  take again screen short with green line on top with message "The product has been added to your product comparison"
  click on product comparison from green line link assert product name clear compare list
   assert message no product for comparison.
 */


public class HomeWork_JUnit1 {
    public static void main(String[] args) throws IOException {
        WebDriver driver;
        System.setProperty("webdriver.chrome.driver","src\\Resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://demo.nopcommerce.com");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        //Click on Apparel
        driver.findElement(By.xpath("//ul[@class='top-menu notmobile']/li[3]/a")).click();
        // click on clothing from left side menu
        driver.findElement(By.xpath("//ul[@class='sublist']/li[2]/a")).click();
        //click on sort by
        driver.findElement(By.id("products-orderby")).click();
        //click on low to high price
        driver.findElement(By.xpath("//select[@id='products-orderby']/option[4]")).click();
        //click on display
        driver.findElement(By.id("products-pagesize")).click();
        //click on 3 par page
        driver.findElement(By.xpath("//select[@id='products-pagesize']/option[1]")).click();
        //click on list(view)
        driver.findElement(By.cssSelector(".product-viewmode>a:nth-child(3)")).click();

        //for screenshot
        String dateAndTimeStamp = new SimpleDateFormat("yyyymmddhhmmss").format(new Date());
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("src\\Screenshot\\"+dateAndTimeStamp+"name"+".jpg"));

        //click on comparison for Custom T-Shirt
        driver.findElement(By.xpath("//div[@class='item-box'][2]/div/div[2]/div[3]/div[2]/input[2]")).click();
        // blank click just to wait to disappear green line
        driver.findElement(By.cssSelector("#bar-notification>div>p")).click();
        //click on comparison for oversized women t-shirt
        driver.findElement(By.xpath("//div[@class='item-box'][3]/div/div[2]/div[3]/div[2]/input[2]")).click();
        //click for green line
        driver.findElement(By.cssSelector("#bar-notification>div>p")).click();

        // Wait for take photo use "w" variable , wait till element visible or appear or present on page
        WebDriverWait w = new WebDriverWait(driver,60);
        w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#bar-notification>div>p")));

        //for screen short with green line on top with message "The product has been added to your product comparison"
        String dateAndTime = new SimpleDateFormat("yyyymmddhhmmss").format(new Date());
        File sc = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(sc, new File("src\\Screenshot\\"+dateAndTime+"name"+".jpg"));

         //click on product comparison from green line link
        driver.findElement(By.cssSelector("#bar-notification>div>p>a")).click();

        //assert for product name oversized Tshirt
        String expectedResult="Oversized Women T-Shirt";
        String actualResult = driver.findElement(By.xpath("//tr[@class='product-name']/td[2]/a")).getText();
        Assert.assertEquals(expectedResult,actualResult);

        //assert for product name custom T-shirt
        String exRe = "Custom T-Shirt";
        String acRe = driver.findElement(By.xpath("//tr[@class='product-name']/td[3]/a")).getText();
        Assert.assertEquals(exRe,acRe);

        // Click on the Clear list of comparison
        driver.findElement(By.cssSelector(".page-body>a")).click();

        //assert for You have no items to compare
        String expected="You have no items to compare.";
        String actual = driver.findElement(By.cssSelector(".page-body>div")).getText();
        Assert.assertEquals(expected,actual);

        driver.close();

            }
        }

















