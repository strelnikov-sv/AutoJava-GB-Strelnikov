package org.example.model;

import io.qameta.allure.Step;
import org.example.common.Values;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class NewProjectPage extends BasePage {

    @FindBy(name = "crm_project[name]")
    private WebElement nameField;

    @FindBy(xpath = "/html/body/div[2]/div/div[2]/div[3]/form/div[2]/div[3]/div/div[1]/div[2]/fieldset/div[1]/div[2]/div[2]/div/div[1]/a/span[1]")
    private WebElement organizationField;

    @FindBy(xpath = "/html/body/div[6]/div/input")
    private WebElement organizationView;

    @FindBy(xpath = "/html/body/div[6]/ul[2]/li/div/span")
    private WebElement organizationChoice;

    @FindBy(name = "crm_project[businessUnit]")
    private WebElement division;

    @FindBy(name = "crm_project[curator]")
    private WebElement curator;

    @FindBy(name = "crm_project[rp]")
    private WebElement director;

    @FindBy(name = "crm_project[manager]")
    private WebElement administrator;

    @FindBy(xpath = "/html/body/div[2]/div/div[2]/div[3]/form/div[1]/div/div/div[2]/div[1]/div[4]/button")
    private WebElement submitButton;

    public NewProjectPage(WebDriver driver) {
        super(driver);
    }

    @Step(value = "Ввод Наименования")
    public NewProjectPage enterNameField() {
        nameField.sendKeys(Values.NAME_FIELD);
        return this;
    }

    @Step(value = "Выбор поля организации")
    public NewProjectPage clickOrganizationField() {
        organizationField.click();
        return this;
    }

    @Step(value = "Ввод названия организации")
    public NewProjectPage enterOrganizationView() {
        organizationView.sendKeys(Values.ORGANIZATION_VIEW);
        return this;
    }

    @Step(value = "Выбор нужной организации")
    public NewProjectPage clickOrganizationChoice() {
        organizationChoice.click();
        return this;
    }

    @Step(value = "Выбор подразделения")
    public NewProjectPage selectDivision() {
        Select Choice = new Select(division);
        Choice.selectByVisibleText(Values.DIVISION);
        return this;
    }

    @Step(value = "Выбор куратор")
    public NewProjectPage selectCurator() {
        Select Choice = new Select(curator);
        Choice.selectByVisibleText(Values.NAME);
        return this;
    }

    @Step(value = "Выбор руководителя")
    public NewProjectPage selectDirector() {
        Select Choice = new Select(director);
        Choice.selectByVisibleText(Values.NAME);
        return this;
    }

    @Step(value = "Выбор админимтратора")
    public NewProjectPage selectAdministrator() {
        Select Choice = new Select(administrator);
        Choice.selectByVisibleText(Values.NAME);
        return this;
    }

    @Step(value = "Сохранить и закрыть")
    public AllProjectPage clickSubmit() {
        submitButton.click();
        return new AllProjectPage(driver);
    }

}
