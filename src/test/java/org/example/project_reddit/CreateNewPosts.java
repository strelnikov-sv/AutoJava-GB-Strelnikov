package org.example.project_reddit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
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

//Создание нового поста

class CreateNewPosts {
    private static final String LOGIN_PAGE_URL = "https://old.reddit.com/";
    private static final String USERNAME_LOGIN = "Test1212125";
    private static final String USERNAME_PASSWORD = "Test1212125";
    private static final String TEXT = "Test";
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
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 5000);");
    }

    private static void openProfile() {
        //Вход в профиль
        driver.findElement(By.xpath("/html/body/div[2]/div[3]/span[1]/a")).click();

        //Проверка, что имя пользователя есть на странице
        String element = driver.findElement(By.xpath("//*[@id=\"header-bottom-left\"]/span")).getText();
        Assertions.assertTrue(element.contains(USERNAME_LOGIN));
    }

    private static void openNewSite() {
        //Переход на новую версию сайта (Это необходимо,так как в старой версии сайта написание постов проходит только с капчей, а в новой - нет)
        driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/button")).click();

        //Проверка, что открылась страница Pinned Posts
        String element = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div/div/div[2]/div[4]/div[1]/div[2]")).getText();
        Assertions.assertTrue(element.contains("Pinned Posts"));
    }

    private static void create() {
        // Кнопка Создать пост
        driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div/div/div[2]/div[4]/div[2]/div/div[1]/div/div[5]/div/a")).click();
        //Ввод название темы поста
        WebElement textInput = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div/div/div[2]/div[3]/div[1]/div[2]/div[3]/div[2]/div[1]/div/textarea"));
        textInput.sendKeys(TEXT);

        // Кнопка создания поста
        driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div/div/div[2]/div[3]/div[1]/div[2]/div[3]/div[3]/div[2]/div/div[1]/button")).click();

        //Проверка, что пост создан
        String element = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div/div/div[2]/div[3]/div[1]/div[2]/div[1]/div/div[2]/div/div[1]")).getText();
        Assertions.assertTrue(element.contains(TEXT));

        driver.quit();
    }

    @Test
    @Tag("Проверка успешного входа")
    void testLogin() {
        login();
    }

    @Test
    @Tag("Вход в профиль")
    void testOpenProfile() {
        openProfile();
    }

    @Test
    @Tag("Переход на новую версию сайта")
    void testOpenNewSite() {
        openNewSite();
    }

    @Test
    @Tag("Создание нового поста")
    void testCreate() {
        create();
    }
}
