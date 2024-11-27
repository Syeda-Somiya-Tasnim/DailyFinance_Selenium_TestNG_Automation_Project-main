package pages;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class AdminDashboardPage {

    @FindBy(tagName="tbody")
    WebElement table;

    public AdminDashboardPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void tableDataMatching(String fName, String email , String phoneNumber){
        WebElement firstDataRow = table.findElements(By.tagName("tr")).get(0);
          int i=0;
          String FirstName;
          String Email ;
          String PhoneNum;
            List<WebElement> cells = firstDataRow.findElements(By.tagName("td"));

            // Firstname
            FirstName = cells.get(0).getText();
            Assert.assertEquals(FirstName,fName);

            // Email
            Email = cells.get(2).getText();
            Assert.assertEquals(Email,email);

            // Phone Number
            PhoneNum = cells.get(3).getText();
            Assert.assertEquals(PhoneNum,phoneNumber);



    }
}


