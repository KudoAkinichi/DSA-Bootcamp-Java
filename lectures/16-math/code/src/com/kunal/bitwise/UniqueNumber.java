package com.kunal.bitwise;

import java.util.Scanner;

public class UniqueNumber {
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

        System.out.println("The number that does not appear three times is: " + findUniqueNumber(arr));

        scanner.close();
    }

    public static int findUniqueNumber(int[] nums) {
        int ones = 0, twos = 0;

        for (int num : nums) {
            // Update `twos` with bits that appear in both `ones` and the current number
            twos |= ones & num;

            // XOR the current number with `ones`
            ones ^= num;

            // Determine the mask to remove bits that appear three times
            int threes = ones & twos;

            // Remove the bits that appear three times from `ones` and `twos`
            ones &= ~threes;
            twos &= ~threes;
        }

        // At this point, `ones` contains the number that appears only once
        return ones;
    }
}
