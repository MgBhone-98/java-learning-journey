import java.util.ArrayList;
import java.util.List;

// Parent Class (ရိုးရိုး Book)
class Book {
    private String title;
    private double price;
    private int quantity;

    public Book(String title, double price, int quantity) {
        this.title = title;
        this.price = price;
        this.quantity = quantity;
    }

    public String getTitle() { return title; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
}

// Child Class (EBook သည် Book ထံမှ အမွေဆက်ခံသည်)
class EBook extends Book {
    private double fileSizeMB;

    public EBook(String title, double price, int quantity, double fileSizeMB) {
        // super() သည် Parent Class ရဲ့ Constructor ကို လှမ်းခေါ်တာဖြစ်သည်
        super(title, price, quantity); 
        this.fileSizeMB = fileSizeMB;
    }

    public double getFileSizeMB() { return fileSizeMB; }
}

public class Main {
    public static void main(String[] args) {
        List<Book> myStore = new ArrayList<>();

        // ရိုးရိုးစာအုပ် ထည့်မယ်
        myStore.add(new Book("Java Core", 15000, 2));

        // E-Book ထည့်မယ် (Book list ထဲကို EBook လည်း ထည့်လို့ရပါတယ် - ဒါကို Polymorphism လို့ခေါ်တယ်)
        myStore.add(new EBook("Spring Boot Guide (PDF)", 8000, 1, 15.5));

        System.out.println("--- Book List ---");
        for (Book b : myStore) {
            System.out.println("Title: " + b.getTitle() + " | Price: " + b.getPrice());
            
            // EBook ဟုတ်မဟုတ် စစ်ပြီး fileSize ထုတ်ပြမယ်
            if (b instanceof EBook) {
                EBook eb = (EBook) b; // Casting လုပ်ခြင်း
                System.out.println(">> Download Size: " + eb.getFileSizeMB() + " MB");
            }
        }
    }
}
