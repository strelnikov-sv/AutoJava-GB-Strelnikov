package org.example.model;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AllContacts extends BasePage {

    @FindBy(linkText = "Создать контактное лицо")
    public WebElement createNewContact;

    public AllContacts(WebDriver driver) {
        super(driver);
    }

    public NewContact clickOnCreateNewContact() {
        createNewContact.click();
        return new NewContact(driver);
    }

    @Step(value = "Проверка, что открылась страница Все Контактные лица")
    public AllContacts checkPage() {
        String element = driver.findElement(By.cssSelector(".oro-subtitle")).getText();
        Assertions.assertTrue(element.contains("Все Контактные лица"));
        return this;
    }

}
