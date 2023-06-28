package pages;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class eBayPage extends BasePage {

    private String searchButton = "//input[@id='gh-btn']";
    private String searchTextField = "//input[@id='gh-ac']";

    private String categorySelect = "//select[@id='gh-cat']";

    private String resultItemsDiv = "/html[1]/body[1]/div[5]/div[4]/div[2]/div[1]/div[2]/";

    private String textResult = "//a/div[@class='s-item__title']";
    private String textProduct = "//div[@class='s-item__title']";
    private String allResults = "//li[@class='s-item s-item__pl-on-bottom']";

    private String geoDefault = "//*[@id='gh-eb-Geo-a-default']/span[2]";
    private String changeGeo = "//*[@id='gh-eb-Geo-a-en']/span[2]";

    private String msgShip = "//*[@id='gh-msgOverlay']";
    private String closeMsgShip = "//*[@id='gh-msgOverlay']/div[2]";

    //Constructor - Super
    public eBayPage(){
        super(driver);
    }


    public void navigateToeBay(){
        navigateToUrl();
    }

    public void verifyGeo(String geoToBeLoaded){
        if (bringAllElements(geoDefault).size()>0) {
            final String textGeo = textFromElement(geoDefault);
            if (!textGeo.equals(geoToBeLoaded)) {
                clickElement(geoDefault);
                final String textChangeGeo = textFromElement(changeGeo);
                if (textChangeGeo.equals(geoToBeLoaded)) {
                    clickElement(changeGeo);
                }
            }
        }
    }
    public void clickEbaySearch(){

            clickElement(searchButton);

    }

    public void enterSearchCriteria(String criteria){
        try{
            write(searchTextField, criteria);
        }catch (NoSuchElementException e){
            System.out.println("The WebElement Search Field couldn't be found");
            e.printStackTrace();
        }


    }

    public void clickCategoriesSelect(){
           clickElement(categorySelect);

    }
    public void selectCategoryCriteria(String criteria){
        selectFromDropdownByText(categorySelect, criteria);
    }

    public String getValueFromGrid(int row, int column){
        return getValueFromResultItemsDiv(resultItemsDiv, row, column);
    }

    public boolean searchBrandOfResultProducts(String item){

        String locatorINeed = textResult+"/span[contains(text(),'"+item+"')]";
        return elementIsDisplayed(locatorINeed);

    }
    public boolean isProductPresent(String expectedResult){
        String locatorINeed = textProduct+"/span[text()=\""+expectedResult+"\"]";
        return elementIsDisplayed(locatorINeed);
    }

    public List<String> getAllSearchResults(){
        List<WebElement> list = bringAllElements(allResults);
        List<String> stringsFromList = new ArrayList<>();
        for (WebElement e : list){
            stringsFromList.add(e.getText());
        }
        return stringsFromList;
    }

    public void isMsgShipPresent() {
        if (bringAllElements(msgShip).size()>0) {
            clickElement(closeMsgShip);

        }
    }



}
