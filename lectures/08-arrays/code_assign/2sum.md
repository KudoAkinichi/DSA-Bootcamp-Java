Here’s a clean and well-documented `README.md` file for the **Two Sum** problem, suitable for GitHub or personal repositories:

````markdown
# 🧮 Two Sum - LeetCode Problem

Given an array of integers `nums` and an integer `target`, return **indices** of the two numbers such that they add up to `target`.

You may assume that **each input** would have **exactly one solution**, and you **may not use the same element twice**.

You can return the answer in **any order**.

---

## 🧪 Examples

### Example 1
```text
Input: nums = [2,7,11,15], target = 9  
Output: [0,1]  
Explanation: nums[0] + nums[1] == 9 → return [0, 1]
````

### Example 2

```text
Input: nums = [3,2,4], target = 6  
Output: [1,2]
```

### Example 3

```text
Input: nums = [3,3], target = 6  
Output: [0,1]
```

---

## ✅ Constraints

* 2 <= nums.length <= 10⁴
* -10⁹ <= nums\[i] <= 10⁹
* -10⁹ <= target <= 10⁹
* Only **one valid answer** exists

---

## 💡 Approaches

### 🔹 Brute Force - O(n²) Time | O(1) Space

Check each pair of numbers to see if they add up to the target.

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        for(int i = 0; i < n - 1; i++ ){
            for(int j = i + 1; j < n; j++){
                if(nums[i] + nums[j] == target){
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{}; // No match found
    }
}
```

---

### 🔹 Optimal - O(n) Time | O(n) Space

Use a HashMap to store values and their indices as you iterate, and check if the complement exists.

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
       Map<Integer, Integer> numMap = new HashMap<>();
       int n = nums.length;

       for (int i = 0; i < n; i++){
           numMap.put(nums[i], i);
       }

       for (int i = 0; i < n; i++){
           int complement = target - nums[i];
           if (numMap.containsKey(complement) && numMap.get(complement) != i) {
               return new int[]{i, numMap.get(complement)};
           }
       }

       return new int[]{}; // No valid pair found
    }
}
```

---

## 📘 Notes

* The problem guarantees **exactly one solution**, so we do not handle edge cases like duplicates with multiple valid answers.
* The HashMap approach is significantly faster for large arrays.

---

## 🏷️ Tags

`Array` `HashMap` `Two Pointers` `Brute Force` `Optimization`

---

## 📌 Related Problems

* [1. Two Sum](https://leetcode.com/problems/two-sum/)
* [167. Two Sum II - Input Array Is Sorted](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/)
* [15. 3Sum](https://leetcode.com/problems/3sum/)

```

Would you like a visual diagram (flowchart or dry run) to help explain the logic?
```
