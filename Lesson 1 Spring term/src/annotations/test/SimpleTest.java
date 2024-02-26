package annotations.test;

public class SimpleTest {

  @Before
  public void setUp() {
    System.out.println("Выполнение подготовки...");
  }

  @Test
  public void test1() {
    System.out.println("Выполнение test1()");
  }

  @Test
  public void test2() {
    System.out.println("Выполнение test2()");
  }

  @After
  public void tearDown() {
    System.out.println("Выполнение завершающих операций...");
  }

  public static void main(String[] args) {
    TestRunner.runClassTests(SimpleTest.class);
  }
}
