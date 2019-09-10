package com.trello.qa;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BoardDeletionTest extends TestBase {
    @BeforeMethod
    public void isThereBoard(){
        if(getBoardsCount()==0)
            testBoardCreation("like");
    }
    @Test
    public void deleteBoardFromLeftNavMenu() {
        int before = getBoardsCount();
        deleteFirstBoard();

        int after = getBoardsCount();
        Assert.assertEquals(after, before - 1);
    }

}
