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

    // f(arr, idx) = a[idx] + f(arr, idx + 1);
    public static void printArr(int[] arr, int idx) {
        if (idx >= arr.length) {
            return;
        }

        System.out.print(arr[idx] + " ");
        printArr(arr, idx + 1);
    }

    // display arr in reverse
    // f(arr, idx) = f(arr, idx + 1) + a[idx]
    public static void printArrInReverse(int[] arr, int idx) {
        if (idx >= arr.length) return;
        printArrInReverse(arr, idx + 1);
        System.out.print(arr[idx] + " ");
    }

    // max of an arr
    // f(arr, idx) = max(arr[0], f(arr, idx + 1))
    public static int maxInArr(int[] arr, int idx) {
        if (idx >= arr.length) {
            return -(int)(1e8);
        }
        int max = maxInArr(arr, idx + 1);
        return Math.max(max, arr[idx]);
    }

    // find first index of given element
    // f(arr, idx) = Math.min(f(arr, idx + 1), arr[idx])
    public static int firstOccInddex (int[] arr, int idx, int key) {
        if (idx >= arr.length) return -1;
        int minIdx = firstOccInddex(arr, idx + 1, key);

        return (key == arr[idx] ? idx : minIdx);

    }

    public static int firstOccInddex_opti(int[] arr, int idx, int key) {
        if (idx == arr.length) return -1;

        return (arr[idx] == key ? idx : firstOccInddex_opti(arr, idx + 1, key));
    }

    // last occurance index
    public static int lastOcc(int[] arr, int idx, int key) {
        if (idx == arr.length) return -1;

        int maxIdx = lastOcc(arr, idx + 1, key);

        if (maxIdx == -1 && arr[idx] == key) {
            return idx;
        } else {
            return maxIdx;
        }
    }

    // all indices
    public static int[] allIndices(int[] arr, int idx, int key, int count) {
        if (idx == arr.length) {
            return new int[count];
        }

        if (arr[idx] == key) {
            int[] left = allIndices(arr, idx + 1, key, count + 1);
            left[count] = idx;
            return left;
        } else {
            int[] right = allIndices(arr, idx + 1, key, count);
            return right;
        }
    }

    /////////////////////////////////  ARRAYLISTS ////////////////////////////////////////////


    // get subsequence
    // f(abc) = (_empty + f(bc)) + (a + f(bc));
    public static ArrayList<String> getSubsequence(String s) {
        if (s.length() == 0) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        char left = s.charAt(0);
        String right = s.substring(1);

        // faith on bc to bring his ss
        ArrayList<String> ans = getSubsequence(right);

        ArrayList<String> curr = new ArrayList<>();

        for (String str: ans) {
            curr.add("" + str);
        }

        for (String str: ans) {
            curr.add(left + str);
        }

        return curr;
    }

    // get keypad codes
    static String[] combi = {".:", "abc", "def", "ghi", "jkl", "mnop", "qrst", "uv", "wxyz"};
    public static ArrayList<String> kpc(String s) {
        
        if (s.length() == 0) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> left = kpc(s.substring(1));

        ArrayList<String> ans = new ArrayList<>();
        String code = combi[s.charAt(0) - '0'];

        for (int i = 0; i < code.length(); i++) {
            char chcode = code.charAt(i);

            for (String l: left) {
                ans.add(chcode + l);
            }
        }

        return ans;
    }

    // get stairs paths
    // he can take [1, 2, 3] steps at a time
    // t(1) = 1
    // t(2) = 1 t(1) + 2 t(2) + 3 t(3)
    public static ArrayList<String> getStairsPath(int n) {
        if (n <= 0) {
            ArrayList<String> baseAns = new ArrayList<>();
            if (n == 0) {
                // basically adding this empty string will help to execute the below code
                baseAns.add(" ");
                // this "" means that there is a path for particular values
            } 
            return baseAns;
        }

        ArrayList<String> oneStep = getStairsPath(n - 1);
        ArrayList<String> twoSteps = getStairsPath(n - 2);
        ArrayList<String> threeSteps = getStairsPath(n - 3);


        ArrayList<String> ans = new ArrayList<>();
        for (String s: oneStep) {
            ans.add(1 + s);
        }

        for (String s: twoSteps) {
            ans.add(2 + s);
        }

        for (String s: threeSteps) {
            ans.add(3 + s);
        }

        return ans;
    }

    // get maza paths
    public static ArrayList<String> mazePaths(int r, int c, int fr, int fc) {
        if (r > fr || c > fc) {
            // out of bounds (1, 1) to (r, c)
            ArrayList<String> base = new ArrayList<>();
            return base;
        }

        if (r == fr && c == fc) {
            ArrayList<String> base = new ArrayList<>();
            // it is a valid path
            base.add(" ");
            return base;
        }

        ArrayList<String> verti = mazePaths(r + 1, c, fr, fc);
        ArrayList<String> hori = mazePaths(r, c + 1, fr, fc);

        ArrayList<String> paths = new ArrayList<>();

        for (String s: verti) {
            paths.add("v" + s);
        }
        // path from verti and add v effort to it

        for (String s: hori) {
            paths.add("h" + s);
        }
        // path from hori and add h effort to it
        return paths;
    }

    // get maze paths with jumps
    public static ArrayList<String> mazePathsWithJumps(int r, int c, int fr, int fc) {

        if (r > fr || c > fc) {
            ArrayList<String> base = new ArrayList<>();
            return base;
        }

        if (r == fr && c == fc) {
            ArrayList<String> base = new ArrayList<>();
            base.add(" ");
            return base;
        }

        ArrayList<String> ans = new ArrayList<>();

        for (int i = 1; i <= fr - r; i++) {
            // this will prevent some calls
            ArrayList<String> v = mazePathsWithJumps(r + i, c, fr, fc);
            for (String s: v) {
                ans.add("v" + i + s);
            }
        }

        for (int j = 1; j <= fc - c; j++) {
            // this will prevent some calls
            ArrayList<String> h = mazePathsWithJumps(r, c + j, fr, fc);
            for (String s: h) {
                ans.add("h" + j + s);
            }
        }

        for (int k = 1; (k <= fr - r && k <= fc - c); k++) {
            ArrayList<String> d = mazePathsWithJumps(r + 1, c + 1, fr, fc);
            for (String s: d) {
                ans.add("d" + k + s);
            }
        }

        return ans;
    }

    ////// PRINT ON ThE WAY OF RECURSION ///////

    // print subsequence
    public static void printSubsequence(String s, String ans) {
        if (s.length() == 0) {
            System.out.print(ans + " ");
            return;
        }

        char ch = s.charAt(0);
        String remaing = s.substring(1);
        // yes call
        printSubsequence(remaing, ans + ch);

        // no call
        printSubsequence(remaing, ans);
    }

    // print keypad combinations
    public static void printKpc(String s, String ans) {
        if (s.length() <= 0) {
            System.out.print(ans + " ");
            return;
        }

        char ch = s.charAt(0);
        String remaining = s.substring(1);

        String code = combi[ch - '0'];

        for (int i = 0; i < code.length(); i++) {
            printKpc(remaining, ans + code.charAt(i));
        }
    }

    // preorder recursion: do current level work and assume for next levels
    // postorder recursion: have faith that other levels will bring desired result and do work for current level

    public static void printStairPaths(int n, String ans) {
        if (n < 0) {
            // not a valid path
            return;
        }

        if (n == 0) {
            // valid path to reach ground
            System.out.print(ans + " ");
        }
        
        printStairPaths(n - 1, ans + "1");
        printStairPaths(n - 2, ans + "2");
        printStairPaths(n - 3, ans + "3");
    }

    // print maze paths 
    public static void printMazePaths(int dr, int dc, int sr, int sc, String ans) {
        if (sr > dr || sc > dc) {
            return;
        }

        if (sr == dr && sc == dc) {
            System.out.print(ans + " ");
            return;
        }
        
        printMazePaths(dr, dc, sr + 1, sc, ans + 'h');
        printMazePaths(dr, dc, sr,  sc + 1, ans + 'v');
    }

    public static void printMazePathsWithJumps(int dr, int dc, int sr, int sc, String ans) {
        if (sr > dr || sc > dc) {
            return;
        }

        if (sr == dr && sc == dc) {
            System.out.print(ans + " ");
            return;
        }


        for (int i = 1; sr + i <= dr; i++) {
            printMazePathsWithJumps(dr, dc, sr + i, sc, ans + "v" + i);
        }

        for (int j = 1; sc + j <= dc; j++) {
            printMazePathsWithJumps(dr, dc, sr, sc + j, ans + "h" + j);
        }

        for (int k = 1; (sc + k <= dc && sr + k <= dr); k++) {
            printMazePathsWithJumps(dr, dc, sr + k, sc + k, ans + "d" + k);
        }

    }


    /// PERMUTATIONS group

    public static void printPermutations(String s, String ans) {
        if (s.length() <= 0) {
            System.out.print(ans + " ");
            return;
        }

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            String left = s.substring(0, i);
            String right = s.substring(i + 1);
            printPermutations(left + right, ans + ch);
        }
    }

    public static void printEncodings(String s, String ans) {
        if (s.length() == 0) {
            System.out.print(ans + " ");
        } else if (s.length() == 1) {
            char ch = s.charAt(0);
            if (ch == '0') {
                return;
            } else {
                // print the corresponding code
                int chv = ch - '0';
                char code = (char)('a' + chv - 1);
                // chv = 2 then code = b: but a + 2 = 3 - 1 = 2;
                System.out.print(ans + code + " ");
                return;
            }
        } else {
            char ch = s.charAt(0);
            String rem = s.substring(1);

            if (ch == '0') {
                return;
            } else {
                // print the corresponding code
                int chv = ch - '0';
                char code = (char)('a' + chv - 1);
                printEncodings(rem, ans + code);

                String ch12 = s.substring(0, 2);
                String rem2 = s.substring(2);

                int ch12v = Integer.parseInt(ch12);

                if (ch12v <= 26) {
                    char code12 = (char)('a' + ch12v - 1);
                    printEncodings(rem2, ans + code12);
                }
            }
        }
    }

    // flood fill
    public static void floodfill(int[][] arr, int sr, int sc, String ans) {
        
        if (sr < 0 || sc < 0 || sr == arr.length || sc == arr[0].length || arr[sr][sc] == 1 || arr[sr][sc] == 2) {
            return;
        }

        if (sr == arr.length - 1 && sc == arr[0].length - 1) {
            System.out.print(ans + " ");
            return;
        }

        arr[sr][sc] = 2;

        floodfill(arr, sr - 1, sc, ans + "t");
        floodfill(arr, sr, sc + 1, ans + "r");
        floodfill(arr, sr + 1, sc, ans + "d");
        floodfill(arr, sr, sc - 1, ans + "l");
        
        arr[sr][sc] = 0;
    }

    //////////////////// SUBSET QUESTIONS //////////////////////////////
 
    public static void subsetSumK(int[] arr, int idx, int tar, String ans) {
        if (idx >= arr.length) {
            if (tar == 0) {
                System.out.print(ans + " ");
            }
            return;
        }

        subsetSumK(arr, idx + 1, tar - arr[idx], ans + arr[idx] + " ");
        subsetSumK(arr, idx + 1, tar, ans);

    } 

    // N Queens
    public static void nQueens(int[][] arr, int r, String ans) {

        if (r == arr.length) {
            System.out.print(ans + " ");
            return;
        }

        for (int i = 0; i < arr[0].length; i++) {
            if (isQueenSafe(arr, r, i)) {
                arr[r][i] = 1;
                nQueens(arr, r + 1, ans + r + "-" + i + ", ");
                arr[r][i] = 0;
            }
        }
    }

    public static Boolean isQueenSafe(int[][] arr, int r, int c) {
        for (int i = r - 1; i >= 0; i--) {
            if (arr[i][c] == 1) return false;
        }

        for (int i = r - 1, j = c - 1; i >= 0 && j >= 0; i--, j--) {
            if (arr[i][j] == 1) return false;
        }

        for (int i = r - 1, j = c + 1; i >= 0 && j < arr[0].length; i++, j++) {
            if (arr[i][j] == 1) return false;
        }

        return true;
    }

    //  given n X n chess board and given start point then print all ways in which it will travel all boxes
    // without visiting any cells twice

    public static void knightPaths(int[][] arr, int r, int c, int move) {
        if (r < 0 || c < 0 || r >= arr.length || c >= arr[0].length || arr[r][c] > 0) {
            return;
        }

        if (move == (arr.length * arr[0].length)) {
            arr[r][c] = move;
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[0].length; j++) {
                    System.out.print(arr[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
            arr[r][c] = 0;
            return;
        }
        
        arr[r][c] = move;

        knightPaths(arr, r - 2, c + 1, move + 1);
        knightPaths(arr, r - 1, c + 2, move + 1);
        knightPaths(arr, r + 1, c + 2, move + 1);
        knightPaths(arr, r + 2, c + 1, move + 1);
        knightPaths(arr, r + 2, c - 1, move + 1);
        knightPaths(arr, r + 1, c - 2, move + 1);
        knightPaths(arr, r - 1, c - 2, move + 1);
        knightPaths(arr, r - 2, c - 1, move + 1);

        arr[r][c] = 0;

    }


    public static void main (String[] args) {
        // int arr[] = new int[]{8, 3, 9, -1, 7, 6, 8, 12, 7, 3, 8};
        // int ans = lastOcc(arr, 0, 8);
        // System.out.print(ans);
        // toh(3, "a", "b", "c");/

        // int[] ans = allIndices(arr, 0, 8, 0);
        // for (int val: ans) {
        //     System.out.print(val + " ");
        // }

        int[][] arr = new int[5][5];
        knightPaths(arr, 2, 2, 1);

        // System.out.println(printSubsequence(1, 1, 3, 3));
    }
}