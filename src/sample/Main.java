package sample;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DB database = new DB();
        boolean flag = true;
        int choise=0;
        while (flag) {
            System.out.println("Co chcesz zrobić?: \n 1. Wczytaj nową książkę \n 2.Wyświetl bazę danych \n 3. Znajdź książki autora \n 4. Zakończ");
            choise = getInt();
            System.out.println(choise);
            if (choise ==1) {
                System.out.println("Podaj nr isbn [13 znaków]: ");
                String isbn = getString();
                System.out.println("Podaj tytuł: ");
                String title = getString();
                System.out.println("Podaj autora: ");
                String author = getString();
                System.out.println("Podaj rok, w którym napisano książkę: ");
                String year = getString();
                database.addBook(isbn, title, author, year);
            }

            if (choise == 2) {
                try {
                    database.listBooks();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (choise == 3){
                System.out.println("Podaj przynajmniej nazwisko autora: ");
                String author = getString();
                database.serchAuthor(author);
            }

            if (choise == 4) flag = false;

        }
            //database.connect();
            //database.listNames();
            //database.listBooks();
            database.serchAuthor("Pamuk");
            //database.alterBooks();
            //database.addBook(new String("9876543210123"), new String("Nieambitny rekord"), new String("Maciej Wilk"), new String("2018"));

        }

    public static String getString() {
        String text = null;
        try{
            InputStreamReader rd = new InputStreamReader(System.in);
            BufferedReader bfr = new BufferedReader(rd);

            text = bfr.readLine();
        }catch(IOException e){e.printStackTrace();}
        return text;
    }
    public static int getInt(){
        int n = 0;
        try {
            InputStreamReader rd = new InputStreamReader(System.in);
            BufferedReader bfr = new BufferedReader(rd);
            n = Integer.parseInt(bfr.readLine());
        }catch (IOException e){e.printStackTrace();}
        return n;
    }
}