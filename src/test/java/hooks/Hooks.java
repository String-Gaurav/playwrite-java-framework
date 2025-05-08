package hooks;

import com.microsoft.playwright.*;
import io.cucumber.java.*;

public class Hooks {
    public static Playwright playwright;
    public static Browser browser;
    public BrowserContext context;
    public Page page;

    @BeforeAll
    public static void setupClass() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @Before
    public void setUp() {
        context = browser.newContext();
        page = context.newPage();
    }

    @After
    public void tearDown() {
        context.close();
    }

    @AfterAll
    public static void tearDownClass() {
        browser.close();
        playwright.close();
    }
}
