import classes.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmployeeComparatorTest {

    @Test
    void justWorks() {
        Assertions.assertNotNull(Employee.FULL_COMPARATOR);
        Assertions.assertNotNull(Employee.PASSPORT_COMPARATOR);
        Assertions.assertNotNull(Employee.AGE_SALARY_COMPARATOR);


        Employee e1 = new Employee("a", 0);
        Employee e2 = new Employee("A", 1);

        Assertions.assertTrue(e1.compareTo(e2) < 0);


        e1 = new Employee("a", 0);
        e2 = new Employee("A", 0);

        Assertions.assertEquals(0, e1.compareTo(e2));


        e1 = new Employee("b", 0);
        e2 = new Employee("A", 0);

        Assertions.assertTrue(e1.compareTo(e2) > 0);
    }


    @Test
    void fullComparatorTest() {
        Employee e1 = new Employee("a", "b", "city", 0, 0);
        Employee e2 = new Employee("a", "b", "city", 0, 1);

        Assertions.assertTrue(Employee.FULL_COMPARATOR.compare(e1, e2) < 0);
    }

    @Test
    void ageSalaryComparatorTest() {
        Employee e1 = new Employee("", "", "city", 11, 0);
        Employee e2 = new Employee("a", "b", "city", 11, 1);

        Assertions.assertTrue(Employee.AGE_SALARY_COMPARATOR.compare(e1, e2) < 0);

        e1 = new Employee("", "", "city", 11, 0);
        e2 = new Employee("a", "b", "city", 9, 1);

        Assertions.assertTrue(Employee.AGE_SALARY_COMPARATOR.compare(e1, e2) > 0);

    }

    @Test
    void passportComparatorTest() {
        Employee e1 = new Employee("", "", "city", 11, 0);
        Employee e2 = new Employee("a", "b", "city", 11, 1);

        Assertions.assertTrue(Employee.PASSPORT_COMPARATOR.compare(e1, e2) < 0);

        e1 = new Employee("", "B", "city", 11, 0);
        e2 = new Employee("", "b", "city", 9, 1);

        Assertions.assertTrue(Employee.PASSPORT_COMPARATOR.compare(e1, e2) > 0);

        e1 = new Employee("", "B", "City", 11, 0);
        e2 = new Employee("", "B", "city", 9, 1);

        Assertions.assertTrue(Employee.PASSPORT_COMPARATOR.compare(e1, e2) > 0);

    }
}
