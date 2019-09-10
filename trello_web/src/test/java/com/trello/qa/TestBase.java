package com.trello.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class TestBase {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        driver.manage().window().maximize(); // для полноэкранного режима

        openSite("https://trello.com"); //Alt enter for method creation
        login("mariposamilagrosa21@gmail.com", "20031990kate");
    }

    @BeforeMethod
    public void isOnHomePage(){
        if(!isTherePersonalBoards())
        {
            returnToHomePage();
        }
    }

    public void login(String email, String password) {
        click(By.cssSelector("[href='/login']"));
        type(By.cssSelector("[type=email]"), email);
        type(By.cssSelector("[type=password]"), password);
        click(By.id("login"));
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public void type(By locator, String text) {
        driver.findElement(locator).click();
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);

    }

    public String getBoardNameFromBoardPage(){
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='js-board-editing-target board-header-btn-text']")));
        return driver.findElement(By.cssSelector("[class='js-board-editing-target board-header-btn-text']")).getText();

    }

    public void testBoardCreation(String name)  {
            click(By.cssSelector("[class='board-tile mod-add']"));
            type(By.cssSelector("[class='subtle-input']"), name);
            click(By.cssSelector("[class= 'primary']"));
        }

    public void openSite(String url) {
        driver.get(url);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    public void returnToHomePage() {
        if(isElementPresent(By.cssSelector("._3gUubwRZDWaOF0._2WhIqhRFBTG7Ry._2NubQcQM83YCVV"))){
            new WebDriverWait(driver, 15)
                 .until(ExpectedConditions
                 .stalenessOf(driver.findElement(By.cssSelector("._3gUubwRZDWaOF0._2WhIqhRFBTG7Ry._2NubQcQM83YCVV"))));
            click(By.cssSelector("a[href='/']")); //stalenessOf - ждать, пока элемент исчезнет
            click(By.cssSelector("a[href='/']"));
        } else
            click(By.cssSelector("a[href='/']"));
    }


    public int getTeamsCount() {
            new WebDriverWait(driver, 5)  //wait проверяет каждый 500милискунд, есть ли элемент, это селениум
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='_mtkwfAlvk6O3f']/../../..//li")));
        return driver.findElements(By.xpath("//*[@class='_mtkwfAlvk6O3f']/../../..//li")).size();
    }

    protected String getTeamNameFromTeamPage() {
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h1")));
        return driver.findElement(By.cssSelector("h1")).getText();
    }

    public void fillTeamCreationForm(String teamName, String description) {
        type(By.cssSelector("[data-test-id='header-create-team-name-input']"), teamName);
        type(By.cssSelector("textarea"), description);
    }

    public void selectCreateTeamFromDropDown() {
        click(By.cssSelector("[data-test-id='header-create-team-button']"));
    }

    public void clickOnPlusButtonOnHeader() {
        click(By.cssSelector("[data-test-id='header-create-menu-button']"));
    }

    public void clickContinueButton() {
        click(By.cssSelector("[type=submit]"));
    }

    public boolean isUserLoggedIn() {
        return isElementPresent(By.cssSelector("[data-test-id='header-member-menu-button']"));
    }

    public boolean isElementPresent(By locator){
        return driver.findElements(locator).size()>0;
    }

    public int getBoardsCount() {
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class=\"icon-lg icon-member\"]/../../..//li")));
      return driver.findElements(By.xpath("//*[@class=\"icon-lg icon-member\"]/../../..//li")).size();
    }

    public int getAfter()
    {
        driver.findElements(By.xpath("//*[@class=\"icon-lg icon-member\"]/../../..//li"));
        return getAfter()-1;
    }

    public void deleteTeam() {

        new WebDriverWait(driver,10)
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".quiet-button")));
        click(By.cssSelector(".quiet-button"));
        click(By.cssSelector(".js-confirm.full.negate"));
    }

    public void openSettings() {
        click(By.cssSelector("[class='icon-gear icon-sm OiX3P2i2J92Xat']"));
    }

    public void clickOnFirstTeam() {
        click(By.xpath("By.xpath(\"//*[@class='_mtkwfAlvk6O3f']/../../..//li\""));
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
}
