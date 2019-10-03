package com.trello.qa.manager;

import com.trello.qa.model.BoardData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class BoardHelper extends HelperBase {
    public BoardHelper(WebDriver driver) {
        super(driver);
    }


    public String getBoardNameFromBoardPage() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='js-board-editing-target board-header-btn-text']")));
        return driver.findElement(By.cssSelector("[class='js-board-editing-target board-header-btn-text']")).getText();

    }

    //public void fillBoardCreationForm(String name) {
       // type(By.cssSelector("[data-test-id='header-create-board-title-input']"));

     //   if (isElementPresent(By.cssSelector(".W6rMLOx8U0MrPx"))) {
       //     click(By.cssSelector(".W6rMLOx8U0MrPx"));
      //      click(By.xpath("//nav[@class='SdlcRrTVPA8Y3K']//li[1]"));//no team
      //  }

  //  }

    public void boardCreation(BoardData board) {
        click(By.cssSelector("[class='board-tile mod-add']"));
        type(By.cssSelector("[class='subtle-input']"),  board.getBoardName());
        click(By.cssSelector("[class='button primary']"));
    }


    public void confirmBoardCreation() {
        clickOnPlusButtonOnHeader();
    }


    public int getBoardsCount() {
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class=\"icon-lg icon-member\"]/../../..//li")));
        return driver.findElements(By.xpath("//*[@class=\"icon-lg icon-member\"]/../../..//li")).size();
    }

    public void openSettings() {
        click(By.cssSelector("[class='icon-gear icon-sm OiX3P2i2J92Xat']"));
    }

    public boolean isTherePersonalBoards() {
        return isElementPresent(By.xpath("//*[@class='icon-lg icon-member']/../../.."));
    }

    public void clickPermanentlyDeleteBoard() {
        click(By.cssSelector("[class='quiet js-delete']"));
    }

    public void clickConfirmDeletion() {
        click(By.cssSelector("[class='js-confirm full negate']"));
    }

    public void clickCloseBoardButton() {
        click(By.cssSelector("[class='board-menu-navigation-item-link js-close-board']"));
    }

    public void clickMoreButton() {
        WebElement menuButton = driver.findElement(By.cssSelector(".js-show-sidebar"));

        if (menuButton.getCssValue("visibility").equals("visible"))
            click(By.cssSelector(".js-show-sidebar"));

        click(By.cssSelector(".js-open-more"));
    }

    public void clickOnFirstBoard() {
        click(By.xpath("//*[@class=\"icon-lg icon-member\"]/../../..//li"));
    }

    public void deleteFirstBoard() {
        clickOnFirstBoard();
        clickMoreButton();
        clickCloseBoardButton();
        clickConfirmDeletion();
        clickPermanentlyDeleteBoard();
        clickConfirmDeletion();
        returnToHomePage();
    }

    @AfterClass
    public void decreaseBoardsNumberToRequired() {
        while (getBoardsCount() > 2)
            deleteFirstBoard();
    }

    public int getAfter() {
        return driver.findElements(By.xpath("//*[@class=\"icon-lg icon-member\"]/../../..//li")).size() - 1;
    }

    public void clickOnBoardNameInTheLeftCorner() {
        click(By.cssSelector("[class='js-board-editing-target board-header-btn-text']"));
    }

    public void changeBoardName(BoardData board) {
        driver.findElement(By.cssSelector(".js-rename-board")).click();
        driver.findElement(By.cssSelector("input.js-board-name-input")).sendKeys(board.getBoardName());
        returnToHomePage();

    }

    public boolean findBoardByName(String name) {

        return driver.findElement(By.xpath("//*[@class='icon-lg icon-member']/../../..//li")).getText().equals(name);
    }

}
