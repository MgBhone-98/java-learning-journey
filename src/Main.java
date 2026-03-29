import java.util.ArrayList;
import java.util.List;

class Book {
    // ၁။ တိုက်ရိုက် ပြင်လို့မရအောင် private လုပ်လိုက်တယ်
    private String title;
    private double price;
    private int quantity;

    public Book(String title, double price, int quantity) {
        this.title = title;
        this.price = price;
        this.quantity = quantity;
    }

    // ၂။ Data တွေကို အပြင်ကလှမ်းယူဖို့ Getter Method များ
    public String getTitle() { return title; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }

    // ၃။ ဈေးနှုန်းကို ပြင်ချင်ရင် စနစ်တကျ ပြင်ဖို့ Setter
    public void setPrice(double price) {
        if (price > 0) {
            this.price = price;
        } else {
            System.out.println("Invalid Price!");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        List<Book> myStore = new ArrayList<>();

        myStore.add(new Book("Java Programming", 15000, 5));
        myStore.add(new Book("Database Design", 12000, 3));

        double totalValue = 0;

        for (Book b : myStore) {
            // b.price လို့ ခေါ်မရတော့ပါဘူး၊ b.getPrice() လို့ ခေါ်ရပါမယ်
            double subTotal = b.getPrice() * b.getQuantity();
            totalValue += subTotal;
            System.out.println("Book: " + b.getTitle() + " | Subtotal: " + subTotal);
        }

        System.out.println("--------------------------");
        System.out.println("Total Value: " + totalValue);
    }
}