package steps;

import com.microsoft.playwright.Page;
import hooks.Hooks;
import io.cucumber.java.en.*;
import pages.LoginPage;
import static org.junit.jupiter.api.Assertions.*;

public class LoginSteps {
    private final Hooks hooks;
    private LoginPage loginPage;

    public LoginSteps(Hooks hooks) {
        this.hooks = hooks;
    }

    @Given("I open the login page")
    public void openLoginPage() {
        hooks.page.navigate("https://practicetestautomation.com/practice-test-login/");
        loginPage = new LoginPage(hooks.page);
    }

    @When("I enter username {string} and password {string}")
    public void enterCredentials(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("I should see an success message {string}")
    public void verifyError(String expectedMessage) {
        loginPage.validateMsg(expectedMessage);
    }
}
