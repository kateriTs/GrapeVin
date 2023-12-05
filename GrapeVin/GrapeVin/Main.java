import java.util.List;
import java.util.ArrayList;
import gf.Questionnaire;
import gf.TestAPI;

public class Main {
        private static ArrayList<String> whiteWines;
        private static ArrayList<String> redWines;
        private static ArrayList<String> roseWines;

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
                saveWinesInArrays(gptResponse);

                // Display the recommended wines to the user
                System.out.println("Recommended Wines:");
                System.out.println(gptResponse);
                // Display the content of the ArrayLists
                System.out.println(whiteWines);
                System.out.println(redWines);
                System.out.println(roseWines);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // Put the wines of the gpt response in the ArrayLists
         public static void saveWinesInArrays(ArrayList<String> gptWineList) {
            for (String wine : gptWineList) {
                if (wine.toLowerCase().contains("white".toLowerCase())){
                    whiteWines.add(wine);
                } else if (wine.toLowerCase().contains("red".toLowerCase())){
                    redWines.add(wine);
                } else if (wine.toLowerCase().contains("rose".toLowerCase())){
                    roseWines.add(wine);
                } else {
                    System.out.println("Unknown Wine Category");
                }
            }
        }
}
