class Node{
    int data;
    Node leftNode;
    Node rightNode;

    public Node(int data) {
        this.data = data;
       
    }

}



public class BinaryTree {
    
    Node rootNode;

    public void insert(int value) {
       rootNode=insertRec(rootNode, value);
    }


    public Node insertRec(Node rootNode , int value){
        if(rootNode == null){
            rootNode = new Node(value);
            
        }
        else if(rootNode.data < value){
            rootNode.rightNode = insertRec(rootNode.rightNode, value);
        }
        else if(rootNode.data > value){
            rootNode.leftNode = insertRec(rootNode.leftNode, value);
        }

        return rootNode;
    }




    public void preorder() {
        System.out.println("/n");
        preorderRec(rootNode);
    }


    

    public void preorderRec(Node rootNode){
        if(rootNode != null){

          System.out.print(rootNode.data + " ");
          preorderRec(rootNode.leftNode);  
          
          preorderRec(rootNode.rightNode);
          
        }
    }


    public void inorder() {
        inorderRec(rootNode);
    }

    public void inorderRec(Node rootNode){
        if(rootNode != null){
          inorderRec(rootNode.leftNode);  
          System.out.print(rootNode.data + " ");
          inorderRec(rootNode.rightNode);
          
        }
    }


}


