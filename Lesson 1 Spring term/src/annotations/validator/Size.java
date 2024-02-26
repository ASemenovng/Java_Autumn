package annotations.validator;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Size {
  int min() default 0;
  int max() default Integer.MAX_VALUE;
}

