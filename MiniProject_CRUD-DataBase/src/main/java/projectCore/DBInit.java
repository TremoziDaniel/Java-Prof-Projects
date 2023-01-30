package projectCore;

import java.util.Arrays;
import java.util.List;

public class DBInit {
    public static List<Employee> init() {
        List<Employee> employees = Arrays.asList(
        new Employee("john", Position.BOSS, 1500, 45),
        new Employee("jina", Position.ASSISTANT, 650, 21),
        new Employee("bill", Position.ENGINEER, 1050, 32),
        new Employee("john", Position.ENGINEER, 1100, 35),
        new Employee("mike", Position.ENGINEER, 1000, 40));

        return employees;
    }
}
