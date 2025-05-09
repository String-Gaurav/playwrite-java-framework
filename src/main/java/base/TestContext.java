package base;

import base.PlaywrightFactory;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Page;

public class TestContext {

    private PlaywrightFactory browserFactory;
    public Page page;
    public APIRequestContext apiContext;
    public APIRequestManager apiManager;

    public void initUI(String browserName) {
        browserFactory = new PlaywrightFactory();
        page = browserFactory.initBrowser(browserName);
    }

    public void initAPI(String baseUrl) {
        if (browserFactory == null) {
            browserFactory = new PlaywrightFactory(); // needed for API context
        }
        apiManager = new APIRequestManager(browserFactory.getPlaywright(), baseUrl);
        apiContext = apiManager.getContext();
    }

    public void tearDown() {
        if (browserFactory != null) {
            browserFactory.close();
        }
    }

    public Page getPage() {
        return page;
    }

    public APIRequestContext getApiContext() {
        return apiContext;
    }
}