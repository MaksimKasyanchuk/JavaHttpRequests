package WeatherApp;

import java.io.*;
import java.net.*;

import org.json.*;

public class MyWeather{

    private static final String API_KEY = "2610c47c3790a41d59419a9861dbe861";

    private static final String USER_AGENT = "Microsoft Edge";

    private static final String WEB_URL = "https://api.openweathermap.org/data/2.5/weather?q=London&appid=" + API_KEY;

    private static final String JSON_DATA = "{\"coord\":{\"lon\":-0.1257,\"lat\":51.5085},\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"overcast clouds\",\"icon\":\"04d\"}],\"base\":\"stations\",\"main\":{\"temp\":293.31,\"feels_like\":293,\"temp_min\":291.64,\"temp_max\":295.37,\"pressure\":1019,\"humidity\":62},\"visibility\":10000,\"wind\":{\"speed\":6.69,\"deg\":90},\"clouds\":{\"all\":100},\"dt\":1658492146,\"sys\":{\"type\":2,\"id\":2075535,\"country\":\"GB\",\"sunrise\":1658462970,\"sunset\":1658520242},\"timezone\":3600,\"id\":2643743,\"name\":\"London\",\"cod\":200}\n";


    public static void server() throws IOException{
        URL url = new URL(WEB_URL); // Create URL object
        HttpURLConnection connection = (HttpURLConnection) url.openConnection(); // Create connection to URL
        connection.setRequestMethod("GET"); // Set a request method for connection
        connection.setRequestProperty("User-Agent", USER_AGENT); // Set agent to connection
        int responseCode = connection.getResponseCode(); // Get response code from connection
        if (responseCode == HttpURLConnection.HTTP_OK) { // check if connection is ok, and it has response code 200
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream())); // create a char reader
            String inputLine; // Create a line of characters
            StringBuffer response = new StringBuffer(); // Convert response to string

            while ((inputLine = in.readLine()) != null) { // Check if reader isn't null, and it is reading lines
                response.append(inputLine); // Add a line to our response if everything works
            } in.close(); // close reader

            System.out.println(response); // print response

            System.out.println("----------------------");

            // Get JSON weather data
            final JSONObject data = new JSONObject(JSON_DATA); // Create new Json Object with a link to data
            final JSONArray arr = data.getJSONArray("weather"); // get weather value from data array

            for (int i = 0; i < arr.length(); i++) { // go through the weather list
                final String weatherId = String.valueOf(arr.getJSONObject(i).getInt("id")); // get weather list value "id"
                final String weatherMain = arr.getJSONObject(i).getString("main"); // get weather list value "main"
                final String weatherDescription = arr.getJSONObject(i).getString("description"); // get weather list value "description"
                final String weatherIcon = arr.getJSONObject(i).getString("icon"); // get weather list value "icon"

                System.out.println(weatherId);
                System.out.println(weatherMain);
                System.out.println(weatherDescription);
                System.out.println(weatherIcon);
            }

        } else {
            System.out.println("GET request not worked");
        }
    }

    public static void main(String[] args) throws IOException {
        server();
    }
}
