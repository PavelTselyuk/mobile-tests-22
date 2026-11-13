package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:testData.properties"
})
public interface BrowserstackConfig extends Config {


    @Key("browserstack.user")
    String getUser();

    @Key("browserstack.key")
    String getKey();

    @Key("browserstack.url")
    String getUrl();
}
