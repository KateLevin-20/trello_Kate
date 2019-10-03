package com.trello.qa.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TeamModificationTest extends TestBase {

    @BeforeMethod
    public void precondition(){
        if(!app.getTeamHelper().isTeamPresent()){
            app.getTeamHelper().createTeam();
        }
    }

    @Test
    public void testRenameTeam() throws InterruptedException {
        app.getTeamHelper().clickOnFirstTeam();
        app.getTeamHelper().openSettings();
        app.getTeamHelper().initEditTeamProfile();
        app.getTeamHelper().changeTeamProfile("life", "death");
        app.getTeamHelper().confirmEditTaem();

        Thread.sleep(5000);

        Assert.assertEquals(app.getTeamHelper().getTeamNameFromTeamPage(), "life");

    }










}
