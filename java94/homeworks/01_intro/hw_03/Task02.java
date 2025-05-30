import java.util.Scanner;

class Task02 {
    public static int getDays(int year) {
        int days = 0;

        if (year % 400 == 0) {
            days = 366;
        } else if (year % 100 == 0) {
            days = 365;
        } else if (year % 4 == 0) {
            days = 366;
        } else {
            days = 365;
        }        
        return days;
    }
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int points = 0; 

        while (true) {
            System.out.println("Введите год в формате \"yyyy\"");
            int yearInput = scanner.nextInt();
            System.out.println("Введите количество дней");
            int daysInput = scanner.nextInt();       

            if (daysInput == getDays(yearInput)) {
                points++;
            } else {
                System.out.println("Неправильно! В этом году " + getDays(yearInput) + " дней!");
                break;
            }
        }

        System.out.println("Набрано очков: " + points);
    }
}