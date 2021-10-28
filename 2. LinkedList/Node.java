class Node{
    int data;
    Node prev;
    Node next;

    public Node(int data){
        this.data = data;
        this.prev = null;
        this.next = null;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public void setNext(Node next){
        this.next = next;
    }
}