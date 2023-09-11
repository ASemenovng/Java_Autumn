package enums;

public class EnumInheritanceTest {

  enum Operator {

    PLUS {
      public int calculate(int lhs, int rhs) {
        return lhs + rhs;
      }
    },

    MINUS {
      public int calculate(int lhs, int rhs) {
        return lhs - rhs;
      }
    },

    MULTIPLICATION {
      public int calculate(int lhs, int rhs) {
        return lhs * rhs;
      }
    };


    public abstract int calculate(int lhs, int rhs);

  }
}
