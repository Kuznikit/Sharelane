import com.sun.jdi.Value;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SignUpTest {
    @Test
    public void validZipCodeShouldBeAccepted() {
        // Открыть https://www.sharelane.com/cgi-bin/register.py
        //Enter 11111 into Zip Code
        // Click Continue
        //Check that 'Register' button exists
        //<input type="text" name="zip_code" value="">
        //<input type="submit" value="Continue">
        //<input type="submit" value="Register">
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("11111");
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        boolean isPageOpened = driver.findElement(By.cssSelector("[value=Register]")).isDisplayed();
        assertTrue(isPageOpened, "Sign Up was not opened");
        driver.quit();

    }

    @Test
    public void ZipCodeShouldBeRequired() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        String error = driver.findElement(By.cssSelector("[class=error_message]")).getText();
        assertEquals(error, "Oops, error on page. ZIP code should have 5 digits", "text is not correct");
        //<span class="error_message">Oops, error on page. ZIP code should have 5 digits</span>

        driver.quit();

    }

    @Test
    public void ZipCodeWithMoreThan6CharShouldRejected() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("111111");
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        String error = driver.findElement(By.cssSelector("[class=error_message]")).getText();
        assertEquals(error, "Oops, error on page. ZIP code should have 5 digits", "text is not correct");
        driver.quit();
    }


    @Test
    public void EmailWithoutSymbols() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=11111");
        driver.findElement(By.name("email")).sendKeys("");
        driver.findElement(By.cssSelector("[value=Register]")).click();
        String error = driver.findElement(By.cssSelector("[class=error_message]")).getText();
        assertEquals(error, "Oops, error on page. Some of your fields have invalid data. Email should have symbols email@domain", "text is not correct");
        driver.quit();
    }
    @Test
    public void FirstNameWithoutSymbols() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=11111");
        driver.findElement(By.name("first_name")).sendKeys("");
        driver.findElement(By.cssSelector("[value=Register]")).click();
        String error = driver.findElement(By.cssSelector("[class=error_message]")).getText();
        assertEquals(error,"Oops, error on page. Some of your fields have invalid data. Email should have symbols First Name","Field FirstName is empty");
        driver.quit();
    }
    @Test
    public void PassWithoutSymbols() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=11111");
        driver.findElement(By.name("password1")).sendKeys("");
        driver.findElement(By.cssSelector("[value=Register]")).click();
        String error = driver.findElement(By.cssSelector("[class=error_message]")).getText();
        assertEquals(error,"Oops, error on page. Some of your fields have invalid data. Password should have any symbols","Field password is empty");
        driver.quit();
    }
    @Test
    public void PassIsNotConfirm() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=11111");
        driver.findElement(By.name("first_name")).sendKeys("Mike");
        driver.findElement(By.name("last_name")).sendKeys("Anderson");
        driver.findElement(By.name("email")).sendKeys("email@email.gmail.com");
        driver.findElement(By.name("password1")).sendKeys("1111");
        driver.findElement(By.name("password2")).sendKeys("");
        driver.findElement(By.cssSelector("[value=Register]")).click();
        String error = driver.findElement(By.cssSelector("[class=error_message]")).getText();
        assertEquals(error, "Oops, error on page. Some of your fields have invalid data. Password is wrong.", "Password is not confirm");
        driver.quit();
    }
    @Test
    public void validSignUpShouldBeAccepted() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=11111");
        driver.findElement(By.name("first_name")).sendKeys("Mike");
        driver.findElement(By.name("last_name")).sendKeys("Anderson");
        driver.findElement(By.name("email")).sendKeys("email@email.gmail.com");
        driver.findElement(By.name("password1")).sendKeys("1111");
        driver.findElement(By.name("password2")).sendKeys("1111");
        driver.findElement(By.cssSelector("[value=Register]")).click();
        boolean isPageOpened = driver.findElement(By.cssSelector("[class=confirmation_message]")).isDisplayed();
        assertTrue(isPageOpened, "Register was not done");
        driver.quit();
}
    @Test
    public void ConfirmPassIsWrong() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=11111");
        driver.findElement(By.name("first_name")).sendKeys("Mike");
        driver.findElement(By.name("last_name")).sendKeys("Anderson");
        driver.findElement(By.name("email")).sendKeys("email@email.gmail.com");
        driver.findElement(By.name("password1")).sendKeys("1111");
        driver.findElement(By.name("password2")).sendKeys("111");
        driver.findElement(By.cssSelector("[value=Register]")).click();
        String error = driver.findElement(By.cssSelector("[class=error_message]")).getText();
        assertEquals(error, "Oops, error on page. Some of your fields have invalid data. Password is wrong.", "Password is wrong");
        driver.quit();
    }


    @Test
    public void PassAreNotMatch() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=11111");
        driver.findElement(By.name("password1")).sendKeys("1111");
        driver.findElement(By.name("password2")).sendKeys("11112");
        String error = driver.findElement(By.cssSelector("[name=password1]")).getAttribute("value");
        String error1 = driver.findElement(By.cssSelector("[name=password2]")).getAttribute("value");
        assertEquals(error,error1, "Password is not confirm");
        driver.quit();
    }

}
