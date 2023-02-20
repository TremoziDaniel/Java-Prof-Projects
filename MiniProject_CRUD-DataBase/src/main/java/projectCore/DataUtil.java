package projectCore;

import java.util.List;
import java.util.Scanner;

public class DataUtil {
    private static Scanner scanner = new Scanner(System.in);

    public static String getString(String prompt) {
        System.out.print(prompt);
        String str = scanner.next();

        return str;
    }

    public static int getInt(String prompt) {
        System.out.print(prompt);
        int num = scanner.nextInt();

        return num;
    }

    public static double getDouble(String prompt) {
        System.out.print(prompt);
        double num = scanner.nextDouble();

        return num;
    }

    public static Employee getEmployee(String prompt){
        System.out.println(prompt);
        String name = getString("name: ");
        Position position = getPosition();
        double salary = getDouble("salary: ");
        int age = getInt("age: ");

        return new Employee(name, position, salary, age);
    }

    public static Employee getEmployeeUpdate(String prompt){
        System.out.println(prompt);
        Position position = getPosition();
        double salary = getDouble("salary: ");
        int age = getInt("age: ");

        return new Employee(null, position, salary, age);
    }

    public static void print(List<Employee> toPrint) {
        if (toPrint.size() == 0) {
            System.out.println("No employees");
        } else {
            for (Employee employee : toPrint) {
                System.out.println(employee);
            }
        }
    }

    private static Position getPosition() {
        String value = getString("position: ");
        try {
            Position position = Position.valueOf(value.toUpperCase());
            return position;
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Position not found.\nType {BOSS, ASSISTANT, ENGINEER}");
            Position position = getPosition();
            return position;
        }
    }

    public static Position getPosition(String value) {
        try {
            Position position = Position.valueOf(value.toUpperCase());
            return position;
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Position not found.\nType {BOSS, ASSISTANT, ENGINEER}");
            Position position = getPosition();
            return position;
        }
    }
}
