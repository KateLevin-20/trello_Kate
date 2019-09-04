package com.trello.qa;

import com.sun.org.apache.xalan.internal.xsltc.dom.CurrentNodeListFilter;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BoardCreationTest extends TestBase {

@Test
        public void  createBoard(){
            testBoardCreation("myfirst");
            openSite("trello.com");

             Assert.assertTrue(isBoardPresented());
        }

        public void testBoardCreation(String name){
            click(By.cssSelector("[class='board-tile mod-add']"));
            type(By.cssSelector("[class='subtle-input']"), name);
            click(By.cssSelector("[class= 'primary']"));
            //new Actions(driver).moveToElement(driver.findElement(By.name("name"))).click().perform();
            //click(By.name("house"));
}

public boolean isBoardPresented(){
    click(By.xpath("//*[@id='header']/div[1]/a"));
    return driver.findElements(By.xpath("//*[@id='header']/div[1]/a")).size()>0;
    //оно не работает!!)))
}
public void openSite(String url){
    driver.get(url);
}
}

