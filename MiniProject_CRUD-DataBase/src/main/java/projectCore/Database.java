package projectCore;

import java.util.*;

public class Database {
    List<Employee> employees;
    Map<Integer, Employee> idxEmployees;

    public Database (List<Employee> employees) {
        // create List
        this.employees = new ArrayList<>(employees);
//        this.employees.addAll(employees);
        // create Map
        this.idxEmployees = new HashMap<>();
        for(Employee employee: employees) {
            idxEmployees.put(employee.getId(), employee);
        }
    }

    public void create() {
        Employee employee = DataUtil.getEmployee("Create employee");
        if (employee != null) {
            employees.add(employee);
            idxEmployees.put(employee.getId(), employee);
            System.out.println(employee + " added");
        }  else {
            System.out.println("This is employee don't exist");
        }
    }

    public void read() {
        DataUtil.print(employees);
    }

    private Employee findById(int id) {
//        for (Employee employee: employees) {
//            if (employee.getId() == id) {
//                return employee;
//            }
//        }
//        return null;
        return idxEmployees.get(id);
    }

    public void update() {
        int id = DataUtil.getInt("Update by id: ");
        Employee employee = findById(id);
        if (employee != null) {
            Employee tmpUpdt = DataUtil.getEmployeeUpdate("Update {positon, salary, age}: ");
            if (tmpUpdt != null) {
                employee.update(tmpUpdt.getPosition(), tmpUpdt.getSalary(), tmpUpdt.getAge());
                System.out.println(employee + " updated.");
            }
        } else {
            System.out.println("This is employee don't exist or was fired");
        }
    }

    public void delete() {
        System.out.print("Delete find: ");
        int id = DataUtil.getInt("");
        Employee employee = findById(id);
        if (employee != null) {
            employees.remove(employee);
            idxEmployees.remove(employee.getId());
            System.out.println("Deleted " + employee);
        }
    }

    public void find() {
        System.out.print("find: ");
        String name = DataUtil.getString("find: ");
        List<Employee> found = new ArrayList<>();
        for (Employee employee: employees) {
            //if (employee.getName().equalsIgnoreCase(name)) {
            if (employee.getName().contains(name)) {
                found.add(employee);
            }
        }

        DataUtil.print(found);
    }

    public void positions() {
        List<Position> positions = new ArrayList<>();
        for (Employee employee: employees) {
            positions.add(employee.getPosition());
        }
        System.out.println(new HashSet<>(positions));
    }

    public void sort() {
        Comparator<Employee> comparator;
        String type = DataUtil.getString("Sort by: ");
        switch(type.toLowerCase().charAt(0)) {
            case 'n':
                comparator = (Employee o1, Employee o2) ->
                        o1.getName().compareTo(o2.getName());
                ;
                break;
            case 'p':
                comparator = (Employee o1, Employee o2) ->
                        o1.getPosition().compareTo(o2.getPosition());
                break;
            case 's':
                comparator = (Employee o1, Employee o2) ->
                        o1.getSalary() > o2.getSalary() ? 1 : o1.getSalary() < o2.getSalary() ? -1 : 0;
                break;
            case 'a':
                comparator = (Employee o1, Employee o2) ->
                        o1.getAge() > o2.getAge() ? 1 : o1.getAge() < o2.getAge() ? -1 : 0;
                break;
            default:
                System.out.println("Error! Irregular input. Write [n]ame, [a]ge or [s]alary");
                return;
        }
    }
}
