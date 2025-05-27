public package com.tck;

import java.util.Scanner;

public class Tree1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        com.tck.BinarySearchTree bst = new com.tck.BinarySearchTree();

        System.out.println("Enter new key (Enter 'END' to finish)");
        String line = sc.nextLine();
        while (!line.equals("END")){
            bst.insert(Integer.parseInt(line));

            System.out.println("Enter next key (Enter 'END' to finish)");
            line = sc.nextLine();
        }

        System.out.println("\n-------------------------\n");
        System.out.println("Traversing BST");
        System.out.println("Enter the order (1-Preorder, 2-Inorder, 3-Postorder, 4-Levelorder)");
        int order = Integer.parseInt(sc.nextLine());
        bst.traverse(order);

    }
}

class TreeNode{
    int key;
    com.tck.TreeNode left,right;

    public TreeNode(int key){
        this.key = key;
        left = right = null;
    }
}

class BinarySearchTree{
    com.tck.TreeNode root;

    public  BinarySearchTree(){
        root = null;
    }

    // Insertion
    public void insert(int key){
        root = insertRec(root,key);
    }

    private com.tck.TreeNode insertRec(com.tck.TreeNode root, int key){
        if(root==null){
            System.out.println("Inserting");
            root = new com.tck.TreeNode(key);
            return root;
        }

        if(key<root.key){
            System.out.println("Moving left of " + root.key);
            root.left = insertRec(root.left, key);
        } else if(key>root.key){
            System.out.println("Moving right of " + root.key);
            root.right = insertRec(root.right, key);
        } else{
            System.out.println("This is already added");
        }

        return root;
    }

    // Traversal  (DFS - Preorder, Inorder, Postorder), (BFS - Levelorder)
    public void traverse(int order){
        System.out.println("");
        if(order==1){
            System.out.println("Printing tree in preorder");
            traversePreOrderRec(root);
        } else if(order==2){
            System.out.println("Printing tree in inorder");
            traverseInOrderRec(root);
        } else if(order==3){
            System.out.println("Printing tree in postorder");
            traversePostOrderRec(root);
        }
    }

    private void traversePreOrderRec(com.tck.TreeNode root){
        if(root!=null){
            System.out.print(root.key + " ");
            traversePreOrderRec(root.left);
            traversePreOrderRec(root.right);
        }else {
            System.out.print("EOP "); // EOP - End of a path
        }
    }

    private void traverseInOrderRec(com.tck.TreeNode root){
        if(root!=null){
            traverseInOrderRec(root.left);
            System.out.print(root.key + " ");
            traverseInOrderRec(root.right);
        } else {
            System.out.print("EOP "); // EOP - End of a path
        }
    }

    private void traversePostOrderRec(com.tck.TreeNode root){
        if(root!=null){
            traverseInOrderRec(root.left);
            traverseInOrderRec(root.right);
            System.out.print(root.key + " ");
        } else {
            System.out.print("EOP "); // EOP - End of a path
        }
    }



} 