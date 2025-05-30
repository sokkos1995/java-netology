import java.util.Scanner;

class Lecture03 {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        int sum = 0;

        while (true) {
            System.out.println("Введи число или end");
            String input = scanner.nextLine();

            if (input.equals("end")) {
                System.out.println("Конец игры");                
                break;
            }

            int x = Integer.parseInt(input);
            sum += x;
        }
        System.out.println("Твоя сумма " + sum);
    } 
}