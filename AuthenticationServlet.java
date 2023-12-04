@WebServlet("/UserAuthenticationServlet")
public class UserAuthenticationServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "login":
                handleLogin(request, response);
                break;
            case "register":
                handleRegister(request, response);
                break;
            case "suggestWines":
                handleWineSuggestions(request, response);
                break;
            default:
                // Handle other actions or errors
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Handle GET requests (e.g., initial page load)
        // You can add additional functionality here
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Logic for login authentication (not implemented in this example)
        // ...

        // Send response to the client
        PrintWriter out = response.getWriter();
        out.println("<h1>Login Successful</h1>");
        // ...
    }

    private void handleRegister(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Προσθήκη νέου πελάτη στη βάση δεδομένων
        CustomerDatabase.addCustomer(email, name, password);

        // Send response to the client
        PrintWriter out = response.getWriter();
        out.println("<h1>Registration Successful</h1>");
        out.println("<p>Name: " + name + "</p>");
        out.println("<p>Email: " + email + "</p>");
    }

    private void handleWineSuggestions(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //!!! Σοφία και εδώ !!!!
        //String API_ENDPOINT = "https://api.openai.com/v1/chat/completions";
        //TestAPI.getApiEndPoint(); Δεν χρειαζεται εδω γιατι υπαρχει στην κλαση TestAPI

        // Assuming you have user preferences stored in request parameters
        String userEmail = request.getParameter("email");
        // ... other parameters ...

        // Σοφία και εδω !!!
        String suggestedWines = TestAPI.getWineRecommendations(request);

        // Ναταλία και εδω !!!
        //saveSuggestedWinesToDatabase(userEmail, suggestedWines);
        TestAPI.saveResponses(suggestedWines);

        // Send response to the client
        PrintWriter out = response.getWriter();
        out.println("<h1>Wine Suggestions for " + userEmail + "</h1>");
        out.println("<p>" + suggestedWines + "</p>");
    }

   /* private String callExternalApi(String apiEndpoint, String userEmail /* other parameters */) {
        // Σοφία εδώ πρέπει να κοιτάξεις πώς θα το γράψεις } */ //υπαρχει ηδη η static μεθοδος getWineRecommendations(request) στην κλαση TestAPI

    /* private void saveSuggestedWinesToDatabase(String userEmail, String suggestedWines) {
        // Ναταλία διαμορφώνετε ανάλογα με αυτό που θα φτιάξετε τα απο πάνω μπορούν να αλλάξουν όλα αναλογως με αυτό που θα φτιάξετε 
        
    }*/ //υπαρχει ηδη η μεθοδος saveResponses(wines) στην κλάση ΤestAPI
}
