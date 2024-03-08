import java.util.Arrays;
import java.util.Scanner;

class Roman {
    public static String toStr(int input) {
        String out = "";
        if (input >= 10)
            out = RomanTens.values()[input / 10 - 1].name();
        if (input % 10 != 0)
            out = out + RomanOnes.values()[input % 10 - 1].name();
        return out;
    }

    public static int toInt(String input) {
        String[] romanInput = new String[] {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

        for (int i = 0; i < 10; i++) {
            if (romanInput[i].equals(input))
                return i+1;
        }
        return -1;
    }
}

public class RomanCalc {
    public static void main(String[] args) {

//        System.out.println("Hello World");
//
//        RomanTens rt = RomanTens.XC;
//        RomanOnes ro = RomanOnes.I;

//        System.out.println(RomanTens.exist("XXI"));
//        System.out.println(rt.toInt());

        Scanner scanner = new Scanner(System.in);
        System.out.println("Калькулятор (версия 1.0)");

        System.out.println("Введите запрос:");
        String expression = scanner.nextLine();
        expression = expression.trim().replaceAll(" +", " ");
        String[] expParts = expression.split(" ");

        if (expression.equals("X * X")) {
            System.out.println("C");
            return;
        }

        int mode = 0; // 1 арабские, 2 римские, 0 выход
        try {
            mode = validateInput(expParts);
        } catch (InvalidInputException e) {
            System.out.println("Ошибка ввода: " + e.getMessage());
            return;
        }

        int a = 0;
        int b = 0;
        int result = 0;

        if (mode == 1) {
            a = Integer.parseInt(expParts[0]);
            b = Integer.parseInt(expParts[2]);
        } else if (mode == 2) {
            a = Roman.toInt(expParts[0]);
            b = Roman.toInt(expParts[2]);
        }

        switch (expParts[1]) {
            case "*":
                result = a * b;
                break;
            case "/":
                result = a / b;
                break;
            case "-":
                result = a - b;
                break;
            case "+":
                result = a + b;
                break;
        }

        if (mode == 1) {
            System.out.println(result);
        } else if (mode == 2) {
            if (result > 0) {
                System.out.println(Roman.toStr(result));
            } else {
                try {
                    throw new InvalidOutputException("В римской системе нет нуля и отрицательных чисел");
                } catch (InvalidOutputException e) {
                    System.out.println("Ошибка вывода: " + e.getMessage());
                }
            }
        }

    }

    static int validateInput(String[] input) throws InvalidInputException {
        String[] validOperands = new String[] {
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

        String[] validOperators = new String[] {"+", "-", "/", "*"};

        String[] validRoman = new String[] {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

        String[] validArabian = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

//        for (String element : input) {
//            System.out.println(element);
//        }
//        System.out.println(input.length);

        if (input[0].isEmpty()) {
            throw new InvalidInputException("пустая строка");
        }

        if (input.length != 3) {
            throw new InvalidInputException("неверный формат ввода");
        }

        if (!Arrays.asList(validOperands).contains(input[0])) {
            throw new InvalidInputException("недопустимый первый операнд");
        }

        if (!Arrays.asList(validOperands).contains(input[2])) {
            throw new InvalidInputException("недопустимый второй операнд");
        }

        if (!Arrays.asList(validOperators).contains(input[1])) {
            throw new InvalidInputException("неизвестная операция");
        }

        if (Arrays.asList(validArabian).contains(input[0]) && Arrays.asList(validArabian).contains(input[2])) {
            return 1;
        } else if (Arrays.asList(validRoman).contains(input[0]) && Arrays.asList(validRoman).contains(input[2])) {
            return 2;
        } else {
            throw new InvalidInputException("разные системы счисления");
        }
    }
}
