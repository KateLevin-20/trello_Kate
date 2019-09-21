package com.trello.qa.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TeamCreationTests extends TestBase {
    @BeforeClass
    public void ensurePreconditionsLogin(){
     if(!app.getSessionHelper().isUserLoggedIn()){
         app.getSessionHelper().login("mariposamilagrosa21@gmail.com", "20031990kate");
     }
 }

    @Test
        public void testTeamCreationFromPlusButtonOnHeader() throws InterruptedException {
            int before = app.getTeamHelper().getTeamsCount();
            app.getTeamHelper().clickOnPlusButtonOnHeader();
            app.getTeamHelper().selectCreateTeamFromDropDown();
            String teamName = "qa21-" + System.currentTimeMillis();
            app.getTeamHelper().fillTeamCreationForm(teamName, "descr qa 21");
            app.getTeamHelper().clickContinueButton();
        Thread.sleep(10);

            String createdTeamName = app.getTeamHelper().getTeamNameFromTeamPage();
            app.getSessionHelper().returnToHomePage();
            int after = app.getTeamHelper().getTeamsCount();
            Assert.assertEquals(after, before+1);
            Assert.assertEquals(createdTeamName.toLowerCase(), teamName.toLowerCase());
        }

}

