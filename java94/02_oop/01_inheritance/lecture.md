# Модификаторы доступа, наследование

## Инкапсуляция

Какие модификаторы доступов есть в джаве и как они помогают нам проектировать классы и поведение объектов. Создадим новый класс Person, поля в нем будут проектировать ту память, которую он должен будет в себе содержать, а методы - будут проектировать его поведение.
```java
public class Person {
    public String name;
    public int age;

    public boolean isTooYoung() {
        if (age < 18) {
            return true;
        } else {
            return false;
        }
    }
}
```
Этих вещей будет достаточно чтобы создавать переменные такого типа, объекты такого типа и обращаться к его полям
```java
public class Main {

    public static void main(String[] args) {
        Person person = new Person();
        person.name = "Petya";
        person.age = 8;
        System.out.println(person.isTooYoung());
    }
}
```

Как мы знаем, джава - это язык для объектно-ориентированного программирования, то есть программа и будет в основном состоять из различных объектов, которые взаимодействуют между собой через вызов команд и обращение к их полям. Например, когда мы вызываем метод `person.isTooYoung()`, мы заглядываем в переменную person, в которой находится адрес нашего человека. Точка означает что мы идем по этому адресу. isTooYoung() - означает "вызови команду isTooYoung".  
Как работает ООП: когда мы вызывали команду isTooYoung, мы не говорили, как ее должен выполнять человек. По сути, мы пришли к самому объекту person и попросили выполнить эту команду, а как это уже делать - решает сам человек, глядя на свой класс. Мы хотим видеть программу в виде набора корректных объектов, которые взаимодействуют между собой. Однако мы видим сразу проблему. Дело в том что когда мы указали age - мы можем туда класть любые значения, которые захотим, которые просто подходят пож тип инт. Например, `person.age = -8;`. Однако мы понимаем, что чисто физически мы это можем сделать (тк возраст - это инт), но логическуи мы сломаем объект. Это происходит потому что у объекта нет полного контроля над своими полями, все что он делаем - это говорит какой тип значения можно туда класть. Мы не можем туда положить текст или дробное число, только инт.  
Решитб проблему можно следующим образом. Мы можем договориться чтобы никто нарпямую не мог обращаться к полям. ЕСли кому то нужно поменять какие то данные у объекта - пусть он подойдет к объекту и попросит поменять. Объект уже сам решит, нужно лди менять значение или нет. Поэтому договоримся что не будет обращаться к полям этого класса вне этого класса. ВНутри этого класса можно это делать, поскольку код этого класса написан автором этого типа данных, автор может спокойно проверять все условия. ПОтребителям же (тем, кто будет работать с объектами этого класса) мы запретим обращаться напрямую к полям.  
Что делать если мы хотим чтобы люди могли менять возраст? Для этого можно создать специальный метод, единственная задача которого - поменять возраст. Обычно такой метод называют следующим образом `setНазваниеЧегоМыХотимПоменять`, например, `setAge`:
```java
    public void setAge(int age) {
        if (age < 0 || age > 200) {
            return;
        }
        this.age = age;
    }
```
void - потому что мы ничего не возвращаем! Теперь менять значение можно следующим образом `person.setAge(40);`. Раньше мы напрямую лезли в память объекта и меняли там значения. Теперь же мы приходим к объекту и просим его запомнить новый возраст. Такой подход решает нашу проблему тем, что мы можем навесить дополнительные проверки. Предварительная проверка внутри метода на какие то условия с выходом если эти условия не подходят - называется `early exit`. Такой метод называется сеттер.  
Для проверки возраста создадим специальный метод (ведь напрямую к полям мы договорились не обращаться). Этот метод будет называться геттер, он ничего не принимает - просто возвращает нам нужное значение
```java
    public int getAge() {
        return age;
    }
```
Еще раз подчернем ключевое отличие - раньше мы напрямую лезли в память объекта и меняли там значение, нас ничего не ограничивало кроме типа данных. Теперь же мы используем объектно-ориентированный подход - мы идем к человеку и просим его самого выполнить команду поменять возраст на новый. Объект сам решает, как ему это делать и делать ли вообще. Объект может и не скипать, а выкинуть ошибку, вывести лог в консоль.  
Теперь мы можем запретить доступ к полям более технически, тк до этого все держалось на договоре. Для этого уберем слово `public` - это называется модификатор доступа. Это специальное слово, которое контроллирует доступ к членам класса. Членами класса являются поля, методы и конструкторы. Слово public означает что мы имеем доступ к этому члену класса из любого места программы. Если же мы поставим слово `private`, то джава будет следить что к этому полю мы можем обратиться только в том келассе, в котором мы его объявляем. Таким образом мы сокращаем область видимости нашего поля. Так мы сможем скрыть поля от тех, кто будет использовать наши объекты. Мы заставим всех использовать именно наши методы, которые мы создали у объекта для работы с этими данными. Если мы ничего не укажем - то будет уролвень доступа по умолчанию (Джава разрешает обращаться членам класса без модификатора доступа в рамках самого класса, а также из всех мест, которые находятся в том же пакете, что и этот класс). Это правило базируется на том что обычно классы в одном пакете пишут одни и те же авторы. Они между собой могут договориться о правильном использовании полей.
```java
public class Person {
    private String name;
    private int age;
}
```
В Java используются следующие модификаторы доступа:
- public: публичный, общедоступный класс или член класса. Поля и методы, объявленные  с модификатором public, видны другим классам из текущего пакета и из внешних пакетов
- private: закрытый класс или член класса. Противоположность модификатору public. Доступен только из кода в том же классе
- protected: такой класс или член класса доступен из любого места в текущем классе, пакете или  в классах-наследниках, даже если они находятся в других пакетах
- default: модификатор по умолчанию. Когда мы не пишем модификатор доступа, он по умолчанию имеет значение default. Такие поля или методы видны всем классам в текущем пакете

