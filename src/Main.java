import java.util.ArrayList;
import java.util.List;

// Book class ကို main class ရဲ့ အပြင်မှာ ထားပေးပါ
class Book {
    String title;
    double price;
    int quantity;

    public Book(String title, double price, int quantity) {
        this.title = title;
        this.price = price;
        this.quantity = quantity;
    }
}

public class Main { // ဒီနေရာမှာ M အကြီး ဖြစ်ရပါမယ်
    public static void main(String[] args) { // ဒီစာကြောင်းက အသက်ပဲ
        List<Book> myStore = new ArrayList<>();

        myStore.add(new Book("Java Programming", 15000, 5));
        myStore.add(new Book("Database Design", 12000, 3));

        double totalValue = 0;

        for (Book b : myStore) {
            double subTotal = b.price * b.quantity;
            totalValue += subTotal;
            System.out.println("Book: " + b.title + " | Subtotal: " + subTotal);
        }

        System.out.println("--------------------------");
        System.out.println("Total Value in Store: " + totalValue);
    }
}