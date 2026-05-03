import java.util.Scanner;

public class TemperatureConverter {
    // method to convert Celsius to Fahrenheit
    public static double celsiusToFahrenheit(double celsius) {
        return (celsius * 9 / 5) + 32;
    }

    // method to convert Fahrenheit to Celsius
    public static double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("----- Temperature Converter -----");
        System.out.println("1. Celsius to Fahrenheit");
        System.out.println("2. Fahrenheit to Celsius");
        System.out.println("Choose an option: ");
        int choice = scanner.nextInt();

        System.out.println("Enter the temperature value: ");
        double temperature = scanner.nextDouble();

        switch (choice) {
            case 1:
                double fahrenheit = celsiusToFahrenheit(temperature);
                System.out.printf("%.2f Celsius is %.2f Fahrenheit%n", temperature, fahrenheit);
                break;
            case 2:
                double celsius = fahrenheitToCelsius(temperature);
                System.out.printf("%.2f Fahrenheit is %.2f Celsius%n", temperature, celsius);
                break;
            default:
                System.out.println("Invalid choice");
        }

        scanner.close();
    }
}
