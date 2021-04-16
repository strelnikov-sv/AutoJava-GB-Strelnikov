package org.example.model;

import org.example.common.Values;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = "prependedInput")
    public WebElement loginInput;
    @FindBy(name = "_password")
    public WebElement passwordInput;
    @FindBy(name = "_submit")
    public WebElement submitButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login() {
        driver.get(Values.LOGIN_PAGE_URL);

        LoginPage loginPage = new LoginPage(driver);

        loginPage.loginInput.sendKeys(Values.STUDENT_LOGIN);
        loginPage.passwordInput.sendKeys(Values.STUDENT_PASSWORD);
        loginPage.submitButton.click();
    }

    public LoginPage enterLogin(String login) {
        loginInput.sendKeys(login);
        return this;
    }

    public LoginPage enterPassword(String password) {
        loginInput.sendKeys(password);
        return this;
    }

    public HomePage clickLoginButton() {
        loginInput.click();
        return new HomePage(driver);
    }
}
