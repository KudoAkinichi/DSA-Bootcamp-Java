package com.kunal.bitwise;

import java.util.Scanner;

public class PascalsTriangleSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input: Row number (1-based index)
        System.out.print("Enter the row number (1-based index): ");
        int n = scanner.nextInt();

        // Calculate the sum of the n-th row
        int sum = 1 << (n - 1); // Compute 2^(n-1) using left shift

        System.out.println("The sum of row " + n + " in Pascal's Triangle is: " + sum);

        scanner.close();
    }
}
