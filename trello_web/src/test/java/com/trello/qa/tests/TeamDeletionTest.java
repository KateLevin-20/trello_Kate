package com.trello.qa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TeamDeletionTest extends TestBase {

    @Test
    public void deleteTeamFromLeftNavMenu(){
        int before = app.getTeamHelper().getTeamsCount();
        app.getTeamHelper().clickOnFirstTeam();
        app.getBoardHelper().openSettings();
        app.getTeamHelper().deleteTeam();

        app.getBoardHelper().returnToHomePage();
        int after = app.getTeamHelper().getTeamsCount();
        Assert.assertEquals(after, before -1);

    }

}
