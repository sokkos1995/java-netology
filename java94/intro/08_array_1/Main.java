import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] temps = new int[30];
        boolean[] isFilled = new boolean[30];
        while (true) {
            System.out.println("Введи два числа: дата температура");
            String input = scanner.nextLine(); // "11 31"
            if ("end".equals(input)) {
                break;
            }

            String[] parts = input.split(" "); // ["11", "31"]
            int day = Integer.parseInt(parts[0]) - 1;
            int t = Integer.parseInt(parts[1]);
            temps[day] = t;
            isFilled[day] = true;

            // тип данных double чтобы при делении получалось дробное число
            double sum = 0;
            for (int tt : temps) {
                sum += tt;
            }
            int cnt = 0;
            for (boolean f : isFilled) {
                if (f) {
                    cnt++;
                }
            }
            System.out.println("Средняя температура: " + (sum / cnt));
        }
    }
}
