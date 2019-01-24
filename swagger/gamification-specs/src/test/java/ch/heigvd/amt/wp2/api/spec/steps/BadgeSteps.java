package ch.heigvd.amt.wp2.api.spec.steps;

import ch.heigvd.amt.wp2.ApiException;
import ch.heigvd.amt.wp2.ApiResponse;
import ch.heigvd.amt.wp2.api.BadgesApi;
import ch.heigvd.amt.wp2.api.spec.helpers.Environment;
import ch.heigvd.amt.wp2.api.dto.Badge;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BadgeSteps {

  private Environment environment;
  private BadgesApi badgesApi;
  private Badge badge;
  //private BadgePost badgePost;

  private ApiResponse lastApiResponse;
  private ApiException lastApiException;
  private boolean lastApiCallThrewException;
  private int lastStatusCode;

    public BadgeSteps(Environment environment) {
        this.environment = environment;
        this.badgesApi = environment.getBadgeApi();
    }

    /*@Given("^there is a Badges server$")
    public void there_is_a_badges_server() throws Throwable {
        assertNotNull(badgesApi);
    }

    @Given("^I have a badge payload$")
    public void i_have_a_badge_payload() throws Throwable {
        badge = new Badge();
        //badgePost = new BadgePost();

        //badge.setDescription("BADGE DESCRIPTION");
        //badge.setImage(null);
        //badge.setName("NAME");
    }

    /*@When("^I POST it to the /bagdes endpoint$")
    public void i_POST_it_to_the_badges_endpoint() throws Throwable {
        try {
            lastApiResponse = badgesApi.createBadgeWithHttpInfo(badgePost);
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


    @When("^I GET it to the /badges endpoint$")
    public void i_GET_it_to_the_badges_endpoint() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^I receive a list of bades$")
    public void i_receive_a_list_of_bades() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }


    @Given("^I know the name of a badge$")
    public void i_know_the_name_of_a_badge() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^I receive a list of badges$")
    public void i_receive_a_list_of_badges() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I ask for a list of badges with a GET on the  /badges endpoint$")
    public void i_ask_for_a_list_of_badges_with_a_GET_on_the_badges_endpoint() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^I receive the badges$")
    public void i_receive_the_badges() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^I see my Badge in the list$")
    public void i_see_my_Badge_in_the_list() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I PATCH it to the /badges endpoint$")
    public void i_PATCH_it_to_the_badges_endpoint() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I ask for a the current badge with a GET on the /badges/id endpoint$")
    public void i_ask_for_a_the_current_badge_with_a_GET_on_the_badges_id_endpoint() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^I see my check if the badge has been patched$")
    public void i_see_my_check_if_the_badge_has_been_patched() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^I don't have authorization$")
    public void i_don_t_have_authorization() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I ask for the badge with a GET on the /badges/badgeName endpoint$")
    public void i_ask_for_the_badge_with_a_GET_on_the_badges_badgeName_endpoint() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I ask for a the current badge with a GET on the /badges/badgeName endpoint$")
    public void i_ask_for_a_the_current_badge_with_a_GET_on_the_badges_badgeName_endpoint() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }*/




}