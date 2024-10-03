package utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetworkUtils {

    public static boolean isIpInRange(String startIp, String endIp, String ipAddress) {
        long ipToCheck = ipToLong(ipAddress);
        return ipToLong(startIp) <= ipToCheck && ipToCheck <= ipToLong(endIp);
    }

    public static String resolveDomain(String domain) throws Exception {
        try {
            InetAddress address = InetAddress.getByName(domain);
            return address.getHostAddress();
        } catch (UnknownHostException e) {
            throw new Exception("Could not resolve domain: " + domain, e);
        }
    }

    private static long ipToLong(String ipAddress) {
        String[] ipParts = ipAddress.split("\\.");
        long result = 0;
        for (int i = 0; i < 4; i++) {
            result += Integer.parseInt(ipParts[i]) << (24 - (8 * i));
        }
        return result;
    }
}
