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

    // cross word puzzle
    public static void crossWord () {
        char[][] arr = {{'+', '+', '+', '+', '+', '+', '+', '+', '+', '-'},
                        {'-', '+', '+', '+', '+', '+', '+', '+', '+', '-'},
                        {'-', '-', '-', '-', '-', '-', '-', '+', '+', '-'},
                        {'-', '+', '+', '+', '+', '+', '+', '+', '+', '-'},
                        {'-', '+', '+', '+', '+', '+', '+', '+', '+', '-'},
                        {'-', '+', '+', '+', '+', '-', '-', '-', '-', '-'},
                        {'-', '-', '-', '-', '-', '-', '+', '+', '+', '-'},
                        {'-', '+', '+', '+', '+', '+', '+', '+', '+', '-'},
                        {'+', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
                        {'+', '+', '+', '+', '+', '+', '+', '+', '+', '+'},
                    };
        String w[] = {"HISTORY", "CHEMISTRY", "PHYSICS", "CIVICS", "MATHS", "GEOGRAPHY"};
        crossWordHelper(arr, w, 0);
    }

    public static void crossWordHelper(char[][] arr, String[] w, int idx) {
        if (idx >= w.length) {
            for (char[] c: arr) {
                for (char val: c) {
                    System.out.print(val + " ");
                }
                System.out.println();
            }
            return;
        }

        String word = w[idx];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {

                if (arr[i][j] == '-' || arr[i][j] == word.charAt(0)) {

                    if (canPlaceHorizontally(arr, word, i, j)) {
                        boolean[] placedH = placeHorizontally(arr, word, i, j);
                        crossWordHelper(arr, w, idx + 1);
                        removeHorizontally(arr, placedH, i, j);
                    }

                    if (canPlaceVertically(arr, word, i, j)) {
                        boolean[] placedV = placeVertically(arr, word, i, j);
                        crossWordHelper(arr, w, idx + 1);
                        removeVertically(arr, placedV, i, j);
                    }

                }
            }
        }
    }

    public static boolean canPlaceHorizontally(char[][] arr, String w, int i, int j) {
        if (j - 1 >= 0 && arr[i][j - 1] != '+') {
            // it doesn't fits on top
            return false;
        } else if (j + w.length() < arr[0].length && arr[i][j + w.length()] != '+') {
            // it doesn't fits on bottom
            return false;
        }

        for (int x = 0; x < w.length(); x++) {
            if (x + j >=  arr[0].length) {
                // technically this case should not hit
                return false;
            }

            if (arr[i][x + j] == '-' || arr[i][x + j] == w.charAt(x)) {
                continue;
            } else {
                return false;
            }
        }

        return true;
    }

    public static boolean[] placeHorizontally(char[][] arr, String w, int i, int j) {
        boolean[] placed = new boolean[w.length()];
        for (int x = 0; x < w.length(); x++) {
            if (arr[i][x + j] == '-') {
                arr[i][x + j] = w.charAt(x);
                placed[x] = true;
            }
        }
        return placed;
    }

    public static void removeHorizontally(char[][] arr, boolean[] placed, int i, int j) {
        for (int x = 0; x < placed.length; x++) {
            if (placed[x]) {
                arr[i][x + j] = '-';
            }
        }
    }

    public static boolean canPlaceVertically(char[][] arr, String w, int i, int j) {
        if (i - 1 >= 0 && arr[i - 1][j] != '+') {
            return false;
        } else if (i + w.length() < arr.length && arr[i + w.length()][j] != '+') {
            return false;
        }

        for (int y = 0; y < w.length(); y++) {
            if (y + i >= arr.length) {
                // technically this case should not hit
                return false;
            }

            if (arr[y + i][j] == '-' || arr[y + i][j] == w.charAt(y)) {
                continue;
            } else {
                return false;
            }
        }

        return true;
    }

    public static boolean[] placeVertically(char[][] arr, String w, int i, int j) {
        boolean[] placed = new boolean[w.length()];
        for (int y = 0; y < w.length(); y++) {
            if (arr[i + y][j] == '-') {
                arr[i + y][j] = w.charAt(y);
                placed[y] = true;
            }
        }
        return placed;
    }

    public static void removeVertically(char[][] arr, boolean[] placed, int i, int j) {
        for (int y = 0; y < placed.length; y++) {
            if (placed[y]) {
                arr[i + y][j] = '-';
            }
        }
    }

    // crypto
    public static void crypto() {
        String p = "send";
        String q = "more";
        String r = "money";
        HashMap<Character, Integer> map = new HashMap<>();
        String us = "";
        
        us += giveUniqueString(map, p);
        us += giveUniqueString(map, q);
        us += giveUniqueString(map, r);

        System.out.println(map);

        boolean[] usedNum = new boolean[10];
        cryptoHelper(map, usedNum, us, p, q, r, 0);
    }

    public static String giveUniqueString(HashMap<Character, Integer> map, String s) {
        String us = "";
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!map.containsKey(ch)) {
                map.put(ch, -1);
                us += ch;
            }
        }
        return us;
    }

    public static int getNum(HashMap<Character, Integer> map, String s) {
        String num = "";
        for (int i = 0; i < s.length(); i++) {
            num += map.get(s.charAt(i));
        }
        return Integer.parseInt(num);
    }

    public static void cryptoHelper(HashMap<Character, Integer> map, boolean[] num, String u, String p, String q, String r, int idx) {
        if (idx >= u.length()) {
            int a = getNum(map, p);
            int b = getNum(map, q);
            int c = getNum(map, r);

            if (a + b == c) {
                for (int i = 0; i < 26; i++) {
                    char ch = (char)('a' + i);
                    if (map.containsKey(ch)) {
                        System.out.print(ch + " - " + map.get(ch) + " ");
                    }
                }
                System.out.println();
            }
            return;
        }

        char ch = u.charAt(idx);

        for (int i = 0; i < 10; i++) {
            if (!num[i]) {
                map.put(ch, i);
                num[i] = true;

                cryptoHelper(map, num, u, p, q, r, idx + 1);

                map.put(ch, -1);
                num[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        // System.out.println(josephus(5, 3));
        crypto();
    }
}