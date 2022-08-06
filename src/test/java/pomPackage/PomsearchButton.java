package pomPackage;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections4.Get;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.collections.Objects;

import basePackage.BaseSearchFuctionality;

public class PomsearchButton extends BaseSearchFuctionality {

	// Create Object repository
	// @findBy is replacement of driver.findElements(By
	// @findBy = driver.findElements(by.
	@FindBy(id = "twotabsearchtextbox")
	WebElement searchbox;
	@FindBy(id = "nav-search-submit-button")
	WebElement searchbutton;
	@FindBy(xpath = ("//div[contains(@class, 'a-section') and contains(@class, 'aok-relative') and contains(@class, 's-image-square-aspect')]"))
	List<WebElement> imgList; // which will return image's list of WebElements
	@FindBy(xpath = ("//span[@class='a-price']"))
	List<WebElement> priceList; // will return list of price of the elements
	@FindBy(xpath = ("//h2 [@class = 'a-size-mini a-spacing-none a-color-base s-line-clamp-4']"))
	List<WebElement> nameList;
	@FindBy(xpath = ("//a [@class = 'a-popover-trigger a-declarative']"))
	List<WebElement> reviewList;
	@FindBy(xpath = ("//a [@class = 'a-link-normal s-underline-text s-underline-link-text s-link-style']"))
	List<WebElement> ratingNoList;

	/*
	 * ("//span [contains(@class,'a-size-base-plus') and contains (@class, 'a-color-base')and contains(@class,'a-text-normal')]"
	 * )) above path is correct but only return single product name
	 * .//span[@class='a-size-base-plus a-color-base a-text-normal']") also above
	 * both link together throws an error exception Element does not fond both links
	 * are correct but together giving error and not proper result then use another
	 * link and return product name now.
	 */
	// To initiate page elements we need to create Objects

	public PomsearchButton() {
		PageFactory.initElements(driver, this);
	}

	// writing all possible method whatever we can perform on this page
	public void typeproductName(String productname) {
		searchbox.sendKeys(productname);
	}

	public void typechar_item(String relavantitem) {
		searchbox.sendKeys(relavantitem);
	}

	public void clickserchButton() {
		searchbutton.click();
	}

	public void getTitle() {
		String actual = driver.getTitle();
		Assert.assertEquals(actual, "Amazon.com : T-shirt");
		System.out.println("Actul is: " + actual + " & " + "Expected is: " + "Amazon.com:T-shirt");
	}

	public void displayImage() throws InterruptedException {
		Thread.sleep(3000);
		System.out.println("Total no of images: " + imgList.size());
		for (int i = 0; i < imgList.size(); i++) {
			WebElement elemI = imgList.get(i);
			WebElement imgElem = elemI.findElement(By.tagName("img"));
			System.out.println(i + " : " + "Image Link:" + imgElem.getAttribute("src"));
		}

	}
	public void displayPrice() throws InterruptedException {
		Thread.sleep(3000);
		System.out.println("Total price: " + priceList.size());
		for (int i = 0; i < priceList.size(); i++) {
			WebElement elemP = priceList.get(i);
			WebElement priceElem = elemP.findElement(By.xpath(".//span[@class='a-offscreen']"));
			System.out.println(i + " : " + " Price : " + priceElem.getAttribute("innerHTML"));
		}
	}

	public void displayNameStarsRating() throws InterruptedException {
		Thread.sleep(3000);
		System.out.println("Total no of the product : " + nameList.size());
		for (int i = 0; i < nameList.size(); i++) {
			WebElement elemN = nameList.get(i);
			WebElement elemS = reviewList.get(i);
			WebElement elemr = ratingNoList.get(i);
			WebElement nameElem = elemN
					.findElement(By.xpath(".//span[@class='a-size-base-plus a-color-base a-text-normal']"));
			WebElement reviewsElem = elemS.findElement(By.xpath(".//span[@class='a-icon-alt']"));
			WebElement ratingElem = elemr.findElement(By.xpath("//span[@class='a-size-base s-underline-text']"));
			System.out.println(i + " : " + " Name: " + nameElem.getAttribute("innerHTML") + " *Stars: "
					+ reviewsElem.getAttribute("innerHTML") + " * " + " @ Rating : "
					+ ratingElem.getAttribute("innerHTML"));
		}
	}

	public void navigationfunctionality() throws InterruptedException {
		WebElement welem = driver.findElement(By.xpath(
				"//a[@class = 's-pagination-item s-pagination-next s-pagination-button s-pagination-separator']"));
		welem.sendKeys(Keys.RETURN);
		Thread.sleep(3000);
	}
	public void paginationfunctionality() throws InterruptedException {
		HashSet<WebElement> imghashset = new HashSet();
		for (int i=0; i<imgList.size(); i++) {

			if (! imghashset.add(imgList.get(i))) {
				System.out.println("Duplicte product is available : " + imgList.get(i));
			}
			/*	else {
				System.out.println("There no duplicate product on next page");
			}*/

		}
		int imgsize = imgList.size();
		int imghashsetsize = imghashset.size();
		Assert.assertEquals(imgsize, imghashsetsize);
		System.out.println("imgsize : "+ imgsize);
		System.out.println("imghashsetsize : "+ imghashsetsize);		
	}
	public void sortingfunctionality () throws InterruptedException {
		WebElement we;
		List <WebElement> dropdownvalue = driver.findElements(By.xpath("//select[@id = 's-result-sort-select']//option"));
		System.out.println("how many element in dropdown : "+ dropdownvalue.size());			
	for (int i=0; i<dropdownvalue.size(); i++) {
			we = dropdownvalue.get(i);
			Actions a = new Actions(driver);
			String weiText = we.getText();
			System.out.println(weiText);
			if (weiText.equals("Price: High to Low")) {
				Thread.sleep(3000);
				System.out.println("we : " + we.getText() + "*********"+weiText);
				a.moveToElement(we).click().build().perform();
				Thread.sleep(2000);
			}
		}	
	}
}

