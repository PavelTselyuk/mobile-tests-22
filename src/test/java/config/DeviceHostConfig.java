package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config.properties"
})
public interface DeviceHostConfig extends Config {

    @Key("deviceHost")
    @DefaultValue("emulation")
    String deviceHost();
}
