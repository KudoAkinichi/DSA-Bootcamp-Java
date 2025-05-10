Here's a complete **Markdown (MD)** document covering **Roman to Integer** problem with:

* **2 Optimal Solutions** (Greedy traversal with map and lookahead)
* **1 Brute Force Solution**
* Explanation, time & space complexity, and comparison table

---

# 🔢 Roman to Integer – All Approaches with Comparison

**Problem Statement:**
Convert a Roman numeral to an integer. Roman numerals follow rules including special subtractive notations like `IV = 4`, `IX = 9`, etc.

---

## 🧠 Roman Symbols

| Symbol | Value |
| ------ | ----- |
| I      | 1     |
| V      | 5     |
| X      | 10    |
| L      | 50    |
| C      | 100   |
| D      | 500   |
| M      | 1000  |

---

## ✅ Optimal Approach 1: Greedy Traversal with Lookahead

### 💡 Idea

* Loop through the string.
* If a smaller value comes before a larger one → subtract.
* Else → add.

### 👨‍💻 Java Code

```java
import java.util.*;

public class RomanToIntegerGreedy {
    public static int romanToInt(String s) {
        Map<Character, Integer> map = Map.of(
            'I', 1, 'V', 5, 'X', 10,
            'L', 50, 'C', 100, 'D', 500, 'M', 1000
        );

        int total = 0;
        for (int i = 0; i < s.length(); i++) {
            int val = map.get(s.charAt(i));
            if (i + 1 < s.length() && val < map.get(s.charAt(i + 1))) {
                total -= val;
            } else {
                total += val;
            }
        }
        return total;
    }
}
```

### 📊 Complexity

* **Time:** O(n)
* **Space:** O(1)

---

## ✅ Optimal Approach 2: Backward Traversal with Max Right

### 💡 Idea

* Traverse from end.
* Maintain the max value seen.
* If current < max → subtract, else add.

### 👨‍💻 Java Code

```java
import java.util.*;

public class RomanToIntegerRightMax {
    public static int romanToInt(String s) {
        Map<Character, Integer> map = Map.of(
            'I', 1, 'V', 5, 'X', 10,
            'L', 50, 'C', 100, 'D', 500, 'M', 1000
        );

        int total = 0;
        int maxRight = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            int curr = map.get(s.charAt(i));
            if (curr < maxRight) {
                total -= curr;
            } else {
                total += curr;
                maxRight = curr;
            }
        }

        return total;
    }
}
```

### 📊 Complexity

* **Time:** O(n)
* **Space:** O(1)

---

## 🐢 Brute Force Approach: String Matching with Full Map

### 💡 Idea

* Match subtractive pairs (`IV`, `IX`, etc.) first.
* Then parse remaining one-by-one.

### 👨‍💻 Java Code

```java
import java.util.*;

public class RomanToIntegerBruteForce {
    public static int romanToInt(String s) {
        Map<String, Integer> map = new HashMap<>();
        map.put("I", 1); map.put("V", 5); map.put("X", 10);
        map.put("L", 50); map.put("C", 100); map.put("D", 500); map.put("M", 1000);
        map.put("IV", 4); map.put("IX", 9); map.put("XL", 40);
        map.put("XC", 90); map.put("CD", 400); map.put("CM", 900);

        int total = 0;
        int i = 0;

        while (i < s.length()) {
            if (i + 1 < s.length() && map.containsKey(s.substring(i, i + 2))) {
                total += map.get(s.substring(i, i + 2));
                i += 2;
            } else {
                total += map.get(String.valueOf(s.charAt(i)));
                i++;
            }
        }

        return total;
    }
}
```

### 📊 Complexity

* **Time:** O(n)
* **Space:** O(1)

---

## 📌 Comparison Table

| Feature / Approach        | Greedy Lookahead | Right-to-Left Max | Brute Force |
| ------------------------- | ---------------- | ----------------- | ----------- |
| **Time Complexity**       | O(n)             | O(n)              | O(n)        |
| **Space Complexity**      | O(1)             | O(1)              | O(1)        |
| **Handles Subtractives?** | Yes              | Yes               | Yes         |
| **Code Simplicity**       | High             | Moderate          | Low         |
| **Best Use Case**         | Optimal speed    | Alternative fast  | Learning    |
| **Map Size**              | 7                | 7                 | 13          |

---

## 🏁 Conclusion

* Use **Greedy Lookahead** for clarity and performance.
* **Right-to-left Max** is a neat variation worth remembering.
* **Brute Force** helps understand the mechanics but is less clean.

---

Let me know if you want this in `.md`, `.pdf`, or ready to paste into your notes!
