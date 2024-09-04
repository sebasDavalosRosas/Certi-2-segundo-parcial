import com.google.common.collect.Ordering;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomePageTests extends BaseTest {

    @Test
    public void testAddProductToCart() {
        WebElement productButton = driver.findElement(By.xpath("//button[@id ='add-to-cart-sauce-labs-onesie']"));
        productButton.click();

        WebElement cartBadge = driver.findElement(By.className("shopping_cart_badge"));
        String itemsInCart = cartBadge.getText();

        assertEquals("1", itemsInCart, "El carrito debería contener 1 producto.");
    }
    @Test
    public void testRemoveProductFromCart()
    {
        // Añadir un producto al carrito
        WebElement productButton = driver.findElement(By.xpath("//button[@data-test='add-to-cart-sauce-labs-backpack']"));
        productButton.click();

        // Eliminar el producto del carrito
        WebElement removeButton = driver.findElement(By.xpath("//button[@data-test='remove-sauce-labs-backpack']"));
        removeButton.click();

        // Verificar que el carrito esté vacío
        boolean cartIsEmpty = driver.findElements(By.className("shopping_cart_badge")).isEmpty();
        assertTrue(cartIsEmpty, "El carrito debería estar vacío.");
    }

    @Test
    public void orderingFilterFromZToATest() throws InterruptedException
    {
        WebElement sortComboBox = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.className("product_sort_container")));

        Select selectObject = new Select(sortComboBox);
        selectObject.selectByVisibleText("Name (Z to A)");

        //Verification
        List<WebElement> productNames = driver.findElements(By.className("inventory_item_name"));

        List<String> actualProductsOrder = new ArrayList<>();

        for(WebElement element: productNames){
            actualProductsOrder.add(element.getText());
        }

        boolean isSorted = Ordering.natural().reverse().isOrdered(actualProductsOrder);
        assertTrue(isSorted);
    }
    @Test
    public void orderingFilterFromAToZTest() throws InterruptedException
    {
        WebElement sortComboBox = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.className("product_sort_container")));

        Select selectObject = new Select(sortComboBox);
        selectObject.selectByVisibleText("Name (A to Z)");

        //Verification
        List<WebElement> productNames = driver.findElements(By.className("inventory_item_name"));

        List<String> actualProductsOrder = new ArrayList<>();

        for(WebElement element: productNames){
            actualProductsOrder.add(element.getText());
        }

        boolean isSorted = Ordering.natural().isOrdered(actualProductsOrder);
        assertTrue(isSorted);
    }
    @Test
    public void testSortProductsByPriceLowToHigh() {
        Select sortDropdown = new Select(driver.findElement(By.className("product_sort_container")));
        sortDropdown.selectByValue("lohi");

        List<WebElement> pricesElements = driver.findElements(By.className("inventory_item_price"));
        List<Double> prices = pricesElements.stream()
                .map(element -> Double.parseDouble(element.getText().replace("$", "")))
                .toList();

        boolean isSorted = true;
        for (int i = 0; i < prices.size() - 1; i++) {
            if (prices.get(i) > prices.get(i + 1)) {
                isSorted = false;
                break;
            }
        }
        assertTrue(isSorted, "Los productos deben estar ordenados por precio de menor a mayor.");
    }
    @Test
    public void testSortProductsByPriceHighToLow() {
        Select sortDropdown = new Select(driver.findElement(By.className("product_sort_container")));
        sortDropdown.selectByValue("hilo");

        List<WebElement> pricesElements = driver.findElements(By.className("inventory_item_price"));
        List<Double> prices = pricesElements.stream()
                .map(element -> Double.parseDouble(element.getText().replace("$", "")))
                .toList();

        boolean isSorted = true;
        for (int i = 0; i < prices.size() - 1; i++) {
            if (prices.get(i) < prices.get(i + 1)) {
                isSorted = false;
                break;
            }
        }
        assertTrue(isSorted, "Los productos deben estar ordenados por precio de mayor a menor.");
    }
    @Test
    public void testInventoryItemCount() {
        List<WebElement> inventoryItems = driver.findElements(By.className("inventory_item"));
        assertEquals(6, inventoryItems.size(), "El inventario debería mostrar 6 productos.");
    }
}
