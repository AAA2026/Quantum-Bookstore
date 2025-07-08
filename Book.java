//AAA
abstract class Book {
    String isbn;
    String title;
    String author;
    int year;
    double price;
    Book(String isbn, String title, String author, int year, double price) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.year = year;
        this.price = price;
    }
    abstract boolean available();
    abstract void buy(int quantity, String email, String address) throws BookException;
}
class Paperbook extends Book {
    int stock;
    Paperbook(String isbn, String title, String author, int year, double price, int stock) {
        super(isbn, title, author, year, price);
        this.stock = stock;
    }
    
@Override
boolean available() { 
    return stock > 0;
    } 
@Override
void buy(int quantity, String email, String address) throws BookException {
        if (stock < quantity)
            throw new BookException("Out of stock"); 
        stock -= quantity;
        double amount = quantity * price;
        System.out.println("Paid " + amount + " gneeh for the paper book: " + title);
        ShippingService.send(address);
    }
}

class ebook extends Book {
    String fileType;
    ebook(String isbn, String title, String author, int year, double price, String fileType) {
        super(isbn, title, author, year, price);
        this.fileType = fileType;
    }
    @Override
    boolean available() {
        return true;
    }
    @Override
    void buy(int quantity, String email, String address) throws BookException {
        double amount = quantity * price;
        System.out.println("paid " + amount + " gneeh for  the eBook: " + title);
        MailService.send(email);
    }
}

class Demobook extends Book {
    Demobook(String isbn, String title, String author, int year) {
        super(isbn, title, author, year, 0);
    }
    @Override
    boolean available() {
        return false;
    }
    @Override
    void buy(int quantity, String email, String address) throws BookException {
        throw new BookException("not for sale");
    }
}

class BookException extends Exception {
    public BookException(String message) {
        super(message);
    }
}
enum BookType { paper, ebook, demo }
class BookFactory {
    static Book paper(String isbn, String title, String author, int year, double price, int stock) {
        return new Paperbook(isbn, title, author, year, price, stock);
    }
    static Book ebook(String isbn, String title, String author, int year, double price, String fileType) {
        return new ebook(isbn, title, author, year, price, fileType);
    }
    static Book demo(String isbn, String title, String author, int year) {
        return new Demobook(isbn, title, author, year);
    }
}