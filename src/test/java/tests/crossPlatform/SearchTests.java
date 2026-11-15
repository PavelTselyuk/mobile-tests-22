package tests.crossPlatform;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;
import static io.qameta.allure.Allure.step;

public class SearchTests extends TestBase {

    @Test
    void successfulSearchTest() {
        back();
        step("Type search", () -> {
            $(id("org.wikipedia.alpha:id/nav_tab_search")).click();
            $x("//android.view.View[@content-desc=\"Close\"]").click();
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Appium");
        });
        step("Verify content found", () ->
                $$x("//android.widget.TextView[contains(@text, 'Appium')]")
                        .shouldHave(sizeGreaterThan(0)));
    }
}