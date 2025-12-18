import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        try (Scanner input = new Scanner(System.in)) {

            System.out.print("Enter first number: ");
            double a = Double.parseDouble(input.nextLine());

            System.out.print("Choose operation (+, -, *, /, ^, №, |): ");
            String operation = input.nextLine().trim();

            double b = 0;

            if (!operation.equals("№") && !operation.equals("|")) {
                System.out.print("Enter second number: ");
                b = Double.parseDouble(input.nextLine());
            }

            double res;

            try {
                switch (operation) {
                    case "+":
                        res = calculator.sum(a, b);
                        break;
                    case "-":
                        res = calculator.sub(a, b);
                        break;
                    case "*":
                        res = calculator.mul(a, b);
                        break;
                    case "/":
                        res = calculator.div(a, b);
                        break;
                    case "^":
                        res = calculator.pow(a, b);
                        break;
                    case "№":
                        res = calculator.sqrt(a);
                        break;
                    case "|":
                        res = calculator.abs(a);
                        break;
                    default:
                        System.out.println("Invalid operation selected");
                        return;
                }
                System.out.println("Result: " + res);

            } catch (ArithmeticException e) {
                System.out.println("Math Error: " + e.getMessage());
            } catch (InvalidInputException e) {
                System.out.println("Input Error: " + e.getMessage());
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input format (please enter a number)");
        }
    }
}