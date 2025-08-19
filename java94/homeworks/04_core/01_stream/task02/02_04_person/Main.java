import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                        names.get(new Random().nextInt(names.size())),
                        families.get(new Random().nextInt(families.size())),
                        new Random().nextInt(100),
                        Sex.values()[new Random().nextInt(Sex.values().length)],
                        Education.values()[new Random().nextInt(Education.values().length)])
            );

        }

        // Найти количество несовершеннолетних (т.е. людей младше 18 лет).
        Stream<Person> childrenStream = persons.stream()
                .filter(person -> person.getAge() < 18);
        long count = childrenStream.count();
        System.out.println("Количество несовершеннолетних " + count);

        // Получить список фамилий призывников (т.е. мужчин от 18 и до 27 лет)
        List<String> militaryFamilies = persons.stream()
                .filter(person -> person.getAge() >= 18)
                .filter(person -> person.getAge() < 27)
                .filter(person -> person.getSex() == Sex.MAN)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println("Количество призывников " + militaryFamilies.size());        

        // Получить отсортированный по фамилии список потенциально работоспособных 
        // людей с высшим образованием в выборке (т.е. людей с высшим образованием 
        // от 18 до 60 лет для женщин и до 65 лет для мужчин)
        List<Person> educatedWorkers = persons.stream()
                .filter(person -> person.getAge() >= 18)
                .filter(person -> person.getSex() == Sex.MAN ? person.getAge() < 65 : person.getAge() < 60)
                .sorted((person1, person2) -> person1.getFamily().compareTo(person2.getFamily()))
                .collect(Collectors.toList());
        System.out.println("Первый работоспособный человек " + educatedWorkers.get(0));        
        System.out.println("Последний работоспособный человек " + educatedWorkers.get(educatedWorkers.size() - 1));        
        
    }
}
