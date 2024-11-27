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
import pages.LoginPage;
import pages.UserProfilePage;
import utils.Utils;

import java.io.FileReader;
import java.io.IOException;

public class UserProfileTestRunner extends Setup {

    LoginPage loginPage;

    @Test(priority = 1, description = "User can login with correct creds" , groups = "smoke")
    public void userLogin() throws ParseException, IOException {
        loginPage=new LoginPage(driver);
        JSONParser parser=new JSONParser();
        JSONArray jsonArray= (JSONArray) parser.parse(new FileReader("./src/test/resources/users.json"));

        JSONObject userObj= (JSONObject) jsonArray.get(jsonArray.size()-1);
        String email= (String) userObj.get("email");
        String password= (String) userObj.get("password");

        loginPage.doLogin(email,password);
        String expectedMsg = "User Daily Costs";
        String actualMsg = loginPage.dashboardMsg.getText();
        Assert.assertTrue(actualMsg.contains(expectedMsg));
        Utils.getToken(driver);

    }

    @Test( priority = 2, description = "Update User Photo")
    public  void updateProfilePic() throws InterruptedException {


        loginPage.btnProfileIcon.click();
        loginPage.btnProfileMenuItems.get(0).click();

        UserProfilePage userProfilePage = new UserProfilePage(driver);
        userProfilePage.uploadPicture();


    }
}
