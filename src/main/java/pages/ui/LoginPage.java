package pages.ui;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import utils.ElementUtils;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginPage {
    private final Page page;
    private final ElementUtils utils;

    //Locators
    public final Locator usernameInput;
    public final Locator passwordInput;
    public final Locator loginButton;
    public final Locator loginSuccess;

    public LoginPage(Page page) {
        this.page = page;
        usernameInput = page.locator("#username");
        passwordInput = page.locator("#password");
        loginButton = page.locator("#submit");
        loginSuccess = page.locator("xpath=//*[@class='post-title']");
        this.utils = new ElementUtils(page);
    }

    public void login(String username, String password) {
        this.utils.enterWithoutClear(usernameInput, username);
        this.utils.enterWithoutClear(passwordInput, password);

        this.utils.click(loginButton);
    }

    public void validateMsg(String expectedMessage) {
        assertTrue(this.utils.isTextEqual(loginSuccess, expectedMessage), "Expected login success message not found");
    }
}
