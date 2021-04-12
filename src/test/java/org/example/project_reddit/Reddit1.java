package org.example.project_reddit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

// Вход в аккаунт Reddit.com

public class Reddit1 {
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

    public static void main(String[] args) throws InterruptedException {
        login();

        Thread.sleep(5000);
        driver.quit();
    }

    private static void login(){
        driver.get(LOGIN_PAGE_URL);

        // Нажатие на кнопку Log in
        driver.findElement(By.xpath("/html/body/div[2]/div[3]/span[1]/a[1]")).click();

        //Ввод имени пользователя
        WebElement loginTextInput = driver.findElement(By.id("user_login"));
        loginTextInput.sendKeys(USERNAME_LOGIN);

        //Ввод пароля
        WebElement passwordTextInput = driver.findElement(By.id("passwd_login"));
        passwordTextInput.sendKeys(USERNAME_PASSWORD);

        //Нажатие на кнопку Войти
        driver.findElement(By.xpath("/html/body/div[7]/div/div/div[2]/div/div[1]/div[1]/div[2]/div[2]/form/div[5]/button")).click();
    }
}
