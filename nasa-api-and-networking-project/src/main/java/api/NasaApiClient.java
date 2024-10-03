package api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.json.JSONObject;
import java.time.LocalDate;

public class NasaApiClient {
    private static final String NASA_APOD_API_URL = "https://api.nasa.gov/planetary/apod";
    private final String apiKey;
    private final RestTemplate restTemplate;

    public NasaApiClient(String apiKey) {
        this.apiKey = apiKey;
        this.restTemplate = new RestTemplate();
    }

    public ApiResponse getAstronomyPictureOfTheDay(LocalDate date) {
        String url = UriComponentsBuilder.fromHttpUrl(NASA_APOD_API_URL)
                                         .queryParam("api_key", apiKey)
                                         .queryParam("date", date.toString())
                                         .toUriString();

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        int statusCode = responseEntity.getStatusCode().value();
        String responseBody = responseEntity.getBody();

        return new ApiResponse(statusCode, new JSONObject(responseBody));
    }

    public static class ApiResponse {
        private final int statusCode;
        private final JSONObject responseBody;

        public ApiResponse(int statusCode, JSONObject responseBody) {
            this.statusCode = statusCode;
            this.responseBody = responseBody;
        }

        public int getStatusCode() {
            return statusCode;
        }

        public JSONObject getResponseBody() {
            return responseBody;
        }
    }
}
