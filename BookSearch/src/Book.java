// Inner class to represent a book
class Book {
    private int refNumber;
    private String title;

    // Constructor to initialize a book
    public Book(int refNumber, String title) {
        this.refNumber = refNumber;
        this.title = title;
    }

    // Getter for the reference number
    public int getRefNumber() {
        return refNumber;
    }

    // Getter for the title
    public String getTitle() {
        return title;
    }
}