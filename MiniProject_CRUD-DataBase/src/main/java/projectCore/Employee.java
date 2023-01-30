package projectCore;

public class Employee {
    private int id;
    private String name;
    private Position position;
    private double salary;
    private int age;
    private static int count = 0;

    public Employee(String name, Position position, double salary, int age) {
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.age = age;
        this.id = ++count;
    }

    public String getName() {return name;}

    public int getId() {return id;}

    public Position getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }

    public int getAge() {
        return age;
    }

    //    public int count() {return count;}

    public void update(Position position, double salary, int age) {
        this.position = position;
        this.salary = salary;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee №" + id + "\n" +
                "{name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                '}';
    }
}
