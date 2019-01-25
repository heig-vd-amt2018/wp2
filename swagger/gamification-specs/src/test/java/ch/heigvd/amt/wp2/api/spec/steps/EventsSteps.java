package ch.heigvd.amt.wp2.api.spec.steps;

import ch.heigvd.amt.wp2.ApiException;
import ch.heigvd.amt.wp2.ApiResponse;
import ch.heigvd.amt.wp2.api.EventsApi;
import ch.heigvd.amt.wp2.api.dto.Event;
import ch.heigvd.amt.wp2.api.spec.helpers.Environment;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.joda.time.DateTime;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EventsSteps {

    private Environment environment;
    private EventsApi eventsApi;
    private Event event;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;

    private String apiKey = null;

    public EventsSteps(Environment environment) {
        this.environment = environment;
        this.eventsApi = environment.getEventsApi();
    }

    @Given("^there is a events server$")
    public void there_is_a_events_server() throws Throwable {
        assertNotNull(eventsApi);
    }

    @Given("^I have authorization for Event$")
    public void i_have_authorization_for_Event() throws Throwable {
        apiKey = "test";
    }

    @Given("^I don't have authorization for Event$")
    public void i_don_t_have_authorization_for_Event() throws Throwable {
        apiKey = "";
    }

    @Given("^I have a badge payload with the eventType \"([^\"]*)\", timestamp \"([^\"]*)\" and username \"([^\"]*)\"$")
    public void i_have_a_badge_payload_with_the_eventType_timestamp_and_username(String arg1, String arg2, String arg3) throws Throwable {

        event = new Event();
        event.setEventType(arg1);
        event.setTimestamp(DateTime.now());
        event.setUsername(arg3);
    }

    @When("^I POST it to the /events endpoint$")
    public void i_POST_it_to_the_events_endpoint() throws Throwable {
        try {
            lastApiResponse = eventsApi.postEventWithHttpInfo(apiKey,event);
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }
    }

    @Then("^I receive a (\\d+) status code for event$")
    public void i_receive_a_status_code_for_event(int arg1) throws Throwable {
        assertEquals(arg1, lastStatusCode);
    }
}