package com.ps;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static Book[] books = new Book[20];

    public static void main(String[] args) {
        books[0] = new Book(1, "9780140449136", "The Odyssey", false, "");
        books[1] = new Book(2, "9780439023528", "The Hunger Games", false, "");
        books[2] = new Book(3, "9780316769488", "The Catcher in the Rye", true, "Bob");
        books[3] = new Book(4, "9780061120084", "To Kill a Mockingbird", false, "");
        books[4] = new Book(5, "9780743273565", "The Great Gatsby", false, "");
        books[5] = new Book(6, "9780451524935", "1984", true, "Dave");
        books[6] = new Book(7, "9780547928227", "The Hobbit", false, "");
        books[7] = new Book(8, "9780141439600", "Wuthering Heights", true, "Garrett");
        books[8] = new Book(9, "9780143127741", "The Alchemist", false, "");
        books[9] = new Book(10, "9780307277671", "The Road", false, "");
        books[10] = new Book(11, "9780156012195", "The Little Prince", false, "");
        books[11] = new Book(12, "9780618640157", "The Lord of the Rings", false, "");
        books[12] = new Book(13, "9780062315007", "The Subtle Art of Not Giving a F*ck", true, "Sally");
        books[13] = new Book(14, "9780141182803", "Crime and Punishment", false, "");
        books[14] = new Book(15, "9781451673319", "Fahrenheit 451", false, "");
        books[15] = new Book(16, "9780374533557", "The Stranger", true, "Perry");
        books[16] = new Book(17, "9780525566151", "Atomic Habits", false, "");
        books[17] = new Book(18, "9780062457714", "The Power of Habit", false, "");
        books[18] = new Book(19, "9780385472579", "Things Fall Apart", false, "");
        books[19] = new Book(20, "9780060850524", "Brave New World", false, "");
        boolean running = true;
        while(running) {
            displayMenu();
            short choice = scanner.nextShort();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    displayBooks(books);
                    promptUserToCheckOut();
                    break;
                case 2:
                    displayCheckedOutBooks(books);
                    promptUserCheckInBooks();
                    break;
                case 3:
                    running = false;
            }
        }
    }
//  Method to display the starting menu
    public static void displayMenu(){
        System.out.println("Welcome to the Library!");
        System.out.println("Choose how you would like to spend your time at the library today");
        System.out.println("1)Show available books");
        System.out.println("2)Show checked out books");
        System.out.println("3)Exit the library");
    }

//  Method to display the books that are not checked out
    public static void displayBooks(Book[] books){
        System.out.println("[");
        for(Book book: books){
            if(!book.isCheckedOut()){
                System.out.print("ID: "+book.getId()+", ");
                System.out.print("Isbn: "+book.getIsbn()+", ");
                System.out.println("Title: "+book.getTitle()+" ");
            }
        }
        System.out.println("]");
    }

//  Method to display all checked out books
    public static void displayCheckedOutBooks(Book[] books){
        System.out.println("[");
        for(Book book: books){
            if(book.isCheckedOut()){
                System.out.print("ID: "+book.getId()+", ");
                System.out.print("Isbn: "+book.getIsbn()+", ");
                System.out.print("Title: "+book.getTitle()+", ");
                System.out.println("Checked out to: "+book.getCheckedOutTo());
            }
        }
        System.out.println("]");
    }

//  Method to choose a single available book
    public static Book chooseBook(Book[] books, String name){
        System.out.println("Choose a book by entering its id: ");
        int bookID = scanner.nextInt();
        scanner.nextLine();
//      empty book
        Book chosenBook = new Book();

        for(Book book : books){
            if(book.getId() == bookID){
                book.setCheckedOut(true);
                book.setCheckedOutTo(name);
                chosenBook = book;
                break;
            }
        }
        return chosenBook;
    }

//  Method that prompts user to choose a book to check out or return to the menu
    public static void promptUserToCheckOut(){
        ArrayList<Book> checkedOutBooks = new ArrayList<>();
        boolean checkingOut= true;
        while(checkingOut) {
            System.out.println("Would you like to check out a book? ");
            String decision = scanner.nextLine();

            decision = decision.trim().toLowerCase();
            char yesNo = decision.charAt(0);

            if(yesNo == 'y'){
                System.out.println("Enter your name: ");
                String name = scanner.nextLine().trim();
                Book chosenBook = chooseBook(books, name);
                checkedOutBooks.add(chosenBook);

            }else{
                checkingOut = false;
            }
        }
        System.out.println("You have checked out-");
        for(Book b : checkedOutBooks){
            System.out.print("ID: "+b.getId()+", ");
            System.out.print("Isbn: "+b.getIsbn()+", ");
            System.out.println("Title: "+b.getTitle()+" ");
        }
    }

//  Method that returns the book by getting the id of the book
    public static Book getBookByID(short id , Book[] books){
        Book b = new Book();

        for(Book book : books){
            if(book.getId() == id){
                b = book;
                break;
            }
        }
        return b;

    }

//  Method to check in a single book
    public static void checkInBook(Book book) {
        book.setCheckedOut(false);
        book.setCheckedOutTo("");
    }

//  Method to prompt user to check in books
    public static void promptUserCheckInBooks() {
        System.out.println("Enter C to check in a book");
        System.out.println("Enter X to exit");
        String choice = scanner.nextLine();

        if (choice.equals("C") || choice.equals("c")) {
            System.out.print("Enter the id of the book you are returning: ");
            short id = scanner.nextShort();

            checkInBook(getBookByID(id, books));
        }
    }
}

