package org.example.CRM;

import org.example.model.HomePage;
import org.example.model.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class CRM2 extends BaseTest {

    @BeforeEach
    void testLogin2() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login();
    }

    @Test
    @Tag("Заполнение")
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
