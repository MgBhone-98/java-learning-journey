import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        Scanner sc = new Scanner(System.in); // ၂။ Scanner Object ဆောက်မယ်
        List<Book> myStore = new ArrayList<>();

        System.out.println("--- Add List BookStore ---");

        while (true) {
            System.out.print("Book Title (not have, Type 'exit'): ");
            String title = sc.nextLine();

            if (title.equalsIgnoreCase("exit")) {
                break; // 'exit' လို့ ရိုက်ရင် Loop ထဲက ထွက်မယ်
            }

            System.out.print("price: ");
            double price = sc.nextDouble();

            System.out.print("quantity: ");
            int qty = sc.nextInt();
            sc.nextLine(); // ကျန်ခဲ့တဲ့ newline character ကို ရှင်းထုတ်တာပါ (Buffer clear)

            // ၃။ User ရိုက်လိုက်တဲ့ data နဲ့ Book object ဆောက်ပြီး List ထဲထည့်မယ်
            myStore.add(new Book(title, price, qty));
            System.out.println("Finish Book Listing!\n");
        }

        // စာရင်းချုပ်ပြန်ထုတ်မယ်
        double totalValue = 0;
        System.out.println("\n--- Total Sale ---");
        for (Book b : myStore) {
            double subTotal = b.getPrice() * b.getQuantity();
            totalValue += subTotal;
            System.out.println(b.getTitle() + " | " + b.getPrice() + " x " + b.getQuantity() + " = " + subTotal);
        }

        System.out.println("Total Value: " + totalValue);
        sc.close(); // ၄။ Scanner ကို ပြန်ပိတ်ပေးရမယ်
    }
}