package validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    @Test
    @DisplayName("null не является корректным AccountNumber")
    void AccountNumberShouldNotBeNull() {
        assertFalse(Validator.isValidAccountNumber(null));
    }

    @Test
    @DisplayName("Пустая строка не является корректным AccountNumber")
    void AccountNumberShouldNotBeAnEmptyString() {
        assertFalse(Validator.isValidAccountNumber(""));
    }

    @Test
    @DisplayName("Строка из пробелов не является корректным AccountNumber")
    void shouldBeFalseIfAccountNumberIsOnlySpaces() {
        assertFalse(Validator.isValidAccountNumber(""));
        assertFalse(Validator.isValidAccountNumber(" "));
        assertFalse(Validator.isValidAccountNumber("  "));
    }

    @Test
    @DisplayName("Строка длиной < 5 не подходит для AccountNumber")
    void shouldBeFalseIfAccountNumberIsLessThanFiveCharacters() {
        assertFalse(Validator.isValidAccountNumber("B451"));
    }

    @Test
    @DisplayName("Строка длиной == 5 подходит для AccountNumber")
    void shouldBeTrueIfAccountNumberIsFiveCharacters() {
        assertTrue(Validator.isValidAccountNumber("ACC23"));
    }

    @Test
    @DisplayName("Строка длиной >= 5 подходит для AccountNumber")
    void shouldBeTrueIfAccountNumberMoreThanFiveCharacters() {
        assertTrue(Validator.isValidAccountNumber("23AIRJ"));
    }

    @Test
    @DisplayName("null не является корректным AccountHolder")
    void AccountHolderShouldNotBeNull() {
        assertFalse(Validator.isValidHolderName(null));
    }

    @Test
    @DisplayName("Пустая строка не является корректным AccountHolder")
    void AccountHolderShouldNotBeAnEmptyString() {
        assertFalse(Validator.isValidHolderName(""));
    }

    @Test
    @DisplayName("Строка из пробелов не является корректным AccountHolder")
    void shouldBeFalseIfAccountHolderIsOnlySpaces() {
        assertFalse(Validator.isValidHolderName(""));
        assertFalse(Validator.isValidHolderName(" "));
        assertFalse(Validator.isValidHolderName("  "));
    }

    @Test
    @DisplayName("Ненулевая и непустая строка подходит для AccountHolder")
    void shouldBeTrueIfAccountHolderIsNotNullAndNotEmpty() {
        assertTrue(Validator.isValidHolderName("23AIRJ"));
    }

    @Test
    @DisplayName("Не допускается отрицательный баланс")
    void shouldBeFalseIfBalanceIsNegative() {
        assertFalse(Validator.isValidBalance(-238.9));
    }

    @Test
    @DisplayName("Нулевой баланс допустим")
    void shouldBeTrueIfBalanceIsZero() {
        assertTrue(Validator.isValidBalance(0));
    }

    @Test
    @DisplayName("Положительный баланс допустим")
    void shouldBeTrueIfBalanceIsPositive() {
        assertTrue(Validator.isValidBalance(6789.78));
    }
}