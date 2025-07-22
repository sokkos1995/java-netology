import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static String[] operations = {"Выход из программы", "Добавить дело", "Показать дела", "Удалить дело по номеру", "Удалить дело по названию", "Удалить дело по ключевому слову"};
    private static List<String> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int operationId;

        while (true) {
            operationId = getOperationId();
            System.out.println("Выбрана операция: " + operations[operationId]); 
            
            switch (operationId) {
                case 0:
                    return;
                case 1:
                    System.out.print("Введите название дела: ");
                    String taskName = scanner.nextLine();
                    addTask(taskName);
                    break;
                case 2:
                    getTasks();
                    break;
                case 3:
                    System.out.print("Введите номер дела: ");
                    int taskId = scanner.nextInt();
                    removeTaskById(taskId);
                    break;
                case 4:
                    System.out.print("Введите название дела: ");
                    String taskNameToRemove = scanner.nextLine();
                    removeTaskByName(taskNameToRemove);
                    break;
                case 5:
                    System.out.print("Введите ключевое слово: ");
                    String keywordToRemove = scanner.nextLine();
                    removeByKeyword(keywordToRemove);
                    break;                    
            }
            System.out.println("");
        }

    }

    public static int getOperationId() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите операцию:");
        for (int i = 0; i < operations.length; i++) {
            System.out.println(i + ". " + operations[i]);
        }
        System.out.print("Ваш выбор: ");
        return scanner.nextInt();
    }

    public static void addTask(String taskName) {
        if (! tasks.contains(taskName)) {
            tasks.add(taskName);
            System.out.println("Добавлено!");
        }
        getTasks();
    }    

    public static void getTasks() {
        System.out.println("Ваш список дел:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ". " + tasks.get(i));
        }        
    }

    public static void removeTaskById(int id) {
        if (tasks.size() > id) {
            tasks.remove(id - 1);
            System.out.println("Удалено!");
        }
        getTasks();
    }
    
    public static void removeTaskByName(String taskName) {
        if (tasks.contains(taskName)) {
            tasks.remove(taskName);
            System.out.println("Удалено!");
        getTasks();  
        }        
    }    

    public static void removeByKeyword(String keyWord) {
        List<String> taskToRemove = new ArrayList<>();

        for (String s : tasks) {
            String[] wordsInTask = s.split(" ");
            for (String ss : wordsInTask) {
                if (ss.toLowerCase().contains(keyWord.toLowerCase())) {
                    taskToRemove.add(s);
                    break;
                }
            }
        }          
        tasks.removeAll(taskToRemove);  
        getTasks();  
    }        
}     