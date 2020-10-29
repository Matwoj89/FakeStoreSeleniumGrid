package TestyPOM;

import PageObjects.CartPage;
import PageObjects.CategoryPage;
import PageObjects.ProductPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class CartTest extends BaseTest {

    @Test
    public void addToCartFromProductPageTest() throws IllegalAccessException {
        ProductPage productPage = new ProductPage(driver).goTo(configuration.getBaseUrl() + testData.getProduct().getUrl());
        productPage.demoNotice.close();

        boolean isProductInCart = productPage.addToCart().viewCart().isProductInCart(testData.getProduct().getId());

        assertTrue(isProductInCart,
                "Remove button was not found for a product with id=" + testData.getProduct().getId() + "(Egipt - El Gouna)." +
                "Was the product added to cart?");
    }
    @Test
    public void addToCartFromCategoryPageTest() throws IllegalAccessException {
        CategoryPage categoryPage = new CategoryPage(driver).goTo(configuration.getBaseUrl() + testData.getCategoryURL());
        categoryPage.demoNotice.close();
        boolean isProductInCart = categoryPage.addToCart(testData.getProduct().getId()).viewCart().isProductInCart(testData.getProduct().getId());

        assertTrue(isProductInCart,
                "Remove button was not found for a product with id=" + testData.getProduct().getId() + "(Egipt - El Gouna)." +
                        "Was the product added to cart?");
    }
    @Test
    public void addOneProductTenTimesTest(){
        ProductPage productPage = new ProductPage(driver).goTo(configuration.getBaseUrl() + testData.getProduct().getUrl());
        productPage.demoNotice.close();
        int productQuantity = productPage.addToCart(10).viewCart().getProductQuantity();

        assertEquals(10, productQuantity,
                "Quantity of the product is not what expected. Expected: 10, but was " + productQuantity);
    }

    @Test
    public void addTenProductsToCartTest(){
        ProductPage productPage = new ProductPage(driver);
        for (String product: testData.getProductPages()){
            productPage.goTo(configuration.getBaseUrl() + "/product" + product).addToCart();
        }
        int numberOfItems = productPage.header.viewCart().getNumberOfProducts();

        assertEquals(10, numberOfItems,
                "NUmber of items in the cart is not correct. Expected: 10, but was: " + numberOfItems);
    }
    @Test
    public void changeNumberOfProductsTest(){
        ProductPage productPage = new ProductPage(driver).goTo(configuration.getBaseUrl() + testData.getProduct().getUrl());
        productPage.demoNotice.close();
        int quantity = productPage.addToCart().viewCart().changeQuantity(8).updateCart().getProductQuantity();
        assertEquals(8, quantity,
        "Quantity of the product is not what expected. Expected: 8, but was: " + quantity);
    }
    @Test
    public void removePositionFromCartTest() throws IllegalAccessException {
        ProductPage productPage = new ProductPage(driver).goTo(configuration.getBaseUrl() + testData.getProduct().getUrl());
        productPage.demoNotice.close();
        boolean isCartEmpty = productPage.addToCart().viewCart().removeProduct(testData.getProduct().getId()).isCartEmpty();
        assertTrue(isCartEmpty,
                "Cart is not empty after removing the product");
    }
}
