package com.trello.qa.tests;

import com.trello.qa.model.BoardData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BoardModificationTest extends TestBase {

    @BeforeMethod
    public void preconditions(){
        if(!app.getBoardHelper().isTherePersonalBoards()){
            app.getBoardHelper().boardCreation(new BoardData().setBoardName("board1"));
        }
    }

    @Test
    public void renameBoard(){
     app.getBoardHelper().clickOnFirstBoard();
     String newName = "April";
    app.getBoardHelper().changeBoardName(new BoardData().setBoardName(newName));
        Assert.assertTrue(app.getBoardHelper().findBoardByName(newName));

    }
}
