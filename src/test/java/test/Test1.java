package test;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test1 {

    @Test
    public void test1() {
        /*

Test Steps

Step 1. Go to http://live.techpanda.org/

Step 2. Verify Title of the page

Step 3. Click on -> MOBILE -> menu

Step 4. In the list of all mobile , select SORT BY -> dropdown as name

Step 5. Verify all products are sorted by name

*/
        WebDriver driver = driverFactory.getChromeDriver();
        boolean check = true;
        try {
            driver.get("http://live.techpanda.org/");

          WebElement pageTitle = (driver.findElement(By.tagName("h2")));
          String text = pageTitle.getText().trim();
            String expectStep = "THIS IS DEMO SITE FOR";
            if(!text.equals(expectStep.trim())) {
                Assert.fail("not equal");
            }else {
                WebElement mobileMenu = driver.findElement(By.xpath("//*[@id=\"nav\"]/ol/li[1]/a"));
                System.out.println(mobileMenu.getText());
                mobileMenu.click();

                WebElement sortByDropdown = driver.findElement(By.cssSelector("select[title='Sort By']"));
                Select dropdown = new Select(sortByDropdown);
                dropdown.selectByVisibleText("Name");


                List<WebElement> productNames = driver.findElements(By.cssSelector("h2.product-name a"));
                List<String> actualProductNames = new ArrayList<>();

                for (WebElement productName : productNames) {
                    actualProductNames.add(productName.getText());
                }

                List<String> sortedProductNames = new ArrayList<>(actualProductNames);
                Collections.sort(sortedProductNames);

                if (actualProductNames.equals(sortedProductNames)) {
                    System.out.println("Sorted by name.");
                } else {
                    System.out.println("Not sorted by name.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test failed: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
