package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.BrowserstackConfig;
import org.aeonbits.owner.ConfigFactory;
import org.jspecify.annotations.NonNull;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackDriver implements WebDriverProvider {

    private final BrowserstackConfig config = ConfigFactory.create(BrowserstackConfig.class, System.getProperties());

    @Override
    public WebDriver createDriver(@NonNull Capabilities capabilities) {
        MutableCapabilities caps = new MutableCapabilities(); // todo move to UiAutomator2Options

        caps.setCapability("browserstack.user", config.getUser());
        caps.setCapability("browserstack.key", config.getKey());
        caps.setCapability("app", config.getApp());
        caps.setCapability("device", config.getDevice());
        caps.setCapability("os_version", config.getOsVersion());
        caps.setCapability("project", config.getProject());
        caps.setCapability("build", config.getBuild());
        caps.setCapability("name", config.getName());

        try {
            return new RemoteWebDriver(new URL(config.getHubUrl()), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
