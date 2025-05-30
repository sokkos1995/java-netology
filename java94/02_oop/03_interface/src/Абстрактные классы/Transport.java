public abstract class Transport {
    private int fuel;

    public Transport(int fuel) {
        this.fuel = fuel;
    }

    public void spendFuel(int fuel) {
        this.fuel -= fuel;
    }

    public abstract void transfer(String name, String from, String to);
}
