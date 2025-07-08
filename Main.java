//AAA
import java.util.*;
class Inventory {
    Map<String, Book> books = new HashMap<>();
    void addBook(Book book) {
        books.put(book.isbn, book);
        System.out.println("added " + book.title);
    }
    Book removeBook(String isbn) {
        return books.remove(isbn);
    }

    void removeOutdatedBooks(int olderThanYears) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        List<String> toRemove = new ArrayList<>();
        for (Book book : books.values()) {
            if (currentYear - book.year > olderThanYears) {
                toRemove.add(book.isbn);
                System.out.println("book is outdated" + book.title);
            }
        }
        for (String isbn : toRemove) {
            books.remove(isbn);
        }
    }
    void buyBook(String isbn, int quantity, String email, String address) throws BookException {
        Book book = books.get(isbn);
        if (book == null)
            throw new BookException("Book is not found");
        if (!book.available())
            throw new BookException("***The Demo book: " + book.title + " is a demo");
        book.buy(quantity, email, address);
    }
}

class QuantumBookstoreFullTest {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Book book1 = BookFactory.paper("ISBN1", "How to cook", "Mahmoud el tabakh", 2018, 100, 10);
        Book book2 = BookFactory.ebook("ISBN2", "It Ends with Us", "Coleen Hoover", 2017, 10, "PDF");
        Book book3 = BookFactory.demo("ISBN3", "AAA for OOP", "Tom Lee", 2010);

        inventory.addBook(book1);
        inventory.addBook(book2);
        inventory.addBook(book3);
        inventory.removeOutdatedBooks(20);
        try {
            inventory.buyBook("ISBN1", 2, "abdala@gmail.com", "Cairo");
            inventory.buyBook("ISBN2", 1, "mahmoud@hotmail.com", "Giza");
            inventory.buyBook("ISBN3", 2, "mo@gmail.com", "qalyoub");
        } catch (BookException e) {
            System.out.println(" " + e.getMessage());
        }
    }
}
class ShippingService {
    static void send(String address) {
        System.out.println("shiping to " + address);
    }
}
class MailService {
    static void send(String email) {
        System.out.println("Sent eBook to " + email);
    }
}