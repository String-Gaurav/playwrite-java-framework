package hooks;

import base.TestContext;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.cucumber.java.*;
import utils.ConfigReader;
import utils.ExtentManager;

import java.util.Base64;

public class Hooks {

    private final TestContext testContext;

    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public Hooks(TestContext testContext) {
        this.testContext = testContext;
    }

    @Before
    public void setUp(Scenario scenario) {
        ConfigReader.initProperties();
        String browser = ConfigReader.get("browser");
        String apiBaseUrl = ConfigReader.get("apiBaseUrl");

        // Reporting setup
        ExtentTest extentTest = extent.createTest(scenario.getName());
        test.set(extentTest);

        if (scenario.getSourceTagNames().contains("@ui")) {
            testContext.initUI(browser);
            test.get().info("🧭 UI Browser initialized: " + browser);
        }

        if (scenario.getSourceTagNames().contains("@api")) {
            testContext.initAPI(apiBaseUrl);
            test.get().info("🔗 API Context initialized for: " + apiBaseUrl);
        }

        test.get().info("🚀 Test setup complete.");
    }

    @AfterStep
    public void afterEachStep(Scenario scenario) {
        // If it's a UI test, capture screenshot at each step
        if (scenario.getSourceTagNames().contains("@ui") && testContext.getPage() != null) {
            try {
                byte[] screenshot = testContext.getPage().screenshot();
                String base64Screenshot = Base64.getEncoder().encodeToString(screenshot);

                test.get().addScreenCaptureFromBase64String(base64Screenshot, "Step Screenshot");

                if (scenario.isFailed()) {
                    test.get().log(Status.FAIL, "❌ Step failed: " + scenario.getStatus());
                } else {
                    test.get().log(Status.PASS, "✅ Step passed: " + scenario.getStatus());
                }

            } catch (Exception e) {
                test.get().warning("⚠️ Failed to capture step screenshot: " + e.getMessage());
            }
        } else {
            // For API or non-UI steps, log step result only
            if (scenario.isFailed()) {
                test.get().log(Status.FAIL, "❌ Step failed: " + scenario.getStatus());
            } else {
                test.get().log(Status.PASS, "✅ Step passed: " + scenario.getStatus());
            }
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        // Final failure screenshot (for UI)
        if (scenario.isFailed() && testContext.getPage() != null) {
            try {
                byte[] screenshot = testContext.getPage().screenshot();
                scenario.attach(screenshot, "image/png", "Failure Screenshot");
                test.get().addScreenCaptureFromBase64String(
                        Base64.getEncoder().encodeToString(screenshot),
                        "Final Failure Screenshot"
                );
                test.get().fail("📸 Final screenshot attached.");
            } catch (Exception e) {
                test.get().warning("⚠️ Failed to capture final screenshot: " + e.getMessage());
            }
        }

        if (scenario.isFailed()) {
            test.get().fail("❌ Scenario Failed");
        } else {
            test.get().pass("✅ Scenario Passed");
        }

        test.get().info("🧹 Test teardown complete.");
        testContext.tearDown();
        extent.flush();
    }
}