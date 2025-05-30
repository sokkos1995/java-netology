public abstract class Transport implements Transferer, Billable {
    private int fuel;

    public Transport(int fuel) {
        this.fuel = fuel;
    }

    public void spendFuel(int fuel) {
        this.fuel -= fuel;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Уплачено в размере " + amount);
    }
}
