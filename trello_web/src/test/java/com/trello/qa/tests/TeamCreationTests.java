package com.trello.qa.tests;

import com.trello.qa.manager.TeamData;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.swing.text.html.HTMLDocument;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TeamCreationTests extends TestBase {
    @DataProvider
    public Iterator<Object[]> validTeams() {
       List<Object[]> list = new ArrayList<>();
       list.add(new Object[]{"name", "description"});
       list.add(new Object[]{"NAME", "DESCRIPTION"});
       list.add(new Object[]{"1234", "745226"});
       list.add(new Object[]{"&*$%*", "^%^*$%"});
       list.add(new Object[]{"name", ""});

        return list.listIterator();
    }


    @BeforeMethod
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
            app.getTeamHelper().fillTeamCreationForm(new TeamData()
                    .withTeamName("service")
                    .withDescription("lalalal"));
            app.getTeamHelper().clickContinueButton();
        Thread.sleep(10);

            String createdTeamName = app.getTeamHelper().getTeamNameFromTeamPage();
            app.getSessionHelper().returnToHomePage();
            int after = app.getTeamHelper().getTeamsCount();
            Assert.assertEquals(after, before+1);
            Assert.assertEquals(createdTeamName.toLowerCase(), teamName.toLowerCase());
        }

    @DataProvider
    public Iterator<Object[]> validTeamsfromcsv() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader =
                new BufferedReader(new FileReader
                        (new File("src/test/resources/Team.csv")));
        String line = reader.readLine();
        while (line!=null){
            String[] split = line.split(",");
            list.add(new Object[] {
                    new TeamData()
                            .withTeamName(split[0])
                            .withDescription(split[1])});

            line = reader.readLine();
        }

        return  list.iterator();
    }

    @Test(dataProvider = "validTeams")
    public void testTeamCreationFromPlusButtonOnHeaderWithDataProvider(String teamName, String description) throws InterruptedException {

        TeamData team = new TeamData().withTeamName(teamName).withDescription(description);
        int before = app.getTeamHelper().getTeamsCount();
        app.getTeamHelper().clickOnPlusButtonOnHeader();
        app.getTeamHelper().selectCreateTeamFromDropDown();
        app.getTeamHelper().fillTeamCreationForm(new TeamData());

        app.getTeamHelper().clickContinueButton();
        Thread.sleep(10);

        String createdTeamName = app.getTeamHelper().getTeamNameFromTeamPage();
        app.getSessionHelper().returnToHomePage();
        int after = app.getTeamHelper().getTeamsCount();
        Assert.assertEquals(after, before+1);
        Assert.assertEquals(createdTeamName.toLowerCase(), teamName.toLowerCase());
    }

}

