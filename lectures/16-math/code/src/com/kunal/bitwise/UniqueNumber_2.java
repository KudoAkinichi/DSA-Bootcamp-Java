package com.kunal.bitwise;

import java.util.Scanner;

public class UniqueNumber_2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user for array input
        System.out.println("Enter the number of elements in the array:");
        int n = scanner.nextInt();
        int[] arr = new int[n];

        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        System.out.println("The number that does not appear three times is: " + findSingleNumber(arr));

        scanner.close();
    }

    public static int findSingleNumber(int[] nums) {
        int result = 0; // This variable will store the single occurring number.

        // We iterate through each bit position in a number (assuming 32-bit integers).
        for (int i = 0; i < 32; i++) {
            int sum = 0; // This will store the sum of bits at position i across all numbers.

            // We go through each number in the array and count the bits at position i.
            for (int num : nums) {
                sum += (num >> i) & 1;
                // (num >> i) shifts the bits of num to the right by i positions.
                // The "& 1" operation isolates the bit at position i.
                // We add the isolated bit to the sum.
            }

            sum %= 3; // Taking the sum modulo 3 eliminates bits appearing in triplets.

            // Now, we set the corresponding bit in the 'result' using bitwise OR operation.
            // We shift the calculated sum by i positions and then set the bit in 'result'.
            result |= (sum << i);
        }

        return result; // The 'result' now holds the single occurring number.
    }
}
