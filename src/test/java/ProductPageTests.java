import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
        Assertions.assertEquals("1",actualCartText );
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
        Assertions.assertEquals("1",actualCartText );
    }

}
