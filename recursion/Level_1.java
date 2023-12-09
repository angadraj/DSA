import java.io.*;
import java.util.*;


public class Level_1 {

    // print decreasing
    public static void printDecreasing (int n) {
        if (n == 0) {
            return;
        }
        System.out.print(n + " ");
        printDecreasing(n - 1);
    }

    public static void printIncreasing (int n) {
        if (n == 0) {
            return;
        }

        printIncreasing(n - 1);
        System.out.print(n + " ");
    }

    public static void printDI (int n) {
        if (n == 0) return;
        System.out.print(n + " ");
        printDI(n - 1);
        System.out.print(n + " ");
    }

    public static int factorial (int n) {
        if (n == 1) return 1;
        n *= factorial(n - 1);
        return n;
    }

    // O(n)
    public static int power (int n, int p) {
        if (p == 1) return n;
        n *= power(n, p - 1);
        return n;
    }

    // O(logn)
    public static int powerLog(int n, int p) {
        if (p == 1) return n;

        int halfPow = powerLog(n, p / 2);
        int val = (halfPow * halfPow);
        if (p % 2 == 1) {
            // if power is odd
            val *= n;
        }
        return val;
    }

    // print zig zag
    // fn = n + f(n - 1) + n + f(n - 1) + n
    // f(2) = 2 + f(1) + 2 + f(1) + 2
    // f(1) = 1 + 0 + 1 + 0 + 1

    // f(2) = 2 + (1 + 0 + 1 + 0 + 1) + 2 + (1 + 0 + 1 + 0 + 1) + 2
    // f(3) = 3 + (2 + (1 + 0 + 1 + 0 + 1) + 2 + (1 + 0 + 1 + 0 + 1) + 2) + 3 + (2 + (1 + 0 + 1 + 0 + 1) + 2 + (1 + 0 + 1 + 0 + 1) + 2) + 3
    public static void pzz(int n) {
        if (n == 0) return;
        System.out.print(n + " ");
        pzz(n - 1);
        System.out.print(n + " ");
        pzz(n - 1);
        System.out.print(n + " ");
    }

    // tower of hanoi
    /**
     * f(1, a, b, c) = a -> b (1, a, b, c)
     * f(2, a, b, c) = a -> c, a -> b, c -> b (2, a, c, b)
     * f(2, a, b, c) = f(1, a, c, b) + (a -> b) + f(1, c, b, a)
     * f(3, a, b, c) = f(2, a, c, b) + (a -> b) + f(2, c, b, a)
     */
    public static void toh(int n, String a, String b, String c) {
        if (n == 1) {
            System.out.println(a + " -> " + b);
            return;
        }
        toh(n - 1, a, c, b);
        System.out.println(a + " -> " + b);
        toh(n - 1, c, b, a);
    }

    public static void main (String[] args) {
        // int ans = powerLog(2, 10);
        // System.out.print(ans);
        toh(3, "a", "b", "c");
    }
}