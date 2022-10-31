package steps;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.junit.Assert;

import java.util.List;


public class StepsDefinitions {

    private WebDriver driver;
    private final By addUserButton = By.cssSelector("button[type='add']");
    private final By firstNameInput = By.cssSelector("input[name='FirstName']");
    private final By lastNameInput = By.cssSelector("input[name='LastName']");
    private final By userNameInput = By.cssSelector("input[name='UserName']");
    private final By password = By.cssSelector("input[name='Password']");
    private final By companyAAA = By.xpath("//label[normalize-space()='Company AAA']");
    private final By companyBBB = By.xpath("//label[normalize-space()='Company BBB']");
    private final By role = By.cssSelector("select[name='RoleId']");
    private final By email = By.cssSelector("input[name='Email']");
    private final By cellPhone = By.cssSelector("input[name='Mobilephone']");
    private final By saveButton = By.cssSelector(".btn.btn-success");
    private final String userName = "Nicolas Koriakos";
    private final By users = By.cssSelector(".smart-table-data-row.ng-scope");
    private final By okButton = By.cssSelector(".btn.ng-scope.ng-binding.btn-primary");
    private List<WebElement> userNameList;
    private final By table = By.cssSelector("tbody");

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","src/main/java/resources/chromedriver");
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.driver.navigate().to("https://www.way2automation.com/angularjs-protractor/webtables/");
    }

    @After
    public void tearDown(){
        this.driver.manage().deleteAllCookies();
        this.driver.quit();
    }

    // Methods for Gherkin feature 01

    @Given("Click on add a user")
    public void clickOnAddAUser() {
        WebElement element = this.driver.findElement(addUserButton);
        element.click();
    }

    @And("user enters requested information")
    public void userEntersRequestedInformation() {
        this.driver.findElement(firstNameInput).sendKeys("Nicolas");
        this.driver.findElement(lastNameInput).sendKeys("Koriakos");
        this.driver.findElement(userNameInput).sendKeys(this.userName);
        this.driver.findElement(password).sendKeys("testPassword");
        WebElement companyTypeAAA = this.driver.findElement(companyAAA);
        companyTypeAAA.click();
        WebElement companyTypeBBB = this.driver.findElement(companyBBB);
        companyTypeBBB.click();
        Select dropDownRole = new Select(this.driver.findElement(role));
        dropDownRole.selectByIndex(2);
        this.driver.findElement(email).sendKeys("test@email.test");
        this.driver.findElement(cellPhone).sendKeys("3541-215719");
    }

    @And("Submit Info")
    public void submitInfo() {
        WebElement saveButtonElement = this.driver.findElement(saveButton);
        saveButtonElement.click();
    }

    @Then("new user is created & contains updated database with the new user")
    public void newUserIsCreatedContainsUpdatedDatabaseWithTheNewUser() {
        WebElement tableContent = this.driver.findElement(table);
        Assert.assertTrue(tableContent.getText().contains(this.userName));
    }

    // Methods for Gherkin feature 02
    @Given("Delete user novak")
    public void deleteUserNovak() {
        this.userNameList = this.driver.findElements(users);
    }

    @When("click on cross button & Ok")
    public void clickOnCrossButtonOk() {
        for (int x = 0; x < userNameList.size();x++){
            if (userNameList.get(x).getText().contains("novak")){
                WebElement deleteButton = this.driver.findElement(By.xpath("(//i[@class='icon icon-remove'])[" + (x+1) + "]"));
                deleteButton.click();
                WebElement okButtonElement = this.driver.findElement(okButton);
                okButtonElement.click();
                userNameList = this.driver.findElements(users);
                break;
            }
        }
    }

    @Then("User is deleted & Contains updated database without the deleted user")
    public void userIsDeleted() {
        WebElement tableContent = this.driver.findElement(table);
        Assert.assertFalse(tableContent.getText().contains("novak"));
    }
}
