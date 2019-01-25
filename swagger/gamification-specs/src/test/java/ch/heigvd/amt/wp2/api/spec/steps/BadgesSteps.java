package ch.heigvd.amt.wp2.api.spec.steps;

import ch.heigvd.amt.wp2.ApiException;
import ch.heigvd.amt.wp2.ApiResponse;
import ch.heigvd.amt.wp2.api.BadgesApi;
import ch.heigvd.amt.wp2.api.dto.Badge;
import ch.heigvd.amt.wp2.api.spec.helpers.Environment;
import com.squareup.okhttp.*;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import ch.heigvd.amt.wp2.api.dto.BadgePost;
import ch.heigvd.amt.wp2.api.dto.BadgePatch;

import java.util.ArrayList;
import java.util.List;

public class BadgesSteps {

    private Environment environment;
    private BadgesApi badgesApi;
    private Badge badge;
    private BadgePost badgePost;
    private BadgePatch badgePatch;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;

    private String apiKey = null;

    private String listBadges = null;

    private String badgejson = null;
    private String badgejsonPatched = null;

    private List<Badge> badgesList;

    private Badge badgePatched;


    public BadgesSteps(Environment environment) {
        this.environment = environment;
        this.badgesApi = environment.getBadgeApi();
    }

    @Given("^there is a Badges server$")
    public void there_is_a_Badges_server() throws Throwable {
        assertNotNull(badgesApi);
    }

    @Given("^I have authorization for Badge$")
    public void i_have_authorization_for_Badge() throws Throwable {
            apiKey = "test";
    }

    @Given("^I don't have authorization for Badge$")
    public void i_don_t_have_authorization_for_Badge() throws Throwable {
        apiKey = "";
    }

    @Given("^I have a badge payload with the name \"([^\"]*)\", description \"([^\"]*)\" and image \"([^\"]*)\"$")
    public void i_have_a_badge_payload_with_the_name_description_and_image(String arg1, String arg2, String arg3) throws Throwable {

        badge = new ch.heigvd.amt.wp2.api.dto.Badge();
        badge.setName(arg1);
        badge.setDescription(arg2);
        badge.setImage(arg3);

        badgePost = new BadgePost();
        badgePost.setName(arg1);
        badgePost.setDescription(arg2);
        badgePost.setImage(arg3);
    }

    @When("^I POST it to the /badges endpoint$")
    public void i_POST_it_to_the_badges_endpoint() throws Throwable {

        try {
            lastApiResponse = badgesApi.createBadgeWithHttpInfo(apiKey,badgePost);
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

    @When("^I ask for a list of badges with a GET on the /badges endpoint$")
    public void i_ask_for_a_list_of_badges_with_a_GET_on_the_badges_endpoint() throws Throwable {

        badgesList = new ArrayList<Badge>();

        try {
            lastApiResponse = badgesApi.getBadgesWithHttpInfo(apiKey);
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
            badgesList = (List<Badge>)lastApiResponse.getData();
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }
    }

    @Then("^I receive the badges$")
    public void i_receive_the_badges() throws Throwable {
        assertNotNull(badgesList);
    }

    @When("^I ask for the badge with a GET on the /badges/\"([^\"]*)\" endpoint$")
    public void i_ask_for_the_badge_with_a_GET_on_the_badges_endpoint(String arg1) throws Throwable {
        try {
            lastApiResponse = badgesApi.getBadgeWithHttpInfo(apiKey,arg1);
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
            badge = (Badge) lastApiResponse.getData();
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }

    }

    @Then("^I receive the badge$")
    public void i_receive_the_badge() throws Throwable {
        assertNotNull(badge);
    }



    @When("^I ask for the badge with a PATCH on the /badges/\"([^\"]*)\" endpoint$")
    public void i_ask_for_the_badge_with_a_PATCH_on_the_badges_endpoint(String arg1) throws Throwable {

        badgePatch = new BadgePatch();
        badgePatch.setDescription(badge.getDescription());
        badgePatch.setImage(badge.getImage());

        badgePatched = badge;


        try {
            badgesApi.updateBadge(apiKey,arg1,badgePatch);
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

    @Then("^I see if the badge has been patched$")
    public void i_see_if_the_badge_has_been_patched() throws Throwable {

        assertNotNull(badge);
        assertNotNull(badgePatched);
        assertEquals(badge,badgePatched);
    }

    @Then("^I receive a (\\d+) status code for badge$")
    public void i_receive_a_status_code_for_badge(int arg1) throws Throwable {
        assertEquals(arg1, lastStatusCode);
    }
}