import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
        
        // ၁။ Program စတာနဲ့ အရင်ရှိပြီးသား စာရင်းတွေကို ပြမယ်
        displayInventory();

        while (true) {
            System.out.print("\nNew Record? (y/n): ");
            String choice = sc.next();
            if (choice.equalsIgnoreCase("n")) break;

            try {
                System.out.print("Price: ");
                double price = sc.nextDouble();
                System.out.print("Quantity: ");
                int qty = sc.nextInt();

                // ဖိုင်ထဲ သိမ်းမယ်
                saveToFile(price, qty);
                
            } catch (Exception e) {
                System.out.println("Error, Type Only Number");
                sc.nextLine(); 
            }
        }
        sc.close();
    }

    // ၂။ ဖိုင်ထဲ သိမ်းတဲ့ Function
    public static void saveToFile(double price, int qty) {
        try (FileWriter writer = new FileWriter("inventory.txt", true)) {
            writer.write("Price: " + price + ", Qty: " + qty + "\n");
            System.out.println("Saved successfully.");
        } catch (IOException e) {
            System.out.println("Save Error: " + e.getMessage());
        }
    }

    // ၃။ ဖိုင်ထဲက Data တွေကို ပြန်ဖတ်ပြတဲ့ Function
    public static void displayInventory() {
        File myFile = new File("inventory.txt");
        if (!myFile.exists()) {
            System.out.println("No Previous Record");
            return;
        }

        System.out.println("--- Previous Record ---");
        try (Scanner fileReader = new Scanner(myFile)) {
            while (fileReader.hasNextLine()) {
                String data = fileReader.nextLine();
                System.out.println(data);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        System.out.println("--------------------------------------");
    }
}