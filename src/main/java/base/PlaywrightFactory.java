package base;

import com.microsoft.playwright.*;

public class PlaywrightFactory {
    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;

    public PlaywrightFactory(){
        playwright = Playwright.create();
    }

    public Page initBrowser(String browserName) {
        switch (browserName.toLowerCase()) {
            case "chrome":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            case "firefox":
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            case "webkit":
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            default:
                throw new RuntimeException("Unsupported browser: " + browserName);
        }

        context = browser.newContext();
        page = context.newPage();
        return page;
    }

    public Page getPage() {
        return this.page;
    }

    public BrowserContext getContext() {
        return this.context;
    }

    public Playwright getPlaywright() {
        return playwright;
    }

    public void close() {
        if (playwright != null) {
            playwright.close();
        }
    }
}
