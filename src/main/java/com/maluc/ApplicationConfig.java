package com.maluc;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashMap;
import java.util.Map;

@ApplicationPath("/")
public class ApplicationConfig extends Application {
    @Override
    public Map<String, Object> getProperties() {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("jersey.config.server.provider.packages", "com.maluc");
        properties.put("jersey.config.server.provider.packages", "com.maluc.query");
        properties.put("jersey.config.server.provider.packages", "com.maluc.services");
        properties.put("jersey.config.server.provider.packages", "com.maluc.usecase");
        properties.put("jersey.config.server.provider.packages", "com.maluc.usecase.login");
        properties.put("jersey.config.server.provider.packages", "com.maluc.user");
        return properties;
    }
}
