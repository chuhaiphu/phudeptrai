package test;
import driver.driverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.Set;


public class Test4 {
    /*

Test Steps:

1. Go to http://live.techpanda.org/

2. Click on �MOBILE� menu

3. In mobile products list , click on �Add To Compare� for 2 mobiles (Sony Xperia & Iphone)

4. Click on �COMPARE� button. A popup window opens

5. Verify the pop-up window and check that the products are reflected in it

Heading "COMPARE PRODUCTS" with selected products in it.

6. Close the Popup Windows

*/

    @Test
    public void test4(){
        WebDriver webDriver = driverFactory.getChromeDriver();
        try {
            //step 1
            webDriver.get("http://live.techpanda.org/");


            //step 2
            WebElement mobileMenu = webDriver.findElement(By.xpath("//*[@id=\"nav\"]/ol/li[1]/a"));
            System.out.println(mobileMenu.getText());
            mobileMenu.click();


            //step 3

            WebElement addToCompare1 = webDriver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[2]/div/div[3]/ul/li[2]/a"));
            System.out.println(addToCompare1.getText());
            addToCompare1.click();

            WebElement addToCompare2 = webDriver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[3]/div/div[3]/ul/li[2]/a"));
            System.out.println(addToCompare2.getText());
            addToCompare2.click();

            //Step 4

            WebElement compareBtn = webDriver.findElement(By.xpath("//button[@title='Compare']"));
            compareBtn.click();
            int seconds = 5; // Your integer value
            Duration duration = Duration.ofSeconds(seconds);
            WebDriverWait wait = new WebDriverWait(webDriver, duration); // Adjust the timeout as needed
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));

            //Step 5
            Set<String> existingHandles = webDriver.getWindowHandles();
            int numberOfHandlesBeforeClick = existingHandles.size();

            Set<String> newHandles = webDriver.getWindowHandles();
            int numberOfHandlesAfterClick = newHandles.size();

            if (numberOfHandlesAfterClick > numberOfHandlesBeforeClick) {
                for (String handle : webDriver.getWindowHandles()) {
                    webDriver.switchTo().window(handle);

                    // Check if the popup window's title contains "COMPARE PRODUCTS"
                    String popupWindowTitle = webDriver.getTitle();
                    System.out.println(popupWindowTitle);
                    Assert.assertTrue(popupWindowTitle.contains("COMPARE PRODUCTS"), "Popup window title does not contain 'COMPARE PRODUCTS'.");

                    // Check if the selected products are reflected
                    WebElement popupContent = webDriver.findElement(By.id("popupContent")); // Replace "popupContent" with the actual element ID or XPath of the popup content
                    String popupText = popupContent.getText();

                    // Verify that both Sony Xperia and iPhone are in the popup content
                    Assert.assertTrue(popupText.contains("Sony Xperia"), "Sony Xperia is not in the popup content.");
                    Assert.assertTrue(popupText.contains("iPhone"), "iPhone is not in the popup content.");
                }
            }

        }catch (Exception e) {
            Assert.fail("some thing wrong at "+e);
        }
    }
}
