package deals;

public class Deal {
    public int debitChange;
    public int creditChange;
    public String comment;

    public Deal(int debitChange, int creditChange, String comment) {
        this.debitChange = debitChange;
        this.creditChange = creditChange;
        this.comment = comment;
    }
}
