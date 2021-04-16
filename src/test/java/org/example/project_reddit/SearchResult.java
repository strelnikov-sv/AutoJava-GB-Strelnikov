package org.example.project_reddit;

//Проверка поиска. Поиск пользователя Test1212125

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

class SearchResult {
    private static final String LOGIN_PAGE_URL = "https://old.reddit.com/";
    private static final String SEARCH_NAME = "author:Test1212125";

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

    private static void search_result() {
        driver.get(LOGIN_PAGE_URL);

        // Нажатие на пользователя поиска
        WebElement search = driver.findElement(By.id("search"));
        Actions builder = new Actions(driver);
        builder
                .click(search)
                .sendKeys(SEARCH_NAME)
                .sendKeys(Keys.ENTER)
                .build()
                .perform();

        //Проверка, что открылись результаты поиска
        String element = driver.findElement(By.xpath("//*[@id=\"header-bottom-left\"]/span")).getText();
        Assertions.assertTrue(element.contains("результаты поиска по - " + SEARCH_NAME));


        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 5000);");
        driver.quit();
    }

    @Test
    @Tag("Проверка на поиск")
    void testSearching() {
        search_result();
    }
}