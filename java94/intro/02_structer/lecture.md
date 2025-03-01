# Структура программы

## Создание и вызов статического метода

Вынесем блок кода, который генерирует отступы, в отдельную команду
```java
class Main {

    public static String name;

    public static void printDelim(String title) {
      System.out.println();
      System.out.println();
      System.out.println("^^^^^^^^^^^^^^^^^^^");
      System.out.println(title);
      System.out.println("___________________");
    }

    public static void main(String[] args) {
        String title = "Демография";  // это локальная переменная
        // она существует только для тех фигурных скобок, в которых она определена
    }
}
```
Вызов этой команды затем будет осуществляться так: `printDelim();`. так мы избегаем лишнего дублирования кода. Такой вид команд называется статическим методом

Здесь:
- `String title = "Демография"; ` - это локальная переменная, она существует только для тех фигурных скобок, в которых она определена. 
- `public static void printDelim();` - нейминг у статических методов такой же, как у переменных
- `printDelim(String title)` - мы можем передавать в статические методы какие то данные на вход. Мы объявляем ячейку (по аналогии с локальной переменной), в которую джава положит переданные данные для работы команды. String - тип, title - название. Эта ячейка будет жить только пока статический метод выполняется, после выполняения ячейка уничтожится. Таким образом она является локальной (только локальной к запуску команды). Такой вид ячейки называется параметром

Что происходит, когда мы нажимаем на run (запускаем код):
- джава ищет ту магическую фразу, начиная с которой она должна начать выполнение нашей программы (`public static void main(String[] args)`). Джава заходит туда и начинает построчно выполнять команду.

Теперь напишем статический метод, который принимает 2 числа на вход и выводит их произведение
```java
    public static void printMult(int a, int b) {
      int result = a * b;
      System.out.println(a + " x " + b + " = " + result);
    }
```

Команда - это не только кусок кода, который будет выполняться. Команда также может в результате выполнения отдать какое то значение.
```java
    public static int calcCredit(int amount, int months, double rate) {
        System.out.println("Кредит: " + amount);
        System.out.println("Срок: " + months);
        System.out.println("Процент: " + rate);
        int monthlyPayment = amount / months; // переменная для ежемесячного платежа
        // System.out.println("Платёж: " + monthlyPayment);

        return monthlyPayment;
    }
```
Здесь 
- `public static int` - возвращаем инт! `void` - этим словом регулируется, отдаем ли мы какое то значение вызывающему или нет. `void` - команда отрабатывает и все
- `return` - что, собственно, отдаем

Еще есть такая штука как глобальные переменные - когда мы хотим чтобы переменная была видна отовсюда, во всех методах класса
```java
public static String name;
```
Но это не очень хорошо, поскольку не будет понятно, когда какая команда какие ячейки использует. Такие глобальные ячейки называются статическими полями. 

## Классы и импорт пакетов

Мы можем структурировать нашу программу в разные файлы, это позволит нам не раздувать единственный файл, а аккуратно разложить наш код. Название файла совпадает с названием класса. Для всех классов кроме Main лучше писать слово public вначале - это повысить доступность кода для других файлов в программе.

Когда мы разбиваем код на разные классы, нам потребуется указать, в каком классе искать тот или иной метод.  
Файл Main.java
```java
class Main {
    public static void main(String[] args) {
    //   printMult(11, 54); // 11 x 54 = 594
      int p1 = A.calcCredit(1000000, 12, 9.9);
      int p2 = A.calcCredit(10000, 12, 29);
      System.out.println("Общая сумма: " + (p1 + p2));
    }
}
```
файл A.java
```java
public class A {
    public static void printDelim(String title) {
      System.out.println();
      System.out.println();
      System.out.println("^^^^^^^^^^^^^^^^^^^");
      System.out.println(title);
      System.out.println("___________________");
    }
  
    public static void printMult(int a, int b) {
      int result = a * b;
      System.out.println(a + " x " + b + " = " + result);
    }
  
    public static int calcCredit(int amount, int months, double rate) {
      int monthlyPayment = amount / months; 
      return monthlyPayment;
    }
}
```
Теперь мы можем логически группировать файлы. 

Иногда имена файлов могут совпадать. В этом случае мы можем ввести полной имя - это называется пакеты. Создаем файл `./my/fav/code/B.java`, в нем пишем код:
```java
package my.favourite.code;

public class B {
  public static void printMe() {
    System.out.println("Hello");
  }
}
```
Просто создать папки недостаточно! Нам необходимо прописать `package my.favourite.code;`. Ну и в самом Main не забываем добавить импорт
java
```java
import my.favourite.code.B;

class Main {

  public static void main(String[] args) {
    int p1 = A.calcCredit(1000000, 12, 9.9);
    int p2 = A.calcCredit(10000, 12, 29);
    System.out.println("Общая сумма: " + (p1 + p2));

    B.printMe();
    // можно обойтись и без импорта, но тогда нужно будет указывать полный путь
    // my.favourite.code.B.printMe();
  }
}
```

Есть определенное правило нейминга:
```java
// слева направо, затем имя проекта javabasics и дальше все более и более уточнять
// так мы гарантируем что нет совпадений с людьми вне нашего проекта и вне нашего сайта
package re.netology.javabasics
```

## Вывод и чтение данных из консоли

System.out.println выводит текст на экран, но иногда нам нужно и принимать что то на вход от пользователя. Для этого нам понадобится такой инструмент как сканер. Сканер - это объект, то есть нам нужно будет создать ячейку, в которую мы его положим, этой ячейкой будет служить локальная переменная. Для сканнеров есть специальный тип `java.util.Scanner`
```java
import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Введи число");
    int x = scanner.nextInt();  // допустим, мы введем 5 3.5 Имя
    // дефолтный разделитель - пробел, так что 5 пойдет в первую переменную
    // 3.5 - в d, Имя - в s
    System.out.println("Введи дробное число");
    double d = scanner.nextDouble();
    System.out.println("Введи любой текст");
    String s = scanner.next();
    String ss = scanner.nextLine();  // так мы считаем строку полностью
    // этот вид сканера плохо сочетается с другими next
    System.out.println("Было введено: " + x);
    System.out.println("Ввёл: " + d);
    System.out.println("Ввёл: " + s);
  }
}
```
Здесь:
- `scanner.nextInt()` - указание что будет введено число. 
- `scanner.next()` - считает текст до следующего разделителя (пробела)
- `scanner.nextLine()` - считает текст до следующего разделителя (перенос строки). Плохо сочетается с другими next, так что нужно определиться, что мы используем - либо nextLine, либо next