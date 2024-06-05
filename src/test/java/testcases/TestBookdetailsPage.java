package testcases;

import io.qameta.allure.Description;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BookdetailsAndOrderPage;
import pages.HomePage;
import pages.LoginPage;
import utilities.BrowserSetup;

import java.time.Duration;


public class TestBookdetailsPage extends BrowserSetup {

    BookdetailsAndOrderPage bookdetailsAndOrderPage = new BookdetailsAndOrderPage();
    LoginPage loginPage = new LoginPage();
    JavascriptExecutor js = (JavascriptExecutor) getBrowser();
    Select select;
    Actions actions;
    @Test(priority = 1)
    @Description("Visiting book details page")
    public void goToBookDetailsPage(){
        getBrowser().get(loginPage.loginPageUrl);
        loginPage.writeOnElement(loginPage.emailField,"kisivel401@adrais.com");
        loginPage.writeOnElement(loginPage.passwordField,"123456");
        loginPage.clickOnElement(loginPage.loginButton);
        getBrowser().get(bookdetailsAndOrderPage.bookDetailsUrl);
        bookdetailsAndOrderPage.addScreenShot("After going to the preferred book's details page");
        Assert.assertEquals(getBrowser().getCurrentUrl(), bookdetailsAndOrderPage.bookDetailsUrl, "Failed to navigate to book details page");
       // Assert.assertEquals(getBrowser().getCurrentUrl(),bookdetailsAndOrderPage.bookDetailsUrl);
    }

    @Test(priority = 2)
    @Description("Order button's visibility")
    public void checkOrderButtonPresence() throws InterruptedException {
        getBrowser().get(bookdetailsAndOrderPage.bookDetailsUrl);
        bookdetailsAndOrderPage.addScreenShot("Checking the order button presence");
        Assert.assertTrue(bookdetailsAndOrderPage.isElementVisible(bookdetailsAndOrderPage.orderButton));

    }

    @Test(priority = 3)
    @Description("Adding book to the cart")
    public void checkIfProductIsAddedToCart() throws InterruptedException {
        getBrowser().get(bookdetailsAndOrderPage.bookDetailsUrl);
        bookdetailsAndOrderPage.clickOnElement(bookdetailsAndOrderPage.orderButton);
        Thread.sleep(10000);
        bookdetailsAndOrderPage.addScreenShot("Confirming product added to the cart");
        Assert.assertEquals(bookdetailsAndOrderPage.getElementText(bookdetailsAndOrderPage.itemConfirmation),"1 items");
        //bookdetailsAndOrderPage.clickOnElement(bookdetailsAndOrderPage.confirmationWindowClose);
        bookdetailsAndOrderPage.clickOnElement(bookdetailsAndOrderPage.cartButton);
        bookdetailsAndOrderPage.clickOnElement(bookdetailsAndOrderPage.cartOrderButton);
        bookdetailsAndOrderPage.writeOnElement(bookdetailsAndOrderPage.nameField,"Ishtiaque");
        bookdetailsAndOrderPage.writeOnElement(bookdetailsAndOrderPage.phoneNumberField,"01557272482");
        bookdetailsAndOrderPage.writeOnElement(bookdetailsAndOrderPage.emailField,"kisivel401@adrais.com");
        bookdetailsAndOrderPage.selectArea(bookdetailsAndOrderPage.districtDropdown,"Khagrachhari ");
        bookdetailsAndOrderPage.selectArea(bookdetailsAndOrderPage.areaDropdown,"গুইমারা");
        bookdetailsAndOrderPage.writeOnElement(bookdetailsAndOrderPage.address,"Jaliapara, Guimara");
        bookdetailsAndOrderPage.clickOnElement(bookdetailsAndOrderPage.deliveryChargeOption);
        //bookdetailsAndOrderPage.clickOnElement(bookdetailsAndOrderPage.paymentMethod);
        bookdetailsAndOrderPage.addScreenShot("After completing the billing information");
//        bookdetailsAndOrderPage.clickOnElement(bookdetailsAndOrderPage.myAccount);
////        bookdetailsAndOrderPage.clickOnElement(bookdetailsAndOrderPage.myAccount);
//        bookdetailsAndOrderPage.clickOnElement(bookdetailsAndOrderPage.cartButton);
//        bookdetailsAndOrderPage.clickOnElement(bookdetailsAndOrderPage.clearCart);
//        loginPage.clickOnElement(loginPage.logOutButton);
//        Thread.sleep(5000);
//        bookdetailsAndOrderPage.addScreenShot("After Logging out");
//        Assert.assertEquals(getBrowser().getCurrentUrl(),loginPage.loginPageUrl);

    }


}
