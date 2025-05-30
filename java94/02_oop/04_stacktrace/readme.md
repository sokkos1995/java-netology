## Обработка ошибок в Java

Напишем команду, которая будет брать строку и возвращать ее 10й символ
```java
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello");

        String s = "Hello!!!!!!!!!!!!";
        char c = get10th(s);
        System.out.println("Ответ был: " + c);

        System.out.println("Bye");
    }

    public static char get10th(String s) {
        System.out.println("Начинаем извлечение символа");
        char c = s.charAt(10);
        System.out.println("Вынули символ");
        return c;
    }
}
```
Но если мы передаем слишком маленькую строку - программа упадет с ошибкой!
```
Exception in thread "main" java.lang.StringIndexOutOfBoundsException: String index out of range: 10
        at java.base/java.lang.StringLatin1.charAt(StringLatin1.java:48)
        at java.base/java.lang.String.charAt(String.java:711)
        at Main.get10th(Main.java:33)
        at Main.main(Main.java:25)
```
Что происходит? Команда `char c = s.charAt(10);` не может выполниться успешно, так как строка слишком короткая. Джава создает специальный объект, в который записывает информацию о произошедшем. Этот объект можно представлять себе просто в виде отчета об ошибке. После этого джава завершает программу и ввыводит информацию из отчета на экран, Разберем всю эту информацию
- `Exception in thread "main"` - возникла исключительная ситуация в потоке main (про потоки будет позже на курсе)
- `java.lang.StringIndexOutOfBoundsException` - класс объекта (отчета), который джава сформировала, когда мы попытались выполнить недопустимую операцию. Так как объект отчета - это обычный джавовский объект, у него конечно же есть свой класс, и именно его джава выведет в терминал
- `String index out of range: 10` - сообщение, которое сохранено в отчет
- `at java.base/java.lang.StringLatin1.charAt(StringLatin1.java:48)` - стектрейс  
Стектрейс - дело в том что во время выполнения программы джава хранит специальную стопку вызовов. Стопку вызовов удобно смотреть через отладчик кода (дебаггер). КОгда джава вызывает какой то метод, она берет и кладет информацию об этом вызове на стопку. Когда метод завершается (и если он завершается успешно) - джава просто выходит из этого метода, убирая его из стопки (убирает последний вызов).  
Разберем подробнее стэктрейс (путь среди стека). Джава показывает, где конкретно произошла ошибка. Так мы сможем узнать, именно в каком месте программа умерла. Читать эту информацию можно снизу вверх
- `at Main.main(Main.java:25)` - мы были в классе Main
- `at Main.get10th(Main.java:33)` - в методе Main.get10th на 33й строке
- `at java.base/java.lang.String.charAt(String.java:711)` - не сразу после вызова метода charAt у нас произошла ошибочная ситуация! Это обычный метод класса String. Он начал свою работу и где то внутри (во внутренних вызовах) джава поняла что не может выполнить код и начала процесс умирания.
- `at java.base/java.lang.StringLatin1.charAt(StringLatin1.java:48)` - там юбудет такой вот код
```java
    public static char charAt(byte[] value, int index) {
        if (index < 0 || index >= value.length) {
            throw new StringIndexOutOfBoundsException(index);
        }
        return (char)(value[index] & 0xff);
    }
```
Рассмотри процесс умирания программы поподробнее. КОгда джава понимает, что дальше ей идти некуда - она создает объект отчета об ошибке и после этого просто умирает, процесс умирания происходит не одномоментно. Джава будет пытаться выйти из всех вызовов, в которых она находится. При этом выходит она будет не через успешное завершение вызовов, а именно крича о том, что произошла ошибка. В конце концов джава выйдет из Main.main. Так как программа завершилась не штатно, джава возьмет отчет об ошибке и выведет его на экран.