Ограничивая доступ к полям объекта (его собственной памяти) и делая это так что работу с данными мы делаем черекз его команды (идем к объекту и просим его что то сдлетаь), мы делаем наш подход более объектно-ориентированным, сам такой подход называется инкапсуляцией.

Инкапсуляция - механизм обертывания данных и кода, работающего с данными, в одно целое. Сокрытие каких то данных и вместо этого предоставление методов для работы с этими данными.

## Наследование полей и методов

Представим себе, что у нас уже есть класс Person, который олицетворяет в себе объекты людей. Создадим еще один класс, но уже для певцов. По идее у певцов уже должны и так быть все характеристики, которые есть у человека. Теоретически мы могли бы взять, скопировать код, который мы написали для людей и вставить его напрямую для певцов. Чтобы избежать копипасты в джаве есть механизм наследования. По сути мы говорим что хотим в качестве основы для класса Singer использовать другой класс Person. Для этого есть команда extends (расширяет). В такой ситуации класс Person будет называться предком, а Singer - потомком (дочерним классом).
```java
public class Singer extends Person {
    public int rating;

    public void printRating() {
        System.out.println(rating);
    }

    public void sing(String verse) {
        System.out.println("Я пою: " + verse);
    }
}
```
То, что мы отнаследовались, напрямую на класс Person влиять не будет. Все спецэффекты будут именно в дочернем классе Singer. Для того, чтобы добавить новую команду в классе Singer, достаточно просто объявить новый метод внутри класса (см выше printRating, sing)
```java
public class Main {

    public static void main(String[] args) {
        Person person = new Person();
        person.setAge(40);
        System.out.println(person.getAge());

        Singer singer = new Singer();
        singer.setAge(10);
        singer.sing("Good morning!");

        // person.sing("Good morning!");  // у person нет такого метода!
    }
}
```
Мы можем не только добавлять новые команды нашему дочернему классу - мы можем и добавлять новую память. Изначально от предка нам достались имя и возраст, внутри дочернего класса мы добавили еще и рейтинг.  
При наследовании мы можем не только объявлять новые сущности в виде новы полей или методов. Мы можем и переопределить какой нибудь метод. Для этого чтобы понять какая версия будет выполняться - нам нужно понять философию ООП. При вызове метода объект идет в свой тип и ищет эту команду. Если не находит - идет искать у предка
```java
    public void happyBirthday() {
        age++;
        System.out.println("Я пою себе с др!");
    }
```
Тут по сути мы просто перезаписали метод happyBirthday() у предка. Если же мы хотим вызвать ту версию, которая пришла в наследство - мы должны в начале указать super (это слово в джаве обычно означает вашего предка, вашего отца):
```java
    public void happyBirthday() {
        super.happyBirthday();
        age++;
        System.out.println("Я пою себе с др!");
    }
```
Какая есть тонкость - допустим, мы опечатались в названии метода, happyBirhday. Такую ошибку тяжело поймать, ведь скомпилируется все верно и даже выполнится, но результат будет неожиданным. Чтобы избежать такого, в джаве придуман специальный инструмент. Если мы объявляет метод в классе и подразумеваем что этим объявлением мы на самом деле меняем версию уже существующего метода (который пришел по наследуству), то мы над ним пишем `@Override`. Это никак не влияет на механизм наследования, но это заставляет компилятор убедиться что мы действительно не создаем новый метод, а меняем реализацию уже существующего. Поэтому если мы опечаталдись - то у нас команда не скомпилится и джава будет явно намекать что мы опечатались. Если же все ок - то у нас поведжение не поменяется, @Override никак не повлияет на происходящее.  
Все классы в джаве, которые явным образом ни от кого не наследуются - на самом деле наследуются от специального класса `Object`. От него в каком то роде происходят все классы. Этот класс содержит ряд полезных методов, например, `toString()`, именно поэтому данный метод автоматически появляется даже у пустого класса. Если мы хотим поменять поведение этого метода - мы обчно брали и писали его.
```java
    @Override
    public String toString() {
        return "[" + rating + "] " + name + " (" + age + " лет)";
    }
```
Помотрим, как эти механизмы влияют на поля. Добавим в класс Person поле rating, в этом случае у дочернего объекта будет 2 поля с одинаковым именем. Дело в том что если бы мы поменяли какой то тип (например, с инта на стринг), то джава бы не имела права просто взять и сменить тип ячейки, ведь мог быть какой нибудь метод, который работал бы с рейтингом как с числом, а в дочернем классе бы тип этого поля поменялся на стринг - это было бы проблемой. Также джава могла бы просто нам не компилить. Разберемся, почему все таки джава разрешила нам скомпилить и как нам работать с 2 одноименными полями (как джава поймет, к кому мы обращаемся). Заставить некомпилиться - это означает сломать нашу программу, мы не можем больше обновить библиотеку просто потому что там появляется новое поле. Так что джава просто создаст новое поле с именем рейтинг. Для разграничения доступа по нему (чтобы понимать к какому полю мы обращаемся) - джава будет смотреть, к какому классу относится поле и именно из этого класса возьмет одно из этих 2 полей

