package gf;

import java.util.ArrayList;

public class User {
    private static ArrayList<User> users = new ArrayList<>();
    
    private String username;
    private String password;
    private String userEmail;
    private ArrayList<String> quizAnswers;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.userEmail = email;
        users.add(this);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return userEmail;
    }

    public ArrayList<String> getQuizAnswers() {
        return quizAnswers;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public void addUserWines(ArrayList<String> gptWineList) {
        for (String wine : gptWineList)
            quizAnswers.add(wine);
    }

    public static User findUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null; // User not found
    }
}
