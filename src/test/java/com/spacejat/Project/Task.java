package com.spacejat.Project;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import static io.appium.java_client.remote.MobileCapabilityType.*;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;
public class Task {

    public static void main(String[]args) throws MalformedURLException, InterruptedException {
        System.out.println("Hello and welcome!");
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(PLATFORM_NAME, "Android");
        caps.setCapability(DEVICE_NAME, "Redmi Note 10 5G");
        caps.setCapability(AUTOMATION_NAME, "UiAutomator2");
        caps.setCapability(UDID, "zxcuugbumjukfuba");
        caps.setCapability("autoGrantPermissions", "true");
        caps.setCapability(APP, "C:\\Users\\zero\\IdeaProjects\\SpaceJat\\src\\main\\resources\\General-Store.apk");
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        AndroidDriver driver = new AndroidDriver(url, caps);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Currently running:" + driver.getCurrentPackage());



        Thread.sleep(3000);
        driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
        scrollToAndClick(driver,"Egypt");
        Thread.sleep(2000);
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("shehab");
        driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        driver.findElement(By.xpath("(//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\"])[1]")).click();
        driver.findElement(By.xpath("(//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\"])[2]")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        verifyPricesAndTotal(driver);
        Thread.sleep(5000);
        driver.closeApp();
    }
    private static void scrollToAndClick(AndroidDriver driver,String text) {
        // Create the UiAutomator command to scroll and find the item
        String uiAutomatorCommand = "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"" + text + "\"))";

        // Execute the UiAutomator command to scroll
        driver.findElement(MobileBy.AndroidUIAutomator(uiAutomatorCommand)).click();
    }
    private static void verifyPricesAndTotal(AndroidDriver driver) {
        // Locate the first price element
        WebElement priceElement1 = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productPrice' and @text='$120.0']"));
        // Locate the second price element
        WebElement priceElement2 = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productPrice' and @text='$160.97']"));

        // Get the text content of the price elements
        String price1Text = priceElement1.getText();
        String price2Text = priceElement2.getText();

        // Extract the numeric values from the price strings
        double price1 = Double.parseDouble(price1Text.substring(1)); // Remove the '$'
        double price2 = Double.parseDouble(price2Text.substring(1));

        // Calculate the sum of the prices
        double expectedTotal = price1 + price2;

        // Locate the total amount element
        WebElement totalElement = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl"));

        // Get the text content of the total amount element
        String actualTotalText = totalElement.getText();
        // Extract the numeric value from the total amount string
        double actualTotal = Double.parseDouble(actualTotalText.substring(1));

        // Check if the calculated total matches the actual total
        if (expectedTotal == actualTotal) {
            System.out.println("The sum of the prices matches the total amount.");
        } else {
            System.out.println("The sum of the prices does not match the total amount.");
        }
    }
    }


