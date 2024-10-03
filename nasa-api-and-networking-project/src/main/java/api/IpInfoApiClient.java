package api;

import org.springframework.web.client.RestTemplate;

public class IpInfoApiClient {
    private static final String IP_INFO_API_URL = "https://ipinfo.io/ip";
    private final RestTemplate restTemplate;

    public IpInfoApiClient() {
        this.restTemplate = new RestTemplate();
    }

    public String getPublicIp() {
        return restTemplate.getForObject(IP_INFO_API_URL, String.class).trim();
    }
}
