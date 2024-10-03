package stepdefinitions;

import io.cucumber.java.en.*;
import utils.NetworkUtils;
import utils.TracerouteExecutor;

import org.junit.Assert;

import api.IpInfoApiClient;

public class NetworkValidationTests {
    private String publicIp;
    private String resolvedIp;
    private int actualHops;


    @Given("I retrieve my public IP address")
    public void retrieveMyPublicIpAddress() {
        IpInfoApiClient ipValidation = new IpInfoApiClient();
        publicIp = ipValidation.getPublicIp();
    }

    @Then("the IP address should not fall within the range from {string} to {string}")
    public void ipShouldNotFallWithinRestrictedRange(String rangeStart, String rangeEnd) {
        Assert.assertFalse(NetworkUtils.isIpInRange(rangeStart, rangeEnd, publicIp));
    }

    @When("I resolve the domain {string}")
    public void resolveDomain(String domain) throws Exception {
        resolvedIp = NetworkUtils.resolveDomain(domain);
        Assert.assertNotNull("Resolved IP should not be null", resolvedIp);
    }
    
    @Then("the resolved IP address should be {string}")
    public void resolvedIpAddressShouldBe(String expectedIp) {
    	Assert.assertEquals(expectedIp, resolvedIp);
    }

    @When("I perform a traceroute to {string}")
    public void performTraceroute(String ip) throws Exception {
        actualHops = TracerouteExecutor.runTraceroute(ip);        
        Assert.assertTrue("Traceroute command should have executed", actualHops > 0);
    }

    @Then("the target should be reached within {int} hops")
    public void targetReachedWithinHops(int maxHops) {
        Assert.assertTrue("Target should be reached within " + maxHops + " hops, but was " + actualHops,
                          actualHops <= maxHops);
    }
}