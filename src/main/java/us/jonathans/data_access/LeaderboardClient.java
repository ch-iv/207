package us.jonathans.data_access;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import org.json.JSONArray;
import org.json.JSONObject;

public class LeaderboardClient {
    private static final String API_KEY = "";        // ADD API KEY for the leaderboard API
    private static final String BASE_URL = "";           // ADD URL for the leaderboard API

    private static HttpClient httpClient = HttpClient.newHttpClient();

    // Method that posts results of user to the leaderboard
    public void postleaderboard(String name, String opponent, int score, long timestamp){
        String url = BASE_URL + API_KEY;

        JSONObject json = new JSONObject();
        json.put("name", name);                 // Takes in the name of the user
        json.put("opponent", opponent);         // Takes in the name of the opponent (AI)
        json.put("score", score);               // Takes in the final score of the user
        json.put("timestamp", timestamp);       // Takes in the date/time the user played the game at

        //Post Request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json.toString(), StandardCharsets.UTF_8))
                .build();

        try {
            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // Method that gets the result of the leaderboard and returns it to the user
    public static JSONArray getleaderboard(){
        String url = BASE_URL + API_KEY;

        //Get Request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        JSONObject jsonResponse = new JSONObject(response.body());
        return jsonResponse.getJSONArray("leaderboard");
    }
}