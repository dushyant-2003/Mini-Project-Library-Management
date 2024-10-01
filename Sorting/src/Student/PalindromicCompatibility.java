package Student;

import java.util.*;

public class PalindromicCompatibility {
    public static void main(String[] args) {
        int n = 2; // length of the array
        int k = 2; // maximum number of bits

        long result = calculatePalindromicAllyCompatibleArrays(n, k);
        System.out.println("Total number of possible arrays: " + result);
    }

    public static long calculatePalindromicAllyCompatibleArrays(int n, int k) {
        long result = 1;

        // Calculate (2^k - 1)^n
        for (int i = 0; i < n; i++) {
            result *= (1L << k) - 1;
        }

        // Calculate 2^k
        result *= (1L << k);

        return result;
    }
}

