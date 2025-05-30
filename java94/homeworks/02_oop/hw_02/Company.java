import deals.Deal;
import taxes.TaxSystem;
import java.util.ArrayList;

public class Company {
    String title;
    private int debit;
    private int credit;
    TaxSystem taxSystem;
    // взял ArrayList а не массив, тк нам неизвестно количество сделок
    ArrayList deals = new ArrayList();

    public Company(String title, TaxSystem taxSystem) {
        this.title = title;
        this.debit = 0;
        this.credit = 0;
        this.taxSystem = taxSystem;
    }

    public void setTaxSystem(TaxSystem taxSystem) {
        this.taxSystem = taxSystem;
    }

    public void shiftMoney(int amount) {
        if (amount > 0) {
            debit += amount;
        } else if (amount < 0) {
            credit += Math.abs(amount);
        } else {
            return; 
        } 
    }

    public void payTaxes() {
        int tax = taxSystem.calcTaxFor(debit, credit);
        System.out.println("Компания " + title + " уплатила налог в размере: " + tax + " руб.");
        debit = 0;
        credit = 0;
    }

    public int applyDeals(Deal[] deals) {
        int debitMinusCredit;

        for (int i = 0; i < deals.length; i++) {
            this.debit += deals[i].debitChange;
            this.credit += deals[i].creditChange;
        }
        debitMinusCredit = debit - credit;
        payTaxes();

        return debitMinusCredit;
    }

}
