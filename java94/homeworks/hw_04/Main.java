import java.util.Scanner;

class Main { 

    public static int earnings = 0;
    public static int spendings = 0;

    public static int taxEarnings(int earnings) {
        int tax = earnings * 6 / 100;
        return tax;
    }

    public static int taxEarningsMinusSpendings(int earnings, int spendings) {
        int tax = (earnings - spendings) * 15 / 100;
        if (tax >= 0) {
           return tax;
        } else {
           return 0;
        }
    }

    public static void chooseSystem(int earnings, int spendings) {
        int systemEarnings = taxEarnings(earnings);
        int systemEarningsMinusSpendings = taxEarningsMinusSpendings(earnings, spendings);

        if (systemEarnings > systemEarningsMinusSpendings) {
            System.out.println("Мы советуем вам УСН доходы минус расходы");
            System.out.println("Ваш налог составит: " + systemEarningsMinusSpendings + " рублей");
            System.out.println("Налог на другой системе: " + systemEarnings + "  рублей");
            System.out.println("Экономия: " + (systemEarnings - systemEarningsMinusSpendings) + "  рублей");
        } else if (systemEarnings < systemEarningsMinusSpendings) {
            System.out.println("Мы советуем вам УСН доходы");
            System.out.println("Ваш налог составит: " + systemEarnings + " рублей");
            System.out.println("Налог на другой системе: " + systemEarningsMinusSpendings + "  рублей");
            System.out.println("Экономия: " + (systemEarningsMinusSpendings - systemEarnings) + "  рублей");
        }
        else {
            System.out.println("Можете выбрать любую систему налогообложения");
            System.out.println("Ваш налог составит: " + systemEarnings + " рублей");
        }
    }

    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(System.in);
        String moneyStr = "";
        int money;

        while (true) {
            System.out.println("""
            \nВыберите операцию и введите её номер:
            1. Добавить новый доход
            2. Добавить новый расход
            3. Выбрать систему налогообложения                
            """
            );            
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    System.out.println("Введите сумму дохода:");
                    moneyStr = scanner.nextLine();
                    money = Integer.parseInt(moneyStr);
                    earnings += money;
                    break;
                case "2":
                    System.out.println("Введите сумму расхода:");
                    moneyStr = scanner.nextLine();
                    money = Integer.parseInt(moneyStr);                    
                    spendings += money;
                    break;                
                case "3":
                    chooseSystem(earnings, spendings);
                    break;
                case "end":
                    break;
                default:
                    System.out.println("Такой операции нет");
            }

            if (input.contentEquals("end")) {
                System.out.println("Программа завершена!");
                break;
            };
        }
    } 
}
