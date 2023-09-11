package builder;

public class NutritionFacts {

  private final int servingSize; // необходимо
  private final int servings; // необходимо
  private final int calories; // опционально
  private final int fat; // опционально
  private final int sodium; // опционально
  private final int carbohydrate; // опционально

  public static class Builder {

    private final int servingSize; // необходимо
    private final int servings; // необходимо
    private int calories = 0; // опционально
    private int fat = 0; // опционально
    private int sodium = 0; // опционально
    private int carbohydrate = 0; // опционально

    public Builder(int servingSize, int servings) {
      this.servingSize = servingSize;
      this.servings = servings;
    }

    public Builder calories(int val) {
      calories = val;
      return this;
    }

    public Builder fat(int val) {
      fat = val;
      return this;
    }

    public Builder sodium(int val) {
      sodium = val;
      return this;
    }

    public Builder carbohydrate(int val) {
      carbohydrate = val;
      return this;
    }

    public NutritionFacts build() {
      return new NutritionFacts(this);
    }
  }

  private NutritionFacts(Builder builder) {
    this.servingSize = builder.servingSize;
    this.servings = builder.servings;
    this.calories = builder.calories;
    this.fat = builder.fat;
    this.sodium = builder.sodium;
    this.carbohydrate = builder.carbohydrate;
  }

  @Override
  public String toString() {
    return "NutritionFacts{" +
        "servingSize=" + servingSize +
        ", servings=" + servings +
        ", calories=" + calories +
        ", fat=" + fat +
        ", sodium=" + sodium +
        ", carbohydrate=" + carbohydrate +
        '}';
  }
}
