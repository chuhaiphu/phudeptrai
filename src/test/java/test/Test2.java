package test;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Test2 {

    @Test
    public void test() {
        WebDriver driver = driverFactory.getChromeDriver();
        boolean check = true;
        try {
            driver.get("http://live.techpanda.org/");

            WebElement mobileMenu = driver.findElement(By.xpath("//*[@id=\"nav\"]/ol/li[1]/a"));
            System.out.println(mobileMenu.getText());
            mobileMenu.click();

            WebElement sonyXperiaPriceElement = driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[2]/div/h2/a"));

            driver.findElement(By.xpath("//h2[contains(., 'Sony Xperia')]/a")).click();


            WebElement sonyXperiaDetailPriceElement = driver.findElement(By.xpath("//span[@class='price']"));

            WebElement price1 = driver.findElement(By.xpath("//*[@id=\"product-price-1\"]/span"));
            WebElement price2 = driver.findElement(By.xpath("//*[@id=\"product-price-1\"]/span"));

            Assert.assertEquals(price1.getText().trim(), price2.getText().trim(), "Prices do not match.");

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test failed: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