Объявление одноименных полей не приводит к переопределению/смене версий полей! Оно приводит лишь к созданию новых полей, пусть и с одинаковыми именами.

Посмотрим на наследование еще вот с каком стороны. Мы знаем что у нас есть правило инкапусляции (мы должны скрывать поля от всех и давать всем просто удобнеые методы для работы с ними). Поэтому мы ставим везде `private`. Но так джава сократит область видимости этих полей этим классом и даже дочерние классы не смогут обратиться  таким полям! Поля сами по себе будут, но обратиться не сможем. Чтобы разрешить наследникам обращаться к членам класса - мы можем использовать вместо `private` - `protected`. Это уровень доступа, когда мы можем обратиться в рамках класса, всех дочерних классов и в рамках всех классов, которые находятся в том же пакете. По сути это чуть облегченная версия дефолтного уровня доступа.  

Если мы не хотим, чтобы от нашего класса наследовались - может при объявлении класса указать слово final, таким образом мы запрретим высем наследоваться от него. Так, мы не можем наследоваться от класса String.
```java
public final class Person {
    protected String name;
    protected int age;
}
```

Правила наследования,
- Правило 1. Наследоваться можно только от одного класса. Java не поддерживает наследование нескольких классов. Один класс — один родитель. Обратите внимание — нельзя наследовать самого себя
- Правило 2. Наследуется всё, кроме приватных переменных и методов. Все методы и переменные, помеченные модификатором private, недоступны классу-наследнику
- Правило 3. Переделать метод класса-родителя. Представим, что мы наследуем класс, но нам не подходит то, что мы унаследовали. Допустим, мы хотим, чтобы определённый метод работал не так, как в родителе. Чтобы переопределить метод класса-родителя, пишем над ним @Override
- Правило 4. Вызываем методы родителя через ключевое слово super. Представим, что вы хотите изменить метод родительского класса совсем чуть-чуть — буквально дописать пару строк. Тогда в своём методе мы можем вызвать родительский метод с помощью ключевого слова super
- Правило 5. Запрещаем наследование. Если вы не хотите, чтобы кто-то наследовал ваш класс, поставьте перед ним модификатор final.

