package test;
import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Test3 {
    @Test
    public void test3() {

/*

“Verify that you cannot add more product in cart than the product available in store”

Test Steps:

1. Go to http://live.techpanda.org/

2. Click on �MOBILE� menu

3. In the list of all mobile , click on �ADD TO CART� for Sony Xperia mobile

4. Change �QTY� value to 1000 and click �UPDATE� button. Expected that an error is displayed

"The requested quantity for "Sony Xperia" is not available.

5. Verify the error message

6. Then click on �EMPTY CART� link in the footer of list of all mobiles. A message "SHOPPING CART IS EMPTY" is shown.

7. Verify cart is empty

 */
        WebDriver driver = driverFactory.getChromeDriver();
        try {
            //Step 1
            driver.get("http://live.techpanda.org/");

            //Step 2
            WebElement mobileMenu = driver.findElement(By.xpath("//*[@id=\"nav\"]/ol/li[1]/a"));
            mobileMenu.click();

            //Step 3
            WebElement AddToCartBtn = driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[2]/div/div[3]/button"));
            AddToCartBtn.click();

            //Step 4
            WebElement qtyInput = driver.findElement(By.xpath("//*[@id=\"shopping-cart-table\"]/tbody/tr/td[4]/input"));
            qtyInput.clear();
            qtyInput.sendKeys("1000");
            WebElement updateBtn = driver.findElement(By.xpath("//*[@id=\"shopping-cart-table\"]/tbody/tr/td[4]/button"));
            updateBtn.click();

            //Step 5
            WebElement itemErrorMsg = driver.findElement(By.xpath("//*[@id=\"shopping-cart-table\"]/tbody/tr/td[2]/p"));
            String errorMsg = itemErrorMsg.getText().trim();
            System.out.println(errorMsg);
            String expectedErrorMsg = "The requested quantity for Sony Xperia is not available.";
            if(!expectedErrorMsg.trim().equals(errorMsg))  Assert.fail("not equal");

            //Step 6
            WebElement emptyCartHref = driver.findElement(By.xpath("//*[@id=\"empty_cart_button\"]/span/span"));
            emptyCartHref.click();

            //Step 7
            WebElement itemCartMsg = driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div/div[1]/h1"));
            String cartMsg = itemCartMsg.getText().trim();
            String expectedCartMsg = "SHOPPING CART IS EMPTY";
            if(!expectedCartMsg.trim().equals(cartMsg))  Assert.fail("not equal");

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test failed: " + e.getMessage());
        }
        finally {
            driver.quit();
        }
    }
}