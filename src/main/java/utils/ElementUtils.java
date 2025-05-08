package utils;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;

public class ElementUtils {
    private final Page page;

    public ElementUtils(Page page) {
        this.page = page;
    }

    /**
     * Clicks on the given locator after ensuring it's visible and enabled.
     */
    public void click(Locator locator) {
        try {
            locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            if (locator.isEnabled()) {
                locator.click();
            } else {
                System.out.println("Element is not enabled.");
            }
        } catch (Exception e) {
            System.out.println("Click failed | Reason: " + e.getMessage());
        }
    }

    /**
     * Clears the input field and enters the given text.
     */
    public void enterWithClear(Locator locator, String text) {
        try {
            locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            locator.fill("");  // Clear existing content
            locator.type(text); // Enter new text
        } catch (Exception e) {
            System.out.println("Enter with clear failed | Reason: " + e.getMessage());
        }
    }

    /**
     * Enters the text in the field without clearing existing content.
     */
    public void enterWithoutClear(Locator locator, String text) {
        try {
            locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            locator.type(text);
        } catch (Exception e) {
            System.out.println("Enter without clear failed | Reason: " + e.getMessage());
        }
    }

    /**
     * Scrolls to the given element.
     */
    public void scrollToElement(Locator locator) {
        try {
            locator.scrollIntoViewIfNeeded();
        } catch (Exception e) {
            System.out.println("Scroll failed | Reason: " + e.getMessage());
        }
    }

    /**
     * Performs a double-click on the element.
     */
    public void doubleClick(Locator locator) {
        try {
            locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            locator.dblclick();
        } catch (Exception e) {
            System.out.println("Double click failed | Reason: " + e.getMessage());
        }
    }

    /**
     * Waits for the element to become visible within the specified timeout.
     */
    public void waitForElement(Locator locator, int timeoutMs) {
        try {
            locator.waitFor(new Locator.WaitForOptions()
                    .setTimeout((double) timeoutMs)
                    .setState(WaitForSelectorState.VISIBLE));
        } catch (Exception e) {
            System.out.println("Element not visible within timeout | Reason: " + e.getMessage());
        }
    }

    /**
     * Checks if the element is visible on the page.
     */
    public boolean isElementVisible(Locator locator) {
        try {
            return locator.isVisible();
        } catch (Exception e) {
            System.out.println("Visibility check failed | Reason: " + e.getMessage());
            return false;
        }
    }

    /**
     * Checks if the element is enabled (interactable).
     */
    public boolean isElementEnabled(Locator locator) {
        try {
            return locator.isEnabled();
        } catch (Exception e) {
            System.out.println("Enablement check failed | Reason: " + e.getMessage());
            return false;
        }
    }

    /**
     * Checks if the element exists in the DOM.
     */
    public boolean isElementFound(Locator locator) {
        try {
            return locator.count() > 0;
        } catch (Exception e) {
            System.out.println("Element presence check failed | Reason: " + e.getMessage());
            return false;
        }
    }

    /**
     * Checks if the input field's value matches the expected text.
     */
    public boolean isInputValueEqual(Locator locator, String expectedValue) {
        try {
            locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            String actualValue = locator.inputValue().trim();
            return actualValue.equals(expectedValue);
        } catch (Exception e) {
            System.out.println("Input value check failed | Reason: " + e.getMessage());
            return false;
        }
    }

    /**
     * Checks if the text matches the expected text.
     */
    public boolean isTextEqual(Locator locator, String expectedValue) {
        try {
            locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            String actualValue = getText(locator).trim();
            return actualValue.equals(expectedValue);
        } catch (Exception e) {
            System.out.println("Input value check failed | Reason: " + e.getMessage());
            return false;
        }
    }

    /**
     * Retrieves the visible text content of a given locator.
     *
     * @param locator The Playwright Locator representing the element
     * @return The trimmed visible text of the element, or null if not found or error occurs
     */
    public String getText(Locator locator) {
        try {
            // Wait until the element is visible on the page
            locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

            // Get the text content and trim any leading/trailing spaces
            return locator.textContent().trim();

        } catch (Exception e) {
            System.out.println("Failed to retrieve text | Reason: " + e.getMessage());
            return null;
        }
    }
}
