import java.util.Scanner;

class Task01 {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int days = 0;

        System.out.println("Введите год в формате \"yyyy\"");
        int year = scanner.nextInt();

        if (year % 400 == 0) {
            days = 366;
        } else if (year % 100 == 0) {
            days = 365;
        } else if (year % 4 == 0) {
            days = 366;
        } else {
            days = 365;
        }

        System.out.println("Количество дней " + days);
    }
}