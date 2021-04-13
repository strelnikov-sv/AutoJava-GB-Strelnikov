package org.example.CRM;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CRM2 {
    private static final String LOGIN_PAGE_URL = "https://crm.geekbrains.space/user/login";
    private static final String STUDENT_LOGIN = "Applanatest";
    private static final String STUDENT_PASSWORD = "Student2020!";
    private static WebDriver driver;

    static {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    private static void login() {
        driver.get(LOGIN_PAGE_URL);

        WebElement loginTextInput = driver.findElement(By.id("prependedInput"));
        loginTextInput.sendKeys(STUDENT_LOGIN);

        String loginText = loginTextInput.getAttribute("value");
        assertEquals(STUDENT_LOGIN, loginText);

        WebElement passwordTextInput = driver.findElement(By.name("_password"));
        passwordTextInput.sendKeys(STUDENT_PASSWORD);

        String passwordText = loginTextInput.getAttribute("value");
        assertEquals(STUDENT_LOGIN, passwordText);

        WebElement loginButton = driver.findElement(By.name("_submit"));
        loginButton.click();

        //Проверка, что открылась страница Панель быстрого запуска
        String element = driver.findElement(By.xpath("//h1[contains(.,'Панель быстрого запуска')]")).getText();
        Assertions.assertTrue(element.contains("Панель быстрого запуска"));
    }

    private static void filling() throws InterruptedException {

        driver.findElement(By.xpath("/html/body/div[2]/div/header/div[2]/ul/li[1]/a/span")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div/header/div[2]/ul/li[1]/ul/li[5]")).click();

        //Проверка, что открылась страница Контактные лица
        String element = driver.findElement(By.xpath("//div[@id='breadcrumb']/ul/li[2]")).getText();
        Assertions.assertTrue(element.contains("Контактные лица"));

        driver.findElement(By.linkText("Создать контактное лицо")).click();

        //Проверка, что открылась страница Создать контактное лицо
        String element2 = driver.findElement(By.cssSelector(".user-name")).getText();
        Assertions.assertTrue(element2.contains("Создать контактное лицо"));

        driver.findElement(By.name("crm_contact[lastName]")).sendKeys("Тест0604111");
        driver.findElement(By.name("crm_contact[firstName]")).sendKeys("Тест0604111");


        driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[3]/form/div[2]/div[3]/div/div[1]/div[2]/fieldset/div[2]/div[1]/div[2]/div/div[1]/a/span[1]")).click();
        driver.findElement(By.xpath("/html/body/div[7]/div/input")).sendKeys("1234");
        Thread.sleep(3000);
        driver.findElement(By.xpath("/html/body/div[7]/ul[2]/li/div/span")).click();

        driver.findElement(By.name("crm_contact[jobTitle]")).sendKeys("Организация Тест");

        driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[3]/form/div[1]/div/div/div[2]/div[1]/div[4]/button")).click();

        //Проверка, что открылась страница Все Контактные лица
        String element3 = driver.findElement(By.cssSelector(".oro-subtitle")).getText();
        Assertions.assertTrue(element3.contains("Все Контактные лица"));

        Thread.sleep(5000);
        driver.quit();
    }

    @Test
    @Tag("Проверка успешного входа")
    void testLogin() {
        login();
    }

    @Test
    @Tag("Заполнение")
    void testFilling() throws InterruptedException {
        filling();
    }
}
