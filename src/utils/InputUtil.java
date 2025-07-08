package utils;

import java.util.Scanner;

public class InputUtil {

    private InputUtil() {

    }

    private static Scanner scan = new Scanner(System.in);

    public static int inputNumber() {
        int input = -1;
        try {
            input = scan.nextInt();
            scan.nextLine();
        } catch(Exception e) {
            System.err.println("Error input number");
        }
        return input;
    }

    public static String inputString() {
        String input = "";
        try {
            input = scan.nextLine();
        } catch(Exception e) {
            System.err.println("Error input number");
        }
        return input;
    }
}
