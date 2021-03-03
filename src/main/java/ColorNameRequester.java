import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ColorNameRequester {
    private final HttpClient client;
    private static final String baseURL = "https://www.thecolorapi.com/id?rgb=rgb";

    ColorNameRequester() {
        client = HttpClient.newHttpClient();
    }

    public String getColorName(Color c) {
        HttpRequest request = HttpRequest.newBuilder(
                URI.create(baseURL + "(" + c.getRed() + "," + c.getGreen() + "," +c.getBlue()+ ")"))
                .header("accept", "application/json")
                .build();


        HttpResponse<String> response = null;
        try {
            for (int i = 0; i < 10; i++) {
                response = client.send(request, BodyHandlers.ofString());
                if (response.statusCode() == 200) {
                    break;
                }
                    Thread.sleep(100);
            }

        } catch ( IOException | InterruptedException e){
            System.err.println(e.getMessage());
            return "";
        }

        return getColorNameField(response.body());

    }

    public String getColorNameField(String s) {
        JSONParser parse = new JSONParser();
        try {
            var jsonObj = (JSONObject)parse.parse(s);
            var valueIter = jsonObj.values().iterator();
            String line = null;
            for (int i = 0; valueIter.hasNext(); i++) {
                var tmp = valueIter.next();
                if (i == 6){
                    line = tmp.toString();
                }
            }
            var jsonObj2 = (JSONObject)parse.parse(line);
            var valueIter2 = jsonObj2.values().iterator();
            String value = null;
            for (int i = 0; valueIter2.hasNext(); i++) {
                var tmp = valueIter2.next();
                if (i == 3){
                    value = tmp.toString();
                }
            }
            //System.out.println(value);
            return value; //this should get the value field
        } catch (ParseException e) {
            return "unknown";
        }
    }
}
