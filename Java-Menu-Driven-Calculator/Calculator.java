import java.util.Scanner;

public class Calculator {
    // methods
    static double add(double x, double y) {
        return x + y;
    }
    static double subtract(double x, double y) {
        return x - y;
    }
    static double multiply(double x, double y) {
        return x * y;
    }
    static double divide(double x, double y) {
        return x / y;
    }
    static double power(double x, double y) {
        return Math.pow(x, y);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        double number1, number2, result;
        double lastResult = 0; // store previous result

        do {
            System.out.println("\n -------- Calculator --------");
            System.out.println("1. Addition");
            System.out.println("2. Subtraction");
            System.out.println("3. Multiplication");
            System.out.println("4. Division");
            System.out.println("5. Power");
            System.out.println("6. Use Previous Result");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Enter a number: ");
                scanner.next(); // clear wrong input
            }

            choice = scanner.nextInt();

            if(choice >=1 && choice <= 5) {
                System.out.println("Enter first number: ");
                number1 = scanner.nextDouble();

                System.out.println("Enter second number: ");
                number2 = scanner.nextDouble();
                try {
                    switch (choice) {
                        case 1:
                            result = add(number1, number2);
                            break;
                        case 2:
                            result = subtract(number1, number2);
                            break;
                        case 3:
                            result = multiply(number1, number2);
                            break;
                        case 4:
                            if (number2 == 0) {
                                throw  new ArithmeticException("Cannot divide by zero");
                            }
                            result = divide(number1, number2);
                            break;
                        case 5:
                            result = power(number1, number2);
                            break;
                        default:
                            result = 0;
                    }

                    lastResult = result;
                    System.out.println("Result: " + result);
                } catch (ArithmeticException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } else if (choice == 6) {
                System.out.println("Previous Result: " + lastResult);
            } else if (choice != 7) {
                System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 7);

        System.out.println("Calculator exited!");
        scanner.close();
    }
}
