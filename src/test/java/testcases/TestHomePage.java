package testcases;

import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utilities.BrowserSetup;

import java.util.Iterator;
import java.util.Set;

public class TestHomePage extends BrowserSetup {

    HomePage homepage = new HomePage();
    LoginPage loginPage = new LoginPage();

    @Test(priority = 1)
    @Description("Visiting the home page")
    public void testHomepageUrl(){
        getBrowser().get(homepage.homepageUrl);
        homepage.addScreenShot("After visiting the homepage");
        Assert.assertEquals(getBrowser().getCurrentUrl(),homepage.homepageUrl);
    }

    @Test(priority = 2)
    @Description("Checking the home page title")
    public void testHomepageTitle(){
        getBrowser().get(homepage.homepageUrl);
        homepage.addScreenShot("Checking the home page URL");
        Assert.assertEquals(getBrowser().getTitle(),homepage.homepageTitle);
    }

    @Test(priority = 3)
    @Description("Checking the visibility of login button")
    public void isLoginButtonVisible(){
        getBrowser().get(homepage.homepageUrl);
        homepage.addScreenShot("Checking the presence of login button");
        Assert.assertTrue(homepage.isElementVisible(homepage.logInButton));
        //System.out.println(homepage.isElementVisible(homepage.loginButton));
    }

    @Test(priority = 3)
    @Description("Checking the functionality of login button")
    public void isLoginButtonClickable(){
        getBrowser().get(homepage.homepageUrl);
        homepage.clickOnElement(homepage.logInButton);
        homepage.addScreenShot("After clicking on login button");
        Assert.assertEquals(getBrowser().getCurrentUrl(),loginPage.loginPageUrl);
        Assert.assertEquals(getBrowser().getTitle(),loginPage.loginPageTitle);
    }

}
