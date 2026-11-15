package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:real.properties"
})
public interface RealDeviceConfig extends Config {

    @Key("platformVersion")
    String platformVersion();

    @Key("deviceName")
    String deviceName();

    @Key("appiumServerUrl")
    String appiumServerUrl();

    @Key("appPackage")
    String appPackage();

    @Key("appActivity")
    String appActivity();
}
