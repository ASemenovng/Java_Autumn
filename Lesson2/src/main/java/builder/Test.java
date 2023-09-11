package builder;

public class Test {

  public static void main(String[] args) {
    NutritionFacts cola = new NutritionFacts.Builder(240, 8)
        .calories(100)
        .fat(8)
        .sodium(14)
        .carbohydrate(30)
        .build();

    System.out.println(cola);
  }

}
