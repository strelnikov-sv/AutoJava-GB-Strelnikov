package org.example.project_reddit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

// Вход в аккаунт Reddit.com

class Login {
    //Используется старая версия сайта, чтобы обойти ограничение капчи на вход
    private static final String LOGIN_PAGE_URL = "https://old.reddit.com/";
    private static final String USERNAME_LOGIN = "Test1212125";
    private static final String USERNAME_PASSWORD = "Test1212125";
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

    @Test
    @Tag("TestCorrectPassword")
    void login() {

        driver.get(LOGIN_PAGE_URL);

        // Нажатие на кнопку Log in
        driver.findElement(By.xpath("/html/body/div[2]/div[3]/span[1]/a[1]")).click();

        //Ввод имени пользователя
        WebElement loginTextInput = driver.findElement(By.id("user_login"));
        loginTextInput.sendKeys(USERNAME_LOGIN);

        //сравниваем логин из файла настроек
        String loginText = loginTextInput.getAttribute("value");
        assertEquals(USERNAME_LOGIN, loginText);

        //Ввод пароля
        WebElement passwordTextInput = driver.findElement(By.id("passwd_login"));
        passwordTextInput.sendKeys(USERNAME_PASSWORD);

        String passwordText = loginTextInput.getAttribute("value");
        assertEquals(USERNAME_PASSWORD, passwordText);

        //Нажатие на кнопку Войти
        WebElement login = driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[5]/button"));
        Actions builder = new Actions(driver);
        builder
                .click(login) // Нажать на кнопку
                .build()
                .perform();

        //Thread.sleep(5000)
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 5000);");
        driver.quit();
    }
}
