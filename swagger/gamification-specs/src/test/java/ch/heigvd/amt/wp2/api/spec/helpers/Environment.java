package ch.heigvd.amt.wp2.api.spec.helpers;


import ch.heigvd.amt.wp2.api.BadgesApi;
import ch.heigvd.amt.wp2.api.PointScalesApi;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Olivier Liechti on 24/06/17.
 */
public class Environment {

    private BadgesApi badgesApi = new BadgesApi();
    private PointScalesApi pointScalesApi = new PointScalesApi();

    public Environment() throws IOException {
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("environment.properties"));
        String url = properties.getProperty("ch.heigvd.amt.wp2.url");

        badgesApi.getApiClient().setBasePath(url);
        pointScalesApi.getApiClient().setBasePath(url);
    }

    public BadgesApi getBadgeApi() {
        return badgesApi;
    }

    public PointScalesApi getPointScalesApi() {
        return pointScalesApi;
    }

}
