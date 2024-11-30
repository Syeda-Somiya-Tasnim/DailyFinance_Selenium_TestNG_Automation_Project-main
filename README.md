# **Daily Finance Automation Testing**


### **Prerequisites**
Before running the tests, make sure you have the following installed:
- **Java**: Version 8 or higher
- **Gradle**: Version 6.0 or higher
- **Selenium WebDriver**
- **TestNG**
- **Allure (for generating reports)**

## **2. Clone the Repository**

Clone the repository to your local machine:

```bash
git clone https://github.com/Syeda-Somiya-Tasnim/DailyFinance_Selenium_TestNG_Automation_Project-main.git
cd DailyFinance_Selenium_TestNG_Automation_Project-main
```



## **Test Cases Overview**

The following test cases are implemented:

### **1. Visit the website and Register User implementing different scenarios**
- **Description**: Visit the website [https://dailyfinance.roadtocareer.net/](https://dailyfinance.roadtocareer.net/) and test the user registration functionality with different scenarios.
- **Scenarios**:
    1. **Register a user with all fields**: Register a new user by filling out all the required fields.
        - **Action**: Fill all fields (e.g., name, email, password, etc.).
        - **Assertion**: Assert the success message.
    
    2. **Register a user with only mandatory fields**: Register a new user by filling in only the mandatory fields.
        - **Action**: Fill only the mandatory fields (e.g., name, email, password).
        - **Assertion**: Assert the success message.
    
    3. **Try to register without any mandatory fields**: Attempt to register without providing any mandatory fields.
        - **Action**: Leave the mandatory fields blank and try to submit the form.
        - **Assertion**: Assert that an error message is shown indicating the mandatory fields are required.

### **2. Log in as admin and verify the last registered user**
- **Description**: Log in as an admin and check if the last registered user is displayed on the admin dashboard.
- **Action**: Log in using admin credentials from the terminal (e.g., `admin@test.com` / `admin123`) and check the admin dashboard for the last registered user.
- **Assertion**: Verify that the last registered user's first name, email, and phone number match the expected values stored in the test data.

### **3. Log in with the last registered user and update their profile image**
- **Description**: Log in with the most recently registered user and update their profile image.
- **Action**: Use the credentials of the last registered user to log in and upload a new profile image.
- **Assertion**: Assert that the profile image is updated successfully.

### **4. Add cost/expenditure from a CSV file**
- **Description**: Add cost/expenditure details from a CSV file containing 5 rows, each representing a different item with names, amounts, quantities, purchase dates, months, and remarks.
- **Action**: Loop through the 5 rows of the CSV file and input each data set into the application.
- **Assertion**: Print and assert the total cost based on the input values from the CSV file.

### **5. Print and assert the total cost**
- **Description**: After adding the expenditure items from the CSV file, calculate the total cost and verify that it matches the expected total sum.
- **Action**: Calculate the total cost of all items added and assert the calculated sum against the expected total value.
- **Assertion**: Assert that the total sum is correct.

### **6. Search for an item by name**
- **Description**: Search for an item by name in the list of expenditures and verify that the price matches the expected value.
- **Action**: Search for an item by name from the list of items.
- **Assertion**: Assert that the price of the searched item matches the expected price from the CSV data.

### **7. Create Regression Suite and Smoke Suite**
- **Description**: Create two TestNG suites, one for regression testing and one for smoke testing, and execute them individually.
- **Action**:
    1. **Regression Suite**: Create a suite to execute all test cases.
    2. **Smoke Suite**: Create a suite to execute only selected tests (e.g., test cases related to cost/expenditure and total cost calculation).
- **Execution**: Run the Regression and Smoke suites individually using the following commands:
    - **Run Regression Suite**:
      ```bash
      ./gradlew clean test -PsuiteName="regressionSuite.xml"
      ```
    - **Run Smoke Suite**:
      ```bash
      ./gradlew clean test -PsuiteName="smokeSuite.xml"
      ```

---

### **8. Generate Allure Report for the Regression Suite**
- **Description**: After running the regression suite, generate an Allure report to visualize the results.
- **Action**:
    1. Generate the Allure report with the following command:
       ```bash
       allure generate build/allure-results -o build/allure-report
       ```
    2. Serve the Allure report:
       ```bash
       allure serve build/allure-results
       ```
- **Assertion**: Review the Allure report to ensure the tests passed and the data is accurate.

---

## **Test Suite Output**

### **Smoke Suite**
- **Output Video Link**:  
  [Click Here To see the output video of Smoke Suite Automation Script](https://drive.google.com/file/d/1wThJFPkB124LmyltqXpbiet5bNeOQOEv/view?usp=drive_link)  
- **Report**:  
  ![Image 1](https://github.com/user-attachments/assets/600eb028-4abd-44be-a5ee-96e41878d73f)  
  ![Image 2](https://github.com/user-attachments/assets/f3a29654-9618-4c1d-a777-8c833a3419da)

---

### **Regression Suite**
- **Output Video Link**:  
  [Click Here To see the output video of Regression Suite Automation Script](https://drive.google.com/file/d/1nu2TdQY3TI2FajypX5sNeJvG1zuvp7z6/view?usp=drive_link)  
- **Report**:  
  ![Image 1](https://github.com/user-attachments/assets/f2de1c31-73df-4fd0-8851-117583e2642c)
  ![Image 2](https://github.com/user-attachments/assets/ce7e0f1e-10d5-417f-ad6c-82ad43833453)  

---




















