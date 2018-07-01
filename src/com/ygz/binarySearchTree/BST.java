package com.ygz.binarySearchTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二分搜索树
 * 二分搜索树的范型内容必须是实现Comparable的  这样才可以对树内容比较 这个二分搜索树不允许重复内容
 * ------>这个类中方法名已Rec结尾的都表示使用递归的形式处理逻辑
 */
public class BST<E extends Comparable<E>> {

    /**
     * 树的节点对象Node
     */
    private class Node{
        //树节点内容
        public E e;

        //树左右下级节点
        public Node left,right;

        //节点无参构造
        public Node(E e){
            this.e = e;
            left = null;
            right = null;
        }
    }

    // 根节点
    private Node root;

    //树内容的数量
    private int size;

    //无参构造
    public BST(){
        root = null;
        size = 0;
    }

    //获取树内容的数量
    public int size(){
        return size;
    }

    //查看树是不是空的
    public boolean isEmpty(){
        return size == 0;
    }

    //给树中添加一个元素E
    public void add(E e){
        root = this.addRec(e,root);
    }

    //递归的方式给二叉树新增一个元素
    private Node addRec(E e,Node node){
        if(node == null){           //先看递归中最小的一种情况  也就是node为空的时候 如果node为空有两种情况
            size++;                 //第一是树本来就是空的  那么直接新建一个根节点返回给root
            return new Node(e);     //第二是到了某个分支的最底层了  那么直接新建一个node作为该分支新的叶子节点
        }

        int res = e.compareTo(node.e);//新增元素和节点元素进行比较
        if(res < 0)
            //如果新增元素小于当前节点元素   那么把当前节点的左下级节点做参数递归
            // 在最小情况下 会新建node并返回给节点的左子树
            node.left = this.addRec(e,node.left);
        else if(res > 0)                //注意这是必须是else if  因为在添加的元素和某个节点如果相等  不做处理
            //如果新增元素大于当前节点元素   那么把当前节点的右下级节点做参数递归
            // 在最小情况下 会新建node并返回给节点的右子树
            node.right = this.addRec(e,node.right);
        return node;
    }

    //查看传入参数e是否在书中存在
    public boolean contains(E e){
        return this.containsRec(e, root);
    }

    //递归的方式查看传入参数e是否在书中存在
    private boolean containsRec(E e, Node node){
        if(node == null)    //如果node为空 不管是树是不是空的  只要他递归到了node==null的 就说明这个e不在树中
            return false;

        int res = e.compareTo(node.e);
        if(res == 0)                    //如果相等  那么说明存在  return true
            return true;
        else if(res < 0)                //如果小于  那么继续把当前节点的左下级做参数递归
            return containsRec(e, node.left);
        else                            //如果大于  那么继续把当前节点的右下级做参数递归
            return containsRec(e, node.right);
    }

    //前序遍历
    public void frontTraverse(){
        frontTraverseRec(root);
    }

    //递归的方式前序遍历树
    private void frontTraverseRec(Node node){
        if(node == null)    //最小的情况下就是到树某个分支的的最底层
            return;

        System.out.println(node.e);//先遍历根节点
        frontTraverseRec(node.left);//再遍历左子树
        frontTraverseRec(node.right);//再遍历右子树
    }

    //非递归的形式遍历树
    public void frontTraverseNoRec(){
        if(root == null)    //如果根节点为空  那么直接return
            return;

        Stack<Node> stack = new Stack<>();      //这里借用栈来实现树的遍历
        stack.push(root);                       //先把根节点压入栈底
        while(!stack.isEmpty()){                //开始循环 条件为只要栈不为空
            Node cur = stack.pop();     //取出栈顶元素
            System.out.println(cur.e);  //输出

            if(cur.right != null)       //当前节点的右子节点不为空
                stack.push(cur.right);  // 那么先把右节点压入栈
            if(cur.left != null)        //当前节点的左子节点不为空
                stack.push(cur.left);   // 那么再把左节点压入栈
            //可以想象有一个树  先把根节点入栈  然后再把右节点入栈  接着把左节点入栈  一次循环完成  下次循环就会
            // 取出后入栈的左节点元素  然后接着把下级的循环这个操作
        }
    }

    //后序遍历
    public void backTraverse(){
        backTraverseRec(root);
    }

    //递归的方式后序遍历树
    private void backTraverseRec(Node node){
        if(node == null)    //最小的情况下就是到树某个分支的的最底层
            return;

        backTraverseRec(node.left);//先遍历左子树
        backTraverseRec(node.right);//再遍历右子树
        System.out.println(node.e);//再遍历根节点
    }

    //中序遍历  中序遍历的结果恰好就是把二分搜索树的内容从下到大排序的结果
    public void middleTraverse(){
        middleTraverseRec(root);
    }

    //递归的方式中序遍历树
    private void middleTraverseRec(Node node){
        if(node == null)    //最小的情况下就是到树某个分支的的最底层
            return;

        middleTraverseRec(node.left);//先遍历左子树
        System.out.println(node.e);//再遍历根节点
        middleTraverseRec(node.right);//再遍历右子树
    }

    //树的层序遍历/广度遍历
    public void hierarchyTraverse(){
        Queue<Node> queue = new LinkedList<>();    //使用java的队列接口Queue实现LinkedList
        queue.add(root);//先把树的根节点放进去
        while (!queue.isEmpty()){   //如果队列不为空  那么就一直循环
            //第一次循环先把跟节点拿出来  以后每次放入的是本次取出的节点的下级左右节点
            //先放左节点再放右节点   循环就是先拿出队列的一个  再放入下级的两个
            // 如果下级为空就不做操作 可以画图了解这个操作
            Node cur = queue.remove();
            System.out.println(cur.e);
            if(null != cur.left)
                queue.add(cur.left);
            if(null != cur.right)
                queue.add(cur.right);
        }
    }

