package com.testautomationguru.container.pages;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {
    
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions action;
       
    Consumer<By> hover = (By by) -> {
        action.moveToElement(driver.findElement(by))
              .perform();
    };
    
    public OrderPage(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, 30);
        this.action = new Actions(driver);
    }

    public void goTo() {
        this.driver.get("http://store.demoqa.com/products-page/product-category/?view_type=default");
        System.out.println("Browser launched and navigated to DemoQA page");
    }

    public void goToCategory(String category){
        hover.accept(By.linkText("Product Category"));
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText(category)));
       
        //click on the category
        driver.findElement(By.linkText(category)).click();
        
        //wait for the list of products to be available
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='"+ category +"']")));
    }
    
    public List<String> getListOfItems() {
        
        return driver.findElements(By.cssSelector("div.productcol"))
                      .stream()
                      .map(this::getItemAndPrice)
                      .collect(Collectors.toList());

    }
    
    private String getItemAndPrice(WebElement element) {
        String title = element.findElement(By.cssSelector("h2.prodtitle")).getText().trim();
        String price = element.findElement(By.cssSelector("span.currentprice")).getText().trim();
        return title + " - " + price;
    }
    

}
