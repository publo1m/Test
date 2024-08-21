package ru.demo;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println(Task1.getNextDispatchDate(Date.valueOf(LocalDate.now())));
        System.out.println(Task2.convertToWords(new BigDecimal("99999.99")));
    }
}