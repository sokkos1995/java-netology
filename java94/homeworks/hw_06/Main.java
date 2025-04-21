import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String[] products = {"Хлеб", "Яблоки", "Молоко"};
        int[] prices = {100, 200, 300};
        int[] productsСart = new int[3];

        Scanner scanner = new Scanner(System.in);

        System.out.println("Список возможных товаров для покупки");
        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + " - " + products[i] + " - " + prices[i] + " руб.");
        }        

        while (true) {
            System.out.println("Выберите товар и количество или введите `end`");

            String input = scanner.nextLine();

            if (input.equals("end")) {
                break;
            }

            String[] product = input.split(" ");
            int productNumber = Integer.parseInt(product[0]) - 1;
            int productCount = Integer.parseInt(product[1]);

            if (productNumber >= 0 && productNumber < products.length) {
                productsСart[productNumber] += productCount;
            }
        }

        System.out.println("Ваша корзина:");
        int totalPrice = 0;
        for (int i = 0; i < products.length; i++) {
            if (productsСart[i] > 0) {
                System.out.println(products[i] + ' '
                + productsСart[i] + " шт "  
                + prices[i] + " руб/шт "  
                + productsСart[i] * prices[i] + " руб в сумме");
                totalPrice += productsСart[i] * prices[i];
            }       
        }
        System.out.println("Итого " + totalPrice + " руб");  
    }
}