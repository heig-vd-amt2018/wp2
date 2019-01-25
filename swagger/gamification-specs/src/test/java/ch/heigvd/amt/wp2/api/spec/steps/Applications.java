package ch.heigvd.amt.wp2.api.spec.steps;

import ch.heigvd.amt.wp2.ApiException;
import ch.heigvd.amt.wp2.ApiResponse;
import ch.heigvd.amt.wp2.api.ApplicationsApi;
import ch.heigvd.amt.wp2.api.dto.Application;
import ch.heigvd.amt.wp2.api.spec.helpers.Environment;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Applications {

    private Environment environment;
    private ApplicationsApi applicationsApi;
    private Application application;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;

    private String apiKey = null;


    public Applications(Environment environment) {
        this.environment = environment;
        this.applicationsApi = environment.getApplicationsApi();
    }

    public String getApiKey(){
        return apiKey;
    }

    @Given("^there is a Application server$")
    public void there_is_a_Application_server() throws Throwable {
        assertNotNull(applicationsApi);
    }

    @Given("^I have a application payload with the apikey \"([^\"]*)\"$")
    public void i_have_a_application_payload_with_the_apikey(String arg1) throws Throwable {
        application = new Application();
        application.setApiKey(arg1);
   }

    @When("^I POST it to the /applications endpoint$")
    public void i_POST_it_to_the_applications_endpoint() throws Throwable {

        try {
            lastApiResponse = applicationsApi.createApplicationWithHttpInfo(application);
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

    @Then("^I receive a (\\d+) status code for application$")
    public void i_receive_a_status_code_for_application(int arg1) throws Throwable {
        assertEquals(arg1, lastStatusCode);
    }
}