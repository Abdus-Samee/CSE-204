import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph {
    private List<ArrayList<Integer>> adjacencyList;
    int sum;
    private int[] visited;
    private int[] pieces;
    private int[] friends;
    private int completed = 0;

    public Graph(int size){
        this.adjacencyList = new ArrayList<>();
        this.visited = new int[size];

        for(int i = 0; i < size; i++) this.adjacencyList.add(new ArrayList<>());
    }

    public void addRoad(int a, int b){
        this.adjacencyList.get(a).add(b);
        this.adjacencyList.get(b).add(a);
    }

    public void bfsTraverse(){
        int[] collected = new int[friends.length];
        this.sum = 0;

        for(int i = 0; i < pieces.length; i++) sum += pieces[i];

        for(int i = 0; i < friends.length; i++){
            int ans = bfs(friends[i]);
            collected[i] = ans;
        }

        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("bfs.txt"));

            if(sum == this.completed) bw.write("Mission Accomplished\n");
            else bw.write("Mission Impossible\n");

            bw.write(this.completed + " out of " + sum + " pieces are collected\n");

            for(int i = 0; i < friends.length; i++) bw.write(i + " collected " + collected[i] + " pieces\n");

            bw.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public int bfs(int start){
        int collected = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        while(!queue.isEmpty()){
            int city = queue.remove();
            if(!isVisited(city)){
                visited[city] = 1;
                collected += this.pieces[city];

                List<Integer> adjacentCity = this.adjacencyList.get(city);
                for(int i = 0; i < adjacentCity.size(); i++){
                    if(!isVisited(adjacentCity.get(i))) queue.add(adjacentCity.get(i));
                }
            }
        }

        this.completed += collected;

        return collected;
    }

    public void dfsTraverse(){
        this.completed = 0;
        int[] collected = new int[this.friends.length];
        this.visited = new int[this.visited.length];

        for(int i = 0; i < friends.length; i++){
            int ans = dfs(friends[i]);
            this.completed += ans;
            collected[i] = ans;
        }

        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("dfs.txt"));

            if(sum == this.completed) bw.write("Mission Accomplished\n");
            else bw.write("Mission Impossible\n");

            bw.write(this.completed + " out of " + sum + " pieces are collected\n");

            for(int i = 0; i < friends.length; i++) bw.write(i + " collected " + collected[i] + " pieces\n");

            bw.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public int dfs(int start){
        if(visited[start] == 1) return 0;

        visited[start] = 1;
        int ans = 0;

        List<Integer> adjacentCity = this.adjacencyList.get(start);
        for(int i = 0; i < adjacentCity.size(); i++){
            if(!isVisited(adjacentCity.get(i))) ans += dfs(adjacentCity.get(i));
        }

        return ans + pieces[start];
    }

    public void setPieces(int[] pieces) { this.pieces = pieces; }

    public void setFriends(int[] friends) { this.friends = friends; }

    public boolean isVisited(int city) { return (this.visited[city] == 0) ? false : true; }
}
