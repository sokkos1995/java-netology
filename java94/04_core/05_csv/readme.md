# Работа с файлами CSV, XML, JSON

## Библиотека OpenCSV

Язык Java не предоставляет собственного эффективного способа работы с файлами CSV. Поэтому рекомендуется использовать сторонние инструменты и библиотеки. 

OpenCSV — библиотека парсеров CSV файлов, поддерживающая весь основной функционал для работы с CSV. Наиболее полный users guide можно найти на [сайте](https://opencsv.sourceforge.net/) разработчиков

В зависимости от того используем ли мы сборщик проекта и какой именно
сборщик, есть несколько способов подключить библиотеку:
Если используем Maven, то подключаем зависимость в pom.xml файле:
```xml
<dependency>
    <groupId>net.sf.opencsv</groupId>
    <artifactId>opencsv</artifactId>
    <version>5.1</version>
</dependency>
```
Если используем Gradle, то подключаем зависимость в gradle.build файле:
```java
compile 'com.opencsv:opencsv:5.1'
```
Если не используем сборщик, то загружаем jar-файлы из
http://sourceforge.net/projects/opencsv/ и подключаем его вручную

Ниже приведен список наиболее используемых классов OpenCSV:
- CSVParser: простой CSV-парсер. Реализует разделение одной строки на поля.
- CSVReader: предназначен для чтения CSV-файла из Java-кода.
- CSVWriter: предназначен для записи CSV-файла из Java-кода.
- CsvToBean: создает Java объекты из содержимого CSV-файла.
- BeanToCsv: экспортирует данные Java объекты в CSV-файл.
- ColumnPositionMappingStrategy: используется CsvToBean (или BeanToCsv) для импорта CSV-данных, если требуется сопоставление полей CSV с полями Java класса

Создадим экземпляр класса CSVWriter и запишем данные в файл CSV:
```java
// Создаем запись
String[] employee = "1,David,Miller,Australia,30".split(",");
// Создаем экземпляр CSVWriter
try (CSVWriter writer = new CSVWriter(new FileWriter("staff.csv"))) {
 // Записываем запись в файл
 writer.writeNext(employee);
} catch (IOException e) {
 e.printStackTrace();
}
```
В корне проекта появится файл staff.csv с следующим содержимым: `"1","David","Miller","Australia","30"`

Как уже упоминалось выше, для чтения CSV-файла необходимо использовать
CSVReader. Давайте посмотрим на простой пример для чтения файла CSV:
```java
// Создаем экземпляр CSVWriter
// Разделитель по умолчанию - запятая
// Символ выражения по умолчанию - двойные кавычки
try (CSVReader reader = new CSVReader(new FileReader("staff.csv"))) {
 // Массив считанных строк
 String[] nextLine;
 // Читаем CSV построчно
 while ((nextLine = reader.readNext()) != null) {
 // Работаем с прочитанными данными.
 System.out.println(Arrays.toString(nextLine));
 }
} catch (IOException | CsvValidationException e) {
 e.printStackTrace();
}
```
Вышеприведенный пример читает файл CSV по одной строке и печатает в консоль: `[1, David, Miller, Australia, 30]`

Можно прочитать весь CSV-файл за один раз, а затем перебрать данные по своему
усмотрению. Ниже приведен пример чтения CSV-данных с использованием метода
readAll().
```java
// Читаем все строки за один раз
List<String[]> allRows = reader.readAll();
// Пройдемся по массиву строк
for (String[] row : allRows) {
 // Выполним операцию над записью
 System.out.println(Arrays.toString(row));
}
```
В приведенном выше примере считывается весь CSV-файл, а затем строки
перебираются по очереди

Вышеприведенный пример создает новый CSV-файл и начинает записывать данные
с начала. Но хотелось бы добавлять данные в существующий файл CSV вместо того,
чтобы создавать новый файл.
Этого можно достичь, передав второй аргумент boolean экземпляру FileWriter:
```java
CSVWriter writer = new CSVWriter(new FileWriter("staff.csv", true));
```
Тогда метод writeNext() дозапишет строчку в уже существующий файл:
```java
String[] employee = "2,David,Feezor,USA,40".split(",");
writer.writeNext(employee);

// Содержимое файла изменится на:
// [1, David, Miller, Australia, 30]
// [2, David, Feezor, USA, 40]
```

Не всегда приходится работать с файлами, которые используют стандартный
разделитель. Например, что делать, если необходимо прочитать файл с следующим
содержимым `4|David|Miller|Australia|30`. Для чтения такого файла, необходимо создать пользовательский парсер CSVParser:
```java
// Создадим пользовательский парсер
CSVParser parser = new CSVParserBuilder()
 .withSeparator('|')
 .build();
```
Чтобы использовать пользовательский парсер при чтении файла, необходимо
создать считывать через класс CVSReaderBuilder:
```java
// Создадим считыватель через билдер
CSVReader reader = new CSVReaderBuilder(new FileReader("staff.csv"))
 .withCSVParser(parser)
 .build();
```
OpenCSV также предоставляет функциональные возможности для сопоставления
CSV-файла с объектом Java.
Свяжем данные из файла staff.csv с объектом класса Employee:
```java
public class Employee {
 private long id;
 private String firstName;
 private String lastName;
 private String country;
 private int age;
 @Override
 public String toString() {
 return "Employee{" +
 "id='" + id + '\'' +
 ", firstName='" + firstName + '\'' +
 ", lastName='" + lastName + '\'' +
 ", country='" + country + '\'' +
 ", age='" + age + '\'' +
 '}';
 }
}
```
ColumnPositionMappingStrategy определяет класс, к которому будут привязывать
данные из CSV документа, а также порядок расположения полей в этом документе:
```java
ColumnPositionMappingStrategy<Employee> strategy = new ColumnPositionMappingStrategy<>();
strategy.setType(Employee. class);
strategy.setColumnMapping( "id", "firstName", "lastName", "country", "age");
```
CsvToBean создает инструмент для взаимодействия CSV документа и выбранной
ранее стратегии:
```java
CsvToBean<Employee> csv = new CsvToBeanBuilder<Employee>(csvReader)
 .withMappingStrategy(strategy)
 .build()
```
CsvToBean позволяет распарсить CSV файл в список объектов, который далее можно
использовать в своих целях:
```java
List<Employee> list = csv.parse();
list.forEach(System.out::println);
```
Полный код чтения CSV документа в Java класс приведен ниже:
```java
try (CSVReader csvReader = new CSVReader(new FileReader("staff.csv"))) {
 ColumnPositionMappingStrategy<Employee> strategy =
new ColumnPositionMappingStrategy<>();
 strategy.setType(Employee.class);
 strategy.setColumnMapping("id", "firstName", "lastName", "country", "age");
 CsvToBean<Employee> csv = new CsvToBeanBuilder<Employee>(csvReader)
 .withMappingStrategy(strategy)
 .build();
 List<Employee> staff = csv.parse();
 staff.forEach(System.out::println);
} catch (IOException e) {
 e.printStackTrace();
}

// Вывод консоли:
// Employee{id='1', firstName='Lokesh', lastName='Gupta', country='India', age='32'}
// Employee{id='2', firstName='David', lastName='Miller', country='England', age='34'}
```

И наконец, стоит рассмотреть способ записи Java объекта в CSV файл.
Предварительно создадим список объектов для записи:
```java
List<Employee> staff = new ArrayList<>();
staff.add(new Employee("1","Nikita", "Shumskii", "Russia", "25"));
staff.add(new Employee("2","Pavel", "Shramko", "Russia", "23"));
```
Произведем запись объектов в файл с помощью метода write():
```java
try(Writer writer = new FileWriter("staff.csv")) {
 StatefulBeanToCsv<Employee> sbc =
 new StatefulBeanToCsvBuilder<Employee>(writer).build();
 sbc.write(staff);
} catch (IOException e) {
 e.printStackTrace();
} catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
 e.printStackTrace();
}

// Содержимое файла “staff.csv” после записи:
// "AGE","COUNTRY","FIRSTNAME","ID","LASTNAME"
// "25","Russia","Nikita","1","Shumskii"
// "23","Russia","Pavel","2","Shramko"
```
Если необходимо записать файл с определенной последовательностью колонок,
необходимо создать соответствующую стратегию:
```java
List<Employee> staff = new ArrayList<>();
staff.add(new Employee(1,"Nikita", "Shumskii", "Russia", 25));
staff.add(new Employee(2,"Pavel", "Shramko", "Russia", 23));
ColumnPositionMappingStrategy<Employee> strategy =
new ColumnPositionMappingStrategy<>();
strategy.setType(Employee. class);
strategy.setColumnMapping( "id", "firstName", "lastName", "country", "age");
try(Writer writer = new FileWriter( "staff.csv")) {
 StatefulBeanToCsv<Employee> sbc =
 new StatefulBeanToCsvBuilder<Employee>(writer)
 .withMappingStrategy(strategy)
 .build();
 sbc.write(staff);
} catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
 e.printStackTrace();
}
```

## Работа с XML

В общем случае XML файлы должны удовлетворять следующим требованиям:
- В заголовке файла помещается объявление XML с указанием языка разметки,
номера версии и дополнительной информации.
- Каждый открывающий тэг обязательно должен иметь своего закрывающего
"напарника".
- Учитывается регистр символов.
- Все значения атрибутов тэгов должны быть заключены в кавычки.
- Вложенность тэгов в XML строго контролируется.
- Учитываются все символы форматирования.

Для работы с XML документами будем использовать стандартную библиотеку,
входящую в JDK, а именно DOM — Document Object Model.
Так что же такое DOM? Судя из
названия это есть объектная
модель документа.
DOM представляет собой всё это
дерево в виде специальных
объектов Node. Каждый Node
соответствует своему XML-тегу.
Таким образом, получается некое
дерево. На самой вершине этой
иерархии находится Document.

DOM XML пapcep читaeт coдepжимoe XML фaйлa и зaгpyжaeт его в oпepaтивнyю
пaмять. Taким oбpaзoм, cтpoитcя oбъeктнaя мoдeль иcхoднoгo XML документа,
используя кoтopyю мoжнo paбoтaть c дaнными:
- читaть/добавлять/удалять элeмeнты дoкyмeнтa
- coвepшaть oбхoд дepeвa элeмeнтoв
- и другие действия 

Для того, чтобы получить объект Document для нашего XML-файла необходимо
выполнить следующий код.
```java
DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
DocumentBuilder builder = factory.newDocumentBuilder();
Document doc = builder.parse(new File("company.xml"));
```

Теперь при помощи DOM методов можно произвести разбор документа и
добраться до нужного узла иерархии,чтобы прочитать его свойства. 
```java
// Получим корневой узел документа:
Node root = doc.getDocumentElement();
System.out.println("Корневой элемент: " + root.getNodeName());

// Получить список дочерних узлов можно при помощи метода getChildNodes():
NodeList nodeList = root.getChildNodes();

// Пробежаться по всем дочерним узлам текущего узла можно с помощью цикла
for (int i = 0; i < nodeList.getLength(); i++) {
    Node node = nodeList.item(i);
    System.out.println("Teкyщий элeмeнт: " + node.getNodeName());
}
```

Чтение содержимого ÿлементов XML 
```java
// Используя метод getNodeType() можно узнать тип узла:
if (Node.ELEMENT_NODE == node.getNodeType()) {
    Element employee = (Element) node;
    // работа с элементом
}

// С помощью метода getAttributes() получается NamedNodeMap, который содержит атрибуты узла:
System.out.println("ID сотрудника: " + employee.getAttribute("id"));

// Узлы можно найти по их тагу с помощью метода getElementsByTagName():
System.out.println("Id инструмента: " + employee.getElementsByTagName( "tool").item(0).getTextContent());
```
Итоговый код для чтения документа:
```java
DocumentBuilderFactory factory = DocumentBuilderFactory. newInstance();
DocumentBuilder builder = factory.newDocumentBuilder();
Document doc = builder.parse( new File("company.xml"));
Node root = doc.getDocumentElement();
System.out.println("Корневой элемент: " + root.getNodeName());
read(root);
```
Используем рекурсию для чтения документа:
```java
private static void read(Node node) {
 NodeList nodeList = node.getChildNodes();
 for (int i = 0; i < nodeList.getLength(); i++) {
Node node_ = nodeList.item(i);
if (Node.ELEMENT_NODE == node_.getNodeType()) {
System. out.println("Текущий узел: " + node_.getNodeName());
Element element = (Element) node_;
NamedNodeMap map = element.getAttributes();
for (int a = 0; a < map.getLength(); a++) {
String attrName = map.item(a).getNodeName();
String attrValue = map.item(a).getNodeValue();
System. out.println("Атрибут: " + attrName + "; значение: " + attrValue);
}
read(node_);
}
 }
}
```

Создание XML документа - Для того, чтобы создать документ Document, необходимо воспользоваться классами
DocumentBuilderFactory для создания билдера документа DocumentBuilder,
который и создаст документ:
```java
DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
DocumentBuilder builder = factory.newDocumentBuilder();
Document document = builder.newDocument();

// Создадим корневой элемент документа, используя метод appendChild():
Element root = document.createElement("root");
document.appendChild(root);

// Добавим несколько вложенных узлов:
Element company = document.createElement("company");
root.appendChild(company);
Element equipment = document.createElement("equipment");
company.appendChild(equipment);
Element staff = document.createElement("staff");
company.appendChild(staff);
```
Добавим к корневому узлу еще несколько узлов, расположенных ниже по
иерархии:
```java
Element employee = document.createElement("employee");
employee.setAttribute("id", "3");
employee.setAttribute("firstname", "Nikita");
employee.setAttribute("lastname", "Shumskii");
staff.appendChild(employee);

// К добавленному узлу можем добавить еще несколько дочерних:
Element tool = document.createElement("tool");
tool.appendChild(document.createTextNode("123456"));
employee.appendChild(tool);
```

Для записи XML документа в файл необходимо проделать три операции.
```java
// Во-первых, необходимо создать объект типа DOMSource, который исходя из названия, будет служить источником данных:
DOMSource domSource = new DOMSource(document);

// Далее необходимо создать поток StreamResult, в который будет производиться запись данных из документа:
StreamResult streamResult = new StreamResult(new File("new_company.xml"));

// Наконец, необходимо создать трансформер Transformer, который произведет преобразование документа в формат пригодный для записи в поток
TransformerFactory transformerFactory = TransformerFactory.newInstance();
Transformer transformer = transformerFactory.newTransformer();
transformer.transform(domSource, streamResult);
```

Полный код создания и записи в файл XML документа:
```java
DocumentBuilderFactory factory = DocumentBuilderFactory. newInstance();
DocumentBuilder builder = factory.newDocumentBuilder();
Document document = builder.newDocument();
Element root = document.createElement( "root");
document.appendChild(root);
Element company = document.createElement( "company");
root.appendChild(company);
Element equipment = document.createElement( "equipment");
company.appendChild(equipment);
Element staff = document.createElement( "staff");
company.appendChild(staff);
Element employee = document.createElement( "employee");
employee.setAttribute( "id", "3");
employee.setAttribute( "firstname", "Nikita");
employee.setAttribute( "lastname", "Shumskii");
staff.appendChild(employee);
Element tool = document.createElement( "tool");
tool.appendChild(document.createTextNode( "123456"));
employee.appendChild(tool);
DOMSource domSource = new DOMSource(document);
StreamResult streamResult = new StreamResult( new File("new_company.xml"));
TransformerFactory transformerFactory = TransformerFactory. newInstance();
Transformer transformer = transformerFactory.newTransformer();
transformer.transform(domSource, streamResult);
```
А что если требуется отредактировать существующий документ? Не проблема.
Необходимо считать его и внести правки с помощью метода setTextContent():
```java
DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
DocumentBuilder builder = factory.newDocumentBuilder();
Document doc = builder.parse(new File("company.xml"));
NodeList nodeList = doc.getElementsByTagName("employee");
for (int i = 0; i < nodeList.getLength(); i++) {
 Node node = nodeList.item(i);
 if (Node.ELEMENT_NODE == node.getNodeType()) {
 Element element = (Element) node;
 if (Node.ELEMENT_NODE == node.getNodeType()) {
 Element element = (Element) node;
 if (element.getAttribute("id").equals("2")) {
 element.getElementsByTagName("tool").item(0).setTextContent("124562");
 }
 }
 }
}
DOMSource domSource = new DOMSource(doc);
StreamResult streamResult = new StreamResult(new File("company.xml"));
TransformerFactory transformerFactory = TransformerFactory.newInstance();
Transformer transformer = transformerFactory.newTransformer();
transformer.transform(domSource, streamResult);
```

## Работа с JSON

Для работы с JSON файлами существует большое количество различных библиотек.
Все они примерно похожи по своим возможностям.
В качестве примера, ознакомимся с библиотекой Json Simple, которая
предоставляет возможность парсинга существующего JSON объекта и создание
нового.

Зависимость в Maven:
```xml
<!-- https://mvnrepository.com/artifact/com.googlecode.json-simple/json-simple -->
<dependency>
 <groupId>com.googlecode.json-simple</groupId>
 <artifactId>json-simple</artifactId>
 <version>1.1.1</version>
</dependency>
```
Зависимость в Gradle:
```groovy
compile 'com.googlecode.json-simple:json-simple:1.1.1'
```
Json Simple — представляет собой простой API для обработки JSON. Сам API состоит
из около 13 классов, основу которых составляют следующие 5 классов:
- Класс JSONParser предназначен для разбора строки с JSON-содержимым. Он
принимает объект java.io.Reader или строку.
- Класс JSONObject — это Java представление JSON строки. Класс JSONObject
наследует HashMap и хранит пары ключ — значение.
- Класс JSONArray представляет собой коллекцию. Он наследует ArrayList и
реализует интерфейсы JSONAware и JSONStreamAware.
- JSONValue — класс для парсинга JSON строки в Java объекты. Для этого он
использует класс JSONParser. J
- Интерфейс JSONAware. Класс должен реализовывать этот интерфейс, чтобы
конвертироваться в JSON формат.

Для чтения из JSON файла необходимо с помощью JSONParser считать файл в
Object и привести его к типу JSONObject:
```java
JSONParser parser = new JSONParser();
try {
 Object obj = parser.parse(new FileReader("data.json"));
 JSONObject jsonObject = (JSONObject) obj;
 System.out.println(jsonObject);
} catch (IOException | ParseException e) {
 e.printStackTrace();
}
```

Из полученного объекта JSONObject можно выборочно извлечь информацию о его
содержимом. Например, получим фамилию:
```java
String lastName = (String) jsonObject.get("lastName");
System.out.println(lastName);

// Из JSONObject можно извлечь подобъект. Например, подобъекты адреса:
JSONObject adress = (JSONObject) jsonObject.get("address");
String city = (String) adress.get("streetAddress");
System.out.println(city);

// Из JSONObject можно извлечь массив. Например, массив с номерами телефонов:
JSONArray phoneNumbers = (JSONArray) jsonObject.get("phoneNumbers");
for (Object number : phoneNumbers) {
    System.out.println(number);
}
```

Для записи JSON в файл в первую очередь необходимо создать JSONObject и
добавить в него компоненты с помощью метода put():
```java
JSONObject obj = new JSONObject();
obj.put("name", "mkyong.com");
obj.put("age", 100);
JSONArray list = new JSONArray();
list.add("msg 1");
list.add("msg 2");
list.add("msg 3");
obj.put("messages", list);
```
После этого произведем запись файла, используя
FileWriter, преобразовав JSONObject в текст с
помощью метода toJSONString():
```java
try (FileWriter file = new
FileWriter("new_data.json")) {
 file.write(obj.toJSONString());
 file.flush();
} catch (IOException e) {
 e.printStackTrace();
}
```
Библиотека GSON была разработана программистами Google и позволяет
конвертировать объекты JSON в Java-объекты и наоборот.

Установим зависимость в Maven:
```xml
<!-- https://mvnrepository.com/artifact/com.google.code.gson/gson -->
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.8.5</version>
</dependency>
```
Установим зависимость в Gradle:
```groovy
implementation 'com.google.code.gson:gson:2.8.2'
```

Конвертируем обüект в JSON
```java
// Создадим класс Cat:
class Cat {
 public String name;
 public int age;
 public int color;
}

// Создадим экземпляр класса Cat:
Cat cat = new Cat();
cat.name = "Матроскин";
cat.age = 5;
cat.color = Color.blue.getRGB();

// Конвертируем объект созданного класса в JSON при помощи метода toJson():
GsonBuilder builder = new GsonBuilder();
Gson gson = builder.create();
System.out.println(gson.toJson(cat));
```
Естественно, требуется и производить конвертацию из JSON в Java класс.
Допустим с сервера пришёл ответ в виде JSON-строки и требуется из нее построить
объект: `String jsonText = "{\"name\":\"Мурзик\",\"color\":-16777216,\"age\":9}";`. В этом случае вызывается метод fromJson():
```java
GsonBuilder builder = new GsonBuilder();
Gson gson = builder.create();
Cat cat = gson.fromJson(jsonText, Cat.class);
System.out.println("Имя: " + cat.name + "\nВозраст: " + cat.age + "\nЦвет: " + cat.color);
```