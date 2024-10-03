package stepdefinitions;

import io.cucumber.java.en.*;
import utils.ConfigLoader;

import org.junit.Assert;
import api.NasaApiClient;
import api.NasaApiClient.ApiResponse;
import org.json.JSONObject;
import java.time.LocalDate;

public class NasaApiInteractionTests {
    private NasaApiClient client;
    private ApiResponse apiResponse;

    @Given("I have the NASA API key")
    public void haveTheNasaApiKey() {
        ConfigLoader configLoader = new ConfigLoader();
        String apiKey = configLoader.getProperty("nasa.api.key");
        client = new NasaApiClient(apiKey);
    }

    @When("I send a request for the Astronomy Picture of the Day for {string}")
    public void sendARequestForTheAstronomyPictureOfTheDay(String date) {
        LocalDate requestDate;
        
        if (date.equalsIgnoreCase("yesterday")) {
            requestDate = LocalDate.now().minusDays(1);
        } else {
            requestDate = LocalDate.parse(date);
        }
        
        apiResponse = client.getAstronomyPictureOfTheDay(requestDate);
    }

    @Then("I should receive a response with status code {int}")
    public void shouldReceiveAResponseWithExpectedStatusCode(int expectedStatusCode) {
        Assert.assertEquals(expectedStatusCode, apiResponse.getStatusCode());
    }

    @Then("the response should contain the keys: {string}")
    public void responseShouldContainKeys(String expectedKeys) {
    	String[] keyArray = expectedKeys.split(",\\s*");
        JSONObject response = apiResponse.getResponseBody();

        for (String key : keyArray) {
            Assert.assertTrue("Response should contain key: " + key, response.has(key));
        }
    }

    @Then("the media_type should match one of the following types: {string} or {string}")
    public void mediaTypeShouldMatchOneOf(String expectedType1, String expectedType2) {
        String mediaTypeFromResponse = apiResponse.getResponseBody().getString("media_type");
        Assert.assertTrue(mediaTypeFromResponse.equals(expectedType1) || mediaTypeFromResponse.equals(expectedType2));
    }
}
