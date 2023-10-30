package classes;

import java.util.Comparator;

public class Employee implements Comparable<Employee> {

    private String name;
    private String surname;
    private String city;
    private int age;

    public Employee(String name, String surname, String city, int age, int salary) {
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.age = age;
        this.salary = salary;
    }

    public Employee(String name, int age) {
       this(name, "", "", age, 0);
    }

    private int salary;

    public static final Comparator<Employee> AGE_SALARY_COMPARATOR = null;
    public static final Comparator<Employee> PASSPORT_COMPARATOR = null;
    public static final Comparator<Employee> FULL_COMPARATOR = null;

    @Override
    public int compareTo(Employee o) {
        return 0;
    }
}