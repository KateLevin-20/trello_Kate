package com.trello.qa;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TeamCreationTests extends TestBase {



        @Test
        public void testTeamCreationFromPlusButtonOnHeader() throws InterruptedException {
            int before = getTeamsCount();
            clickOnPlusButtonOnHeader();
            selectCreateTeamFromDropDown();
            String teamName = "qa21";
            fillTeamCreationForm(teamName, "descr qa 21");
            clickContinueButton();
            String createdTeamName = getTeamNameFromTeamPage();
            returnToHomePage();
            int after = getTeamsCount();
            Assert.assertEquals(after, before+1);
            Assert.assertEquals(createdTeamName.toLowerCase(), teamName.toLowerCase());
        }

    public int getTeamsCount() {
        return driver.findElements(By.xpath("//*[@class='_mtkwfAlvk6O3f']/../../..//li")).size();
    }

    protected String getTeamNameFromTeamPage() {
        return driver.findElement(By.cssSelector("h1")).getText();
    }
    public void fillTeamCreationForm(String teamName, String description) {
        type(By.cssSelector("[data-test-id='header-create-team-name-input']"), teamName);
        type(By.cssSelector("textarea"), description);
    }

    public void selectCreateTeamFromDropDown() {
        click(By.cssSelector("[data-test-id='header-create-team-button']"));
    }

    public void clickOnPlusButtonOnHeader() {
        click(By.cssSelector("[data-test-id='header-create-menu-button']"));
    }
    public void clickContinueButton() {
        click(By.cssSelector("[type=submit]"));
    }

    public boolean isUserLoggedIn() {
        return isElementPresent(By.cssSelector("[data-test-id='header-member-menu-button']"));
    }

    public boolean isElementPresent(By locator){
        return driver.findElements(locator).size()>0;
    }

    public int getBoardsCount() {
      return driver.findElements(By.xpath("//*[@class=\"icon-lg icon-member\"]/../../..//li")).size();
    }

    public int getAfter()
    {
        driver.findElements(By.xpath("//*[@class=\"icon-lg icon-member\"]/../../..//li"));
        return getAfter()-1;
    }
}

