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

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double price = 0;
        int qty = 0;

        while (true) {
            try {
                System.out.print("Please Price: ");
                price = sc.nextDouble(); // ဒီမှာ စာသားရိုက်ရင် InputMismatchException တက်မယ်

                System.out.print("Quantity: ");
                qty = sc.nextInt();
                
                break; // အကုန်မှန်မှ Loop ထဲက ထွက်မယ်
            } catch (InputMismatchException e) {
                System.out.println("Error: Please text number");
                sc.nextLine(); // Buffer ရှင်းထုတ်ပစ်ရမယ် (ဒါမှ နောက်တစ်ခါ ပြန်ရိုက်လို့ရမှာ)
            }
        }

        System.out.println("Input Received - Price: " + price + ", Qty: " + qty);
        sc.close();
    }
}
