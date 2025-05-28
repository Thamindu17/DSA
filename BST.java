import java.util.LinkedList;
import java.util.Queue;

public class BST {

    class TreeNode{
        int key;
        TreeNode left,right;

        public TreeNode(int item){
            key=item;
            left=right=null;
        }        
    }

    class BinarySearchTree{
        TreeNode root;
        public BinarySearchTree(){
            root=null;
        }

        public void insert(int key){
        root=insertRec(root,key);
        }

        private TreeNode insertRec(TreeNode root,int key){
            if(root==null){
                root= new TreeNode(key);
                return root;
                }
            
            if(key<root.key)
                root.left=insertRec(root.left, key);
            else if(key>root.key) 
                root.right =insertRec(root.right, key);


         return root;
         }


        public void delete1(int key){
            root=deleteLeafNode(root,key);
         }
         private TreeNode deleteLeafNode(TreeNode root,int key){
            if(root==null) return null;

            if(key<root.key)
                root.left=deleteLeafNode(root.left, key);
            else if(key>root.key)
                root.right=deleteLeafNode(root.right, key);
            else{
                root=null; //no children
            }
            return root;
         }



         
        public void delete2(int key){
            root=deleteOnechildNode(root,key);
         }
         private TreeNode deleteOnechildNode(TreeNode root,int key){
            if(root==null) return null;

            if(key<root.key)
                root.left=deleteOnechildNode(root.left, key);
            else if(key>root.key)
                root.right=deleteOnechildNode(root.right, key);
            else{
                if(root.left ==null) return root.right;
                else if(root.right == null) return root.left;

            }
            return root;
         }



         
        public void delete3(int key){
            root=deletetwochildNode(root,key);
         }
         private TreeNode deletetwochildNode(TreeNode root,int key){
            if(root==null) return null;

            if(key<root.key)
                root.left=deletetwochildNode(root.left, key);
            else if(key>root.key)
                root.right=deletetwochildNode(root.right, key);
            else{
                TreeNode successor = findSuccessor(root.right);
                root.key=successor.key;
                root.right =deletetwochildNode(root.right, successor.key);

            }

            
            
            return root;
         }


         private TreeNode findSuccessor(TreeNode root){
                while(root.left != null){
                    root=root.left;
                }
                return root;
            }


    public TreeNode search(int key){
        return searchRec(root,key);
    }
    private TreeNode searchRec(TreeNode root,int key){
        if(root == null || root.key == key)
            return root;
        
        if(root.key <key)  //key is greater
            return searchRec(root.right, key);
        //key is smaller

        return searchRec(root.left, key);
    }

    
    public void preorder(){
        traversePreOrderRec(root);
    }
    
    private void traversePreOrderRec(TreeNode root){
        if(root != null){
            System.out.print(root.key + " ");
            traversePreOrderRec(root.left);
            traversePreOrderRec(root.right);
        }
    }


    public void inorder(){
        traverseInOrderRec(root);
    }
    

    private void traverseInOrderRec(TreeNode root){
        if(root != null){
            traverseInOrderRec(root.left);
            System.out.print(root.key + " ");
            traverseInOrderRec(root.right);
        }
    }


    
    public void postorder(){
        traversePostOrderRec(root);
    }
    
    private void traversePostOrderRec(TreeNode root){
        if(root != null){
            traversePostOrderRec(root.left);
            traversePostOrderRec(root.right);
            System.out.print(root.key + " ");
        }
    }



    public static void traverseLevelOrderBFS(TreeNode root){
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
   
}
