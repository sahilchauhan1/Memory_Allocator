import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Driver_alt {
    public static void printarray(A1DynamicMem fox) {
        //Dictionary free1 = fox.freeBlk.getFirst();
        //Dictionary alloc1 = fox.allocBlk.getFirst();
        for (Dictionary d = fox.freeBlk.getFirst(); d != null; d = d.getNext()) {
            System.out.print(d.address + " ");
            System.out.print( d.size + " ");
            System.out.print(d.key + " ");
            //System.out.print(d.key + " ");
            System.out.print("    ");
        }
        System.out.println(" ");
        for (Dictionary d = fox.allocBlk.getFirst(); d != null; d = d.getNext()) {
            System.out.print(d.address + " ");
            System.out.print(d.size + " ");
            System.out.print(d.key + " ");
            //System.out.print(d.key + " ");
            System.out.print("    ");
        }
        System.out.println("");
        System.out.println("");
    }
    public static void main(String args[]) throws IOException{
        File myObj = new File("./temptest.in");
        Scanner sc = new Scanner(myObj);
        int numTestCases;
        numTestCases = sc.nextInt();
        while (numTestCases-- > 0) {
            int size;
            size = sc.nextInt();
            A2DynamicMem obj = new A2DynamicMem(size,2);
            int numCommands = sc.nextInt();
            while (numCommands-- > 0) {
                String command;
                command = sc.next();
                int argument;
                argument = sc.nextInt();
                int result = -5;
//                boolean toPrint = true;
                switch (command) {
                    case "Allocate":
                        result = obj.Allocate(argument);
                        break;
                    case "Free":
                        result = obj.Free(argument);
                        break;
                    case "Defragment":
                        obj.Defragment();
//                        toPrint = false;
                        break;
                    default:
                        break;
                }
                System.out.println(command+" "+argument+" Output : "+result);
                printarray(obj);
            }

        }
        sc.close();
    }
}