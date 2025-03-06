# Условные операторы и циклы

## Условные операторы

Универсальный условный оператор выглядит так: `if (условие) {блок кода}` , не забываем про отступы в фигурных скобках. Джава вычислит условие в фигурных скобках, если возвращается true - выполнится код в фигурных скобках.
```java
class Main {
    public static void main(String[] args) {
        System.out.println("Hello !");

        int x = 10;
        if (x > 0) {
            System.out.println("X положительное");
        }

        System.out.println("Bye");
    } 
}
```
Это была простая форма, но есть и расширенная - после блока кода в фигурных скобках можем указать код, который выполнится в случае с false `if (условие) {блок кода} else {блок кода}`. Также можем указывать составные условия - но! тут не `and`/`or` , а `&&`/`||`. Также есть проверка на равенство/неравенство `==`, `!=`
```java
class Main {
    public static void main(String[] args) {
        System.out.println("Hello !");

        int x = 1;
        if (x >= 2 || x >= 1) {
            System.out.println("X положительное");
        } else {
            System.out.println("X отрицательное");
        }

        System.out.println("Bye");
    } 
}
```
В джаве все типы делятся на 2 больших семейства - примитивные и ссылочные типы. Их отличить можно по внешнему виду - если тип называется с маленькой буквы и у него нету никаких квадратных скобок в названии - то это примитивный тип, иначе - ссылочный. В данном блоке нас  интересует, как мы будем сравнивать значения этих типов на равенство. В примитивных типах данных сравнение идет через ==, но это неправильно для ссылочных типов данных. Там сравнение идет через `чтоСравниваем.equals(сЧемСравниваем)`
```java
class Main {
    public static void main(String[] args) {
        System.out.println("Hello !");

        // Тип начинается с заглавной буквы - поэтому это не 
        // примитивный тип данных
        String name = "Petya";
        if (name.equals("Olya")) {
            System.out.println("то же самое ");
        } else {
            System.out.println("не то же самое");
        }

        System.out.println("Bye");
    } 
}
```
Еще есть else if - когда нам нужно пробежаться по нескольким вариантам
```java
class Main {
    public static void main(String[] args) {
        System.out.println("Hello !");

        int x = 5;
        if (x == 2) {
            System.out.println("Плохо");
        } else if (x == 3) {
            System.out.println("удовл");
        } else if (x == 4) {
            System.out.println("хор");
        } else if (x == 5) {
            System.out.println("отл");
        } else {
            System.out.println("Некорректно");
        }               

        System.out.println("Bye");
    } 
}
```
Условный оператор switch (переключатель) - позволяет осуществлять сравнение попроще 
```java
class Main {
    public static void main(String[] args) {
        System.out.println("Hello !");

        int x = 2;       
        
        switch (x) {
            case 2:
                System.out.println("Плохо");
                break;
            case 3:
                System.out.println("3");
                break;                
            case 4:
                System.out.println("4");
                break;  
            case 5:
                System.out.println("5");
                break;                          
            default:  // если ни одно из условий не произошло
                break;
        }

        System.out.println("Bye");
    } 
}
```
Джава один раз вычиляет x, а потом ищет подходящий случай из case. break здесь для того чтобы джава вышла из блока - иначе она будет выполнять весь код, пока не встретит break (перепрыгнет на следующий case). Логика здесь следующая - избежать дублирование кода, так можно под условия 2 и 3 записать:
```java
        switch (x) {
            case 2:
            case 3:
                System.out.println("3");
                break;   
                // так мы с помощью break избежали дублирования кода
        }        
```
Еще один популярный условный оператор - тернарный условный оператор. Пусть у нас есть переменная `int x = 13;` и переменная y, в которыю мы хотим положить что то в зависимости от значения x. Через иф присвоить не получится - локальная переменная видна только в тех фигурных скобках, в которых она объявлена! Можем воспользоваться такой нотацией: `int yy = (x > 0) ? 1 : -1;`. `(условие) ? первоеЗначение : второеЗначение;`. если джава видит тернарный оператор - она просто должна вычислить из него какое то значение. Да - это первое значение, нет - второе.
```java
class Main {
    public static void main(String[] args) {
        System.out.println("Hello !");

        int x = 13;
        int y;
        
        if (x > 0) {
            y = 1;
        } else {
            y = -1;
        }

        int yy = (x > 0) ? 1 : -1;
        
        System.out.println(y);
        System.out.println(yy);
    } 
}
```

## Циклы

Есть цикл while `while (условие) {код}`
```java
class Main {
    public static void main(String[] args) throws Exception {

        int x = 10;
        while (x > 0) {
            x--;  // можно и x = x - 1 или x -= 1
            System.out.println("Hello !" + x);
            Thread.sleep(300);  // чтобы прервать код на 300 миллисекунд
        }
    } 
}
```
Тут `throws Exception` - это требование джавы чтобы можно было использовать паузы. Чтобы первать текущую итерацию и сразу перейти к следующей / прервать цикл:
```java
class Main {
    public static void main(String[] args) throws Exception {

        int x = 1;
        while (x > 0) {
            x++;  // можно и x = x - 1 или x -= 1
            if (x % 2 == 0) {
                continue;
            } else if (x == 17) {
                break;
            }
            System.out.println("Hello " + x);
            Thread.sleep(300);  // чтобы прервать код на 300 миллисекунд
        }
    } 
}
```
Еще есть цикл `do {блок кода} while (условие)` - это почти то же самое, только сначала выполняется блок кода, а затем проверяется условие. Так у нас точно выполнится по крайней мере одна итерация.

Иногда мы хотим проитерироваться с счетчиком. Для этого существует цикл `for`. Он состоит из 3 блоков `for (объявление счетчика; условие; изменение) {`. Так мы:
- не занимаем пространство имен счетчиком (не занимаем имя переменной)
- сразу в шапке цикла указываем, как будет вести себя счетчик
- даже если будет continue, у нас все равно отработает счетчик
```java
class Main {
    public static void main(String[] args) throws Exception {

        int x = 1;

        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
        // переменная i будет видна только внутри блока итераций!
    } 
}
```

## Общение с пользователем в цикле

Решим следующщую задачу - пользователю нужно будет вводить число, также он может ввести end чтобы закончить игру.
```java
import java.util.Scanner;

class Main {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        int sum = 0;

        while (true) {
            System.out.println("Введи число или end");
            String input = scanner.nextLine();

            if (input.equals("end")) {        
                break;
            }

            int x = Integer.parseInt(input);
            sum += x;
        }
        System.out.println("Твоя сумма " + sum);
    } 
}
```
- `Integer.parseInt` - принимает на вход текст, если там было записано число - функция вернет число