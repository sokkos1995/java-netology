Тестирование программы. Mockito

На предыдущей лекции мы разобрали unit-
тестирование (модульное). Сейчас мы
рассмотрим наиболее частые проблемы, с
которыми можем столкнуться во время
создания тестов.
Давайте начнем с примера, не связанного с
программированием.

Таким образом, тестируя функционал модульно, мы сталкиваемся с
проблемой тестирования, при которой один функционал зависит
от другого.
Для решения такой проблемы придется тестировать некоторые
модули вместе - например, сейф вместе с сигнализацией.

А есть ли способы обойтись без настройки и подключения
нашего модуля к другим - зависимым от него?
Забегая вперед, ответим, что варианты есть, а пока давайте
рассмотрим еще один пример, уже из разработки.

Наша задача - протестировать сервис рекомендаций. Сервис советует вам,
чем можно заняться в зависимости от погоды и ваших индивидуальных
предпочтений.
На схеме изображен Advice Service - сервис рекомендаций, который
зависит от 2-х других сервисов:
22
1) Сервис погоды (Weather Service) -
возвращает нам актуальные данные о
погоде в запрашиваемом городе
2) Сервис индивидуальных предпочтений
пользователя (Preferences Service) -
возвращает нам данные о том, чем любит
заниматься пользователь, данные о его
хобби и спортивных увлечениях.

Advice Service
```java
import java.util.Set;
import java.util.stream.Collectors;
public class AdviceService {
private final PreferencesService preferencesService;
private final WeatherService weatherService;
public AdviceService(PreferencesService preferencesService, WeatherService weatherService) {
this.preferencesService = preferencesService;
this.weatherService = weatherService;
}
public Set<Preference> getAdvice(String userId) {
Weather weather = weatherService.currentWeather();
Set<Preference> preferences = preferencesService.get(userId);
if (Weather.RAINY == weather || Weather.STORMY == weather) {
return preferences.stream()
.filter(p -> p != Preference.FOOTBALL)
.collect(Collectors.toSet());
} else if (Weather.SUNNY == weather) {
return preferences.stream()
.filter(p -> p != Preference.READING)
.collect(Collectors.toSet());
}
return preferences;
}
}
```

Preferences Service
```java
public interface PreferencesService {
Set<Preference> get(String userId);
}
enum Preference {
FOOTBALL("Сыграть в футбол"),
WALKING("Выйти на прогулку"),
WATCHING_FILMS("Посмотреть кино дома"),
READING("Почитать книгу");
private final String value;
Preference(String value) {
this.value = value;
}
}
```

Weather Service
```java
public interface WeatherService {
Weather currentWeather();
}
enum Weather {
RAINY("Дождливо"),
STORMY("Сильный ветер"),
SUNNY("Солнечно"),
CLOUDY("Облачно");
private String weather;
Weather(String weather) {
this.weather = weather;
}
}
```

Напишем тест, который проверяет, что сервис не посоветует нам пойти гулять в
плохую погоду (в дождь или при сильном ветре).
```java
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Set;
class AdviceServiceTest {
@Test
void test_get_advice_in_bad_weather() {
WeatherService weatherService = ?
PreferencesService preferencesService = ?;
AdviceService adviceService = new AdviceService(preferencesService, weatherService);
Set<Preference> preferences = adviceService.getAdvice("user1");
Set<Preference> expected = Set.of(Preference.READING, Preference.WATCHING_FILMS);
Assertions.assertEquals(expected, preferences);
}
}
```

Чтобы тест заработал, знаки “?” нужно заменить на реализацию сервисов
WeatherService и PreferencesService.
Мы можем использовать вызовы реальных сервисов - такой способ
подходит, если:
● мы не боимся создать дополнительную нагрузку на эти сервисы для
тестов
● мы уверены, что они будут гарантированно доступны там, где эти
сервисы могут быть запущены
Это прямая аналогия с модульным тестированием автомата с «Кока-
Колой», когда наш тестируемый модуль зависит от других модулей
(сервисов).

Недостатки вызова реальных сервисов:
- нужно, чтобы модули, от которых зависят тестируемые модули,
были доступны и настроены
- ответ и обработка внешних модулей могут влиять на результат
теста и замедлять его работу
Для решения такой проблемы в тестировании было введено понятие
“заглушка” (или “mock” по-английски).

Mock, или заглушка - это подражание поведению
сервиса или объекта, функционал которого реализует
mock.
Создание такой заглушки позволяет не использовать в
тестах реальные модули, объекты или сервисы,
которые часто могут зависеть от сети, баз данных,
Интернета, а использовать копии этих модулей,
возвращающих уже заранее подготовленный
результат.

Создадим два mock, которые будем использовать в
тестах:
- для WeatherService создадим класс-заглушку
WeatherServiceMock
- для PreferencesService создадим класс-заглушку
PreferencesServiceMock

