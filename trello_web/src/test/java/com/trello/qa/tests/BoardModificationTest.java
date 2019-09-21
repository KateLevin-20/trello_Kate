package com.trello.qa.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import javax.swing.*;

public class BoardModificationTest extends TestBase {

    @Test
    public void renameBoard(){
     app.getBoardHelper().clickOnFirstBoard();
     app.getBoardHelper().clickOnBoardNameInTheLeftCorner();

    //    new Actions((WebDriver) app).moveToElement(((WebDriver) app).
    //            findElement(By.cssSelector("[class='js-board-editing-target board-header-btn-text']"))).click().perform();
    //    ((WebDriver) app).findElement(By.cssSelector("[class='js-board-editing-target board-header-btn-text']")).click();


     app.getBoardHelper().changeBoardName("hello");
     app.getBoardHelper().returnToHomePage();

    }
}
