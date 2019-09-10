package com.trello.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BoardCreationTest extends TestBase {
    @BeforeClass
    public void ensurePreconditionsLogin() {
        if (!isUserLoggedIn()) {
            login("mariposamilagrosa21@gmail.com", "20031990kate");
        }
    }

    @Test
        public void  createBoard()  {
        int before = getBoardsCount();
        String nameBoard = "Frida";
        testBoardCreation(nameBoard);
        String createdBoardName = getBoardNameFromBoardPage();
        returnToHomePage();
        int after = getBoardsCount();
        Assert.assertEquals(before, after-1);
        Assert.assertEquals(nameBoard, createdBoardName);

        }

    public void openSite(String url){
    driver.get(url);
}

    public int getBoardsCount() {
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='icon-lg icon-member']/../../..")));
      return driver.findElements(By.xpath("//*[@class='icon-lg icon-member']/../../..//li")).size()-1;
    }

    public int getAfter()
    {
        driver.findElements(By.xpath("//*[@class=\"icon-lg icon-member\"]/../../..//li"));
        return getAfter()-1;
    }
    @AfterClass(enabled = false)
    public void decreaseBoardsNumberToRequired(){
        while (getBoardsCount()>2)
            deleteFirstBoard();
    }

}

