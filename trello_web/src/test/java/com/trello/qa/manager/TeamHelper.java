package com.trello.qa.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TeamHelper extends HelperBase {

    public TeamHelper(WebDriver driver) {
        super(driver);
    }

    public void waitForElementAndClick(By locator, int time){
        new WebDriverWait(driver, time).until(ExpectedConditions.elementToBeClickable(locator)).click();

    }

    public int getTeamsCount() {
        new WebDriverWait(driver, 5)  //wait проверяет каждый 500милискунд, есть ли элемент, это селениум
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='_mtkwfAlvk6O3f']/../../..//li")));
        return driver.findElements(By.xpath("//*[@class='_mtkwfAlvk6O3f']/../../..//li")).size();
    }

    public void fillTeamCreationForm(String teamName, String description) {
        type(By.cssSelector("[data-test-id='header-create-team-name-input']"), teamName);
        type(By.cssSelector("textarea"), description);
    }

    public void selectCreateTeamFromDropDown() {
        click(By.cssSelector("[data-test-id='header-create-team-button']"));
    }



    public void clickContinueButton() {
        click(By.cssSelector("[type=submit]"));
    }

    public void clickOnFirstTeam() {

        click(By.xpath("//*[@class='_mtkwfAlvk6O3f']/../../..//li"));
    }

    public String getTeamNameFromTeamPage() throws InterruptedException {
        Thread.sleep(15);
        // new WebDriverWait(driver, 15)
        //        .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h1")));
        return driver.findElement(By.cssSelector("h1")).getText();
    }

    public void deleteTeam() {

        new WebDriverWait(driver,10)
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".quiet-button")));
        click(By.cssSelector(".quiet-button"));
        click(By.cssSelector(".js-confirm.full.negate"));
    }
    public void openSettings() throws InterruptedException {
        //waitForElementAndClick(By.xpath("//*[@class='icon-gear icon-sm OiX3P2i2J92Xat']/../../.."), 20);
        Thread.sleep(5000);
        click(By.xpath("//*[@class='icon-gear icon-sm OiX3P2i2J92Xat']/../../.."));
        //   click(By.cssSelector("[href$=account]"));
        //waitForElementAndClick(By.cssSelector("li .icon-gear.icon-sm.OiX3P2i2J92Xat"), 30);
    }


    public void initEditTeamProfile() {
        click(By.cssSelector(".js-edit-profile"));
        //waitForElementAndClick(By.cssSelector(".js-edit-profile"), 10);
    }

    public void changeTeamProfile(String name, String description) {
        type(By.name("displayName"), name);
        type(By.name("desc"), description);
    }

    public void confirmEditTaem() {
        click(By.cssSelector(".js-submit-profile"));
    }
}
