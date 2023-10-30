package test;
import driver.driverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pom.RegistrationPage;

import java.io.File;
import java.time.Duration;
import java.util.Set;

/* Verify can create an account in e-Commerce site and can share wishlist to other poeple using email.

Test Steps:

1. Go to http://live.techpanda.org/

2. Click on my account link

3. Click Create an Account link and fill New User information excluding the registered Email ID.

4. Click Register

5. Verify Registration is done. Expected account registration done.

6. Go to TV menu

7. Add product in your wish list - use product - LG LCD

8. Click SHARE WISHLIST

9. In next page enter Email and a message and click SHARE WISHLIST

10.Check wishlist is shared. Expected wishlist shared successfully.

 */

public class Test5 {
    @Test
    public void test5() {
        // Create a WebDriver instance
        WebDriver driver = driverFactory.getChromeDriver();

        try {
            // Step 1: Go to the e-commerce site
            driver.get("http://live.techpanda.org/");

            // Step 2: Click on My Account link
            WebElement Account = driver.findElement(By.cssSelector("a.skip-link.skip-account"));
            Account.click();

            WebElement myAccountLink = driver.findElement(By.linkText("My Account"));
            myAccountLink.click();

            // Step 3: Click Create an Account link and fill New User information
            WebElement createAccountLink = driver.findElement(By.xpath("//*[@id=\"login-form\"]/div/div[1]/div[2]/a"));
            createAccountLink.click();
            RegistrationPage registrationPage = new RegistrationPage(driver);
            registrationPage.enterFirstName("John");
            registrationPage.enterMiddleName("FFF");
            registrationPage.enterLastName("Doe");
            registrationPage.enterEmail("test7@example.com");
            registrationPage.enterPassword("password123");
            registrationPage.enterConfirmPassword("password123");

            //Step 4
            registrationPage.clickRegister();

            //Step 5
            WebElement registrationSuccessMessage = driver.findElement(By.xpath("//li[@class='success-msg']"));
            String expectedMessage = "Thank you for registering with Main Website Store.";
            if (registrationSuccessMessage.getText().equals(expectedMessage)) {
                System.out.println("Account registration successful.");
            } else {
                System.out.println("Account registration failed.");
            }

            //Step 6
            WebElement tvMenu = driver.findElement(By.linkText("TV"));
            tvMenu.click();

            //Step 7
            WebElement addToWishlistButton = driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div[1]/div[2]/ul/li[1]/div/div[3]/ul/li[1]/a"));
            addToWishlistButton.click();

            //Step 8
            WebElement shareWishlistButton = driver.findElement(By.xpath("//button[@title='Share Wishlist']"));
            shareWishlistButton.click();

            // Step 9: In the next page, enter Email and a message and click SHARE WISHLIST
            WebElement emailInputWishlist = driver.findElement(By.id("email_address"));
            WebElement messageInput = driver.findElement(By.id("message"));
            WebElement shareWishlistSubmit = driver.findElement(By.xpath("//button[@title='Share Wishlist']"));

            emailInputWishlist.sendKeys("friend@example.com");
            messageInput.sendKeys("Check out my wishlist!");
            shareWishlistSubmit.click();

            // Step 10: Check wishlist is shared
            WebElement wishlistSharedMessage = driver.findElement(By.xpath("//li[@class='success-msg']"));
            String shareMsg = wishlistSharedMessage.getText();
            String expectedSharedMessage = "Your Wishlist has been shared.";
            Assert.assertTrue(shareMsg.equals(expectedSharedMessage), "Not showing <Your Wishlist has been shared.> Msg");
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Step 4: Save the screenshot to a file
            File destination = new File("screenshot.png");
            FileUtils.copyFile(screenshot, destination);

            System.out.println("Screenshot captured and saved to: " + destination.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test failed: " + e.getMessage());
        } finally {
            //driver.quit();
        }
    }
}
