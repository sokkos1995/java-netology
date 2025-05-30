package accounts;

public class SimpleAccount extends Account {

    public SimpleAccount(long balance) {
        super(balance);
    }

    @Override
    public boolean add(long amount) {
        balance += amount;
        return true;
    }

    @Override
    public boolean pay(long amount) {
        if (balance - amount >= 0) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    };


    public boolean transfer(Account account, long amount) {
        if (account.getClass() == CreditAccount.class) {
            if ( ( (balance - amount) >= 0 ) && (account.getBalance() + amount <= 0) ) {
                    balance -= amount;
                    account.add(amount);
                    return true;
                }
        } else if (account.getClass() == SimpleAccount.class) {
            if ( ( (balance - amount) >= 0 ) ) {
                balance -= amount;
                account.add(amount);
                return true;
            }            
        }
        return false;
    }
}

