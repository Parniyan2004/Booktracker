import java.sql.*;

public class BooktrackerApp {

    private static final String url = "jdbc:sqlite:booktracker.db";

    public static void addUser(int id, int age, String gender, String name) {
        String sql = "INSERT INTO User(userID, age, gender, name) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setInt(2, age);
            pstmt.setString(3, gender);
            pstmt.setString(4, name);
            pstmt.executeUpdate();
            System.out.println("User added.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getUserReadingHabits(int userID) {
        String sql = "SELECT * FROM ReadingHabit WHERE userID = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("Book: " + rs.getString("book") + ", Pages: " + rs.getInt("pagesRead"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateBookTitle(int habitID, String newTitle) {
        String sql = "UPDATE ReadingHabit SET book = ? WHERE habitID = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newTitle);
            pstmt.setInt(2, habitID);
            pstmt.executeUpdate();
            System.out.println("Book title updated.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteReadingHabit(int habitID) {
        String sql = "DELETE FROM ReadingHabit WHERE habitID = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, habitID);
            pstmt.executeUpdate();
            System.out.println("Reading habit deleted.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getAverageAge() {
        String sql = "SELECT AVG(age) AS avgAge FROM User";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("Average age: " + rs.getDouble("avgAge"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getUserCountForBook(String bookTitle) {
        String sql = "SELECT COUNT(DISTINCT userID) AS count FROM ReadingHabit WHERE book = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, bookTitle);
            ResultSet rs = pstmt.executeQuery();
            System.out.println("Users who read \"" + bookTitle + "\": " + rs.getInt("count"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getTotalPagesRead() {
        String sql = "SELECT SUM(pagesRead) AS total FROM ReadingHabit";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("Total pages read: " + rs.getInt("total"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getUsersWithMultipleBooks() {
        String sql = "SELECT userID FROM ReadingHabit GROUP BY userID HAVING COUNT(DISTINCT book) > 1";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("Users with multiple books:");
            while (rs.next()) {
                System.out.println("UserID: " + rs.getInt("userID"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
