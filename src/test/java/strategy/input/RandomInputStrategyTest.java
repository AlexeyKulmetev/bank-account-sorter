package strategy.input;

import collection.CustomArrayList;
import data.BankAccount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import validation.Validator;

import static org.junit.jupiter.api.Assertions.*;

class RandomInputStrategyTest {

    @Test
    @DisplayName("Вызов RandomStrategy c 0 возвращает пустой массив")
    void fillShouldReturnEmptyWhenZeroLengthPassed() {
        DataInputStrategy randomStrategy = new RandomInputStrategy();
        CustomArrayList<BankAccount> zeroLengthList = randomStrategy.fill(0);
        assertTrue(zeroLengthList.isEmpty());
    }

    @Test
    @DisplayName("Вызов RandomStrategy c отрицательной длиной возвращает пустой массив")
    void fillShouldReturnEmptyWhenNegativeLengthPassed() {
        DataInputStrategy randomStrategy = new RandomInputStrategy();
        CustomArrayList<BankAccount> zeroLengthList = randomStrategy.fill(-1);
        assertTrue(zeroLengthList.isEmpty());
    }

    @Test
    @DisplayName("Вызов RandomStrategy c 1 возвращает массив длиной 1")
    void fillShouldReturnArrayContainingOneElementWhenOneIsPassed() {
        DataInputStrategy randomStrategy = new RandomInputStrategy();
        CustomArrayList<BankAccount> zeroLengthList = randomStrategy.fill(1);
        assertEquals(1, zeroLengthList.size());
    }

    @Test
    @DisplayName("Вызов RandomStrategy c 15 возвращает массив длиной 15")
    void fillShouldReturnArrayOfGivenSize() {
        DataInputStrategy randomStrategy = new RandomInputStrategy();
        CustomArrayList<BankAccount> zeroLengthList = randomStrategy.fill(15);
        assertEquals(15, zeroLengthList.size());
    }

    @Test
    @DisplayName("Единственный элемент массива длиной 1 должен быть корректным")
    void fillTheElementMustBeValid() {
        DataInputStrategy randomStrategy = new RandomInputStrategy();
        CustomArrayList<BankAccount> zeroLengthList = randomStrategy.fill(1);

        assertTrue(Validator.isValidAccount(zeroLengthList.get(0)));
    }

    @Test
    @DisplayName("Все элементы массива длиной 15 должны быть корректными")
    void fillAllGeneratedAccountsShouldBeValid() {
        DataInputStrategy randomStrategy = new RandomInputStrategy();
        CustomArrayList<BankAccount> zeroLengthList = randomStrategy.fill(15);
        assertEquals(15, zeroLengthList.size());

        for (var acc: zeroLengthList) {
            assertTrue(Validator.isValidAccount(acc));
        }
    }
}