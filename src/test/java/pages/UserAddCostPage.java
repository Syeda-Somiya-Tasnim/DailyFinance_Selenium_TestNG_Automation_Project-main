package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class UserAddCostPage {

    WebDriver driver;

    @FindBy(className = "add-cost-button")
    public WebElement btnAddCost;
    @FindBy(css = "button[type='button']")
    public List<WebElement> btnIncreaseQuantity;
    @FindBy(id = "itemName")
    WebElement txtItemName;
    @FindBy(id="amount")
    WebElement txtAmount;
    @FindBy(id = "purchaseDate")
    WebElement dateElement;
    @FindBy(id = "remarks")
    WebElement txtRemark;
    @FindBy(id = "month")
    WebElement dropDownMonths;
    @FindBy(css = "[type=submit]")
    WebElement btnSubmit;
    public UserAddCostPage(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }
    public void addCost(String name,String amount,int quantity,String date,String month,String remark) throws InterruptedException {
        txtItemName.sendKeys(name);

        for (int i=2; i<=quantity ; i++)
            btnIncreaseQuantity.get(2).click();

        txtAmount.sendKeys(amount);

       // WebElement dateElement = driver.findElement(By.id("purchaseDate"));
        dateElement.clear();
        dateElement.sendKeys(date);

        Select monthSelect = new Select(dropDownMonths);
        monthSelect.selectByValue(month);

        txtRemark.sendKeys(remark);

        btnSubmit.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
         wait.until(ExpectedConditions.alertIsPresent());
         driver.switchTo().alert().accept();
    }
}
