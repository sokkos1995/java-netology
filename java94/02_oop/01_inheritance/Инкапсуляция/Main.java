public class Main {

    public static void main(String[] args) {
        Person person = new Person();
        //person.name = "Petya";
        person.age = -8;
        person.setAge(40);
        person.setAge(-8);
        System.out.println(person.getAge());
    }
}
