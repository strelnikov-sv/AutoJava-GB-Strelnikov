package org.example.CRM;

import org.example.model.HomePage;
import org.example.model.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


public class CRM1 extends BaseTest {

    @BeforeEach
    void testLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login();
    }

    @Test
    @Tag("Заполнение")
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
