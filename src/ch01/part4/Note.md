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

  we can use `set` to help us find common element

  time complexity: $O(n)$

  space complexity: $O(n)$

## 1.4.14

- func1

  use four loop to iterate all number

  time complexity: $O(n^4)$

  space complexity: O(1)

- func2

  we can sorted list in $O(nlogn)$, than we use three loop to get three number `a,b,c`. in this moment, we can use `binarySearch` to search 
  `t = -(a + b + c)` in $O(logn)$

  time complexity: $O(n^3logn)$

  space complexity: $O(1)$

  

## 1.4.15

it has two part:

- two sum
- three sum

a is sorted list

**two sum:**

in this part, we optimize time complexity from $O(n^2)$ to $O(n)$

- TwoSum_ini

  initial edition to solve two sum problem

  time complexity: $O(n^2)$

  space complexity: $O(1)$

- TwoSum_NlogN

  use `binarySearch` to help us find `a[j]` that make `a[i] + a[j] = 0` set up.

  time complexity: $O(nlogn)$

  space complexity: $O(1)$

- TwoSum_NlogN_p

  use `lower_bound` (detail at 1.4.10) to solve problem

  its idea almost equal to `TwoSum_NlogN`

  time complexity: $O(nlogn)$

  space complexity: $O(1)$

- TwoSumFaster

  use hash map.

  we loop two times.

  first loop we store number into hash map

  second loop we find number pair that plus equal zero

  notice we need process zero number in particularly

  time complexity: $O(n)$

  space complexity: $O(n)$

- TwoSumFaster_p

  use two point.

  notice a is sorted list that mean if `a[i] + a[j] = 0` the `i` and `j` must in two ends of list(relative).

  we can use two point `i=0` and `j = n-1` to iterate all number to find number pair that problem required.

  we still have to process zero number in particularly that make us code more simplify.

  so we focus on `a[i] != 0` and `a[j] != 0 ` to see how we iterate.

  1. `a[i] + a[j] > 0`

     this means `a[j]` is too large, so `j -= 1`

  2. a[i] + a[j] < 0 

     this means `a[i]` is too small, so `i += 1`

  3. a[i] + a[j] == 0

     Note that we have to deal with the case of duplicate numbers, so move `i` to right until `new a[i] != a[i]`, and move `j` to left in same rule.

     when move stop, calculate the pair use `cmn`

  finally, we counting zero numbers that calculate the pair

  maybe the coding and description are slightly complicated, but its idea is very simple.

  time complexity: $O(n)$

  space complexity: $O(1)$

**three sum:**

if we have get `a[i]` and `a[j]`, we can construct `t = a[i] + a[j]`, now we problem is change to find `-t` , note that three sum problem has transfer to  two sum problem.

so if you want to use two sum problem method to solve three sum problem, you can fixed `a[i]` and `a[j]`. get `a[i]` and `a[j]` is always $O(n^2)$ cost that means algorithm is $\Omega(n^2)$. in fact, this problem optimal time complexity is $O(n^2)$

- ThreeSumFaster

  use hash map. key is `a[i]` and value is a list which store `index` of `a[i]`, `a[i]` may have occur more than once.

  we loop list two times to get `t = a[i] + a[j]` then we search `-t` in hash map, we just store all `index` of value, so we can check `index>j` as `-t`.

  index must bigger than j, because we don't want duplicate counting pair, it is very trickiness to get right answer in duplicated situation.

  time complexity: O(n^2), if search in hash map cost is $O(1)$

  space complexity: O(n)

- ThreeSumFaster_2

  use two point

  we just see `TwoSumFaster_p` use two point to optimize space complexity from $O(n^2)$ to $O(n)$, if we want use this trick in three sum problem, the trick is **fixed a[i] then find a[j] + a[k] = -a[i]**

  the code is similarity to `TwoSumFaster_p`, check the two answers make sure you understand.

  time complexity: $O(n^2)$

  space complexity: $O(1)$

## 1.4.16

the time complexity which required is $O(nlogn)$

we can sorted list in $O(nlogn)$ and recorded the minimal distance between `a[i]` and `a[i+1]`, $ i \in {1,2, ... n -1}$

## 1.4.17

the time complexity which required is $O(n)$, that means we can't sorted list again

we can iterate list and record minimal and maximal number

## 1.4.18

find `a[i]` which `a[i-1]>a[i]<a[i+1]`, note that edge number is an answer also.

> eq: 
> `1 3 5`,  `1` is answer, because `1<3`
>
> `5 3 1` , `1` is answer, because `3>1`

the local minimal number may not be only.

- func1

  brute force method

  time complexity: $O(n)$

- func2

  only effective when list is **distinct**

  we use binary search method to find a local minimal number

  when we get `a[mid]`, check it meet require or not. if it meet, return `mid`; if not, we search in smaller number side

  > eq: [3,4,5,3,4]
  >
  > 5 not meet
  >
  > search in [3,4]

  repeat the above operation, we always can find a local minimal number

  time complexity: $O(logn)$

  

  more detail see at: ...

## 1.4.19

now we need find local minimal number in matrix.

- func1

  it is very easy to write code of brute force method

  time complexity: $O(n^2)$

- func2

  we can see a matrix to a list which space is `N*M`

  we use binary search in list, and check `a[mid]` meet require or not. if it meet, return `mid`; if not, we search in smaller number side.

  time complexity: $O(nlogn)$

- func3

  divide and conquer

  time complexity: $O(nlogn)$

## 1.4.20

it is a slightly complicate binary search problem, since list now contain **ascend** and **descend** part 

we still get `mid = (lo + hi)/2` and judge it. if it is the key , return `mid`

else:

we need judge **`a[mid]`, `key`** and **`a[mid-1]`, `a[mid]`,`a[mid+1]`** size relationship

we can judge `a[mid]` in what part.

if `a[mid] > a[mid+1]`, `a[mid]` in **descend** part

if `a[mid] < a[mid+1]`, `a[mid]` in **ascend** part



if `a[mid] > key`, means `a[mid]` is too large, we need search in **smaller** area

if `a[mid] < key`, means we need search `key` in a **bigger** area.

now problem is how to define **bigger** and **smaller** area

if `a[mid] > a[mid+1]`, not doubt that the number which in `[mid+1,lo]` is smaller `a[mid]`, **but** the number which in [lo,mid-1] maybe smaller than `a[mid]`, so there is two **smaller** area, but bigger 

if `a[mid] < a[mid+1]`, the **smaller** area is `[lo,mid-1]` and `[mid+1,hi]`, **bigger** area is `[mid+1,hi]`



time complexity: $O(logn)$

## 1.4.23

well, the solve of problem may seems counterintuitive

 let us reframe the problem.

we have lots of `eggs`,and a building which height is `N`. a `eggs` will break if we drop it from a high enough floor(`>=F`), the egg will break.
but we don't know `F` definite value, we want to get it in as few trials as possible.

Obviously, if we drop egg layer by layer, we can get it in `F` times trails. this algorithm is brute force.

but we want to get `F` in $o(n)$ times, dose this algorithm exist?

consider binary search, seems it can solve this problem in $O(logn)$ times, but actually, it may worst than brute force in some case.

> eq: F=49, height=100
>
> brute force will take 49 times trials
>
> but binary search will take 50 times

[鸡蛋掉落 - 鸡蛋掉落 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/super-egg-drop/solution/ji-dan-diao-luo-by-leetcode-solution-2/)



