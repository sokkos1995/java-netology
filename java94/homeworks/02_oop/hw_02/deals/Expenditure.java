package deals;

public class Expenditure extends Deal {
    public Expenditure(int debitChange, int creditChange, String comment) {
        super(debitChange, creditChange, comment);
        this.comment = "Покупка " + comment + " на " + creditChange + " руб.";
        this.debitChange = 0;        
    }    
}