package ch.jolly.nights.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Joshua Kern on 30.09.2016.
 */
public class JsonHandler {

    public static String convertGeoCode(String lat, String lng) {
        URL myURL;
        TimerTask task;
        String reportKey = "";

        try {
            myURL = new URL(
                    "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + lat + "," + lng + "&key=AIzaSyCVG5bffPnxRZkGFq9x7rpMl9PHEFCpAQs");

//            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("webproxy.sr.allianz-suisse.ch", 8080));
//            URLConnection conn = myURL.openConnection(proxy);
            URLConnection conn = myURL.openConnection();
            conn.connect();

            conn.setConnectTimeout(4 * 600000);
            conn.setReadTimeout(4 * 600000);

            Timer timer = new Timer();
            task = new TimerTask() {
                int time = 0;

                public void run() {
                    System.out.println(time++);
                }
            };

            System.out.println("Connecting..." + "Timeout="
                    + conn.getConnectTimeout());
            timer.scheduleAtFixedRate(task, 0, 1000);
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    conn.getInputStream(), "UTF8"));
            task.cancel();

            StringBuilder jsonInString = new StringBuilder();

            String line = in.readLine();

            while (line != null) {
                jsonInString.append(line);
                line = in.readLine();
            }

            ObjectMapper mapper = new ObjectMapper();
            JsonNode array = mapper.readValue(jsonInString.toString(), JsonNode.class);
            JsonNode object = array.get("results").get(0);
            reportKey = object.get("formatted_address").textValue();

            return reportKey;


        } catch (Exception e) {
            System.out.println("Proxy ist noch eingestellt");
        }
        return reportKey;
    }

}
