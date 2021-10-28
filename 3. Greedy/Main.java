import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input;

        input = scanner.nextLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);

        int[] plantPrice = new int[n];
        input = scanner.nextLine().split(" ");
        for(int i = 0; i < n; i++) plantPrice[i] = Integer.parseInt(input[i]);

        int[] friendPlant = new int[k];

        sort(plantPrice, 0, plantPrice.length-1);

        int minCost = 0, idx = 0;
        for(int i = 0; i < plantPrice.length; i++){
            minCost += plantPrice[i]*(friendPlant[idx]+1);
            friendPlant[idx] += 1;
            idx = (idx+1)%k;
        }

        System.out.println(minCost);
    }

    public static void sort(int[] arr, int a, int b){
        if(a < b){
            int m = (a+b)/2;
            sort(arr, a, m);
            sort(arr, m+1, b);
            combine(arr, a, m, b);
        }
    }

    public static void combine(int[] arr, int a, int m, int b){
        int[] first = new int[m-a+1];
        int[] second = new int[b-m];

        for(int i = 0; i < (m-a+1); i++) first[i] = arr[a+i];
        for(int i = 0; i < (b-m); i++) second[i] = arr[m+i+1];

        int i = 0, j = 0, k = a;
        while((i < first.length) && (j < second.length)){
            if(first[i] >= second[j]) arr[k++] = first[i++];
            else arr[k++] = second[j++];
        }

        while(i < first.length) arr[k++] = first[i++];
        while(j < second.length) arr[k++] = second[j++];
    }
}
