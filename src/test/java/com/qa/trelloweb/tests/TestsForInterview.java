package com.qa.trelloweb.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestsForInterview {

    WebDriver wd;

    @BeforeSuite
    public void test() throws InterruptedException {

        wd = new ChromeDriver();
        wd.get("https://ilcarro.xyz/search");
        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Dimension window = wd.manage().window().getSize();
        System.out.println(window.getWidth() + "=====" + window.getHeight());
        Thread.sleep(4000);
    }

    @Test
    public void clickByCoordinates() throws InterruptedException {
        // WebElement element = wd.findElement(By.xpath("//a[text()=' Let the car work ']"));
        WebElement element = wd.findElement(By.id("city"));
        element.click();
        element.clear();
        element.sendKeys("Tel Aviv ");
        Thread.sleep(4000);
            /*    Rectangle r = element.getRect();
                    System.out.println(r.height);
                    int x = r.getX() + r.getWidth()/2;
                    int y = r.getY() + r.getHeight()/2;*/

        Point p = element.getLocation();
        int x = p.getX() + 20;
        int y = p.getY() + 45;
        System.out.println(x + " + " + y);
        Actions actions = new Actions(wd);
        // actions.moveToElement(wd.findElement(By.tagName("body")),0,0);
        actions.moveByOffset(x, y).click().build().perform();
    }

    @Test
    public void elementIsPresent() {
        String element = "//a[text()=' Let the car work ']";
        boolean res = buttonOnPage1(By.xpath(element));
        System.out.println("Output result = " + res);
        Assert.assertTrue(buttonOnPage1(By.xpath(element)));
        Assert.assertFalse(buttonOnPage2(By.xpath(element)));
        Assert.assertTrue(buttonOnPage3(By.xpath(element)));
        Assert.assertEquals(actual(By.xpath(element)), "Let the car work");
    }


    private boolean buttonOnPage1(By locator) {
        try {
            return wd.findElement(locator).isEnabled();
        } catch (NoSuchElementException ex) {
            System.out.println("Element not present on page:  " + ex.getMessage());
            return false;
        }


    }

    private boolean buttonOnPage2(By locator) {
        try {
            return wd.findElements(locator).size() == 0;
        } catch (NoSuchElementException ex) {
            return true;
        }


    }

    private boolean buttonOnPage3(By locator) {
        try {
            return wd.findElement(locator).isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }


    }

    private String actual(By locator) {
        return wd.findElement(locator).getText();
    }


    private void locaators() {
        wd.findElement(By.cssSelector(".navigation-link[id='1']"));
        wd.findElement(By.xpath("//a[@id='1']"));
        wd.findElement(By.xpath("//a[contains(@class,'navigation-link')][@id='1']")); // all variants of button on header
        wd.findElement(By.xpath("//a[@id='1'])[1]")); //by index in xpath
        wd.findElement(By.xpath("//a[text()=' Let the car work ']"));
        wd.findElement(By.cssSelector("label[for='terms-of-use']"));// by tag name

//selectors of phoneBook
        wd.findElement(By.cssSelector("#root"));
        wd.findElement(By.id("root")); //  <----------------------- 3 variants of the same element
        wd.findElement(By.xpath("//div[@id='root']"));

// To find button on header at PhoneBook
        wd.findElement(By.xpath("//*[@class='navbar-logged_nav__2Hx7M']/a[2]"));
        wd.findElement(By.cssSelector("div[class='navbar-logged_nav__2Hx7M'] a:nth-child(2)")); //these all to find specific link
        wd.findElement(By.xpath("//a[text()='ABOUT']"));


        //Find one contact from list
        wd.findElement(By.cssSelector(".contact-item_card__2SOIM:nth-child(2)")); //by index of element
        wd.findElement(By.xpath("(//div[@class='contact-item_card__2SOIM'])[2]")); //by index of element

        //by xPath you can move above element, using two points .. Like //input/..
//input[2]/following-sibling::br ----------> axle shaft in xPath
//input[2]/ancestor::div
// button[2]/preceding-sibling::br -------------------> with this axle shaft you move above
// button[2]/parent::*
    }

}
