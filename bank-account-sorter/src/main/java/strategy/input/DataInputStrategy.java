package strategy.input;

import data.BankAccount;

import java.util.List;

public interface DataInputStrategy {
    List<BankAccount> fill(int length);
}