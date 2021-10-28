import java.util.Scanner;

public class Main {
    public static boolean checkPrecedence(String curr, String top){
        if(((curr.equals("*")) || (curr.equals("/"))) && ((top.equals("+")) || (top.equals("-")))) return false;

        return true;
    }

    public static String operate(String operator, String val1, String val2){
        if(operator.equals("+")) return String.valueOf(Integer.parseInt(val1) + Integer.parseInt(val2));
        else if(operator.equals("-")) return String.valueOf(Integer.parseInt(val1) - Integer.parseInt(val2));
        else if(operator.equals("*")) return String.valueOf(Integer.parseInt(val1) * Integer.parseInt(val2));
        else if(operator.equals("/") && val2.equals("0")) throw new ArithmeticException("Division by zero");

        return String.valueOf(Integer.parseInt(val1) / Integer.parseInt(val2));
    }

    public static String postfix(String input){
        String res = "";
        CustomStack st = new CustomStack();

        for(int i = 0; i < input.length(); i++){
            if(Character.isDigit(input.charAt(i))) {
                int num = 0;
                int j;
                for(j = i; j < input.length(); j++){
                    if(Character.isDigit(input.charAt(j))){
                        num = num*10 + Integer.parseInt(String.valueOf(input.charAt(j)));
                    }else{
                        i = j-1;
                        break;
                    }
                }

                res += num + ",";
                if(j >= input.length()) break;
            }
            else if(input.charAt(i) == '('){
                if(i != 0 && (Character.isDigit(input.charAt(i-1)) || input.charAt(i-1) == ')')) return "error";

                st.push(String.valueOf(input.charAt(i)));
            }
            else if(input.charAt(i) == ')'){
                if(st.isEmpty()) return "error";
                while(!st.isEmpty() && !st.peek().equals("(")){
                    res += st.pop() + ",";
                }
                st.pop();
            }
            else{
                if(input.charAt(i) == '-' && input.charAt(i-1) == '('){
                    int num = 0;
                    int j;
                    for(j = i+1; j < input.length(); j++){
                        if(Character.isDigit(input.charAt(j))){
                            num = num*10 + Integer.parseInt(String.valueOf(input.charAt(j)));
                        }else{
                            break;
                        }
                    }

                    if(input.charAt(j) == ')'){
                        st.pop();
                        String t = "(-" + num + "),";
                        res += t;
                        i = j;
                    }else{
                        return "error";
                    }
                }
                else if(st.isEmpty() || st.peek().equals("(")) st.push(String.valueOf(input.charAt(i)));
                else{
                    while(!st.isEmpty() && !st.peek().equals("(") && checkPrecedence(String.valueOf(input.charAt(i)), st.peek())){
                        res += st.pop() + ",";
                    }
                    st.push(String.valueOf(input.charAt(i)));
                }
            }
        }

        while(!st.isEmpty()){
            if(st.peek().equals("(")) return "error";
            else res += st.pop() + ",";
        }

        return res;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        String expression = postfix(input);
        CustomStack valueStack = new CustomStack();

        if(expression.equals("error")) System.out.println("Not valid");
        else{
            for(int i = 0; i < expression.length(); i++){
                if(Character.isDigit(expression.charAt(i))) {
                    int num = 0;
                    while(i < expression.length()){
                        if(Character.isDigit(expression.charAt(i))){
                            num = num*10 + Integer.parseInt(String.valueOf(expression.charAt(i)));
                            i++;
                        }
                        else{
                            break;
                        }
                    }

                    valueStack.push(String.valueOf(num));
                }
                else if(expression.charAt(i) == '('){
                    int num = 0;
                    i += 2;
                    while(i < expression.length()){
                        if(Character.isDigit(expression.charAt(i))){
                            num = num*10 + Integer.parseInt(String.valueOf(expression.charAt(i)));
                            i++;
                        }else{
                            break;
                        }
                    }

                    String t = "-" + num;
                    valueStack.push(t);
                    ++i;
                }
                else{
                    String second = valueStack.pop();
                    String  first= valueStack.pop();
                    valueStack.push(operate(String.valueOf(expression.charAt(i)), first, second));
                    i++;
                }
            }

            System.out.println("Valid expression, Computed value: " + valueStack.pop());
        }
    }
}