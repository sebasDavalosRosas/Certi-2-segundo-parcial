import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductPageTests extends BaseTest{
    @Test
    public void addToCartTest() throws InterruptedException {

        WebElement product = driver.findElement(By.xpath("//*[@id='item_4_title_link']/div"));
        product.click();

        WebElement button = driver.findElement(By.xpath("//button[@id='add-to-cart']"));
        button.click();

        // //span[@class='fa-layers-counter shopping_cart_badge']
        // span.fa-layers-counter.shopping_cart_badge
        WebElement cartIcon = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']"));

        String actualCartText = cartIcon.getText();
        assertEquals("1",actualCartText );
    }
    @Test
    public void removeFromCartTest() throws InterruptedException {
        WebElement productButton = driver.findElement(By.xpath("//button[@id ='add-to-cart-sauce-labs-backpack']"));
        productButton.click();
        WebElement productButton1 = driver.findElement(By.xpath("//button[@id ='add-to-cart-sauce-labs-onesie']"));
        productButton1.click();

        WebElement product = driver.findElement(By.xpath("//*[@id='item_4_title_link']/div"));
        product.click();

        WebElement button = driver.findElement(By.xpath("//button[@id='remove']"));
        button.click();
        // //span[@class='fa-layers-counter shopping_cart_badge']
        // span.fa-layers-counter.shopping_cart_badge
        WebElement cartIcon = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']"));

        String actualCartText = cartIcon.getText();
        assertEquals("1",actualCartText );
    }
    @Test
    public void testCancelCheckoutAfterEnteringShippingInfo() {
        WebElement productButton = driver.findElement(By.xpath("//button[@data-test='add-to-cart-sauce-labs-backpack']"));
        productButton.click();
        WebElement cartLink = driver.findElement(By.className("shopping_cart_link"));
        cartLink.click();
        WebElement checkoutButton = driver.findElement(By.id("checkout"));
        checkoutButton.click();
        driver.findElement(By.id("first-name")).sendKeys("Juan");
        driver.findElement(By.id("last-name")).sendKeys("Pérez");
        driver.findElement(By.id("postal-code")).sendKeys("12345");
        driver.findElement(By.xpath("//input[@name='continue']")).click();

        // Cancelar el checkout
        WebElement cancelButton = driver.findElement(By.id("cancel"));
        cancelButton.click();

        // Verificar que el usuario ha sido redirigido a la página del carrito
        String currentUrl = driver.getCurrentUrl();
        assertEquals("https://www.saucedemo.com/inventory.html", currentUrl, "El usuario debería ser redirigido a la página del carrito.");
    }

}
