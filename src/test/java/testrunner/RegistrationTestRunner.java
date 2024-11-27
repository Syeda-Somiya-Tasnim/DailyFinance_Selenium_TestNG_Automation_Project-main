package testrunner;

import com.github.javafaker.Faker;
import config.Setup;
import config.UserModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RegistrationPage;
import utils.Utils;

import java.io.IOException;
import java.time.Duration;

public class RegistrationTestRunner extends Setup {
    @Test(priority = 2, description = "User can register by providing all info", groups = "smoke")
    public void userRegByAllFields() throws InterruptedException, IOException, ParseException {
        RegistrationPage userReg=new RegistrationPage(driver);
        Faker faker=new Faker();
        userReg.btnRegister.click();
        String firstname=faker.name().firstName();
        String lastname=faker.name().lastName();
        //String email=faker.internet().emailAddress();
        String email = Utils.geneateRandomEmail();
        String password="1234";
        String phonenumber= "01505"+Utils.generateRandomNumber(100000,999999);
        String address=faker.address().fullAddress();
        UserModel userModel=new UserModel();
        userModel.setFirstname(firstname);
        userModel.setLastname(lastname);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setPhonenumber(phonenumber);
        userModel.setAddress(address);
        userReg.doRegistration(userModel);

        //assertion
        doRegAssertion();

        JSONObject userObj=new JSONObject();
        userObj.put("firstName",firstname);
        userObj.put("lastName",lastname);
        userObj.put("email",email);
        userObj.put("password",password);
        userObj.put("phoneNumber",phonenumber);
        userObj.put("address",address);
        Utils.saveUserInfo("./src/test/resources/users.json",userObj);

        // Waiting for register button to be loaded and clickable properly
       // Thread.sleep(3000);
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.elementToBeClickable(userReg.btnRegister));
    }
    @Test(priority = 3, description = "User can register by providing only mandatory info")
    public void userRegByMandatoryFields() throws IOException, ParseException, InterruptedException {
        RegistrationPage userReg=new RegistrationPage(driver);
        Faker faker=new Faker();

        userReg.btnRegister.click();
        String firstname=faker.name().firstName();
        //String email=faker.internet().emailAddress();
        String email = Utils.geneateRandomEmail();
        String password="1234";
        String phonenumber= "01505"+Utils.generateRandomNumber(100000,999999);
        UserModel userModel=new UserModel();
        userModel.setFirstname(firstname);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setPhonenumber(phonenumber);
        userReg.doRegistration(userModel);

        doRegAssertion();
        JSONObject userObj=new JSONObject();
        userObj.put("firstName",firstname);
        userObj.put("email",email);
        userObj.put("password",password);
        userObj.put("phoneNumber",phonenumber);

        Utils.saveUserInfo("./src/test/resources/users.json",userObj);

        // Waiting for register button to be loaded and clickable properly
        //Thread.sleep(3000);
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.elementToBeClickable(userReg.btnRegister));
    }

    @Test(priority = 1, description = "User can not register by missing any one mandatory info")
    public void userRegMissingAnyMandatoryField() throws IOException, ParseException, InterruptedException {
        RegistrationPage userReg=new RegistrationPage(driver);
        Faker faker=new Faker();
        userReg.btnRegister.click();
        String firstname=faker.name().firstName();
       // String email=faker.internet().emailAddress();
        String email = Utils.geneateRandomEmail();
        String password="";
        String phonenumber= "01505"+Utils.generateRandomNumber(100000,999999);
        UserModel userModel=new UserModel();
        userModel.setFirstname(firstname);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setPhonenumber(phonenumber);
        userReg.doRegistration(userModel);

        invalidRegAssertion();

        // Going Back To the registration Page
        driver.findElement(By.tagName("a")).click();

        // Waiting for register button to be loaded and clickable properly
        // Thread.sleep(3000);
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.elementToBeClickable(userReg.btnRegister));


    }

    public void invalidRegAssertion() throws InterruptedException {

        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        String errorMessageActual= element.getAttribute("validationMessage");
        String errorMessageExpected="Please fill out this field";
        System.out.println(errorMessageActual);
        Assert.assertTrue(errorMessageActual.contains(errorMessageExpected));
    }

    public void doRegAssertion() throws InterruptedException {
        Thread.sleep(2000);
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("Toastify__toast")));
        String successMessageActual= driver.findElement(By.className("Toastify__toast")).getText();
        String successMessageExpected="successfully";
        System.out.println(successMessageActual);
        Assert.assertTrue(successMessageActual.contains(successMessageExpected));
    }
}
