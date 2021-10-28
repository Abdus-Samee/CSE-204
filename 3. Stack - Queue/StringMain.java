import java.util.Scanner;

public class StringMain {

    public static void main(String[] args) {
        CustomQueue queue = new CustomQueue();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String ans = "";
        int[] freq = new int[26];

        for(int i = 0; i < input.length(); i++){
            freq[input.charAt(i) - 'a']++;
            if(freq[input.charAt(i) - 'a'] == 1) queue.enqueue(input.charAt(i));

            while(!queue.isEmpty()){
                if(freq[queue.peek() - 'a'] == 1){
                    ans += queue.peek();
                    break;
                }else{
                    queue.dequeue();
                }
            }

            if(queue.isEmpty()) ans += '#';
        }

        System.out.println(ans);
    }
}
