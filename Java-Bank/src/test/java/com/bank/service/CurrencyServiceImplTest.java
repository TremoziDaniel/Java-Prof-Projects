package com.bank.service;

import com.bank.domain.entity.Currency;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceImplTest {

    @InjectMocks
    private CurrencyServiceImpl service;

    private static final Currency currency1;

    private static final Currency currency2;

    static {
        currency1 = new Currency(1, "Paper", "PPR", new BigDecimal("1.1"));
        currency2 = new Currency(2, "Salad", "SLD", new BigDecimal("46.2345"));
    }

    @Test
    void convertCurrency() {
        BigDecimal testAmount = new BigDecimal("4203.14");

        assertEquals(service.convertCurrency(currency1, currency2, new BigDecimal(100)), testAmount);
        assertThrows(IllegalArgumentException.class, () ->
                service.convertCurrency(currency2, currency1, new BigDecimal("0.1")));
    }
}
