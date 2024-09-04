import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckOutTests extends BaseTest{
    @Test
    public void testCompleteCheckout() {
        // Añadir un producto al carrito
        WebElement productButton = driver.findElement(By.xpath("//button[@data-test='add-to-cart-sauce-labs-backpack']"));
        productButton.click();

        // Ir al carrito
        WebElement cartLink = driver.findElement(By.className("shopping_cart_link"));
        cartLink.click();

        // Comenzar el proceso de compra
        WebElement checkoutButton = driver.findElement(By.id("checkout"));
        checkoutButton.click();

        // Completar información de pago
        driver.findElement(By.id("first-name")).sendKeys("Juan");
        driver.findElement(By.id("last-name")).sendKeys("Pérez");
        driver.findElement(By.id("postal-code")).sendKeys("12345");
        driver.findElement(By.id("continue")).click();

        // Finalizar compra
        WebElement finishButton = driver.findElement(By.id("finish"));
        finishButton.click();

        // Verificar que el mensaje de finalización de compra sea el correcto
        WebElement confirmationMessage = driver.findElement(By.className("complete-header"));
        assertEquals("Thank you for your order!", confirmationMessage.getText(), "El mensaje de confirmación debe ser 'THANK YOU FOR YOUR ORDER'.");
    }
    @Test
    public void testEmptyFields()
    {
        // Añadir un producto al carrito
        WebElement productButton = driver.findElement(By.xpath("//button[@data-test='add-to-cart-sauce-labs-backpack']"));
        productButton.click();

        // Ir al carrito
        WebElement cartLink = driver.findElement(By.className("shopping_cart_link"));
        cartLink.click();

        // Comenzar el proceso de compra
        WebElement checkoutButton = driver.findElement(By.id("checkout"));
        checkoutButton.click();

        driver.findElement(By.id("continue")).click();

        WebElement errorMessage = driver.findElement(By.xpath("//div[@class='error-message-container error']"));
        assertEquals("Error: First Name is required", errorMessage.getText());

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
