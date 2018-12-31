package dec18hw;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Xmas18 {

    String expectedRegisterSuccessMessage = "Your registration completed";

    WebDriver driver;

    public void clickelement(By by) {

        driver.findElement(by).click();
    }

    public void enterText(By by, String value) {

        driver.findElement(by).sendKeys(value);
    }
    @BeforeTest
    public void openbrowser() {
        //set driver path
        System.setProperty("webdriver.chrome.driver","src\\main\\java\\browserdriver\\chromedriver.exe");
        // asinge driver
        driver = new ChromeDriver();
        // 5 second impicity wait
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        // browser open on full screen
        // driver.manage().window().fullscreen();
        // open url
        driver.get("https://demo.nopcommerce.com");
    }
    //@AfterTest
    public void closebrower() {
        driver.quit();
    }

    @Test
    public void user_should_able_to_register_successfully() {
        //Register page open
        clickelement(By.className("ico-register"));
        // select gender button
        clickelement(By.id("gender-male"));
        // Add First Name
        enterText(By.id("FirstName"), "Pranav");
        System.out.println("Firstname Pranav  will appear");
        // Add Last Name
        enterText(By.id("LastName"), "Mehta");
        System.out.println("LastName Mehta will appear");
        //select the dob box
        Select day = new Select(driver.findElement(By.name("DateOfBirthDay")));
        day.selectByIndex(1);
        System.out.println("User is able to see Day Box");
        // select the month box
        Select month = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        month.selectByVisibleText("January");
        System.out.println("User is able to see month Box");
        // select the year box
        Select Year = new Select(driver.findElement(By.name("DateOfBirthYear")));
        Year.selectByValue("1990");
        // Create Object for date
        DateFormat date = new SimpleDateFormat("MMddyyyyHHmmss");
        Date date1 = new Date();
        String date2 = date.format(date1);
        //Every time change email address by adding date and time
        enterText(By.id("Email"), "Pranav190" + date2 + "@gmail.com");
        System.out.println("user should login with this email id");
        //Enter company name
        enterText(By.id("Company"), "GS IT Hub Ltd");
        System.out.println("use should see company name");
        // newsletter button box
        enterText(By.id("Newsletter"), "Ture");
        System.out.println("User could see newsletter button box");
        // user should enter the password
        enterText(By.id("Password"), "Abc123");
        System.out.println("User is able to enter the password");
        //user should enter the confirm password
        enterText(By.id("ConfirmPassword"), "Abc123");
        System.out.println("User is able to enter the password in the confirm box");
        clickelement(By.id("register-button"));
        //user is able to click on register button
        String actualRegisterSuccessMessage = driver.findElement(By.xpath("//div[@class=\"result\"]")).getText();
        //user should get successfully register message
        Assert.assertEquals("Your registration completed", expectedRegisterSuccessMessage, actualRegisterSuccessMessage);
        //user should click on continue  button
        clickelement(By.xpath("//input[@name=\"register-continue\"]"));
        System.out.println(" User is able to see next page");
    }
    public void Registeredusershouldbeabletosendemailwithproductsuccessfully() {

        user_should_able_to_register_successfully();

        // click on product button
        clickelement(By.xpath("//div[@class=\"details\"]/h2[@class=\"product-title\"]/a[@href=\"/apple-macbook-pro-13-inch\"]"));
        System.out.println("User is able to click on selected product");
        //click on email a friend button
        clickelement(By.xpath("//div[@class=\"email-a-friend\"]"));
        System.out.println("User is able to click on email a friend button");
        // enter the friend email id in the email box
        enterText(By.id("FriendEmail"), "abc123@gmail.com");
        System.out.println("User is able to enter friend email id in the email box");
        // enter the personal message in the box
        enterText(By.id("PersonalMessage"), "add it.");
        System.out.println("User is able to see personal message in the box");
        // user is able to send email
        clickelement(By.name("send-email"));
        System.out.println("user is able to email his/her friend");
        //user is able to sent message successfully
        String actualresult = driver.findElement(By.xpath("//div[@class=\"result\"]")).getText();
        // user is able to sent message
        String expected = "Your message has been sent.";
        System.out.println("User is able to sent message successfully");
        Assert.assertEquals("Your message has been sent.", expected, actualresult);
        //user should //a[@class="ico-logout"]
        clickelement(By.xpath("//a[@class=\"ico-logout\"]"));
        System.out.println("user is able to log out");
    }

    @Test

    public void Unregistereduseshouldnotbeabletosendemail() {
        // click on the product
        clickelement(By.xpath("//div[@class=\"details\"]/h2[@class=\"product-title\"]/a[@href=\"/build-your-own-computer\"]"));
        System.out.println("Product page will appear");
        //click on email to friend button
        clickelement(By.xpath("//div[@class=\"overview-buttons\"]/div[@class=\"email-a-friend\"]"));
        System.out.println("Email a friend page will appear");
        //enter the friend email id
        enterText(By.id("FriendEmail"), "abc123@gmail.com");
        System.out.println("Friend email id shoud apper in the mail box");
        //enter your email id
        enterText(By.id("YourEmailAddress"), "quick123@gmail.com");
        System.out.println("User email id appear in the mail box");
        //write personal message in the personal message box
        enterText(By.id("PersonalMessage"), "send message to friend");
        System.out.println("user is able to see message in the personal message box");
        // click on send email
        clickelement(By.name("send-email"));
        System.out.println("user is able send message");
        String expected = "Only registered customers can use email a friend feature";
        // user is able to send message and get error
        String actualresult = driver.findElement(By.xpath("//div[@class=\"message-error validation-summary-errors\"]")).getText();
        System.out.println(" user is not able to send message and get error");
        Assert.assertEquals("Only registered customers can use email a friend feature", expected, actualresult);
    }

    @Test

    public void userneedtoaccepttermsTermsandConditionasperbelowscreen() {

        //click on the selected product
        clickelement(By.xpath("//div[@class=\"details\"]/h2[@class=\"product-title\"]/a[@href=\"/apple-macbook-pro-13-inch\"]"));
        System.out.println("user able to see product page");
        //click on add to craft product
        clickelement(By.id("add-to-cart-button-4"));
        System.out.println(" user is able to add the selected product");
        //click on shopping cart button
        clickelement(By.xpath("//p[@class=\"content\"]/a[@href=\"/cart\"]"));
        System.out.println(" user is able to see shopping cart page");
        // click on checkout button
        clickelement(By.id("checkout"));
        System.out.println(" user is able to click on checkout button");
        //user can see the terms conditon message
        String actualresult = driver.findElement(By.id("terms-of-service-warning-box")).getText();
        System.out.println("user is able to see message in the box");
        String expected = "Please accept the terms of service before the next step.";
        Assert.assertEquals("Please accept the terms of service before the next step.", expected, actualresult);
    }

    @Test

    public void Registeredusershouldbeabletobuyanysingleproductsuccessfully() {


        user_should_able_to_register_successfully();

        //click on the selected product
        clickelement(By.xpath("//div[@class=\"details\"]/h2[@class=\"product-title\"]/a[@href=\"/apple-macbook-pro-13-inch\"]"));
        System.out.println("user able to see product page");
        //click on add to craft product
        clickelement(By.id("add-to-cart-button-4"));
        System.out.println(" user is able to add the selected product");
        //click on shopping cart button
        clickelement(By.xpath("//p[@class=\"content\"]/a[@href=\"/cart\"]"));
        System.out.println(" user is able to see shopping cart page");
        // user should agree with term and condition
        clickelement(By.xpath("//label[@for=\"termsofservice\"]"));
        System.out.println("User has agreed with terms condition ");
        //user can clieck on checkout button
        clickelement(By.xpath("//button[@type=\"submit\"]"));
        System.out.println("user can see next page");
        // select country name
        enterText(By.xpath("//select[@data-val=\"true\"]"), "United Kingdom");
        System.out.println(" user is able to select country name");
        // select city name
        enterText(By.name("BillingNewAddress.City"), "London");
        System.out.println("user is able to enter city name");
        //enter the first line address
        enterText(By.name("BillingNewAddress.Address1"), "100 mollison way");
        System.out.println("user is able to enter firstline of address");
        // user is able to enter post code
        enterText(By.id("BillingNewAddress_ZipPostalCode"), "ha8 5qw");
        System.out.println("user is able to enter postcode");
        //user is able to enter phone number
        enterText(By.id("BillingNewAddress_PhoneNumber"), "00447872514199");
        System.out.println(" user is able to enter phone number");
        //user should click on continue button
        clickelement(By.xpath("//div[@id=\"billing-buttons-container\"]/input[@title=\"Continue\"]"));
        System.out.println(" user is able to go  next step");
        //user should able to select shipping method
        clickelement(By.id("shippingoption_0"));
        System.out.println("user is able to click on shipping method button");
        // user is able to click on continue button
        clickelement(By.xpath("//input[@onclick=\"ShippingMethod.save()\"]"));
        System.out.println("user is able to click on continue button and see next step");
        //user can select payment mode
        clickelement(By.xpath("//input[@id=\"paymentmethod_1\"]"));
        System.out.println("user is able to click credit card payment method");
        //user should click on continue button
        clickelement(By.xpath("//input[@class=\"button-1 payment-method-next-step-button\"]"));
        System.out.println("user should able to go next page");
        //user is able to select creditcard type
        enterText(By.id("CreditCardType"), "Visa");
        System.out.println("user can select creditcard type");
        //user should enter the cardholder name
        enterText(By.id("CardholderName"), "Mr p mehta");
        System.out.println("cardholder name should apper in the box");
        // user is able to enter card number in the box
        enterText(By.name("CardNumber"), "4111 1111 1111 1111");
        System.out.println("user is able to see card details in the box");
        //user is able to enter card detail in expire month box
        enterText(By.id("ExpireMonth"), "07");
        System.out.println("user is able to enter card detail in the expire month box");
        //user is able to enter data in expire year box
        enterText(By.id("ExpireYear"), "2021");
        System.out.println("user is able to see enter date in the expire year box");
        //use is able to enter cvv number in the securty card box
        enterText(By.id("CardCode"), "737");
        System.out.println("user is able to enter security code in the box");
        //user is able to click continue button
        clickelement(By.xpath("//input[@class=\"button-1 payment-info-next-step-button\"]"));
        System.out.println("user is able to click continue button and  go to next step");
        //user is able to click confirm button
        clickelement(By.xpath("//input[@onclick=\"ConfirmOrder.save()\"]"));
        System.out.println("user is able to click on confirm button and see next step");
        String expected = "Your order has been successfully processed!";
        String actual = driver.findElement(By.xpath("//div[@class=\"page-body checkout-data\"]/div/div[@class=\"title\"]/strong")).getText();
        Assert.assertEquals("Your order has been successfully processed!", expected, actual);
    }

    @Test

    public void UsershouldabletosortbypriceHightolow() {

        user_should_able_to_register_successfully();

        // user is able to click on electronic  product image
        clickelement(By.xpath("//img[@alt=\"Picture for category Electronics\"]"));
        System.out.println("User is able to enter electronic product page");
        //user is able to click on camera and photo
        clickelement(By.xpath("//img[@alt=\"Picture for category Camera & photo\"]"));
        System.out.println("User is able to see camera and photo page");
        // user is able to select sort by "position"
        clickelement(By.id("products-orderby"));
        System.out.println("User is able to select positon");
        //user is able to select high to low position
        clickelement(By.xpath("//select[@id=\"products-orderby\"]/option[@value=\"https://demo.nopcommerce.com/camera-photo?orderby=11\"]"));
        System.out.println("user is able to select high to low position");
        //user is able to select value of product
        String valueoffirstproduct = driver.findElement(By.xpath("//div[@class=\"page-body\"]/div[3]/div/div[1]/div/div[2]/div[3]/div/span[1]")).getText();
        System.out.println(valueoffirstproduct);
        //user is able to select value of last product
        String valueoflastproduct = driver.findElement(By.xpath("//div[@class=\"page-body\"]/div[3]/div/div[3]/div/div[2]/div[3]/div/span[1]")).getText();
        System.out.println(valueoflastproduct);


        String trimvalueFP = valueoffirstproduct.substring(1,valueoffirstproduct.length()-3);
        String trimvalueLP = valueoflastproduct.substring(1,valueoflastproduct.length()-3);
        System.out.println("Value of first product in list" + trimvalueFP);
        System.out.println("Value of last product in list" + trimvalueLP);
        String trimvalueFP1 = "";

        for (int i = 0; i <= trimvalueFP.length() - 1; i++) {
            char c = trimvalueFP.charAt(i);
            if (c != ',') {
                trimvalueFP1 = trimvalueFP1 + c;
            }
        }

        System.out.println("trimvalueFP1");

        int final_value_of_FirstProduct_in_int = Integer.parseInt(trimvalueFP1);

        int final_value_of_LastProduct_in_int = Integer.parseInt(trimvalueLP);

        if (final_value_of_FirstProduct_in_int > final_value_of_LastProduct_in_int) {

            Assert.assertNotEquals(final_value_of_FirstProduct_in_int, final_value_of_LastProduct_in_int);

        }


    }


}
