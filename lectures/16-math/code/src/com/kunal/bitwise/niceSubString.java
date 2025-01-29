package com.kunal.bitwise;

import java.util.*;

public class niceSubString {

    public static void main(String[] args) {
        System.out.println("");
        System.out.println(longestNiceSubstring("YazaAay"));
    }

    public static String longestNiceSubstring(String s) {
        if (s.length() < 2)
            return "";

        Set<Character> CS = new HashSet<>();
        for (char c : s.toCharArray()) {
            CS.add(c);
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (!CS.contains(Character.toLowerCase(c)) || !CS.contains(Character.toUpperCase(c))) {
                String left = longestNiceSubstring(s.substring(0, i));
                String Right = longestNiceSubstring(s.substring(i + 1));

                return left.length() >= Right.length() ? left : Right;
            }
        }
        return s;
    }
}
