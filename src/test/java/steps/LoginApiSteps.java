package steps;

import base.TestContext;
import com.microsoft.playwright.APIResponse;
import io.cucumber.java.en.*;
import pages.api.LoginApiPage;

import static org.junit.jupiter.api.Assertions.*;
import static utils.Helper.getStringResponse;

public class LoginApiSteps {

    private final TestContext context;
    private LoginApiPage loginApiPage;
    private APIResponse response;

    public LoginApiSteps(TestContext context) {
        this.context = context;
    }

    @When("I call the login API with username {string} and password {string}")
    public void callLoginApi(String username, String password) {
        loginApiPage = new LoginApiPage(context.getApiContext());
        loginApiPage.login(username, password);
    }

    @Then("the response status should be {int}")
    public void validateStatusCode(int expectedStatus) {
        loginApiPage.validateStatusCode(expectedStatus);
    }

    @Then("the response should access token")
    public void validateResponseBody() {
        loginApiPage.fetchAccessToken();
    }
}
