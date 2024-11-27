package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.Utils;

import java.time.Duration;
import java.util.List;

public class UserProfilePage {

      WebDriver driver;

      @FindBy(tagName = "button")
      public List<WebElement> buttons;

      @FindBy(className = "upload-input")
      public WebElement imgFileSelect;

      public UserProfilePage(WebDriver driver){
            PageFactory.initElements(driver,this);
            this.driver = driver;
      }

      public void uploadPicture() throws InterruptedException {
            buttons.get(1).click(); // Edit Button
            imgFileSelect.sendKeys(System.getProperty("user.dir")+ "./src/test/resources/profilepic.png");
           // Thread.sleep(2000);
            buttons.get(1).click();// Upload Image Button

            // Image upload Alert
            WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(50));
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            String ImgUploadMsgExpected = "Image uploaded successfully!";
            String ImgUploadMsgActual = alert.getText();
            Assert.assertTrue(ImgUploadMsgActual.contains(ImgUploadMsgExpected));
            alert.accept();


            WebElement img = driver.findElement(By.className("profile-image"));
            wait.until(ExpectedConditions.visibilityOf(img));
            buttons.get(2).click();// update button


            // Profile update alert
            wait.until(ExpectedConditions.alertIsPresent());
            alert = driver.switchTo().alert();
            String userUpdateMsgExpected = "User updated successfully!";
            String userUpdateMsgActual = alert.getText();
            Assert.assertTrue(userUpdateMsgActual.contains(userUpdateMsgExpected));
            alert.accept();



      }

}