WeatherServiceMock
```java
public class WeatherServiceMock implements WeatherService {
@Override
public Weather currentWeather() {
return Weather.STORMY;
}
}
```
Такая класс-заглушка всегда будет возвращать только плохую погоду -
добавим ей возможность изменять возвращаемое значение на наше:
```java
public class WeatherServiceMock implements WeatherService {
private Weather value;
@Override
public Weather currentWeather() {
return value;
}
public void setValue(Weather value) {
this.value = value;
}
}
```

PreferencesServiceMock
```java
import java.util.Set;
public class PreferencesServiceMock implements PreferencesService {
private Set<Preference> value;
@Override
public Set<Preference> get(String userId) {
return value;
}
public void setValue(Set<Preference> value) {
this.value = value;
}
}
```
На ваше усмотрение к таким классам можно добавить еще конструктор,
который будет задавать значение по умолчанию для класса заглушки:
```java
public PreferencesServiceMock() {
this.value = Set.of(Preference.READING, Preference.FOOTBALL);
}
```

Подставим классы заглушки в тест
```java
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Set;
class AdviceServiceTest {
@Test
void test_get_advice_in_bad_weather() {
WeatherServiceMock weatherService = new WeatherServiceMock();
weatherService.setValue(Weather.STORMY);
PreferencesServiceMock preferencesService = new PreferencesServiceMock();
preferencesService.setValue(Set.of(Preference.FOOTBALL, Preference.WATCHING_FILMS,
Preference.READING));
AdviceService adviceService = new AdviceService(preferencesService, weatherService);
Set<Preference> preferences = adviceService.getAdvice("user1");
Set<Preference> expected = Set.of(Preference.READING, Preference.WATCHING_FILMS);
Assertions.assertEquals(expected, preferences);
}
}
```
Какие проблемы возникают в таком подходе?

Создание mock-классов позволяет сократить зависимость тестируемого сервиса,
но усложняет разработку тестов тем, что нам приходится на каждый зависимый
модуль писать свои реализации.
В случае, если мы будем менять интерфейс любого из модулей, каждый раз
придется актуализировать реализацию классов-заглушек.
Получается, при таком подходе нужно:
1. Создать класс-заглушку на каждый модуль, от которого зависит наш тест
2. Всегда актуализировать наши классы-заглушки в случае изменения базового
класса
Звучит неплохо, но, может, есть способ писать меньше кода?
Действительно, можно писать меньше, используя уже разработанные библиотеки
для тестирования. Одна из самых популярных библиотек называется Mockito.

## Библиотека Mockito

Библиотека Mockito позволяет упростить создание классов-заглушек. Нам не
нужно будет каждый раз следить за изменениями в классе “родителей” любой
из заглушек - достаточно будет использовать тот функционал, который
необходим в тесте.
Перепишем наш тест с использованием Mockito
```java
@Test
void test_get_advice_in_bad_weather() {
WeatherService weatherService = Mockito.mock(WeatherService.class);
Mockito.when(weatherService.currentWeather())
.thenReturn(Weather.STORMY);
PreferencesService preferencesService = Mockito.mock(PreferencesService.class);
Mockito.when(preferencesService.get("user1"))
.thenReturn(Set.of(Preference.FOOTBALL, Preference.WATCHING_FILMS, Preference.READING));
AdviceService adviceService = new AdviceService(preferencesService, weatherService);
Set<Preference> preferences = adviceService.getAdvice("user1");
Set<Preference> expected = Set.of(Preference.READING, Preference.WATCHING_FILMS);
Assertions.assertEquals(expected, preferences);
}
```
При создании тестов могут возникнуть ситуации, когда мы хотим
использовать уже существующий объект в качестве mock или изменить
только часть поведения модуля - сервиса. Для этого в Mockito вместо
обычного метода mock нужно использовать метод spy (шпион), который
создаст объект-заглушку, но будет передавать все свои вызовы реальному
объекту, из которого был создан.
Создавать объект Mockito.spy из interface не имеет смысла, так как мы
получим то же самое, что и при вызове Mockito.mock.
Реализация Mockito.spy происходит с помощью вызова метода Mockito.mock:
```java
public static <T> T spy(Class<T> classToSpy) {
return MOCKITO_CORE.mock(classToSpy, withSettings()
.useConstructor()
.defaultAnswer(CALLS_REAL_METHODS));
}
```

Для проверки Mockito Spy создадим реализацию интерфейса:
```java
public class WeatherServiceImpl implements WeatherService {
@Override
public Weather currentWeather() {
return Weather.SUNNY;
}
}
```
Вызовем в тесте и посмотрим, что вернет наш spy-объект:
```java
@Test
void test_spy_weather_service() {
WeatherService weatherService = Mockito.spy(WeatherServiceImpl.class);
Assertions.assertEquals(Weather.SUNNY, weatherService.currentWeather());
}
```
У этого spy-объекта, как и у любого mock, можно переопределять
значения, возвращаемые из методов, вызовом конструкции
when/thenReturn

