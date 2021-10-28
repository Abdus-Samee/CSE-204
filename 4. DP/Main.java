import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) throws Exception {
        int mod = 1000000007, idx = -1, n = 0, s = 0;
        int[] arr = null;
        String line;
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        while(true){
            line = br.readLine();
            if(line == null) break;

            if(idx == -1){
                n = Integer.parseInt(line.split(" ")[0]);
                s = Integer.parseInt(line.split(" ")[1]);
                arr = new int[n];
                idx++;
            }else{
                String[] input = line.split(" ");
                for(int i = 0; i < n; i++) arr[i] = Integer.parseInt(input[i]);
            }
        }

        int[][] dp = new int[n][s+1];
        for(int i = 1; (i <= arr[0]) && (i <= s); i++) dp[0][i] = 1;

        for(int i = 1; i < n; i++){
            for(int j = 1; j <= s; j++){
                dp[i][j] = ((dp[i-1][j-1])%mod + (dp[i][j-1])%mod)%mod;
                if(j > arr[i]) dp[i][j] = ((dp[i][j]%mod) - (dp[i-1][j-arr[i]-1]%mod))%mod;
            }
        }

        System.out.println(dp[n-1][s]);
    }
}
