public class Main {

    public static void main(String[] args) {
        String name = "Petya";

        Car car = new Car();
        spbToMoscow(name, car);

        Ship ship = new Ship();
        spbToMoscow(name, ship);

        Plane plane = new Plane();
        spbToMoscow(name, plane);

        Velo velo = new Velo();
        spbToMoscow(name, velo);
    }

    public static void spbToMoscow(String name, Transferer transport) {
        transport.transfer(name, "SPB", "MSC");
    }
}
