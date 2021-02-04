package stepdefinitions;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class PersonStepdefs {
    private WebDriver webDriver;
    private static final String pathToChromeDriver = "/home/oscar/Oscar/Software/chromedriver";

    @Before // Cuidado con esta anotación, está en el paquete cucumber, no junit
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", pathToChromeDriver);
        webDriver = new ChromeDriver();
        // Wait before getting WebElements
        webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    @After // Cuidado con esta anotación, está en el paquete cucumber, no junit
    public void tearDown() {
        webDriver.quit();
    }

    @Given("^I am on the main page$")
    public void i_am_on_the_main_page() throws Throwable {
        webDriver.navigate().to("http://localhost:8080");
    }

    @Given("^No user with nif \"([^\"]*)\" exists$")
    public void no_user_with_nif_exists(String nif) throws Throwable {
        try {
            webDriver.findElement(By.id(nif));
            fail("There is already a Person with nif: " + nif);
        } catch (NoSuchElementException e) {
        }
    }

    @When("^I provide \"([^\"]*)\" for the name$")
    public void i_provide_for_the_name(String name) throws Throwable {
        webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        webDriver.findElement(By.xpath("//input[@ng-model='name']")).sendKeys(name);
        Thread.sleep(1000); // Eliminar, es para que se vea cómo se van escribiendo los datos.
    }

    @And("^I provide \"([^\"]*)\" for the surname$")
    public void i_provide_for_the_surname(String surname) throws Throwable {
        webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        webDriver.findElement(By.xpath("//input[@ng-model='surname']")).sendKeys(surname);
        Thread.sleep(1000); // Eliminar, es para que se vea cómo se van escribiendo los datos.
    }

    @And("^I provide \"([^\"]*)\" for the nif$")
    public void i_provide_for_the_nif(Integer nif) throws Throwable {
        webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        webDriver.findElement(By.xpath("//input[@ng-model='nif']")).sendKeys(""+nif);
        Thread.sleep(1000); // Eliminar, es para que se vea cómo se van escribiendo los datos.
    }

    @And("^I click the New button$")
    public void i_click_the_New_button() throws Throwable {
        webDriver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    @Then("^The person with nif \"([^\"]*)\" is created in the Agenda$")
    public void the_person_with_nif_is_created_in_the_Agenda(String nif) throws Throwable {
        Thread.sleep(1000); // Eliminar, es para que se vea cómo se van escribiendo los datos.
        assertNotNull(webDriver.findElement(By.id(nif)));
    }
}
