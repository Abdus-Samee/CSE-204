import java.util.Scanner;

public class PassingList {
    int size;
    int timer;
    boolean over = false;
    static int[] reflex;
    String dir;
    Node head;
    Node tail;
    Node curr;

    public PassingList(){
        this.size = 0;
        this.timer = 0;
        this.head = null;
        this.tail = null;
        this.curr = null;
        this.dir = "right";
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() { return this.size; }

    public int getCurrData() { return this.curr.data; }

    public void printList(){
        Node temp = this.curr;
        System.out.println("--------");
        while (temp != this.tail){
            System.out.println(temp.data);
            temp = temp.next;
        }
        System.out.println(curr.data);
        System.out.println("--------");
    }

    public void add(int i){
        Node newNode = new Node(i);

        if(this.head == null){
            this.head = newNode;
            this.curr = newNode;
            this.tail = newNode;
        }else{
            this.tail.next = newNode;
            newNode.prev = this.tail;
            newNode.next = this.head;
            this.head.prev = newNode;
            this.tail = newNode;
        }

        this.size++;
    }

    public void currentPlayer(int time){
        if(this.timer == 0) this.timer = reflex[curr.data-1];
        while(true){
            if(time > this.timer) traverse();
            else break;
        }

        System.out.println("Player " + curr.data + " is holding the pillow at t=" + time);
    }

    public void eliminatePlayer(int time){
        if(this.size > 1){
            System.out.println("Player " + curr.data + " has been eliminated at t=" + time);

            curr.prev.next = curr.next;
            curr.next.prev = curr.prev;

            if(dir.equals("right")) curr = curr.next;
            else curr = curr.prev;

            this.timer = reflex[curr.data-1] + time;
            this.size--;
        }
    }

    public void reverseDirection(int time){
        if(this.size > 1){
            if(this.timer == 0) this.timer = reflex[curr.data-1];
            while(true){
                if(time > this.timer) traverse();
                else break;
            }

            if(this.dir.equals("right")) this.dir = "left";
            else this.dir = "right";
        }
    }

    public void insertPlayer(int data){
        if(this.size > 1){
            Node prevNode;

            Node newNode = new Node(data);

            if(dir.equals("right")){
                newNode.prev = curr.prev;
                curr.prev.next = newNode;
                newNode.next = curr;
                curr.prev = newNode;
            }else{
                newNode.prev = curr;
                newNode.next = curr.next;
                curr.next.prev = newNode;
                curr.next = newNode;
            }

            this.size++;
        }
    }

    public void endGame(int time){
        if(this.size > 1){
            if(this.timer == 0) this.timer = reflex[curr.data-1];
            while(true){
                if(time > this.timer) traverse();
                else break;
            }

            System.out.println("Game over : Player " + curr.data + " is holding the pillow at time t=" + time);
            System.out.printf("Pillow passing sequence = Player ");
            while(this.size > 0){
                System.out.printf(curr.data + ", ");
                traverse();
                this.size--;
            }
        }
    }

    public void traverse(){
        if(dir.equals("right")){
            if(curr.next != null) curr = curr.next;
        }else{
            if(curr.prev != null) curr = curr.prev;
        }

        this.timer += reflex[curr.data-1];
    }

    public static void main(String[] args) {
        int n, time;
        String command;
        PassingList pl = new PassingList();

        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        reflex = new int[n];

        for(int i = 1; i <= n; i++) pl.add(i);

        for(int i = 0; i < n; i++){
            int x = scanner.nextInt();
            reflex[i] = x;
        }

        scanner.nextLine();

        while(true){
            if((pl.getSize() == 1) && !pl.over){
                System.out.println("Game Over : Player " + pl.getCurrData() + " wins!!");
                pl.over = !pl.over;
            }

            String[] input = scanner.nextLine().split(" ");
            time = Integer.parseInt(input[0]);
            command = input[1];

            if(command.equals("F")){
                pl.endGame(time);
                break;
            }
            else if(command.equals("P")) pl.currentPlayer(time);
            else if(command.equals("R")) pl.reverseDirection(time);
            else if(command.equals("M")) pl.eliminatePlayer(time);
            else if(command.equals("I")){
                if(pl.getSize() > 1){
                    int newReflex = Integer.parseInt(input[2]);
                    int[] temp = reflex;
                    reflex = new int[temp.length + 1];
                    for(int i = 0; i < temp.length; i++) reflex[i] = temp[i];
                    reflex[temp.length] = newReflex;

                    pl.insertPlayer(++n);

                }
            }
            else System.out.println("Invalid command");
        }
    }
}
