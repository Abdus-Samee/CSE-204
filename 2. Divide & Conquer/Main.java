import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) throws Exception{
        int idx = -1, n = 0;
        String line;
        Point[] arr = null;
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        while(true){
            line = br.readLine();
            if(line == null) break;

            if(idx == -1){
                n = Integer.parseInt(line.split(" ")[0]);
                arr = new Point[n];
                idx++;
            }else{
                String[] input = line.split(" ");
                arr[idx] = new Point(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
                idx++;
            }
        }

        Point[] xSort = new Point[n];
        Point[] ySort = new Point[n];
        for(int i = 0; i < n; i++){
            xSort[i] = arr[i];
            ySort[i] = arr[i];
        }

        SecondNearestPair sp = new SecondNearestPair();
        sp.setXSort(xSort);
        sp.setYSort(ySort);

        Triplet ans = sp.secondNearestPair();

        int from = -1, to = -1;
        for(int i = 0; i < arr.length; i++){
            if(ans.from.equals(arr[i])){
                from = i;
                break;
            }
        }
        for(int i = 0; i < arr.length; i++){
            if(ans.to.equals(arr[i])){
                to = i;
                break;
            }
        }

        System.out.println(Math.min(from, to) + " " + Math.max(from, to));
        System.out.println(String.format("%.4f", ans.d));
    }
}
