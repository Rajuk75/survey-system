import java.sql.*;

public class SurveySystem {

    static final String JDBC_URL = "jdbc:mysql://localhost/survey_system";
    static final String JDBC_USER = "your_username";
    static final String JDBC_PASSWORD = "your_password";

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            // Create a survey
            String createSurveyQuery = "INSERT INTO surveys (title) VALUES (?)";
            PreparedStatement createSurveyStmt = connection.prepareStatement(createSurveyQuery, Statement.RETURN_GENERATED_KEYS);
            createSurveyStmt.setString(1, "Customer Satisfaction Survey");
            createSurveyStmt.executeUpdate();

            ResultSet generatedKeys = createSurveyStmt.getGeneratedKeys();
            int surveyId = -1;
            if (generatedKeys.next()) {
                surveyId = generatedKeys.getInt(1);
            }

            // Create a question
            String createQuestionQuery = "INSERT INTO questions (survey_id, question_text) VALUES (?, ?)";
            PreparedStatement createQuestionStmt = connection.prepareStatement(createQuestionQuery, Statement.RETURN_GENERATED_KEYS);
            createQuestionStmt.setInt(1, surveyId);
            createQuestionStmt.setString(2, "How satisfied are you with our services?");
            createQuestionStmt.executeUpdate();

            // Create options for the question
            String createOptionQuery = "INSERT INTO options (question_id, option_text) VALUES (?, ?)";
            PreparedStatement createOptionStmt = connection.prepareStatement(createOptionQuery);
            createOptionStmt.setInt(1, questionId);
            createOptionStmt.setString(2, "Very Satisfied");
            createOptionStmt.executeUpdate();

            // ... repeat for other options

            // Simulate user response
            String submitResponseQuery = "INSERT INTO responses (survey_id, question_id, option_id) VALUES (?, ?, ?)";
            PreparedStatement submitResponseStmt = connection.prepareStatement(submitResponseQuery);
            submitResponseStmt.setInt(1, surveyId);
            submitResponseStmt.setInt(2, questionId);
            submitResponseStmt.setInt(3, optionId);
            submitResponseStmt.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
