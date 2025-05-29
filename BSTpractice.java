import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
    int key;
    TreeNode left, right;

    public TreeNode(int item) {
        key = item;
        left = right = null;
    }
}

public class BSTpractice {
    TreeNode root;

    public BSTpractice() {
        root = null;
    }

    // Insert
    public void insert(int key) {
        root = insertRec(root, key);
    }

    private TreeNode insertRec(TreeNode root, int key) {
        if (root == null) {
            root = new TreeNode(key);
            return root;
        }
        if (key < root.key) {
            root.left = insertRec(root.left, key);
        } else if (key > root.key) {
            root.right = insertRec(root.right, key);
        }
        return root;
    }

    // Delete Leaf Node
    public void delete1(int key) {
        root = deleteLeafNode(root, key);
    }

    private TreeNode deleteLeafNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (key < root.key) {
            root.left = deleteLeafNode(root.left, key);
        } else if (key > root.key) {
            root.right = deleteLeafNode(root.right, key);
        } else {
            // Node found, check if it's a leaf (no children)
            if (root.left == null && root.right == null) {
                return null;
            }
            // If not a leaf, don't delete
            System.out.println("Node " + key + " is not a leaf node, cannot delete with delete1");
        }
        return root;
    }

    // Delete Node with One Child
    public void delete2(int key) {
        root = deleteOneCnode(root, key);
    }

    private TreeNode deleteOneCnode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (key < root.key) {
            root.left = deleteOneCnode(root.left, key);
        } else if (key > root.key) {
            root.right = deleteOneCnode(root.right, key);
        } else {
            // Node found, check if it has exactly one child
            if (root.left == null && root.right != null) {
                return root.right;
            } else if (root.right == null && root.left != null) {
                return root.left;
            }
            // If not one child, don't delete
            System.out.println("Node " + key + " does not have exactly one child, cannot delete with delete2");
        }
        return root;
    }

    // Delete Node with Two Children
    public void delete3(int key) {
        root = deleteTwoCNode(root, key);
    }

    private TreeNode deleteTwoCNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (key < root.key) {
            root.left = deleteTwoCNode(root.left, key);
        } else if (key > root.key) {
            root.right = deleteTwoCNode(root.right, key);
        } else {
            // Node found, check if it has two children
            if (root.left != null && root.right != null) {
                TreeNode successor = findSuccessor(root.right);
                root.key = successor.key;
                root.right = deleteTwoCNode(root.right, successor.key);   //delete successor
            } else {
                // If not two children, don't delete
                System.out.println("Node " + key + " does not have two children, cannot delete with delete3");
            }
        }
        return root;
    }

    private TreeNode findSuccessor(TreeNode root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    // Search
    public boolean search(int key) {
        return searchRec(root, key) != null;
    }

    private TreeNode searchRec(TreeNode root, int key) {
        if (root == null || root.key == key) {
            return root;
        }
        if (key < root.key) {
            return searchRec(root.left, key);
        }
        return searchRec(root.right, key);
    }

    // Traversals
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

    public void bfs() {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.key + " ");
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        BSTpractice bst = new BSTpractice();
        bst.insert(5);
        bst.insert(10);
        bst.insert(2);
        bst.insert(15);
        bst.insert(3);
        bst.insert(9);
        bst.insert(1);

        System.out.println("Inorder traversal before deletion:");
        bst.inorder(); // Should print: 1 2 3 5 9 10 15

        bst.delete1(1); // Delete leaf node
        bst.delete2(2); // Delete node with one child (2 has left child 3)
        bst.delete3(10); // Delete node with two children

        System.out.println("Inorder traversal after deletion:");
        bst.inorder(); // Should print: 3 5 9 15

        System.out.println("Search 5: " + bst.search(5)); // true
        System.out.println("Search 10: " + bst.search(10)); // false

        System.out.println("Preorder traversal:");
        bst.preorder();
        System.out.println("Postorder traversal:");
        bst.postorder();
        System.out.println("BFS traversal:");
        bst.bfs();
    }
}