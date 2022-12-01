package com.company;

import java.util.Scanner;

public class Main {
    public static final String[] NUMBER_ARABIC = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    public static final String[] NUMBER_ROMAN1 = new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
    public static final String[] NUMBER_ROMAN2 = new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
            "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII",
            "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII",
            "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
            "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI",
            "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX",
            "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите данные.");
        String input = in.nextLine();
        in.close();
        String str = checkException(input);
        String result = calc(str);
        System.out.println(result);
    }


    public static String checkException(String input) throws Exception {
        String[] split = input.split("[-,+,*,/]");
        if (split.length >= 3) {
            throw new Exception("Формат математической операции не удовлетворяет заданию.");
        } else if (split.length == 1 || split[0].trim().isEmpty() || split[1].trim().isEmpty()) {
            throw new Exception("Строка не является математической операцией.");
        } else if (split.length == 2 && (input.endsWith("+") || input.endsWith("-") || input.endsWith("*") || input.endsWith("/"))) {
            throw new Exception("Формат математической операции не удовлетворяет заданию.");
        } else {
            return input;
        }
    }

    public static String calc(String input) throws Exception {
        String[] st = input.split("[-,+,*,/]");
        boolean n1 = false;
        boolean n2 = false;
        boolean n3 = false;
        boolean n4 = false;
        boolean p = true;
        String result = "";
        while (p) {
            for (String s : NUMBER_ARABIC) {
                if (s.equals(st[0].trim())) n1 = true;
                if (s.equals(st[1].trim())) n2 = true;
            }
            for (String s : NUMBER_ROMAN1) {
                if (s.equals(st[0].trim())) n3 = true;
                if (s.equals(st[1].trim())) n4 = true;
            }
            p = false;
        }
        if (n1 == true && n2 == true) {
            int number1 = Integer.parseInt(st[0].trim());
            int number2 = Integer.parseInt(st[1].trim());
            if (input.contains("+")) {
                int r = number1 + number2;
                result = String.valueOf(r);
            } else if (input.contains("-")) {
                int r = number1 - number2;
                result = String.valueOf(r);
            } else if (input.contains("*")) {
                int r = number1 * number2;
                result = String.valueOf(r);
            } else if (input.contains("/")) {
                int r = number1 / number2;
                result = String.valueOf(r);
            }
        } else if (n3 == true && n4 == true) {
            int number3 = 0;
            int number4 = 0;
            for (int i = 0; i < NUMBER_ROMAN2.length; i++) {
                if (NUMBER_ROMAN2[i].equals(st[0].trim())) {
                    number3 = i + 1;
                }
                if (NUMBER_ROMAN2[i].equals(st[1].trim())) {
                    number4 = i + 1;
                }
            }
            if (input.contains("+")) {
                int r = number3 + number4;
                result = NUMBER_ROMAN2[r - 1];
            } else if (input.contains("-")) {
                if (number3 < number4) {
                    throw new Exception("В римской системе нет отрицательных чисел.");
                }
                int r = number3 - number4;
                result = NUMBER_ROMAN2[r - 1];
            } else if (input.contains("*")) {
                int r;
                r = number3 * number4;
                result = NUMBER_ROMAN2[r - 1];
            } else if (input.contains("/")) {
                if (number3 < number4) {
                    throw new Exception("Результат меньше единицы.");
                }
                int r = number3 / number4;
                result = NUMBER_ROMAN2[r - 1];
            }
        } else {
            throw new Exception("Используются одновременно разные системы счисления или" +
                    " Введенное число(числа) не входит в диапазон от 1 до 10.");
        }
        return result;
    }
}
