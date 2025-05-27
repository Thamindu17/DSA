
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

public class Tree1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        BinarySearchTree bst = new BinarySearchTree();

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

        sc.close();
    }
}

class TreeNode {
    int key;
    TreeNode left, right;

    public TreeNode(int key){
        this.key = key;
        left = right = null;
    }
}

class BinarySearchTree {
    TreeNode root;

    public BinarySearchTree(){
        root = null;
    }

    // Insertion
    public void insert(int key){
        root = insertRec(root, key);
    }

    private TreeNode insertRec(TreeNode root, int key){
        if(root == null){
            System.out.println("Inserting");
            root = new TreeNode(key);
            return root;
        }

        if(key < root.key){
            System.out.println("Moving left of " + root.key);
            root.left = insertRec(root.left, key);
        } else if(key > root.key){
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
        if(order == 1){
            System.out.println("Printing tree in preorder");
            traversePreOrderRec(root);
        } else if(order == 2){
            System.out.println("Printing tree in inorder");
            traverseInOrderRec(root);
        } else if(order == 3){
            System.out.println("Printing tree in postorder");
            traversePostOrderRec(root);
        } else if(order == 4){
            System.out.println("Printing tree in level order");
            traverseLevelOrder(root);
        }
        System.out.println();
    }

    private void traversePreOrderRec(TreeNode root){
        if(root != null){
            System.out.print(root.key + " ");
            traversePreOrderRec(root.left);
            traversePreOrderRec(root.right);
        }
    }

    private void traverseInOrderRec(TreeNode root){
        if(root != null){
            traverseInOrderRec(root.left);
            System.out.print(root.key + " ");
            traverseInOrderRec(root.right);
        }
    }

    private void traversePostOrderRec(TreeNode root){
        if(root != null){
            traversePostOrderRec(root.left);
            traversePostOrderRec(root.right);
            System.out.print(root.key + " ");
        }
    }

    private void traverseLevelOrder(TreeNode root){
        if(root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            System.out.print(node.key + " ");
            if(node.left != null) queue.add(node.left);
            if(node.right != null) queue.add(node.right);
        }
    }
}