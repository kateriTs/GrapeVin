import java.util.List;
import java.util.ArrayList;
import gf.Questionnaire;
import gf.TestAPI;

public class Main {

        public static void main(String[] args) {         
            Questionnaire questionnaire = new Questionnaire();
            List<String> userResponses = questionnaire.collectUserResponses();
            String request = "Find 5 brands of wine that are: " + userResponses.get(1) + "and " + userResponses.get(3)
                 + ". Please display them strictly like this: name - color and flavor";
           
            // Print user responses
            System.out.println("User Responses: " + userResponses);
           
            // Use GPT-3.5 to analyze responses and get wine recommendations
            try {
                ArrayList<String> gptResponse = TestAPI.getWineRecommendations(request);

                // Display the recommended wines to the user
                System.out.println("Recommended Wines:");
                System.out.println(gptResponse);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } 
}
