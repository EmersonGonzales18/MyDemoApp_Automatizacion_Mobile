package com.nttdata.steps;

import com.nttdata.screens.CartScreen;
import org.junit.Assert;

public class CartSteps {
    CartScreen cartScreen;

    public void validateAllproducts(){
        cartScreen.isProductsDisplay();
    }

    public void agregateCart(int unidades, String producto) {
        cartScreen.selectedProduct(unidades,producto);
    }

    public void validateCart() {
        Assert.assertEquals(cartScreen.getItemsCart(), cartScreen.getItemslabel());
    }
}
