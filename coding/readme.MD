## Tricks

- For k window problems, start count = 0 if you want to do at start of window, count = 1 for end for window.

Important problems

## Arrays

1. House robber - I - (Max(arr[i-1], arr[i-2]+curr))
   II - Houses in circle - Max(rob(arr,0, arr.length-2) + rob(arr,1, arr.length-1))
2. Meeting rooms - I, II, III - Use min heaps for interval[i][1]. Add into heap if needs a merge.
3. Evaluate expression like 2[a]3[b] => "aabbb"
   Use 2 stacks one for the count & another for the string. start when we see '[' & then when we see ']' pop from stack & decode the string.
4. Largest histogram - previous & next smallest, then area at i is height[i] \* (nextSmall[i] - prevSmall[i] - 1)
5. Rain water - array with leftMax & rightMax, at i if leftMax[i] & rightMax[i] > arr[i] then (Min(leftMax[i], rightMax[i]) - arr[i] )

## Grid

1. Matrix diagonal - create a map with key as i+j & value a list. Then for even index of list reverse list.
2. Number of islands - Do BFS for all the adj cell [i,j] if its 1, then count the number of BFS calls.
3. Rotten oranges - Do BFS, first count fresh oranges('1'), add all rotten('2') to the queue. while q not empty, poll from queue, a for loop to iterate over q size, go all directions & mark them rotten & add to queue & deduct fresh orange count.
4. Sudoku - maintain a map for each box.
5. BFS in 2D matrix - First search column, then based on index returned do a row search.
6. Word search - Given a grid with chars & a word to search. Iterate through grid & if first char of word is matched call a func which pass i,j,x(word idx). Then for each direction check char at x matches, if matches call recursively. Any match make grid[i][j]='.' & then back track.

## Tree

1. LCA in BST - p & q < root then go left, p & q > root then go right, else return node.
2. Count the number of nodes in a almost complete binary tree - if no of nodes is left & right are equal then nodes = 2^height-1. if not go to the node where number of nodes are more. if not -> 1 + count(root.left) + count(root.right)
3. Merge - if root1=null -> return root2, vice-versa, root1.val+= root2.val, root1.left=merge(root1.left, root2.left) & root1.right=merge(root1.right, root2.right)
4. Path sum - use pre-order traversal, maintain sum & a list.
