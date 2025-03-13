import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) { //program runs until user enters exit
            System.out.println("Enter the temperature (e.g., 100 C, 32 F, 273.15 K):");
            String input = sc.nextLine().trim(); //any extra space is removed
            if (input.equalsIgnoreCase("exit")) {
                sc.close();
                break;
            }
            String[] parts = input.split("(?<=\\d)(?=\\D)"); //regex, if the user doesn't add space between words
            if (parts.length == 2) { //determine how many the split parts are
                double temperature; //here we have to make sure the 1st part is a number
                try {
                    temperature = Double.parseDouble(parts[0]); //(code that cause exception), converts String to number
                } catch (NumberFormatException e) { //(code to handle exception), if anything but numbers are inputted return invalid
                    System.out.println("Invalid temperature");
                    continue;
                }
                String unit = parts[1].toUpperCase(); //if the inputted letter is small turn it to capital
                if (unit.equals("K") && temperature < 0) { //if someone added a negative kelvin, handle  it
                    System.out.println("Invalid temperature, Kelvin cant be negative");
                    continue;
                }
                if (unit.equals("C") || unit.equals("F") || unit.equals("K")) { //and here we make sure the 2nd part is a letter
                    double fahrenheit, kelvin, celsius;
                    switch (unit) {
                        case "C":
                            fahrenheit = (temperature * 9 / 5) + 32;
                            kelvin = temperature + 273.15;
                            System.out.println(temperature + " " + unit + " = " +
                                    String.format("%.2f", fahrenheit) + " F = " +
                                    String.format("%.2f", kelvin) + " K"); //we round the decimal numbers
                            break;
                        case "F":
                            celsius = (temperature - 32) * 5 / 9;
                            kelvin = (temperature - 32) * 5 / 9 + 273.15;
                            System.out.println(temperature + " " + unit + " = " +
                                    String.format("%.2f", celsius) + " C = " +
                                    String.format("%.2f", kelvin) + " K");
                            break;
                        case "K":
                            celsius = temperature - 273.15;
                            fahrenheit = (temperature - 273.15) * 9 / 5 + 32;
                            System.out.println(temperature + " " + unit + " = " +
                                    String.format("%.2f", celsius) + " C = " +
                                    String.format("%.2f", fahrenheit) + " F");
                            break;
                    }
                } else {
                    System.out.println("Invalid unit");
                    continue;
                }
            } else {
                System.out.println("Invalid input");
                continue;
            }

        }
    }
}