package gf;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TestAPI {
    private static String API_ENDPOINT = "https://api.openai.com/v1/chat/completions";
    private static String apiKey;

    public static void setApiKey(String apiKey) {
        TestAPI.apiKey = apiKey;
    }

    public String getApiKey(){
        return apiKey;
    }

    public static String getWineRecommendations(String[] userResponses) throws Exception {

        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(API_ENDPOINT);

        // Create JSON payload for GPT-3.5 API request
        String jsonPayload = "{ \"model\": \"gpt-3.5-turbo-1106\", \"messages\": ["
        + "{\"role\": \"system\", \"content\": \"You are a helpful assistant.\"},"
        + "{\"role\": \"user\", \"content\": \"Find 5 wines that are: " + userResponses[0] + ", " + userResponses[1] + " and " + userResponses[2] + "\"}"
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

        return result.toString();
    }
}
