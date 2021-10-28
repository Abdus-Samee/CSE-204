public class BST {
    Node root;

    private class Node{
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public BST(){
        this.root = null;
    }

    public void insertItem(int i){
        Node newNode = new Node(i);

        if(root == null) this.root = newNode;
        else{
            Node temp = this.root;
            while(true){
                if(i < temp.data){
                    if(temp.left != null) temp = temp.left;
                    else{
                        temp.left = newNode;
                        return;
                    }
                }
                else{
                    if(temp.right != null) temp  =temp.right;
                    else{
                        temp.right = newNode;
                        return;
                    }
                }
            }
        }
    }

    public void searchItem(int i){
        Node temp = this.root;

        while(true){
            if(temp == null) break;
            else if(i < temp.data) temp = temp.left;
            else if(i > temp.data) temp = temp.right;
            else{
                System.out.println(i + " has been found.");
                return;
            }
        }

        System.out.println(i + " has not been found.");
    }

    public int getInOrderSuccessor(int item){
        int ans = Integer.MIN_VALUE;
        Node temp = this.root;

        while(true){
            if(temp == null) break;
            else if(item < temp.data) temp = temp.left;
            else if(item > temp.data) temp = temp.right;
            else break;
        }

        if(temp == null) return ans;

        if(temp.right != null){
            temp = temp.right;
            while(temp.left != null) temp = temp.left;
            ans = temp.data;
        }else{
            temp = this.root;
            while(temp.data != item){
                if(temp.data > item){
                    ans = temp.data;
                    temp = temp.left;
                }else temp = temp.right;
            }
        }

        return ans;
    }

    public int getInOrderPredecessor(int item){
        Node temp = this.root;
        Node ans = null;

        while(true){
            if(temp == null) break;
            else if(item < temp.data) temp = temp.left;
            else if(item > temp.data){
                ans = temp;
                temp = temp.right;
            }
            else{
                break;
            }
        }

        if(temp == null) return Integer.MIN_VALUE;
        else if(temp.left == null && ans == null) return Integer.MIN_VALUE;
        else if(temp.left == null) return ans.data;

        temp = temp.left;
        while(temp.right != null) temp = temp.right;

        return temp.data;
    }

    public Node deleteItem(Node parent, int item){
        if(parent == null) return null;

        if(item < parent.data) parent.left = deleteItem(parent.left, item);
        else if(item > parent.data) parent.right = deleteItem(parent.right, item);
        else{
            if(parent.left == null && parent.right == null){
                return null;
            }else if(parent.left == null){
                parent = parent.right;
                return parent;
            }else if(parent.right == null){
                parent = parent.left;
                return parent;
            }else{
                Node temp = parent.right;

                while(temp.left != null) temp = temp.left;

                parent.data = temp.data;
                parent.right = deleteItem(parent.right, temp.data);
            }
        }

        return parent;
    }

    public int getItemDepth(Node parent, int item, int depth){
        if(parent == null) return Integer.MIN_VALUE;

        if(item < parent.data) return getItemDepth(parent.left, item, depth+1);
        else if(item > parent.data) return getItemDepth(parent.right, item, depth+1);

        return depth;
    }

    public int getHeight(Node parent){
        if(parent == null) return -1;

        return 1 + Math.max(getHeight(parent.left), getHeight(parent.right));
    }

    public int getMaxItem(){
        Node temp = this.root;

        if(temp == null) return Integer.MAX_VALUE;

        while(temp.right != null) temp = temp.right;

        return temp.data;
    }

    public int getMinItem(){
        Node temp = this.root;

        if(temp == null) return Integer.MIN_VALUE;

        while(temp.left != null) temp = temp.left;

        return temp.data;
    }

    public void printPreOrder(Node parent){
        if(parent == null) return;

        System.out.println("Value of node: " + parent.data);
        printPreOrder(parent.left);
        printPreOrder(parent.right);
    }

    public void printInOrder(Node parent){
        if(parent == null) return;

        printInOrder(parent.left);
        System.out.println("Value of node: " + parent.data);
        printInOrder(parent.right);
    }

    public void printPostOrder(Node parent){
        if(parent == null) return;

        printPostOrder(parent.left);
        printPostOrder(parent.right);
        System.out.println("Value of node: " + parent.data);
    }

    public int getSize(Node parent){
        if(parent == null) return 0;

        return 1 + getSize(parent.left) + getSize(parent.right);
    }
}
