import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

interface Discountable {
    double calculateDiscount();
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
        return price * 0.1;
    }
}

// 1. Class နာမည်ကို 'Main' (M အကြီး) လို့ ပြင်ထားတယ်
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Book> myStore = new ArrayList<>();

        loadFromFile(myStore);

        while (true) {
            System.out.println("\n1. Entry | 2. Sorting | 3. Search | 4. Delete | 5. Exit");
            System.out.print("Choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); 

            if (choice == 5) break;

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
                myStore.sort((b1, b2) -> Double.compare(b2.getPrice(), b1.getPrice()));
                System.out.println("\n--- Total List ---");
                for (Book b : myStore) {
                    System.out.println(b.getTitle() + " - " + b.getPrice() + " Ks (" + b.getQuantity() + " book)");
                }
            } else if (choice == 3) {
                System.out.print("Searching Book: ");
                String keyword = sc.nextLine().toLowerCase();
                myStore.stream()
                    .filter(b -> b.getTitle().toLowerCase().contains(keyword))
                    .forEach(b -> System.out.println(b.getTitle() + " - " + b.getPrice() + " Ks"));
            } else if (choice == 4) {
                System.out.print("Delete Book Name: ");
                String target = sc.nextLine();
                if (myStore.removeIf(b -> b.getTitle().equalsIgnoreCase(target))) {
                    updateFile(myStore);
                    System.out.println("Deleted!");
                }
            }
        }
        sc.close();
    } // Main Method အပိတ်

    // Method တိုင်းမှာ 'static' ပါရပါမယ်
    public static void saveToFile(Book b) {
        try (FileWriter writer = new FileWriter("inventory.txt", true)) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            writer.write(now.format(fmt) + " | " + b.getTitle() + " | " + b.getPrice() + "\n");
        } catch (IOException e) { System.out.println("Error saving."); }
    }

    public static void loadFromFile(List<Book> list) {
        File f = new File("inventory.txt");
        if (!f.exists()) return;
        try (Scanner reader = new Scanner(f)) {
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(" \\| ");
                if (parts.length == 3) {
                    list.add(new Book(parts[1], Double.parseDouble(parts[2]), 1));
                }
            }
        } catch (Exception e) { }
    }

    public static void updateFile(List<Book> list) {
        try (FileWriter writer = new FileWriter("inventory.txt", false)) {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            for (Book b : list) {
                writer.write(LocalDateTime.now().format(fmt) + " | " + b.getTitle() + " | " + b.getPrice() + "\n");
            }
        } catch (IOException e) { }
    }
} // Class အပိတ်