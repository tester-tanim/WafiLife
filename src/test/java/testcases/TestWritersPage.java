package testcases;

import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.BookdetailsAndOrderPage;
import pages.LoginPage;
import pages.WritersPage;
import utilities.BrowserSetup;

import java.util.List;

public class TestWritersPage extends BrowserSetup {

    WritersPage writersPage = new WritersPage();
    LoginPage loginPage = new LoginPage();

    BookdetailsAndOrderPage bookdetailsAndOrderPage = new BookdetailsAndOrderPage();

    String booksPageUrl;

    @Test
    public void logIntoAccount(){
        getBrowser().get(loginPage.loginPageUrl);
        loginPage.writeOnElement(loginPage.emailField,"kisivel401@adrais.com");
        loginPage.writeOnElement(loginPage.passwordField,"123456");
        loginPage.clickOnElement(loginPage.loginButton);
    }

    @Test(priority = 1)
    @Description("Writer option validation")
    public void hoverOnWriter() throws InterruptedException {
        WebElement elementToHover = getBrowser().findElement(writersPage.writer);
        writersPage.hoverOverElement(elementToHover);
        writersPage.addScreenShot("After hovering over the writer menu");
        Thread.sleep(2000);
    }

    @Test(priority = 2)
    @Description("Writers page visit")
    public void gotoWritersPage(){
        writersPage.clickOnElement(writersPage.writer);
        writersPage.addScreenShot("After visiting the writers page");
        Assert.assertEquals(getBrowser().getCurrentUrl(),writersPage.writerPageUrl);
        Assert.assertEquals(writersPage.getElementText(writersPage.writerPageHeadingElement),"লেখক");
    }

    @Test(priority = 3)
    @Description("Visiting next page")
    public void gotoNextPage() throws InterruptedException {
        writersPage.clickOnElement(writersPage.writer);
        writersPage.scrolltoElement(writersPage.nextPageButton);
        writersPage.clickOnElement(writersPage.nextPageButton);
        writersPage.addScreenShot("After clicking on next page");
        writersPage.pageNavigate("back");
        //Thread.sleep(2000);
    }

    //scraping the writer by preferred name and searching by the scraped name
    @Test(priority = 4)
    @Description("Selecting preferred writer")
    public void searchPreferredWriter() throws InterruptedException {
        writersPage.clickOnElement(writersPage.writer);
        String name;
        List<WebElement> writers = getBrowser().findElements(writersPage.writersList);

        for (WebElement writer : writers) {
            if (writer.getText().equalsIgnoreCase(writersPage.preferredWriter)) {
                name = writer.getText();
                writersPage.writeOnElement(writersPage.searchBox,name);
                writersPage.clickOnElement(writersPage.searchButton);
                getBrowser().findElement(By.linkText(name)).click();
                booksPageUrl = getBrowser().getCurrentUrl();
                writersPage.addScreenShot("After selecting preferred writer");
                Assert.assertEquals(getBrowser().getCurrentUrl(),writersPage.selectedWriterBooksUrl);
                Assert.assertEquals(writersPage.getElementText(writersPage.selectedWriterPageHeading), writersPage.preferredWriter);
                //Thread.sleep(5000);
                break;
            }
        }

    }

    //count books of the selected writer and get the name of the books
    @Test(priority = 5)
    @Description("Book information")
    public void countAndGetBooksName(){
        //getDriver().get(booksPageUrl);
        List<WebElement> books = getBrowser().findElements(writersPage.books);
        System.out.println("Total books: "+books.size());
        for (WebElement book: books){
            System.out.println(book.getText());
        }
    }

    @Test(priority = 6)
    @Description("Selecting preferred book")
    public void selectBook(){
        getBrowser().findElement(By.linkText("Islam Science And Culture")).click();
        writersPage.addScreenShot("After selecting preferred book");
        Assert.assertEquals(getBrowser().getCurrentUrl(), bookdetailsAndOrderPage.bookDetailsUrl);
        Assert.assertEquals(writersPage.getElementText(bookdetailsAndOrderPage.bookHeading),"ISLAM SCIENCE AND CULTURE");
    }

}
