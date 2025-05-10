Here's a complete **Markdown (MD)** document covering the **Remove Element** problem with:

* Optimal and alternate approaches
* Brute force for understanding
* Code in **Java**
* **Comparison table**
* Explanations

---

# 🧹 Remove Element – All Approaches with Comparison

**Problem:**
Given an array `nums` and a value `val`, remove all instances of that value **in-place** and return the new length `k`. Modify the input array such that the first `k` elements do not contain `val`.

---

## 🔍 Example

```txt
Input:  nums = [3,2,2,3], val = 3  
Output: 2  
Modified nums: [2,2,_,_]

Input:  nums = [0,1,2,2,3,0,4,2], val = 2  
Output: 5  
Modified nums: [0,1,4,0,3,_,_,_]
```

---

## ✅ Optimal Approach: Two-Pointer (In-Place)

### 💡 Idea

* Maintain a pointer `k` that tracks the position to overwrite.
* Iterate through `nums`. For elements not equal to `val`, assign them to `nums[k++]`.

### 👨‍💻 Java Code

```java
public class RemoveElementOptimal {
    public int removeElement(int[] nums, int val) {
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[k++] = nums[i];
            }
        }
        return k;
    }
}
```

### 📊 Complexity

* **Time:** O(n)
* **Space:** O(1)

---

## 🔁 Alternate Optimal: Swap with End

### 💡 Idea

* Replace `val` elements with the last valid element.
* Reduces number of assignments in some cases (when many trailing `val`s).

### 👨‍💻 Java Code

```java
public class RemoveElementSwap {
    public int removeElement(int[] nums, int val) {
        int n = nums.length;
        int i = 0;
        while (i < n) {
            if (nums[i] == val) {
                nums[i] = nums[n - 1];
                n--;
            } else {
                i++;
            }
        }
        return n;
    }
}
```

### 📊 Complexity

* **Time:** O(n)
* **Space:** O(1)

> ⚠️ Order of elements can change, which is acceptable for this problem.

---

## 🐢 Brute Force Approach

### 💡 Idea

* Use a new array or list (violates **in-place** constraint).
* Copy all elements except `val` and then override the original array (not recommended for interviews).

```java
// Not in-place, added for conceptual clarity
```

---

## 📌 Comparison Table

| Approach              | Time Complexity | Space Complexity | Preserves Order | Notes                               |
| --------------------- | --------------- | ---------------- | --------------- | ----------------------------------- |
| Two-pointer overwrite | O(n)            | O(1)             | ✅ Yes           | Best general-purpose solution       |
| Swap with end         | O(n)            | O(1)             | ❌ No            | Efficient when order doesn't matter |
| Brute force (copy)    | O(n)            | O(n)             | ✅ Yes           | ❌ Not in-place, avoid in interviews |

---

## 🏁 Conclusion

* ✅ Use **two-pointer overwrite** for clean, in-place logic that preserves order.
* 🔁 Use **swap-from-end** if order doesn’t matter and `val` is frequent.
* 🐢 Brute force is good for beginners but not acceptable in coding interviews.

---

Let me know if you'd like the solution in another language (like Python, C++, JavaScript) or a downloadable `.md` or `.pdf` version.
