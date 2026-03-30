import java.util.ArrayList;
import java.util.List;

interface Discountable {
    double calculateDiscount(); // ဒီ Function ကို implementation လုပ်တဲ့သူတိုင်း မဖြစ်မနေ ရေးရမယ်
}

class Book implements Discountable {
    private String title;
    private double price;
    private int quantity;

    public Book(String title, double price, int quantity) {
        this.title = title;
        this.price = price;
        this.quantity = quantity;
    }

    public double getPrice() { return price; }
    public String getTitle() { return title; }

    @Override
    public double calculateDiscount() {
        return price * 0.1; // ရိုးရိုးစာအုပ်ကို 10% discount ပေးမယ်
    }
}

class EBook extends Book {
    public EBook(String title, double price, int quantity) {
        super(title, price, quantity);
    }

    @Override
    public double calculateDiscount() {
        return getPrice() * 0.2; // E-Book ကို 20% discount ပေးမယ်
    }
}

public class Main {
    public static void main(String[] args) {
        List<Book> myStore = new ArrayList<>();
        myStore.add(new Book("Java Basics", 10000, 1));
        myStore.add(new EBook("Advanced Java (E-Book)", 10000, 1));

        for (Book b : myStore) {
            double discount = b.calculateDiscount();
            double finalPrice = b.getPrice() - discount;
            
            System.out.println("Item: " + b.getTitle());
            System.out.println("Original Price: " + b.getPrice());
            System.out.println("Discount: " + discount);
            System.out.println("Final Price: " + finalPrice);
            System.out.println("-------------------------");
        }
    }
}
