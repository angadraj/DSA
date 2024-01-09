import java.util.*;
import java.io.*;

class Level_2 {

    public static void printAbbre(String s, String ans, int count, int idx) {
        if (idx >= s.length()) {
            if (count == 0) {
                System.out.print(ans + " ");
            } else {
                System.out.print(ans + count + " ");
            }
            return;
        }

        if (count > 0) {
            // if any character is missing
            printAbbre(s, ans + count + s.charAt(idx), 0, idx + 1);
        } else {
            printAbbre(s, ans + s.charAt(idx), 0, idx + 1);
        }

        printAbbre(s, ans, count + 1, idx + 1);
    }

    // n queens: branch and bound
    public static void nQueensBranchAndBound() {
        
    }

    public static void main(String[] args) {
        printAbbre("pep", "", 0, 0);
    }
}