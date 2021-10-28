public class Main {
    public static void main(String[] args) {
        Array array = new Array();
        array.add("a");
        array.add("b");
        array.add("b");
        array.add("c");

        String[] res = array.getArray();
        for(String s : res) System.out.println(s);

        array.remove("b");
        res = array.getArray();
        System.out.println("Array after removal of b");
        for(String s : res) System.out.println(s);

        array.add(0, "x");
        res = array.getArray();
        System.out.println("Array after insertion of x");
        for(String s : res) System.out.println(s);
    }
}
