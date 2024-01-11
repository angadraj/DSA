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
    /**
     * arr[n][n], cols[n], ndiag[2 * n - 1], rdiag[2 * n - 1]
     */
    public static void nQueensBranchAndBound(boolean[][] arr, int r, boolean[] cols, boolean[] diag, boolean[] rdiag, String ans) {
        if (r >= arr.length) {
            System.out.print(ans + " ");
            return;
        }
        
        for (int i = 0; i < arr[0].length; i++) {
            if (!cols[i] && !diag[r + i] && !rdiag[r - i + arr[0].length - 1]) {
                
                arr[r][i] = true;
                cols[i] = true;
                diag[r + i] = true;
                rdiag[r - i + arr[0].length - 1] = true;

                nQueensBranchAndBound(arr, r + 1, cols, diag, rdiag, r + "-" + i);

                arr[r][i] = false;
                cols[i] = false;
                diag[r + i] = false;
                rdiag[r - i + arr[0].length - 1] = false;
            }
        }
    }

    public static void nQueensHelper(int n) {
        boolean[][] arr = new boolean[n][n];
        boolean[] cols = new boolean[n];
        boolean ndiag[] = new boolean[2 * n - 1];
        boolean rdiag[] = new boolean[2 * n - 1];
        nQueensBranchAndBound(arr, 0, cols, ndiag, rdiag, "");
    }

     // josephus problem
    public static int josephus(int n, int k) {
        if (n == 1) return 0;
        int x = josephus(n - 1, k);
        int y = (x + k) % n;
        System.out.println(n + "-" + x + "-" + y);
        return y;
    }

    // print in lexicographic order
    public static void printLexico(int n) {
        for (int i = 1; i <= 9; i++) {
            lexDfs(i, n);
        }
    }

    public static void lexDfs(int i, int n) {
        if (i > n) return;
        System.out.println(i);

        for (int j = 0; j < 10; j++) {
            lexDfs(10 * i + j, n);
        }
    }

    // gold mine 2
    public static void goldmine2() {
        int[][] arr = {{10, 0, 100, 200, 0, 8, 0}, 
                        {20, 0, 0, 0, 0, 6, 0}, 
                        {30, 0, 0, 9, 12, 3, 4}, 
                        {40, 0, 2, 5, 8, 3, 11}, 
                        {0, 0, 0, 0, 0, 9, 0}, 
                        {5, 6, 7, 0, 7, 4, 2}, 
                        {8000, 9, 10, 0, 1, 1000, 800}
                    };

        int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        int ans = -(int)(1e8);
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] != 0) {
                    ans = Math.max(ans, goldMine2Dfs(arr, i, j, dirs));
                }
            }
        }

        System.out.println(ans);
    }

    public static int goldMine2Dfs(int[][] arr, int i, int j, int[][] dirs) {
        if (i < 0 || j < 0 || i >= arr.length || j >= arr[0].length || arr[i][j] == 0) {
            return -(int)(1e8);
        }

        int curr = arr[i][j];
        int ans = 0;
        // use given values for marking as visited
        arr[i][j] = 0;

        for (int[] d: dirs) {
            int x = i + d[0];
            int y = j + d[1];
            ans = Math.max(ans, goldMine2Dfs(arr, x, y, dirs));
        }
        return (ans + curr);
    }

    // solve sudoku
    public static void sudoku() {
        int[][] arr = {
                    {3, 0, 6, 5, 0, 8, 4, 0, 0},
                    {5, 2, 0, 0, 0, 0, 0, 0, 0},
                    {0, 8, 7, 0, 0, 0, 0, 3, 1},
                    {0, 0, 3, 0, 1, 0, 0, 8, 0},
                    {9, 0, 0, 8, 6, 3, 0, 0, 5},
                    {0, 5, 0, 0, 9, 0, 6, 0, 0}, 
                    {1, 3, 0, 0, 0, 0, 2, 5, 0},
                    {0, 0, 0, 0, 0, 0, 0, 7, 4},
                    {0, 0, 5, 2, 0, 6, 3, 0, 0}
                };

        sudokuHelper(arr, 0, 0);
    }

    public static void sudokuHelper(int[][] arr, int i, int j) {
        if (i == arr.length - 1 && j == arr[0].length - 1) {
            for (int[] a: arr) {
                for (int val: a) {
                    System.out.print(val + " ");
                }
                System.out.println();
            }
            return;
        }

        int ni = 0;
        int nj = 0;
        if (j == arr[0].length - 1) {
            ni = i + 1;
            nj = 0;
        } else {
            ni = i;
            nj = j + 1;
        }

        if (arr[i][j] != 0) {
            sudokuHelper(arr, ni, nj);
        } else {
            // explore all options
            for (int num = 1; num <= 9; num++) {
               if (isValidSudoku(arr, i, j, num)) {
                    arr[i][j] = num;
                    sudokuHelper(arr, ni, nj);
                    arr[i][j] = 0;
               }
            }
        }
    }

    public static boolean isValidSudoku(int[][] arr, int r, int c, int num) {
        for (int i = 0; i < 9; i++) {
            if (arr[i][c] == num) return false;
        }

        for (int j = 0; j < 9; j++) {
            if (arr[r][j] == num) return false;
        }

        // submatrix check
        int subr = r / 3 * 3;
        int subc = c / 3 * 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (arr[subr + i][subc + j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        // System.out.println(josephus(5, 3));
        sudoku();
    }
}