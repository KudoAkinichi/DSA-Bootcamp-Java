Here’s a **complete Markdown (MD) document** with:

* **Optimal Approach (Kadane’s Algorithm – O(n))**
* **Divide and Conquer Approach (O(n log n))**
* **Brute Force Approach (O(n²) / O(n³))**
* **Comparison Table**
* **Explanations + Java Code**

---

# 🔥 Maximum Subarray – All Approaches with Comparison

**Problem:**
Given an integer array `nums`, find the contiguous subarray with the largest sum and return its sum.

---

## 🧪 Example

```txt
Input: nums = [-2,1,-3,4,-1,2,1,-5,4]  
Output: 6  
Explanation: [4,-1,2,1] has the largest sum.
```

---

## ✅ Optimal Approach: Kadane’s Algorithm

### 💡 Idea

* Track the **max sum so far** and **current sum**.
* If current sum becomes negative, reset to 0.

### 👨‍💻 Java Code

```java
public class MaximumSubarrayKadane {
    public int maxSubArray(int[] nums) {
        int maxSoFar = nums[0];
        int currSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currSum = Math.max(nums[i], currSum + nums[i]);
            maxSoFar = Math.max(maxSoFar, currSum);
        }
        return maxSoFar;
    }
}
```

### 📊 Complexity

* **Time:** O(n)
* **Space:** O(1)

---

## ⚔️ Divide and Conquer Approach

### 💡 Idea

* Divide into two halves.
* Recursively compute:

  * Max in left half.
  * Max in right half.
  * Max crossing middle.

### 👨‍💻 Java Code

```java
public class MaximumSubarrayDivideConquer {
    public int maxSubArray(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    private int helper(int[] nums, int left, int right) {
        if (left == right) return nums[left];

        int mid = (left + right) / 2;

        int leftSum = helper(nums, left, mid);
        int rightSum = helper(nums, mid + 1, right);
        int crossSum = cross(nums, left, mid, right);

        return Math.max(Math.max(leftSum, rightSum), crossSum);
    }

    private int cross(int[] nums, int left, int mid, int right) {
        int leftMax = Integer.MIN_VALUE, sum = 0;
        for (int i = mid; i >= left; i--) {
            sum += nums[i];
            leftMax = Math.max(leftMax, sum);
        }

        int rightMax = Integer.MIN_VALUE;
        sum = 0;
        for (int i = mid + 1; i <= right; i++) {
            sum += nums[i];
            rightMax = Math.max(rightMax, sum);
        }

        return leftMax + rightMax;
    }
}
```

### 📊 Complexity

* **Time:** O(n log n)
* **Space:** O(log n) (for recursion stack)

---

## 🐢 Brute Force Approach

### 💡 Idea

* Check all subarrays and track the maximum.

### 👨‍💻 Java Code (O(n²))

```java
public class MaximumSubarrayBrute {
    public int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            int currSum = 0;
            for (int j = i; j < nums.length; j++) {
                currSum += nums[j];
                maxSum = Math.max(maxSum, currSum);
            }
        }
        return maxSum;
    }
}
```

### 📊 Complexity

* **Time:** O(n²)
* **Space:** O(1)

---

## 📌 Comparison Table

| Approach           | Time Complexity | Space Complexity | Suitable For           | Notes                    |
| ------------------ | --------------- | ---------------- | ---------------------- | ------------------------ |
| Kadane’s Algorithm | O(n)            | O(1)             | Optimal cases          | Most efficient           |
| Divide and Conquer | O(n log n)      | O(log n)         | Practice recursion     | Elegant but slower       |
| Brute Force        | O(n²) or O(n³)  | O(1)             | Learning purposes only | Avoid in production code |

---

## 🏁 Conclusion

* ✅ **Use Kadane’s Algorithm** for best performance.
* ⚔️ **Try Divide & Conquer** to practice recursive strategies.
* 🐢 **Understand Brute Force** to build logic from scratch.

---

Would you like a downloadable `.md` or `.pdf` version of this with syntax highlighting?
