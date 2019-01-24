package ch.heigvd.amt.wp2.api.spec.steps;

import ch.heigvd.amt.wp2.ApiException;
import ch.heigvd.amt.wp2.ApiResponse;
import ch.heigvd.amt.wp2.api.BadgesApi;
import ch.heigvd.amt.wp2.api.dto.Badge;
import ch.heigvd.amt.wp2.api.dto.PointScale;
import ch.heigvd.amt.wp2.api.spec.helpers.Environment;
import com.squareup.okhttp.*;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BadgesSteps {

    private Environment environment;
    private BadgesApi badgesApi;
    private Badge badge;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;

    private String apiKey = null;

    private String listScalePoint = null;

    private String badgejson = null;
    private String badgejsonPatched = null;


    public BadgesSteps(Environment environment) {
        this.environment = environment;
        this.badgesApi = environment.getBadgeApi();
    }

    @Given("^there is a Badges server$")
    public void there_is_a_Badges_server() throws Throwable {

    }

    @Given("^I have a badge payload with the name \"([^\"]*)\", description \"([^\"]*)\" and image \"([^\"]*)\"$")
    public void i_have_a_badge_payload_with_the_name_description_and_image(String arg1, String arg2, String arg3) throws Throwable {

    }

    @When("^I POST it to the /badges endpoint$")
    public void i_POST_it_to_the_badges_endpoint() throws Throwable {

    }



    @When("^I ask for a list of badges with a GET on the /badges endpoint$")
    public void i_ask_for_a_list_of_badges_with_a_GET_on_the_badges_endpoint() throws Throwable {

    }

    @Then("^I receive the badges$")
    public void i_receive_the_badges() throws Throwable {

    }

    @When("^I ask for the badge with a GET on the /badges/\"([^\"]*)\" endpoint$")
    public void i_ask_for_the_badge_with_a_GET_on_the_badges_endpoint(String arg1) throws Throwable {

    }

    @Then("^I receive the badge$")
    public void i_receive_the_badge() throws Throwable {

    }

    @When("^I have a badge payload with the name \"([^\"]*)\" and description \"([^\"]*)\"$")
    public void i_have_a_badge_payload_with_the_name_and_description(String arg1, String arg2) throws Throwable {

    }

    @When("^I ask for the badge with a PATCH on the /badges/\"([^\"]*)\" endpoint$")
    public void i_ask_for_the_badge_with_a_PATCH_on_the_badges_endpoint(String arg1) throws Throwable {

    }

    @Then("^I see if the badge has been patched$")
    public void i_see_if_the_badge_has_been_patched() throws Throwable {

    }
}