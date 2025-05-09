package base;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;

public class APIRequestManager {
    private APIRequestContext apiRequestContext;

    public APIRequestManager(Playwright playwright, String baseURL) {
        this.apiRequestContext = playwright.request().newContext(
                new APIRequest.NewContextOptions()
                        .setBaseURL(baseURL)
                        .setIgnoreHTTPSErrors(true)
        );
    }

    public APIRequestContext getContext() {
        return apiRequestContext;
    }
}