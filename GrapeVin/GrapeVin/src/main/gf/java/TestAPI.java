package gf;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.text.Normalizer;

public class TestAPI {
    private static String API_ENDPOINT = "https://api.openai.com/v1/chat/completions";
    private static String apiKey = "OUR_API_KEY";

    public String getApiKey(){
        return apiKey;
    }

    public static ArrayList<String> getWineRecommendations(String request) throws Exception {

        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(API_ENDPOINT);

        // Remove accents from request string
        String request = Normalizer.normalize(input, Normalizer.Form.NFKD);

        // Create JSON payload for GPT-3.5 API request
        String jsonPayload = "{ \"model\": \"gpt-3.5-turbo-1106\", \"messages\": ["
        + "{\"role\": \"system\", \"content\": \"You are a helpful assistant.\"},"
        + "{\"role\": \"user\", \"content\": \"Find 5 wines that are: " + request + "\"}"
        + "] }";

        // Set headers
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Authorization", "Bearer " + apiKey);

        // Set the JSON payload
        httpPost.setEntity(new StringEntity(jsonPayload));

        // Execute the request
        HttpResponse response = httpClient.execute(httpPost);

        // Process the response
        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuilder result = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            result.append(line);
        }

        return parseGptResponse(result.toString());
    }
    
    // Parse the JSON response to extract the recommended wines
    private static ArrayList<String> parseGptResponse(String gptResponse) {
        ArrayList<String> recommendedWines = new ArrayList<>();

        // Extract the assistant's content from the JSON response
        String content = gptResponse.split("\"content\": \"")[1].split("\"")[0];

        // Split the content into lines
        String[] lines = content.split("\\\\n");

        // Add each line to the ArrayList
        for (String line : lines) {
            recommendedWines.add(line.trim());
        }

        return recommendedWines;
    }
}


