package com.trello.qa.tests;
import com.trello.qa.model.BoardData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BoardDeletionTest extends TestBase {
    @BeforeMethod
    public void isThereBoard(){
        if(app.getBoardHelper().getBoardsCount()==0)
            app.getBoardHelper().boardCreation(new BoardData().setBoardName("lallala"));
    }
    @Test
    public void deleteBoardFromLeftNavMenu() {
        int before = app.getBoardHelper().getBoardsCount();
        app.getBoardHelper().deleteFirstBoard();

        int after = app.getBoardHelper().getBoardsCount();
        Assert.assertEquals(after, before - 1);
    }

}
