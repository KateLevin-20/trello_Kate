package com.trello.qa.tests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BoardCreationTest extends TestBase {
    @BeforeClass
    public void ensurePreconditionsLogin() {
        if (!app.getSessionHelper().isUserLoggedIn()) {
            app.getSessionHelper().login("mariposamilagrosa21@gmail.com", "20031990kate");
        }
    }

    @Test
    public void  createBoard()  {
        int before = app.getBoardHelper().getBoardsCount();
        String nameBoard = "Frida";
        app.getBoardHelper().testBoardCreation(nameBoard);
        String createdBoardName = app.getBoardHelper().getBoardNameFromBoardPage();
        app.getBoardHelper().confirmBoardCreation();

        app.getBoardHelper().returnToHomePage();
        int after = app.getBoardHelper().getBoardsCount();
        Assert.assertEquals(before, after-1);
        Assert.assertEquals(nameBoard, createdBoardName);

    }
}

