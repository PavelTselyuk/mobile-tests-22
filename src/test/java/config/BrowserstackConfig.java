package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:testData.properties",
        "classpath:browserstack.properties"
})
public interface BrowserstackConfig extends Config {

    @Key("browserstack.user")
    String getUser();

    @Key("browserstack.key")
    String getKey();

    @Key("browserstack.url")
    String getUrl();

    @Key("browserstack.hubUrl")
    String getHubUrl();

    @Key("browserstack.app")
    String getApp();

    @Key("browserstack.device")
    String getDevice();

    @Key("browserstack.osVersion")
    String getOsVersion();

    @Key("browserstack.appiumVersion")
    String getAppiumVersion();

    @Key("browserstack.project")
    String getProject();

    @Key("browserstack.build")
    String getBuild();

    @Key("browserstack.name")
    String getName();
}
