package com.trello.qa.tests;
import com.trello.qa.manager.TeamData;
import com.trello.qa.model.BoardData;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BoardCreationTest extends TestBase {
    @BeforeClass
    public void ensurePreconditionsLogin() {
        if (!app.getSessionHelper().isUserLoggedIn()) {
            app.getSessionHelper().login("mariposamilagrosa21@gmail.com", "20031990kate");
        }
    }

    @DataProvider
    public Iterator<Object[]> validBoards() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"name"});
        list.add(new Object[]{"NAME"});
        list.add(new Object[]{"1234"});
        list.add(new Object[]{"&*$%*"});

        return list.listIterator();
    }


    @Test(dataProvider = "validBoardsfromcsv")
    public void  createBoardvalidBoardsfromcsv(String boardName) {
        BoardData board = new BoardData().setBoardName(boardName);
        int before = app.getBoardHelper().getBoardsCount();

        app.getBoardHelper().boardCreation(board);
        String createdBoardName = app.getBoardHelper().getBoardNameFromBoardPage();
        app.getBoardHelper().confirmBoardCreation();

        app.getBoardHelper().returnToHomePage();
        int after = app.getBoardHelper().getBoardsCount();
        Assert.assertEquals(before, after - 1);
    }

    @Test(dataProvider = "validBoards")
    public void  createBoardDP(String boardName)  {
        BoardData board = new BoardData().setBoardName(boardName);
        int before = app.getBoardHelper().getBoardsCount();

        app.getBoardHelper().boardCreation(board);
        String createdBoardName = app.getBoardHelper().getBoardNameFromBoardPage();
        app.getBoardHelper().confirmBoardCreation();

        app.getBoardHelper().returnToHomePage();
        int after = app.getBoardHelper().getBoardsCount();
        Assert.assertEquals(before, after-1);
       // Assert.assertEquals("Frida", createdBoardName);

    }

    @DataProvider
    public Iterator<Object[]> validBoardsfromcsv() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader =
                new BufferedReader(new FileReader
                        (new File("src/test/resources/Board.csv")));
        String line = reader.readLine();
        while (line!=null){
            String[] split = line.split(" ");
            list.add(new Object[] {new BoardData().setBoardName(split[0])});

            line = reader.readLine();
        }


        return  list.iterator();
    }

    @Test
    public void  createBoard()  {
        int before = app.getBoardHelper().getBoardsCount();

        app.getBoardHelper().boardCreation(new BoardData().setBoardName("Frida"));
        String createdBoardName = app.getBoardHelper().getBoardNameFromBoardPage();
        app.getBoardHelper().confirmBoardCreation();

        app.getBoardHelper().returnToHomePage();
        int after = app.getBoardHelper().getBoardsCount();
        Assert.assertEquals(before, after-1);
        Assert.assertEquals("Frida", createdBoardName);

    }
}

