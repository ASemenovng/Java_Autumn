import java.math.BigInteger;
import java.util.Arrays;

public class Homework1 {

    public static void main(String[] args) {
        // тут можете тестировать ваш код

        convert(10);
        System.out.println(normalize(721));
        System.out.println(max(10, 12, -14));
        System.out.println(fact(12));
        System.out.println(fact(BigInteger.valueOf(30)));
        multiplTable();
        System.out.println(average(1, 2, 3, 4, 5, 6, 7));
        System.out.println(isMagicSquare(new int[][]{{16, 3, 2, 13}, {5, 10, 11, 8}, {9, 6, 7, 12}, {4, 15, 14, 1}}));
        System.out.println(Arrays.toString(reverse(new int[]{1, 2, 3})));
        System.out.println(Arrays.toString(sort(new int[]{2, 1, 45, 34, 12, 2})));
        System.out.println(Arrays.toString(removeExtra(new int[]{2, 1, 45, 2, 34, 1, 12, 2}, 2)));
    }

    // task 1
    static void convert(int arg) {
        String binary = Integer.toBinaryString(arg);
        String octary = Integer.toOctalString(arg);
        String hexary = Integer.toHexString(arg).toUpperCase();

        System.out.print(binary + " ");
        System.out.print(octary + " ");
        System.out.print(hexary);
    }


    // task 2
    static int normalize(int angle) {
        // Ваш код здесь
        return angle;
    }


    // task 3
    static int max(int x, int y, int z) {
        // Ваш код здесь
        return 0;
    }


    // task 4
    static int fact(int n) {
        // Ваш код здесь
        return 0;
    }


    // task 5
    static BigInteger fact(BigInteger n) {
        // Ваш код здесь
        return null;
    }

    // task 6
    static void multiplTable() {
        // Ваш код здесь
    }


    // task 7
    static int average(int... nums) {
        // Ваш код здесь
        return 0;
    }


    // task 8
    static boolean isMagicSquare(int[][] square) {
        // Ваш код здесь
        return false;
    }


    // task 9
    static int[] reverse(int[] arr) {
        // Ваш код здесь
        return null;
    }


    // task 10
    static int[] sort(int[] arr) {
        // Ваш код здесь
        return null;
    }


    // task 11
    static int[] removeExtra(int[] arr, int n) {
        // Ваш код здесь
        return null;
    }
}
