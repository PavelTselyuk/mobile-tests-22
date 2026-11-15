package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.BrowserstackConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.aeonbits.owner.ConfigFactory;
import org.jspecify.annotations.NonNull;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static io.appium.java_client.remote.MobilePlatform.ANDROID;

public class BrowserstackDriver implements WebDriverProvider {

    private final BrowserstackConfig config = ConfigFactory.create(BrowserstackConfig.class, System.getProperties());

    @Override
    public WebDriver createDriver(@NonNull Capabilities capabilities) {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName(ANDROID);
        options.setDeviceName(config.getDevice());
        options.setPlatformVersion(config.getOsVersion());
        options.setApp(config.getApp());

        Map<String, Object> bstackOptions = new HashMap<>();
        bstackOptions.put("userName", config.getUser());
        bstackOptions.put("accessKey", config.getKey());
        bstackOptions.put("projectName", config.getProject());
        bstackOptions.put("buildName", config.getBuild());
        bstackOptions.put("sessionName", config.getName());
        options.setCapability("bstack:options", bstackOptions);

        try {
            return new AndroidDriver(new URL(config.getHubUrl()), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
