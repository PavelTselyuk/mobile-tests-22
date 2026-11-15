package tests.crossPlatform;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static io.qameta.allure.Allure.step;

public class WelcomePagesTest extends TestBase {

    @Test
    void successfulPassWelcomeScreens() {
        step("Check 1st welcome page appeared and click next", () -> {
            $x("//android.widget.TextView[@text=\"All the world's knowledge\"]").shouldBe(visible);
            $x("//android.widget.Button").click();
        });

        step("Check 2nd welcome page appeared and click next", () -> {
            $x("//android.widget.ImageView[@content-desc=\"Data & Privacy\"]").shouldBe(visible);
            $x("//android.view.View[@content-desc=\"Forward\"]").click();
        });

        step("Check 3rd welcome page appeared and click next", () -> {
            $x("//android.widget.ImageView[@content-desc=\"Read in more than 300 languages\"]").shouldBe(visible);
            $x("//android.widget.Button").click();
        });

        step("Check 4th welcome page appeared", () -> {
            $x("//android.widget.ImageView[@content-desc=\"Follow your curiosity\"]").shouldBe(visible);
        });
    }
}
