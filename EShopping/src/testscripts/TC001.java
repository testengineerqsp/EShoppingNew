package testscripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import genericlibs.ExcelLibrary;
import pages.DressesPage;
import pages.OrderDetailsPage;
import pages.ProductDetailsPage;

public class TC001 extends BaseTest {
	@Test(description="Add the Product to ODP and verify it is displayed in ODP")
	public void testProductInODP() {
		//Reading data from Excel
		String sheetName = "TC001";
		String menuLinkName=ExcelLibrary.getStringData(sheetName, 1, 0);
		String productId=ExcelLibrary.getStringData(sheetName, 1, 1).split("\\.")[0];
		int quantity=(int)ExcelLibrary.getNumericData(sheetName, 1, 2);
		boolean increase=ExcelLibrary.getBooleanData(sheetName, 1, 3);
		String size=ExcelLibrary.getStringData(sheetName, 1, 4);
		String colorName=ExcelLibrary.getStringData(sheetName, 1, 5);
				
		//Using in App
		DressesPage dressPage=(DressesPage)homePage.clickOnMenu(menuLinkName);
		ProductDetailsPage productDetailsPage = dressPage.clickOnProduct(productId);
		OrderDetailsPage orderDetailsPage = productDetailsPage.addItemToKart(quantity, increase, size, colorName);
		Assert.assertTrue(orderDetailsPage.isProductDisplayed(productId));
	}
}
