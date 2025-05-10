Here's a full **Markdown (MD)** solution document for **Leetcode 169 – Majority Element** covering:

* **Brute Force**
* **HashMap (Better)**
* **Boyer-Moore Voting Algorithm (Optimal)**
* Java code for all
* A **comparison table** and notes

---

# 🗳️ Majority Element – All Approaches with Comparison

**Problem:**
Return the element that appears **more than ⌊n/2⌋ times** in an array `nums`.
You may assume such element **always exists**.

---

## 🧪 Example

```txt
Input:  [3,2,3]
Output: 3

Input:  [2,2,1,1,1,2,2]
Output: 2
```

---

## 🚀 1. Brute Force

### 💡 Idea

* Count each element’s frequency using two nested loops.
* Return the element with count > n/2.

### 👨‍💻 Java Code

```java
public class MajorityElementBrute {
    public int majorityElement(int[] nums) {
        int majorityCount = nums.length / 2;

        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] == nums[i]) count++;
            }
            if (count > majorityCount) return nums[i];
        }
        return -1; // Won't be hit due to constraint
    }
}
```

### 📊 Complexity

* **Time:** O(n²)
* **Space:** O(1)

---

## 🧠 2. Better: HashMap Frequency Count

### 💡 Idea

* Use a HashMap to count occurrences.
* Return the element whose frequency > n/2.

### 👨‍💻 Java Code

```java
import java.util.*;

public class MajorityElementHashMap {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int majority = nums.length / 2;

        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
            if (countMap.get(num) > majority) return num;
        }

        return -1;
    }
}
```

### 📊 Complexity

* **Time:** O(n)
* **Space:** O(n)

---

## 🥇 3. Optimal: Boyer-Moore Voting Algorithm

### 💡 Idea

* Maintain a **candidate** and a **count**.
* Increment count when current equals candidate, else decrement.
* Reset candidate when count is 0.

### 👨‍💻 Java Code

```java
public class MajorityElementOptimal {
    public int majorityElement(int[] nums) {
        int candidate = nums[0];
        int count = 1;

        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                candidate = nums[i];
                count = 1;
            } else if (nums[i] == candidate) {
                count++;
            } else {
                count--;
            }
        }

        return candidate;
    }
}
```

### 📊 Complexity

* **Time:** O(n)
* **Space:** O(1) ✅

---

## 📌 Comparison Table

| Approach           | Time Complexity | Space Complexity | Notes                           |
| ------------------ | --------------- | ---------------- | ------------------------------- |
| Brute Force        | O(n²)           | O(1)             | Not recommended for large input |
| HashMap Frequency  | O(n)            | O(n)             | Easy to implement               |
| Boyer-Moore Voting | O(n) ✅          | O(1) ✅           | Most optimal & elegant solution |

---

## ✅ Conclusion

* 🔁 Use **HashMap** for quick development.
* 🥇 Use **Boyer-Moore** for best performance (asked in interviews).
* ❌ Avoid **Brute Force** unless explicitly requested.

---

Let me know if you'd like a combined `.md` file for this and other problems you're preparing.
