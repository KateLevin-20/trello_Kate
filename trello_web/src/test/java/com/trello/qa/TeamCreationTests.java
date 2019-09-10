package com.trello.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TeamCreationTests extends TestBase {
    @BeforeClass
    public void ensurePreconditionsLogin(){
     if(!isUserLoggedIn()){
         login("mariposamilagrosa21@gmail.com", "20031990kate");
     }
 }

    @Test
        public void testTeamCreationFromPlusButtonOnHeader()  {
            int before = getTeamsCount();
            clickOnPlusButtonOnHeader();
            selectCreateTeamFromDropDown();
            String teamName = "qa21-" + System.currentTimeMillis();
            fillTeamCreationForm(teamName, "descr qa 21");
            clickContinueButton();
            String createdTeamName = getTeamNameFromTeamPage();
            returnToHomePage();
            int after = getTeamsCount();
            Assert.assertEquals(after, before+1);
            Assert.assertEquals(createdTeamName.toLowerCase(), teamName.toLowerCase());
        }

}

