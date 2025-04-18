## Что такое программирование

Компиляция - перевод из нашего ЯП в машинный код. Компилятор - программа-переводчик. Альтернативный процесс - интерпретация (аналог - синхронный перевод).  
Особенности компилятора
- высокое качество перевода программы
- Разные компьютеры понимают разный машинный код, поэтому для каждого машинного языка требуется отдельная компиляция программы.  
Особенности интерпретатора
- перевод в машинный язык осуществяет сам компьютер при помощи интерпретатора
- медленный и некачественный перевод

Подход Java

Java придумали свой единый машинный код (который не понимает ни один компьютер) - байткод (bytecode) (набор инструкций, исполняемый виртуальной машиной Java). Когда мы пишем нашу программу на языке Java, мы прежде всего берем компилятор и переводим ее в байткод. Далее уже скомпилированный в байткод вариант мы уже отправляем на тот компьютер, на котором мы хотим запустить программу. Этот компьютер байткод не понимает (как и ни один другой компьютер), но там будет встроенный интерпретатор этого байткода (синхронный переводчик, байткод в машинный язык). Машинный язык (байткод) в машинный язык будет переводиться достаточно быстро и точно.

## JVM, JRE, JDK

Основные инструменты для работы с Java-программой:
- Java Virtual Machine (JVM) — это программа-интерпретатор байткода, позволяющая запускать программу на целевом компьютере. По сути, эта команда притворяется компьютером, понимающим байткод (на самом деле синхронно переводит в машинный код того компьютера, на котором запущена).  
Другие языки (Kotlin, Scala, Groovy и другие) могут компилироваться в байткод, чтобы запускаться на JVM и даже быть частью Java-программ
- Java Runtime Environment (JRE) (рабочее окружение Java) — это совокупность JVM, стандартной библиотеки и прочих инструментов, необходимых для исполнения программы. Очень часто нашей программе может требоваться обращение к каким то стандартным вещам, которые она не хочет тащить с собой.
- Java Development Kit (JDK) — это набор инструментов разработки, которые нужны для программирования на Java. Сюда будет входить все чтобы запустить программу плюс те инструменты, которые не нужны для работы программы, но нажны для разработки (например, тот же самый компилятор в байткод)

## Создание программы на Java и локальные переменные

```java
class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```
Все что в фигурных скобках после `class Main` - это содержимое класса. 
- `System.out.println` - название команды в Java, которая приказывает ей вывести что то на экран
- `"Hello, World!"` - текст, который выведем
- `;` - в конце обязательно ставим! Это помогает джаве ориентированться, где же на самом деле закончилась программа.

Для запуска программы нам сначала надо ее скомпилировать в байткод, а затем запустить интерпретатор для этого байткода.

Чтобы оперировать любыми значениями в Java, нужно создать переменные. Это способ связи строкового именования (имени переменной) со значением в оперативной памяти компьютера, которое может менять значение в процессе работы программы.  
Локальные переменные - это просто ячейка текста, которую мы просим у Java выделить для нас. Для этого мы указываем тип (что мы собираемся там хранить) и название.
```java
public static void main(String[] args) {
    int x ;  // просто объявляем переменную
    int y = 5;  // объявляем переменную и присваиваем ей значение
    x = 3;  // присваиваем значение переменной после объявления

    double d = 3.5;  // x не даст выбрать, тк была уже занята ранее!

    String name = "Petr";  // String с большой буквы!

    int z = x + 1;  // объявляем переменную и присваиваем ей значение существующей переменной
}
```
По именованию переменных - java позволяет нам использовать буквы латинского алфавита, цифры (но нельзя в самом начале), знаки подчеркивания, $ . Но есть правила форматирования кода
- цифры чаще всего пишутся в конце
- название переменной из нескольких слов - кэмелкейс(`myBestScore`)
- локальные переменные всегда начинаются с маленькой буквы

Java — строго типизированный язык. Это значит, все переменные на этапе компиляции должны иметь свой определённый тип, например, числа или строки (тип переменной невозможно изменить)

Знания, необходимые для программирования на Java
- Синтаксис языка и типы данных
- Основные конструкции
- Как запускать программу
- Как находить и исправлять ошибки  
Конечно, чтобы писать программы для мобильных платформ или веб-приложения, необходимо дополнительно разбираться в работе конкретной системы или так называемых фреймворках.  
Фреймворк (framework) — специально подготовленные шаблоны для разработки программ


```java
class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        System.out.println(2 + 3);
        System.out.println("Hello " + "World!");  // конкатенация

        int x ;  // просто объявляем переменную
        int y = 5;  // объявляем переменную и присваиваем ей значение
        x = 3;  // присваиваем значение переменной после объявления        
        System.out.println(x);

        String name = "Petr";  // String с большой буквы
        System.out.println("Hello " + name);
    }
}
```