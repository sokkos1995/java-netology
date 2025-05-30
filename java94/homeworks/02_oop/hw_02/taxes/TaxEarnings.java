package taxes;

public class TaxEarnings extends TaxSystem {

    @Override
    public int calcTaxFor(int debit, int credit) {
        return (int) (debit * 0.06);
    }
}
