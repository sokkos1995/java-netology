public class Main {

    public static void main(String[] args) {
        Person person = new Person("Petya", 8);
        //person.name = "Petya";
        person.age = -8;
        person.setAge(40);
        person.setAge(-8);
        person.happyBirthday();
        System.out.println(person.getAge());

        Singer singer = new Singer("Tanya", 15, 4);
        singer.setAge(10);
        singer.sing("Good morning!");
        singer.happyBirthday();
    }
}

