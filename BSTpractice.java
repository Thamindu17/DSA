import java.util.LinkedList;
import java.util.Queue;

class TreeNode{
        int key;
        TreeNode left,right;
        
        public TreeNode(int item){
            key=item;
            left=right=null;
        }
    }

public class BSTpractice {
    TreeNode root;    

    public BSTpractice(){
        root=null;
    }


    //insert
    
    public void insert(int key){
        root=insertRec(root, key)
    }

    private TreeNode insertRec(TreeNode root,int key){
        if(root==null){
            root=new TreeNode(key);
            return root;
        }
        if(key<root.key){
            root.left=insertRec(root.left, key);
        }else if(key>root.key){
            root.right=insertRec(root.right, key);
        }
        return root;
    }


    //delete

    public void delete1(int key){
        root= deleteLeafNode(root,key);
    }
    private TreeNode deleteLeafNode(TreeNode root,int key){
        if(root==null){
            return null;
        }
        if(key<root.key){
            root.left=deleteLeafNode(root.left, key);
        }
        else if(key>root.key){
            root.right=deleteLeafNode(root.right, key);
        }
        else{
            root=null;
        }
        return root;
    }


    public void delete2(int key){
        root= deleteOneCnode(root,key);
    }
    private TreeNode deleteOneCnode(TreeNode root,int key){
        if(root==null){
            return null;
        }
        if(key<root.key){
            root.left=deleteOneCnode(root.left, key);
        }
        else if(key>root.key){
            root.right=deleteOneCnode(root.right, key);
        }
        else{
            if(root.left==null) return root.right;
            else if(root.right==null) return root.left;
        }
        return root;


    }









    public void delete3(int key){
        root= deleteTwoCNode(root,key);
    }
    private TreeNode deleteTwoCNode(TreeNode root,int key){
         if(root==null){
            return null;
        }
        if(key<root.key){
            root.left=deleteTwoCNode(root.left, key);
        }
        else if(key>root.key){
            root.right=deleteTwoCNode(root.right, key);
        }
        else{
           TreeNode successor= findSuccessor(root.right);
           root.key=successor.key;
           root.right=deleteTwoCNode(root.right, successor.key)
        }
        return root;

    }

    private TreeNode findSuccessor(TreeNode root){
        while(root.left != null){
            root=root.left;
        }
        return root;
    }
   



    //search
    public TreeNode search(int key){
        return searchRec(root,key);
    }

    private TreeNode searchRec(TreeNode root,int key){
        if(root == null || root.key == key){
            return root;
        }
        if(root.key<key){
            return searchRec(root.right,key);
        }
        return searchRec(root.left, key);
        
    }



    //traversal

    public void preorder(){
        preorderRec(root);
    }

    private void preorderRec(TreeNode root){
        if(root != null){
            System.out.print(root.key + " ");
            preorderRec(root.left);
            preorderRec(root.right);
        }
    }
    private void inorderRec(TreeNode root){
        if(root != null){
            
            preorderRec(root.left);
            System.out.print(root.key + " ");
            preorderRec(root.right);
        }
    }
    private void postorderRec(TreeNode root){
        if(root != null){
            
            preorderRec(root.left);
            preorderRec(root.right);
            System.out.print(root.key + " ");
        }
    }


    public static void bfs(TreeNode root){
        if(root==null)
        return;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node =queue.poll();
            System.out.print(node.key + " ");

            if(node.left != null){
                queue.add(node.left);
            }
            if(node.right != null){
                queue.add(node.right);
            }

        }
    }







    public static void main(String[] args) {
        
    }
    
}
