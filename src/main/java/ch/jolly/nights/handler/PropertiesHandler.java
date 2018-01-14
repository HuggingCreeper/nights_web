package ch.jolly.nights.handler;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Joshua Kern on 05.10.2016.
 */
public class PropertiesHandler {

    public static Properties load(String filename) {
        Properties prop = new Properties();
        InputStream in = PropertiesHandler.class.getResourceAsStream("../../../../settings/" + filename + ".properties");
        try {
            prop.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

}
