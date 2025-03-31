public class Main {
    public static void main(String[] args) {
        BooktrackerApp.addUser(3, 22, "f", "Sara");
        BooktrackerApp.getUserReadingHabits(1);
        BooktrackerApp.updateBookTitle(2, "Clean Code");
        BooktrackerApp.deleteReadingHabit(3);
        BooktrackerApp.getAverageAge();
        BooktrackerApp.getUserCountForBook("Head First Data Analysis");
        BooktrackerApp.getTotalPagesRead();
        BooktrackerApp.getUsersWithMultipleBooks();
    }
}



