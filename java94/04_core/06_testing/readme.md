Тестирование кода и Unit-тесты

Ошибки в тестировании

Зачем нам тестировать?

Основные понятия Unit- тестирования

Unit-модуль - изолированная часть кода
● класс
● метод
● набор методов
● набор классов

Unit-тестирование - соответствие ожидаемого поведения модуля реальному

Уровни тестирования

Основные уровни
тестирования 

Модульное - ЦЕЛЬ - ПРОВЕРИТЬ, ЧТО КАЖДЫЙ МОДУЛЬ (UNIT) РАБОТАЕТ В ОТДЕЛЬНОСТИ
● Если хоть один модуль не работает - вся система не работает
● Если каждый модуль работает - вся система, скорее всего, работает
Интеграционное - ЦЕЛЬ - ПРОВЕРИТЬ, ЧТО МОДУЛИ РАБОТАЮТ ВМЕСТЕ
● Иногда проверяют взаимодействие и с внешними системами: БД и др.
Системное - ЦЕЛЬ - ПРОВЕРИТЬ, ЧТО РАБОТАЕТ СИСТЕМА В ЦЕЛОМ
● Полное тестирование от А до Я
● Обычно проводится тестерами и автотестерами
Приемочное - ЦЕЛЬ - ПОКАЗАТЬ РЕЗУЛЬТАТЫ ЗАКАЗЧИКУ
● Приходит заказчик и проверяет, что всё действительно так, как он хотел
● Либо мы сами приходим и проводим демо-показ

Что, где и как тестировать?

JUnit

JUnit - фреймворк Unit-тестирования для Java
JUnit
@After/@AfterEach
Exception testing@Before/@BeforeEach

Чтобы подключить зависимость, нужно совершить следующие операции
- maven
```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-engine</artifactId>
    <version>5.1.0</version>
    <scope>test</scope>
</dependency>
```
- gradle
```groovy
dependencies {
// .. другие зависимости
    testImplementation('org.junit.jupiter:junit-jupiter: 5.6.2')
}
test {
    useJUnitPlatform()
}
```

Метод класса String, который добавляет одну строку в конец другой. При передаче пустой строки - возвращает исходную “AB”.concat(“BC”) = “ABBC”
```java
public String concat(String str) {
    int otherLen = str.length();
    if (otherLen == 0) {
        return this;
    }
    int len = value.length;
    char buf[] = Arrays.copyOf(value, len + otherLen); 
    str.getChars(buf, len);
    return new String(buf, true);
}
```
Результат при непустом аргументе:
```java
@Test
public void testConcat_validArgument_success() {
// подготавливаем данные:
final String original = "Test
string "; final String argument
= "valid";
final String expected = "Test string valid";
// вызываем целевой метод:
final String result = original.concat(argument);
// производим проверку (сравниваем ожидание и
результат): Assertions.assertEquals(expected, result);
}
```

Результат при пустом аргументе:
```java
@Test
public void testConcat_emptyString_originalString() {
// given:
final String original = "Test
string "; final String argument
= "";
// when:
final String result = original.concat(argument);
// then:
Assertions.assertEquals(original,
result);
}
```
Результат при null аргументе*:
```java
@Test
public void testConcat_nullArgument_throwsException() {
// given:
final String original = "Test string ";
// expect:
assertThrows(NullPointerException.class, ()
-> {
original.concat(null);
});
}
```
Наш тест — это просто метод:
1. На месте возвращаемого типа стоит void
ничего не возвращает (не нужен return)
2. Скобки для параметров пусты — значит метод не принимает
никаких входных данных
@org.junit.jupiter.api.Test3. Над методом стоит конструкция
— это аннотация

Чтобы запустить тест, воспользуйтесь иконкой “play”.

Как это работает
1. Когда мы запускаем тесты, запускается JUnit (как
раньше запускался наш Main).
2. JUnit ищет все классы в каталоге src/test/java над методами
которых стоит@Test.
3. Для каждого метода (с @Test) — создаёт объект из класса
и вызывает метод (это и есть тест).
4. Для каждого вызова — проверяет результат

Дополнительные методы
```java
class NetologyTesting {
private static long
suiteStartTime;
private long testStartTime;
@BeforeAll
public static void initSuite() {
System.out.println("Running
StringTest"); suiteStartTime =
System.nanoTime();
}
@AfterAll
public static void completeSuite() {
System.out.println("StringTest complete: " + (System.nanoTime() -
suiteStartTime));
}
@BeforeEach
public void initTest() {
System.out.println("Starting new
nest"); testStartTime =
System.nanoTime();
}
@AfterEach
public void finalizeTest() {
System.out.println("Test complete:" + (System.nanoTime() -
testStartTime));
}
}
```
Ещё ассерты
assertTrue(condition) Проверить что condition (типа boolean) является
true
assertFalse(condition) Проверить что condition (типа boolean) является
false
assertEquals(expected, actual) Проверить
равенство
expected и actual аналогично
Objects.equals(expected,
actual)
assertNotEquals(expected,
actual)
Проверить
неравенство
expected и actual аналогично
!Objects.equals(expected,
actual)
assertNull(value) Проверить что value является null
assertNotNull(value) Проверить что value не является null
assertArrayEquals(expected,
actual)
Проверить равенство массивов

Параметризованные тесты

Тесты на наборе различных
параметров:
```java
@ParameterizedTest
@ValueSource(strings = { "Hello", "World" })
public void testWithStringParameter(String
argument) {
Assertions.assertTrue(argument.contains("o"));
}
```
[Еще больше параметризации](https://docs.junit.org/current/user-guide/#writing-tests-parameterized-tests)

TDD - вначале тесты. Код по ним
BDD - вначале критерии приемочного тестирования (и иногда системные автотесты к ним)
[Hamcrest](https://www.baeldung.com/java-junit-hamcrest-guide) - библиотека для большей читабельности + больше возможностей