
/**
 *
 Licensing Information:  You are free to use or extend these projects for
 # personal and educational purposes provided that (1) you do not distribute
 # or publish solutions, (2) you retain this notice, and (3) you provide clear
 # attribution to UT Dallas, including a link to http://cs.utdallas.edu.
 #
 # This file is part of Project for CE|CS|SE 3345: DataStructure and Introduction to Algorithms.
 #
 # Anjum Chida (anjum.chida@utdallas.edu)
 #
 #
 */
import java.io.File;
import java.io.PrintWriter;
import java.util.*;
public class Main {
    public static void main(String[] args) {


        Scanner in;
        if (args.length!=2) {
            System.out.print("Error Incorrect Arguments:" + Arrays.toString(args));
            System.exit(0);

        }
        try {
            File input_file = new File(args[0]);
            in = new Scanner(input_file);
            File output_file = new File(args[1]);
            PrintWriter  out;
            out = new PrintWriter(output_file);

            LazyBinarySearchTree myTree = new LazyBinarySearchTree();

            String operation = "";
            int lineno = 0;


            int key = 0;
            boolean result;

            whileloop:
            while (in.hasNextLine()) {
                lineno++;

                operation = in.nextLine().trim();
                if(operation.contains(":")){
                    key = Integer.parseInt(operation.substring(operation.indexOf(':')+1));
                    operation = operation.substring(0,operation.indexOf(':'));
                }
                //System.out.println(operation);

                switch (operation) {

                    case "Insert":
                        try {
                            //System.out.println(key);
                            result = myTree.insert(key);
                            out.println(result ? "True" :"False");
                        }
                        catch (Exception e){
                            out.println(e.getMessage());
                        }

                        break;
                    case "Contains":
                        try {
                            result = myTree.contains(key);
                            out.println(result ? "True" :"False");

                        }
                        catch (Exception e){
                            out.println("Error");
                        }
                        break;
                    case "Delete":
                        try{
                            result = myTree.delete(key);
                            out.println(result ? "True" :"False");
                        }
                        catch (Exception e){
                            out.println("Error for delete");
                        }

                        break;

                    case "PrintTree":
                        //System.out.print("**** ");
                        out.println(myTree);
                        break;


                    case "FindMin":
                        out.println(myTree.findMin());
                        break;

                    case "FindMax":
                        out.println(myTree.findMax());
                        break;

                    case "Size":
                        out.println(myTree.size());
                        break;

                    case "Height":
                        out.println(myTree.height());
                        break;

                    default:
                        out.println("Error in Line: " + operation);

                }

            }
            in.close();
            out.close();

        }
        catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }


    }


}