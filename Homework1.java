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
        int arg1 = arg;
        int arg2 = arg;
        String str1 = new String();
        while (arg != 0) {
            if (arg % 16 >= 10) {
                int m = 55+arg%16;
                char c = (char) m;
                str1 = str1 + c;
            } else {
                str1 = str1 + Integer.toString(arg1 % 16);
            }

            arg = (arg / 16);
        }
        str1 = str1 + " ";
        while (arg1 != 0) {
            str1 = str1 + Integer.toString(arg1 % 8);
            arg1 = (arg1 / 8);
        }
        str1 = str1 + " ";
        while (arg2 != 0) {
            str1 = str1 + Integer.toString(arg2 % 2);
            arg2 = (arg2 / 2);
        }

        StringBuilder sb = new StringBuilder(str1);
        sb.reverse();

        System.out.println(sb);
    }


    // task 2
    static int normalize(int angle) {
        int n = (angle % 360);
        if (n < 0) {
            n = n + 360;
        }        
        return n;
    }


    // task 3
    static int max(int x, int y, int z) {
        if ((x > y) && (x > z)) {
            return x;
        } else if ((y > x) && (y > z)) {
            return y;
        } else {
            return z;
        }
    }


    // task 4
    static int fact(int n) {
        if (n == 1) {
            return 1;
        } else {
            return n * fact(n-1);
        }
    }

    // task 5
    static BigInteger fact(BigInteger n) {
        BigInteger l;
        l = new BigInteger("1");
        int comparevalue = l.compareTo(n);
        if (comparevalue == 0) {
            return l;
        } else {
            return n.multiply(fact(n.subtract(l)));
        }
    }

    // task 6
    static void multiplTable() {
        System.out.print("   ");
        for (int i = 1; i < 9; i++) {
            System.out.print(i);
            System.out.print("  ");
        }
        System.out.println(9);
        for (int i = 1; i < 10; i++) {
            System.out.print(i);
            System.out.print("  ");
            for (int j = 1; j < 9; j++) {
                System.out.print(j * i);
                if (i * j <= 9) {
                    System.out.print(" ");
                }
                System.out.print(" ");
            }
            System.out.println(9 * i);
        }
    }


    // task 7
    static int average(int... nums) {
        int l = nums.length;
        int sm = 0;
        for (int i = 0; i < l; i++) {
            sm = sm + nums[i];
        }
        return (sm / l + sm % l);
    }


    // task 8
    static boolean isMagicSquare(int[][] square) {
        int l = square.length;
        int f = 0;
        boolean status = true;
        for (int i = 0; i < l; i++) {
            int n = 0;
            for (int j = 0; j < l; j++) {
                n = n + square[i][j];
                if (i == 0) {
                    f = n;
                }
                if (n != f) {
                    status = false;
                }
            }
        }
        int n = 0;
        int p = 0;
        for (int i = 0; i < l; i++) {
            n = n + square[i][i];
            p = p + square[i][l-i-1];
            if ((n != f) || (p != f)) {
                    status = false;
                }
        }
        return status;
    }


    // task 9
    static int[] reverse(int[] arr) {
        int l = arr.length;
        int[] arr1 = new int [l];
        for (int i = (l - 1); i >= 0; i--) {
            arr1[l - 1 - i] = arr[i];
        }
        return arr1;
    }


    // task 10
    static int[] sort(int[] arr) {
        int l = arr.length;
        int s = 0;
        for (int i = 0; i < l; i++) {
            for (int j = i; j < l-1; j++) {
                if (arr[j] > arr[j+1]) {
                    s = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = s;
                }
            }
        }
        return arr;
    }


    // task 11
    static int[] removeExtra(int[] arr, int n) {
        int l = arr.length;
        int col = 0;
        for (int i = 0; i < l; i++) {
            if (arr[i] != n) {
                col = col + 1;
            }
        }
        int[] arr1 = new int[col];
        int tek = 0;
        for (int i = 0; i < l; i++) {
            if (arr[i] != n) {
                arr1[tek] = arr[i];
                tek = tek + 1;
            }
        }
        return(arr1);
    }
}
