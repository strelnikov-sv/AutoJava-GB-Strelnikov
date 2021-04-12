package org.example.project_reddit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

//Создание нового поста

public class Reddit2 {
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

    public static void main(String[] args) throws InterruptedException {
        login();

        Thread.sleep(5000);
        //Вход в профиль
        driver.findElement(By.xpath("/html/body/div[2]/div[3]/span[1]/a")).click();
        //Переход на новую версию сайта (Это необходимо,так как в старой версии сайта написание постов проходит только с капчей, а в новой - нет)
        driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/button")).click();
        // Кнопка Создать пост
        driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div/div/div[2]/div[4]/div[2]/div/div[1]/div/div[5]/div/a")).click();

        //Ввод название темы поста
        WebElement textInput = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div/div/div[2]/div[3]/div[1]/div[2]/div[3]/div[2]/div[1]/div/textarea"));
        textInput.sendKeys(TEXT);

        // Кнопка создания поста
        driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div/div/div[2]/div[3]/div[1]/div[2]/div[3]/div[3]/div[2]/div/div[1]/button")).click();

        Thread.sleep(5000);
        driver.quit();
    }

    private static void login(){

        driver.get(LOGIN_PAGE_URL);

        driver.findElement(By.xpath("/html/body/div[2]/div[3]/span[1]/a[1]")).click();

        WebElement loginTextInput = driver.findElement(By.id("user_login"));
        loginTextInput.sendKeys(USERNAME_LOGIN);

        WebElement passwordTextInput = driver.findElement(By.id("passwd_login"));
        passwordTextInput.sendKeys(USERNAME_PASSWORD);

        driver.findElement(By.xpath("/html/body/div[7]/div/div/div[2]/div/div[1]/div[1]/div[2]/div[2]/form/div[5]/button")).click();
    }
}
