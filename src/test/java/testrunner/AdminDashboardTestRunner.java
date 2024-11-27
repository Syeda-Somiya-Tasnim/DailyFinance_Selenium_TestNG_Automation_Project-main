package testrunner;

import config.Setup;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.AdminDashboardPage;
import pages.LoginPage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class AdminDashboardTestRunner extends Setup {
    LoginPage loginPage;
    @BeforeTest
    public void doLogin() throws IOException, ParseException {
        loginPage=new LoginPage(driver);
        if(System.getProperty("username")!=null && System.getProperty("password")!=null){
            loginPage.doLogin(System.getProperty("username"),System.getProperty("password"));
        }
        else{
            loginPage.doLogin("admin@test.com","admin123");
        }
    }

    @Test( description = "Check the newly created user is registered on the DashBoard" )
    public void userValueMatching() throws IOException, ParseException {
        JSONParser parser=new JSONParser();
        JSONArray jsonArray= (JSONArray) parser.parse(new FileReader("./src/test/resources/users.json"));

        JSONObject userObj= (JSONObject) jsonArray.get(jsonArray.size()-1);
        String fName = (String) userObj.get("firstName");
        String email= (String) userObj.get("email");
        String phoneNum= (String) userObj.get("phoneNumber");

        AdminDashboardPage admin = new AdminDashboardPage(driver);
        admin.tableDataMatching(fName,email,phoneNum);
    }


}
