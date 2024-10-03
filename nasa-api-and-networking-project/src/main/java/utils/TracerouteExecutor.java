package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TracerouteExecutor {

    public static int runTraceroute(String targetIp) throws Exception {
    	String os = System.getProperty("os.name").toLowerCase();
        ProcessBuilder pb;

        if (os.contains("win")) {
            pb = new ProcessBuilder("tracert", targetIp);
        } else {
            pb = new ProcessBuilder("traceroute", targetIp);
        }

        pb.redirectErrorStream(true);
        Process process = pb.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        System.out.println("---> Traceroute Output for " + targetIp);

        String line;
        int hopCount = 0;

        while ((line = reader.readLine()) != null) {

            if (line.matches("^\\s*\\d+\\s+.*")) { 
                hopCount++;
            }
            
            System.out.println("---> Line: " + line);
        }

        process.waitFor();
        return hopCount; 
    }
}
