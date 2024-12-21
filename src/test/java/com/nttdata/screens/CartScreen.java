package com.nttdata.screens;
import io.appium.java_client.pagefactory.AndroidFindBy;
import net.serenitybdd.core.pages.PageObject;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.target.model.SessionID;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartScreen extends PageObject{

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Increase item quantity\"]")
    private WebElement btn_agregate;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Tap to add product to cart\"]")
    private WebElement btn_add_to_cart;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/cartTV\"]")
    private WebElement items_of_cart;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/noTV\"]")
    private WebElement quantity_add;

    public void isProductsDisplay(){
        List<WebElement> productNames = getDriver().findElements(By.xpath("//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/titleTV\"]"));
        List<WebElement> productImages = getDriver().findElements(By.xpath("//android.widget.ImageView"));
        List<WebElement> productPrices = getDriver().findElements(By.xpath("//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/priceTV\"]"));
        if(!productNames.isEmpty() && !productImages.isEmpty() && !productPrices.isEmpty()){
            System.out.println("Se cargaron todos los nombres de productos");
        }
    }

    public void selectedProduct(int unidades, String producto) {
        String productXpath = "//android.widget.ImageView[@content-desc='"+producto+"']";
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(3));
        try {
            WebElement productbtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(productXpath)));
            Assert.assertTrue(productbtn.isDisplayed());
            try {
                productbtn.click();
            }catch (Exception e){
                System.out.println("La app se cerro inesperadamente");
            }
        } catch (TimeoutException e) {
            Assert.fail("No se encontro el producto. Deteniendo prueba");
        }
        agregateUnits(unidades);

    }

    public void agregateUnits(int unidades){
        for(int i=0;i<unidades;i++){
            btn_agregate.click();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("Error durante el sleep: " + e.getMessage());
            }
        }
    }

    public String getItemsCart(){
        btn_add_to_cart.click();
        waitFor(ExpectedConditions.visibilityOf(items_of_cart));
        return items_of_cart.getText();
    }

    public String getItemslabel(){
        return quantity_add.getText();
    }
}