## Наследование конструкторов

Посмотрим, как будут вести себя конструкторы при наследовании. Когда мы вызываем создание нового объекта простого лкасса, без наследования - джава смотрим, какие ячейки ей надо будет хранить в объекте этого класса. Она ищет подходящее место в куче и выделяет там нужное место (в данном случае под ячейки name и age). Далее она учит этот кусок памяти некоторым методам, которые мы здесь прописали. И уже ссылку на этот метод отдает нам. Мы знаем, что если мы не объявляем никакой контруктор, то появляется встроенный пустой конструктор. Перед тем как отдать ссылку - джава может еще вызвать его. Конструктор выполняется последним этапом, прямо перед возвращением ссылки из new.  
Посмотрим, что будет при наследовании. Джава будет создавать класс Singer в несколько этапов. Джава видит, что этот объект наследуется от класса Персон, поэтому она сперва создает класс Персон - идет в область памяти, выделяет там секции, учит выполнять определенные команды и получает готовый объект класса Персон.
```java
public class Person {
    protected String name;
    protected int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    ...
}
```
Затем джава идет в класс Сингер и смотрит, как он расширяет класс Персон. Она видим, что мы добавили новое поле рейтинг - поэтому она берет класс Персон и добавляет в него новое поле. Сделав все изменения, которые мы указали здесь (в классе сингер) в фигурных скобках, джава по идее получает уже готовый объект класса сингер и отдаем его нам.  
```java
public class Singer extends Person {
    public Singer {
    }
}   
```

Мы создаем объект класса Персон, а затем наделяем его теми отличиями, которые отличают Сингера от Персона! Поэтому на самом деле джава будет вызывать конструктор класса персон - ведь она захочет сперва создать полноцуенный объект класса Персон, а затем из человека сделать певца. В итоге если мы создадим конструктор в Персоне, то у нас ничего не скомпилится - ведь джава в сингере создаст пустой конструктор, который ничего не принимает. Но когда мы вызовем создание нашего певца - джава сперва попытается создать объект класса Персон и у нее не получится (потому что объекты класса Персон создаются через констуктор с параметрами, джаве непонятно, какие значения ей нужно присваивать в эти параметры). Поэтому джава попросит нас это сделать. В этом конструкторе мы специальным синтаксисом первой строкой должны указать, как джаве вызывать конструктор нашего предка Person, используем слово super как будто мы вызваем метод
```java
public class Singer extends Person {
    public int rating;

    public Singer(String name, int age, int rating) {
        super(name, age);
        this.rating = rating;
    }
}    
```