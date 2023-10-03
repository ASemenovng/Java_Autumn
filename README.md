# Homework 
## Дедлайн - 16 октября 15:30


## Task 1
### 2 балла
В интерфейсе IntSequence реализуйте статический метод of (), возвращающий последовательность из передаваемых ему аргументов(их количество может быть любым). Также реализуйте статический метод constant (), возвращающий бесконечную последовательность констант. Например, в результате вызова IntSequence.constant (1) возвращается бесконечная последовательность из 1. Организуйте возврат как экземпляр анонимного внутреннего класса.

## Task 2
### 4 балла
Представим, вы делаете систему фильтрации комментариев на каком-то веб-портале.

Вы хотите фильтровать комментарии по разным критериям, уметь легко добавлять новые фильтры и модифицировать старые.

Допустим, мы будем фильтровать спам, комментарии с негативным содержанием и слишком длинные комментарии.
Спам будем фильтровать по наличию указанных ключевых слов в тексте.
Негативное содержание будем определять по наличию одного из трех смайликов – :( =( :|
Слишком длинные комментарии будем определять исходя из данного числа – максимальной длины комментария.

Вы решили абстрагировать фильтр в виде следующего интерфейса:
```java
interface TextAnalyzer {
  Label processText(String text);
}
```
Label – тип-перечисление, которые содержит метки, которыми будем помечать текст: спам, негативное содержание, слишком длинный, ок.

Дальше, вам необходимо реализовать три класса, которые реализуют данный интерфейс: SpamAnalyzer, NegativeTextAnalyzer и TooLongTextAnalyzer.
  SpamAnalyzer должен создаваться на основе массива строк с ключевыми словами.
	NegativeTextAnalyzer должен конструироваться конструктором по-умолчанию. (здесь валидируем только на три смайлика :( =( :|)
	TooLongTextAnalyzer должен конструироваться от int с максимальной допустимой длиной комментария.
	
SpamAnalyzer и NegativeTextAnalyzer во многом похожи – они оба проверяют текст на наличие каких-либо ключевых слов. Эту логику нужно убрать в абстрактный класс KeywordAnalyzer следующим образом:
  пусть в KeywordAnalyzer будет хранится массив слов, на наличие которых нужно проверить комментарий, и реализованный метод processText(), заполнение массива сделать в конструкторе уже для наследников этого класса.


В классе Test нужно написать метод checkLabels (принимает список классов для анализа текста и сам комментарий , коммент = просто строка), который будет возвращать метку для комментария. checkLabels должен возвращать первую не-OK метку в порядке данного набора классов для анализа, и OK, если все классы вернули OK.

## Task 3
### 1 балл
Написать интерфейс Printable, который содержит один метод void print(), и лямбда выражение для него, в котором что-то выводить в консоль (текст на ваше усмотрение). Написать метод, который будет принимать эту лямбду и выполнять.

## Task 4
### 1 балла
Написать лямбда выражение, которое принимает на вход число и возвращает строку “Положительное число”, “Отрицательное число” или  “Ноль”. 

## Task 5
### 3 балл
1. Создать статический метод который принимает на вход три параметра: login, password и confirmPassword.
2. Login должен содержать только латинские буквы, цифры и знак подчеркивания.
3. Длина login должна быть меньше 20 символов. Если login не соответствует этим требованиям, необходимо выбросить WrongLoginException.
4. Password должен содержать только латинские буквы, цифры и знак подчеркивания. Длина password должна быть меньше 20 символов. Также password и confirmPassword должны быть равны. Если password не соответствует этим требованиям, необходимо выбросить WrongPasswordException.  
5. WrongPasswordException и WrongLoginException - пользовательские классы исключения с двумя конструкторами – один по умолчанию, второй принимает сообщение 6. исключения и передает его в конструктор класса Exception.
7. Обработка исключений проводится внутри метода.
8. Используем multi-catch block.
9. Метод возвращает true, если значения верны или false в другом случае. 

## Task 6
### 3 балла
Дан следующий код, исправьте его там, где требуется.
```java
public static void main(String[] args) throws Exception {
   try {
       int a = 90;
       int b = 3;
       System.out.println(a / b);
       printSum(23, 234);
       int[] abc = { 1, 2 };
       abc[3] = 9;
   } catch (Throwable ex) {
       System.out.println("Что-то пошло не так...");
   } catch (NullPointerException ex) {
       System.out.println("Указатель не может указывать на null!");
   } catch (IndexOutOfBoundsException ex) {
       System.out.println("Массив выходит за пределы своего размера!");
   }
}
public static void printSum(Integer a, Integer b) throws FileNotFoundException {
   System.out.println(a + b);
}
```

## Task 7
### 1 балл
В методе main перехватить исключение (с сообщением об ошибке), возникающее при выполнении кода:
int a = 42 / 0;

## Task 8
### 1 балл
В методе main перехватить исключение (с сообщением об ошибке), возникающее при выполнении кода:
String s = null;
String m = s.toLowerCase();

## Task 9
### 1 балл
В методе main перехватить исключение (с сообщением об ошибке), возникающее при выполнении кода:
int[] m = new int[2];
m[8] = 5;
