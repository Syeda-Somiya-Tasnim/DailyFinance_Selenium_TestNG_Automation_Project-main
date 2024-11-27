package testrunner;

import config.AddCostDataSet;
import config.Setup;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.UserAddCostPage;
import pages.LoginPage;
import pages.UserDashboardPage;
import utils.Utils;

import java.io.FileReader;
import java.io.IOException;

public class UserDashboardTestRunner extends Setup {
    LoginPage loginPage;
    int totalSum = 0;


    @BeforeTest (groups = "smoke")
    public void setToken() throws ParseException, IOException, InterruptedException {
        Utils.setAuth(driver);
    }

    @Test( priority = 1, dataProvider = "AddCostData", dataProviderClass = AddCostDataSet.class, description = "Adding Data From CSV File to User" , groups = "smoke")
    public void addCost(String name,String amount,int quantity,String date,String month,String remark) throws InterruptedException {
        driver.get("https://dailyfinance.roadtocareer.net/user");
        UserAddCostPage addCostPage=new UserAddCostPage(driver);
        addCostPage.btnAddCost.click();

        addCostPage.addCost(name,amount,quantity,date, month ,remark);
        totalSum+= Integer.parseInt(amount) ;

    }





    @Test(priority = 2, description = "Compare the Expected Total Cost and Actual Total Cost",groups = "smoke")
    public  void costAssertion( ) throws InterruptedException {

        driver.get("https://dailyfinance.roadtocareer.net/user");
        UserDashboardPage dashboardPage = new UserDashboardPage(driver);
        dashboardPage.assertTotalCost(totalSum);


    }

    @Test(priority = 3, description = "Search Item and Assert the price" , groups = "smoke")
    public  void priceAssertion( ) throws InterruptedException {
        driver.get("https://dailyfinance.roadtocareer.net/user");
        UserDashboardPage dashboardPage = new UserDashboardPage(driver);
        dashboardPage.searchItem();


    }



}
