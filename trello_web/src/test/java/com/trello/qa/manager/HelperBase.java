package com.trello.qa.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;

public class HelperBase {
    WebDriver driver;

    public HelperBase(WebDriver driver) {
        this.driver = driver;
    }

    public void openSite(String url){ driver.get(url); }

   public void click(By locator) {driver.findElement(locator).click();
}

    public void type(By locator, String text) {

        if (text != null) {
            driver.findElement(locator).click();
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(text);

        }
    }


    public boolean isElementPresent(By locator){
        return driver.findElements(locator).size()>0;
    }

    public void returnToHomePage() {
        if(isElementPresent(By.cssSelector("._3gUubwRZDWaOF0._2WhIqhRFBTG7Ry._2NubQcQM83YCVV"))){
            new WebDriverWait(driver, 15)
                    .until(ExpectedConditions
                            .stalenessOf(driver.findElement(By.cssSelector("._3gUubwRZDWaOF0._2WhIqhRFBTG7Ry._2NubQcQM83YCVV"))));
            click(By.cssSelector("a[href='/']")); //stalenessOf - ждать, пока элемент исчезнет
            click(By.cssSelector("a[href='/']"));
        } else
            click(By.cssSelector("a[href='/']"));
    }

    public void clickOnPlusButtonOnHeader() {
        click(By.cssSelector("[data-test-id='header-create-menu-button']"));
    }
}

