package sample;


public class Main {
    public static void main(String[] args) {
        DB database = new DB();
        //database.connect();
        //database.listNames();
        //database.listBooks();
        database.serchAuthor("Pamuk");
        //database.alterBooks();
        //database.addBook(new String("9876543210123"), new String("Nieambitny rekord"), new String("Maciej Wilk"), new String("2018"));

    }
}