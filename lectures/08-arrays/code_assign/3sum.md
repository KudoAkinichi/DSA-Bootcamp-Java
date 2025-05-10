Here's a complete **Markdown (MD)** document that includes all **three approaches** to solving the **3Sum problem**, along with **code, explanation, complexity analysis, and a final comparison table**:

---

# 🧠 3Sum Problem – All Approaches with Comparison

**Problem Statement:**

Given an integer array `nums`, return all the triplets `[nums[i], nums[j], nums[k]]` such that:

* `i != j`, `i != k`, and `j != k`
* `nums[i] + nums[j] + nums[k] == 0`

The solution must not contain **duplicate triplets**.

Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.

Notice that the solution set must not contain duplicate triplets.

Example 1:

Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
Explanation:
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
The distinct triplets are [-1,0,1] and [-1,-1,2].
Notice that the order of the output and the order of the triplets does not matter.
Example 2:

Input: nums = [0,1,1]
Output: []
Explanation: The only possible triplet does not sum up to 0.
Example 3:

Input: nums = [0,0,0]
Output: [[0,0,0]]
Explanation: The only possible triplet sums up to 0.

Constraints:

3 <= nums.length <= 3000
-105 <= nums[i] <= 105

---

## 🔍 Example

```text
Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
```

---

## ✅ Approach 1: Brute Force (3 Nested Loops)

### 👨‍💻 Code

```java
import java.util.*;

public class ThreeSumBruteForce {
    public static List<List<Integer>> triplet(int n, int[] arr) {
        Set<List<Integer>> st = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (arr[i] + arr[j] + arr[k] == 0) {
                        List<Integer> temp = Arrays.asList(arr[i], arr[j], arr[k]);
                        Collections.sort(temp);
                        st.add(temp);
                    }
                }
            }
        }
        return new ArrayList<>(st);
    }
}
```

### 📊 Time & Space Complexity

* **Time:** O(N³ × logM), where M = number of unique triplets
* **Space:** O(M) (set + result list)

---

## ⚡ Approach 2: HashSet (2 Loops + Lookup)

### 💡 Intuition

Instead of using 3 loops, use a `HashSet` to lookup the third number in O(1) time.

### 👨‍💻 Code

```java
import java.util.*;

public class ThreeSumHashSet {
    public static List<List<Integer>> triplet(int n, int[] arr) {
        Set<List<Integer>> st = new HashSet<>();

        for (int i = 0; i < n; i++) {
            Set<Integer> seen = new HashSet<>();
            for (int j = i + 1; j < n; j++) {
                int third = -(arr[i] + arr[j]);
                if (seen.contains(third)) {
                    List<Integer> temp = Arrays.asList(arr[i], arr[j], third);
                    Collections.sort(temp);
                    st.add(temp);
                }
                seen.add(arr[j]);
            }
        }

        return new ArrayList<>(st);
    }
}
```

### 📊 Time & Space Complexity

* **Time:** O(N² × logM), where M = number of unique triplets
* **Space:** O(N + M)

---

## 🚀 Approach 3: Two Pointers (Optimized)

### 💡 Intuition

Sort the array, fix one element, and use two pointers to find the other two.

### 👨‍💻 Code

```java
import java.util.*;

public class ThreeSumTwoPointers {
    public static List<List<Integer>> triplet(int n, int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // skip duplicates
            int j = i + 1;
            int k = n - 1;

            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++; k--;
                    while (j < k && nums[j] == nums[j - 1]) j++; // skip duplicates
                    while (j < k && nums[k] == nums[k + 1]) k--; // skip duplicates
                } else if (sum < 0) {
                    j++;
                } else {
                    k--;
                }
            }
        }

        return res;
    }
}
```

### 📊 Time & Space Complexity

* **Time:** O(N²)
* **Space:** O(M) (result list)

---

## 📌 Comparison Table

| Feature / Approach        | Brute Force (3 Loops) | HashSet (2 Loops)  | Two Pointers (Optimized) |
| ------------------------- | --------------------- | ------------------ | ------------------------ |
| **Time Complexity**       | O(N³)                 | O(N²)              | O(N²)                    |
| **Space Complexity**      | O(M)                  | O(N + M)           | O(M)                     |
| **Handles Duplicates**    | Yes (via Set)         | Yes (via Set)      | Yes (via skipping)       |
| **Sorting Needed?**       | No                    | No                 | Yes                      |
| **Best For**              | Learning, small data  | Medium input sizes | Optimal for interviews   |
| **Worst-case Efficiency** | Very Slow             | Moderate           | Fastest                  |

---

## 🏁 Conclusion

* **Use Brute Force** for small arrays and concept clarity.
* **Use HashSet Method** if sorting is not desirable.
* **Use Two-Pointers** for best performance and clean output without duplicates.

---

Would you like a downloadable `.md` or `.pdf` file of this summary?
