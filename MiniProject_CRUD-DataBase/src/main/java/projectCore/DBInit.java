package projectCore;

import javax.xml.crypto.Data;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DBInit {
    private static final File fileEmployeeDB = new File("EmployeeDB.csv");
    private static List<Employee> employeesDBoutput;
//        new Employee("john", Position.BOSS, 1500, 45)
//        new Employee("jina", Position.ASSISTANT, 650, 21)
//        new Employee("bill", Position.ENGINEER, 1050, 32)
//        new Employee("john", Position.ENGINEER, 1100, 35)
//        new Employee("mike", Position.ENGINEER, 1000, 40)

    public static void save(List<Employee> employees) {
        try (PrintWriter pw = new PrintWriter(fileEmployeeDB)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Id");
            sb.append(",");
            sb.append("Name");
            sb.append(",");
            sb.append("Position");
            sb.append(",");
            sb.append("Salary");
            sb.append(",");
            sb.append("Age");
            sb.append("\n");
            for (Employee employee : employees) {
                sb.append(employee.getId());
                sb.append(",");
                sb.append(employee.getName());
                sb.append(",");
                sb.append(employee.getPosition());
                sb.append(",");
                sb.append(employee.getSalary());
                sb.append(",");
                sb.append(employee.getAge());
                sb.append("\n");
            }
            pw.write(sb.toString());
            System.out.println("Employees DataBase has successfully written.");
        } catch (FileNotFoundException e) {
                try {
                    fileEmployeeDB.createNewFile();
                    System.out.println("File \"EmployeeDB.csv\" does not exist and was created automatically. Try again.");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
        }
    }

    public static List<Employee> read() {
        List<Employee> employees = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileEmployeeDB))) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                if (line.split(",").length == 5) {
                    employees.add(splitToGiveEmployee(line));
                }
            }

            if (employees.size() == 0) {
                System.out.println("Your \"EmployeeDB.csv\" was empty.");
            } else {
                System.out.println("All " + employees.size() + " employees from \"EmployeeDB.csv\" have successfully loaded.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("There is no \"EmployeeDB.csv\" in project directory. " +
                    "You have to work with empty Employee database.\n" +
                    "Find database file or save new database from Command line.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return employees;
    }

    private static Employee splitToGiveEmployee (String line) {
        String[] employeeList = line.split(",");
        Employee employee = new Employee(Integer.parseInt(employeeList[0]), // id
                employeeList[1], // name
                DataUtil.getPosition(employeeList[2]), // position
                Double.parseDouble(employeeList[3]), // salary
                Integer.parseInt(employeeList[4])); // age

        return employee;
    }
}