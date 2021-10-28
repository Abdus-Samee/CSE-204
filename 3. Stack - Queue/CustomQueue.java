public class CustomQueue {
    private Node head;
    private Node tail;

    private class Node{
        Character data;
        Node next;

        public Node(Character data){
            this.data = data;
            this.next = null;
        }
    }

    public CustomQueue(){
        this.head = null;
        this.tail = null;
    }

    public void enqueue(Character data){
        Node node = new Node(data);
        if(head == null){
            head = node;
            tail = node;
        }
        else{
            tail.next = node;
            tail = node;
        }
    }

    public Character dequeue(){
        Character data = head.data;
        head = head.next;

        return data;
    }

    public Character peek() { return head.data; }

    public boolean isEmpty() { return this.head == null; }
}
