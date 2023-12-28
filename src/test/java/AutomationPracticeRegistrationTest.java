import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

    public class AutomationPracticeRegistrationTest {

        WebDriver driver;

        @BeforeMethod
        public void setup() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        // Povecava prozor na maksimum
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }


        @AfterMethod
        public void tearDown() {

        driver.quit();
    }


        @Test
        public void ContactForm() {
        //Click on Contact Us
        driver.findElement(By.cssSelector("[title='Contact us']")).click();

        //Select value
        Select select = new Select(driver.findElement(By.id("id_contact")));
        select.selectByVisibleText("Webmaster");
//        select.selectByIndex(2);
//

        // Enter email address
        driver.findElement(By.xpath("//*[@id='email']")).sendKeys("test@test.zcom");
        //enter order reference
        driver.findElement(By.xpath("//*[@id='id_order']")).sendKeys("123456789");
        //upload file.
        driver.findElement(By.cssSelector("#fileUpload")).sendKeys("C:\\Users\\NemanjaPecic\\Downloads\\Test.txt");
        //Enter message
        driver.findElement(By.cssSelector("#message")).sendKeys("Test, Test");

        //Click send
        driver.findElement(By.id("submitMessage")).click();

        String text = driver.findElement(By.cssSelector(".alert.alert-success")).getText();
        String color = driver.findElement(By.cssSelector(".alert.alert-success")).getCssValue("background-color");
        String classValue = (driver.findElement(By.cssSelector(".alert.alert-success")).getAttribute("class"));

//        System.out.println(color);

        Assert.assertEquals(text,"Your message has been successfully sent to our team.");
        Assert.assertEquals(color, "rgba(85, 198, 94, 1)");
        Assert.assertEquals(classValue,"alert alert-success");
    }



        @Test
        public void registrationTest() throws InterruptedException {

            driver.get("http://www.automationpractice.pl/index.php");

        // Generate a random email address
        //  String randomEmail = "test" + UUID.randomUUID().toString().substring(0, 8) + "@example.com";

        //Click on Sign in
        driver.findElement(By.className("login")).click();

        //Fills in the email address
        driver.findElement(By.cssSelector("#email_create")).sendKeys("test" + System.currentTimeMillis() + "@test.com");

        //Click on Create an account
        driver.findElement(By.id("SubmitCreate")).click();

        //Waits for 2 seconds
        Thread.sleep(2000);

        //We select the radio button Mr.
        driver.findElement(By.id("id_gender1")).click();


        //We enter the First Name
        driver.findElement(By.cssSelector("#customer_firstname")).sendKeys("Jessie");

        //We enter the Last Name
        driver.findElement(By.cssSelector("#customer_lastname")).sendKeys("Jamess");

        //We clear the old email address

        driver.findElement(By.id("email")).clear();


        //We enter the new email address
        driver.findElement(By.id("email")).sendKeys("test" + System.currentTimeMillis() + "@test.com");

        //We enter the password
        driver.findElement(By.id("passwd")).sendKeys("Test123");

        //Select value
        // First dropdown
        Select select1 = new Select(driver.findElement(By.id("days")));
        select1.selectByIndex(5);

        // Second dropdown
        Select select2 = new Select(driver.findElement(By.id("months")));
        select2.selectByIndex(6);

        // Third dropdown
        Select select3 = new Select(driver.findElement(By.id("years")));
        select3.selectByValue("1985");

        //Select Register
        driver.findElement(By.id("submitAccount")).click();

        //  Thread.sleep(5000);

        // Get text after registration
        WebElement successMessageElement = driver.findElement(By.xpath("//*[@id=\"center_column\"]/p[1]"));
        String successMessage = successMessageElement.getText();
        System.out.println("Success Message: " + successMessage);

        String username = driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a/span")).getText();

        Assert.assertEquals(driver.getCurrentUrl(),"http://www.automationpractice.pl/index.php?controller=my-account" );
        Assert.assertEquals(username,"Jessie Jamess");

        Thread.sleep(2000);
        //Clicks on ADD my first Address button
        driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/div/ul/li[1]/a")).click();

        //we add Company name
        driver.findElement(By.id("company")).sendKeys("Test123");

        //We add Address into the field
        driver.findElement(By.id("address1")).sendKeys("33 Orchard Dr UNIT 33");

        //We add Address2 into the field
        driver.findElement(By.id("address2")).sendKeys("33");

        //We add City into the field
        driver.findElement(By.id("city")).sendKeys("Stow");

        //Pravimo select za State
        Select select4 = new Select(driver.findElement(By.id("id_state")));
        select4.selectByVisibleText("Massachusetts");

        //We add ZIP code
        driver.findElement(By.id("postcode")).sendKeys("01775");

        //We add home phone
        driver.findElement(By.id("phone")).sendKeys("0305467845");

        //We add mobile phone
        driver.findElement(By.id("phone_mobile")).sendKeys("0305467845");

        //We add additional information
        driver.findElement(By.id("other")).sendKeys("Test by Nemanja");

        //We clear the address tittle
        driver.findElement(By.name("alias")).clear();

        //We add tittle for my home address
        driver.findElement(By.name("alias")).sendKeys("Moja Adresa");

        //Save the information button
        driver.findElement(By.cssSelector("#submitAddress")).click();





        //We get the First name
        WebElement addressNameMessageElement = driver.findElement(By.className("address_name"));
        String addressMessage = addressNameMessageElement.getText();
        System.out.print("Name: " + addressMessage + " ");

        //We get the Last name
        WebElement addressLastMessageElement = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/div/div/ul/li[2]/span[2]"));
        String addressLastMessage = addressLastMessageElement.getText();
        System.out.println("Last: " + addressLastMessage);

        //We get the Company name
        WebElement addressCompanyMessageElement = driver.findElement(By.className("address_company"));
        String addressCompanyMessage = addressCompanyMessageElement.getText();
        System.out.println("Company: " + addressCompanyMessage);

        //We get the Address
        WebElement address1MessageElement = driver.findElement(By.className("address_address1"));
        String address1Message = address1MessageElement.getText();
        System.out.println("Address: " + address1Message + " ");

        //We get the City
        WebElement CityMessageElement = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/div/div/ul/li[5]/span[1]"));
        String CityMessage = CityMessageElement.getText();
        System.out.print("City: " + CityMessage + " ");

        //We get the State
        WebElement StateMessageElement = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/div/div/ul/li[5]/span[2]"));
        String StateMessage = StateMessageElement.getText();
        System.out.print("State: " + StateMessage + " ");

        //We get the ZIP code
        WebElement ZIPMessageElement = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/div/div/ul/li[5]/span[3]"));
        String ZIPMessage = ZIPMessageElement.getText();
        System.out.println("ZIP: " + ZIPMessage + " ");

        //We get the Country
        WebElement CountryMessageElement = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/div/div/ul/li[6]/span"));
        String CountryMessage = CountryMessageElement.getText();
        System.out.println("Country: " + CountryMessage + " ");

        //We get the Number
        WebElement NumberMessageElement = driver.findElement(By.className("address_phone"));
        String NumberMessage = NumberMessageElement.getText();
        System.out.println("Number: " + NumberMessage + " ");

        //We go back to account page
        driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/a/span")).click();

        //We go to My account page
        driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/div/ul/li[4]/a/i")).click();

        //We get the email address used for the registration
        WebElement emailMessageElement = driver.findElement(By.xpath("//*[@id=\"email\"]"));
        String emailMessage = emailMessageElement.getAttribute("value");
        System.out.println("Email: " + emailMessage);

        Thread.sleep(3000);
    }

        @Test
        public void souceDemoLogin () {

            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.cssSelector("#login-button")).click();

            Assert.assertEquals(driver.findElement(By.cssSelector(".product_label")).getText(), "Products");
            Assert.assertEquals(driver.getTitle(), "Swag Labs");
            Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/inventory.html");

        }


        @Test
        public void buyProduct () throws InterruptedException {

            String item1 = "Sauce Labs Bolt T-Shirt";
            String item2 = "Sauce Labs Bike Light";

            driver.get("https://www.saucedemo.com/v1/");
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.cssSelector("#login-button")).click();

            //       driver.findElements(By.cssSelector("btn_primary.btn_inventory")).get(2).click();

            driver.findElement(By.xpath("//div[text()='" + item1 + "']/../../..//div[3]/button")).click();
            driver.findElement(By.xpath("//div[text()='" + item2 + "']/../../..//div[3]/button")).click();


            String item1Price = driver.findElement(By.xpath("//div[text()='" + item1 + "']/../../..//div[3]/div")).getText();
            String item2Price = driver.findElement(By.xpath("//div[text()='" + item2 + "']/../../..//div[3]/div")).getText();
            String item1Name = driver.findElement(By.xpath("//div[text()='" + item1 + "']")).getText();
            String item2Name = driver.findElement(By.xpath("//div[text()='" + item2 + "']")).getText();
            String item1Description = driver.findElement(By.xpath("//div[text()='"+item1+"']/../../div")).getText();
            String item2Description = driver.findElement(By.xpath("//div[text()='"+item2+"']/../../div")).getText();

            driver.findElement(By.cssSelector(".svg-inline--fa.fa-shopping-cart.fa-w-18.fa-3x")).click();

            String item1CartPrice = driver.findElement(By.xpath("//div[text()='" + item1 + "']/../..//div[@class='inventory_item_price']")).getText();
            String item2CartPrice = driver.findElement(By.xpath("//div[text()='" + item2 + "']/../..//div[@class='inventory_item_price']")).getText();
            String item1CartName = driver.findElement(By.xpath("//div[text()='" + item1 + "']")).getText();
            String item2CartName = driver.findElement(By.xpath("//div[text()='" + item2 + "']")).getText();
            String item1DescriptionCart = driver.findElement(By.xpath("//div[text()='"+item1+"']/../../div")).getText();
            String item2DescriptionCart = driver.findElement(By.xpath("//div[text()='"+item2+"']/../../div")).getText();

            Assert.assertEquals(item1Price, "$" + item1CartPrice);
            Assert.assertEquals(item2Price, "$" + item2CartPrice);
            Assert.assertEquals(item1Name, item1CartName);
            Assert.assertEquals(item2Name, item2CartName);
            Assert.assertEquals(item1Description,item1DescriptionCart);
            Assert.assertEquals(item2Description, item2DescriptionCart);


            Assert.assertEquals(driver.findElement(By.xpath("//div[@class='cart_list']/div[3]//div[@class = 'inventory_item_name']")).getText(), item1);
            Assert.assertEquals(driver.findElement(By.xpath("//div[@class='cart_list']/div[4]//div[@class = 'inventory_item_name']")).getText(), item2);

            driver.findElement(By.cssSelector(".btn_action.checkout_button")).click();

            driver.findElement(By.id("first-name")).sendKeys("Jessie");
            driver.findElement(By.id("last-name")).sendKeys("James");
            driver.findElement(By.id("postal-code")).sendKeys("36200");

            driver.findElement(By.cssSelector(".btn_primary.cart_button")).click();

            String item1CartPriceCheckOut = driver.findElement(By.xpath("//div[text()='" + item1 + "']/../..//div[@class='inventory_item_price']")).getText();
            String item2CartPriceCheckOut = driver.findElement(By.xpath("//div[text()='" + item2 + "']/../..//div[@class='inventory_item_price']")).getText();
            String item1CartNameCheckOut = driver.findElement(By.xpath("//div[text()='" + item1 + "']")).getText();
            String item2CartNameCheckOut = driver.findElement(By.xpath("//div[text()='" + item2 + "']")).getText();
            String item1DescriptionCheckOut = driver.findElement(By.xpath("//div[text()='"+item1+"']/../../div")).getText();
            String item2DescriptionCheckOut = driver.findElement(By.xpath("//div[text()='"+item2+"']/../../div")).getText();

            Assert.assertEquals(item1Price, item1CartPriceCheckOut);
            Assert.assertEquals(item2Price, item2CartPriceCheckOut);
            Assert.assertEquals(item1Name, item1CartNameCheckOut);
            Assert.assertEquals(item2Name, item2CartNameCheckOut);
            Assert.assertEquals(item1Description, item1DescriptionCheckOut);
            Assert.assertEquals(item2Description,item2DescriptionCheckOut);


            String itemTotalPrice = driver.findElement(By.cssSelector(".summary_subtotal_label")).getText().replace("Item total: $", "");
            double itemTotalPriceCalc = Double.parseDouble(item1Price.replace("$", "")) + Double.parseDouble(item2Price.replace("$", ""));

            Assert.assertEquals(Double.parseDouble(itemTotalPrice), itemTotalPriceCalc);

            driver.findElement(By.cssSelector(".btn_action.cart_button")).click();


            Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/checkout-complete.html");

            Thread.sleep(5000);
        }


        @Test
        public void hoverExample () {
            driver.get("https://www.tehnomanija.rs/");

//          driver.findElement(By.cssSelector(".ammenu-button.-hamburger.-trigger")).click();
//          driver.findElement(By.xpath("//a[contains(@href,'it-gaming') and @class=\"ammenu-link\"]")).click();

            Actions actions = new Actions(driver);

            actions.moveToElement(driver.findElement(By.xpath("//a[contains(@href, 'it-gaming')]")))
                    .moveToElement(driver.findElement(By.xpath("//a[contains(@href, 'it-gaming/konzole-i-gaming')]")))
                    .click(driver.findElement(By.xpath("//a[contains(@href, 'it-gaming/konzole-i-gaming')]")))
                    .build().perform();
        }


        @Test
        public void slideExample () {
            driver.get("https://jqueryui.com/slider/");

            driver.switchTo().frame(driver.findElement(By.cssSelector("[class=\"demo-frame\"]")));

            Actions actions = new Actions(driver);
            actions
                    .dragAndDropBy(driver.findElement(By.cssSelector(".ui-slider-handle.ui-corner-all.ui-state-default")), 50, 0)
                    .build()
                    .perform();
//            driver.switchTo().parentFrame(); // vraca nas jedan frame gore
            driver.switchTo().defaultContent(); // vraca nas diretno iz frame
        }


        @Test
        public void dragAndDropExample () {
            driver.get("https://demo.guru99.com/test/drag_drop.html");

            Actions actions2 = new Actions(driver);
            actions2.dragAndDrop(driver.findElement(By.xpath("//a[contains(text(),'BANK')]")), driver.findElement(By.id("bank")))
                    .dragAndDrop(driver.findElement(By.id("fourth")), driver.findElement(By.id("amt7")))
                    .dragAndDrop(driver.findElement(By.id("credit1")), driver.findElement(By.id("loan")))
                    .dragAndDrop(driver.findElement(By.id("fourth")), driver.findElement(By.id("amt8")))
                    .pause(5000)
                    .build()
                    .perform();

            String score = driver.findElement(By.cssSelector(".table4_result")).getText();

            Assert.assertEquals(score, "Perfect!");

        }

    }









