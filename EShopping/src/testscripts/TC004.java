package testscripts;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import genericlibs.ExcelLibrary;
import pages.DressesPage;
import pages.OrderDetailsPage;
import pages.ProductDetailsPage;

public class TC004 extends BaseTest {
	
	@DataProvider
	public String[][] getData(){
		return ExcelLibrary.getMultipleData("TC003");
	}
	
	@Test(dataProvider="getData",description="Add the Product to ODP and verify it is displayed in ODP")
	public void testProductInODP(String menuLinkName,
								 String productId,
								 String quantity,
								 String increase,
								 String size,
								 String colorName) {
		productId=productId.split("\\.")[0];
		DressesPage dressPage=(DressesPage)homePage.clickOnMenu(menuLinkName);
		ProductDetailsPage productDetailsPage = dressPage.clickOnProduct(productId);
		int qty = (int) Double.parseDouble(quantity);
		boolean incrs = Boolean.parseBoolean(increase);
		OrderDetailsPage orderDetailsPage = productDetailsPage.addItemToKart(qty, incrs, size, colorName);
		Assert.assertTrue(orderDetailsPage.isProductDisplayed(productId), productId+" is Not Displayed");
	}
}
