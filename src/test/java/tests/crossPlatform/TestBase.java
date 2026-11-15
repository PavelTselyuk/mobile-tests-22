package tests.crossPlatform;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.appium.SelenideAppium;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.DeviceHostConfig;
import drivers.BrowserstackDriver;
import drivers.LocalDriver;
import drivers.RealDriver;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class TestBase {

    private static final DeviceHostConfig config =
            ConfigFactory.create(DeviceHostConfig.class, System.getProperties());

    @BeforeAll
    static void beforeAll() {
        String host = config.deviceHost();
        if ("browserstack".equals(host)) {
            Configuration.browser = BrowserstackDriver.class.getName();
        } else if ("emulation".equals(host)) {
            Configuration.browser = LocalDriver.class.getName();
        } else if ("real".equals(host)) {
            Configuration.browser = RealDriver.class.getName();
        } else {
            throw new IllegalArgumentException(
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
            String sessionId = Selenide.sessionId().toString();
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
