package org.example.CRM;

import io.qameta.allure.Feature;
import org.example.model.HomePage;
import org.example.model.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CRM2Test extends BaseTest {

    @Feature("Login CRM2")
    @BeforeEach
    void testLogin2() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login();
    }

    @Feature("Заполнение CRM2")
    @Test
    void testFilling() {

        HomePage homePage = new HomePage(driver);
        homePage.checkUrl();
        homePage
                .moveToMyContacts()
                .clickOnCreateNewContact()
                .enterLastName()
                .enterFirstName()
                .clickOrganizationField()
                .enterOrganizationView()
                .clickOrganizationChoice()
                .enterJobTitle()
                .clickSubmit()
                .checkPage();
    }
}
