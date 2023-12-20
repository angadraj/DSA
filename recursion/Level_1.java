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
    public static ArrayList<String> getStaisPath(int n) {
        if (n <= 0) {
            ArrayList<String> baseAns = new ArrayList<>();
            if (n == 0) {
                // basically adding this empty string will help to execute the below code
                baseAns.add(" ");
                // this "" means that there is a path for particular values
            } 
            return baseAns;
        }

        ArrayList<String> oneStep = getStaisPath(n - 1);
        ArrayList<String> twoSteps = getStaisPath(n - 2);
        ArrayList<String> threeSteps = getStaisPath(n - 3);


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

    public static void main (String[] args) {
        // int arr[] = new int[]{8, 3, 9, -1, 7, 6, 8, 12, 7, 3, 8};
        // int ans = lastOcc(arr, 0, 8);
        // System.out.print(ans);
        // toh(3, "a", "b", "c");/

        // int[] ans = allIndices(arr, 0, 8, 0);
        // for (int val: ans) {
        //     System.out.print(val + " ");
        // }

        printSubsequence("abc", "");

        // System.out.println(printSubsequence(1, 1, 3, 3));
    }
}