Теперь посмотрим, как мы можем контроллировать этот процесс. Для этого существует специальная синтаксическая конструктция, которая называется блок try. Мы можем тот блок, в котором потенциально может произойти ошибка, заключить в блок try. После блока try следует блок catch - здесь в круглых скобках мы можем объявить ячейку, в которую положим отчет об ошибке. Мы говорим - если ошибка произошла, то не умирай, а передай управление нам, а мы разберемся. В круглых скобках мы объявляем ячейку, в которую мы положим объект с описанием ситуации.
```java
    public static void main(String[] args) {
        System.out.println("Hello");

        try {
            String s = "Hello";
            char c = get10th(s);
            System.out.println("Ответ был: " + c);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("ОШИБКА");
        }
    }
```
Если ошибки не будет - джава выполнит блок кода в try (catch проигнорирует), если будет - выполнит то что в catch. Джава в случае ошибки попытается выйти из Main.main ( потому что get10th завершилось с ошибкой), но встретит catch - это как фейс контроль определенных типов ошибок. В данном случае мы как раз и ловим все ошибки с отчетом типа StringIndexOutOfBoundsException. Джава увидит что мы именно для того блока кода, который обернут в try ищем и ловим именно ошибку этого типа. Тога джава прекратит процесс умирания, присвоит объект отчета в переменную e `StringIndexOutOfBoundsException e` и просто выполнит фигурные скобки, которые следуют за catch. ДЖальше джава продолжит выполнять код, как будто ничего и не случалось. Джава считает что если мы остановили отчет об умирании, то мы компетентны в том чтобы разобраться с этой ситуацией и ей никаких дальнейшим действий делать не требуется.  
Вместо StringIndexOutOfBoundsException можно сработать по принципам полиморфизма и указать какого то его родителя, например, `IndexOutOfBoundsException` или даже просто `Exception`.  
Блоков catch может быть несколько, также мы можем указать блок кода, который будет выполняться всегда (в любой ситуации, произойдет ошибка или нет). Пригождается когда, например, работаем с ресурсами (открываем файл, подключаемся к БД и тд)
```java
    try {
        String s = "Hello";
        char c = get10th(s);
        System.out.println("Ответ был: " + c);
    } catch (StringIndexOutOfBoundsException e) {
        System.out.println("ОШИБКА 1");
    } catch (ArrayIndexOutOfBoundsException e) {
        System.out.println("ОШИБКА 2");
    } finally {

    }
```
Работа с ресурсами - распространенная в джаве штука, так что джава позволяет объявлять их в круглых скобках после слова try! Указываем там переменные, куда кладем открытые ресурсы. Тогда блок finally не потребуется, джава сама закроет все открытые ресурсы.

## Иерархия классов ошибок

Все классы в джаве, которые могут использоваться в качестве ошибки, объединяет класс Throwable. Он является общим предком для этих типов (в частности, для типов Error и Exception). Сам класс Throwable почти никогда не используется - используются его наследними.  
Первым важным наследником класса Throwable является Error (обычно это ошибки самой jvm). Он в себе (в своих наследниках) объединяет все типы ошибок, которые являются критическими для нашей программы. Если такая ошибка произошла - лучше ее не ловить. По сути, лучше дать программе умереть. К таким ошибкам относится напримерп что у нас закончилась память.  
Другим важным наследником является RuntimeException и все виды Exception кроме него. Мы разделяем их на 2 таким группы. Группа ошибок, объединяемая наследниками RuntimeException используется для таких ошибок, которые джава не заставляет нас ловить. Эти ошибки обычно считаются ошибками программиста (как например ArrayIndexOutOfBoundsException - мы знаем какого размера этот массив и мы могли не допустить эту ошибку. Поэтому джава позволяет нам ее не обрабатывать, можно ничего не делать). Другие ошибки джава заставит нас ловить. Предполагается что это ошибки, которые не зависят от качества написания программы. Например, что делать, если моргнула сеть и тд (как бы идекально мы не написали программу, такая ошибка может возникнуть). Это ошибки среды и наша программа будет их обрабюатывать (по крайней мере, джава попытается заставить их обработать).

## Создание собственных ошибок

Спроектируем и напишем программу, которая принимает текст и оттуда вычленяет имя и фамилию. Дальше программа проверит, совпадают ли они по количеству букв.
```java
public class Main {

    public static void main(String[] args) {
        System.out.println(sameLength("Petrov"));
    }

    public static boolean sameLength(String text) { // "Petrov Petya"
        String[] parts = text.split(" "); // ["Petrov", "Petya"]
        String name = parts[1];
        String lastName = parts[0];
        return name.length() == lastName.length();
    }
}
```
Но если мы передадим туда какой то некорректный текст (например, без пробела ) - джава упадет с ошибкой, мы выйдем за пределы границ массива
```
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 1 out of bounds for length 1
        at Main.sameLength(Main.java:12)
        at Main.main(Main.java:4)
```
Нам как пользователям неочевидны причины падения - мы используем строки, а в ошибке речь про массив. Для этого существуют инструменты в джаве, которые позволят программе умереть, причем именно с той ошибкой, которая нам нужна. Спроектируем класс по другому, проверяя что у нам есть пробел
```java
        String[] parts = text.split(" ");
        if (parts.length != 2) {
            throw new RunimeException();
        }
        String name = parts[1];
        String lastName = parts[0];
        return name.length() == lastName.length();
```
Мы хотим, что если программа упадет с ошибкой - чтобы пользователь получал говорящее название ошибки для быстрого дебага. Чтобы программа умирала с определенной ошибкой - нам нужно сперва создать объект отчета об этой ошибке, после чего специальной конструкцией `throw new NameInputMismatchException(text);` сказать джаве - умирай. Сделав это мы увидим, что текст ошибки (и ее место) поменялись
```
Exception in thread "main" java.lang.RuntimeException
        at Main.sameLength(Main.java:10)
        at Main.main(Main.java:4)
```
Неинформативно - нету текста ошибки. Но у RuntimeException есть конструктор, принимающий текст. Этот текст как раз и будет сообщением об ошибке

