package com.ygz.binarySearchTree;

public class BSTMain {

    public static void main(String[] args) {

        BST<Integer> bst = new BST<>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for(int num: nums)
            bst.add(num);

        /////////////////
        //      5      //
        //    /   \    //
        //   3    6    //
        //  / \    \   //
        // 2  4     8  //
        /////////////////
//        System.out.println(bst);

//        bst.frontTraverse();
//        System.out.println();
//
//        bst.backTraverse();
//        System.out.println();
//
//        bst.middleTraverse();
//        System.out.println();

//        bst.frontTraverseNoRec();
//        bst.hierarchyTraverse();
//        System.out.println(bst.findMin());
//        System.out.println(bst.findMax());
        bst.remove(3);
        bst.frontTraverse();
    }
}