Помимо создания заглушек, библиотека Mockito позволяет узнать,
сколько раз у нашей заглушки был вызван тот или иной метод во
время теста. Для этого используется метод Mockito.verify.
Изменим наш первый тест и посмотрим, сколько раз был вызван
метод PreferencesService.get(String userId)
```java
@Test
void test_get_advice_in_bad_weather() {
WeatherService weatherService = Mockito.mock(WeatherService.class);
Mockito.when(weatherService.currentWeather()).thenReturn(Weather.STORMY);
PreferencesService preferencesService = Mockito.mock(PreferencesService.class);
Mockito.when(preferencesService.get(Mockito.any())).thenReturn(Set.of(Preference.FOOTBALL));
AdviceService adviceService = new AdviceService(preferencesService, weatherService);
adviceService.getAdvice("user1");
Mockito.verify(preferencesService, Mockito.times(1)).get("user1");
Mockito.verify(preferencesService, Mockito.times(0)).get("user2");
}
```
Verify принимает два аргумента:
1. Объект-заглушка
2. Объект, реализующий интерфейс Mockito - VerificationMode.
В нашем случае Mockito.times проверяет, сколько раз был
вызван метод get с аргументом “user1” и “user2”. Так как
вызовов со значением “user2” не было, мы проверяем
Mockito.times(0), такой вызов можно заменить на
Mockito.never()

Какие еще VerificationMode доступны:
● Mockito.only() - проверяет, что метод был вызван строго
1 раз
● Mockito.atLeastOnce() - проверяет, что метод был вызван
хотя бы 1 раз
● Mockito.atLeast(n) - проверяет, что метод был вызван хотя
бы “n” раз
● Mockito.atMost(n) - проверяет, что метод был вызван не
более “n” раз
● Mockito.timeout(int n) - проверяет, что метод был вызван в
течение заданного времени (“n” миллисекунд)
● Mockito.after(int n) - проверяет, что вызов метода был
осуществлен после заданного интервала (“n” миллисекунд)

Mockito ArgumentCaptor

Библиотека позволяет получать значения, с которыми были вызваны методы mock-
объекта. Для этого в библиотеке Mockito есть специальный класс ArgumentCaptor.
Чтобы перехватить значение, переданное при вызове метода mock, нужно создать
ArgumentCaptor с типом значения, которое мы будем перехватывать. Например, мы
хотим получить аргумент, с которым был вызван метод get класса PreferencesService  
`ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);`.  
Я указал тип String, так как метод get принимает в качестве userId этот тип.
Следующим шагом нам нужно вызвать Mockito.verify и в качестве аргумента передать
наш mock:  
`Mockito.verify(preferencesService).get(argumentCaptor.capture());`.  
Для перехвата значения обязательно нужно вызвать метод capture(): он не изменит
значение, но перехватит его и сохранит. Чтобы получить перехваченное значение у
argumentCaptor, нужно вызвать метод getValue() или getValues():  
`Assertions.assertEquals("user1", argumentCaptor.getValue());`.  

Итоговый тест с проверкой переданного значения методу get PreferencesService
```java
@Test
void test_get_advice_in_bad_weather() {
WeatherService weatherService = Mockito.mock(WeatherService.class);
Mockito.when(weatherService.currentWeather()).thenReturn(Weather.STORMY);
PreferencesService preferencesService = Mockito.mock(PreferencesService.class);
Mockito.when(preferencesService.get(Mockito.any())).thenReturn(Set.of(Preference.FOOTBALL))
;
ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
AdviceService adviceService = new AdviceService(preferencesService, weatherService);
adviceService.getAdvice("user1");
Mockito.verify(preferencesService).get(argumentCaptor.capture());
Assertions.assertEquals("user1", argumentCaptor.getValue());
}
```

Библиотека Mockito позволяет:
1. Mock - создавать mock-объекты (заглушки) и определять их поведение прямо в тесте
2. Spy - создавать объекты на основе классов реализаций и передавать вызовы реальным объектам
3. Verify - проверять, сколько раз тот или иной метод у объекта заглушки был вызван
4. ArgumentCaptor - проверять, с какими значениями были вызваны методы объекта заглушки

Что не может сделать библиотека Mockito:
● Не создает заглушки из final-классов
● Не создает заглушки к private-методам
● Не создает заглушки к конструкторам
● Не создает заглушки к методам equals и hashCode()
Для более сложного создания объектов-заглушек можно
рассмотреть библиотеку PowerMock, которая основана на
Mockito, или рассмотреть аналоги EasyMock и JMock

Библиотека Mockito позволяет сократить время на создание
объектов-заглушек, а также создавать и менять поведение этих
объектов прямо в тестах.
Эта библиотека одна из самых популярных на текущий момент и
рекомендуется к использованию.