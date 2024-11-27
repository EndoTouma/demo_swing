package config;

import java.net.MalformedURLException;
import java.net.URL;

public class Provider {

    public static URL getAppium(){
        try {
            return new URL("http://127.0.0.1:4723/");
        }
        catch (MalformedURLException e){
            throw new RuntimeException(e);
        }
    }
}