Мы можем указать явнм образов что метод выкидывает такую ошибку при объявлении метода `public static boolean sameLength(String text) throws RuntimeException`. Делается это для того чтобы джава начала заставлять всех тех, кто будет вызывать этот метод, обрабаытвать эту ошибку (так мы говорим что этот метод МОЖЕТ (не обязательно) выкинуть такую ошибку. Мы говорим джаве что знаем что этот выкидывает такую ошибку - и мы в принципе согласны на такой вариант )

Мы можем и создать свой класс для ошибок
```java
public class NameInputMismatchException extends RuntimeException {

    public NameInputMismatchException(String text) {
        super("В параметре надо указать имя и фамилию, но было указано: " + text);
    }
}
```
Checked- и unchecked-исключения. Исключения делятся на два типа
- Checked (проверяемые) — исключения, которые обязательно нужно обработать или указать в описании метода, что в нём может возникнуть такого типа ошибка. Если этого не сделать, то компилятор не скомпилирует код и вернёт ошибку компиляции. Это всё исключения, наследуемые от Throwable и Exception.
- Unchecked (непроверяемые) — исключения, которые не обязательно обрабатывать, но они могут возникнуть во время выполнения программы, Это всё исключения, наследуемые от Error и RuntimeException

Для создания своего checked exception можно отнаследоваться от классов Throwalbe или Exception, а для создания unchecked exception — от класса RuntimeException. Исключения создаются, чтобы пользователь, который неправильно вызывает наш метод, получал говорящее сообщение об ошибке

В программе может возникнуть ситуация, когда ошибка в одном из методов создаст исключение в другом. В этом случае stacktrace будет выглядеть похожим образом.
```
javax.servlet.ServletException: Send message error
 at com.example.myproject.OpenSessionInViewFilter.doFilter(OpenSessionInViewFilter.java:60)
 at org.mortbay.jetty.servlet.ServletHandler$CachedChain.doFilter(ServletHandler.java:1157)
 at com.example.myproject.ExceptionHandlerFilter.doFilter(ExceptionHandlerFilter.java:28)
 at org.mortbay.thread.QueuedThreadPool$PoolThread.run(QueuedThreadPool.java:582)
Caused by com.example.myproject.MyProjectServletException
 at com.example.myproject.MyServlet.doPost(MyServlet.java:169)
 at javax.servlet.http.HttpServlet.service(HttpServlet.java:727)
 at javax.servlet.http.HttpServlet.service(HttpServlet.java:820)
Caused by org.hibernate.exception.ConstraintViolationException: could not insert: [com.example.myproject.MyEntity]
 at org.hibernate.exception.SQLStateConverter.convert(SQLStateConverter.java:96)
 at com.example.myproject.MyServlet.doPost(MyServlet.java:164)
 ... 32 more
Caused by java.sql.SQLException: Violation of unique constraint MY_ENTITY_UK_1: duplicate value(s) for column(s) MY_COLUMN
in statement [...]
 at org.hsqldb.jdbc.Util.throwError(Unknown Source)
 ... 54 **more**
```
Ошибка ServletException возникла из-за MyProjectServletException, которая произошла из-за ConstraintViolationException, а самой первой ошибкой, из-за которой прекратилась работа программы, была SQLException. В сообщении ошибки выделены Caused by

Stacktrace можно вывести у любого объекта Throwable и всех его потомков, например Exceptions, вызвав метод printStackTrace() или getStackTrace(). Такое бывает нужно, если мы хотим гарантировать вывод stacktrace, например, в terminal или console. Вывод stacktrace позволяет быстро понять, в каком методе произошла ошибка, без дополнительного вывода переменных, точек остановки программы и сообщений логирования.  При возникновении ошибки стоит первым делом посмотреть, что было выведено в stacktrace