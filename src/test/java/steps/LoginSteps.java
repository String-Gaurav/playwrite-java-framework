package steps;

import base.TestContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.APIRequestContext;
import io.cucumber.java.en.*;
import pages.ui.LoginPage;

public class LoginSteps {

    private final TestContext context;
    private final Page page;
    private LoginPage loginPage;

    public LoginSteps(TestContext context) {
        this.context = context;
        this.page = context.getPage();
    }

    @Given("I open the login page")
    public void openLoginPage() {
        if (page == null) throw new RuntimeException("Page is not initialized. Are you missing the @ui tag?");
        page.navigate("https://practicetestautomation.com/practice-test-login/");
        loginPage = new LoginPage(page);
    }

    @When("I enter username {string} and password {string}")
    public void enterCredentials(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("I should see a success message {string}")
    public void verifySuccess(String expectedMessage) {
        loginPage.validateMsg(expectedMessage);
    }

    // ---------- API Step ----------
//    @When("I call the login API with username {string} and password {string}")
//    public void callLoginApi(String username, String password) {
//        if (api == null) throw new RuntimeException("API Context is not initialized. Are you missing the @api tag?");
//        var response = api.post("/api/login", r -> r
//                .setHeader("Content-Type", "application/json")
//                .setData("{\"username\":\"" + username + "\", \"password\":\"" + password + "\"}")
//        );
//
//        assertEquals(200, response.status());
//        System.out.println("✅ Login API success response: " + response.text());
//    }
}
