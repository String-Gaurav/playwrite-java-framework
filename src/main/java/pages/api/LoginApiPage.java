package pages.api;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static utils.Helper.getStringResponse;

public class LoginApiPage {

    private final APIRequestContext apiContext;
    private APIResponse response;

    public LoginApiPage(APIRequestContext apiContext) {
        this.apiContext = apiContext;
    }

    public void login(String username, String password) {
        String requestBody = String.format("{\"username\":\"%s\", \"password\":\"%s\"}", username, password);

        RequestOptions options = RequestOptions.create()
                .setHeader("Content-Type", "application/json")
                .setData(requestBody);

        response = apiContext.post("auth/login", options);
        assertNotNull(response, "API response is null!");
    }

    public void validateStatusCode(int expectedStatus) {
        assertEquals(expectedStatus, response.status(), "Unexpected status code");
    }

    public void fetchAccessToken() {
        String body = response.text();
        String accessToken = getStringResponse(body, "accessToken");
        assertNotNull(accessToken, "Expected access token not found in response.");
    }
}
