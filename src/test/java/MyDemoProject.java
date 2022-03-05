import com.beust.ah.A;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MyDemoProject {
    WebDriver driver;
    WebDriverWait wait;

    //    ...Setup Option...
    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "./src/test/resources/geckodriver.exe");
        FirefoxOptions ops = new FirefoxOptions();
        ops.addArguments("--headed");
        driver = new FirefoxDriver(ops);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }
//    ...Write text Box...


    // -- Title  --
    @Test
    public void getTitle() {
        driver.get("https://demoqa.com");
        String title = driver.getTitle();
        System.out.println(title);
        Assert.assertTrue(title.contains("ToolsQA"));
    }


    @Test
    public void ifElementsCheckExits() {
        driver.get("https://demoqa.com");

//        ...  Using Implicitly Process...
//     Boolean status = driver.findElement(By.className("banner-image")).isDisplayed();
//        WebElement imfElement = driver.findElement(By.className("banner-image"));
//        Boolean status =imfElement.isDisplayed();
//        Assert.assertTrue(status);
//        ... using Explicitly process....
        wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement imgElement = wait.until(ExpectedConditions.elementToBeClickable(By.className("banner-image")));
        Boolean status = imgElement.isDisplayed();
        Assert.assertTrue(status);


    }

    //    ... Elements Check ...
    @Test
    public void writeTextBox() {
        driver.get("https://demoqa.com/text-box");
        driver.findElement(By.id("userName")).sendKeys("Alamin");
        driver.findElement(By.id("userEmail")).sendKeys("alamincse@gmail.com");
        driver.findElement(By.id("submit")).submit();

    }

    //    ...Double & Right click perform operations...
    @Test
    public void clickOnDoubleClick() {
        driver.get("https://demoqa.com/buttons");
        WebElement doubleclickElemet = driver.findElement(By.id("doubleClickBtn"));
        Actions actions = new Actions(driver);
        actions.doubleClick(doubleclickElemet).perform();
        WebElement rightClick = driver.findElement(By.id("rightClickBtn"));
        actions.contextClick(rightClick).perform();

        String text1 = driver.findElement(By.id("doubleClickMessage")).getText();
        String text2 = driver.findElement(By.id("rightClickMessage")).getText();

        Assert.assertTrue(text1.contains("You have done a double click"));
        Assert.assertTrue(text2.contains("You have done a right click"));

    }

    //    ...Multiple click using list...
    @Test
    public void clickMultipleBtn() {
        driver.get("https://demoqa.com/buttons");
        List<WebElement> clickMultipleElementn = driver.findElements(By.tagName("button"));
        Actions actions = new Actions(driver);
        actions.doubleClick(clickMultipleElementn.get(1)).perform();
        actions.contextClick(clickMultipleElementn.get(2)).perform();
        actions.click(clickMultipleElementn.get(3)).perform();
    }
//    ... Alert handaling Process...
    @Test
    public void handleAlert() {
        driver.get("https://demoqa.com/alerts");
        driver.findElement(By.id("alertButton")).click();
        driver.switchTo().alert().accept();
        driver.findElement(By.id("timerAlertButton")).click();
        driver.findElement(By.id("confirmButton")).click();
//        driver.switchTo().alert().accept();
        driver.switchTo().alert().dismiss();
        driver.findElement(By.id("promtButton")).click();
        driver.switchTo().alert().sendKeys("Alamin");
        driver.switchTo().alert().accept();

        String text = driver.findElement(By.id("promptResult")).getText();
        Assert.assertTrue(text.contains("Alamin"));

    }
//    ... Date Selected process ...
@Test
    public void selectDate() {
    driver.get("https://demoqa.com/date-picker");
    driver.findElement(By.id("datePickerMonthYearInput")).clear();
    driver.findElement(By.id("datePickerMonthYearInput")).sendKeys("05/08/1993");
    driver.findElement(By.id("datePickerMonthYearInput")).sendKeys(Keys.ENTER);
}
@Test
public void selectDropdown(){
        driver.get("https://demoqa.com/select-menu");
    Select select = new Select(driver.findElement(By.id("oldSelectMenu")));
    select.selectByValue("1");
}

    @After
    public void finishTest() {
//      driver.close();
       driver.quit();
    }


}

