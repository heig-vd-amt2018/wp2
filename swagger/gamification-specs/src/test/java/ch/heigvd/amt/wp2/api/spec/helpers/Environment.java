package ch.heigvd.amt.wp2.api.spec.helpers;


import ch.heigvd.amt.wp2.api.*;
import ch.heigvd.amt.wp2.api.ApplicationsApi;
import ch.heigvd.amt.wp2.api.BadgesApi;
import ch.heigvd.amt.wp2.api.EventsApi;
import ch.heigvd.amt.wp2.api.PointScalesApi;
import ch.heigvd.amt.wp2.api.RulesApi;
import ch.heigvd.amt.wp2.api.PlayersApi;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Olivier Liechti on 24/06/17.
 */
public class Environment {

    private BadgesApi badgesApi = new BadgesApi();
    private PointScalesApi pointScalesApi = new PointScalesApi();
    private EventsApi eventsApi = new EventsApi();
    private RulesApi rulesApi = new RulesApi();
    private PlayersApi playersApi = new PlayersApi();
    private ApplicationsApi applicationsApi = new ApplicationsApi();

    public Environment() throws IOException {
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("environment.properties"));
        String url = properties.getProperty("ch.heigvd.amt.wp2.url");

        badgesApi.getApiClient().setBasePath(url);
        pointScalesApi.getApiClient().setBasePath(url);
        eventsApi.getApiClient().setBasePath(url);
        rulesApi.getApiClient().setBasePath(url);
        playersApi.getApiClient().setBasePath(url);
        applicationsApi.getApiClient().setBasePath(url);
    }

    public BadgesApi getBadgeApi() {
        return badgesApi;
    }
    public PointScalesApi getPointScalesApi() {
        return pointScalesApi;
    }
    public EventsApi getEventsApi() {
        return eventsApi;
    }
    public RulesApi getRulesApi() {
        return rulesApi;
    }
    public PlayersApi getPlayersApi() {
        return playersApi;
    }
    public ApplicationsApi getApplicationsApi() {
        return applicationsApi;
    }

}
