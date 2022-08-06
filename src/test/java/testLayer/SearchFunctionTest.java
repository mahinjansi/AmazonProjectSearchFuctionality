package testLayer;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import basePackage.BaseSearchFuctionality;
import pomPackage.PomsearchButton;
import utility.TimeUtils;

public class SearchFunctionTest extends BaseSearchFuctionality {
	PomsearchButton serchitem;

	public SearchFunctionTest() {
		super();
	}

	@BeforeMethod
	public void initialsetup() {
		browserinitiate();
		serchitem = new PomsearchButton();
	}

	@Test(groups = "verification")
	public void relaventItem() throws InterruptedException {
		serchitem.typechar_item(prop.getProperty("relavantitem"));
		Thread.sleep(3000);
		screenshots("relavent_item");
		System.out.println("AP_FP_TC2 case pass");
	}

	@Test(groups = "verification")
	public void typeproductName() throws InterruptedException {
		serchitem.typechar_item(prop.getProperty("productname"));
		Thread.sleep(3000);
		serchitem.clickserchButton();
		serchitem.getTitle();
		// Thread.sleep(3000);
		screenshots(" Search_Result_Page");
		System.out.println("AP_FP_TC1 case pass");
	}

	@Test // (dependsOnMethods = {"typeproductName"})
	public void totalimages() throws InterruptedException {
		serchitem.typechar_item(prop.getProperty("productname"));
		serchitem.clickserchButton();
		serchitem.displayImage();
		System.out.println("AP_FP_TC3_Search product  display image Pass");
	}

	@Test
	public void totalPrice() throws InterruptedException {
		serchitem.typechar_item(prop.getProperty("productname"));
		serchitem.clickserchButton();
		serchitem.displayPrice();
		System.out.println("AP_FP_TC4_Search product  display image Pass");
	}

	@Test
	public void totalName() throws InterruptedException {
		serchitem.typechar_item(prop.getProperty("productname"));
		serchitem.clickserchButton();
		serchitem.displayNameStarsRating();
		System.out.println("AP_FP_TC5_Search product  display image Pass");
	}

	@Test
	public void navigateToNextpage() throws InterruptedException {
		serchitem.typechar_item(prop.getProperty("productname"));
		serchitem.clickserchButton();
		serchitem.navigationfunctionality();
	}

	@Test
	public void checkingPagination() throws InterruptedException {
		serchitem.typechar_item(prop.getProperty("productname"));
		serchitem.clickserchButton();
		serchitem.navigationfunctionality();
		serchitem.paginationfunctionality();
	}

	@Test
	public void checkingsortingoption() throws InterruptedException {
		serchitem.typechar_item(prop.getProperty("productname"));
		serchitem.clickserchButton();
		serchitem.sortingfunctionality();
	}

	@AfterMethod
	public void close() {
		driver.manage().timeouts().pageLoadTimeout(TimeUtils.timepage, TimeUnit.SECONDS);
		driver.close();
	}
}
