package org.example.model;

import org.example.common.Values;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewContact extends BasePage {

    @FindBy(name = "crm_contact[lastName]")
    private WebElement lastName;

    @FindBy(name = "crm_contact[firstName]")
    private WebElement firstName;

    @FindBy(xpath = "/html/body/div[2]/div/div[2]/div[3]/form/div[2]/div[3]/div/div[1]/div[2]/fieldset/div[2]/div[1]/div[2]/div/div[1]/a/span[1]")
    private WebElement organizationField;

    @FindBy(xpath = "/html/body/div[7]/div/input")
    private WebElement organizationView;

    @FindBy(xpath = "/html/body/div[7]/ul[2]/li/div/span")
    private WebElement organizationChoice;

    @FindBy(name = "crm_contact[jobTitle]")
    private WebElement jobTitle;

    @FindBy(xpath = "/html/body/div[2]/div/div[2]/div[3]/form/div[1]/div/div/div[2]/div[1]/div[4]/button")
    private WebElement submitButton;

    public NewContact(WebDriver driver) {
        super(driver);
    }

    public NewContact enterLastName() {
        lastName.sendKeys(Values.NAME_FIELD);
        return this;
    }

    public NewContact enterFirstName() {
        firstName.sendKeys(Values.NAME_FIELD);
        return this;
    }

    public NewContact clickOrganizationField() {
        organizationField.click();
        return this;
    }

    public NewContact enterOrganizationView() {
        organizationView.sendKeys(Values.ORGANIZATION_VIEW);
        return this;
    }

    public NewContact clickOrganizationChoice() {
        organizationChoice.click();
        return this;
    }

    public NewContact enterJobTitle() {
        jobTitle.sendKeys(Values.JOB_TITLE);
        return this;
    }

    public AllContacts clickSubmit() {
        submitButton.click();
        return new AllContacts(driver);
    }

}
