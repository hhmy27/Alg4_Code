[toc]

## 3.2.5

[[CLRS\][CH 15.5]最优二叉查找树 - Rancher - 博客园 (cnblogs.com)](https://www.cnblogs.com/rancher/p/4143536.html)

[What is Knuth's optimization in dynamic programming? - Quora](https://www.quora.com/What-is-Knuths-optimization-in-dynamic-programming)

题目的要求是构造一棵最优二叉搜索树

## 3.2.11

Therefore, there are $2^{N - 1}$ binary tree shapes of N nodes with height N.
And there are $2^{N - 1}$different ways to insert N distinct keys into an initially empty BST that result in a tree of height N.

[algorithms-sedgewick-wayne/Exercise11.txt at master · reneargento/algorithms-sedgewick-wayne (github.com)](https://github.com/reneargento/algorithms-sedgewick-wayne/blob/master/src/chapter3/section2/Exercise11.txt)

## 3.2.32

[98. 验证二叉搜索树 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/validate-binary-search-tree/)

```
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    bool isValidBST(TreeNode* root) {
        return func(root,LONG_MIN,LONG_MAX);
    }
    //给定一个值
    bool func(TreeNode* root,long low,long high){
        if(!root)return true;
        if(root->val<=low||root->val>=high)return false;
        else
            return func(root->left,low,root->val)&&func(root->right,root->val,high);
    }
};
```

