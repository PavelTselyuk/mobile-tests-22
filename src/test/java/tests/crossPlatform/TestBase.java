package tests.crossPlatform;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.appium.SelenideAppium;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.DeviceHostConfig;
import drivers.BrowserstackDriver;
import drivers.LocalDriver;
import drivers.RealDriver;
import helpers.Attach;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class TestBase {

    private static final DeviceHostConfig config =
            ConfigFactory.create(DeviceHostConfig.class, System.getProperties());

    @BeforeAll
    static void beforeAll() {
        String host = config.deviceHost();
        switch (host) {
            case "browserstack" -> Configuration.browser = BrowserstackDriver.class.getName();
            case "emulation" -> Configuration.browser = LocalDriver.class.getName();
            case "real" -> Configuration.browser = RealDriver.class.getName();
            case null, default -> throw new IllegalArgumentException(
                    "Unknown deviceHost: " + host
                            + ". Expected: browserstack | emulation | real");
        }
        Configuration.browserSize = null;
        Configuration.timeout = 30000;
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        if (isLocalHost()) {
            SelenideAppium.launchApp();
        } else {
            open();
        }
    }

    @AfterEach
    void addAttachments() {
        if (isLocalHost()) {
            Attach.screenshotAs("Last screenshot");
            Attach.pageSource();
            closeWebDriver();
        } else {
            String sessionId = ((AndroidDriver) getWebDriver()).getSessionId().toString();
            Attach.pageSource();
            closeWebDriver();
            Attach.addVideo(sessionId);
        }
    }

    private boolean isLocalHost() {
        String host = config.deviceHost();
        return "emulation".equals(host) || "real".equals(host);
    }
}
