import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/QuizServlet")
public class QuizServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Questionnaire questionnaire = new Questionnaire();

        
        List<String> userResponses = questionnaire.collectUserResponses();

        // Κλήση του TestAPI για αποστολή απαντήσεων στο ChatGPT
        TestAPI testAPI = new TestAPI();  
        List<String> gptResponses = testAPI.getWineRecommendations(String.join(", ", userResponses));

        // Ναταλία και Σοφία τροποποιήστε τα ονόματα αναλόγως --> κλήση του TestAPI για αποθήκευση των απαντήσεων στη βάση δεδομένων
        try {
            testAPI.saveResponsesToDatabase(gptResponses);
        } catch (Exception e) {
            e.printStackTrace();
            // Εδώ μπορείτε να χειριστείτε τυχόν σφάλματα σύνδεσης ή αποθήκευσης στη βάση δεδομένων
        }

        // Εδώ θα προσθέσω πληροφορίες αφού πρώτα φτιάξουμε την σελίδα html για τα αποτελέσματα και την μορφή αποτελεσμάτων αφού τα πάρουμε απο το DB
        response.sendRedirect("quiz.html");
    }
}



