package accounts;

public class CreditAccount extends Account {
    long creditLimit;

    public CreditAccount(long balance, long creditLimit) {
        super(balance);
        this.creditLimit = creditLimit;
    }

    @Override
    public boolean add(long amount) {
        if (this.balance + amount <= 0) {
            this.balance += amount;
            return true;
        } else {
            return false;
        }
    };

    @Override
    public boolean pay(long amount) {
        if (this.balance - amount >= creditLimit) {
            this.balance -= amount;
            return true;
        } else {
            return false;
        }
    };


    public boolean transfer(Account account, long amount) {
        if (account.getClass() == CreditAccount.class) {
            if ( ( (this.balance - amount) >= this.creditLimit ) && (account.getBalance() + amount <= 0) ) {
                    this.balance -= amount;
                    account.add(amount);
                    return true;
                }
        } else if (account.getClass() == SimpleAccount.class) {
            if ( ( (this.balance - amount) >= this.creditLimit ) ) {
                this.balance -= amount;
                account.add(amount);
                return true;
            }            
        }
        return false;
    }
    
}
