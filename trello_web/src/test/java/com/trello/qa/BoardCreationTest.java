package com.trello.qa;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BoardCreationTest extends TestBase {

    @Test
        public void  createBoard() throws InterruptedException {
        int before = getBoardsCount();
        String nameBoard = "Frida";
        testBoardCreation(nameBoard);
        String createdBoardName = getBoardNameFromBoardPage();
        returnToHomePage();
        int after = getBoardsCount();
        Assert.assertEquals(before, after-1);
        Assert.assertEquals(nameBoard, createdBoardName);

        }


    public String getBoardNameFromBoardPage() throws InterruptedException {
        Thread.sleep(3000);
        return driver.findElement(By.cssSelector("[class='js-board-editing-target board-header-btn-text']")).getText();

    }

    public void testBoardCreation(String name)  {
            click(By.cssSelector("[class='board-tile mod-add']"));
            type(By.cssSelector("[class='subtle-input']"), name);
            click(By.cssSelector("[class= 'primary']"));
        }

    public void openSite(String url){
    driver.get(url);
}

    public int getBoardsCount() {
      return driver.findElements(By.xpath("//*[@class=\"icon-lg icon-member\"]/../../..//li")).size();
    }

    public int getAfter()
    {
        driver.findElements(By.xpath("//*[@class=\"icon-lg icon-member\"]/../../..//li"));
        return getAfter()-1;
    }
}

