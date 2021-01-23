[toc]

# Note

## 1.4.8

- $O(n^2)$

  just two loop iterate all number, when `list[i] == list[j]`, `ans += 1`

  time complexity: $O(nlogn)$

  space complexity: $O(n)$

- $O(nlogn)$

  we sorted all number, thus equal number will adjoin each other. use this quality, we can loop all number and check adjacent numbers to get answer.

  notice: the answer of `1 2 2 2 3` is `3`, because three equal number will produce three equal number pair.

  generally, n number will produce
  $$
  C^2_n
  $$
  equal number pair.

  time complexity: $O(nlogn)$

  space complexity: $O(n)$

- O(n)

  if we can use hash map, he time complexity will optimize to O(n)

## 1.4.10

it call `lower_bound` in cpp

standard binary search look like this:

```python
def binarySearch(list,k):
	L,R = 0,len(list)-1
	while(L<=R):
        M = (L+R)/2
        if list[M] == k:
        	return M
       	elif list[M] > k:
            R = M-1
        else:
            L = M+1
    return -1
```

notice we need find smaller index which `list[index] == k` (if k in list), so when we find a number equal k, **fixed** it and try to find smaller index.

end loop when we have only one number, because we dont need to try anymore.

time complexity: $O(logn)$

space complexity: $O(1)$

## 1.4.12

two list has sorted.

- print

  use two point to iterate number.

  time complexity: $O(n)$

  space complexity: $O(1)$

- print2

  we can use `set` to help us find commen element

  time complexity: $O(n)$

  space complexity: $O(n)$

  ## 1.4.14

  