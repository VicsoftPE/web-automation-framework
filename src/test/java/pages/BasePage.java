package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;

public class BasePage {

    protected static WebDriver driver;
    private static WebDriverWait wait;
    private static Actions action;
    protected static final String url;
    protected static final FileReader reader;

    static{

        try {
            reader = new FileReader("src/test/resources/environment.properties");
            Properties props=new Properties();
            props.load(reader);
            url= props.getProperty("production");
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\victor.arroyo\\Documents\\Victor\\Automation\\JavaProject\\chromeDriver\\chromedriver.exe");
            ChromeOptions chromeOptions = new ChromeOptions();
            driver = new ChromeDriver(chromeOptions);
            driver.manage().window().maximize();
            wait = new WebDriverWait(driver, 10);



        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    //Constructor
    public BasePage(WebDriver driver){

        BasePage.driver = driver;
        wait = new WebDriverWait(driver, 10);

    }

    public static void navigateToUrl(){
        driver.get(url);
    }

    public static void closeBrowser(){
        driver.quit();
    };
    private WebElement Find(String locator){
          return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
    }


    public void clickElement(String locator){
        Find(locator).click();
    }

    public void write(String locator, String textToWrite){
        Find(locator).clear();
        Find(locator).sendKeys(textToWrite);
    }

    public void selectFromDropdownByValue(String locator, String valueToSelect){
        Select dropdown = new Select(Find(locator));
        dropdown.selectByValue(valueToSelect);
    }

    public void selectFromDropdownByIndex(String locator, int valueToSelect){
        Select dropdown = new Select(Find(locator));
        dropdown.selectByIndex(valueToSelect);
    }

    public void selectFromDropdownByText(String locator, String valueToSelect){
        Select dropdown = new Select(Find(locator));
        dropdown.selectByVisibleText(valueToSelect);
    }

    public void hoverOverElement(String locator){
        action.moveToElement(Find(locator));

    }
    public void doubleClick(String locator){
        action.doubleClick(Find(locator));

    }

    public void rightClick(String locator){
        action.contextClick(Find(locator));

    }

    public String getValueFromResultItemsDiv(String locator, int row, int column){
        String cellINeed = locator+"ul["+row+"]/li["+column+"]";
        return Find(cellINeed).getText();
    }

    public void setValueOnDiv(String locator, int row, int column, String stringToSend){
        String cellToFill = locator+"ul["+row+"]/li["+column+"]";
        Find(cellToFill).sendKeys(stringToSend);
    }

    public void switchToiFrame(String iFrameID){
        driver.switchTo().frame(iFrameID);

    }

    public void switchToParentFrame(){
        driver.switchTo().parentFrame();
    }

    public void dismissAlert(){
        try{
            driver.switchTo().alert().dismiss();
        }catch (NoAlertPresentException e){
            e.printStackTrace();
        }

    }

    public String textFromElement(String locator){
        return Find(locator).getText();
    }

    public boolean elementIsDisplayed(String locator){
           return Find(locator).isDisplayed();
      }
    public boolean elementIsEnabled(String locator){
        return Find(locator).isEnabled();
    }

    public boolean elementIsSelected(String locator){
        return Find(locator).isSelected();
    }

    public List<WebElement> bringAllElements(String locator){
       return driver.findElements(By.xpath(locator));
    }
}
