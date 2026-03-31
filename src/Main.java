import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;



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
    public int getQuantity() { return quantity; }

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
        List<Book> myStore = new ArrayList<>();

        // ၁။ ဖိုင်ထဲက data တွေကို List ထဲအရင်ပြန်ဖတ်ထည့်မယ် (ဒါမှ စီလို့ရမှာ)
        loadFromFile(myStore);

        while (true) {
            System.out.println("\n1. New Entry List | 2. Sorting Check List | 3. Exit");
            System.out.print("Choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); 

            if (choice == 3) break;

            if (choice == 1) {
                System.out.print("Title: ");
                String title = sc.nextLine();
                System.out.print("Price: ");
                double price = sc.nextDouble();
                System.out.print("Quantity: ");
                int qty = sc.nextInt();

                Book newBook = new Book(title, price, qty);
                myStore.add(newBook);
                saveToFile(newBook);
            } else if (choice == 2) {
                // ၂။ စာရင်းကို ဈေးနှုန်းအလိုက် စီမယ် (Higher price first)
                myStore.sort((b1, b2) -> Double.compare(b2.getPrice(), b1.getPrice()));
                
                System.out.println("\n--- Total List ---");
                for (Book b : myStore) {
                    System.out.println(b.getTitle() + " - " + b.getPrice() + " Ks (" + b.getQuantity() + " book)");
                }
            }
        }
        sc.close();
    }

    // ၃။ အချိန်နဲ့တကွ ဖိုင်ထဲသိမ်းတဲ့ Function
    public static void saveToFile(Book b) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = now.format(formatter);

        try (FileWriter writer = new FileWriter("inventory.txt", true)) {
            writer.write(formattedDate + " | " + b.getTitle() + " | " + b.getPrice() + "\n");
            System.out.println("Saved at " + formattedDate);
        } catch (IOException e) {
            System.out.println("Save Error.");
        }
    }

    // ၄။ ဖိုင်ထဲက data တွေကို Memory (List) ထဲ ပြန်တင်တဲ့ Function
    public static void loadFromFile(List<Book> list) {
        File f = new File("inventory.txt");
        if (!f.exists()) return;

        try (Scanner reader = new Scanner(f)) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                // ဥပမာ- 2026-03-30 | Java | 15000.0 ဆိုတဲ့ စာသားကို ခွဲထုတ်မယ်
                String[] parts = line.split(" \\| ");
                if (parts.length == 3) {
                    list.add(new Book(parts[1], Double.parseDouble(parts[2]), 1));
                }
            }
        } catch (FileNotFoundException e) { }
    }
}