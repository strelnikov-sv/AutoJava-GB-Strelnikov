package org.example.project_reddit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

// Проверка переключения языков на странице

class Languages {
    private static final String LOGIN_PAGE_URL = "https://old.reddit.com/";

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

    private static void languageEnglish() {
        driver.get(LOGIN_PAGE_URL);

        // Нажатие на кнопку выбора языка
        driver.findElement(By.xpath("//*[@id=\"header-bottom-right\"]/ul/li/a")).click();

        // Выбор английского языка
        Select businessUnit = new Select(driver.findElement(By.name("lang")));
        businessUnit.selectByVisibleText("English [en]");

        // Нажатие на кнопку Сохранить параметры
        driver.findElement(By.xpath("//*[@id=\"pref-form\"]/table/tbody/tr[2]/td/input")).click();

        //Проверка, что страница на английском
        String element = driver.findElement(By.xpath("//*[@id=\"header-bottom-left\"]/ul/li[1]/a")).getText();
        Assertions.assertTrue(element.contains("hot"));
    }

    private static void languageRussian() {

        // Нажатие на кнопку выбора языка
        driver.findElement(By.xpath("//*[@id=\"header-bottom-right\"]/ul/li/a")).click();

        // Выбор русского языка
        Select businessUnit = new Select(driver.findElement(By.name("lang")));
        businessUnit.selectByVisibleText("русский [ru]");

        // Нажатие на кнопку Сохранить параметры
        driver.findElement(By.xpath("//*[@id=\"pref-form\"]/table/tbody/tr[2]/td/input")).click();

        //Проверка, что страница на русском
        String element = driver.findElement(By.xpath("//*[@id=\"header-bottom-left\"]/ul/li[1]/a")).getText();
        Assertions.assertTrue(element.contains("горячее"));

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 5000);");
        driver.quit();
    }

    @Test
    @Tag("Проверка на Английский язык")
    void testEn() {
        languageEnglish();
    }

    @Test
    @Tag("Проверка на Русский язык")
    void testRu() {
        languageRussian();
    }

}