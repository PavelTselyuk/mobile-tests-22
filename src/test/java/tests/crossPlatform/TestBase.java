package tests.crossPlatform;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.appium.SelenideAppium;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class TestBase {
    @BeforeAll
    static void beforeAll() {
//        if (config.host == BS) {
//            Configuration.browser = LocalDriver.class.getName();
//        } else if (config.host == emulator) {
//            Configuration.browser = BrowserstackDriver.class.getName();
//        } else {
//            Configuration.browser = RealDriver.class.getName();
//        }
        Configuration.browserSize = null;
        Configuration.timeout = 30000;
    }


    @BeforeEach
    void beforeEach() {
        if (config.host == emulator || config.host == real) {
            SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
            SelenideAppium.launchApp();
        } else if (config.host == BS) {
            SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
            open();
        }
    }


    @AfterEach
    void addAttachments() {
        if (config.host == emulator || config.host == real) {
            Attach.screenshotAs("Last screenshot");
            Attach.pageSource();
            closeWebDriver();
        } else if (config.host == BS) {
            String sessionId = Selenide.sessionId().toString();
            System.out.println(sessionId);
            Attach.pageSource();
            closeWebDriver();
            Attach.addVideo(sessionId);
        }
    }




}

