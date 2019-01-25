package ch.heigvd.amt.wp2.api.spec.steps;

import ch.heigvd.amt.wp2.ApiException;
import ch.heigvd.amt.wp2.ApiResponse;
import ch.heigvd.amt.wp2.api.PointScalesApi;
import ch.heigvd.amt.wp2.api.dto.PointScale;
import ch.heigvd.amt.wp2.api.dto.PointScalePost;
import ch.heigvd.amt.wp2.api.spec.helpers.Environment;
import com.squareup.okhttp.*;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ch.heigvd.amt.wp2.api.dto.PointScalePatch;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PointscaleSteps {

    private Environment environment;
    private PointScalesApi pointScalesApi;
    private PointScale pointScale;
    private PointScale pointScalePatched;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;
    private String apiKey = null;

    private List<PointScale> pointScaleList;

    private PointScalePost pointScalePost;


    public PointscaleSteps(Environment environment) {
        this.environment = environment;
        this.pointScalesApi = environment.getPointScalesApi();
    }

    @Given("^I have authorization$")
    public void i_have_authorization() throws Throwable {
        apiKey = "test";
    }

    @Given("^I don't have authorization$")
    public void i_don_t_have_authorization() throws Throwable {
        apiKey = "";
    }

    @Given("^there is a Pointscales server$")
    public void there_is_a_Pointscales_server() throws Throwable {
        assertNotNull(pointScalesApi);
    }

    @Given("^I have a pointscale payload$")
    public void i_have_a_pointscale_payload() throws Throwable {
        pointScale = new ch.heigvd.amt.wp2.api.dto.PointScale();
        pointScale.setName("test name");
        pointScale.setDescription("test description");
    }

    @Given("^I have a pointscale payload with the name \"([^\"]*)\" and description \"([^\"]*)\"$")
    public void i_have_a_pointscale_payload_with_the_name_and_description(String arg1, String arg2) throws Throwable {

        pointScale = new PointScale();
        pointScale.setName(arg1);
        pointScale.setDescription(arg2);

        pointScalePost = new PointScalePost();
        pointScalePost.setName(arg1);
        pointScalePost.setDescription(arg2);
    }

    @When("^I POST it to the /pointscales endpoint$")
    public void i_POST_it_to_the_pointscales_endpoint() throws Throwable {

            try {
            lastApiResponse = pointScalesApi.createPointScaleWithHttpInfo(apiKey,pointScalePost);
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

    @Then("^I receive a (\\d+) status code$")
    public void i_receive_a_status_code(int arg1) throws Throwable {
        assertEquals(arg1, lastStatusCode);
    }

    @When("^I ask for a list of pointscales with a GET on the /pointscales endpoint$")
    public void i_ask_for_a_list_of_pointscales_with_a_GET_on_the_pointscales_endpoint() throws Throwable {

        pointScaleList = new ArrayList<PointScale>();

    try {
            lastApiResponse = pointScalesApi.getPointScalesWithHttpInfo(apiKey);
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
            pointScaleList = (List<PointScale>)lastApiResponse.getData();
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }
    }

    @Then("^I receive the pointscales$")
    public void i_receive_the_pointscales() throws Throwable {
        assertNotNull(pointScaleList);
    }

    @When("^I ask for the pointscale with a GET on the /pointscales/\"([^\"]*)\" endpoint$")
    public void i_ask_for_the_pointscale_with_a_GET_on_the_pointscales_endpoint(String arg1) throws Throwable {

        try {
            lastApiResponse = pointScalesApi.getPointScaleWithHttpInfo(apiKey,arg1);
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
            pointScale = (PointScale)lastApiResponse.getData();
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }
    }

    @Then("^I receive the pointscale$")
    public void i_receive_the_pointscale() throws Throwable {
        assertNotNull(pointScale);
    }

    @When("^I ask for the pointscale with a PATCH on the /pointscales/\"([^\"]*)\" endpoint$")
    public void i_ask_for_the_pointscale_with_a_PATCH_on_the_pointscales_endpoint(String arg1) throws Throwable {


        PointScalePatch pointScalePatch = new PointScalePatch();
        pointScalePatch.setDescription(pointScale.getDescription());

        pointScalePatched = new PointScale();

        pointScalePatched.setDescription(pointScale.getDescription());
        pointScalePatched.setName(pointScale.getName());

        try {
            pointScalesApi.updatePointScale(apiKey,arg1,pointScalePatch);
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

    @Then("^I see if the pointscale has been patched$")
    public void i_see_if_the_pointscale_has_been_patched() throws Throwable {

        assertNotNull(pointScale);
        assertNotNull(pointScalePatched);
        assertEquals(pointScale,pointScalePatched);
    }

}