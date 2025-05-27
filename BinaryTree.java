import java.util.Scanner;

// Main class
public class BinaryTree {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        BinarySearchTree bst = new BinarySearchTree();

        System.out.println("Enter keys to insert into the BST (Enter 'END' to finish):");
        String line = sc.nextLine();

        // Accept inputs until the user enters END
        while (!line.equalsIgnoreCase("END")) {
            try {
                int value = Integer.parseInt(line);
                bst.insert(value);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
            System.out.println("Enter next key (Enter 'END' to finish):");
            line = sc.nextLine();
        }

        // Sample tree operations
        System.out.println("\n--- Tree Traversals ---");
        System.out.print("Inorder Traversal: ");
        bst.inorder();

        System.out.print("\nPreorder Traversal: ");
        bst.preorder();

        System.out.print("\nPostorder Traversal: ");
        bst.postorder();

        // Search demo
        System.out.print("\n\nEnter key to search: ");
        int keyToSearch = sc.nextInt();
        boolean found = bst.search(keyToSearch);
        System.out.println(found ? "Key found!" : "Key not found.");

        // Delete demo
        System.out.print("\nEnter key to delete: ");
        int keyToDelete = sc.nextInt();
        bst.delete(keyToDelete);

        System.out.println("Inorder traversal after deletion:");
        bst.inorder();
    }
}

// Node class (represents each node in the tree)
class TreeNode {
    int key;
    TreeNode left, right;

    public TreeNode(int key) {
        this.key = key;
        left = right = null;
    }
}

// Binary Search Tree class
class BinarySearchTree {
    TreeNode root;

    public BinarySearchTree() {
        root = null;
    }

    // Insert operation
    public void insert(int key) {
        root = insertRec(root, key);
    }

    private TreeNode insertRec(TreeNode root, int key) {
        if (root == null) {
            System.out.println("Inserting " + key);
            return new TreeNode(key);
        }

        if (key < root.key) {
            System.out.println("Moving left of " + root.key);
            root.left = insertRec(root.left, key);
        } else if (key > root.key) {
            System.out.println("Moving right of " + root.key);
            root.right = insertRec(root.right, key);
        } else {
            System.out.println("Key already exists.");
        }

        return root;
    }

    // Search operation
    public boolean search(int key) {
        return searchRec(root, key);
    }

    private boolean searchRec(TreeNode root, int key) {
        if (root == null) return false;

        if (key == root.key) return true;

        return key < root.key ? searchRec(root.left, key) : searchRec(root.right, key);
    }

    // Delete operation
    public void delete(int key) {
        root = deleteRec(root, key);
    }

    private TreeNode deleteRec(TreeNode root, int key) {
        if (root == null) return null;

        if (key < root.key) {
            root.left = deleteRec(root.left, key);
        } else if (key > root.key) {
            root.right = deleteRec(root.right, key);
        } else {
            // Case 1: No child
            if (root.left == null && root.right == null) {
                return null;
            }
            // Case 2: One child
            else if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            // Case 3: Two children
            else {
                TreeNode successor = findMin(root.right);
                root.key = successor.key;
                root.right = deleteRec(root.right, successor.key);
            }
        }
        return root;
    }

    // Helper to find minimum node (used in delete)
    private TreeNode findMin(TreeNode node) {
        while (node.left != null)
            node = node.left;
        return node;
    }

    // Inorder traversal (Left, Root, Right)
    public void inorder() {
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(TreeNode root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.key + " ");
            inorderRec(root.right);
        }
    }

    // Preorder traversal (Root, Left, Right)
    public void preorder() {
        preorderRec(root);
        System.out.println();
    }

    private void preorderRec(TreeNode root) {
        if (root != null) {
            System.out.print(root.key + " ");
            preorderRec(root.left);
            preorderRec(root.right);
        }
    }

    // Postorder traversal (Left, Right, Root)
    public void postorder() {
        postorderRec(root);
        System.out.println();
    }

    private void postorderRec(TreeNode root) {
        if (root != null) {
            postorderRec(root.left);
            postorderRec(root.right);
            System.out.print(root.key + " ");
        }
    }
}