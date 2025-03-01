import java.util.Scanner;
import ru.netology.service.CustomsService;

public class Main {
 
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите цену товара (в руб.):");
        int price = scanner.nextInt();

        System.out.print("Введите вес товара (в кг.):");     
        int weight = scanner.nextInt();

        double tax_exact = CustomsService.calculateCustoms(price, weight);
        int tax = (int) tax_exact;
        System.out.print("Размер пошлины (в руб.) составит: " + tax + " руб.");
    }
}