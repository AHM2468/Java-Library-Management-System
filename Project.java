import java.util.ArrayList; // Importing ArrayList class to use dynamic arrays
import java.util.List; // Importing List interface to use lists for storing books in the library
import java.util.InputMismatchException; // Importing InputMismatchException class to handle input mismatch exceptions
import java.util.Scanner; // Importing Scanner class to make the client could enter an input
// The book class represents a book with attributes such as title, author, and availability status.
// It provides methods to get and set these attributes.
class Book {
    private String title;
    private String author;
    private boolean available;
    // constructor to initialize the title,author and available attributes to specific values
    public Book(String title,String author){
        this.title = title;
        this.author = author;
        this.available = true; // Set available to true by default
    }
    // Setter method for title to update the title of the book
    public void setTitle(String Title){
        this.title = title;
    }
    // Getter method for title to retrieve the title of the book
    public String getTitle(){
        return title;
    }
    // Setter method for author to update the author of the book
    public void setAuthor(String author){
        this.author = author;
    }
    // Getter method for author to retrieve the author of the book
    public String getAuthor(){
        return author;
    }
    // Setter method for available to update the availability status of the book (true if available, false if borrowed)
    public void setAvailable(boolean available){
        this.available = available;
    }
    // Getter method for available to retrieve the availability status of the book (true if available, false if borrowed)
    public boolean isAvailable(){
        return available;
    }
} // end Book class
// 2- Library class
// Library class to be able to manage the book collection in the library
class Library {
    private List<Book> books;
    // Constructor
    public Library(){
        this.books = new ArrayList<>();
    }
    // Creating method to allow clients to add books to the library collection
    public void addNewBook(String title, String author){
// Creating for loop and nested if statement to check if the book already exists in the library's collection
        for (Book book : books){
            if (book.getTitle().equalsIgnoreCase(title) && book.getAuthor().equalsIgnoreCase(author)) {
// If the book was available in the library and not borrowed the system will print that the book is already available
                if (book.isAvailable()) {
                    System.out.println("The book '" + title + "' by author " + author + " is already available in the library.");
                }
// If the book was not available in the library (borrowed) the system will print that the book is in the library collection but is not currently available (borrowed).
                else {
                    System.out.println("The book '" + title + "' by author " + author + " is in the library collection but is not currently available (borrowed).");
                } // end of the inner if...else statement
                return;
            } // end of the outer if statement
        }
// If the book is not found in the library, add it to the library's collection
        Book newBook = new Book(title, author);
        books.add(newBook);
        System.out.println("The book '" + title + "' by " + author + " has been successfully added to the library.");
    }
    // Creating method to add new books to the library
    public void addBook(Book book){
        books.add(book);
    }
    // Creating method to display the available books in the library
    public void displayAvailableBooks(){
        boolean available = false;
        System.out.println("The available books in library:");
        for (Book book : books){
            if (book.isAvailable()){
                System.out.println("Book title: " + book.getTitle() + ", Author: " + book.getAuthor());
                available = true;
            }
        }
// If there are no books available (all borrowed) , the system will print that all books are borrowed
        if (! available ){
            System.out.println("All books are currently borrowed.");
        }
    }
    // Creating method to allow for clients to borrow a book
    public void borrowBook(String title){
        for (Book book : books){
            if (book.getTitle().equals(title) && book.isAvailable()){
                book.setAvailable(false);
                System.out.println("The book with the title (" + title + ") has been successfully borrowed.");
                return;
            }
        }
        System.out.println("The book with title (" + title + ") is either not available currently (borrowed) or does not exist.");
    }
    // Creating method to allow for clients to return a book
    public void returnBook(String title){
        for (Book book : books){
            if (book.getTitle().equalsIgnoreCase(title) && !book.isAvailable()){
                book.setAvailable(true);
                System.out.println("The book with the title (" + title + ") has been returned successfully.");
                return;
            }
        }
        System.out.println("You have either entered an invalid book title or the book is already available.");
    }
    // Creating Method to make clients could search for books by title or author
    public void searchBooks(String keyword){
        boolean FoundResults = false; // Flag to track if any results were found
        System.out.println("The results of the search:");
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase()) || book.getAuthor().toLowerCase().contains(keyword.toLowerCase())) {
                if (book.isAvailable()) {
                    System.out.println("Book title: " + book.getTitle() + ", Author: " + book.getAuthor());
                    FoundResults = true; // Set flag to true if results were found
                }
                else{
                    System.out.println("The book '" + book.getTitle() + "' by author " + book.getAuthor() + " is currently borrowed.");
                }
            }
        }
