package org.example.model;

import org.example.common.Values;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomePage extends BasePage {

    @FindBy(xpath = "//div[2]/ul/li[3]/a/span")
    public WebElement project;

    @FindBy(xpath = "//li[3]/ul/li[4]/a/span")
    public WebElement myProject;

    @FindBy(xpath = "/html/body/div[2]/div/header/div[2]/ul/li[1]/a/span")
    public WebElement counterparty;

    @FindBy(xpath = "/html/body/div[2]/div/header/div[2]/ul/li[1]/ul/li[5]")
    public WebElement contactPersons;


    public HomePage(WebDriver driver) {
        super(driver);
    }

    //Проверка, что открылась нужная страница
    public void checkUrl() {
        assertEquals(driver.getCurrentUrl(), Values.MENU_PAGE_URL);
    }

    public AllProjectPage moveToMyProject() {
        Actions actions = new Actions(driver);
        actions
                .moveToElement(project)
                .build()
                .perform();

        myProject.click();


        //Проверка, что открылась страница Мои проекты
        String element = driver.findElement(By.cssSelector(".breadcrumb > li:nth-child(2)")).getText();
        Assertions.assertTrue(element.contains("Мои проекты"));

        return new AllProjectPage(driver);
    }

    public AllContacts moveToMyContacts() {
        Actions actions = new Actions(driver);
        actions
                .moveToElement(counterparty)
                .build()
                .perform();
        contactPersons.click();

        //Проверка, что открылась страница Контактные лица
        String element = driver.findElement(By.xpath("//div[@id='breadcrumb']/ul/li[2]")).getText();
        Assertions.assertTrue(element.contains("Контактные лица"));

        return new AllContacts(driver);
    }
}
