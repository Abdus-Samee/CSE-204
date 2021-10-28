import java.util.Scanner;

public class Main {
    public static void menu(){
        System.out.println("         MENU         ");
        System.out.println("------------------------");
        System.out.println("     1. Insert an item");
        System.out.println("     2. Search Item");
        System.out.println("     3. Get In Order Successor");
        System.out.println("     4. Get In Order Predecessor");
        System.out.println("     5. Delete Item");
        System.out.println("     6. Get Item Depth");
        System.out.println("     7. Get Height");
        System.out.println("     8. Get Maximum Item");
        System.out.println("     9. Get Minimum Item");
        System.out.println("     10. Print Pre Order");
        System.out.println("     11. Print In Order");
        System.out.println("     12. Print Post Order");
        System.out.println("     13. Get Size");
        System.out.println("------------------------");
    }

    public static void main(String[] args) {
        BST bst = new BST();

        Scanner scanner = new Scanner(System.in);

        while(true){
            menu();

            int input = scanner.nextInt();

            if(input == 1){
                System.out.println("Enter an item to insert: ");
                int item = scanner.nextInt();
                bst.insertItem(item);
            }else if(input == 2){
                System.out.println("Enter an item to search: ");
                int item = scanner.nextInt();
                bst.searchItem(item);
            }else if(input == 3){
                System.out.println("Enter an item: ");
                int item = scanner.nextInt();
                System.out.println(bst.getInOrderSuccessor(item));
            }else if(input == 4){
                System.out.println("Enter an item: ");
                int item = scanner.nextInt();
                System.out.println(bst.getInOrderPredecessor(item));
            }else if(input == 5){
                System.out.println("Enter an item to delete: ");
                int item = scanner.nextInt();
                bst.deleteItem(bst.root, item);
            }else if(input == 6){
                System.out.println("Enter an item to find depth: ");
                int item = scanner.nextInt();
                System.out.println(bst.getItemDepth(bst.root, item, 0));
            }else if(input == 7){
                System.out.println("Height of tree is: " + bst.getHeight(bst.root));
            }else if(input == 8){
                System.out.println("Maximum item is: " + bst.getMaxItem());
            }else if(input == 9){
                System.out.println("Minimum item is: " + bst.getMinItem());
            }else if(input == 10){
                System.out.println("Printing Pre Order of tree: ");
                bst.printPreOrder(bst.root);
            }else if(input == 11){
                System.out.println("Printing In Order of tree: ");
                bst.printInOrder(bst.root);
            }else if(input == 12){
                System.out.println("Printing Post Order of tree: ");
                bst.printPostOrder(bst.root);
            }else if(input == 13){
                System.out.println("Size of tree is: " + bst.getSize(bst.root));
            }
        }
    }
}
