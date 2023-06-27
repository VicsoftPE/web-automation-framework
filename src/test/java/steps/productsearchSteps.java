package steps;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;

import org.junit.Assert;
import pages.eBayPage;

public class productsearchSteps {

    eBayPage ebayPage = new eBayPage();
    private String valueForMatch;

   @Given("^User goes to eBay page in (.+)$")
    public void navigateToeBay(String geo){

        ebayPage.navigateToeBay();
        ebayPage.verifyGeo(geo);
    }
    @When("^User searches for product (.+) on (.+) category$")
    public void searchProductByCategory(String product, String itemCategory){
        ebayPage.clickCategoriesSelect();
        ebayPage.selectCategoryCriteria(itemCategory);
        ebayPage.enterSearchCriteria(product);
        ebayPage.clickEbaySearch();
    }
    @Then("^Search result page shows (.+) brand of product$")
    public void showBrandOfProduct(String item){
        Assert.assertTrue("All ok", ebayPage.searchBrandOfResultProducts(item));
   }
    @And("^The results page matches with (.+) within products shown$")
    public void validateResults(String expectedResult){
        Assert.assertTrue("Expected Result exists", ebayPage.isProductPresent(expectedResult));

    }

}