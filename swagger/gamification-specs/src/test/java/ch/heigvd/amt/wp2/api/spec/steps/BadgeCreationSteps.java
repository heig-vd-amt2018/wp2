package ch.heigvd.amt.wp2.api.spec.steps;

import ch.heigvd.amt.wp2.ApiException;
import ch.heigvd.amt.wp2.ApiResponse;
import ch.heigvd.amt.wp2.api.BadgesApi;
import ch.heigvd.amt.wp2.api.spec.helpers.Environment;
import ch.heigvd.amt.wp2.api.dto.Badge;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BadgeCreationSteps {

  private Environment environment;
  private BadgesApi api;
  private Badge badge;

  private ApiResponse lastApiResponse;
  private ApiException lastApiException;
  private boolean lastApiCallThrewException;
  private int lastStatusCode;

    public BadgeCreationSteps(Environment environment) {
        this.environment = environment;
        this.api = environment.getBadgeApi();
    }

    @Given("^there is a Badges server$")
    public void there_is_a_badges_server() throws Throwable {
        assertNotNull(api);
    }

    @Given("^I have a badge payload$")
    public void i_have_a_badge_payload() throws Throwable {
        badge = new Badge();
        badge.setDescription("BADGE DESCRIPTION");
        badge.setImage(null);
        badge.setName("NAME");
    }

    @When("^I POST it to the /bagdes endpoint$")
    public void i_POST_it_to_the_badges_endpoint() throws Throwable {
        try {
            lastApiResponse = api.createBadgeWithHttpInfo(badge);
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }
        // result verified in @Then
    }

    @Then("^I receive a (\\d+) status code$")
    public void i_receive_a_status_code(int arg1) throws Throwable {
        assertEquals(arg1, lastStatusCode);
    }
}
