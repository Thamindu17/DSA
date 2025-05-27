class TreeNode{
    int key;
    TreeNode left, right;
    
    public TreeNode(int item){
        key = item;
        left = right = null;
    }
}

class BinarySearchTree{
    TreeNode root;
    
    public BinarySearchTree(){
        root = null;
    }
    
    public void insert(int key){
        root = insertRec(root, key);
    }
    
    private TreeNode insertRec(TreeNode tempnode, int value){
        
        if(tempnode == null){
            tempnode = new TreeNode(value);
            return tempnode;
        }
        
        if(value <= tempnode.key){
            tempnode.left = insertRec(tempnode.left, value);
        }else if(value > tempnode.key){
            tempnode.right = insertRec(tempnode.right, value);
        }

        return tempnode;
    }
    
    public boolean search(int key){
        if (searchRec(root, key) == null){
            return false;
        }else{
            return true;
        }
    }
    
    private TreeNode searchRec(TreeNode tempnode, int value){
        
        if(tempnode.key == value || tempnode == null){
            return tempnode;
        }
        
        if(value < tempnode.key){
            return searchRec(tempnode.left, value);
        }else if(value > tempnode.key){
            return searchRec(tempnode.right, value);
        }
    }
    
    
}