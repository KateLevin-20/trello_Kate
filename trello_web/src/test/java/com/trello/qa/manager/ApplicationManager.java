package com.trello.qa.manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

public class ApplicationManager{
WebDriver driver;
TeamHelper teamHelper;
BoardHelper boardHelper;
SessionHelper sessionHelper;
    public String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {

       if(browser.equals(BrowserType.CHROME)){
           driver = new ChromeDriver();
       } if(browser.equals(BrowserType.FIREFOX)){
            driver = new FirefoxDriver();
        }if(browser.equals(BrowserType.IE)){
            driver = new InternetExplorerDriver();
       }


        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();// для полноэкранного режима

        teamHelper = new TeamHelper(driver);
        boardHelper = new BoardHelper(driver);
        sessionHelper = new SessionHelper(driver);


        sessionHelper.openSite("https://trello.com"); //Alt enter for method creation
        sessionHelper.login("mariposamilagrosa21@gmail.com", "20031990kate");
    }

    public void stop() {
        driver.quit();
    }

    public TeamHelper getTeamHelper() {
        return teamHelper;
    }

    public BoardHelper getBoardHelper() {
        return boardHelper;
    }

    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }
}
