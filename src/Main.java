import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length;
        int symbols;
        String inputString = null;
        try {
            System.out.println("Please, enter the secret code's length:");
            inputString = scanner.nextLine();
            length = Integer.parseInt(inputString);
        } catch (Exception e) {
            System.out.println("Error: " + inputString + " isn't a valid number.");
            return;
        }
        if (length < 1) {
            System.out.println("Error: the length cannot be less than 1");
            return;
        }
        try {
            System.out.println("Input the number of possible symbols in the code:");
            inputString = scanner.nextLine();
            symbols = Integer.parseInt(inputString);
        } catch (Exception e) {
            System.out.println("Error: " + inputString + " isn't a valid number.");
            return;
        }
        if (length > symbols) {
            System.out.println("Error: it's not possible to generate a code with a length of " + length + " with " + symbols + " unique symbols.");
            return;
        }
        if (symbols >= 37) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            return;
        }
        System.out.print("The secret is prepared: ");
        for (int i = 1; i <= length; i++){
            System.out.print("*");
        }
        if (symbols <= 10) {
            System.out.println(" (0-9)");
        } else {
            System.out.println(" (0-9) (a-" + ((char) (86 + symbols)) + ")");
        }
        String code = randomGenerator(length, symbols);
        System.out.println("Okay, let's start a game!");
        grade(code);
        System.out.println("The random secret number is " + code);

    }

    public static String randomGenerator(int length, int symbols) {
        StringBuilder str = new StringBuilder();
        char[] randomListOfDigit = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        ArrayList<Character> list = new ArrayList<>();
        if (symbols > 10) {
            char[] randomListOfCharacters = new char[26];
            for (int i = 0; i < symbols - 10; i++) {
                randomListOfCharacters[i] = (char) (96 + i);
            }
            for (int i = 0; i < 9; i++) {
                list.add(randomListOfDigit[i]);
            }
            for (int i = 0; i < symbols - 10; i++) {
                list.add(randomListOfCharacters[i]);
            }
            Collections.shuffle(list);
            for (int i = 0; i < length; i++) {
                str.append(list.get(i));
            }
        } else {
            for (int i = 0; i < symbols; i++) {
                list.add(randomListOfDigit[i]);
            }
            Collections.shuffle(list);
            for (int i = 0; i < length; i++) {
                str.append(list.get(i));
            }
        }
        return str.toString();

    }

    public static void grade(String code) {
        Scanner scanner = new Scanner(System.in);
        int turn = 1;
        System.out.println("Turn " + turn + ":");
        String userCode = scanner.nextLine();
        int cows;
        int bulls = 0;
        if (!code.equals(userCode)) {
            while (!code.equals(userCode)) {
                cows = 0;
                bulls = 0;
                for (int j = 0; j < code.length(); j++) {
                    for (int i = 0; i < code.length(); i++) {
                        boolean a = code.charAt(j) == userCode.charAt(i);
                        if(a) {
                            if(i == j) {
                                bulls++;
                            } else {
                                cows++;
                            }
                        }
                    }
                }
                if (cows == 0 && bulls == 0) {
                    System.out.println("Grade: None");
                } else {
                    System.out.println("Grade: " + bulls + " bull(s) and " + cows + " cow(s)");
                }
                turn++;
                System.out.println("Turn " + turn + ":");
                userCode = scanner.nextLine();
            }
        }
        System.out.println("Grade: " + code.length() + " bull(s) and 0 cow(s)");
        System.out.println("Congratulations! You guessed the secret code.");
        System.out.println("The random secret number is " + code);
    }
}