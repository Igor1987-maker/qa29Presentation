package com.qa.trelloweb.tests;

import com.qa.trelloweb.model.User;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTestsAndGetWindowHandle extends TestBase {
    @BeforeMethod
    public void preconditions() {
        if (app.user().isTrelloButtonOnHeader()) {
            app.user().logout();
        }
    }


    @Test
    public void testLogin() throws InterruptedException {
        app.user().initLogin();
        app.user().fillLoginForm(new User().withUser("rochman.elena@gmail.com").withPassword("12345.com"));
        app.user().submitLogin();
        Thread.sleep(15000);

        Assert.assertTrue(app.user().isTrelloButtonOnHeader(), "Element 'Logo' not found");
    }

    @Test
    public void openNewWindow() throws InterruptedException {

        app.user().openWindow("https://google.com");
        app.user().openWindow("https://gidonline.io");
        Thread.sleep(2000);
        app.user().chooseWindow(2);
    }

  /*  @Test
    public void negativeLoginWithoutPassword() throws InterruptedException {
        *//*
     * 1. init login
     * 2. fillLogin form
     * 3. submit Login
     * *//*

        app.user().initLogin();
        app.user().fillLoginForm(new User().withUser("rochman.elena@gmail.com"));
        app.user().submitLogin();
        Thread.sleep(15000);

        //Assert.assertTrue(isErrorPresent(), "Element 'Error' not found");
    }*/


}
