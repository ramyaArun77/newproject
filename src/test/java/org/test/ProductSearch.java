package org.test;

import java.util.List;

import org.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class ProductSearch extends BaseClass{
	public ProductSearch() {
	       PageFactory.initElements(driver,this);
		}
		
		@FindBy(id="twotabsearchtextbox")
		private WebElement productname;

		public WebElement getProductname() {
			return productname;
		}

		@FindBy(xpath="//input[@type='submit']")
		private WebElement productsearch;

		public WebElement getProductsearch() {
			return productsearch;
		}
//		@FindBys({@FindBy(xpath="(//span[@class='a-size-medium a-color-base a-text-normal'])[1]")})
		@FindBy(xpath="(//span[@class='a-size-medium a-color-base a-text-normal'])")
		private List<WebElement> productlist;

		public List<WebElement> getProductlist() {
			return productlist;
		}
		@FindBy(xpath="//span[@class='a-price-whole']")
		private List<WebElement> productprice;

		public List<WebElement> getProductprice() {
			return productprice;
		}
		
}
