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
        if (n == 10) {
            return;
        }
        System.out.print(n + " ");
        printIncreasing(n + 1);
    }

    public static void main (String[] args) {
        printIncreasing(0);
    }
}