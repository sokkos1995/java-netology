package deals;

public class Sale extends Deal {
    public Sale(int debitChange, int creditChange, String comment) {
        super(debitChange, creditChange, comment);
        this.comment = "Продажа " + comment + " на " + debitChange + " руб.";
        this.creditChange = 0;        
    }    
}
