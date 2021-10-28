import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, order;

        while(true){
            System.out.println("Enter size of array:");
            n = scanner.nextInt();
            System.out.println("Type 0 for ascending, 1 for descending or 2 for random order:");
            order = scanner.nextInt();

            int[] arr = new int[n];

            if(order == 0) ascendingOrder(arr);
            else if(order == 1) descendingOrder(arr);
            else randomOrder(arr);

            int[] mergeSortArray = new int[arr.length];
            int[] quickSortArray = new int[arr.length];

            for(int i = 0; i < mergeSortArray.length; i++) mergeSortArray[i] = arr[i];
            for(int i = 0; i < quickSortArray.length; i++) quickSortArray[i] = arr[i];

            long mergeStart = System.nanoTime();
            mergeSort(mergeSortArray, 0, mergeSortArray.length-1);
            long mergeEnd = System.nanoTime();

            long quickStart = System.nanoTime();
            quickSort(quickSortArray, 0, quickSortArray.length-1);
            long quickEnd = System.nanoTime();

            double mergeSortTime = ((double)(mergeEnd-mergeStart))/1000000.0;
            double quickSortTime = ((double)(quickEnd-quickStart))/1000000.0;

            System.out.println("Merge Sort Array\tQuick Sort Array");
            System.out.println("------------------------------------");
            for(int i = 0; i < arr.length; i++) System.out.println(mergeSortArray[i] + "\t\t\t" + quickSortArray[i]);

            System.out.println("\nMerge sort took " + String.format("%.12f", mergeSortTime) + " milli seconds.");
            System.out.println("Quicksort took " + String.format("%.12f", quickSortTime) + " milli seconds.\n");
        }
    }

    public static void ascendingOrder(int[] arr){
        Random random = new Random();
        arr[0] = random.nextInt();
        for(int i = 1; i < arr.length; i++) arr[i] = arr[i-1] + random.nextInt(100);
    }

    public static void descendingOrder(int[] arr){
        Random random = new Random();
        arr[0] = random.nextInt();
        for(int i = 1; i < arr.length; i++) arr[i] = arr[i-1] - random.nextInt(100);
    }

    public static void randomOrder(int[] arr){
        Random random = new Random();
        for(int i = 0; i < arr.length; i++) arr[i] = random.nextInt();
    }

    public static void mergeSort(int[] arr, int a, int b){
        if(a < b){
            int c = (a+b)/2;
            mergeSort(arr, a, c);
            mergeSort(arr, c+1, b);
            merge(arr, a, c, b);
        }
    }

    public static void merge(int[] arr, int a, int c, int b){
        int[] first = new int[c-a+1];
        int[] second = new int[b-c];

        for(int i = 0; i < (c-a+1); i++) first[i] = arr[a+i];
        for(int i = 0; i < (b-c); i++) second[i] = arr[c+1+i];

        int i = 0, j = 0, k = a;
        while((i < first.length) && (j < second.length)){
            if(first[i] <= second[j]) arr[k++] = first[i++];
            else arr[k++] = second[j++];
        }

        while(i < first.length) arr[k++] = first[i++];
        while(j < second.length) arr[k++] = second[j++];
    }

    public static void quickSort(int[] arr, int a, int b){
        if(a < b){
            int p = partition(arr, a, b);
            quickSort(arr, a, p-1);
            quickSort(arr, p+1, b);
        }
    }

    public static int randomPartition(int[] arr, int a, int b){
        Random random = new Random();
        int r = a + random.nextInt((b-a)+1);
        int temp = arr[r];
        arr[r] = arr[b];
        arr[b] = temp;

        return partition(arr, a, b);
    }

    public static int partition(int[] arr, int a, int b){
        int pivot = arr[b];
        int pivotIdx = a;

        for(int i = a; i < b; i++){
            if(arr[i] < pivot){
                int temp = arr[pivotIdx];
                arr[pivotIdx]  =arr[i];
                arr[i] = temp;

                pivotIdx++;
            }
        }
        int temp = arr[pivotIdx];
        arr[pivotIdx] = arr[b];
        arr[b] = temp;

        return pivotIdx;
    }
}
