package org.example.model;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AllProjectPage extends BasePage {

    @FindBy(linkText = "Создать проект")
    public WebElement createNewProjectPage;

    public AllProjectPage(WebDriver driver) {
        super(driver);
    }

    public NewProjectPage clickOnCreateNewProjectPage() {
        createNewProjectPage.click();
        return new NewProjectPage(driver);
    }

    //Проверка, что открылась страница Все Проекты
    public AllProjectPage checkPage() {
        String element = driver.findElement(By.cssSelector(".oro-subtitle")).getText();
        Assertions.assertTrue(element.contains("Все Проекты"));
        return this;
    }
}
