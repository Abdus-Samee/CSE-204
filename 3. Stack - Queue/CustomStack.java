public class CustomStack {
    private Node head;

    private class Node{
        String data;
        Node next;

        public Node(String data){
            this.data = data;
            this.next = null;
        }
    }

    public CustomStack(){
        this.head = null;
    }

    public void push(String data){
        Node temp = this.head;
        this.head = new Node(data);
        this.head.next = temp;
    }

    public String pop(){
        String ans = head.data;
        head = head.next;

        return ans;
    }
    
    public String peek() { return this.head.data; }

    public boolean isEmpty() { return this.head == null; }
}