    //查找树的最小值
    public E findMin(){
        if(this.isEmpty())
            throw new IllegalArgumentException("BST is empty.");

        if(null == root.left)       //如果根节点的左子节点为空  那说明根节点就是最小值
            return root.e;

        Node cur = root.left;       //否则的话直接拿到跟节点的左子节点并且循环判断这个节点的左子节点是否为空
        while (null != cur){
            if(null != cur.left)
                cur = cur.left;
            else                    // 直到为空  那么这个节点就是最小值
                break;
        }
        return cur.e;
    }

    // 寻找二分搜索树的最小元素递归写法
    public E findMinRec(){
        if(size == 0)
            throw new IllegalArgumentException("BST is empty!");

        return findMinRec(root).e;
    }

    // 返回以node为根的二分搜索树的最小值所在的节点
    private Node findMinRec(Node node){
        if(node.left == null)
            return node;
        return findMinRec(node.left);
    }

    //查找树的最大值
    public E findMax(){
        if(this.isEmpty())
            throw new IllegalArgumentException("BST is empty.");

        if(null == root.right)       //如果根节点的右子节点为空  那说明根节点就是最大值
            return root.e;

        Node cur = root.right;       //否则的话直接拿到跟节点的右子节点并且循环判断这个节点的右子节点是否为空
        while (null != cur){
            if(null != cur.right)
                cur = cur.right;
            else                    // 直到为空  那么这个节点就是最大值
                break;
        }
        return cur.e;
    }

    // 寻找二分搜索树的最大元素的递归写法
    public E findMaxRec(){
        if(size == 0)
            throw new IllegalArgumentException("BST is empty");

        return findMaxRec(root).e;
    }

    // 返回以node为根的二分搜索树的最大值所在的节点
    private Node findMaxRec(Node node){
        if(node.right == null)
            return node;

        return findMaxRec(node.right);
    }

    //删除树的最小值  递归写法
    public E removeMinRec(){
        E res = this.findMin();
        root = removeMinRec(root);
        return res;
    }

    private Node removeMinRec(Node node){
        if(null == node.left){              //如果一个节点的左子节点为空   因为从一开始就是往左节点开始找的
            Node rightNode = node.right;    // 所以说明这个节点的就是最小值  先把右节点记录下来
            node.right = null;              //然后把这个节点的右节点变成null
            size--;                         //数量减一
            return rightNode;               //返回这个右子节点 其实这个右子节点有可能有值也可能是null
                                            // 如果有值  那么说明这个最小值不是叶子节点  如果没值说明是叶子节点
                                            // 但是不管有没有值  都把这个返回并给他的上级左节点
        }
        node.left = removeMaxRec(node.left);  //递归调用 把每次的返回值给左子节点
        return node;
    }

    //删除树的最大值 递归写法
    public E removeMaxRec(){
        E res = this.findMax();
        root = removeMaxRec(root);
        return res;
    }


    private Node removeMaxRec(Node node){
        if(null == node.right){           //如果一个节点的右子节点为空  因为从一开始就吃往右开始找的
            Node leftNode = node.left;    //所以说明这个节点的就是最大值   先把左节点记录下来
            node.left = null;             //然后把这个节点的左节点变成null
            size--;                       //数量减一
            return leftNode;              //返回这个左子节点 其实这个左子节点有可能有值也可能是null
                                          // 如果有值  那么说明这个最大值不是叶子节点  如果没值说明是叶子节点
                                          //但是不管有没有值  都把这个返回并给他的上级右节点
        }
        node.right = removeMaxRec(node.right);  //递归调用 把每次的返回值给右子节点
        return node;
    }

    // 从二分搜索树中删除元素为e的节点
    public void remove(E e){
        root = remove(root, e);
    }

    // 删除掉以node为根的二分搜索树中值为e的节点, 递归算法
    // 返回删除节点后新的二分搜索树的根
    private Node remove(Node node, E e){
        if( node == null )
            return null;
        if( e.compareTo(node.e) < 0 ){          //如果要删的元素比当前元素小  那么继续去当前元素的左子树找
            node.left = remove(node.left , e);
            return node;
        }
        else if(e.compareTo(node.e) > 0 ){      //如果要删的元素比当前元素大  那么继续去当前元素的右子树找
            node.right = remove(node.right, e);
            return node;
        }
        else{   // e.compareTo(node.e) == 0     //如果相等那就准备删除
            if(node.left == null){              // 待删除节点左子树为空的情况
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;               //把他的右子树返回给上级的找到他的节点
            }
            if(node.right == null){             // 待删除节点右子树为空的情况
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;                //把他的左子树返回给上级的找到他的节点
            }
            // 待删除节点左右子树均不为空的情况
            // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
            // 也可以找比待删除节点的小的最大节点值，既待删除节点左子树的最大节点 这里使用前一种
            // 用这个节点顶替待删除节点的位置
            Node successor = findMinRec(node.right);
            successor.right = removeMinRec(node.right);
            successor.left = node.left;
            node.left = node.right = null;          //这里不用size--  因为在removeMinRec中已经减了
            return successor;
        }
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        generateString(root, 0, res);
        return res.toString();
    }

    // 生成以node为根节点，深度为depth的描述二叉树的字符串
    private void generateString(Node node, int depth, StringBuilder res){

        if(node == null){
            res.append(generateDepthString(depth) + "null\n");
            return;
        }

        res.append(generateDepthString(depth) + node.e + "\n");
        generateString(node.left, depth + 1, res);
        generateString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth){
        StringBuilder res = new StringBuilder();
        for(int i = 0 ; i < depth ; i ++)
            res.append("-");
        return res.toString();
    }
}
