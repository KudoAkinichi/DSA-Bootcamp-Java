Here’s a **well-structured Markdown (MD)** explanation for **Leetcode 198 – House Robber**, with:

1. 🧠 **Intuitive Explanation** for non-DP users
2. 💡 **Dynamic Programming (DP) Approach**
3. ✅ **Java Code for Both**
4. 📊 **Comparison Table**

---

# 🏠💰 Leetcode 198 – House Robber

## 🧩 Problem

You're given a list of non-negative integers, where each number represents the money in a house. You **can’t rob two adjacent houses**. Return the **maximum amount** of money you can rob **without triggering alarms**.

---

## 🧪 Examples

```txt
Input:  [1,2,3,1]      → Output: 4    (Rob house 1 and 3 → 1+3)
Input:  [2,7,9,3,1]    → Output: 12   (Rob house 1, 3, and 5 → 2+9+1)
```

---

## 👶 Approach 1: Intuitive Solution (For Beginners – No DP Knowledge)

### 🔍 Idea

Try **every valid combination** of houses you can rob where **no two are adjacent**, and return the combination with the **maximum total**.

### ⛔ Problem

This leads to **exponential time** – not good for large input.

### ✅ Simplified Version Using Recursion

```java
public class HouseRobberRecursive {
    public int rob(int[] nums) {
        return helper(nums, 0);
    }

    private int helper(int[] nums, int i) {
        if (i >= nums.length) return 0;

        // Option 1: Rob current house and move to i+2
        int rob = nums[i] + helper(nums, i + 2);

        // Option 2: Skip current house and move to i+1
        int skip = helper(nums, i + 1);

        return Math.max(rob, skip);
    }
}
```

### ❗ Time Complexity

* **O(2ⁿ)** → For each house, we branch into 2 options (rob/skip)

---

## 💡 Approach 2: Dynamic Programming

### 🧠 Core Idea

Avoid re-computation by storing already computed values.

At each house `i`, we choose the **maximum** between:

* Robbing it → `nums[i] + dp[i-2]`
* Skipping it → `dp[i-1]`

### 🔁 Recurrence

```java
dp[i] = max(dp[i-1], nums[i] + dp[i-2])
```

### ✅ Java Code – DP Array

```java
public class HouseRobberDP {
    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i-1], nums[i] + dp[i-2]);
        }

        return dp[nums.length - 1];
    }
}
```

---

## 🧠 Bonus: Optimized DP (Only 2 variables)

### ✅ Java Code

```java
public class HouseRobberOptimized {
    public int rob(int[] nums) {
        int prev2 = 0, prev1 = 0;
        for (int num : nums) {
            int curr = Math.max(prev1, num + prev2);
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }
}
```

---

## 📊 Comparison Table

| Approach          | Time Complexity | Space Complexity | Suitable For            |
| ----------------- | --------------- | ---------------- | ----------------------- |
| Recursive (No DP) | O(2ⁿ)           | O(n)             | Beginners to understand |
| DP Array          | O(n)            | O(n)             | Intermediate            |
| Optimized DP      | O(n)            | O(1) ✅           | Best/Production use     |

---

## 🏁 Summary

* Start with **recursion** if you're new.
* Move to **DP** for efficiency.
* Use **space-optimized DP** for best performance.

Would you like me to format this in downloadable `.md` format or continue with the next related problem like **House Robber II** (circular houses)?
