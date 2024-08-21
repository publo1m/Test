package ru.demo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class Task2 {

    private static final Map<Integer, String> NUMBERS_TO_WORDS = new HashMap<>();

    static {
        NUMBERS_TO_WORDS.put(0, "ноль");
        NUMBERS_TO_WORDS.put(1, "один");
        NUMBERS_TO_WORDS.put(2, "два");
        NUMBERS_TO_WORDS.put(3, "три");
        NUMBERS_TO_WORDS.put(4, "четыре");
        NUMBERS_TO_WORDS.put(5, "пять");
        NUMBERS_TO_WORDS.put(6, "шесть");
        NUMBERS_TO_WORDS.put(7, "семь");
        NUMBERS_TO_WORDS.put(8, "восемь");
        NUMBERS_TO_WORDS.put(9, "девять");
        NUMBERS_TO_WORDS.put(10, "десять");
        NUMBERS_TO_WORDS.put(20, "двадцать");
        NUMBERS_TO_WORDS.put(30, "тридцать");
        NUMBERS_TO_WORDS.put(40, "сорок");
        NUMBERS_TO_WORDS.put(50, "пятьдесят");
        NUMBERS_TO_WORDS.put(60, "шестьдесят");
        NUMBERS_TO_WORDS.put(70, "семьдесят");
        NUMBERS_TO_WORDS.put(80, "восемьдесят");
        NUMBERS_TO_WORDS.put(90, "девяносто");
        NUMBERS_TO_WORDS.put(100, "сто");
        NUMBERS_TO_WORDS.put(200, "двести");
        NUMBERS_TO_WORDS.put(300, "триста");
        NUMBERS_TO_WORDS.put(400, "четыреста");
        NUMBERS_TO_WORDS.put(500, "пятьсот");
        NUMBERS_TO_WORDS.put(600, "шестьсот");
        NUMBERS_TO_WORDS.put(700, "семьсот");
        NUMBERS_TO_WORDS.put(800, "восемьсот");
        NUMBERS_TO_WORDS.put(900, "девятьсот");
        NUMBERS_TO_WORDS.put(1000, "тысяча");
        NUMBERS_TO_WORDS.put(2000, "две тысячи");
        NUMBERS_TO_WORDS.put(100000, "сто тысяч");
    }

    public static String convertToWords(BigDecimal number) {
        if (number == null) {
            throw new RuntimeException("Число не должно быть null");
        }

        if (number.compareTo(BigDecimal.ZERO) == 0) {
            return "ноль целых, ноль десятых";
        }

        BigDecimal integerPart = number.setScale(0, RoundingMode.DOWN);
        BigDecimal fractionalPart = number.subtract(integerPart).movePointRight(number.scale()).setScale(0, RoundingMode.DOWN);
        String integerPartNumberToWords = convertIntegerPartToWords(integerPart);
        String fractionalPartNumberToWords = convertIntegerPartToWords(fractionalPart);
        String fractionalWord = (fractionalPart.toString().length() > 1) ? " сотых" : " десятых";

        return integerPartNumberToWords + " целых " + fractionalPartNumberToWords + fractionalWord;
    }

    private static String convertIntegerPartToWords(BigDecimal number) {
        int num = number.intValue();
        if (num == 0) {
            return "ноль";
        }

        StringBuilder words = new StringBuilder();
        if (num >= 1000) {
            int thousands = num / 1000;
            words.append(convertNumberToWords(thousands)).append(" ").append(NUMBERS_TO_WORDS.get(1000)).append(" ");
            num %= 1000;
        }
        if (num > 0) {
            words.append(convertNumberToWords(num));
        }

        return words.toString().trim();
    }

    private static String convertNumberToWords(int num) {
        StringBuilder words = new StringBuilder();

        if (num >= 100) {
            int hundreds = num / 100 * 100;
            words.append(NUMBERS_TO_WORDS.get(hundreds)).append(" ");
            num %= 100;
        }

        if (num >= 20) {
            int tens = num / 10 * 10;
            words.append(NUMBERS_TO_WORDS.get(tens)).append(" ");
            num %= 10;
        }

        if (num > 0) {
            words.append(NUMBERS_TO_WORDS.get(num)).append(" ");
        }

        return words.toString().trim();
    }
}