// If no available results were found, the system will print that there are no results for this input
        if (!FoundResults){
            System.out.println("No results found for the input '" + keyword + "'.");
        }
    }
} // end Library class
//7- Testing
// Project class to ensure that Library system working well
public class Project {
    // main method begins execution of java application
    public static void main (String[]args) {
        Library L = new Library(); // Create an object called L from Library class
        Scanner input = new Scanner(System.in); // Create an object called input from Scanner class
//4- Sample Data
// Add three books to the library's collection during initialization
        L.addBook(new Book("General Physics 101", "Showkat Lone")); // Adding the first book by the method addBook from Library class
        L.addBook(new Book("Introduction to IT and IS", "Ali Abu Abid")); // Adding the second book by the method addBook from Library class
        L.addBook(new Book("Java Programming", "Jameel Alhejely")); // Adding the third book by the method addBook from Library class
        int Service;
// 3- User interface to make clients and librarians could interact with the system
        System.out.println("Welcome to the Saudi E-university Library , which service do you want to choose?");
        displayMenu();
        do{
// 6- Error handling
            try{
                Service = input.nextInt();
                switch(Service){
                    case 1:
                        System.out.println();
                        L.displayAvailableBooks();
// Add a separator line after the end of each service to make it easier for the customer to read the program while it is being executed
                        System.out.println("---------------------------------------------------------");
                        System.out.println("Do you want to choose another service?");
                        displayMenu();
                        break;
// 5- Functionality
// Implement a search function to enable clients to search for books by typing the book title or author name.
                    case 2:
                        System.out.println();
                        input.nextLine(); // Consume newline
                        System.out.print("Enter the title of the book or the author name of this book to search for the book you want: ");
                        String keyword = input.nextLine();
                        L.searchBooks(keyword);
// Add a separator line after the end of each service to make it easier for the customer to read the program while it is being executed
                        System.out.println("---------------------------------------------------------");
                        System.out.println("Do you want to choose another service?");
                        displayMenu();
                        break;
                    case 3:
// When a client borrows a book, the system updates the list of books to remove it
                        System.out.println();
                        input.nextLine(); // Consume newline
                        System.out.print("Enter the title of the book you want to borrow, paying attention to (capital and small letters, spaces): ");
                        String borrowTitle = input.nextLine();
                        L.borrowBook(borrowTitle);
// Add a separator line after the end of each service to make it easier for the customer to read the program while it is being executed
                        System.out.println("---------------------------------------------------------");
                        System.out.println("Do you want to choose another service?");
                        displayMenu();
                        break;
                    case 4:
// When a client returns a book, the system updates the book list to return the book to the book list again
                        System.out.println();
                        input.nextLine(); // Consume newline
                        System.out.print("Enter the title of the book you want to return, paying attention to (capital and small letters, spaces): ");
                        String returnTitle = input.nextLine();
                        L.returnBook(returnTitle);
// Add a separator line after the end of each service to make it easier for the customer to read the program while it is being executed
                        System.out.println("---------------------------------------------------------");
                        System.out.println("Do you want to choose another service?");
                        displayMenu();
                        break;
                    case 5:
// Allow clients to add books to the library collection
                        System.out.println();
                        input.nextLine(); // Consume newline
                        System.out.println("Enter the title of the new book: ");
                        String newTitle = input.nextLine();
                        System.out.println("Enter the author of the new book: ");
                        String newAuthor = input.nextLine();
                        L.addNewBook(newTitle, newAuthor);
// Add a separator line after the end of each service to make it easier for the customer to read the program while it is being executed
                        System.out.println("---------------------------------------------------------");
                        System.out.println("Do you want to choose another service?");
                        displayMenu();
                        break;
// If the client does not want to use another service, the exit option will terminate the program
                    case 6:
                        System.out.println("Exiting...Thank you for using the library system of Saudi E-university.");
                        break;
                    default:
// If the client entered a wrong number , the system will ask him to write a number between 1 and 6 again
                        System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                }
            }
// 6- Error handling
            catch(InputMismatchException e){
                System.out.println("Invalid number. Please enter a valid number.");
                input.nextLine(); // Consume invalid input
                Service = 0; // Reset Service to prompt the user again
            }
        } while(Service != 6);
        input.close();
    } // end method main
    public static void displayMenu(){
        System.out.println("1. Display the available books in the library");
        System.out.println("2. Search books");
        System.out.println("3. Borrow a book");
        System.out.println("4. Return a book");
        System.out.println("5. Add a book");
        System.out.println("6. Exit");
        System.out.print("Enter the number of the service that you want please: ");
    }
} // end Project class