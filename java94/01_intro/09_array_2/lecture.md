# Массивы многомерные

Очень помогают в проектировании пространства в различных двумерных игрушках.

## Основы работы с многомерными массивами

Одномерный массив интов - это просто набор из ячеек типа инт. Двумерный массив - это по сути набор из наборов (то есть в нашем случае массив из массивов интов)
```java
    public static void main(String[] args) {
        int[] arr = new int[3];
        int[][] arr2 = new int[3][];  // можно указать и вторую размерность - для вложенных массивов
        arr2[0] = new int[1];  // в ячейку 0 присвоим обычный интовый массив размером 1
        arr2[1] = new int[3];
        arr2[2] = new int[5];

        System.out.println(Arrays.toString(arr2));  // [[I@7fbe847c, [I@41975e01, [I@c2e1f26]
        System.out.println(Arrays.deepToString(arr2));  // [[0], [0, 0, 0], [0, 0, 0, 0, 0]]
    }
```
Джава выведет криво массивы через sout, так что для вложенных массивов стоит использовать `Arrays.deepToString`. Для обращения к ячейке следует воспользоваться двойной индексацией.

Можем и просто воспользоваться нотацией через фигурные скобки `int[][] arr = {{1, 2}, {1, 1, 1}}`

Попытаемся вывести двумерный массив так, чтобы это выглядело в виде таблицы. Для этого можно воспользоваться циклом.
```java
public static void main(String[] args) {
    int[] arr = new int[3];
    int[][] arr2 = {{1}, {2, 3, 4}, {5, 6, 7, 8, 9}};
    for (int i = 0; i < arr2.length; i++) {
        for (int j = 0; j < arr2[i].length; j++) {
            System.out.print(arr2[i][j] + " ");  // для вывода в строку, без переноса
        }
        System.out.println();
    }
}
```

## Практическое задание «Создание игры Крестики нолики»

Числа в коде писать напрямую - плохая практика, так что из предпочтительно выносить (тут это `char[][] field = new char[SIZE][SIZE];`, `public static final int SIZE = 3;`), final - частое решение для параметров программы (числа, текст, специальный символ).

`(isCrossTurn ? "крестики" : "нолики")` - тернарный условный оператор

```java
import java.util.Scanner;

public class Main {
    public static final int SIZE = 3;
    public static final char EMPTY = '-';
    public static final char CROSS = 'X';
    public static final char ZERO = '0';

    public static void main(String[] args) {
        char[][] field = new char[SIZE][SIZE];
        // заполняем дефолтными значениями `-`
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                field[i][j] = EMPTY;
            }
        }

        Scanner scanner = new Scanner(System.in);
        boolean isCrossTurn = true;

        while (true) {
            System.out.println("Ходят " + (isCrossTurn ? "крестики" : "нолики") + "!");
            printFiled(field);
            String input = scanner.nextLine(); // "1 2"
            String[] parts = input.split(" "); // ["1", "2"]
            int x = Integer.parseInt(parts[0]) - 1;  // минусуем чтобы не сбить нумерацию
            int y = Integer.parseInt(parts[1]) - 1;

            if (field[x][y] != EMPTY) {
                // перескакиваем итерацию без смены состояния флага isCrossTurn
                continue;
            }
            field[x][y] = isCrossTurn ? CROSS : ZERO;  // в зависимости от флага isCrossTurn будут крестики или нолики

            if (isWin(field, isCrossTurn ? CROSS : ZERO)) {
                System.out.println("Выиграли " + (isCrossTurn ? "крестики" : "нолики") + "!");
                printFiled(field);
                break;
            } else {
                if (isCrossTurn) {
                    isCrossTurn = false;
                } else {
                    isCrossTurn = true;
                }
                //isCrossTurn = !isCrossTurn;
            }
        }
    }

    // ВНИМАНИЕ, ТОЛЬКО ДЛЯ 3x3
    public static boolean isWin(char[][] field, char player) {
        // отдельный статический метод, который проверяет, победил ли игрок
        if (field[0][0] == player && field[0][1] == player && field[0][2] == player)
            return true;
        if (field[1][0] == player && field[1][1] == player && field[1][2] == player)
            return true;
        if (field[2][0] == player && field[2][1] == player && field[2][2] == player)
            return true;

        if (field[0][0] == player && field[1][0] == player && field[2][0] == player)
            return true;
        if (field[0][1] == player && field[1][1] == player && field[2][1] == player)
            return true;
        if (field[0][2] == player && field[1][2] == player && field[2][2] == player)
            return true;

        if (field[0][0] == player && field[1][1] == player && field[2][2] == player)
            return true;
        if (field[2][0] == player && field[1][1] == player && field[0][2] == player)
            return true;

        return false;
    }

    public static void printFiled(char[][] field) {
        // так как мы поле выводим на экран часто - можем вывести в отдельный метод

        // тут можно использовать foreach
        for (char[] row : field) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}
```