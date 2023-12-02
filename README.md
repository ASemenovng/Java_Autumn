# Homework 
## Дедлайн - 11 декабря 15:30

```java
static <A, B, C> Function<B, C> bind(BiFunction<A, B, C> fn, A a) {
  return b -> fn.apply(a, b);
}

static <A, B, C> Function<A, Function<B, C>> curry(BiFunction<A, B, C> fn){
  return a -> b -> fn.apply(a, b);
}
```
## Task 1
### 3 балла
С помощью функции `bind()` реализуйте (как функциональный интерфес) функцию умножения на 2.

## Task 2
### 3 балла
С помощью функции `bind()` реализуйте (как функциональный интерфес) функцию взятия подстроки от 0 до 5 у входящей строки.

## Task 3
### 4 балла
Реализуйте метод `sayHello()`, который при помощи функции `curry()` будет возвращать функцию для форматирования строки по типу "greeting, name!" (параметры задаете вы в методах).
```java
Function<String, String> saySmth (String word) {
}
```
Пример использования кода:
```java
Function<String, String> sayHello = saySmth("Hello");
System.out.println(sayHello.apply("Alice")); // Выведет "Hello, Alice!"
```
## Task 4
### 10 балла
Разбиение текста на слова с использованием собственного Spliterator.
Допустим, у нас есть поток текста, и мы хотели бы реализовать собственный Spliterator для разбиения текста на слова (разделяемые по пробелу).
Вам нужно реализовать свой сплитератор для этого:
```java
public class WordSpliterator implements Spliterator<String> {
    
    @Override
    public boolean tryAdvance(Consumer<? super String> action) {
    }

    @Override
    public Spliterator<String> trySplit() {
    }

    @Override
    public long estimateSize() {
    }

    @Override
    public int characteristics() {
    }

    
}
```
По итогу у вас должен работать следующий код:
```java
public class Test {
  public static void main(String[] args) {
    String text = "This is a sample sentence demonstrating custom Spliterator implementation";
    Stream<String> wordStream = StreamSupport.stream(new WordSpliterator(text), false);
    wordStream.forEach(System.out::println);
  }
}
```
