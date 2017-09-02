package com.testautomationguru.container.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(name = "q")
    private WebElement searchBox;

    @FindBy(css = "input.lsb")
    private WebElement searchButton;

    @FindBy(className = "rc")
    private List<WebElement> searchResults;

    @FindBy(id = "foot")
    private WebElement footer;

    public SearchPage(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, 30);
    }

    public void goTo() {
        this.driver.get("https://www.google.com");
        System.out.println("Browser launched and navigated to Google");
    }

    public void searchFor(String text) {
        this.searchBox.sendKeys(text);
        System.out.println(text + " - entered for search");
        wait.until(ExpectedConditions.elementToBeClickable(this.searchButton));
        System.out.println("Search button clicked");
        this.searchButton.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("rc")));
        System.out.println("Results appeared");
    }

    public List<WebElement> getResults() {
        System.out.println("Results Count : " + this.searchResults.size());
        System.out.println("---------------------------------------------");
        return this.searchResults;
    }

}
