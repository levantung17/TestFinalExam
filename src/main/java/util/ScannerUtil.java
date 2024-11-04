package util;

import java.util.Scanner;

public class ScannerUtil {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static int inputInt() {
        while (true) {
            try {
                String input = inputString();
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                System.err.println("Yêu cầu nhập vào số nguyên");
            }
        }
    }

    public static String inputEmail() {
        while (true) {
            String input = inputString();
            if (input.contains("@")) {
                return input;
            } else {
                System.err.println("Yêu cầu chứa kí tự @");
            }
        }
    }

    public static String inputPassword() {
        while (true) {
            String input = inputString();
            int length = input.length();
            if (length < 6 || length > 12) {
                System.err.println("Yêu cầu từ 6 đến 12 kí tự");
            } else if (hasAnyUppercaseCharacter(input)) {
                return input;
            } else {
                System.err.println("Yêu cầu ít nhất 1 kí tự viết hoa");
            }
        }
    }

    private static boolean hasAnyUppercaseCharacter(String input) {
        int length = input.length();
        for (int i = 0; i < length; i++) {
            char c = input.charAt(i);
            if (Character.isUpperCase(c)) {
                return true;
            }
        }
        return false;
    }

    public static String inputFullName() {
        while (true) {
            String input = inputString();
            if (checkFullName(input)) {
                return input;
            } else {
                System.err.println("Yêu cầu chỉ chứa chữ cái");
            }
        }
    }

    private static boolean checkFullName(String input) {
        int length = input.length();
        for (int i = 0; i < length; i++) {
            char c = input.charAt(i);
            if (Character.isWhitespace(c)) {
                continue;
            }
            if (!Character.isAlphabetic(c)) {
                return false;
            }
        }
        return true;
    }

    private static String inputString() {
        return SCANNER.nextLine()
                .trim()
                .replaceAll("\\s{2,}", " ");
    }
}