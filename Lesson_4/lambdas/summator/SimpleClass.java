package lambdas.summator;

public class SimpleClass {

  private int[] values;

  // рассмотреть static
  private Summator sm = () -> {
    int res = 0;
    for (int i : values) {
      res += i;
    }
    return res;
  };

  // private int[] values; - если указать тут, будет ошибка компиляции
  public SimpleClass(int[] values) {
    this.values = values;
  }

  public int getSum() {
    return sm.sum();
  }
}
