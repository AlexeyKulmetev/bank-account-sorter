package strategy.input;

import collection.CustomArrayList;
import data.BankAccount;



public interface DataInputStrategy {
    CustomArrayList<BankAccount> fill(int length);
}