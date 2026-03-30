import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

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

// Discountable, Book, EBook class တွေက အရင်အတိုင်းပဲ ထားပါ (မပြောင်းလဲပါ)

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double price = 0;
        int qty = 0;

        while (true) {
            try {
                // ၁။ အရင်ဆုံး User ဆီက Data တောင်းမယ်
                System.out.print("Type Price (Type '0' Exit): ");
                price = sc.nextDouble();
                
                if (price == 0) break; // 0 ရိုက်ရင် program ပိတ်မယ်

                System.out.print("Quantity: ");
                qty = sc.nextInt();

                // ၂။ Data ရပြီဆိုမှ File ထဲကို သိမ်းမယ်
                try (FileWriter writer = new FileWriter("inventory.txt", true)) {
                    writer.write("Price: " + price + ", Qty: " + qty + "\n");
                    System.out.println("--- Finished Saving to File ---\n");
                } catch (IOException e) {
                    System.out.println("File Error: " + e.getMessage());
                }

            } catch (InputMismatchException e) {
                System.out.println("Error: Type Only Number");
                sc.nextLine(); // Buffer clear လုပ်မယ်
            }
        }

        System.out.println("Program Closed။ Check inventory.txt");
        sc.close();
    }
}