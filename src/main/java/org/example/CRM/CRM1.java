package org.example.CRM;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class CRM1 {
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

    public static void main(String[] args) throws InterruptedException {
        login();

        driver.findElement(By.xpath("//div[2]/ul/li[3]/a/span")).click();
        driver.findElement(By.xpath("//li[3]/ul/li[4]/a/span")).click();

        driver.findElement(By.linkText("Создать проект")).click();
        driver.findElement(By.name("crm_project[name]")).sendKeys("Тест060411111");

        driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[3]/form/div[2]/div[3]/div/div[1]/div[2]/fieldset/div[1]/div[2]/div[2]/div/div[1]/a/span[1]")).click();
        driver.findElement(By.xpath("/html/body/div[6]/div/input")).sendKeys("1234");
        Thread.sleep(3000);
        driver.findElement(By.xpath("/html/body/div[6]/ul[2]/li/div/span")).click();

        Select businessUnit = new Select(driver.findElement(By.name("crm_project[businessUnit]")));
        businessUnit.selectByVisibleText("Research & Development");

        Select crmProjectCurator = new Select(driver.findElement(By.name("crm_project[curator]")));
        crmProjectCurator.selectByVisibleText("Applanatest Applanatest Applanatest");

        Select crmProjectRp = new Select(driver.findElement(By.name("crm_project[rp]")));
        crmProjectRp.selectByVisibleText("Applanatest Applanatest Applanatest");

        Select crmProjectManager = new Select(driver.findElement(By.name("crm_project[manager]")));
        crmProjectManager.selectByVisibleText("Applanatest Applanatest Applanatest");

        driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[3]/form/div[1]/div/div/div[2]/div[1]/div[4]/button")).click();

        Thread.sleep(5000);
        driver.quit();
    }

    private static void login() {
        driver.get(LOGIN_PAGE_URL);

        WebElement loginTextInput = driver.findElement(By.id("prependedInput"));
        loginTextInput.sendKeys(STUDENT_LOGIN);

        WebElement passwordTextInput = driver.findElement(By.name("_password"));
        passwordTextInput.sendKeys(STUDENT_PASSWORD);

        WebElement loginButton = driver.findElement(By.name("_submit"));
        loginButton.click();
    }
}
