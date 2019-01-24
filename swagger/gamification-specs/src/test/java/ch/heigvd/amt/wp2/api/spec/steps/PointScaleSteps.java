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

public class PointScaleSteps {

    private Environment environment;
    private BadgesApi badgesApi;
    private Badge badge;
    //private BadgePost badgePost;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;

    public PointScaleSteps(Environment environment) {
        this.environment = environment;
        this.badgesApi = environment.getBadgeApi();
    }

    @Given("^There is a pointscales server$")
    public void there_is_a_pointscales_server() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^I have a pointscale payload$")
    public void i_have_a_pointscale_payload() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I POST it to the /pointscales endpoint$")
    public void i_POST_it_to_the_pointscales_endpoint() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^I receive a (\\d+) status code$")
    public void i_receive_a_status_code(int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^I don't have authorization$")
    public void i_don_t_have_authorization() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I ask for a list of pointscales with a GET on the /pointscales endpoint$")
    public void i_ask_for_a_list_of_pointscales_with_a_GET_on_the_pointscales_endpoint() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^I receive the pointscales$")
    public void i_receive_the_pointscales() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^I know the name of a pointscale$")
    public void i_know_the_name_of_a_pointscale() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I ask for the pointscale with a GET on the /pointscales/pointscaleName endpoint$")
    public void i_ask_for_the_pointscale_with_a_GET_on_the_pointscales_pointscaleName_endpoint() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^I receive the pointscale$")
    public void i_receive_the_pointscale() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I ask for the pointscale with a GET on the /pointscales/bapointscaleName endpoint$")
    public void i_ask_for_the_pointscale_with_a_GET_on_the_pointscales_bapointscaleName_endpoint() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I PATCH it to the /pointscales endpoint$")
    public void i_PATCH_it_to_the_pointscales_endpoint() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I ask for a the current pointscale with a GET on the /pointscales/pointscaleName endpoint$")
    public void i_ask_for_a_the_current_pointscale_with_a_GET_on_the_pointscales_pointscaleName_endpoint() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^I see my check if the pointscale has been patched$")
    public void i_see_my_check_if_the_pointscale_has_been_patched() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }





}