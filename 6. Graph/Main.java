import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split(" ");
        int C = Integer.parseInt(input[0]);
        int R = Integer.parseInt(input[1]);
        int L = Integer.parseInt(input[2]);
        int F = Integer.parseInt(input[3]);

        Graph graph = new Graph(C);
        int[] pieces = new int[C];
        int[] friends = new int[F];

        for(int i = 0; i < R; i++){
            input = scanner.nextLine().split(" ");
            int C1 = Integer.parseInt(input[0]);
            int C2 = Integer.parseInt(input[1]);

            graph.addRoad(C1, C2);
        }

        for(int i = 0; i < L; i++){
            input = scanner.nextLine().split(" ");
            int c = Integer.parseInt(input[0]);
            int p = Integer.parseInt(input[1]);

            pieces[c] = p;
        }
        graph.setPieces(pieces);

        for(int i = 0; i < F; i++){
            input = scanner.nextLine().split(" ");
            int c = Integer.parseInt(input[0]);
            int f = Integer.parseInt(input[1]);

            friends[f] = c;
        }

        graph.setFriends(friends);

        graph.bfsTraverse();

        graph.dfsTraverse();
    }
}
