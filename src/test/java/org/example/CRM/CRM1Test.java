package org.example.CRM;

import io.qameta.allure.Feature;
import org.example.model.HomePage;
import org.example.model.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CRM1Test extends BaseTest {

    @Feature("Login CRM1")
    @BeforeEach
    void testLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login();
    }

    @Feature("Заполнение CRM1")
    @Test
    void testFilling() {
        HomePage homePage = new HomePage(driver);
        homePage.checkUrl();
        homePage
                .moveToMyProject()
                .clickOnCreateNewProjectPage()
                .enterNameField()
                .clickOrganizationField()
                .enterOrganizationView()
                .clickOrganizationChoice()
                .selectDivision()
                .selectCurator()
                .selectDirector()
                .selectAdministrator()
                .clickSubmit()
                .checkPage();
    }
}
