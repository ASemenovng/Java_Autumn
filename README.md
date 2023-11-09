# Homework 
## Дедлайн - 27 ноября 15:30

```java
static <A, B, C> Function<B, C> bind(BiFunction<A, B, C> fn, A a) {
  return b -> fn.apply(a, b);
}
```
## Task 1
### 3 балла
С помощью функции `bind()` реализуйте (как функциональный интерфес) функцию умножения на 2.

## Task 2
### 3 балла
С помощью функции `bind()` реализуйте (как функциональный интерфес) функцию взятия подстроки от 0 до 5 у входящей строки.

## Task 3
### 3 балла
